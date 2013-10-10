package com.example.hex;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.Shape;

/**
 * Created by Adam on 10/10/13.
 */
public class Pawn extends ShapeDrawable {
	private int width;
	private int height;
	private int offsetX;
	private int offsetY;

	public Pawn() {
		Path path = new Path();
		path.moveTo(10.0f, 30.0f);
		path.lineTo(5.0f, 10.0f);
		path.addArc(new RectF(4, 0, 16, 15), -180, 180);
		path.lineTo(10.0f, 30.0f);
		path.close();

		Shape s = new PathShape(path, 20, 30);
		setShape(s);

		Paint p = getPaint();
		p.setColor(Color.RED);
		p.setStyle(Paint.Style.FILL_AND_STROKE);
		p.setStrokeWidth(1);

		this.offsetX = 40;
		this.offsetY = 40;
		this.width = 20;
		this.height = 30;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getOffsetX() {
		return offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}
}
