package com.zhaiyz.ipin.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ImageLayout extends LinearLayout {

	private ImageView[][] imageViewArray;

	public ImageLayout(Context context, ImageView[][] imageViewArray) {
		super(context);
		this.imageViewArray = imageViewArray;
		setContent(context);
	}

	public ImageLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	private void setContent(Context context) {
		LinearLayout linearLayoutGlobal = new LinearLayout(context);
		linearLayoutGlobal.setOrientation(LinearLayout.VERTICAL);

		for (int i = 0; i < imageViewArray.length; i++) {
			LinearLayout ll = new LinearLayout(context);
			ll.setOrientation(LinearLayout.HORIZONTAL);

			int length = imageViewArray[i].length;

			for (int j = 0; j < length; j++) {
				ImageView imageView = imageViewArray[i][j];
				imageView.setPadding(1, 1, 1, 1);
				if (imageView != null) {
					ll.addView(imageView, new LayoutParams(
							LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT));
				}
			}
			linearLayoutGlobal.addView(ll);
		}
		this.addView(linearLayoutGlobal);
	}

}
