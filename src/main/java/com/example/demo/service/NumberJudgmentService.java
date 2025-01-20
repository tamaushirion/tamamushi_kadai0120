package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.model.NumberEnthity;

public interface NumberJudgmentService {
	
	
	public void insertOne(NumberEnthity number);
	
	public List<NumberEnthity> find5Number();
	
	public NumberEnthity lastTimeNumber();
	
}
