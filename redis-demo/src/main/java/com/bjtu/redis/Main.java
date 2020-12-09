package com.bjtu.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

    private static List<CountersSpec> counterList;
    private static List<ActionsSpec> actionList;
    private static FileAlterationMonitor monitor;
    public static AtomicBoolean lock = new AtomicBoolean(false);

    public static List<CountersSpec> getCounterList() {
        return counterList;
    }

    // 加载JSON文件并更新counter/action对象
    public static void loadConfigJson() {
        String counterStr = readJsonFile("src/main/resources/counters.json");
        counterList = JSON.parseArray(Objects.requireNonNull
                (JSON.parseObject(counterStr, JSONObject.class))
                .getJSONArray("counters").toJSONString(), CountersSpec.class);
        readAction();
    }

    // 读取action
    public static void readAction() {
        actionList = new ArrayList<>();
        String path = "src/main/resources/actions.json";
        String s = readJsonFile(path);
        JSONObject jobj = JSON.parseObject(s);
        JSONArray actions = jobj.getJSONArray("actions");
        for (Object action : actions) {
            ArrayList<String> featureRetrieve = new ArrayList<>();
            ArrayList<String> saveCounter = new ArrayList<>();

            JSONObject key = (JSONObject) action;
            String actionName = (String) key.get("actionName");

            JSONArray retrieve = (JSONArray) key.get("featureRetrieve");
            JSONArray save = (JSONArray) key.get("saveCounter");

            for (int j = 0; j < retrieve.size(); j++) {
                JSONObject key2 = retrieve.getJSONObject(j);
                featureRetrieve.add((String) key2.get("counterName"));
            }

            for (int j = 0; j < save.size(); j++) {
                JSONObject key2 = save.getJSONObject(j);
                saveCounter.add((String) key2.get("counterName"));
            }

            ActionsSpec a = new ActionsSpec();
            a.setActionType(actionName);
            a.setFeatureRetrieve(featureRetrieve);
            a.setSaveCounter(saveCounter);

            actionList.add(a);
        }
    }

    // 读取json文件
    public static String readJsonFile(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuilder lastStr = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                lastStr.append(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return lastStr.toString();
    }

    // 启动文件监听
    private static void startObserver(String path, FileListener listener) {
        // 轮询间隔时间（1000）毫秒
        long interval = TimeUnit.SECONDS.toMillis(1);

        FileAlterationObserver observer = new FileAlterationObserver(path);
        observer.addListener(listener);

        // 创建文件变化监听器
        monitor = new FileAlterationMonitor(interval, observer);
        // 开始监听
        try {
            monitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // main方法
    public static void main(String[] args) throws Exception {
        System.out.println("基于redis的counter");
        System.out.println("18301034-陈佳悦\n");

        //启动文件监听
        startObserver("src/main/resources", new FileListener());
        System.out.println("文件监听已开启..");
        //加载json文件
        loadConfigJson();
        System.out.println("JSON文件加载完毕..");

        Scanner sc = new Scanner(System.in);
        String str;
        do {

            System.out.println("Actions列表(0退出)：");
            for (int i = 0; i < actionList.size(); i++) {
                System.out.println((i + 1) + ". " + actionList.get(i).getActionType());
            }
            System.out.println("请选择：");
            str = sc.nextLine();
            if (str.equals("1") || str.equals("2") || str.equals("3")
                    || str.equals("4") || str.equals("5") || str.equals("6")) {
                ActionsResolve actionsResolve = new ActionsResolve();
                actionsResolve.executeAction(actionList.get(Integer.parseInt(str) - 1));
            } else if (str.equals("0")) {
                monitor.stop();
                System.exit(1);
            } else {
                System.out.println("非法输入！请重新选择action序号（输入0退出）：");
            }
        }
        while (!str.equals("0"));
    }
}
