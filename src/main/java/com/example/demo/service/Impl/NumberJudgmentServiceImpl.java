package com.example.demo.service.Impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.model.NumberEnthity;
import com.example.demo.form.NumberForm;
import com.example.demo.repository.NumberJudgmentMapper;
import com.example.demo.service.NumberJudgmentService;

@Service
public class NumberJudgmentServiceImpl implements NumberJudgmentService {

	@Autowired
	private NumberJudgmentMapper mapper;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public void insertOne(NumberEnthity number) {
		// TODO 自動生成されたメソッド・スタブ
		mapper.insertOne(number);
	}
	
	//5件履歴取得
	@Override
	public List<NumberEnthity> find5Number() {
		// TODO 自動生成されたメソッド・スタブ
		return mapper.find5Number();
	}
	
	//過去入力数値
	@Override
	public NumberEnthity lastTimeNumber() {
		// TODO 自動生成されたメソッド・スタブ
		return mapper.lastTimeNumber();
	}
	
	//1~1000の数字であるか判定
    public boolean JudgmentNumberSize(NumberForm form) {
		
		
		if(form.getInputNumber() < 1 || form.getInputNumber() > 1000) {
			return false;
		} else {
			return true;
		}
	}
	
    
	//偶数、奇数判定
	public boolean JudgmentEvenorOdd(NumberForm form) {
		
		//偶数
		if(form.getInputNumber() % 2 == 0) {
			
			return true;
		} else {
			
			return false;
		}
		
		
	}
	
	//素数の判定
	public boolean JudgmentPrimeNumber(NumberForm form) {
		
		if (form.getInputNumber() < 2) {
			
			return false;
	    }
		
		if (form.getInputNumber() == 2) {
	    	
	    	return true;
	    }
		
	    if (form.getInputNumber() % 2 == 0) { // 偶数は先にリターン
	    	
	    	return false;
	    }

	    for (int i = 3; i <= Math.sqrt(form.getInputNumber()); i+=2) {
	      if (form.getInputNumber() % i == 0) {
	    	  
	    	  return false;
	      }
	    }
	    
	    
	    
	    return true;
	}
	
	//完全数の判定
	public boolean JudgmentPerfectNumber(NumberForm form) {
		
		// ２未満は完全数ではない
		if ( 2 > form.getInputNumber() ) {
			
			return false;
		}

		// 和を計算
		int sum = 0;
		for ( int i = 1; i <= form.getInputNumber() / 2; i ++ )
		{
			if ( 0 == ( form.getInputNumber() % i ) )
				sum += i;
		}

		// 判定
		if ( sum == form.getInputNumber() ) {
			
			return true;
		} else {
			// 和sumと元の値nが不一致なので完全数ではない
			
			return false;
		}
		
		
	}
	
	//友愛数の判定
	public boolean JudgmentFraternalNumber(NumberForm form, Integer lastTimeNumber) {
		
		// 和を計算
		int formNumberSum = 0;
		for ( int i = 1; i <= form.getInputNumber() / 2; i ++ )
		{
			if ( 0 == ( form.getInputNumber() % i ) )
				formNumberSum += i;
		}
		
		if(formNumberSum == lastTimeNumber) {
			
			return true;
		} else {
			
			return false;
		}
		
	}
	
    public NumberEnthity Judgment(NumberForm form) {
    	
    	NumberEnthity number = new NumberEnthity();
    	
    	if(JudgmentFraternalNumber(form, lastTimeNumber().getInputNumber())) {
    		form.setClassificationNumber("友愛数");
    		form.setColor("gray");
    		number = modelMapper.map(form, NumberEnthity.class);
    	} else if(JudgmentPerfectNumber(form)) {
    		form.setClassificationNumber("完全数");
    		form.setColor("gold");
    		number = modelMapper.map(form, NumberEnthity.class);
    	} else if(JudgmentPrimeNumber(form)) {
    		form.setClassificationNumber("素数");
    		form.setColor("green");
    	    number = modelMapper.map(form, NumberEnthity.class);
    	} else if(JudgmentEvenorOdd(form)) {
    		form.setClassificationNumber("偶数");
    		form.setColor("blue");
    		number = modelMapper.map(form, NumberEnthity.class);
    	} else {
    		form.setClassificationNumber("奇数");
    		form.setColor("red");
    		number = modelMapper.map(form, NumberEnthity.class);
    	}
    	
		return number;
    	
    }
}
