package com.my.math_quiz.views;

import java.lang.reflect.Field;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class ViewPagerWithCustomSpeedOfSwitchingPages extends ViewPager {

	
	private final int fastSpeed=80;
	private int mDuration = fastSpeed;
	public void setSlowSpeed(){
		mDuration = 200;
	}
	public ViewPagerWithCustomSpeedOfSwitchingPages(Context context) {
		super(context);
		init(context);
	}
	public ViewPagerWithCustomSpeedOfSwitchingPages(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	private void init(Context context){
		Interpolator sInterpolator = new AccelerateInterpolator();
		try {
            Field mScroller;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true); 
            FixedSpeedScroller scroller = new FixedSpeedScroller(this.getContext(), sInterpolator);
            // scroller.setFixedDuration(5000);
            mScroller.set(this, scroller);
         } catch (NoSuchFieldException e) {Log.d("exception",e+"");
        } catch (IllegalArgumentException e) {Log.d("exception",e+"");
        } catch (IllegalAccessException e) {Log.d("exception",e+"");
        }
	}
	
	class FixedSpeedScroller extends Scroller {
	
	    
	    
	    public FixedSpeedScroller(Context context) {
	        super(context);
	    }
	    public FixedSpeedScroller(Context context, Interpolator interpolator) {
	        super(context, interpolator);
	    }
	
	    @Override
	    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
	        // Ignore received duration, use fixed one instead
	        super.startScroll(startX, startY, dx, dy, mDuration);
	        mDuration=fastSpeed;
	    }
	
	    @Override
	    public void startScroll(int startX, int startY, int dx, int dy) {
	        // Ignore received duration, use fixed one instead
	        super.startScroll(startX, startY, dx, dy, mDuration);
	        mDuration=fastSpeed;
	    }
	}

}
