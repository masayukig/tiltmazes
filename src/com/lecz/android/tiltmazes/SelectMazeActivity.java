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

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class SelectMazeActivity extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);

        setListAdapter(new MazeListAdapter(this));
        setTitle(R.string.select_maze_title);
        setContentView(R.layout.select_maze_layout);
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	Intent result = new Intent();
    	result.putExtra("selected_maze", position);
    	setResult(RESULT_OK, result);
    	finish();
    }
    
    private class MazeListAdapter extends BaseAdapter {
    	
    	private Context mContext;
    	
        public MazeListAdapter(Context context) {
            mContext = context;
        }

        public int getCount() {
            return MapDesigns.designList.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

		public View getView(int position, View convertView, ViewGroup parent) {
			TextView mazeNameView;
			if (convertView == null) {
				mazeNameView = new TextView(mContext);
			}
			else {
				mazeNameView = (TextView)convertView;
			}
			MapDesign m = MapDesigns.designList.get(position);
			mazeNameView.setText(""
					+ (position + 1)
					+ " - "
					+ m.getName()
					+ " (" + m.getSizeX() + "x" + m.getSizeY() + "), "
					+ m.getGoalCount() + " goal" + (m.getGoalCount() > 1 ? "s" : "")
				);
			mazeNameView.setTextAppearance(mContext, android.R.style.TextAppearance_Large);
			mazeNameView.setPadding(8, 10, 0, 14);
			return mazeNameView;
		}
    }
}
