package com.example.springbootredis.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootredis.model.Hachage;
import com.example.springbootredis.service.HachageService;

@RestController
@RequestMapping("/api/hachage")
public class HachageResource {

	@Autowired
	HachageService hachageService;

	@GetMapping(path = "{id}")
	@Cacheable(value = "Hachage", key = "#id")
	public Hachage getHachage(@PathVariable("id") final Long id) {
		return hachageService.findById(id);
	}

	@PostMapping
	public Hachage saveHachage(@RequestBody Hachage hach) {
		return hachageService.save(hach);
	}

}
