package com.example.demo.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.LineApiConst;
import com.example.demo.domain.KeyWord;
import com.example.demo.repository.KeyWordRepoisitory;

@Service
@Transactional
public class LineApiService {
	@Autowired
	KeyWordRepoisitory keywordRepository;

	/**
	 * ユーザの全キーワード取得サービス
	 * @return List<item>
	 */
	public List<KeyWord> findByUserId(String userId) {
		return keywordRepository.findByUserId(userId);
	}

	/**
	 * ユーザの特定キーワード取得サービス
	 * @return List<item>
	 */
	public List<KeyWord> find(String userId, String key) {
		return keywordRepository.findByUserIdAndKeyword(userId, key);
	}

	/**
	 * キーワード登録サービス(同一ユーザ・キーは一度削除)
	 * @param userId
	 * @param key
	 * @param word
	 * @return void
	 */
	public KeyWord save(String userId, String key, String content) {

		KeyWord keyword =  new KeyWord();
		keyword.setUserId(userId);
		keyword.setKeyword(key);
		keyword.setContent(content);
		keyword.setCreateddate(ZonedDateTime.now(ZoneId.systemDefault()));

		keywordRepository.deleteByUserIdAndKeyword(userId, key);
		return keywordRepository.save(keyword);
	}

	/**
	 * SHELF_LIFE以前に登録されたものは削除するサービス
	 */
	public void cleanUp() {
		ZonedDateTime time = ZonedDateTime.now(ZoneId.systemDefault());
		time.minusDays(LineApiConst.VAL.SHELF_LIFE);
		keywordRepository.deleteByCreateddateBefore(time);
	}

	public List<KeyWord> findAll() {
		return keywordRepository.findAll();
	}

	public void delete() {
		keywordRepository.deleteAll();
	}

	public void deleteSharp(String userId) {
		keywordRepository.deleteByUserIdAndKeyword(userId, LineApiConst.VAL.SHARP);
	}

}
