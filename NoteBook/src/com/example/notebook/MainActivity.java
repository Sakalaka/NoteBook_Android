package com.example.notebook;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements AnimationListener,OnClickListener {
	ImageView jumpguy;
	ImageView notelogo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		jumpguy=(ImageView) findViewById(R.id.jumpguy);
		notelogo=(ImageView)findViewById(R.id.notelogo);
		doJumpAnimation();
		doShowLogo();
		jumpguy.setOnClickListener(this);
		
	}

	private void doShowLogo() {
		// TODO Auto-generated method stub
		AlphaAnimation showlogo=new AlphaAnimation(0, 1);
		showlogo.setDuration(5000);
		showlogo.setFillAfter(true);
		notelogo.startAnimation(showlogo);
		showlogo.setAnimationListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public void doJumpAnimation() {
		jumpguy.setBackgroundResource(R.drawable.anlist);
		AnimationDrawable an=(AnimationDrawable) jumpguy.getBackground();
		an.start();
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		skipTo();
	}

	private void skipTo() {
		// TODO Auto-generated method stub
		Intent intent=new Intent(MainActivity.this,LoginActivity.class);
		startActivity(intent);
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		skipTo();
	}
}
