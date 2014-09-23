package com.pangff.zakerguider;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BgPagerAdapter extends PagerAdapter {

	int bgs[] = { R.drawable.g_bg1, R.drawable.g_bg2, R.drawable.g_bg3,
			R.drawable.g_bg4, R.drawable.g_bg5 };
	String texts[] = { "10万模拟资金 ", "免费智能化预警 ", "牛人牛股追踪 ",
			"免费交易操作评级 ", "免费创建比赛 " };
	String texts2[] = { "万三实盘开户 ", "止盈止损自动化交易 ", "牛人操作实时提醒 ",
			"基金级专业评测 ", "各类炒股比赛奖励多多 " };
	
	Typeface typeface;
	public BgPagerAdapter(Context context){
		typeface = Typeface.createFromAsset(context.getAssets(), "fonts/f.ttf");
	}
	
	

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
		View view = LayoutInflater.from(container.getContext()).inflate(
				R.layout.guide_item, null);
		
		TextView textViewTop = (TextView) view.findViewById(R.id.titleTop);
		textViewTop.setTypeface(typeface);
		
		TextView textViewBottom = (TextView) view.findViewById(R.id.titleBottom);
		textViewBottom.setTypeface(typeface);
		
		SpannableString msp = new SpannableString(texts[position]);
		msp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD_ITALIC), 0,
				msp.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 斜体
		
		SpannableString msp2 = new SpannableString(texts2[position]);
		msp2.setSpan(new StyleSpan(android.graphics.Typeface.BOLD_ITALIC), 0,
				msp2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 斜体

		textViewTop.setText(msp);
		textViewBottom.setText(msp2);
		//view.setBackgroundResource(bgs[position]);
		container.addView(view);
		return view;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

}
