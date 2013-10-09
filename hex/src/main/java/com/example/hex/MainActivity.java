package com.example.hex;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {
	private HexGridView hexview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		this.hexview = new HexGridView(this);
		this.hexview.setSize(4,4);

		this.hexview.addHex(new StoneHex(), 0, 0);
		this.hexview.addHex(new StoneHex(), 1, 0);
		this.hexview.addHex(new StoneHex(), 0, 2);
		this.hexview.addHex(new StoneHex(), 0, 3);

		GrassHex g1 = new GrassHex();
		this.hexview.floodFill(g1);

		setContentView(this.hexview);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
