package com.lecz.android.tiltmazes;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class TiltMazesDBAdapter {
	private static final String DATABASE_NAME = "tiltmazes.db";
	private static final String DATABASE_TABLE = "mazes";
	private static final int DATABASE_VERSION = 1;
	
	public static final String KEY_ID = "id";
	
	public static final String KEY_NAME = "name";
	public static final int NAME_COLUMN = 1;
	
	public static final String KEY_SOLUTION_STEPS = "solution_steps";
	public static final int SOLUTION_STEPS_COLUMN = 2;
	
	private SQLiteDatabase mDB;
	private TiltMazesDBOpenHelper mDBHelper;
	
	public TiltMazesDBAdapter(Context context) {
		mDBHelper = new TiltMazesDBOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public TiltMazesDBAdapter open() throws SQLException {
		mDB = mDBHelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		mDB.close();
	}
	
	public void updateMaze(String name, int solution_steps) {
		ContentValues values = new ContentValues();
		values.put(KEY_SOLUTION_STEPS, solution_steps);

		mDB.update(DATABASE_TABLE, values, KEY_NAME + " = ?", new String[]{name});
	}
	
	private static class TiltMazesDBOpenHelper extends SQLiteOpenHelper {
		private static final String DATABASE_CREATE =
			"create table " + DATABASE_TABLE + " ("
			+ KEY_ID + " integer primary key autoincrement, "
			+ KEY_NAME + " text not null, "
			+ KEY_SOLUTION_STEPS + " integer"
			+ ");";
			
		
		public TiltMazesDBOpenHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);
			
			db.beginTransaction();
			try {
				ContentValues values = new ContentValues();
				values.put(KEY_SOLUTION_STEPS, 0);

				for (MapDesign map : MapDesigns.designList) {
					values.put(KEY_NAME, map.getName());
					db.insert(DATABASE_TABLE, null, values);
				}
				
				db.setTransactionSuccessful();
			}
			finally {
				db.endTransaction();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// FIXME(leczbalazs) implement data migration instead of the DROP+CREATE
			db.execSQL("drop table if exists " + DATABASE_TABLE);
			onCreate(db);
		}
		
	}
}
