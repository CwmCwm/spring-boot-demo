
package com.demo.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@SpringBootTest(classes = RedisApplication.class)
@RunWith(value = SpringRunner.class)
public class RedisTest {

	//RedisConfiguration 中定义 RedisTemplate<String, Object>
	@Autowired
	RedisTemplate<String, Object> redisTemplate;


	//redisTemplate.opsForValue() 其他方法猜测验证一下就知道了，你知道命令行就能轻松猜测
	//测试redis的 string 类型在springboot中的如何使用
	@Test
	public void opsForValue() {
		//user:10:name 这个命名规则是 表名:主键值:字段名。  使用冒号: 是大家都用冒号
		//当然不推荐这种方式，因为用redis的hash类型更好
		//所以redis的string类型使用场景就是单纯的1对1，如没想到什么场景
		String key = "user:10:name";
		String value = "hwy";
		//等价于redis命令=>        set user:10:name hwy
		redisTemplate.opsForValue().set(key, value);
		//等价于redis命令=>        get user:10:name
		String value1 = (String) redisTemplate.opsForValue().get(key);
		System.out.println(value1);
		//设置10s后过期
		//等价于redis命令=>        set user:10:name hwy EX 10
		redisTemplate.opsForValue().set(key, value, 10, TimeUnit.SECONDS);
	}


	//redisTemplate.opsForHash() 其他方法猜测验证一下就知道了，你知道命令行就能轻松猜测
	//因为RedisConfiguration中配置hash的value序列化方式采用jackson，所以在redis-cli命令行上会多 \"
	//测试redis的 hash 类型在springboot中的如何使用
	//场景：user表的记录缓存
	@Test
	public void opsForHash() {
		Map<String, Object> user_10 = new HashMap<>();
		user_10.put("name", "cwm");
		user_10.put("age", 18);
		user_10.put("sex", "男");
		//等价于redis命令=>        hmset user_10 name cwm age 18 sex 男
		redisTemplate.opsForHash().putAll("user_10", user_10);
		//等价于redis命令=>        hgetall user_10
		Map<Object, Object> user_10_tmp = redisTemplate.opsForHash().entries("user_10");
		System.out.println(user_10_tmp);
	}


	//redisTemplate.opsForList() 其他方法猜测验证一下就知道了，你知道命令行就能轻松猜测
	//测试redis的 list 类型在springboot中的如何使用
	//场景：排队，秒杀
	@Test
	public void opsForList() {
		//排队上公共汽车25路
		//等价于redis命令=>        rpush bus25 cwm
		redisTemplate.opsForList().rightPush("bus25", "cwm");
		redisTemplate.opsForList().rightPush("bus25", "hwy");
		redisTemplate.opsForList().rightPush("bus25", "hjb");

		//排队下公共汽车
		Object who = null;
		//等价于redis命令=>        rpop bus25
		while ((who = redisTemplate.opsForList().leftPop("bus25")) != null) {
			System.out.println(who.toString() + "下公共汽车");
		}
	}



	//redisTemplate.opsForSet() 其他方法猜测验证一下就知道了，你知道命令行就能轻松猜测
	//测试redis的 set 类型在springboot中的如何使用
	//场景：点赞
	@Test
	public void opsForSet() {
		//等价于redis命令=>        sadd article_1 cwm
		redisTemplate.opsForSet().add("article_1", "cwm");
		redisTemplate.opsForSet().add("article_1", "hwy");

		//等价于redis命令=>        scard article_1
		System.out.println(redisTemplate.opsForSet().size("article_1"));
		redisTemplate.opsForSet().add("article_1", "hwy");
		System.out.println(redisTemplate.opsForSet().size("article_1"));

		//等价于redis命令=>        smembers article_1
		System.out.println(redisTemplate.opsForSet().members("article_1"));

		//等价于redis命令=>        srem article_1 hwy
		redisTemplate.opsForSet().remove("article_1", "hwy");
		System.out.println(redisTemplate.opsForSet().members("article_1"));
	}



	//redisTemplate.opsForZSet() 其他方法猜测验证一下就知道了，你知道命令行就能轻松猜测
	//测试redis的 zset 类型在springboot中的如何使用
	//场景：排名
	@Test
	public void opsForZSet() {
		//数学分数排名
		//等价于redis命令=>        zadd math 100 cwm
		redisTemplate.opsForZSet().add("math", "cwm", 100);
		redisTemplate.opsForZSet().add("math", "hwy", 90);
		redisTemplate.opsForZSet().add("math", "hjb", 80);

		//等价于redis命令=>        zrange math 0 -1 WITHSCORES
		System.out.println(redisTemplate.opsForZSet().range("math", 0, -1));

		//等价于redis命令=>        zrevrange math 0 -1 WITHSCORES
		System.out.println(redisTemplate.opsForZSet().reverseRange("math", 0, -1));

		redisTemplate.opsForZSet().add("math", "cwm", 60);
		System.out.println(redisTemplate.opsForZSet().reverseRange("math", 0, -1));
	}




}
