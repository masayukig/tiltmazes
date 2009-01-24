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
	private boolean DEBUG = false;

	private Paint paint;

	private SensorManager mSensorManager;
	private Vibrator mVibrator;

	private static float ACCEL_THRESHOLD = 2;
	private float mAccelX = 0;
	private float mAccelY = 0;
	@SuppressWarnings("unused")
	private float mAccelZ = 0;

	private static float WALL_WIDTH = 5;
	private float mXMin;
	private float mYMin;
	private float mXMax;
	private float mYMax;
	private float mUnit;

	private Timer mTimer;
	private long mT1 = 0;
	private long mT2 = 0;
	private int mDrawStep = 0;
	private long[] mDrawTimeHistory;
	private int mDrawTimeHistorySize = 20;

	private GestureDetector mGestureDetector;
	private Handler mHandler;

	private Map mMap;
	private Ball mBall;
	private int mCurrentMap = 0;

	private Direction mCommandedRollDirection = Direction.NONE;

	private final SensorListener mSensorAccelerometer = new SensorListener() {

		public void onSensorChanged(int sensor, float[] values) {
			mAccelX = values[0];
			mAccelY = values[1];
			mAccelZ = values[2];

			mCommandedRollDirection = Direction.NONE;
			if (Math.abs(mAccelX) > Math.abs(mAccelY)) {
				if (mAccelX < -ACCEL_THRESHOLD) mCommandedRollDirection = Direction.LEFT;
				if (mAccelX >  ACCEL_THRESHOLD) mCommandedRollDirection = Direction.RIGHT;
			}
			else {
				if (mAccelY < -ACCEL_THRESHOLD) mCommandedRollDirection = Direction.DOWN;
				if (mAccelY >  ACCEL_THRESHOLD) mCommandedRollDirection = Direction.UP;
			}

			if (mCommandedRollDirection != Direction.NONE && ! mBall.isRolling()) {
				mBall.roll(mCommandedRollDirection);
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
		mXMin = WALL_WIDTH / 2;
		mYMin = WALL_WIDTH / 2;
		mXMax = Math.min(w, h) - WALL_WIDTH / 2;
		mYMax = mXMax;

		mMap = new Map(MapDesigns.designList.get(mCurrentMap));
		
		// Create ball
		mBall = new Ball(this, mMap,
				mMap.getInitialPositionX(),
				mMap.getInitialPositionY());

		// Stats
		mDrawTimeHistory = new long[mDrawTimeHistorySize];
		mT1 = SystemClock.elapsedRealtime();

		// Create gesture detector to detect flings
		mGestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
			@Override
			public boolean onDown(MotionEvent e) {
				return true;
			}
						
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2,
					float velocityX, float velocityY) {
				// Roll the ball in the direction of the fling				
				mCommandedRollDirection = Direction.NONE;
				
				if (Math.abs(velocityX) > Math.abs(velocityY)) {
					if (velocityX < 0)	mCommandedRollDirection = Direction.LEFT;
					else				mCommandedRollDirection = Direction.RIGHT;
				}
				else {
					if (velocityY < 0)	mCommandedRollDirection = Direction.UP;
					else 				mCommandedRollDirection = Direction.DOWN;
				}

				if (mCommandedRollDirection != Direction.NONE && ! mBall.isRolling()) {
					mBall.roll(mCommandedRollDirection);
				}
				
				return true;
			}
		});
		mGestureDetector.setIsLongpressEnabled(false);
		
		// Create message handler
		mHandler = new Handler() {
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
					mBall.setX(mMap.getInitialPositionX());
					mBall.setY(mMap.getInitialPositionY());
					mMap.init();
					invalidate();
					return;
				
				case Messages.MSG_MAP_PREVIOUS:
				case Messages.MSG_MAP_NEXT:
					if (msg.what == Messages.MSG_MAP_PREVIOUS) {
						if (mCurrentMap == 0) {
							// Wrap around
							mCurrentMap = MapDesigns.designList.size() - 1;
						}
						else {
							mCurrentMap = (mCurrentMap - 1) % MapDesigns.designList.size();
						}
					}
					else {
						mCurrentMap = (mCurrentMap + 1) % MapDesigns.designList.size();
					}
					mMap = new Map(MapDesigns.designList.get(mCurrentMap));
					mBall.setMap(mMap);
					mBall.setX(mMap.getInitialPositionX());
					mBall.setY(mMap.getInitialPositionY());
					mMap.init();
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
			mTimer = new Timer(true);
			mTimer.schedule(redrawTask, 0, 1000/*ms*/ / 20);
		}	
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		mXMax = Math.min(w, h) - WALL_WIDTH / 2;
		mYMax = mXMax;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_LEFT:
			mBall.roll(Direction.LEFT);
			return true;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			mBall.roll(Direction.RIGHT);
			return true;
		case KeyEvent.KEYCODE_DPAD_UP:
			mBall.roll(Direction.UP);
			return true;
		case KeyEvent.KEYCODE_DPAD_DOWN:
			mBall.roll(Direction.DOWN);
			return true;

		default:
			return false;
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return mGestureDetector.onTouchEvent(event);
	}

	@Override
	public void onDraw(Canvas canvas) {
		// FPS stats
		mT2 = SystemClock.elapsedRealtime();
		long dt = (mT2 - mT1);
		mT1 = mT2;
		mDrawTimeHistory[mDrawStep % mDrawTimeHistorySize] = dt;
		mDrawStep = mDrawStep + 1;

		// Set up geometry
		float xUnit = ((mXMax - mXMin) / mMap.getSizeX());
		float yUnit = ((mYMax - mYMin) / mMap.getSizeY());
		mUnit = Math.min(xUnit, yUnit);

		drawWalls(canvas);
		drawGoals(canvas);
		drawBall(canvas);

		// TODO(leczbalazs): put this into a proper layout 
		paint.setColor(Color.RED);
		canvas.drawText(mMap.getName(), 20, 30, paint);
		
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
		mHandler.sendEmptyMessage(msg);
	}

	public void sendMessage(Message msg) {
		mHandler.sendMessage(msg);
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
		for (long t : mDrawTimeHistory) {
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
		paint.setStrokeWidth(WALL_WIDTH);
		paint.setStrokeCap(Cap.ROUND);

		for (int y = 0; y < mMap.getSizeY(); y++) {
			for (int x = 0; x < mMap.getSizeX(); x++) {

				walls = mMap.getWalls(x, y);

				if ((walls & Wall.TOP) > 0) {
					canvas.drawLine(
							mXMin + x * mUnit,
							mYMin + y * mUnit,
							mXMin + (x + 1) * mUnit,
							mYMin + y * mUnit,
							paint);
				}
				if ((walls & Wall.RIGHT) > 0) {
					canvas.drawLine(
							mXMin + (x + 1) * mUnit,
							mYMin + y * mUnit,
							mXMin + (x + 1) * mUnit,
							mYMin + (y + 1) * mUnit,
							paint);
				}
				if ((walls & Wall.BOTTOM) > 0) {
					canvas.drawLine(
							mXMin + x * mUnit,
							mYMin + (y + 1) * mUnit,
							mXMin + (x + 1) * mUnit,
							mYMin + (y + 1) * mUnit,
							paint);
				}
				if ((walls & Wall.LEFT) > 0) {
					canvas.drawLine(
							mXMin + x * mUnit,
							mYMin + y * mUnit,
							mXMin + x * mUnit,
							mYMin + (y + 1) * mUnit,
							paint);
				}
			}		
		}
	}

	private void drawGoals(Canvas canvas) {
		int sizeX = mMap.getSizeX();
		int sizeY = mMap.getSizeY();

		paint.setColor(Color.BLUE);
		paint.setStyle(Style.FILL);

		for (int y = 0; y < sizeY; y++) {
			for (int x = 0; x < sizeX; x++) {
				if (mMap.getGoal(x, y) > 0) {
					canvas.drawRect(
							mXMin + x * mUnit + mUnit / 4,
							mYMin + y * mUnit + mUnit / 4,
							mXMin + (x + 1) * mUnit - mUnit / 4,
							mYMin + (y + 1) * mUnit - mUnit / 4,
							paint);
				}
			}		
		}
	}

	private void drawBall(Canvas canvas) {
		paint.setColor(Color.WHITE);
		paint.setStyle(Style.FILL);
		canvas.drawCircle(
				mXMin + (mBall.getX() + 0.5f) * mUnit,
				mYMin + (mBall.getY() + 0.5f) * mUnit,
				mUnit * 0.4f,
				paint
		);

		// Draw target position
		if (DEBUG) {
			paint.setStrokeWidth(1);
			paint.setColor(Color.MAGENTA);
			paint.setStyle(Style.STROKE);
			canvas.drawCircle(
					mXMin + (mBall.getXTarget() + 0.5f) * mUnit,
					mYMin + (mBall.getYTarget() + 0.5f) * mUnit,
					mUnit * 0.4f,
					paint
			);
		}
	}

	private void drawDirection(Canvas canvas) {
		paint.setColor(Color.GREEN);
		paint.setStrokeWidth(5);
		canvas.drawLine(mXMax / 2, mYMax / 2, mXMax / 2 + mAccelX * 10, mYMax / 2 - mAccelY * 10, paint);

		paint.setColor(Color.RED);
		if (mCommandedRollDirection == Direction.NONE) {
			canvas.drawCircle(mXMax / 2, mYMax / 2, 5, paint);
		}
		else {
			int x = 0;
			int y = 0;
			switch (mCommandedRollDirection) {
			case LEFT: { x = -1; y =  0; break;}
			case RIGHT:{ x =  1; y =  0; break;}
			case UP:   { x =  0; y = -1; break;}
			case DOWN: { x =  0; y =  1; break;}
			}
			canvas.drawLine(mXMax / 2, mYMax / 2, mXMax / 2 + x * 20, mYMax / 2 + y * 20, paint);
		}
	}
}
