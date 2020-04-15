
package com.demo.mongo;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 下面就是curd操作
 对应的我会写上 mongo 命令行的命令
 * */
@SpringBootTest(classes = MongoApplication.class)
@RunWith(value = SpringRunner.class)
public class MongoTest {

	@Autowired
	private MongoTemplate mongoTemplate;


	//下面curd 大致够用
	@Test
	public void insert() {
		//插入数据，具体看该方法的入参类型就知道，我直接用Map，因为我不写POJO
		//集合名是user ， 数据如下
		Map<String, Object> user = new HashMap<>();
		user.put("userId", 2);
		user.put("userName", "hwy");
		user.put("age", 18);
		//对应命令        db.user.insert({"userId":2, "userName":"hwy", "age":18})
		Map<String, Object> userTmp = mongoTemplate.insert(user,"user");
		System.out.println(userTmp);
	}

	@Test
	public void updateFirst() {
		Map<String, Object> user = new HashMap<>();
		user.put("userId", 2);
		Query query = new Query(Criteria.where("userId").is(user.get("userId")));
		Update update = new Update().set("age", 19);
		//对应命令        db.user.updateOne({"userId":2}, {$set:{"age":19}})
		UpdateResult updateResult = mongoTemplate.updateFirst(query, update, "user");
		System.out.println(updateResult);
	}

	@Test
	public void updateMulti() {
		//插入数据，具体看该方法的入参类型就知道，我直接用Map，因为我不写POJO
		Map<String, Object> user = new HashMap<>();
		user.put("userId", 2);
		Query query = new Query(Criteria.where("userId").is(user.get("userId")));
		Update update = new Update().set("age", 19);
		//对应命令        db.user.updateMany({"userId":2}, {$set:{"age":19}})
		UpdateResult updateResult = mongoTemplate.updateMulti(query, update, "user");
		System.out.println(updateResult);
	}

	@Test
	public void find() {
		//插入数据，具体看该方法的入参类型就知道，我直接用Map，因为我不写POJO
		Map<String, Object> user = new HashMap<>();
		user.put("userId", 2);
		Query query = new Query(Criteria.where("userId").is(user.get("userId")));
		//对应命令        db.user.find({"userId":2})
		List<Map> users = mongoTemplate.find(query, Map.class, "user");
		System.out.println(users);
	}

	@Test
	public void remove() {
		//插入数据，具体看该方法的入参类型就知道，我直接用Map，因为我不写POJO
		Map<String, Object> user = new HashMap<>();
		user.put("userId", 2);
		Query query = new Query(Criteria.where("userId").is(user.get("userId")));
		//对应命令        db.user.remove({"userId":2})
		DeleteResult deleteResult = mongoTemplate.remove(query,"user");
		System.out.println(deleteResult);
	}

	//聚合函数也是经常用，就是类似sql的 count, group by, limit 等等
	//聚合函数看上去很麻烦，你最好写一堆示例，我可记不得语法，没示例看不懂语法
	//你要先熟悉命令行的写法，再转成 mongoTemplate 的写法
	@Test
	public void aggregate() {
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.skip(0L),
				Aggregation.limit(1)
		);
		//相当于分页， $skip 是跳过前几条， $limit 是查询几条出来
		//SQL类似于  select * from user limit $skip, $limit
		//对应命令        db.user.aggregate([{$skip:0}, {$limit:1}])
		AggregationResults aggregationResults = mongoTemplate.aggregate(aggregation,"user", Map.class);
		List list = aggregationResults.getMappedResults();
		System.out.println(aggregationResults);
		System.out.println(list);
	}


}
