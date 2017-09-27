package Dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;
import bean.Note;
import bean.User;

public class DaoDemo {
	static String USER_TABLE="Users";
	static String NOTE_TABLE="Notes";
	public User queryUser(DBBuildAndConnection dbhelper,String username) {
		Log.e("queryUser分割线-------","===============");
		SQLiteDatabase db =dbhelper.getReadableDatabase();
		Cursor cursor;
		String sql="select * from Users where username=?";
		String[] username1= {username};
		cursor=db.rawQuery(sql,username1);
		cursor.moveToFirst();
		User user=new User();
		Log.e("attendtion:::", cursor.getColumnCount()+"");
		Log.e("getResult:::", cursor.getColumnName(0));
		try {
		Log.e("getResult 名字:::", cursor.getString(0));
		Log.e("getResult psw:::", cursor.getString(1));
		String userNameGet=cursor.getString(0);
		String passWordGet=cursor.getString(1);
		user.setUsername(userNameGet);
		user.setPassword(passWordGet);
		}catch(Exception e) {
			Log.e("warn:::","数据没有");
			user.setUsername("");
			user.setPassword("");
		}
		db.close();
//		queryAll(dbhelper);
		Log.e("查询所有笔记test：：","========");
		queryAllNote(dbhelper);
		return user;
	}
	
//	添加用户
	public int addUser(DBBuildAndConnection dbHelper,User user) {
		SQLiteDatabase db =dbHelper.getReadableDatabase();
		String sql="insert into Users values(?,?)";
		String[] va= {user.getUsername(),user.getPassword()};
		db.execSQL(sql,va);
		
		User usertest=queryUser(dbHelper,user.username);
		Log.e("添加：：", user.password+"成功");
		db.close();
		return 0;
	}
//	查询所有用户
	public int queryAll(DBBuildAndConnection dbHelper) {
		Log.e("queryAll分割线-------","===============");
		SQLiteDatabase db =dbHelper.getReadableDatabase();
		String sql="select * from Users";
//		String sql2="delete from Users where username=?";
		Cursor cursor;
		String[] va= {};
		String[] va1= {"woshiwangwu"};
//		db.execSQL(sql2,va1);
		cursor=db.rawQuery(sql,va);
		while(cursor.moveToNext()) {
			Log.e("这里有用户名值：：",cursor.getString(0));
			Log.e("这里有用户密码值：：",cursor.getString(1));
		}
		db.close();
		return 0;
	}
	//根据用户名查询所有笔记
	public List<String> queryAllNoteName(DBBuildAndConnection dbhelper,String username){
		SQLiteDatabase db =dbhelper.getReadableDatabase();
		String sql="select title from Notes where username=?";
		String[] va= {username};
		Cursor cursor=db.rawQuery(sql, va);
		List<String> notename_list=new ArrayList<String>();
		cursor.moveToFirst();
		Log.e("queryAllNoteName分割线-------","===============");
		try {
		while(cursor.moveToNext()) {
			notename_list.add(cursor.getString(0));
//			Log.e("存在笔记",cursor.getString(0));
		}
		}catch(Exception e){
			Log.e("DD-QANN查询","数据库中无数据");
		}
		db.close();
		return notename_list;
		
	}
	//添加笔记
	public void addNote(DBBuildAndConnection dbHelper,Note note) {
		Log.e("addNote分割线-------","===============");
		SQLiteDatabase db =dbHelper.getReadableDatabase();
		String sql="insert into Notes values(?,?,?,?)";
		String[] va= {note.getNotename(),note.getTitle(),note.getUsername(),note.getNote_content()};
		Log.e("add::content::",note.getNote_content());
		Log.e("add::title::",note.getTitle());
		Log.e("add::Notename::",note.getNotename());
		Log.e("add::Username::",note.getUsername());
		db.execSQL(sql,va);
		this.queryAllNoteName(dbHelper,note.getUsername());
		db.close();
	}
	
	//根据笔记名(日期)查询笔记
	public Note queryNoteByNoteName(DBBuildAndConnection dbHelper,String noteName) {
		SQLiteDatabase db =dbHelper.getReadableDatabase();
		Log.e("DaoDemo::getNotename",noteName);
		String sql="select * from Notes where notename=?";
		String [] va= {noteName};
		Cursor cursor=db.rawQuery(sql, va);
		cursor.moveToFirst();
		Note note=new Note();
		Log.e("queryNoteByNoteName分割线-------","===============");
		try {
			note.setNotename(cursor.getString(0));
			note.setUsername(cursor.getString(1));
			note.setNote_content(cursor.getString(2));
		}catch(Exception e) {
			Log.e("queryNoteByNoteName查询笔记","数据库中无数据");
		}
		db.close();
		return note;
	}
	//根据笔记名删除笔记
	public void deleteNote(DBBuildAndConnection dbHelper,String noteName) {
		Log.e("deleteNote分割线-------","===============");
		SQLiteDatabase db =dbHelper.getReadableDatabase();
		String sql="delete from Notes where notename=? ";
		String[] va= {noteName};
		db.execSQL(sql,va);
		Log.e("删除note", noteName);
		Log.e("删除", "成功");
		db.close();
		this.queryAllNoteName(dbHelper,"zhangsan");
	}
	//根据title删除笔记
	public void deleteNoteByTitle(DBBuildAndConnection dbHelper,String title) {
		Log.e("deleteNoteByTitle分割线-------","===============");
		SQLiteDatabase db =dbHelper.getReadableDatabase();
		String sql="delete from Notes where title=? ";
		String[] va= {title};
		db.execSQL(sql,va);
		Log.e("删除note", title);
		Log.e("删除", "成功");
		db.close();
//		this.queryAllNoteName(dbhelper,"zhangsan");
	}	
	public List<String> queryAllNote(DBBuildAndConnection dbHelper){
		SQLiteDatabase db =dbHelper.getReadableDatabase();
		String sql="select * from Notes";
		String[] va= {};
		Cursor cursor=db.rawQuery(sql, va);
		List<String> noteNameList=new ArrayList<String>();
		cursor.moveToFirst();
		Log.e("queryAllNote分割线-------","===============");
		try {
		while(cursor.moveToNext()) {
			noteNameList.add(cursor.getString(1));
			Log.e("存在笔记",cursor.getString(0));
			Log.e("用户名",cursor.getString(2));
			Log.e("笔记内容",cursor.getString(3));
		}
		}catch(Exception e){
			Log.e("查询所有笔记","数据库中无数据");
		}
		db.close();
		return noteNameList;		
	}
	//按title查询Note
	public Note queryNoteByTitle(DBBuildAndConnection dbHelper,String title) {
		SQLiteDatabase db =dbHelper.getReadableDatabase();
		Log.e("DaoDemo::getTitle",title);
		String sql="select * from Notes where title=?";
		String [] va= {title};
		Cursor cursor=db.rawQuery(sql, va);
		cursor.moveToFirst();
		Note note=new Note();
		Log.e("queryNoteByTitle分割线-------","===============");
		try {
			note.setNotename(cursor.getString(0));
			note.setTitle(cursor.getString(1));
			note.setUsername(cursor.getString(2));
			note.setNote_content(cursor.getString(3));
		}catch(Exception e) {
			Log.e("queryNoteByNoteName查询笔记","数据库中无数据");
		}
		db.close();
		return note;
	}
	public void updateNote(DBBuildAndConnection dbHelper,String title,Note note) {
		SQLiteDatabase db =dbHelper.getReadableDatabase();
		ContentValues values=new ContentValues();
		values.put("notename", note.getNotename());
		values.put("title", note.getTitle());
		values.put("note_content",note.getNote_content());
//		String where="title="+title;
		db.update("Notes", values, "title=?",new String []{title});
		this.queryNoteByTitle(dbHelper,note.getTitle());
		db.close();
	}
}
