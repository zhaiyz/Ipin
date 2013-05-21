package com.zhaiyz.ipin.util;

import android.graphics.Bitmap;

public class ImagePiece {

	private int index;
	
	private Bitmap bitmap;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		
		if (!(o instanceof ImagePiece)) {
			return false;
		}
		
		ImagePiece ip = (ImagePiece) o;
		if (index == ip.getIndex() && ((bitmap == null && ip == null) || bitmap.equals(ip.getBitmap()))) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return index + bitmap.hashCode();
	}
}
