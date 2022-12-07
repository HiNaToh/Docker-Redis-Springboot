package com.example.springbootredis.service;

import com.example.springbootredis.model.Hachage;

public interface HachageService {

	public Hachage save(Hachage hach);

	public Hachage findById(Long id);
    
}
