package com.pangff.zakerguider;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

public class BasePagerAdapter extends PagerAdapter{

	//int images[] = {R.drawable.home_tip1,R.drawable.home_tip2,R.drawable.home_tip3,R.drawable.home_tip4};
	@Override
	public int getCount() {
		return 4;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		TextView textView = new TextView(container.getContext());
		textView.setText("第"+position+"个");
		textView.setPadding(60, 60,60, 60);
		container.addView(textView);
//		ImageView imageView = new ImageView(container.getContext());
//		imageView.setImageResource(images[position]);
//		container.addView(imageView);
//		imageView.setScaleType(ScaleType.CENTER_CROP);
		return textView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}
  
	
	
}
