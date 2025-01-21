package com.example.demo.controller;

import java.util.List;

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
	
	@GetMapping("/NumberJudgment")
	public String getNumberJudgmentList(NumberForm form, Model model) {
		
		List<NumberEnthity> enthityNumberList = service.find5Number();
		
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
	
	NumberEnthity number = service.Judgment(form);
	service.insertOne(number);
	
	return "redirect:/NumberJudgment";
		
	}
}
