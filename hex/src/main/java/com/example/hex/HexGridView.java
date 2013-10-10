package com.example.hex;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Adam on 10/8/13.
 */
public class HexGridView  extends View {
	private ArrayList<ArrayList<HexDrawable>> hexes = new ArrayList<ArrayList<HexDrawable>>();

	Pawn pawn = new Pawn();
	HexDrawable activeHex = null;
	ActiveHexOutline activeHexOutline = new ActiveHexOutline();

	public HexGridView(Context context) {
		super(context);
		this.pawn.setBounds(40, 40, 60, 70);
	}

	protected void onDraw(Canvas canvas) {
		for (ArrayList<HexDrawable> row : this.hexes) {
			for (HexDrawable hex : row) {
				hex.draw(canvas);
			}
		}
		pawn.draw(canvas);
		if (activeHex != null) {
			activeHexOutline.draw(canvas);
		}
	}

	public void setSize(int rows, int columns) {
		this.hexes.ensureCapacity(rows);
		for (int r = 0; r < rows; ++r) {
			ArrayList<HexDrawable> row = new ArrayList<HexDrawable>();
			for (int c = 0; c < columns; ++c) {
				row.add(null);
			}
			this.hexes.add(row);

		}
	}

	public void floodFill(HexDrawable hex) {
		for (int r = 0; r < this.hexes.size(); ++r) {
			ArrayList<HexDrawable> row = this.hexes.get(r);
			for (int c = 0; c < row.size(); ++c) {
				if (row.get(c) == null) {
					HexDrawable copy = new HexDrawable(hex);
					int x = (c > 0 ? c * 68 : 0);
					int y = r * 100 + (c % 2 > 0 ? 50 : 0);
					copy.setBounds(x, y, x + 100, y + 100);
					row.set(c, copy);
				}
			}
		}
	}

	public void addHex(HexDrawable hex, int row, int column) {
		if (row < this.hexes.size()) {
			if (column < this.hexes.get(row).size()) {
				HexDrawable copy = new HexDrawable(hex);
				int x = (column > 0 ? column * 68 : 0);
				int y = row * 100 + (column % 2 > 0 ? 50 : 0);
				copy.setBounds(x, y, x + 100, y + 100);
				this.hexes.get(row).set(column, copy);
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		// MotionEvent reports input details from the touch screen
		// and other input controls. In this case, you are only
		// interested in events where the touch position changed.

		float x = e.getX();
		float y = e.getY();

		switch (e.getAction()) {
			case MotionEvent.ACTION_UP:
				for (ArrayList<HexDrawable> row : this.hexes) {
					for (HexDrawable hex : row) {
						if (hex.hitTest(x, y)) {
							//Toast.makeText(this.getContext(), "Hit test: " + hex.getName(), Toast.LENGTH_SHORT).show();
							if (this.activeHex == hex) {
								int left = hex.getBounds().left + this.pawn.getOffsetX();
								int top = hex.getBounds().top + this.pawn.getOffsetY();
								int width = hex.getBounds().left + this.pawn.getOffsetX() + this.pawn.getWidth();
								int height = hex.getBounds().top + this.pawn.getOffsetY() + this.pawn.getHeight();
								this.pawn.setBounds(left, top, width, height);
								this.activeHex = null;
							}
							else {
								this.activeHex = hex;
								this.activeHexOutline.setBounds(hex.getBounds());
							}
						}
					}
				}
		}
		invalidate();
		return true;
	}
}
