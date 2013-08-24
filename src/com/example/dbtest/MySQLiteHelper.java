package com.example.dbtest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
	
	public static final String TABLE_FESTIVALS = "festivals";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_FESTIVAL_NAME = "name";
	public static final String DATABASE_NAME = "potch_app.db";
	public static final int DATABASE_VERSION = 1;
	
	private static final String DATABASE_CREATE = "CREATE TABLE "
	+ TABLE_FESTIVALS + "(" + COLUMN_ID + " integer primary key,"
	+ COLUMN_FESTIVAL_NAME + " text not null );";
	
	public MySQLiteHelper (Context context){
		super (context,  DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public void onCreate(SQLiteDatabase database){
		database.execSQL(DATABASE_CREATE);
	}
	
	@Override
	public void onUpgrade (SQLiteDatabase db, int OldVersion, int NewVersion){
		Log.w(MySQLiteHelper.class.getName(), "Upgrading database from " + OldVersion + " to:"
				+ NewVersion);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FESTIVALS );
		
	}
			
} 
