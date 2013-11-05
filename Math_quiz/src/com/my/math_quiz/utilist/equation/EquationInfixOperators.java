package com.my.math_quiz.utilist.equation;

public class EquationInfixOperators extends Equation{

	
	
	private int operator;
	private Equation leftSide=null;
	private Equation rightSide=null;
	
	public EquationInfixOperators(Equation leftSide,Equation rigtSide,int operator){
		this.leftSide=leftSide;
		this.rightSide=rigtSide;
		this.operator=operator;
	}
	
	
	@Override
	public String getText() {
		String leftText=leftSide.getText();
		String rightText=rightSide.getText();
		switch(operator){
			case operatorDIV:
				return leftText+" / "+rightText;
			case operatorLOG:
				/**TODO*/
				return "";
			case operatorMUL:
				return leftText+" * "+rightText;
			case operatorSUB:
				return leftText+" - "+rightText;
			case operatorSUM:
				return leftText+" + "+rightText;
			default:
				return "";
		}
	}

	@Override
	public float getValue() {
		float leftValue=leftSide.getValue();
		float rightValue=rightSide.getValue();
		switch(operator){
			case operatorDIV:
				return leftValue/rightValue;
			case operatorLOG:
				/**TODO*/
				return 0;
			case operatorMUL:
				return leftValue*rightValue;
			case operatorSUB:
				return leftValue-rightValue;
			case operatorSUM:
				return leftValue+rightValue;
			default:
				return 0;
		}
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
