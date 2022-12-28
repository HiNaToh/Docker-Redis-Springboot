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
		    // The previous code did not return a correct sha-256 !
			// I'm not a java expert but I think the bug was that
			// the `digest method of the MessageDigest class returns a byte array, which cannot be converted to a string using the toString method.
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(hach.getAHacher().getBytes(StandardCharsets.UTF_8));

			StringBuilder hexString = new StringBuilder();
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}

			hach.setHacheSha(hexString.toString());
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