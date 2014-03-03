package com.project.watchwithme;

import com.jovita.whatson.R;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class Chat extends BaseActivity {

/**
 * @author JOVITA P J
 *
 *
 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		
		getSupportActionBar().setTitle(
				Html.fromHtml("<font color='#058ec7'>Shout Box</font>"));
		TextView title=(TextView)findViewById(R.id.title);
		title.setText("Shout Box");
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
