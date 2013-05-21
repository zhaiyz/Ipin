package com.zhaiyz.ipin.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

import com.zhaiyz.ipin.R;
import com.zhaiyz.ipin.layout.ImageLayout;
import com.zhaiyz.ipin.util.ImageGestureDetector;
import com.zhaiyz.ipin.util.ImagePiece;
import com.zhaiyz.ipin.util.ImageSplitter;

public class MainActivity extends Activity {
	
	private static final int COL_NUM = 3;
	
	private static final int ROW_NUM = 4;
	
	private GestureDetector gestureDetector;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ImageLayout imageLayout = new ImageLayout(this, getImageViewArray());
		gestureDetector = new GestureDetector(this, new ImageGestureDetector());
		setContentView(imageLayout);
	}

	private ImageView[][] getImageViewArray() {
		ImageView[][] imageViewArray = new ImageView[ROW_NUM][COL_NUM];
		List<ImagePiece> imagePieces = getRandomImagePieces();
		for(int i = 0; i < ROW_NUM; i++) {
			for (int j = 0; j < COL_NUM; j++) {
				ImageView iv = new ImageView(this);
				iv.setLongClickable(true);
				iv.setOnTouchListener(new OnTouchListener() {
					
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						return gestureDetector.onTouchEvent(event);
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
