package com.cy.src.gallery;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @title
 * @author CY
 * @date 2014年4月25日
 * @class BasePagerAdapter.java
 * @package com.src.zf.doudou.xmpp.core.chat
 * 
 */
public class BasePagerAdapter<T> extends PagerAdapter {

	public Activity mAct;
	public LayoutInflater mInflater;
	public List<T> mListData;

	public BasePagerAdapter(Activity act) {
		mAct = act;
		mInflater = mAct.getLayoutInflater();
		mListData = new ArrayList<T>();
	}

	@Override
	public int getCount() {
		return mListData.size();
	}

	public void addListData(T t, boolean isNotify) {
		mListData.add(t);
		if (isNotify) {
			notifyDataSetChanged();
		}
	}

	public void addListData(List<T> list) {
		mListData.addAll(list);
		notifyDataSetChanged();
	}

	public void addListData(T... t) {
		mListData.addAll(Arrays.asList(t));
		notifyDataSetChanged();
	}

	public void setListData(T... t) {
		mListData.clear();
		mListData.addAll(Arrays.asList(t));
		notifyDataSetChanged();
	}

	public void clearListData() {
		mListData.clear();
		notifyDataSetChanged();
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		return super.instantiateItem(container, position);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		super.destroyItem(container, position, object);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}
}
