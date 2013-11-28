package com.my.math_quiz.utils;

import java.util.ArrayList;

import android.view.View;
import android.view.animation.Animation.AnimationListener;

import com.my.math_quiz.ApplicationClass;

public class OneAnimationStep{
	public ArrayList<OneItemToAnimate> itemsToDisplay=null;
	public ArrayList<OneItemToAnimate> itemsTohide=null;
	private AnimationListener animationListener;
	public OneAnimationStep(AnimationListener listener){
		animationListener=listener;
		
	}
	public OneAnimationStep(View view1,boolean addListener1,AnimationListener listener){
		animationListener=listener;
		addItemToDisplay(view1,addListener1);
	}

	public OneAnimationStep(View view1,boolean addListener1,View view2,boolean addListener2,AnimationListener listener){
		animationListener=listener;
		addItemToDisplay(view1,addListener1);
		addItemToDisplay(view2,addListener2);
	}
	public OneAnimationStep(View view1,boolean addListener1,View view2,boolean addListener2,View view3, boolean addListener3,AnimationListener listener){
		animationListener=listener;
		addItemToDisplay(view1,addListener1);
		addItemToDisplay(view2,addListener2);
		addItemToDisplay(view3,addListener3);
	}

	public void executeAnimation(){
		
		if(itemsToDisplay!=null){
			for(OneItemToAnimate item:itemsToDisplay){
				item.view.clearAnimation();
				if(item.addListener){
					item.view.startAnimation(ApplicationClass.getFadeInAnimation(animationListener));		
				}
				else{
					item.view.startAnimation(ApplicationClass.getFadeInAnimation(null));		
				}
			}
			
		}
		if(itemsTohide!=null){
			for(OneItemToAnimate item:itemsTohide){
				item.view.clearAnimation();
				if(item.addListener){
					item.view.startAnimation(ApplicationClass.getFadeOutAnimation(animationListener));		
				}
				else{
					item.view.startAnimation(ApplicationClass.getFadeOutAnimation(null));		
				}
			}
		}
		
		
	}
	
	
	
	public void addItemToDisplay(View view, boolean addListener){
		if(itemsToDisplay==null){
			itemsToDisplay=new ArrayList<OneItemToAnimate>();
		}
		itemsToDisplay.add(new OneItemToAnimate(view, addListener));
	}
	
	public void addItemToHide(View view, boolean addListener){
		if(itemsTohide==null){
			itemsTohide=new ArrayList<OneItemToAnimate>();
		}
		itemsTohide.add(new OneItemToAnimate(view, addListener));
	}
	class OneItemToAnimate{
		
		public boolean addListener;
		public View view;
		
		public OneItemToAnimate(View view, boolean addListener){
			this.addListener=addListener;
			this.view=view;
		}
	}
}