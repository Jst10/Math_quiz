package com.my.math_quiz.utils;

import com.my.math_quiz.utilist.equation.Equation;

public class Task {

	private Equations equation;
	int[] answers=new int[4];
	
	
	/**@selectedAnswer on answer clicked we save the answer and if we return back we set color to this button according to varible @isCorrectAnswer*/
	private int selectedAnswer;
	/**@correctAnswer number of corrrect answer from 0 to 4 in this specific task, we have this to compare later and other thing, so we don't need always go thru all answers in task*/
	private int correctAnswer;

	public Task(Equations equation,int[] answers){
		setEquationAndAnswers(equation,answers);
	}
	
	public void setEquationAndAnswers(Equations equation,int[] answers){
		selectedAnswer=-1;
		this.equation=equation;
		this.answers=answers;
		int result=equation.getValue();
		for(int i=0; i<answers.length; i++){
			if(result==answers[i]){
				correctAnswer=i;
				break;
			}
		}
	}
//	public int getResult(){
//		return equation.getValue();
//	}
	public int[] getAnswers(){
		return answers;
	}
	public String getText(){
		return equation.getText();
	}
	
	public int getCorrectAnswer(){
		return correctAnswer;
	}
	/**Set the selected answer to specific task for single player only, in multipleyer we save answers to bottom buttons 
	 * @param selectedAnswer the number of answer which user select from 0 to 4
	 * @return if previous user didn't already select answer to this task we return true else we return false*/
	public boolean setSelectedAnswer(int selectedAnswer){
		if(this.selectedAnswer==-1){
			this.selectedAnswer=selectedAnswer;
			return true;
		}
		return false;
	}
	/**
	 * For single player only, in multipleyer we save answers to bottom buttons 
	 * */
	public int getSelectedAnswer(){
		return selectedAnswer;
	}
}
