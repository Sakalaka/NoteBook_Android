package com.example.notebook;

import java.util.Calendar;
import java.util.TimeZone;

import Dao.DBBuildAndConnection;
import Dao.DaoDemo;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import bean.Note;
/*此类用于写笔记并存入数据库，key为用户名和当前时间*/
public class WriteNoteActivity extends Activity implements OnClickListener{
	EditText content;
	Button save;
	EditText title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.write_note);
		content=(EditText) findViewById(R.id.write_note);
		Bundle bundleW=this.getIntent().getExtras();
		String username=bundleW.getString("user_name").toString();
		save=(Button)findViewById(R.id.save1);
		save.setOnClickListener(this);
//		et3.setText(this.getDateAndTime());
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
	
	public void addNote() {
		DBBuildAndConnection helper=new DBBuildAndConnection(WriteNoteActivity.this,"NoteBook.db", null, 1);
		DaoDemo dd=new DaoDemo();
		Note noteadd=new Note();
		content=(EditText) findViewById(R.id.write_note);
		title=(EditText) findViewById(R.id.title);
		Bundle bundleW=this.getIntent().getExtras();
		noteadd.setNotename(this.getDateAndTime());
		noteadd.setTitle(title.getText().toString());
		noteadd.setUsername(bundleW.getString("user_name").toString());
		noteadd.setNote_content(content.getText().toString());
		if(noteadd.getTitle().equals("")) {
			Toast.makeText(WriteNoteActivity.this, "标题不能为空！", Toast.LENGTH_SHORT).show();
		}else
			dd.addNote(helper, noteadd);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.save1:
			addNote();
			Toast.makeText(WriteNoteActivity.this, "保存成功",Toast.LENGTH_SHORT).show();
			WriteNoteActivity.this.finish();
			break;
		}
	}

}
