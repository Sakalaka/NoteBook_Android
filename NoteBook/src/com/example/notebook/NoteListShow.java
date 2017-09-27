/**
 * 
 */
package com.example.notebook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import Dao.DBBuildAndConnection;
import Dao.DaoDemo;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Melody
 *
 */
public class NoteListShow extends Activity implements OnScrollListener,OnItemClickListener,OnClickListener{
	/* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		this.onCreate(null);
	}
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	//数据表名
	static String NOTE_TABLE="Notes";
	ListView listview1;
	Button addBtn;
	TextView username1;
	//声明适配器
//	int number=1;
	List<String> list;
	ArrayAdapter<String> ada; 
	static Bundle bundle2;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.note_list);
		list=new ArrayList<String>();
		list.addAll(getData());
		ada=new ArrayAdapter<String>(this,R.layout.list_item,list);
		username1=(TextView) findViewById(R.id.username1);
		addBtn=(Button) findViewById(R.id.addBtn);
		addBtn.setOnClickListener(this);
		//将从上一个Activity传来的username显示在页面上
		bundle2=this.getIntent().getExtras();
		username1.setText(bundle2.getString("user_name").toString());
		listview1=(ListView) findViewById(R.id.listView1);
		listview1.setAdapter(ada);
		//设置滑动监听器
		listview1.setOnScrollListener(this);
		listview1.setOnItemClickListener(this);
		
		
	}
	private List<String> getData() {
		// TODO Auto-generated method stub
//		从数据库获取用户名全为***的笔记列表名
		DBBuildAndConnection helper=new DBBuildAndConnection(NoteListShow.this,"NoteBook.db", null, 1);
		List<String> sublist=new ArrayList<String>();
		DaoDemo dd=new DaoDemo();
		bundle2=this.getIntent().getExtras();
		sublist.addAll(dd.queryAllNoteName(helper, bundle2.getString("user_name").toString()));
		Log.e("NLS-username", bundle2.getString("user_name").toString());
		return sublist;
	}
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		//如果滚动条滑到底部
		if (scrollState==SCROLL_STATE_IDLE) {
			if(view.getLastVisiblePosition()==list.size()-1) {
				Toast.makeText(NoteListShow.this, "Loading", Toast.LENGTH_LONG).show();
				list.addAll(getData());
				ada.notifyDataSetChanged();
			}
		}
	}
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		bundle2=this.getIntent().getExtras();
		TextView tv=(TextView) findViewById((int) id);
		Log.e("now position::",position+"");
		String content=list.get(position);
		Log.e("this position content:：", content);
		skipToReadPage(content,bundle2.getString("user_name").toString());
		}
	public void skipToNotePage() {
		Intent intent2=new Intent();
		intent2.setClass(NoteListShow.this,WriteNoteActivity.class);
		Bundle bundle3=new Bundle();
		//将用户名传递给下一个Activity
		bundle3.putString("user_name",bundle2.getString("user_name").toString());
		intent2.putExtras(bundle3);
		startActivity(intent2);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		skipToNotePage();
	}
  public void skipToReadPage(String title,String username) {
	  Intent intent2=new Intent();
		intent2.setClass(NoteListShow.this,ReadNoteActivity.class);
		Bundle bundle3=new Bundle();
		//将用户名传递给下一个Activity
		bundle3.putString("titleb",title);
		bundle3.putString("username2",username);
		intent2.putExtras(bundle3);
		startActivity(intent2);
  }
}
