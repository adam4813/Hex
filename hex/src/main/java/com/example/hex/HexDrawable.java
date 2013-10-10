package com.example.hex;

import android.graphics.*;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.Shape;

/**
 * Created by Adam on 10/8/13.
 */
public class HexDrawable extends ShapeDrawable {
	protected String name;
	protected int color;
	protected int textColor;
	private static double distanceToEdge;
	protected Paint textPaint = new Paint();

	public HexDrawable(int color) {
		this.name = "0x00";
		this.color = color;

		this.textPaint.setColor(Color.BLACK);
		this.textPaint.setStyle(Paint.Style.FILL);
		this.textPaint.setTextSize(15);
		this.textPaint.setTextAlign(Paint.Align.CENTER);

		Path path = new Path();
		int stdW = 100;
		int stdH = 100;
		float w3 = stdW / 3;
		float h2 = stdH / 2;

		double edgeMiddleX = (stdW + stdW * 0.75) / 2;
		double edgeMiddleY = (stdH / 2 + stdH) / 2;
		double edgeX = (edgeMiddleX - stdW / 2) * (edgeMiddleX - stdW / 2);
		double edgeY = (edgeMiddleY - stdH / 2) * (edgeMiddleY - stdH / 2);
		distanceToEdge = Math.sqrt(edgeX + edgeY) * 1.05;

		path.moveTo(0, h2);
		h2 -= 6 / 2;
		path.rLineTo(w3, -h2); path.rLineTo(w3, 0); path.rLineTo(w3, h2);
		path.rLineTo(-w3, h2); path.rLineTo(-w3, 0); path.rLineTo(-w3, -h2);
		Shape s = new PathShape(path, stdW, stdH);
		setShape(s);
		Paint p = getPaint();
		p.setColor(color);
		p.setStyle(Paint.Style.FILL_AND_STROKE);
		p.setStrokeWidth(1);
	}

	public HexDrawable(HexDrawable other) {
		this(other.color);
		this.textColor = other.textColor;
		this.textPaint.setColor(other.textColor);
		this.name = other.name;
	}

	public boolean hitTest(float x, float y) {
		Rect bounds = this.getBounds();
		float quadX = Math.abs(x - bounds.centerX());
		float quadY = Math.abs(y - bounds.centerY());
		if (quadX > bounds.width() || quadY > bounds.height()) {
			return false;
		}
		double distanceToPoint = Math.sqrt((bounds.centerX() - x) * (bounds.centerX() - x) + (bounds.centerY() - y) * (bounds.centerY() - y));

		return distanceToPoint < distanceToEdge;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		canvas.drawText(this.name, this.getBounds().centerX(), this.getBounds().top + 17, this.textPaint);
	}
}
