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
        System.out.println("Execute counter: " + countersSpec.getCounterName());
        int num = 0;
        String time, time1;
        RedisUtil jedis = new RedisUtil();

        switch (countersSpec.getCounterIndex()) {
            case "1":
                System.out.println("keyFields: " + countersSpec.getKeyFields());
                if (jedis.get(countersSpec.getKeyFields()) != null) {
                    num = Integer.parseInt(jedis.get(countersSpec.getKeyFields()));
                    System.out.println("num: " + num);
                } else
                    System.out.println("num: null");
                break;

            case "2":
                System.out.println("keyFields: " + countersSpec.getKeyFields());
                System.out.println("ValueFields: " + countersSpec.getValueFields());
                if (jedis.get(countersSpec.getKeyFields()) != null) {
                    num = Integer.parseInt(jedis.get(countersSpec.getKeyFields()));
                }
                num = num + Integer.parseInt(countersSpec.getValueFields());
                jedis.set(countersSpec.getKeyFields(), String.valueOf(num));
                break;

            case "3":
                System.out.println("keyFields: " + countersSpec.getKeyFields());
                System.out.println("ValueFields: " + countersSpec.getValueFields());
                if (jedis.get(countersSpec.getKeyFields()) != null) {
                    num = Integer.parseInt(jedis.get(countersSpec.getKeyFields()));
                }
                num = num - Integer.parseInt(countersSpec.getValueFields());
                jedis.set(countersSpec.getKeyFields(), String.valueOf(num));
                break;

            case "4":
                System.out.println("keyFields:" + countersSpec.getKeyFields());
                System.out.println("Fields:" + countersSpec.getFields());
                time = countersSpec.getFields();
                time1 = time.substring(0, 14) + "00:00";
                if (jedis.hget(countersSpec.getKeyFields(), time1) != null) {
                    num = Integer.parseInt(jedis.hget(countersSpec.getKeyFields(), time1));
                    System.out.println("num (at recent Fields):" + num);
                } else
                    System.out.println("num (at recent Fields): null");
                break;

            case "5":
                System.out.println("keyFields: " + countersSpec.getKeyFields());
                System.out.println("Fields: " + countersSpec.getFields());
                System.out.println("ValueFields: " + countersSpec.getValueFields());
                time = countersSpec.getFields();
                time1 = time.substring(0, 14) + "00:00";
                if (jedis.hget(countersSpec.getKeyFields(), time1) != null) {
                    num = Integer.parseInt(jedis.hget(countersSpec.getKeyFields(), time1));
                }
                num = num + Integer.parseInt(countersSpec.getValueFields());
                jedis.hset(countersSpec.getKeyFields(), time1, String.valueOf(num));
                break;

            case "6":
                System.out.println("keyFields: " + countersSpec.getKeyFields());
                System.out.println("Fields: " + countersSpec.getFields());
                System.out.println("ValueFields: " + countersSpec.getValueFields());
                time = countersSpec.getFields();
                time1 = time.substring(0, 14) + "00:00";
                if (jedis.hget(countersSpec.getKeyFields(), time1) != null) {
                    num = Integer.parseInt(jedis.hget(countersSpec.getKeyFields(), time1));
                }
                num = num - Integer.parseInt(countersSpec.getValueFields());
                jedis.hset(countersSpec.getKeyFields(), time1, String.valueOf(num));
                break;
        }
    }
}
