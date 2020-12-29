package com.bjtu.redis;

import redis.clients.jedis.Jedis;
import java.util.*;

// redis常用操作封装工具类
public class RedisUtil {

    //获取连接
    private final Jedis jedis = JedisInstance.getInstance().getResource();

    //==================================string===================================

    //string的value在遇到incr, decr操作时会转成数值型进行计算
    //数值加1
    public void incr(String key){
        jedis.incr(key);
    }

    //数值减1
    public void decr(String key)
    {
        jedis.decr(key);
    }

    //数值加num
    public void incrBy(String key, long num){
        jedis.incrBy(key, num);
    }

    //数值减num
    public void decrBy(String key, long num)
    {
        jedis.decrBy(key, num);
    }

    //为string添加元素
    public void set(String key, String value) {
        jedis.set(key,value);
    }

    //获取string
    public String get(String key) {
        return jedis.get(key);
    }

    //追加string
    public void append(String key, String value) {
        jedis.append(key,value);
    }

    //==================================hash===================================

    //hash数值加num
    public void hincrBy(String key, String field, long num)
    {
        jedis.hincrBy(key, field, num);
    }

    //为hash添加元素
    public void hset(String key, String field, String value) {
        jedis.hset(key,field,value);
    }

    //获取hash元素
    public String hget(String key, String field) {
        return jedis.hget(key,field);
    }

    //==================================set===================================
    //添加set
    public void sadd(String key, Set<String> value) {
        for(String str: value){
            jedis.sadd(key, str);
        }
    }

    //set删除指定元素
    public void srem(String key, Set<String> value) {
        for (String str : value) {
            jedis.srem(key, str);
        }
    }

    //获取key对应的value总数
    public Long scard(String key) {
        return jedis.scard(key);
    }

    //获取key对应的所有value
    public Set<String> smembers(String key) {
        return jedis.smembers(key);
    }

    //判断set是否存在
    public boolean sismember(String key, String value) {
        return jedis.sismember(key,value);
    }

    //随机获取数据
    public String srandmember(String key) {
        return jedis.srandmember(key);
    }

    //==================================list===================================
    //向list添加元素
    public void lpush(String key, List<String> list) {
        for(String s: list){
            jedis.lpush(key,s);
        }
    }

    //获取list
    public List<String> lrange(String key, Integer start, Integer end) {
        return jedis.lrange(key, start, end);
    }

    //删除任意类型的key
    public void del(String key) {
        jedis.del(key);
    }

    //==================================map===================================
    //设置map
    public void hmset(String key, Map<String, String> map) {
        jedis.hmset(key,map);
    }

    //获取map的key的个数
    public Long hlen(String key) {
        return jedis.hlen(key);
    }

    //获取map中所有key
    public Set<String> hkeys(String key) {
        return jedis.hkeys(key);
    }

    //获取map中所有value
    public List<String> hvals(String key) {
        return jedis.hvals(key);
    }

    //获取map中的指定key的value
    public List<String> hmget(String key, String... params) {
        if (null == params || params.length == 0) {
            throw new RuntimeException(this.getClass().getSimpleName()+  "::"
                    + new Exception().getStackTrace()[0].getMethodName()+"parameter can not be null");
        }
        return jedis.hmget(key,params);
    }

    //获取map所有的key和value
    public Map<String, String> hgetAll(String key) {
        return jedis.hgetAll(key);
    }

    //删除指定key的map
    public void hdel(String key, String... params) {
        if (null == params || params.length == 0) {
            throw new RuntimeException(this.getClass().getSimpleName()+  "::"
                    + new Exception().getStackTrace()[0].getMethodName()+"parameter can not be null");
        }
        jedis.hdel(key,params);
    }
}

