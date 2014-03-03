package com.project.watchwithme;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.facebook.Request;
import com.facebook.Request.GraphUserListCallback;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.jovita.whatson.R;
import com.project.watchwithme.adapters.FriendsListAdapter;
import com.project.watchwithme.adapters.NotificationListAdapter;
import com.project.watchwithme.domain.FriendListDomain;
import com.project.watchwithme.domain.NotificationDomain;
import com.project.watchwithme.domain.PublicValues;

import android.R.drawable;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyProfiles extends BaseActivity{
	

/**
 * @author JOVITA P J
 *
 *
 */
	ListView list;
	FriendsListAdapter adapter;
	public  ArrayList<FriendListDomain> CustomListViewValuesArr = new ArrayList<FriendListDomain>();
	public  MyProfiles CustomListView = null;
    ArrayList<String> friends;
	Bitmap bitmap,backbitmap,targetBitmap,sdbitmap;
	int fulllayoutheightmargine=0;
	BitmapDrawable image;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myprofile);
		
		
		
		
		getSupportActionBar().setTitle(
				Html.fromHtml("<font color='#058ec7'>My Profile</font>"));
		TextView title=(TextView)findViewById(R.id.title);
		title.setText("My Profile");
	   
	        
	       PublicValues publicval=new PublicValues();
	       Toast.makeText(getApplicationContext(), ""+publicval.friends_list.size(), Toast.LENGTH_SHORT).show();
	      friends= new ArrayList<String>();
	      friends=publicval.friends_list;
	      
	        File sdCard = Environment.getExternalStorageDirectory();

			File directory = new File (sdCard.getAbsolutePath());

			File file = new File(directory, "test.png"); //or any other format supported

			FileInputStream streamIn = null;
			try {
				streamIn = new FileInputStream(file);
				
				sdbitmap = BitmapFactory.decodeStream(streamIn);//This gets the image

				Log.i("File", "Got file from sd card");
				
				if(sdbitmap!=null)
			       {
					 
			         image=new BitmapDrawable(getRoundedShape(sdbitmap,1));
			         Log.i("File", "Got file from sd card");
			       }
			       else
			       { bitmap=BitmapFactory.decodeResource(getApplicationContext().getResources(),
			                R.drawable.images);
			        image=new BitmapDrawable(getRoundedShape(bitmap,1));
			       }
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
				 
				
			/*try {
				streamIn.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        */
	        
	        /* bitmap=BitmapFactory.decodeResource(getApplicationContext().getResources(),
	                R.drawable.images);*/
	      /* if(PublicValues.profileImage!=null)
	       {
	         image=new BitmapDrawable(getRoundedShape(PublicValues.profileImage,1));
	       }
	       else
	       { bitmap=BitmapFactory.decodeResource(getApplicationContext().getResources(),
	                R.drawable.images);
	        image=new BitmapDrawable(getRoundedShape(bitmap,1));
	       }*/
				 ImageView img=(ImageView)findViewById(R.id.image);
	ImageView imgback=(ImageView)findViewById(R.id.back);
	        
	         backbitmap=BitmapFactory.decodeResource(getApplicationContext().getResources(),
	                R.drawable.imagesback);
	        BitmapDrawable backimage=new BitmapDrawable(getRoundedShape(backbitmap,0));
	       
	        
	        imgback.setBackgroundDrawable(image);
	        img.setBackgroundDrawable(backimage);
	        
	        TextView profilename=(TextView)findViewById(R.id.Profile_name);
	         SharedPreferences Prefs = getPreferences(MODE_PRIVATE);
	        String name = Prefs.getString("profilename", "User");
	        
	        Log.i("prefe: name:",""+name);
	        profilename.setText(name);
	        
	        
	        CustomListView = this;
			
			setListData();
			
			 list=(ListView)findViewById(R.id.Friends_list);
			
			 //**************** Create Custom Adapter *********
		        adapter=new FriendsListAdapter(CustomListView, CustomListViewValuesArr);
		        list.setAdapter(adapter);
	    
		       
	    }
	
	
	/****** Function to set data in ArrayList *************/
    public void setListData()
    {
    	for(String frnd:friends)
    {
			
			final FriendListDomain friendlist = new FriendListDomain();
			    
			  /******* Firstly take data in model object ******/
			friendlist.setpgName(frnd.toString());
			friendlist.setpgImg(R.drawable.ic_launcher);
			   
			/******** Take Model Object in ArrayList **********/
			CustomListViewValuesArr.add(friendlist);
		}
		}
	    
    public void onItemClick(int mPosition)
    {
    	FriendListDomain tempValues = (FriendListDomain) CustomListViewValuesArr.get(mPosition);
    	
    	Toast.makeText(CustomListView, 
    			""+tempValues.getpgName(),Toast.LENGTH_SHORT).show();
    }
	    

		public Bitmap getRoundedShape(Bitmap scaleBitmapImage,int i) {
	    	// TODO Auto-generated method stub
	    	
	    	if(i==1)
	    	{
	    		int targetWidth = 150;
	        	int targetHeight = 150;
	        	Log.i("scaled bitmap="," bitmap");
	        	
	
	        	
	        	
	        	
	        	  targetBitmap = Bitmap.createBitmap(targetWidth, 
	                     targetHeight,Bitmap.Config.ARGB_8888);

	         Canvas canvas = new Canvas(targetBitmap);
	Path path = new Path();
	path.addCircle(((float) targetWidth - 1) / 2,
	((float) targetHeight - 1) / 2,
	(Math.min(((float) targetWidth), 
	         ((float) targetHeight)) / 2),
	Path.Direction.CCW);

	canvas.clipPath(path);
	Bitmap sourceBitmap = scaleBitmapImage;
	canvas.drawBitmap(sourceBitmap, 
	                         new Rect(0, 0, sourceBitmap.getWidth(),
	sourceBitmap.getHeight()), 
	                         new Rect(0, 0, targetWidth,
	targetHeight), null);

	    	}
	    	if(i==0)
	    	{
	    		int targetWidth = 160;
	        	int targetHeight = 160;
	        	Log.i("scaled bitmap=", "back");
	        	
	        	
	           	
	      	  targetBitmap = Bitmap.createBitmap(targetWidth, 
	                   targetHeight,Bitmap.Config.ARGB_8888);

	       Canvas canvas = new Canvas(targetBitmap);
	Path path = new Path();
	path.addCircle(((float) targetWidth - 1) / 2,
	((float) targetHeight - 1) / 2,
	(Math.min(((float) targetWidth), 
	       ((float) targetHeight)) / 2),
	Path.Direction.CCW);

	canvas.clipPath(path);
	Bitmap sourceBitmap = scaleBitmapImage;
	canvas.drawBitmap(sourceBitmap, 
	                       new Rect(0, 0, sourceBitmap.getWidth(),
	sourceBitmap.getHeight()), 
	                       new Rect(0, 0, targetWidth,
	targetHeight), null);

	        	
	    	}
	    	
	    	
	    	return targetBitmap;
	    	}
	    
	    @Override
		protected void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
			finish();
		}
	

}
