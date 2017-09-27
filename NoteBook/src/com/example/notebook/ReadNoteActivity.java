package com.example.notebook;

import java.util.Calendar;
import java.util.TimeZone;

import Dao.DBBuildAndConnection;
import Dao.DaoDemo;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import bean.Note;

public class ReadNoteActivity extends Activity implements OnClickListener {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	EditText note_content;
	TextView title1;
	Button delete2;
	Button update1;
	TextView date1;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.read_note);
		note_content=(EditText) findViewById(R.id.notecontent2);
		update1=(Button) findViewById(R.id.update1);
		delete2=(Button) findViewById(R.id.delete2);
		title1=(TextView) findViewById(R.id.title1);
		update1.setOnClickListener(this);
		delete2.setOnClickListener(this);
		this.show();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Bundle bundleW=this.getIntent().getExtras();
		String titleb=bundleW.getString("titleb").toString();
		String username=bundleW.getString("username2").toString();
		title1=(TextView) findViewById(R.id.title1);
		Log.e("Read username:",username);
		switch(v.getId()) {
		case R.id.update1:
			upDate(getDateAndTime(),username,title1.getText().toString());
			Toast.makeText(ReadNoteActivity.this, "修改成功",Toast.LENGTH_SHORT).show();
			ReadNoteActivity.this.finish();
			break;
		case R.id.delete2:
			deleteNote(titleb);
			Toast.makeText(ReadNoteActivity.this, "删除成功",Toast.LENGTH_SHORT).show();
			ReadNoteActivity.this.finish();
			break;
		}
	}
	private void upDate(String notename,String username,String title) {
		// TODO Auto-generated method stub
		Bundle bundleW=this.getIntent().getExtras();
		DBBuildAndConnection helper=new DBBuildAndConnection(ReadNoteActivity.this,"NoteBook.db", null, 1);
		DaoDemo dd=new DaoDemo();
		String titleb=bundleW.getString("titleb").toString();
		note_content=(EditText) findViewById(R.id.notecontent2);
		String content=note_content.getText().toString();
		Log.e(" ReadNoteActivity get content::",content);
		Log.e(" ReadNoteActivity get updatename::",notename);
		Note note=new Note(notename,username,title,content);
//		dd.addNote(helper,note);
		dd.updateNote(helper, titleb, note);

	}
	private void deleteNote(String title) {
		// TODO Auto-generated method stub
		DBBuildAndConnection helper=new DBBuildAndConnection(ReadNoteActivity.this,"NoteBook.db", null, 1);
		DaoDemo dd=new DaoDemo();
		dd.deleteNoteByTitle(helper, title);
	}
	public String getDateAndTime() {
		Calendar cal=Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		String year=String.valueOf(cal.get(Calendar.YEAR));
		String month=String.valueOf(cal.get(Calendar.MONTH)+1);
		String day=String.valueOf(cal.get(Calendar.DATE));
		String hour;
		if(cal.get(Calendar.AM_PM)==0) {
			hour=String.valueOf(cal.get(Calendar.HOUR));
		}
		else
			hour=String.valueOf(cal.get(Calendar.HOUR)+12);
		String minute=String.valueOf(cal.get(Calendar.MINUTE));
		String second=String.valueOf(cal.get(Calendar.SECOND));
		String str=year+"-"+month+"-"+day+"   "+hour+":"+minute+":"+second;
		return str;
		
	}
	public void show() {
		DBBuildAndConnection helper=new DBBuildAndConnection(ReadNoteActivity.this,"NoteBook.db", null, 1);
		DaoDemo dd=new DaoDemo();
		Bundle bundleW=this.getIntent().getExtras();
		String titleb=bundleW.getString("titleb").toString();
		Log.e("ReadNoteActivity titleb::",titleb);
		Note note;
		note=dd.queryNoteByTitle(helper,titleb);
		note_content=(EditText) findViewById(R.id.notecontent2);
		title1=(TextView) findViewById(R.id.title1);
		date1=(TextView) findViewById(R.id.date2);
		Log.e("ReadNoteActivity content::",note.getNote_content());
		note_content.setText(note.getNote_content());
		title1.setText(note.getTitle());
		date1.setText(note.getNotename());
	}
}
