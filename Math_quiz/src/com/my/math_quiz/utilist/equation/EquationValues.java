package com.my.math_quiz.utilist.equation;

import java.util.Random;

public class EquationValues extends Equation{

	public int operator;
	public float value;
	
	public EquationValues(float value){
		this.value=value;
	}
	@Override
	public String getText() {
		return  value+"";
	}

	@Override
	public float getValue() {
		return value;
	}
	
	@Override
	public float getValue(float newFirstPart) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getLeftPriority() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getLefttPart() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getRightPriority() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getRightPart() {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
