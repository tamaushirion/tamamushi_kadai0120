package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.model.NumberEnthity;

@Mapper
public interface NumberJudgmentMapper {
	
	public void insertOne(NumberEnthity number);
	
}
