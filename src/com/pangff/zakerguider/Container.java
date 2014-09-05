package com.pangff.zakerguider;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class Container extends FrameLayout{
	 ViewPager pager;
	public Container(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void setItems(ViewPager pager1){
		this.pager = pager1;
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		pager.dispatchTouchEvent(ev);
		return true;
	}

}
