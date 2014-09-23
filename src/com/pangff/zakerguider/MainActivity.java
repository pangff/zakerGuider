package com.pangff.zakerguider;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class MainActivity extends Activity {

	ViewPager pager;
	InnerViewpager innerPager;
	Container container;
	CircleImageView imageView;
	boolean isStop = false;
	boolean isFinsised = false;
	ImageView coverView;
	int animationViews[] = {R.id.cloudBig,R.id.cloudSmall};
	static int innerDrawables[] = {R.drawable.lookup_1,R.drawable.lookup_2,R.drawable.lookup_3,R.drawable.lookup_4,R.drawable.lookup_5};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		imageView = (CircleImageView) findViewById(R.id.circleView);
		coverView = (ImageView) findViewById(R.id.coverView);
		pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(new BgPagerAdapter(this));
		
		innerPager =  (InnerViewpager) findViewById(R.id.innerPager);
		innerPager.setAdapter(new PhonePagerAdapter());
		innerPager.setDrawingCacheEnabled(false);
		innerPager.setOffscreenPageLimit(3);
		
		container = (Container) findViewById(R.id.container);
		container.setItems(pager);
		pager.setOnPageChangeListener(mMainPagerListener);
		
		
		innerPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				isFinsised = true;
				if(isStop){
					showBig(arg0);
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				isStop = false;
				isFinsised = false;
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				 switch(arg0) {
		            case ViewPager.SCROLL_STATE_IDLE:
		            case ViewPager.SCROLL_STATE_SETTLING:
		            		isStop = true;
		            		if(isFinsised){
		            			showBig(innerPager.getCurrentItem());
		            		}
		             break;
		             default:{
		            	 	isStop = false;
		            	 	break;
		             }
		          }
			}
		});
		innerPager.post(new Runnable() {
			
			@Override
			public void run() {
				showBig(0);
			}
		});
	}
	
	
	private void startCloudAnimation(final View view,int offset){
		final TranslateAnimation ts = getAnimation();
		ts.setDuration(12000);
		ts.setInterpolator(new LinearInterpolator());
		ts.setRepeatCount(-1);
		if(offset>0){
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					view.startAnimation(ts);				
				}
			}, offset);
		}else{
			view.startAnimation(ts);		
		}
	}
	
	
	public TranslateAnimation getAnimation(){
		return new TranslateAnimation(Animation.RELATIVE_TO_PARENT,  
                1.0f, Animation.RELATIVE_TO_PARENT, -0.2f,  
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,  
                0.0f); 
	}
	

	private synchronized void showBig(final int pager) {
//		new Handler().postDelayed(new Runnable() {
//
//			@Override
//			public void run() {
//				if(innerPager.getCurrentItem()==pager){
//					innerPager.bigShow();
//				}
//			}
//		}, 500);
		AlphaAnimation an = new AlphaAnimation(1f,0f);
		an.setDuration(250);
		an.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				//imageView.setVisibility(View.GONE);
				if(innerPager.getCurrentItem()==pager){
					//innerPager.bigShow();
					imageView.setImageResource(innerDrawables[pager]);
				}
			}
		});
		imageView.startAnimation(an);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
//		for(int i=0;i<animationViews.length;i++){
//			findViewById(animationViews[i]).clearAnimation();
//		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
//		new Handler().post(new Runnable() {
//			
//			@Override
//			public void run() {
//				for(int i=0;i<animationViews.length;i++){
//					startCloudAnimation(findViewById(animationViews[i]),5000*i);
//				}
//			}
//		});
	}

	private OnPageChangeListener mMainPagerListener = new OnPageChangeListener() {

		private int mLastScrollPosition;
		private int mLastPagePosition;

		@Override
		public void onPageSelected(int position) {
			mLastPagePosition = position;
			innerPager.setCurrentItem(position);
		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			if (innerPager.isFakeDragging()) {
				int absoluteOffsetPixels = (int) (positionOffset * innerPager
						.getWidth());
				if (mLastPagePosition != position) {
					absoluteOffsetPixels += (position - mLastPagePosition)
							* innerPager.getWidth();
					mLastPagePosition = position;
				}
				innerPager.fakeDragBy(mLastScrollPosition
						- absoluteOffsetPixels);
				mLastScrollPosition = (int) (positionOffset * innerPager
						.getWidth());
			}
		}

		@Override
		public void onPageScrollStateChanged(int state) {
			if (!pager.isFakeDragging()) {
				switch (state) {
				case ViewPager.SCROLL_STATE_DRAGGING:
					if (!innerPager.isFakeDragging())
						innerPager.beginFakeDrag();
					break;
				case ViewPager.SCROLL_STATE_SETTLING:
				case ViewPager.SCROLL_STATE_IDLE:
					if (innerPager.isFakeDragging()) {
						innerPager.endFakeDrag();
						mLastScrollPosition = 0;
					}
					break;
				}
			}
		}
	};
}
