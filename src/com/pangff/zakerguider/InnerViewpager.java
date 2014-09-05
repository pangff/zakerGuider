package com.pangff.zakerguider;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class InnerViewpager extends ViewPager{

	public InnerViewpager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	PathView pathView;
	public void setPathView(PathView pathView){
		this.pathView = pathView;
	}
	
	public synchronized void bigShow(){
		if(this.pathView!=null){
			pathView.setBitMap(this.getDrawingCache());
		}
	}
	
	

}
