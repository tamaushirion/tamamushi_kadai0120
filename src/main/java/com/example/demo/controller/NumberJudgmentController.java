package com.example.demo.controller;

import java.util.List;

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
		
		List<NumberEnthity> enthityNumberList = service.find5Number();
		
		//NumberForm numberList = modelMapper.map(enthityNumberList, form.getClass());
		
		model.addAttribute("numberList", enthityNumberList);
		
		return "html/list";
		
	}
	
	@PostMapping("/NumberJudgment")
    public String postNumberJudgmentList(NumberForm form, Model model) {
	
	//数値が1~1000の範囲ないか
	if(!service.JudgmentNumberSize(form)) {
		model.addAttribute("sizeCheck", "1~1000の範囲内で入力してください");
		return getNumberJudgmentList(form, model);
	}
	
	//友愛数の判定
	if(service.JudgmentFraternalNumber(form, service.lastTimeNumber().getInputNumber())) {
		
		//2回目以降の判定
		
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
