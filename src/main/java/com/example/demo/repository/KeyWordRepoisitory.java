package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.KeyWord;

@Repository
public interface KeyWordRepoisitory extends JpaRepository<KeyWord, Integer> {


	public List<KeyWord> findByUserId(String userId);

	public List<KeyWord> findByUserIdAndKeyword(String userId, String Key);

	public void deleteByUserIdAndKeyword(String userId, String key);

	public void deleteByCreateddateBefore(Date  createddate);

	}
