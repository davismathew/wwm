package com.project.watchwithme;

import com.jovita.whatson.R;

import android.os.Bundle;

public class MyFriends extends BaseActivity {

/**
 * @author JOVITA P J
 *
 *
 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_friends);
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}
