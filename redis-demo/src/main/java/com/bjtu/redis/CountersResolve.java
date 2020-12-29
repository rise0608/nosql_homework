package com.bjtu.redis;

import java.util.List;

public class CountersResolve {
    List<CountersSpec> counterList;
    CountersSpec countersSpec;

    public CountersResolve() {
        counterList = Main.getCounterList();
    }

    public void executeCounter(String counterName) {
        for (CountersSpec countersSpec : counterList) {
            if (countersSpec.getCounterName().equals(counterName)) {
                this.countersSpec = countersSpec;
            }
        }
        System.out.println("Execute counter: " + countersSpec.getCounterName());
        int num;
        String time;
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
                jedis.incrBy(countersSpec.getKeyFields(),Long.parseLong(countersSpec.getValueFields()));
                break;

            case "3":
                System.out.println("keyFields: " + countersSpec.getKeyFields());
                System.out.println("ValueFields: " + countersSpec.getValueFields());
                jedis.decrBy(countersSpec.getKeyFields(),Long.parseLong(countersSpec.getValueFields()));
                break;

            case "4":
                System.out.println("keyFields:" + countersSpec.getKeyFields());
                System.out.println("Fields:" + countersSpec.getFields());
                time = countersSpec.getFields().substring(0, 14) + "00:00";
                if (jedis.hget(countersSpec.getKeyFields(), time) != null) {
                    num = Integer.parseInt(jedis.hget(countersSpec.getKeyFields(), time));
                    System.out.println("num (at recent Fields):" + num);
                } else
                    System.out.println("num (at recent Fields): null");
                break;

            case "5":
                System.out.println("keyFields: " + countersSpec.getKeyFields());
                System.out.println("Fields: " + countersSpec.getFields());
                System.out.println("ValueFields: " + countersSpec.getValueFields());
                time = countersSpec.getFields().substring(0, 14) + "00:00";
                jedis.hincrBy(countersSpec.getKeyFields(), time,Long.parseLong(countersSpec.getValueFields()));
                break;

            case "6":
                System.out.println("keyFields: " + countersSpec.getKeyFields());
                System.out.println("Fields: " + countersSpec.getFields());
                System.out.println("ValueFields: " + countersSpec.getValueFields());
                time = countersSpec.getFields().substring(0, 14) + "00:00";
                jedis.hincrBy(countersSpec.getKeyFields(), time,-Long.parseLong(countersSpec.getValueFields()));
                break;
        }
    }
}
