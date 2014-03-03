package com.project.watchwithme;

import java.util.ArrayList;

import com.jovita.whatson.R;
import com.project.watchwithme.adapters.MostPopularListAdapter;
import com.project.watchwithme.adapters.TvGuideChannelListAdapter;
import com.project.watchwithme.domain.MostpopularShowDomain;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TVGuide extends BaseActivity {

/**
 * @author JOVITA P J
 *
 *
 */

	ListView channellist;
	TvGuideChannelListAdapter adapter;
	ArrayList<String> channels=new ArrayList<String>();
	public  TVGuide CustomListView = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tv_guide);
		
		
		//Adding title..........................
		getSupportActionBar().setTitle(
				Html.fromHtml("<font color='#058ec7'>TV Guide</font>"));
		TextView title=(TextView)findViewById(R.id.title);
		title.setText("TV Guide");
		
		CustomListView = this;
		//adding items.......................
		for(int i=0;i<=10;i++)
		{
			channels.add("channel:"+i);
		}
	
		channellist=(ListView)findViewById(R.id.lst_channellist);
		 adapter=new TvGuideChannelListAdapter(CustomListView, channels);
		 channellist.setAdapter(adapter);
		
		
		
		
		
		
		
	}
	
	public void onItemClick(int mPosition)
    {
    	String tempValues = (String) channels.get(mPosition);
    	
    	Toast.makeText(CustomListView, 
    			""+tempValues.toString(),Toast.LENGTH_SHORT).show();
    }
    
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
}
