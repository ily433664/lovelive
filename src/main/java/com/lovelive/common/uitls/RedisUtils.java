package com.lovelive.common.uitls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * redis工具类
 *
 * @author dHe
 * @date 2019-6-18
 */
@Component
public class RedisUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisUtils.class);

    @Autowired(required = false)
    private JedisPool jedisPool;

    public String set(String key, HashMap<String, String> map) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedisPool.getResource().hmset(key, map);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public String set(byte[] key, HashMap<byte[], byte[]> map) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hmset(key, map);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public String set(String key, List<String> list) {
        try (Jedis jedis = jedisPool.getResource()) {
            Long aLong = -1l;
            for (String value : list
                    ) {
                aLong = jedis.lpush(key, value);
            }
            return aLong <= 0 ? "error" : "OK";
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public String set(byte[] key, List<byte[]> list) {
        try (Jedis jedis = jedisPool.getResource()) {
            Long aLong = -1l;
            for (byte[] value : list
                    ) {
                aLong = jedis.lpush(key, value);
            }
            return aLong <= 0 ? "error" : "OK";
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public String set(String key, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.set(key, value);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public String set(byte[] key, byte[] value) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.set(key, value);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public String set(String key, Set<String> set) {
        try (Jedis jedis = jedisPool.getResource()) {
            Long aLong = -1l;
            for (String value : set
                    ) {
                aLong = jedis.sadd(key, value);
            }
            return aLong <= 0 ? "error" : "OK";
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public String set(byte[] key, Set<byte[]> set) {
        try (Jedis jedis = jedisPool.getResource()) {
            Long aLong = -1l;
            for (byte[] value : set
                    ) {
                aLong = jedis.sadd(key, value);
            }
            return aLong <= 0 ? "error" : "OK";
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    //redis写入对象，用序列化对象的方式
    public String set(String key, Object Obj) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.set(key.getBytes(), SerializationUtils.serialize(Obj));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public Object get(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            Object object = null;
            //获取value的类型
            String type = jedis.type(key);
            //根据不同类型调用不同的获取方法
            if (type.toLowerCase().equals("map")) {
                object = jedis.hgetAll(key);
            } else if (type.toLowerCase().equals("set")) {
                object = jedis.smembers(key);
            } else if (type.toLowerCase().equals("list")) {
                object = jedis.lrange(key, 0, -1);
            } else if (type.toLowerCase().equals("string")) {
                byte[] bytes = jedis.get(key.getBytes());
                object = SerializationUtils.deserialize(bytes);
                if (object == null) {
                    object = jedis.get(key);
                }
            }
            return object;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public Object get(byte[] key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.get(key);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public Long delete(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.del(key);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public String update(String key, Object value) {
        try (Jedis jedis = jedisPool.getResource()) {
            //先删除原有数据
            Long along = jedis.del(key);
            if (along < 0) {
                return "error";
            }
            //再重新添加
            if (value instanceof Map) {
                set(key, (Map) value);
            } else if (value instanceof Set) {
                set(key, (Set) value);
            } else if (value instanceof List) {
                set(key, (List) value);
            } else if (value instanceof String) {
                set(key, (String) value);
            } else {
                set(key, value);
            }
            return "OK";
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

}
