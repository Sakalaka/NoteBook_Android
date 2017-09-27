package Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBBuildAndConnection extends SQLiteOpenHelper{
//	数据库名
static String DATABASE_NAME="NoteBook.db";
static String USER_TABLE="Users";
static String NOTE_TABLE="Notes";
static String USER_NAME="username";
static String PASSWORD="password";
//在此以时间作为笔记名
static String NOTE_NAME="notename";
static String NOTE_CONTENT="note_content";
static String TITLE="title";
static int mode=Context.MODE_PRIVATE;
static int version=1;
public DBBuildAndConnection(Context context, String name, CursorFactory factory, int version) {
	super(context,DATABASE_NAME, null, version);
	// TODO Auto-generated constructor stub
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	@Override
	//建立数据库
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_USERSTABLE="CREATE TABLE "+USER_TABLE+" ("+USER_NAME+" char(20),"
				+PASSWORD+" char(20))";
		String CREATE_NOTETABLE="CREATE TABLE "+NOTE_TABLE+" ("+NOTE_NAME+" char(100),"+TITLE+" char(100),"
				+USER_NAME+" char(100), "+NOTE_CONTENT+" char(8000))";
		db.execSQL(CREATE_USERSTABLE);
		db.execSQL(CREATE_NOTETABLE);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
}
