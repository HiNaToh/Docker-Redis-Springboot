package com.example.springbootredis.model;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hachage implements Serializable{

	private Long id;
	private String aHacher;
	private String hacheSha;
    
}
