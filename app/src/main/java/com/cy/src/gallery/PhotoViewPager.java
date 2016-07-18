package com.cy.src.gallery;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 
 * @title
 * @author CY
 * @date 2014-12-23
 * 
 */
public class PhotoViewPager extends ViewPager {

	public PhotoViewPager(Context context) {
		super(context);
	}

	public PhotoViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		try {
			return super.onInterceptTouchEvent(arg0);
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {

		try {
			return super.onTouchEvent(arg0);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

}
