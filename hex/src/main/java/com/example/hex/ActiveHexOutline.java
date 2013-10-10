package com.example.hex;

import android.graphics.*;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.Shape;

/**
 * Created by Adam on 10/8/13.
 */
public class ActiveHexOutline extends ShapeDrawable {
	public ActiveHexOutline() {
		Path path = new Path();
		int stdW = 102;
		int stdH = 102;
		float w3 = stdW / 3;
		float h2 = stdH / 2;

		path.moveTo(0, h2);
		h2 -= 6 / 2;
		path.rLineTo(w3, -h2); path.rLineTo(w3, 0); path.rLineTo(w3, h2);
		path.rLineTo(-w3, h2); path.rLineTo(-w3, 0); path.rLineTo(-w3, -h2);
		Shape s = new PathShape(path, stdW, stdH);
		setShape(s);
		Paint p = getPaint();
		p.setColor(0xffffff00);
		p.setStyle(Paint.Style.STROKE);
		p.setStrokeWidth(2);
	}
}
