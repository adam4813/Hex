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

	public HexDrawable(int color) {
		this.name = "0x00";
		this.color = color;
		this.textColor = Color.BLACK;
		Path path = new Path();
		int stdW = 100;
		int stdH = 100;
		float w3 = stdW / 3;
		float h2 = stdH / 2;
		path.moveTo(0, h2);
		h2 -= 6 / 2;
		path.rLineTo(w3, -h2); path.rLineTo(w3, 0); path.rLineTo(w3, h2);
		path.rLineTo(-w3, h2); path.rLineTo(-w3, 0); path.rLineTo(-w3, -h2);
		Shape s = new PathShape(path, stdW, stdH);
		setShape(s);
		Paint p = getPaint();
		p.setColor(0xff00ff00);
		p.setColor(color);
		p.setStyle(Paint.Style.FILL_AND_STROKE);
		p.setStrokeWidth(1);
	}

	public HexDrawable(HexDrawable other) {
		this(other.color);
		this.name = other.name;
	}

	public boolean hitTest(float x, float y) {
		Rect bounds = this.getBounds();
		float quadX = Math.abs(x - bounds.centerX());
		float quadY = Math.abs(y - bounds.centerY());
		if (quadX > bounds.width() || quadY > bounds.height()) {
			return false;
		}
		//return 4 * (bounds.width() * bounds.height() - bounds.width() * quadX - bounds.height() * quadY) >= 0;
		double edgeMiddleX = (bounds.width() + bounds.width() * 0.75) / 2;
		double edgeMiddleY = (bounds.height() / 2 + bounds.height()) / 2;
		double edgeX = (edgeMiddleX + bounds.left - bounds.centerX()) * (edgeMiddleX + bounds.left - bounds.centerX());
		double edgeY = (edgeMiddleY + bounds.top - bounds.centerY()) * (edgeMiddleY + bounds.top - bounds.centerY());
		double distanceToEdge = Math.sqrt(edgeX + edgeY) * 1.05;
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
		Paint p = getPaint();
		p.setColor(this.textColor);
		p.setStyle(Paint.Style.FILL);
		p.setTextSize(15);
		p.setTextAlign(Paint.Align.CENTER);
		canvas.drawText(this.name, this.getBounds().centerX(), this.getBounds().top + 17, p);
	}
}
