/*
 * Copyright (c) 2009, Balazs Lecz <leczbalazs@gmail.com>
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * 
 *     * Redistributions of source code must retain the above copyright notice,
 *       this list of conditions and the following disclaimer.
 * 
 *     * Redistributions in binary form must reproduce the above copyright notice,
 *       this list of conditions and the following disclaimer in the documentation
 *       and/or other materials provided with the distribution.
 * 
 *     * Neither the name of Balazs Lecz nor the names of
 *       contributors may be used to endorse or promote products derived from this
 *       software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */

package com.lecz.android.tiltmazes;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


public class TiltMazesActivity extends Activity {
	private TiltMazesView mView;
	
	private static final int MENU_RESTART = 1;
	private static final int MENU_MAP_PREV = 2;
	private static final int MENU_MAP_NEXT = 3;
	
	private TextView mMazeNameLabel;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d(this.toString(), "onCreate() called");

		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.game_layout);
		
		mView = (TiltMazesView) findViewById(R.id.maze_view);
		
		mMazeNameLabel = (TextView) findViewById(R.id.maze_name_label);
		mView.setMazeNameLabel(mMazeNameLabel);
		mMazeNameLabel.setText(getResources().getText(R.string.maze_label) + " " + mView.getMap().getName());
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0, MENU_MAP_PREV, 0, R.string.menu_map_prev);
        menu.add(0, MENU_RESTART, 0, R.string.menu_restart);
        menu.add(0, MENU_MAP_NEXT, 0, R.string.menu_map_next);
        
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case MENU_RESTART:
        	mView.sendEmptyMessage(Messages.MSG_RESTART);
            return true;
        case MENU_MAP_PREV:
        	mView.sendEmptyMessage(Messages.MSG_MAP_PREVIOUS);
            return true;
        case MENU_MAP_NEXT:
        	mView.sendEmptyMessage(Messages.MSG_MAP_NEXT);
            return true;        	
        }
        
        return false;
    }
    
    @Override
    protected void onStart() {
		Log.d(this.toString(), "onStart() called");

    	// TODO Auto-generated method stub
    	super.onStart();
    }

    @Override
    protected void onStop() {
		Log.d(this.toString(), "onStop() called");

    	// TODO Auto-generated method stub
    	super.onStop();
    }
    
    @Override
	protected void onPause() {
		Log.d(this.toString(), "onPause() called");

		super.onPause();
		mView.unregisterListener();
	}

	@Override
	protected void onResume() {
		Log.d(this.toString(), "onResume() called");

		super.onResume();
		mView.registerListener();
	}

	@Override
	public void onSaveInstanceState(Bundle icicle) {
		Log.d(this.toString(), "onSaveInstanceState() called");

		super.onSaveInstanceState(icicle);
		mView.unregisterListener();
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		Log.d(this.toString(), "onRestoreInstanceState() called");

		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
    protected void onDestroy() {
		Log.d(this.toString(), "onDestroy() called");

    	// TODO Auto-generated method stub
    	super.onDestroy();
    }
}
