package com.example.pmll;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MemberDAO extends SQLiteOpenHelper{
	private static final String DB_NAME = "menber.db";
	private static final int DB_VERSION = 1;
	private static final String TABLE_NAME = "account_info";
	
	public MemberDAO(Context c) {
		super(c, DB_NAME, null, DB_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		//key_id integer primary key autoincrement
		//key_id被设定为主键列，db.insert后key_id会被自动赋值，依次增加
		//和name，tel，sex等列不同
		String SQL_CREAT_TABLE = 
				"create table "  + TABLE_NAME
				+ " (key_id integer primary key autoincrement," + "name String," + "password String," + "tel String," + "e_mail String)";
	    db.execSQL(SQL_CREAT_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}


