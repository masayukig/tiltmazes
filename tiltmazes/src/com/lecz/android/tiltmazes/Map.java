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

public class Map {
	private MapDesign mapDesign;

	private int[][] mGoals;
	private int mGoalCount;

	public Map(MapDesign design) {
		mapDesign = design;
		init();
	}
	
	public void init() {
		if (mGoals == null) mGoals = new int[mapDesign.getSizeY()][mapDesign.getSizeX()];
		
		int[][] goals = mapDesign.getGoals();
		for (int y = 0; y < mapDesign.getSizeY(); y++)
			for (int x = 0; x < mapDesign.getSizeX(); x++)
				mGoals[y][x] = goals[y][x];

		mGoalCount = mapDesign.getGoalCount();
	}
	
	public String getName() {
		return mapDesign.getName();
	}

	public int getWalls(int x, int y) {
		return mapDesign.getWalls(x, y);
	}

	public int getGoal(int x, int y) {
		return mGoals[y][x];
	}

	public void removeGoal(int x, int y) {
		mGoalCount = mGoalCount - mGoals[y][x];
		mGoals[y][x] = 0;
	}

	public int getSizeX() {
		return mapDesign.getSizeY();
	}

	public int getSizeY() {
		return mapDesign.getSizeY();
	}
	
	public int getInitialPositionX() {
		return mapDesign.getInitialPositionX();
	}

	public int getInitialPositionY() {
		return mapDesign.getInitialPositionY();
	}
}
