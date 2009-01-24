/*
 * Copyright (c) 2009, Balazs Lecz <leczbalazs@gmail.com>
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * 
 *  * Redistributions of source code must retain the above copyright notice,
 *       this list of conditions and the following disclaimer.
 * 
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *       this list of conditions and the following disclaimer in the documentation
 *       and/or other materials provided with the distribution.
 * 
 *  * Neither the name of Balazs Lecz nor the names of
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

import android.view.View;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.os.SystemClock;
import android.os.Vibrator;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.GestureDetector;

import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.view.KeyEvent;


public class TiltMazesView extends View {
	private boolean DEBUG = true;

	private Paint paint;

	private SensorManager mSensorManager;
	private Vibrator mVibrator;

	private float accelThreshold = 2;
	private float accelX = 0;
	private float accelY = 0;
	@SuppressWarnings("unused")
	private float accelZ = 0;

	private static float wallWidth = 5;
	private float x_min;
	private float y_min;
	private float x_max;
	private float y_max;
	private float unit;

	private Timer timer;
	private long t1 = 0;
	private long t2 = 0;
	private long dt = 0;
	private int drawStep = 0;
	private long[] drawTimeHistory;
	private int drawTimeHistorySize = 20;

	private GestureDetector gestureDetector;
	private Handler handler;

	private Map map;
	private Ball ball;

	private Direction commandedRollDirection = Direction.NONE;

	private final SensorListener mSensorAccelerometer = new SensorListener() {

		public void onSensorChanged(int sensor, float[] values) {
			accelX = values[0];
			accelY = values[1];
			accelZ = values[2];

			commandedRollDirection = Direction.NONE;
			if (Math.abs(accelX) > Math.abs(accelY)) {
				if (accelX < -accelThreshold) commandedRollDirection = Direction.LEFT;
				if (accelX >  accelThreshold) commandedRollDirection = Direction.RIGHT;
			}
			else {
				if (accelY < -accelThreshold) commandedRollDirection = Direction.DOWN;
				if (accelY >  accelThreshold) commandedRollDirection = Direction.UP;
			}

			if (commandedRollDirection != Direction.NONE && ! ball.isRolling()) {
				ball.roll(commandedRollDirection);
			}
		}

		public void onAccuracyChanged(int sensor, int accuracy) {
		}
	};

	
	public TiltMazesView(Context context, Activity activity) {
		super(context);

		setFocusableInTouchMode(true);
		
		// Set up default Paint values
		paint = new Paint();
		paint.setAntiAlias(true);

		// Request vibrator service
		mVibrator = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);

		// Register the sensor listener
		mSensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
		mSensorManager.registerListener(mSensorAccelerometer, SensorManager.SENSOR_ACCELEROMETER,
				SensorManager.SENSOR_DELAY_GAME);

		// Calculate geometry
		int w = getWidth();
		int h = getHeight();
		x_min = wallWidth / 2;
		y_min = wallWidth / 2;
		x_max = Math.min(w, h) - wallWidth / 2;
		y_max = x_max;

		map = new Map(MapDesigns.MapM6B);
		
		// Create ball
		ball = new Ball(this, map,
				map.getInitialPositionX(),
				map.getInitialPositionY());

		// Stats
		drawTimeHistory = new long[drawTimeHistorySize];
		t1 = SystemClock.elapsedRealtime();

		// Create gesture detector to detect flings
		gestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
			@Override
			public boolean onDown(MotionEvent e) {
				return true;
			}
						
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2,
					float velocityX, float velocityY) {
				// Roll the ball in the direction of the fling				
				commandedRollDirection = Direction.NONE;
				
				if (Math.abs(velocityX) > Math.abs(velocityY)) {
					if (velocityX < 0)	commandedRollDirection = Direction.LEFT;
					else				commandedRollDirection = Direction.RIGHT;
				}
				else {
					if (velocityY < 0)	commandedRollDirection = Direction.UP;
					else 				commandedRollDirection = Direction.DOWN;
				}

				if (commandedRollDirection != Direction.NONE && ! ball.isRolling()) {
					ball.roll(commandedRollDirection);
				}
				
				return true;
			}
		});
		gestureDetector.setIsLongpressEnabled(false);
		
		// Create message handler
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case Messages.MSG_INVALIDATE:
					invalidate();
					return;
				case Messages.MSG_REACHED_GOAL:
					vibrate(100);
					return;
				case Messages.MSG_REACHED_WALL:
					vibrate(12);
					return;
				case Messages.MSG_RESTART:
					ball.setX(map.getInitialPositionX());
					ball.setY(map.getInitialPositionY());
					map.init();
					invalidate();
					return;
				}
				super.handleMessage(msg);
			}
		};
		
		// Schedule a redraw at 20Hz
		if (DEBUG) {
			TimerTask redrawTask = new TimerTask() {
				public void run() {
					postInvalidate();
				}
			};
			timer = new Timer(true);
			timer.schedule(redrawTask, 0, 1000/*ms*/ / 20);
		}	
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		x_max = Math.min(w, h) - wallWidth / 2;
		y_max = x_max;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_LEFT:
			ball.roll(Direction.LEFT);
			return true;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			ball.roll(Direction.RIGHT);
			return true;
		case KeyEvent.KEYCODE_DPAD_UP:
			ball.roll(Direction.UP);
			return true;
		case KeyEvent.KEYCODE_DPAD_DOWN:
			ball.roll(Direction.DOWN);
			return true;

		default:
			return false;
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return gestureDetector.onTouchEvent(event);
	}

	@Override
	public void onDraw(Canvas canvas) {
		// FPS stats
		t2 = SystemClock.elapsedRealtime();
		dt = (t2 - t1);
		t1 = t2;
		drawTimeHistory[drawStep % drawTimeHistorySize] = dt;
		drawStep = drawStep + 1;

		// Set up geometry
		float x_unit = ((x_max - x_min) / map.getSizeX());
		float y_unit = ((y_max - y_min) / map.getSizeY());
		unit = Math.min(x_unit, y_unit);

		drawWalls(canvas);
		drawGoals(canvas);
		drawBall(canvas);

		if (DEBUG) {
			drawDirection(canvas);
			// Print FPS
			paint.setColor(Color.WHITE);
			paint.setStyle(Style.STROKE);
			paint.setStrokeWidth(1);
			canvas.drawText("FPS: " + getFPS(), 20, 30, paint);
		}
	}

	public void sendEmptyMessage(int msg) {
		handler.sendEmptyMessage(msg);
	}

	public void sendMessage(Message msg) {
		handler.sendMessage(msg);
	}

	public void registerListener() {
		mSensorManager.registerListener(mSensorAccelerometer, SensorManager.SENSOR_ACCELEROMETER,
				SensorManager.SENSOR_DELAY_GAME);
	}

	public void unregisterListener() {
		mSensorManager.unregisterListener(mSensorAccelerometer);
	}

	public double getFPS() {
		double avg = 0;
		int n = 0;
		for (long t : drawTimeHistory) {
			if (t > 0) {
				avg = avg + t;
				n = n + 1;
			}
		}
		if (n == 0) return -1;
		return 1000 * n / avg;
	}

	public void vibrate(long milliseconds) {
		mVibrator.vibrate(milliseconds);
	}

	private void drawWalls(Canvas canvas) {
		int walls;

		paint.setColor(Color.RED);
		paint.setStrokeWidth(wallWidth);
		paint.setStrokeCap(Cap.ROUND);

		for (int y = 0; y < map.getSizeY(); y++) {
			for (int x = 0; x < map.getSizeX(); x++) {

				walls = map.getWalls(x, y);

				if ((walls & Wall.TOP) > 0) {
					canvas.drawLine(
							x_min + x * unit,
							y_min + y * unit,
							x_min + (x + 1) * unit,
							y_min + y * unit,
							paint);
				}
				if ((walls & Wall.RIGHT) > 0) {
					canvas.drawLine(
							x_min + (x + 1) * unit,
							y_min + y * unit,
							x_min + (x + 1) * unit,
							y_min + (y + 1) * unit,
							paint);
				}
				if ((walls & Wall.BOTTOM) > 0) {
					canvas.drawLine(
							x_min + x * unit,
							y_min + (y + 1) * unit,
							x_min + (x + 1) * unit,
							y_min + (y + 1) * unit,
							paint);
				}
				if ((walls & Wall.LEFT) > 0) {
					canvas.drawLine(
							x_min + x * unit,
							y_min + y * unit,
							x_min + x * unit,
							y_min + (y + 1) * unit,
							paint);
				}
			}		
		}
	}

	private void drawGoals(Canvas canvas) {
		int sizeX = map.getSizeX();
		int sizeY = map.getSizeY();

		paint.setColor(Color.BLUE);
		paint.setStyle(Style.FILL);

		for (int y = 0; y < sizeY; y++) {
			for (int x = 0; x < sizeX; x++) {
				if (map.getGoal(x, y) > 0) {
					canvas.drawRect(
							x_min + x * unit + unit / 4,
							y_min + y * unit + unit / 4,
							x_min + (x + 1) * unit - unit / 4,
							y_min + (y + 1) * unit - unit / 4,
							paint);
				}
			}		
		}
	}

	private void drawBall(Canvas canvas) {
		paint.setColor(Color.WHITE);
		paint.setStyle(Style.FILL);
		canvas.drawCircle(
				x_min + (ball.getX() + 0.5f) * unit,
				y_min + (ball.getY() + 0.5f) * unit,
				unit * 0.4f,
				paint
		);

		// Draw target position
		if (DEBUG) {
			paint.setStrokeWidth(1);
			paint.setColor(Color.MAGENTA);
			paint.setStyle(Style.STROKE);
			canvas.drawCircle(
					x_min + (ball.getXTarget() + 0.5f) * unit,
					y_min + (ball.getYTarget() + 0.5f) * unit,
					unit * 0.4f,
					paint
			);
		}
	}

	private void drawDirection(Canvas canvas) {
		paint.setColor(Color.GREEN);
		paint.setStrokeWidth(5);
		canvas.drawLine(x_max / 2, y_max / 2, x_max / 2 + accelX * 10, y_max / 2 - accelY * 10, paint);

		paint.setColor(Color.RED);
		if (commandedRollDirection == Direction.NONE) {
			canvas.drawCircle(x_max / 2, y_max / 2, 5, paint);
		}
		else {
			int x = 0;
			int y = 0;
			switch (commandedRollDirection) {
			case LEFT: { x = -1; break;}
			case RIGHT:{ x =  1; break;}
			case UP:   { y = -1; break;}
			case DOWN: { y =  1; break;}
			}
			canvas.drawLine(x_max / 2, y_max / 2, x_max / 2 + x * 20, y_max / 2 + y * 20, paint);
		}
	}
}
