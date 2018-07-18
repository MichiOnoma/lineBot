package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.LineApiService;

@RestController
@RequestMapping("api/set")
public class testController {
	  @Autowired
	    LineApiService itemService;

	    @GetMapping
	    String setItems() {
	    	itemService.save("niitsuma", "key", "content");
	        return "Set!!";
	    }


}
