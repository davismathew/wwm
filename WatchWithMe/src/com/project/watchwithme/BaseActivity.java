package com.project.watchwithme;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Documented;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import com.facebook.Session;
import com.facebook.Session.OpenRequest;
import com.facebook.SessionLoginBehavior;
import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;
import com.facebook.android.AsyncFacebookRunner.RequestListener;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.Util;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jovita.whatson.R;
import com.project.watchwithme.domain.PublicValues;

public class BaseActivity extends SherlockActivity implements OnClickListener {

/**
 * @author JOVITA P J
 *
 *
 */

	private AsyncFacebookRunner mAsyncRunner;
	 Drawable picture;
	private SlidingMenu slidingMenuLeft;
	private SlidingMenu slidingMenuRight;
	ArrayList<TextView> textviews;
	ArrayList<ImageView> iconimages;
	BaseActivity thisactivity;
	int pos;
	ArrayList<String> dummyfriends = new ArrayList<String>();
	int sideiconsnormal[] ={R.drawable.icon_home_normal,R.drawable.icon_featured_normal,R.drawable.icon_fav_normal,R.drawable.icon_tv_normal,R.drawable.icon_chat_normal,
			R.drawable.icon_notification_normal,R.drawable.icon_profile_normal};
	int sideiconspressed[] ={R.drawable.icon_home_pressed,R.drawable.icon_featured_pressed,R.drawable.icon_fav_pressed,R.drawable.icon_tv_pressed,R.drawable.icon_chat_pressed,
			R.drawable.icon_notification_pressed,R.drawable.icon_profile_pressed};
	ImageView img_whatson,img_featured,img_mostpopular,img_tvguide,img_notification,img_shouts,img_myprofile;
	private LinearLayout leftMenuItems;
	public static String colorchangeFlag="";
	TextView txtwhatson,txtshouts,txtmyprofile,txtnotification,txttvguide,txtmostPopular,txtfeatured;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
thisactivity=this;
		final ActionBar actionBar = getSupportActionBar();
		
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setCustomView(R.layout.general_top_header);
		actionBar.setHomeButtonEnabled(true);
		Drawable d=getResources().getDrawable(R.drawable.navbarnew);
		actionBar.setBackgroundDrawable(d);
		
		
		actionBar.setIcon(R.drawable.icon_menu);
		mAsyncRunner = new AsyncFacebookRunner(facebook);
		
		/*getSupportActionBar().setTitle(
				Html.fromHtml("<font color='#058ec7'>What's On</font>"));
		
		
		//actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.icon_menu);
		Drawable navbar=getResources().getDrawable(R.drawable.navbar);
		actionBar.setBackgroundDrawable(navbar);*/
		
		
		slidingMenuLeft = new SlidingMenu(this);
		slidingMenuLeft.setMode(SlidingMenu.LEFT);
		// slidingMenuLeft.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		slidingMenuLeft.setShadowWidthRes(R.dimen.slidingmenu_shadow_width);
		slidingMenuLeft.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		slidingMenuLeft.setFadeDegree(0.35f);
		slidingMenuLeft.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
		slidingMenuLeft.setMenu(R.layout.left_slide_menu);
		
		LinearLayout whatson=(LinearLayout)slidingMenuLeft.findViewById(R.id.whatson);
		whatson.setOnClickListener(this);
		
		LinearLayout featured=(LinearLayout)slidingMenuLeft.findViewById(R.id.Featured_Show);
		featured.setOnClickListener(this);
		
		LinearLayout popular=(LinearLayout)slidingMenuLeft.findViewById(R.id.most_popular_show);
		popular.setOnClickListener(this);
		
	
		
		LinearLayout tv_guide=(LinearLayout)slidingMenuLeft.findViewById(R.id.tv_guide);
		tv_guide.setOnClickListener(this);
		
		
		
		LinearLayout notification=(LinearLayout)slidingMenuLeft.findViewById(R.id.notification_center);
		notification.setOnClickListener(this);
		
		LinearLayout my_profile=(LinearLayout)slidingMenuLeft.findViewById(R.id.my_profiles);
		my_profile.setOnClickListener(this);
		
		LinearLayout Chat=(LinearLayout)slidingMenuLeft.findViewById(R.id.chat);
		Chat.setOnClickListener(this);
		
		RelativeLayout fb=(RelativeLayout)slidingMenuLeft.findViewById(R.id.fb_login);
		fb.setOnClickListener(this);
		
		textviews=new ArrayList<TextView>();
		 txtwhatson=(TextView)slidingMenuLeft.findViewById(R.id.txt_sidemenu_whatson);
		 txtfeatured=(TextView)slidingMenuLeft.findViewById(R.id.txt_sidemenu_featuredProgram);
		 txtmostPopular=(TextView)slidingMenuLeft.findViewById(R.id.txt_sidemenu_mostPopular);
		 txttvguide=(TextView)slidingMenuLeft.findViewById(R.id.txt_sidemenu_tvGuide);
		 txtnotification=(TextView)slidingMenuLeft.findViewById(R.id.txt_sidemenu_notificationCenter);
		 txtmyprofile=(TextView)slidingMenuLeft.findViewById(R.id.txt_sidemenu_MyProfile);
		 txtshouts=(TextView)slidingMenuLeft.findViewById(R.id.txt_sidemenu_shouts);
		
		textviews.add(txtwhatson);
		textviews.add(txtfeatured);
		textviews.add(txtmostPopular);
		textviews.add(txttvguide);
		textviews.add(txtshouts);
		textviews.add(txtnotification);
		textviews.add(txtmyprofile);
		
		iconimages=new ArrayList<ImageView>();
		img_featured=(ImageView)slidingMenuLeft.findViewById(R.id.imv_sideMenuIcon_featuresProgram);
		img_mostpopular=(ImageView)slidingMenuLeft.findViewById(R.id.imv_sideMenuIcon_mostPopularProgram);
		img_myprofile=(ImageView)slidingMenuLeft.findViewById(R.id.imv_sideMenuIcon_myProfile);
		img_notification=(ImageView)slidingMenuLeft.findViewById(R.id.imv_sideMenuIcon_notificationCenter);
		img_shouts=(ImageView)slidingMenuLeft.findViewById(R.id.imv_sideMenuIcon_shouts);
		img_tvguide=(ImageView)slidingMenuLeft.findViewById(R.id.imv_sideMenuIcon_tvGuide);
		img_whatson=(ImageView)slidingMenuLeft.findViewById(R.id.imv_sideMenuIcon_whatson);
		iconimages.add(img_whatson);
		iconimages.add(img_featured);
		iconimages.add(img_mostpopular);
		iconimages.add(img_tvguide);
		iconimages.add(img_shouts);
		iconimages.add(img_notification);
		iconimages.add(img_myprofile);
		
		dummyfriends.add("ninshid");
		dummyfriends.add("swathi");
		dummyfriends.add("jasmine");
		dummyfriends.add("jovita");
		dummyfriends.add("nikil");
		
		
		
	}
	
	
	public void changecolor()
	{
		for(TextView i:textviews)
		{
			i.setTextColor(Color.parseColor("#4d4b5c"));
		}
		int k=0;
		for(ImageView j:iconimages)
		{
			
			//j.setBackgroundResource(sideiconsnormal[k]);
			j.setImageResource(sideiconsnormal[k]);
			k++;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.actionbar_item_list, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
		
		switch (item.getItemId()) {
		case android.R.id.home:
			
			resumetext();
			slidingMenuLeft.toggle();
			break;
		case R.id.action_settings:
			slidingMenuRight.toggle();
			break;
		case R.id.icon_search:
			resumetext();
			slidingMenuLeft.toggle();
			break;
	
/*		case R.id.menu_addExpense:
			intent = new Intent(BaseActivity.this, ExpenseActivity.class);
			startActivity(intent);
			Toast.makeText(this, "Menu Item 1 selected", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.menu_addIncome:
			intent = new Intent(BaseActivity.this, IncomeActivity.class);
			startActivity(intent);
			break;
		default:
			return super.onOptionsItemSelected(item);*/
		}

		return true;
	}

	@Override
	public void onBackPressed() {
		
		if (slidingMenuLeft.isMenuShowing()) {
			slidingMenuLeft.toggle();
		} else {
			
			super.onBackPressed();
			
		}
	}
	
	public void resumetext()
	{		
		pos=0;
		boolean flag=false;
		for(TextView i:textviews)
		{
			
			if(colorchangeFlag!=null)
			{
			if(i.getText().toString().equalsIgnoreCase(colorchangeFlag))
			{
			flag=true;
			i.setTextColor(Color.parseColor("#08c6f7"));
			break;
			}
			}
			pos++;
		}
		if(flag==true)
		{
		//iconimages.get(pos).setBackgroundResource(sideiconspressed[pos]);
			iconimages.get(pos).setImageResource(sideiconspressed[pos]);
		}
		/*for(int i=0;i<iconimages.size();i++)
		{
			if(colorchangeFlag!=null)
			{
				if(textviews.get(i).toString().equalsIgnoreCase(colorchangeFlag))
				{
					textviews.get(i).setTextColor(Color.parseColor("#08c6f7"));
					iconimages.get(i).setBackgroundResource(sideicons[i]);
				}
			}
		}*/
		
	}

	@Override
	public void onClick(View view) {
		int id = view.getId();
		Intent intent;
		changecolor();
		switch (id) {
		case R.id.whatson:
			img_whatson.setImageResource(R.drawable.icon_home_pressed);
			txtwhatson.setTextColor(Color.parseColor("#08c6f7"));
			colorchangeFlag=txtwhatson.getText().toString();
			intent=new Intent(this,WhatsonMainTest.class);
			startActivity(intent);
			
			break;
			
		case R.id.Featured_Show:
			img_featured.setImageResource(R.drawable.icon_featured_pressed);
			txtfeatured.setTextColor(Color.parseColor("#08c6f7"));
			colorchangeFlag=txtfeatured.getText().toString();
			intent=new Intent(this,Featured.class);
			startActivity(intent);
			break;
case R.id.most_popular_show:
	img_mostpopular.setImageResource(R.drawable.icon_fav_pressed);
	txtmostPopular.setTextColor(Color.parseColor("#08c6f7"));
	colorchangeFlag=txtmostPopular.getText().toString();
	
	intent=new Intent(this,MostPopularShow.class);
	startActivity(intent);
			break;

case R.id.tv_guide:
	img_tvguide.setImageResource(R.drawable.icon_tv_pressed);
	txttvguide.setTextColor(Color.parseColor("#08c6f7"));
	colorchangeFlag=txttvguide.getText().toString();
	intent=new Intent(this,TVGuide.class);
	startActivity(intent);
	break;

case R.id.notification_center:
	img_notification.setImageResource(R.drawable.icon_notification_pressed);
	txtnotification.setTextColor(Color.parseColor("#08c6f7"));
	colorchangeFlag=txtnotification.getText().toString();
	intent=new Intent(this,NotificationCenter.class);
	startActivity(intent);
	break;
case R.id.my_profiles:
	img_myprofile.setImageResource(R.drawable.icon_profile_pressed);
	txtmyprofile.setTextColor(Color.parseColor("#08c6f7"));
	colorchangeFlag=txtmyprofile.getText().toString();
	intent=new Intent(this,MyProfiles.class);
	startActivity(intent);
	break;
case R.id.chat:
	img_shouts.setImageResource(R.drawable.icon_chat_pressed);
	txtshouts.setTextColor(Color.parseColor("#08c6f7"));
	colorchangeFlag=txtshouts.getText().toString();
	intent=new Intent(this,Shout.class);
	startActivity(intent);
	break;
case R.id.fb_login:
	Log.i("log in clicked", "going to loging method");
	
	Bitmap dummy=BitmapFactory.decodeResource(getResources(),R.drawable.images);
	 File sdCardDirectory = Environment.getExternalStorageDirectory();
	  File image = new File(sdCardDirectory, "test.png");
	  FileOutputStream outStream;
	    try {

	        outStream = new FileOutputStream(image);
	        dummy.compress(Bitmap.CompressFormat.PNG, 100, outStream); 
	        /* 100 to keep full quality of the image */

	        outStream.flush();
	        outStream.close();
	      
	    } catch (FileNotFoundException e1) {
	        e1.printStackTrace();
	    } catch (IOException e1) {
	        e1.printStackTrace();
	    }
		PublicValues publicval=new PublicValues();
	    publicval.friends_list=dummyfriends;
	    
	    Toast.makeText(getApplicationContext(), "Logged In", Toast.LENGTH_SHORT).show();
	/*try {
		
		loginToFacebook();
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		Toast.makeText(getApplicationContext(), "Logged In", Toast.LENGTH_SHORT).show();
		Bitmap dummy=BitmapFactory.decodeResource(getResources(),R.drawable.images);
		 File sdCardDirectory = Environment.getExternalStorageDirectory();
		  File image = new File(sdCardDirectory, "test.png");
		  FileOutputStream outStream;
		    try {

		        outStream = new FileOutputStream(image);
		        dummy.compress(Bitmap.CompressFormat.PNG, 100, outStream); 
		         100 to keep full quality of the image 

		        outStream.flush();
		        outStream.close();
		      
		    } catch (FileNotFoundException e1) {
		        e1.printStackTrace();
		    } catch (IOException e1) {
		        e1.printStackTrace();
		    }
			PublicValues publicval=new PublicValues();
		    publicval.friends_list=dummyfriends;
		    
		
		e.printStackTrace();
	}*/
	break;

		default:
			break;
		}

			
		}
	
	private SharedPreferences mPrefs;
	//private static String APP_ID = "269767983173044";
	private static String APP_ID = "1409413262632879";
	private Facebook facebook = new Facebook(APP_ID);
	 Drawable d;
	
	@SuppressWarnings("deprecation")
	public void loginToFacebook() throws IOException {

		
		mPrefs = getPreferences(MODE_PRIVATE);
		String access_token = mPrefs.getString("access_token", null);
		long expires = mPrefs.getLong("access_expires", 0);

		
		if (access_token != null) {
			Log.i("token", "not null");
			facebook.setAccessToken(access_token);
			
			Log.d("FB Sessions", "" + facebook.isSessionValid());
			mAsyncRunner.request("me", new RequestListener() {
				
			public void onComplete(String response, Object state) { 
				
				getProfileInformation();
				}

			@Override
			public void onIOException(IOException e, Object state) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onFileNotFoundException(FileNotFoundException e,
					Object state) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onMalformedURLException(MalformedURLException e,
					Object state) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onFacebookError(FacebookError e, Object state) {
				// TODO Auto-generated method stub
				
			};
			
			});
			
			
			File sdCard = Environment.getExternalStorageDirectory();

			File directory = new File (sdCard.getAbsolutePath());

			File file = new File(directory, "test.png"); //or any other format supported

			FileInputStream streamIn = null;
			try {
				streamIn = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Bitmap bitmap = BitmapFactory.decodeStream(streamIn); //This gets the image


			
			 d = new BitmapDrawable(getResources(),bitmap);
			 
			/* LinearLayout main=(LinearLayout)findViewById(R.id.mainlayout);
				main.setBackgroundDrawable(d);*/
				
			
			
		}

		if (expires != 0) {
			facebook.setAccessExpires(expires);
		}

		if (!facebook.isSessionValid()) {
			Session currentSession = Session.getActiveSession();
			Toast.makeText(getApplicationContext(), "session:"+currentSession, Toast.LENGTH_SHORT).show();
			
			
			
			if (currentSession == null || currentSession.getState().isClosed()) {
		        Session session = new Session.Builder(this).build();
		        
		        Session.setActiveSession(session);
		        currentSession = session;
		    }

		    if (currentSession.isOpened()) {
		        // Do whatever u want. User has logged in
		    	
		    	Toast.makeText(getApplicationContext(), "Session is open", Toast.LENGTH_SHORT).show();

		    } else if (!currentSession.isOpened()) {
		        // Ask for username and password
		        OpenRequest op = new Session.OpenRequest(this);
		    	Toast.makeText(getApplicationContext(), "Session is not open", Toast.LENGTH_SHORT).show();
		        op.setLoginBehavior(SessionLoginBehavior.SUPPRESS_SSO);
		        op.setCallback(null);

		        List<String> permissions = new ArrayList<String>();
		        permissions.add("publish_stream");
		        permissions.add("user_likes");
		        permissions.add("email");
		        permissions.add("user_birthday");
		        op.setPermissions(permissions);

		        Session session = new Session.Builder(BaseActivity.this).build();
		        Session.setActiveSession(session);
		        session.openForPublish(op);
		    	Toast.makeText(getApplicationContext(), "Session is open to publish", Toast.LENGTH_SHORT).show();
		    }
			
			
			
			
			
			
			
			
			
			
			
			
			facebook.authorize(this,
					new String[] { "email", "publish_stream" },
					new DialogListener() {

						@Override
						public void onCancel() {
							// Function to handle cancel event
						}

						@Override
						public void onComplete(Bundle values) {
							// Function to handle complete event
							// Edit Preferences and update facebook acess_token
							SharedPreferences.Editor editor = mPrefs.edit();
							editor.putString("access_token",
									facebook.getAccessToken());
							editor.putLong("access_expires",
									facebook.getAccessExpires());
							
							editor.commit();
Log.i("log in", "sucess");
							Toast.makeText(getApplicationContext(), "Logged in", Toast.LENGTH_SHORT).show();
							getProfileInformation();
						}

						@Override
						public void onError(DialogError error) {
							// Function to handle error

						}

						@Override
						public void onFacebookError(FacebookError fberror) {
							// Function to handle Facebook errors

						}

					});
		}
		
		
		
	}
	
	 public void onActivityResult(int requestCode, int resultCode, Intent data) {
	        switch (requestCode) {
	            case 1:
	            	Toast.makeText(getApplicationContext(), "case1", Toast.LENGTH_SHORT).show();
	                getProfileInformation();
	                break;
	            default:
	                Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	                break;
	        }
	    } 
	 

	@SuppressWarnings("deprecation")
	public void getProfileInformation() {
		mAsyncRunner.request("me", new RequestListener() {
			@Override
			public void onComplete(String response, Object state) {
				Log.d("Profile", response);
				String json = response;
				try {
					// Facebook Profile JSON data
					JSONObject profile = new JSONObject(json);
					
					// getting name of the user
					final String name = profile.getString("name");
					SharedPreferences.Editor editor = mPrefs.edit();
					editor.putString("profilename",name);
					editor.commit();
					// getting email of the user
					final String email = profile.getString("email");
					//id=profile.getInt("id");
					
					String facebookId = profile.optString("id");

					mAsyncRunner.request("me/friends", new FriendsRequestListener());
					
					PublicValues publicval=new PublicValues();
					publicval.friends_list=null;
					publicval.friends_list=friends_list;
				     picture = null;
				    InputStream inputStream = null;
					 try {
					        inputStream = new URL("https://graph.facebook.com/" + facebookId + "/picture").openStream();
					    } catch (Exception e) {        
					     e.printStackTrace();
					   

					    }
					    picture = Drawable.createFromStream(inputStream, "facebook-pictures");
					    
					    File sdCardDirectory = Environment.getExternalStorageDirectory();
					    File image = new File(sdCardDirectory, "test.png");
					    
					    FileOutputStream outStream;
					    try {

					        outStream = new FileOutputStream(image);
					        Bitmap bitmap = ((BitmapDrawable)picture).getBitmap();
					        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream); 
					        /* 100 to keep full quality of the image */

					       Log.i("image", "addede to sd card");
					        outStream.flush();
					        outStream.close();
					       
					    } catch (FileNotFoundException e) {
					        e.printStackTrace();
					    } catch (IOException e) {
					        e.printStackTrace();
					    }
					
					/*Bitmap bitmap = null;
					 					   
					    String imageURL = "https://graph.facebook.com/" + id + "/picture";
					    try {
					        bitmap = BitmapFactory.decodeStream((InputStream)new URL(imageURL).getContent());
					    } catch (Exception e) {
					        Log.d("TAG", "Loading Picture FAILED");
					        e.printStackTrace();
					    }
					   d = new BitmapDrawable(getResources(),bitmap);
					  */
					    
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							
							/* img.setBackgroundDrawable(picture);*/
							Toast.makeText(getApplicationContext(), "Name: " + name + "\nEmail: " + email, Toast.LENGTH_LONG).show();
						}

					});

					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onIOException(IOException e, Object state) {
			}

			@Override
			public void onFileNotFoundException(FileNotFoundException e,
					Object state) {
			}

			@Override
			public void onMalformedURLException(MalformedURLException e,
					Object state) {
			}

			@Override
			public void onFacebookError(FacebookError e, Object state) {
			}
		});
		
		
		
	}
	
	ArrayList<String> friends_list = new ArrayList<String>();
	private class FriendsRequestListener implements RequestListener {
        String friendData;

        //Method runs when request is complete
        public void onComplete(String response, Object state) {
            Log.v("", "FriendListRequestONComplete");
            //Create a copy of the response so i can be read in the run() method.
            friendData = response; 
            Log.v("friendData--", ""+friendData);
            //Create method to run on UI thread
            BaseActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        //Parse JSON Data
                        JSONObject json;
                        json = Util.parseJson(friendData);

                        //Get the JSONArry from our response JSONObject
                        JSONArray friendArray = json.getJSONArray("data");

                        Log.v("friendArray--", ""+friendArray);

                        for(int i = 0; i< friendArray.length(); i++)
                        {
                            JSONObject frnd_obj = friendArray.getJSONObject(i);
                          
							friends_list.add(frnd_obj.getString("name"));//+"~~~"+frnd_obj.getString("id"));
                        }

                        Toast.makeText(getApplicationContext(), "size:"+friends_list.size(), Toast.LENGTH_SHORT).show();

                      //  ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1,android.R.id.text1, friends_list);
                     //   lv.setAdapter(adapter);

                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (FacebookError e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
        }

		@Override
		public void onIOException(IOException e, Object state) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onFileNotFoundException(FileNotFoundException e,
				Object state) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onMalformedURLException(MalformedURLException e,
				Object state) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onFacebookError(FacebookError e, Object state) {
			// TODO Auto-generated method stub
			
		}

	}
}
