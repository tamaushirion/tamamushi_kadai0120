package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.model.NumberEnthity;
import com.example.demo.form.NumberForm;
import com.example.demo.service.Impl.NumberJudgmentServiceImpl;

@Controller
public class NumberJudgmentController {
	
	@Autowired
	private NumberJudgmentServiceImpl service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/NumberJudgment")
	public String getNumberJudgmentList(NumberForm form, Model model) {
		
		
		
		return "html/list";
		
	}
	
	@PostMapping("/NumberJudgment")
    public String postNumberJudgmentList(NumberForm form, Model model) {
	
	//数値チェック入れていく
	
	//友愛数の判定
	if(service.JudgmentFraternalNumber(form, 1)) {
		
		NumberEnthity number = modelMapper.map(form, NumberEnthity.class);
		service.insertOne(number);
		
		return "redirect:/NumberJudgment";
	}
	
	//完全数の判定
	if(service.JudgmentPerfectNumber(form)) {
		
		NumberEnthity number = modelMapper.map(form, NumberEnthity.class);
		service.insertOne(number);
		
		return "redirect:/NumberJudgment";
	}
	
	//素数の判定
	if(service.JudgmentPrimeNumber(form)) {
		
		NumberEnthity number = modelMapper.map(form, NumberEnthity.class);
		service.insertOne(number);
		
		return "redirect:/NumberJudgment";
	}
	
	//偶数、奇数判定
	if(service.JudgmentEvenorOdd(form)) {
		
		NumberEnthity number = modelMapper.map(form, NumberEnthity.class);
		service.insertOne(number);
		
		return "redirect:/NumberJudgment";
	} else {
		
		NumberEnthity number = modelMapper.map(form, NumberEnthity.class);
		service.insertOne(number);
		
		return "redirect:/NumberJudgment";
	}
	
		
	}
}
