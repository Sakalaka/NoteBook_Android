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
		Log.e("queryUser�ָ���-------","===============");
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
		Log.e("getResult ����:::", cursor.getString(0));
		Log.e("getResult psw:::", cursor.getString(1));
		String usernameget=cursor.getString(0);
		String passwordget=cursor.getString(1);
		user.setUsername(usernameget);
		user.setPassword(passwordget);
		}catch(Exception e) {
			Log.e("warn:::","����û��");
			user.setUsername("");
			user.setPassword("");
		}
		db.close();
//		queryAll(dbhelper);
		Log.e("��ѯ���бʼ�test����","========");
		queryAllNote(dbhelper);
		return user;
	}
	
//	����û�
	public int addUser(DBBuildAndConnection dbhelper,User user) {
		SQLiteDatabase db =dbhelper.getReadableDatabase();
		String sql="insert into Users values(?,?)";
		String[] va= {user.getUsername(),user.getPassword()};
		db.execSQL(sql,va);
		
		User usertest=queryUser(dbhelper,user.username);
		Log.e("��ӣ���", user.password+"�ɹ�");
		db.close();
		return 0;
	}
//	��ѯ�����û�
	public int queryAll(DBBuildAndConnection dbhelper) {
		Log.e("queryAll�ָ���-------","===============");
		SQLiteDatabase db =dbhelper.getReadableDatabase();
		String sql="select * from Users";
//		String sql2="delete from Users where username=?";
		Cursor cursor;
		String[] va= {};
		String[] va1= {"woshiwangwu"};
//		db.execSQL(sql2,va1);
		cursor=db.rawQuery(sql,va);
		while(cursor.moveToNext()) {
			Log.e("�������û���ֵ����",cursor.getString(0));
			Log.e("�������û�����ֵ����",cursor.getString(1));
		}
		db.close();
		return 0;
	}
	//�����û�����ѯ���бʼ�
	public List<String> queryAllNoteName(DBBuildAndConnection dbhelper,String username){
		SQLiteDatabase db =dbhelper.getReadableDatabase();
		String sql="select title from Notes where username=?";
		String[] va= {username};
		Cursor cursor=db.rawQuery(sql, va);
		List<String> notename_list=new ArrayList<String>();
		cursor.moveToFirst();
		Log.e("queryAllNoteName�ָ���-------","===============");
		try {
		while(cursor.moveToNext()) {
			notename_list.add(cursor.getString(0));
//			Log.e("���ڱʼ�",cursor.getString(0));
		}
		}catch(Exception e){
			Log.e("DD-QANN��ѯ","���ݿ���������");
		}
		db.close();
		return notename_list;
		
	}
	//��ӱʼ�
	public void addNote(DBBuildAndConnection dbhelper,Note note) {
		Log.e("addNote�ָ���-------","===============");
		SQLiteDatabase db =dbhelper.getReadableDatabase();
		String sql="insert into Notes values(?,?,?,?)";
		String[] va= {note.getNotename(),note.getTitle(),note.getUsername(),note.getNote_content()};
		Log.e("add::content::",note.getNote_content());
		Log.e("add::title::",note.getTitle());
		Log.e("add::Notename::",note.getNotename());
		Log.e("add::Username::",note.getUsername());
		db.execSQL(sql,va);
		this.queryAllNoteName(dbhelper,note.getUsername());
		db.close();
	}
	
	//���ݱʼ���(����)��ѯ�ʼ�
	public Note queryNoteByNoteName(DBBuildAndConnection dbhelper,String notename) {
		SQLiteDatabase db =dbhelper.getReadableDatabase();
		Log.e("DaoDemo::getNotename",notename);
		String sql="select * from Notes where notename=?";
		String [] va= {notename};
		Cursor cursor=db.rawQuery(sql, va);
		cursor.moveToFirst();
		Note note=new Note();
		Log.e("queryNoteByNoteName�ָ���-------","===============");
		try {
			note.setNotename(cursor.getString(0));
			note.setUsername(cursor.getString(1));
			note.setNote_content(cursor.getString(2));
		}catch(Exception e) {
			Log.e("queryNoteByNoteName��ѯ�ʼ�","���ݿ���������");
		}
		db.close();
		return note;
	}
	//���ݱʼ���ɾ���ʼ�
	public void deleteNote(DBBuildAndConnection dbhelper,String notename) {
		Log.e("deleteNote�ָ���-------","===============");
		SQLiteDatabase db =dbhelper.getReadableDatabase();
		String sql="delete from Notes where notename=? ";
		String[] va= {notename};
		db.execSQL(sql,va);
		Log.e("ɾ��note", notename);
		Log.e("ɾ��", "�ɹ�");
		db.close();
		this.queryAllNoteName(dbhelper,"zhangsan");
	}
	//����titleɾ���ʼ�
	public void deleteNoteByTitle(DBBuildAndConnection dbhelper,String title) {
		Log.e("deleteNoteByTitle�ָ���-------","===============");
		SQLiteDatabase db =dbhelper.getReadableDatabase();
		String sql="delete from Notes where title=? ";
		String[] va= {title};
		db.execSQL(sql,va);
		Log.e("ɾ��note", title);
		Log.e("ɾ��", "�ɹ�");
		db.close();
//		this.queryAllNoteName(dbhelper,"zhangsan");
	}	
	public List<String> queryAllNote(DBBuildAndConnection dbhelper){
		SQLiteDatabase db =dbhelper.getReadableDatabase();
		String sql="select * from Notes";
		String[] va= {};
		Cursor cursor=db.rawQuery(sql, va);
		List<String> notename_list=new ArrayList<String>();
		cursor.moveToFirst();
		Log.e("queryAllNote�ָ���-------","===============");
		try {
		while(cursor.moveToNext()) {
			notename_list.add(cursor.getString(1));
			Log.e("���ڱʼ�",cursor.getString(0));
			Log.e("�û���",cursor.getString(2));
			Log.e("�ʼ�����",cursor.getString(3));
		}
		}catch(Exception e){
			Log.e("��ѯ���бʼ�","���ݿ���������");
		}
		db.close();
		return notename_list;		
	}
	//��title��ѯNote
	public Note queryNoteByTitle(DBBuildAndConnection dbhelper,String title) {
		SQLiteDatabase db =dbhelper.getReadableDatabase();
		Log.e("DaoDemo::getTitle",title);
		String sql="select * from Notes where title=?";
		String [] va= {title};
		Cursor cursor=db.rawQuery(sql, va);
		cursor.moveToFirst();
		Note note=new Note();
		Log.e("queryNoteByTitle�ָ���-------","===============");
		try {
			note.setNotename(cursor.getString(0));
			note.setTitle(cursor.getString(1));
			note.setUsername(cursor.getString(2));
			note.setNote_content(cursor.getString(3));
		}catch(Exception e) {
			Log.e("queryNoteByNoteName��ѯ�ʼ�","���ݿ���������");
		}
		db.close();
		return note;
	}
	public void updateNote(DBBuildAndConnection dbhelper,String title,Note note) {
		SQLiteDatabase db =dbhelper.getReadableDatabase();
		ContentValues values=new ContentValues();
		values.put("notename", note.getNotename());
		values.put("title", note.getTitle());
		values.put("note_content",note.getNote_content());
//		String where="title="+title;
		db.update("Notes", values, "title=?",new String []{title});
		this.queryNoteByTitle(dbhelper,note.getTitle());
		db.close();
	}
}
