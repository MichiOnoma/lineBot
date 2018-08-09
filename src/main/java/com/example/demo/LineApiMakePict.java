package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.domain.KeyWord;
import com.example.demo.service.LineApiService;

public class LineApiMakePict {
	@Autowired
	LineApiService itemService;

	String getResult(String userId) {
		List<KeyWord> list = itemService.findByUserId(userId);

		StringBuilder str = new StringBuilder();
		for(KeyWord k: list) {
			str.append(LineApiConst.VAL.HOSI);
			str.append(k.getKeyword());
			str.append(LineApiConst.VAL.KAI);
			str.append(LineApiConst.VAL.SEN);
			str.append(k.getContent());
			str.append(LineApiConst.VAL.KAI);
			str.append(LineApiConst.VAL.KAI);
		}
		return str.toString();
	}
}