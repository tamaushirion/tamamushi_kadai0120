package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.model.NumberEnthity;

@Mapper
public interface NumberJudgmentMapper {
	
	public void insertOne(NumberEnthity number);
	
	public List<NumberEnthity> find5Number();
	
	public NumberEnthity lastTimeNumber();
}
