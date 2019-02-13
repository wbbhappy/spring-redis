package com.spring.redis.controller;

import com.spring.redis.pojo.User;
import com.spring.redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;

@Controller
@RequestMapping("/")
public class RedisController {
	@Autowired
	private RedisUtil redisUtil;

	@RequestMapping(value = "add", method = RequestMethod.GET)
	@ResponseBody
	public String add() {
		System.out.println("================华丽进=======================");
		this.redisUtil.set("test", "aaa");
		System.out.println("================华丽出=======================");
		return "redis添加数据成功！";
	}

	@RequestMapping(value = "get", method = RequestMethod.GET)
	@ResponseBody
	public String get() {
		System.out.println("================获取redis中数据=======================");
		return String.valueOf(this.redisUtil.get("test"));
	}

	@RequestMapping(value = "addHash", method = RequestMethod.GET)
	@ResponseBody
	public String addHash() {
		System.out.println("================华丽进===addHash====================");
		User user = new User();
		user.setAge(11);
		user.setId(1L);
		user.setName("李四");
		// 存储hash结构的数据，可以是HashMap
		this.redisUtil.setHash("map", "user1", user);
		HashMap<String, User> map = new HashMap<String, User>();
		map.put("user", user);
		this.redisUtil.setHash("map", "user2", map);
		System.out.println("================华丽出====addHash===================");
		return "redis添加map数据成功！";
	}

	@RequestMapping(value = "getHash", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, User> getHash() {
		System.out.println("================获取redis中数据========getHash===============");
		User user = (User) this.redisUtil.getHash("map", "user1");
		System.out.println("================获取redis中数据========getHash===============" + user);
		return (HashMap<String, User>) this.redisUtil.getHash("map", "user2");
	}
}
