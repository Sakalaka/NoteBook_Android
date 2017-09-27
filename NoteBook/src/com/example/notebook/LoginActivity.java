package com.example.notebook;

import Dao.DBBuildAndConnection;
import Dao.DaoDemo;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import bean.User;

public class LoginActivity extends Activity implements OnClickListener{
	/* (non-Javadoc)
	 * @see android.app.Activity#startActivityForResult(android.content.Intent, int, android.os.Bundle)
	 */
	@Override
	public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
		// TODO Auto-generated method stub
		super.startActivityForResult(intent, requestCode, options);
	}
	Button loginbtn,createbtn;
	EditText username,password;
	static String USER_TABLE="Users";
	SharedPreferences settings;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_page);
		loginbtn=(Button) findViewById(R.id.loginBtn);
		createbtn=(Button) findViewById(R.id.createBtn);
		username=(EditText)findViewById(R.id.username);
		password=(EditText)findViewById(R.id.psw);
		loginbtn.setOnClickListener(this);
		createbtn.setOnClickListener(this); 
		DBBuildAndConnection helper=new DBBuildAndConnection(LoginActivity.this,"NoteBook.db", null, 1);
	}

	public LoginActivity() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
//		Bundle bundle2;
//		bundle2=this.getIntent().getExtras();
//		bundle2.getString("user_name").toString();
		switch(v.getId()) {
		case R.id.loginBtn:
			int flag=checkUser();
			Log.e("点击事件","没问题");
			if(flag==1) {
				addShare();
				skipToNotePage();
				}
			if(flag==-1)
				Toast.makeText(LoginActivity.this, "密码错误！", Toast.LENGTH_SHORT).show();
			if(flag==0)
				Toast.makeText(LoginActivity.this, "无此用户！", Toast.LENGTH_SHORT).show();
			break;
		case R.id.createBtn:
			int flag2=checkUser();
			if(flag2==0) {
				createUser();
				addShare();
				skipToNotePage();
				}
			else
				Toast.makeText(LoginActivity.this, "用户名已存在", Toast.LENGTH_SHORT).show();
			break;
		}
	}
	private void createUser() {
		// TODO Auto-generated method stub
		DBBuildAndConnection helper=new DBBuildAndConnection(LoginActivity.this,"NoteBook.db", null, 1);
		DaoDemo dd=new DaoDemo();
		User userAdd=new User();
		userAdd.setUsername(username.getText().toString());
		userAdd.setPassword(password.getText().toString());
		dd.addUser(helper, userAdd);
	}

	private int checkUser() {
		// TODO Auto-generated method stub
		DBBuildAndConnection helper=new DBBuildAndConnection(LoginActivity.this,"NoteBook.db", null, 1);
		String username1=username.getText().toString();
		String password1=password.getText().toString();
		DaoDemo dd=new DaoDemo();
		User user=dd.queryUser(helper, username1);
		if(user.getUsername().equals(username1)) {
			if(user.getPassword().equals(password1))
				return 1;
			return -1;
		}
		return 0;
	}
	public void skipToNotePage() {
		Intent intent1=new Intent();
		intent1.setClass(LoginActivity.this,NoteListShow.class);
		Bundle bundle1=new Bundle();
		//将用户名传递给下一个Activity
		bundle1.putString("user_name",username.getText().toString());
		intent1.putExtras(bundle1);
		startActivity(intent1);
	}
	public void addShare() {
		settings=getSharedPreferences("userbook",Context.MODE_PRIVATE);
		SharedPreferences.Editor editor=settings.edit();
		editor.putString("username", username.getText().toString());
		editor.putString("password",password.getText().toString());
		editor.commit();
	}
	
}
