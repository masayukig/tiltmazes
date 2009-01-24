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

import java.util.Timer;
import java.util.TimerTask;

import android.os.SystemClock;

public class Ball {
	private TiltMazesView parentView;
	
	private float x = 0;
	private float y = 0;
	private int x_target;
	private int y_target;
	private int v_x = 0;
	private int v_y = 0;
	private float v = 0.005f; // Speed
	
	private long t1;
	private long t2;
    private float dt;
    private static final int dt_target = 1000/25; // Target time step (ms)
    private Timer timer;

    private Map map;
	
	private boolean isRolling = false;
	private Direction rollDirection = Direction.NONE;
	
	public Ball(TiltMazesView view, Map map, int init_x, int init_y) {
		parentView = view;
		this.map = map; 
		x = init_x;
		y = init_y;
		x_target = init_x;
		y_target = init_y;
	}

	public boolean isRolling() {
		return isRolling;
	}
	
	private boolean isValidMove(int x, int y, Direction dir) {
		switch (dir) {
			case LEFT: {
				// Left wall
				if (x <= 0) return false;
				if ((map.getWalls(x, y) & Wall.LEFT) > 0 ||
					(map.getWalls(x - 1, y) & Wall.RIGHT) > 0	
					) return false;
				break;
			}
			case RIGHT:{
				// Right wall
				if (x >= map.getSizeX() - 1) return false;
				if ((map.getWalls(x, y) & Wall.RIGHT) > 0 ||
					(map.getWalls(x + 1, y) & Wall.LEFT) > 0	
					) return false;
				break;
			}
			case UP:   {
				// Top wall
				if (y <= 0) return false;
				if ((map.getWalls(x, y) & Wall.TOP) > 0 ||
					(map.getWalls(x, y - 1) & Wall.BOTTOM) > 0	
					) return false;
				break;
			}
			case DOWN: {
				// Bottom wall
				if (y >= map.getSizeY() - 1) return false;
				if ((map.getWalls(x, y) & Wall.BOTTOM) > 0 ||
					(map.getWalls(x, y + 1) & Wall.TOP) > 0	
					) return false;
				break;
			}		
		}
		
		return true;
	}

	
	public synchronized void roll(Direction dir) {
		// Don't accept another roll command if the ball is already rolling
		if (isRolling) return;

		// Set speed according to commanded direction
		switch (dir) {
			case LEFT: { v_x = -1; v_y =  0; break;}
			case RIGHT:{ v_x =  1; v_y =  0; break;}
			case UP:   { v_x =  0; v_y = -1; break;}
			case DOWN: { v_x =  0; v_y =  1; break;}
		}
		
		// Calculate target position
		x_target = Math.round(x);
		y_target = Math.round(y);
		while (isValidMove(x_target, y_target, dir)) {
			x_target = x_target + v_x;
			y_target = y_target + v_y;
		}

		// We can't move
		if (x_target == x && y_target == y) return;
		
		// Let's roll...
		isRolling = true;
		rollDirection = dir;

		// Schedule animation		
		t1 = SystemClock.elapsedRealtime();
		TimerTask simTask = new TimerTask() {
			public void run() {
				doStep();
			}
		};
		timer = new Timer(true);
		timer.schedule(simTask, 0, dt_target);
	}
	
	private void doStep() {
		// Calculate elapsed time since last step
		t2 = SystemClock.elapsedRealtime();
		dt = (float)(t2 - t1);
		t1 = t2;

		// Calculate next position
		float xNext = x + v_x * v * dt;
		float yNext = y + v_y * v * dt;

		// Check if we have reached the target position
		boolean reachedTarget = false;
		switch (rollDirection) {
		case LEFT:
			if (xNext <= 1f * x_target) {
				xNext = x_target;
				reachedTarget = true;
			}
			break;
		case RIGHT:
			if (xNext >= 1f * x_target) {
				xNext = x_target;
				reachedTarget = true;
			}
			break;
		case UP:
			if (yNext <= 1f * y_target) {
				yNext = y_target;
				reachedTarget = true;
			}
			break;
		case DOWN:
			if (yNext > 1f * y_target) {
				yNext = y_target;
				reachedTarget = true;
			}
			break;
		}
			
    	x = xNext;
		y = yNext;			 

		// Check if we have reached a goal
		if (map.getGoal(Math.round(x), Math.round(y)) == 1) {
			map.removeGoal(Math.round(x), Math.round(y));
			// TODO
			// Send MSG_REACHED_GOAL message to the parent View
			// (maybe add number of remaining goals here?)
			parentView.sendEmptyMessage(Messages.MSG_REACHED_GOAL);
		}
		
		// Stop rolling if we have reached the target position
		if (reachedTarget) {
			rollDirection = Direction.NONE;
			v_x = 0;
			v_y = 0;
			isRolling = false;
			timer.cancel();

			// TODO
			// Send MSG_REACHED_WALL message to the parent View
			parentView.sendEmptyMessage(Messages.MSG_REACHED_WALL);
		}
		
		// Send invalidate message to the parent View,
		// so that it gets redrawn during the next cycle.
		parentView.postInvalidate();
	}
	
	public Direction getRollDirection() {
		return rollDirection;
	}
	
	public float getX() {
		return x;
	}	

	public float getY() {
		return y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getXTarget() {
		return x_target;
	}	

	public float getYTarget() {
		return y_target;
	}
	
}
