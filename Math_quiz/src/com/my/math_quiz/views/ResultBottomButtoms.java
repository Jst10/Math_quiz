package com.my.math_quiz.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.my.math_quiz.R;

public class ResultBottomButtoms extends LinearLayout{
	public interface ResultBottomButtonListener{
		public void onAgainButtonClicked();
		public void onFinishButtonClicked();
		public void onShareButtonClicked();
	}
	
	
	
	Button againButton=null;
	Button shareButton=null;
	Button endButton=null;
	
	ResultBottomButtonListener listener=null;
	

	public ResultBottomButtoms(Context context) {
		super(context);


	}

	public ResultBottomButtoms(Context context, AttributeSet attrs) {
		super(context, attrs);
	

	}

	public ResultBottomButtoms(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

	}


	private void initViews(){
		if(againButton==null){
			againButton=(Button)findViewById(R.id.LPbuttonAgain);
			shareButton=(Button)findViewById(R.id.LPbuttonShare);
			endButton=(Button)findViewById(R.id.LPbuttonFinish);

			
			againButton.setOnClickListener(onClickListener);
			shareButton.setOnClickListener(onClickListener);
			endButton.setOnClickListener(onClickListener);


		}
	}
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		initViews();
		
	}
	public void setListener(ResultBottomButtonListener listener){
		this.listener=listener;
	}
	
	OnClickListener onClickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if(listener!=null){
				if(v.getId()==R.id.LPbuttonAgain){
					listener.onAgainButtonClicked();
				}else if(v.getId()==R.id.LPbuttonShare){
					listener.onShareButtonClicked();
				}else if(v.getId()==R.id.LPbuttonFinish){
					listener.onFinishButtonClicked();
				}
			}
			
		}
	};
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

}
