package com.example.demo;

import java.util.List;

import com.example.demo.domain.KeyWord;

public class LineApiMakePict {

	String getResult(List<KeyWord> list) {
		StringBuilder str = new StringBuilder();
		if (list.size() == 0) str.append(LineApiConst.MESSAGE.NO_DATA_MSG2);
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