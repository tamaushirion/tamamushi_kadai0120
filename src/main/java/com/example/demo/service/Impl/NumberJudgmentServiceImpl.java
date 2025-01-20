package com.example.demo.service.Impl;

import java.util.List;

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
	
	//偶数、奇数判定
	public boolean JudgmentEvenorOdd(NumberForm form) {
		
		//偶数
		if(form.getInputNumber() % 2 == 0) {
			form.setClassificationNumber("偶数");
			return true;
		} else {
			form.setClassificationNumber("奇数");
			return false;
		}
		
		
	}
	
	//素数の判定
	public boolean JudgmentPrimeNumber(NumberForm form) {
		
		if (form.getInputNumber() < 2) {
			form.setClassificationNumber("素数ではない");
			return false;
	    }
	    if (form.getInputNumber() % 2 == 0) { // 偶数は先にリターン
	    	form.setClassificationNumber("素数ではない");
	    	return false;
	    }

	    for (int i = 3; i <= Math.sqrt(form.getInputNumber()); i+=2) {
	      if (form.getInputNumber() % i == 0) {
	    	  form.setClassificationNumber("素数ではない");
	    	  return false;
	      }
	    }
	    
	    form.setClassificationNumber("素数");
	    
	    return true;
	}
	
	//完全数の判定
	public boolean JudgmentPerfectNumber(NumberForm form) {
		
		// ２未満は完全数ではない
		if ( 2 > form.getInputNumber() ) {
			form.setClassificationNumber("完全数ではない");
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
			form.setClassificationNumber("完全数");
			return true;
		} else {
			// 和sumと元の値nが不一致なので完全数ではない
			form.setClassificationNumber("完全数ではない");
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
		
//		//1回前の入力値の約数の和
//		int lastTimeNumberSum = 0;
//		for ( int i = 1; i <= lastTimeNumber / 2; i ++ )
//		{
//			if ( 0 == ( lastTimeNumber % i ) )
//				lastTimeNumberSum += i;
//		}
		
		if(formNumberSum == lastTimeNumber) {
			form.setClassificationNumber("友愛数");
			return true;
		} else {
			form.setClassificationNumber("友愛数ではない");
			return false;
		}
		
	}
	
}
