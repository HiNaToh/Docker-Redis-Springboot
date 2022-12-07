package com.example.springbootredis.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.springbootredis.model.Hachage;

@Service
public class HachageServiceImpl implements HachageService {

	private static final String HACHAGE = "Hachage";

	private RedisTemplate<String, Object> redisTemplate;

	private HashOperations<String, Long, Hachage> hashOperations;

	@Autowired
	public HachageServiceImpl(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	@PostConstruct
	public void init() {
		this.hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public Hachage save(Hachage hach) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			hach.setHacheSha(digest.digest(hach.getAHacher().getBytes(StandardCharsets.UTF_8)).toString());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hashOperations.put(HACHAGE, hach.getId(),hach);
		return hach;
	}

	@Override
	public Hachage findById(Long id) {
		return (Hachage) hashOperations.get(HACHAGE, id);
	}

}