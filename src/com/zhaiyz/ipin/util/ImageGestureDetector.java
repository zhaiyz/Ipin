package com.zhaiyz.ipin.util;

import android.util.Log;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

public class ImageGestureDetector extends SimpleOnGestureListener {

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		final int FLING_MIN_DISTANCE = 20;
		final int FLING_MIN_VELOCITY = 5;
		if(e1.getX() - e2.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
			Log.i("mylog", "向左滑动");
		} else if(e2.getX() - e1.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
			Log.i("mylog", "向右滑动");
		} else if(e1.getY() - e2.getY() > FLING_MIN_DISTANCE && Math.abs(velocityY) > FLING_MIN_VELOCITY) {
			Log.i("mylog", "向上滑动");
		} else if(e2.getY() - e1.getY() > FLING_MIN_DISTANCE && Math.abs(velocityY) > FLING_MIN_VELOCITY) {
			Log.i("mylog", "向下滑动");
		}
		return super.onFling(e1, e2, velocityX, velocityY);
	}
}
