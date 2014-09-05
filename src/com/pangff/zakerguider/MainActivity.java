package com.pangff.zakerguider;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

public class MainActivity extends Activity {

	ViewPager pager;
	InnerViewpager innerPager;
	Container container;
	PathView pathView;
	boolean isStop = false;
	boolean isFinsised = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		pathView = (PathView) findViewById(R.id.pathView);
		
		pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(new BasePagerAdapter());
		
		innerPager =  (InnerViewpager) findViewById(R.id.innerPager);
		innerPager.setAdapter(new BasePagerAdapter());
		innerPager.setDrawingCacheEnabled(true);
		innerPager.setPathView(pathView);
		
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

	private synchronized void showBig(final int pager) {
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				if(innerPager.getCurrentItem()==pager){
					innerPager.invalidate();
					innerPager.buildDrawingCache();
					innerPager.bigShow();
				}
			}
		}, 500);

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
