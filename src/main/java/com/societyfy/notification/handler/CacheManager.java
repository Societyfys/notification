package com.societyfy.notification.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class CacheManager<K, V> {
    private final RedisTemplate<K, V> redisTemplate;

    public void save(K key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void save(K key, V value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public V get(K key) {
        return redisTemplate.opsForValue().get(key);
    }

    public boolean hasKey(K key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    public boolean delete(K key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

    public <HK,HV> void hashOperationSave(K key,HK hashKey, HV hashValue){
        redisTemplate.opsForHash().put(key,hashKey,hashValue);
    }

    public <HK> boolean hashOperationsHasKey(K key, HK hashKey){
        return redisTemplate.opsForHash().hasKey(key,hashKey);
    }

    public <HK> Set<HK> hashOperationsKeys(K key){
        return (Set<HK>) redisTemplate.opsForHash().keys(key);
    }

    public void hashOperationsDelete(K key, Object... hashKeys){
        redisTemplate.opsForHash().delete(key);
    }
}

