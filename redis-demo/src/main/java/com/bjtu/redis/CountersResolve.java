package com.bjtu.redis;

import java.util.List;

public class CountersResolve {
    List<CountersSpec> counterList;
    CountersSpec countersSpec;

    public CountersResolve() {
        counterList = Main.getCounterList();
    }

    public void executeCounter(String counterName) throws Exception {
        for (CountersSpec countersSpec : counterList) {
            if (countersSpec.getCounterName().equals(counterName)) {
                this.countersSpec = countersSpec;
            }
        }
        System.out.println("执行counter: " + countersSpec.getCounterName());
        int num;
        String t, t1;
        RedisUtil jedis = new RedisUtil();

        switch (countersSpec.getCounterIndex()) {
            case "1":
                System.out.println("keyFields: " + countersSpec.getKeyFields());
                num = 0;
                if (jedis.get(countersSpec.getKeyFields()) != null) {
                    num = Integer.parseInt(jedis.get(countersSpec.getKeyFields()));
                    System.out.println("当前num值为：" + num);
                } else
                    System.out.println("当前num值为空");
                break;

            case "2":
                System.out.println("keyFields: " + countersSpec.getKeyFields());
                System.out.println("ValueFields: " + countersSpec.getValueFields());
                num = 0;

                if (jedis.get(countersSpec.getKeyFields()) != null) {
                    num = Integer.parseInt(jedis.get(countersSpec.getKeyFields()));
                }
                num = num + Integer.parseInt(countersSpec.getValueFields());
                jedis.set(countersSpec.getKeyFields(), String.valueOf(num));
                break;

            case "3":
                System.out.println("keyFields: " + countersSpec.getKeyFields());
                System.out.println("ValueFields: " + countersSpec.getValueFields());
                num = 0;
                if (jedis.get(countersSpec.getKeyFields()) != null) {
                    num = Integer.parseInt(jedis.get(countersSpec.getKeyFields()));
                }
                num = num - Integer.parseInt(countersSpec.getValueFields());
                jedis.set(countersSpec.getKeyFields(), String.valueOf(num));
                break;

            case "4":
                System.out.println("keyFields:" + countersSpec.getKeyFields());
                System.out.println("Fields:" + countersSpec.getFields());
                t = countersSpec.getFields();
                t1 = t.substring(0, 14) + "00:00";
                num = 0;
                if (jedis.hget(countersSpec.getKeyFields(), t1) != null) {
                    num = Integer.parseInt(jedis.hget(countersSpec.getKeyFields(), t1));
                    System.out.println("在当前Fields下num为：" + num);
                } else
                    System.out.println("在当前Fields下num为空");
                break;

            case "5":
                System.out.println("keyFields: " + countersSpec.getKeyFields());
                System.out.println("Fields: " + countersSpec.getFields());
                System.out.println("ValueFields: " + countersSpec.getValueFields());
                t = countersSpec.getFields();
                t1 = t.substring(0, 14) + "00:00";
                num = 0;
                if (jedis.hget(countersSpec.getKeyFields(), t1) != null) {
                    num = Integer.parseInt(jedis.hget(countersSpec.getKeyFields(), t1));
                }
                num = num + Integer.parseInt(countersSpec.getValueFields());
                jedis.hset(countersSpec.getKeyFields(), t1, String.valueOf(num));
                break;

            case "6":
                System.out.println("keyFields: " + countersSpec.getKeyFields());
                System.out.println("Fields: " + countersSpec.getFields());
                System.out.println("ValueFields: " + countersSpec.getValueFields());
                t = countersSpec.getFields();
                t1 = t.substring(0, 14) + "00:00";
                num = 0;
                if (jedis.hget(countersSpec.getKeyFields(), t1) != null) {
                    num = Integer.parseInt(jedis.hget(countersSpec.getKeyFields(), t1));
                }
                num = num - Integer.parseInt(countersSpec.getValueFields());
                jedis.hset(countersSpec.getKeyFields(), t1, String.valueOf(num));
                break;
        }
    }
}
