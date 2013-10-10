package com.example.hex;

import android.graphics.Color;

/**
 * Created by Adam on 10/9/13.
 */
public class StoneHex extends HexDrawable {
	public StoneHex() {
		super(0xff0F1F23);
	 	name = "Stone";
		textColor = Color.WHITE;
		this.textPaint.setColor(Color.WHITE);
	}
}
