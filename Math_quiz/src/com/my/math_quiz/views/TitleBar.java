package com.my.math_quiz.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.my.math_quiz.R;

public class TitleBar extends RelativeLayout{
	public interface TitleBarListener{
		public void onLeftButtonClick();
		public void onRightButtonClick();
		
	}
	
	ImageButton leftButton=null;
	ImageButton rightButton=null;
	TextView middleText=null;
	
	TitleBarListener listener=null;
	
	String title=null;
	
	Bitmap rightImage=null;
	
	public TitleBar(Context context) {
		super(context);
	}

	public TitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TitleBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		
		if(leftButton==null){
			leftButton=(ImageButton)findViewById(R.id.TBleftButton);
			rightButton=(ImageButton)findViewById(R.id.TBRightButton);
			middleText=(TextView)findViewById(R.id.TBmiddleText);
			
			setRightImage();
			setTitle();
			
			leftButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if(listener!=null)
						listener.onLeftButtonClick();
				}
			});
			rightButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(listener!=null)
						listener.onRightButtonClick();
				}
			});
		}
	}
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		
	}

	public void setTitleBarListener(TitleBarListener listener){
		if(listener!=null){
			this.listener=listener;
		}
	}
	
	public void setTitle(String title){
		this.title=title;
		setTitle();
	}
	public void setRightImage(Bitmap rightImage){
		this.rightImage=rightImage;
		setRightImage();
	}
	
	
	private void setTitle(){
		if(title!=null&&middleText!=null)
			middleText.setText(title);
	}
	private void setRightImage(){
		if(rightImage!=null&&rightButton!=null)
			rightButton.setImageBitmap(rightImage);
	}
	
	

}
