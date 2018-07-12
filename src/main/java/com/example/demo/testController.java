package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.KeyWord;

@RestController
@RequestMapping("api/items")
public class testController {
	  @Autowired
	    LineApiService itemService;

	    @GetMapping
	    List<KeyWord> getItems() {
	        List<KeyWord> customers = itemService.findByUserId("niitsuma");
	        return customers;
	    }
}
