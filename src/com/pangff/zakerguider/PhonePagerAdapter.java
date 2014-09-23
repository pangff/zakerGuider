package com.pangff.zakerguider;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;

public class PhonePagerAdapter extends PagerAdapter{

	int images[] = {R.drawable.g_p1,R.drawable.g_p2,R.drawable.g_p3,R.drawable.g_p4,R.drawable.g_p5};
	@Override
	public int getCount() {
		return 5;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ImageViewTopCrop imageView = new ImageViewTopCrop(container.getContext());
		imageView.setImageResource(images[position]);
		container.addView(imageView);
		return imageView ;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}
  
	
	
}
