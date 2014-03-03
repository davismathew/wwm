package com.project.watchwithme;

import java.util.ArrayList;

import com.jovita.whatson.R;
import com.project.watchwithme.adapters.NotificationListAdapter;
import com.project.watchwithme.domain.NotificationDomain;

import android.os.Bundle;
import android.text.Html;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NotificationCenter extends BaseActivity {
	

/**
 * @author JOVITA P J
 *
 *
 */

	ListView list;
	NotificationListAdapter adapter;
	public  ArrayList<NotificationDomain> CustomListViewValuesArr = new ArrayList<NotificationDomain>();
	public  NotificationCenter CustomListView = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification_center);
		
		getSupportActionBar().setTitle(
				Html.fromHtml("<font color='#058ec7'>Notification Center</font>"));
		TextView title=(TextView)findViewById(R.id.title);
		title.setText("Notification Center");
		
		
		CustomListView = this;
		
		setListData();
		
		 list=(ListView)findViewById(R.id.Notification_listItem);
		
		 /**************** Create Custom Adapter *********/
	        adapter=new NotificationListAdapter(CustomListView, CustomListViewValuesArr);
	        list.setAdapter(adapter);
		
		
		
	}
	
	/****** Function to set data in ArrayList *************/
    public void setListData()
    {
    	
		for (int i = 0; i < 11; i++) {
			
			final NotificationDomain notification = new NotificationDomain();
			    
			  /******* Firstly take data in model object ******/
			notification.setpgName("Program"+i);
			notification.setpgImg(R.drawable.ic_launcher);
			notification.setpgDesc("Program will start soon At:"+i);
			   
			/******** Take Model Object in ArrayList **********/
			CustomListViewValuesArr.add(notification);
		}
		
    }
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
	 public void onItemClick(int mPosition)
	    {
	    	NotificationDomain tempValues = (NotificationDomain) CustomListViewValuesArr.get(mPosition);
	    	
	    	Toast.makeText(CustomListView, 
	    			""+tempValues.getpgName(),Toast.LENGTH_SHORT).show();
	    }
	   
	 
}
