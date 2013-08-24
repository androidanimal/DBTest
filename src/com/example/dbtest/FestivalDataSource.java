package com.example.dbtest;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class FestivalDataSource {

	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String [] allColumns = {MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_FESTIVAL_NAME};
	
	public FestivalDataSource (Context context){
		dbHelper = new MySQLiteHelper(context);
	}
	
	public void open() throws SQLException{
		database = dbHelper.getWritableDatabase();
	}
	
	public void close(){
		dbHelper.close();
	}
	
	public Festival createFestival (String name){
		ContentValues values =  new  ContentValues();
		values.put(MySQLiteHelper.COLUMN_FESTIVAL_NAME, name);
		long insertId = database.insertOrThrow(MySQLiteHelper.TABLE_FESTIVALS, null, values);
		Cursor cursor = database.query(MySQLiteHelper.TABLE_FESTIVALS, allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId,
				null, null, null, null);
		cursor.moveToFirst();
		Festival newFestival = cursorToFestival(cursor);
		cursor.close();
		return newFestival;
	}
	
	
	public void deleteFestival(Festival festival){
		long id = festival.getID();
		System.out.println("Festival : " + festival.getName() + " deleted");
		database.delete(MySQLiteHelper.TABLE_FESTIVALS, MySQLiteHelper.COLUMN_ID + " = " + id, null);
	}
		
		public List <Festival> getAllFestivals(){
			List<Festival> comments = new ArrayList<Festival>();
			
			Cursor cursor = database.query(MySQLiteHelper.TABLE_FESTIVALS, allColumns, null, null, null, null, null);
			cursor.moveToFirst();
			
			while(!cursor.isAfterLast()){
				Festival festival = cursorToFestival(cursor);
				comments.add(festival);
				cursor.moveToNext();				
			}
			cursor.close();
			return comments;
	}
		
		private Festival cursorToFestival(Cursor cursor){
			Festival festival =  new Festival();
			festival.setId(cursor.getLong(0));
			festival.setName(cursor.getString(1));
			return festival;
		}
}
