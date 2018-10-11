package com.bootdo.common.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: RedisDAO
 * @Description: redisDao 提供redis 相关服务接口
 */
public interface RedisService {

	public void sendMessage(String channel, Serializable message);

	/** 
	 * 批量删除对应的value 
	 * 
	 * @param keys 
	 */
	public void remove(final String... keys);

	/** 
	 * 批量删除key 
	 * 
	 * @param pattern 
	 */
	public void removePattern(final String pattern);

	/** 
	 * 删除对应的value 
	 * 
	 * @param key 
	 */
	public void remove(final String key);

	/** 
	 * 判断缓存中是否有对应的value 
	 * 
	 * @param key 
	 * @return 
	 */
	public boolean exists(final String key);

	/** 
	 * 读取缓存 
	 * 
	 * @param key 
	 * @return 
	 */
	public Object get(final String key);

	/** 
	 * 写入缓存 
	 * 
	 * @param key 
	 * @param value 
	 * @return 
	 */
	public boolean set(final String key, Object value);

	//获取set
	 public Object getSet(final String key);

	/**
	 * 写入缓存
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, Object value, Long expireTime);

	/**
	 * 写入缓存(hashMap)
	 *
	 * @param key
	 * @return
	 */
	public boolean setHash(final String key, String hashKey, Object hashValue, Long time);

	/**
	 * 写入缓存(set)
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setAdd(String key, String value);

	public boolean removeSetValue(String key, String value);

	public Object getHash(String key, String hashKey);

	public Object hmGet(String key, List<String> hashKey);

	/**
	     * 写入缓存(List) 
	     * 
	     * @param key 
	     * @param value 
	     * @return 
	     */
	public boolean setList(final String key, Object Value, Long time);

	/**
	 * 一次写入多个缓存
	 * @param key
	 * @param time
	 * @return
	 */
	public boolean hsetList(final String key, String[] value, Long time);

	List<Object> getList(String key);

	/**
	 * 
	 * @Title: tryLock 
	 * @Description: 获取redis锁
	 * @param @param key
	 * @param @param value
	 * @param @param timeout
	 * @param @return
	 * @return Boolean
	 * @throws
	 */
	Boolean tryLock(final String key, final String value, final Long timeout);

	/**
	 * 
	 * @Title: unLock 
	 * @Description: 释放redis锁
	 * @param @param key
	 * @return void
	 * @throws
	 */
	void unLock(final String key);

	/**
	 * 
	 * @Title: hmset 
	 * @Description: hash存储多个值
	 * @param @param key
	 * @param @param t
	 * @return void
	 * @throws
	 */
	public <T> void hmset(final String key, T t);

	/**
	 * 
	 * @Title: hmget 
	 * @Description: hash获取多个值
	 * @param @param key
	 * @param @param fields
	 * @param @return
	 * @return Map<String,String>
	 * @throws
	 */
	public Map<String, String> hmget(final String key, final String... fields);

	/**
	 * 
	 * @Title: hset 
	 * @Description: hash存储单个值
	 * @param @param key
	 * @param @param field
	 * @param @param value
	 * @return void
	 * @throws
	 */
	public void hset(final String key, final String field, final String value);

	/**
	 * 
	 * @Title: hget 
	 * @Description: hash获取单个值
	 * @param @param key
	 * @param @param fields
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String hget(final String key, final String fields);
	
	/**
	 * 
	 * @Title: hDelete 
	 * @Description: hash删除单个值
	 * @param @param key
	 * @param @param fields
	 * @return Long
	 * @throws
	 */
	public Long hDelete(final String key, final String fields);

	/**
	 * 
	 * @Title: hGetAll 
	 * @Description: hash获取全部
	 * @param @param key
	 * @param @return
	 * @return Map<String,String>
	 * @throws
	 */
	public Map<String, String> hGetAll(final String key);

	public boolean hasKey(final String key);

	/**
	 * 
	 * @Title: incr 
	 * @Description: 将key中储存的数字值增一。如果key不存在，那么key的值会先被初始化为 0 ，然后再执行incr操作。
	 * @param @param key
	 * @param @return
	 * @return Long 执行incr命令之后 key的值
	 * @throws
	 */
	Long incr(final String key);

	/**
	 * 
	 * @Title: incrBy 
	 * @Description: 将key所储存的值加上增量 increment。如果key不存在，那么key的值会先被初始化为 0 ，然后再执行incrBy命令。
	 * @param @param key
	 * @param @param value 
	 * @param @return
	 * @return Long 执行incrBy命令之后 key的值
	 * @throws
	 */
	Long incrBy(final String key, final long value);

	/**
	 * 
	 * @Title: decr 
	 * @Description: 将key中储存的数字值减一。如果 key不存在，那么key的值会先被初始化为 0 ，然后再执行 decr操作。
	 * @param @param key
	 * @param @return
	 * @return Long
	 * @throws
	 */
	Long decr(final String key);

	/**
	 * 
	 * @Title: decrBy 
	 * @Description: 将key所储存的值减去减量 decrement。如果key不存在，那么key的值会先被初始化为 0 ，然后再执行decrBy操作。
	 * @param @param key
	 * @param @param value
	 * @param @return
	 * @return Long
	 * @throws
	 */
	Long decrBy(final String key, final long value);

	/**
	 * 
	 * @Title: getIncrOrDecrValue 
	 * @Description: 获取Incr或者Decr值
	 * @param @param key
	 * @param @return
	 * @return Long
	 * @throws
	 */
	Long getIncrOrDecrValue(final String key);

	/**
	 * 
	 * @Title: delete 
	 * @Description: 删除key
	 * @param @param key
	 * @return void
	 * @throws
	 */
	void delete(final String key);

	/**
	 * 
	 * @Title: deletePrefix 
	 * @Description: 模糊删除以指定key开头的所有
	 * @param @param key
	 * @return void
	 * @throws
	 */
	void deletePrefix(final String key);
}
