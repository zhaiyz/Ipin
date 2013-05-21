package com.zhaiyz.ipin.util;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;

public final class ImageSplitter {

	private ImageSplitter() {
	}
	
	public static List<ImagePiece> split(Bitmap bitmap, int xPiece, int yPiece) {
		List<ImagePiece> pieces = new ArrayList<ImagePiece>(xPiece * yPiece);
		
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		int pieceWidth = width / xPiece;
		int pieceHeight = height / yPiece;
		
		for (int i = 0; i < yPiece; i++) {
			for (int j = 0; j < xPiece; j++) {
				int xValue = j * pieceWidth;
				int yValue = i * pieceHeight;
				
				ImagePiece ip = new ImagePiece();
				ip.setIndex(j + i * xPiece);
				ip.setBitmap(Bitmap.createBitmap(bitmap, xValue, yValue, pieceWidth, pieceHeight));
				
				pieces.add(ip);
			}
		}
		return pieces;
	}
}