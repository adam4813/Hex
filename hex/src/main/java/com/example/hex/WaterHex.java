package com.example.hex;

import android.graphics.Color;

/**
 * Created by Adam on 10/9/13.
 */
public class WaterHex extends HexDrawable {
	public WaterHex() {
		super(0xff2F55cd);
	 	name = "Water";
		textColor = Color.WHITE;
		this.textPaint.setColor(Color.WHITE);
	}
}
