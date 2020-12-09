package com.bjtu.redis;

import redis.clients.jedis.Jedis;
import java.util.*;

// redis常用操作封装工具类
public class RedisUtil {

    //获取连接
    public  Jedis connection() {
        return JedisInstance.getInstance().getResource();
    }

    //==================================string===================================
    //为string添加元素
    public void set(String key, String value) throws Exception {
        Jedis jedis = connection();
        jedis.set(key,value);

    }

    //获取string
    public String get(String key) throws Exception {
        Jedis jedis = connection();
        return jedis.get(key);
    }


    //追加string
    public void append(String key, String value) throws Exception {
        Jedis jedis = connection();
        jedis.append(key,value);
    }

    //==================================hash===================================
    //为hash添加元素
    public void hset(String key, String field, String value) throws Exception {
        Jedis jedis = connection();
        jedis.hset(key,field,value);
    }

    //获取hash元素
    public String hget(String key, String field) throws Exception {
        Jedis jedis = connection();
        return jedis.hget(key,field);
    }

    //==================================set===================================
    //添加set
    public void sadd(String key, Set<String> value) throws Exception {
        Jedis jedis = connection();
        for(String str: value){
            jedis.sadd(key, str);
        }
    }

    //set删除指定元素
    public void srem(String key, Set<String> value) throws Exception {
        Jedis jedis = connection();
        for (String str : value) {
            jedis.srem(key, str);
        }
    }

    //获取key对应的value总数
    public Long scard(String key) throws Exception {
        Jedis jedis = connection();
        return jedis.scard(key);
    }

    //获取key对应的所有value
    public Set<String> smembers(String key) throws Exception {
        Jedis jedis = connection();
        return jedis.smembers(key);
    }

    //判断set是否存在
    public boolean sismember(String key, String value) throws Exception {
        Jedis jedis = connection();
        return jedis.sismember(key,value);
    }

    //随机获取数据
    public String srandmember(String key) throws Exception {
        Jedis jedis = connection();
        return jedis.srandmember(key);
    }

    //==================================list===================================
    //向list添加元素
    public void lpush(String key, List<String> list) throws Exception {
        Jedis jedis = connection();
        for(String s: list){
            jedis.lpush(key,s);
        }
    }

    //获取list
    public List<String> lrange(String key, Integer start, Integer end)
            throws Exception {
        Jedis jedis = connection();
        return jedis.lrange(key, start, end);
    }

    //删除任意类型的key
    public void del(String key) throws Exception {
        Jedis jedis = connection();
        jedis.del(key);
    }

    //==================================map===================================
    //设置map
    public void hmset(String key, Map<String, String> map) throws Exception {
        Jedis jedis = connection();
        jedis.hmset(key,map);
    }

    //获取map的key的个数
    public Long hlen(String key) throws Exception {
        Jedis jedis = connection();
        return jedis.hlen(key);
    }

    //获取map中所有key
    public Set<String> hkeys(String key) throws Exception {
        Jedis jedis = connection();
        return jedis.hkeys(key);
    }

    //获取map中所有value
    public List<String> hvals(String key) throws Exception {
        Jedis jedis = connection();
        return jedis.hvals(key);
    }

    //获取map中的指定key的value
    public List<String> hmget(String key, String... params) {
        Jedis jedis = connection();
        if (null == params || params.length == 0) {
            throw new RuntimeException(this.getClass().getSimpleName()+  "::"
                    + new Exception().getStackTrace()[0].getMethodName()+"parameter can not be null");
        }
        return jedis.hmget(key,params);
    }

    //获取map所有的key和value
    public Map<String, String> hgetAll(String key)
            throws Exception {
        Jedis jedis = connection();
        return jedis.hgetAll(key);
    }

    //删除指定key的map
    public void hdel(String key, String... params) {
        Jedis jedis = connection();
        if (null == params || params.length == 0) {
            throw new RuntimeException(this.getClass().getSimpleName()+  "::"
                    + new Exception().getStackTrace()[0].getMethodName()+"parameter can not be null");
        }
        jedis.hdel(key,params);
    }
};

