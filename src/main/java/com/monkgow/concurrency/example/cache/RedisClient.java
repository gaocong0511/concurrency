package com.monkgow.concurrency.example.cache;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

@Component
public class RedisClient {

    @Resource(name = "redisPool")
    private JedisPool jedisPool;

    /**
     * 向Redis之中添加值
     *
     * @param key   键
     * @param value 值
     * @throws Exception
     */
    public void setJedisPool(String key, String value) throws Exception {
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 从Redis之中取值
     *
     * @param key 键
     * @return 取出的对应的值
     * @throws Exception
     */
    public String get(String key) throws Exception {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
