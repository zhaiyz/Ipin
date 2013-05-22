package com.zhaiyz.ipin.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

import com.zhaiyz.ipin.R;
import com.zhaiyz.ipin.layout.ImageLayout;
import com.zhaiyz.ipin.util.ImagePiece;
import com.zhaiyz.ipin.util.ImageSplitter;

public class MainActivity extends Activity {
	
	private static final int COL_NUM = 3;
	
	private static final int ROW_NUM = 4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ImageLayout imageLayout = new ImageLayout(this, getImageViewArray());
		setContentView(imageLayout);
	}

	private ImageView[][] getImageViewArray() {
		final ImageView[][] imageViewArray = new ImageView[ROW_NUM][COL_NUM];
		List<ImagePiece> imagePieces = getRandomImagePieces();
		for(int i = 0; i < ROW_NUM; i++) {
			for (int j = 0; j < COL_NUM; j++) {
				ImageView iv = new ImageView(this);
				iv.setLongClickable(true);
				iv.setOnTouchListener(new OnTouchListener() {
					
					private float x = 0L;
					
					private float y = 0L;
					
					private float x1 = 0L;
					
					private float y1 = 0L;
					
					private static final float MIN_DISTANCE = 50L;
					
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						int action = event.getAction();
						switch (action) {
						case MotionEvent.ACTION_DOWN:
							x = event.getX();
							y = event.getY();
							break;
						case MotionEvent.ACTION_UP:
							x1 = event.getX();
							y1 = event.getY();
							if (x - x1 > MIN_DISTANCE) {
								Log.i("mylog", "向左滑动");
								ImageView imageView = (ImageView) v;
								for (int m = 0; m < ROW_NUM; m++) {
									for (int n = 0; n < COL_NUM; n++) {
										if (imageView.equals(imageViewArray[m][n])) {
											Log.i("mylog", "(m,n)=" + "(" + m + "," + n + ")");
											if (n == 0) {
												Log.i("mylog", "不能向左滑动");
											} else {
												Drawable d = imageView.getDrawable();
												Drawable d1 = imageViewArray[m][n - 1].getDrawable();
												imageViewArray[m][n].setImageDrawable(d1);
												imageViewArray[m][n - 1].setImageDrawable(d);
											}
										}
									}
								}
							} else if (x1 - x > MIN_DISTANCE) {
								Log.i("mylog", "向右滑动");
								ImageView imageView = (ImageView) v;
								for (int m = 0; m < ROW_NUM; m++) {
									for (int n = 0; n < COL_NUM; n++) {
										if (imageView.equals(imageViewArray[m][n])) {
											Log.i("mylog", "(m,n)=" + "(" + m + "," + n + ")");
											if (n == COL_NUM - 1) {
												Log.i("mylog", "不能向右滑动");
											} else {
												Drawable d = imageView.getDrawable();
												Drawable d1 = imageViewArray[m][n + 1].getDrawable();
												imageViewArray[m][n].setImageDrawable(d1);
												imageViewArray[m][n + 1].setImageDrawable(d);
											}
										}
									}
								}
							} else if (y - y1 > MIN_DISTANCE) {
								Log.i("mylog", "向上滑动");
								ImageView imageView = (ImageView) v;
								for (int m = 0; m < ROW_NUM; m++) {
									for (int n = 0; n < COL_NUM; n++) {
										if (imageView.equals(imageViewArray[m][n])) {
											Log.i("mylog", "(m,n)=" + "(" + m + "," + n + ")");
											if (m == 0) {
												Log.i("mylog", "不能向上滑动");
											} else {
												Drawable d = imageView.getDrawable();
												Drawable d1 = imageViewArray[m - 1][n].getDrawable();
												imageViewArray[m][n].setImageDrawable(d1);
												imageViewArray[m - 1][n].setImageDrawable(d);
											}
										}
									}
								}
							} else if (y1 - y > MIN_DISTANCE) {
								Log.i("mylog", "向下滑动");
								ImageView imageView = (ImageView) v;
								for (int m = 0; m < ROW_NUM; m++) {
									for (int n = 0; n < COL_NUM; n++) {
										if (imageView.equals(imageViewArray[m][n])) {
											Log.i("mylog", "(m,n)=" + "(" + m + "," + n + ")");
											if (m == ROW_NUM - 1) {
												Log.i("mylog", "不能向下滑动");
											} else {
												Drawable d = imageView.getDrawable();
												Drawable d1 = imageViewArray[m + 1][n].getDrawable();
												imageViewArray[m][n].setImageDrawable(d1);
												imageViewArray[m + 1][n].setImageDrawable(d);
											}
										}
									}
								}
							}
							break;
						}
						return true;
					}
				});
				iv.setImageBitmap(imagePieces.get(j + i * COL_NUM).getBitmap());
				imageViewArray[i][j] = iv;
			}
		}
		return imageViewArray;
	}
	
	private List<ImagePiece> getRandomImagePieces() {
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.puzzle);
		List<ImagePiece> ips = ImageSplitter.split(bitmap, COL_NUM, ROW_NUM);
		List<ImagePiece> pieces = new ArrayList<ImagePiece>(ips.size());
		Random random = new Random();
		int num = 0;
		while(true) {
			num = random.nextInt(ips.size());
			if (!pieces.contains(ips.get(num))) {
				pieces.add(ips.get(num));
			}
			
			if (pieces.size() == ips.size()) {
				break;
			}
		}
		return pieces;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
}
