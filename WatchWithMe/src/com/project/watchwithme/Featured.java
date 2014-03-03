package com.project.watchwithme;

import java.util.ArrayList;

import com.jovita.whatson.R;
import com.project.watchwithme.adapters.FeaturedListAdapter;
import com.project.watchwithme.adapters.MostPopularListAdapter;
import com.project.watchwithme.domain.MostpopularShowDomain;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Featured extends BaseActivity{

/**
 * @author JOVITA P J
 *
 *
 */
	
	ListView list;
	FeaturedListAdapter adapter;
	public  ArrayList<MostpopularShowDomain> CustomListViewValuesArr = new ArrayList<MostpopularShowDomain>();
	public  Featured CustomListView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorites);
		
		getSupportActionBar().setTitle(
				Html.fromHtml("<font color='#058ec7'>Featured Program</font>"));
		TextView title=(TextView)findViewById(R.id.title);
		title.setText("Featured Program");
CustomListView = this;
		
		setListData();
		
		 list=(ListView)findViewById(R.id.FeaturesShow_list);
		
		 /**************** Create Custom Adapter *********/
	        adapter=new FeaturedListAdapter(CustomListView, CustomListViewValuesArr);
	        list.setAdapter(adapter);
		
		
		
	}
	
	/****** Function to set data in ArrayList *************/
    public void setListData()
    {
    	int img[]={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f
				,R.drawable.g,R.drawable.h,R.drawable.i,R.drawable.j,R.drawable.k};
    	String programs[]={
			"Men vs Wild",
				"Push Girls","Dormitoryo","Zach Stone Is Gonna Be Famous","Running Wild",
				"Kaun Banega Crorepathi","Asia Edge","George Clarke's Amazing Spaces",
				"London Dreams","Go Diego Go!","Immortal Songs 2","Untamed Americas",
				"Reel Time","Daphne Hazard",
			"Men vs Wild",
				"Push Girls","Dormitoryo","Zach Stone Is Gonna Be Famous","Running Wild",
				"Kaun Banega Crorepathi","Asia Edge","George Clarke's Amazing Spaces",
				"London Dreams","Go Diego Go!","Immortal Songs 2","Untamed Americas",
				"Reel Time","Daphne Hazard"		};
    	
    	
    	String[] web1 = {
    			"Director:Ash Bolland",
    			"Director:Ash Bolland","Director:Ash Bolland","Director:Ash Bolland","Director:Ash Bolland",
    			"Director:Ash Bolland","Director:Ash Bolland","Director:Ash Bolland",
    			"Director:Ash Bolland","Director:Ash Bolland","Director:Ash Bolland","Director:Ash Bolland",
    			"Director:Ash Bolland","Director:Ash Bolland",
    			"Director:Ash Bolland",
    			"Director:Ash Bolland","Director:Ash Bolland","Director:Ash Bolland","Director:Ash Bolland",
    			"Director:Ash Bolland","Director:Ash Bolland","Director:Ash Bolland",
    			"Director:Ash Bolland","Director:Ash Bolland","Director:Ash Bolland","Director:Ash Bolland",
    			"Director:Ash Bolland","Director:Ash Bolland"
    			
    	};
    	
		for (int i = 0; i < 11; i++) {
			
			final MostpopularShowDomain mostpopular = new MostpopularShowDomain();
			    
			  /******* Firstly take data in model object ******/
			mostpopular.setpgName(programs[i]);
			
			mostpopular.setpgImg(img[i]);
			mostpopular.setpgDesc(web1[i]);
			   
			/******** Take Model Object in ArrayList **********/
			CustomListViewValuesArr.add(mostpopular);
		}
		
    }
	
    public void onItemClick(int mPosition)
    {
    	MostpopularShowDomain tempValues = (MostpopularShowDomain) CustomListViewValuesArr.get(mPosition);
    	
    	Toast.makeText(CustomListView, 
    			""+tempValues.getpgName(),Toast.LENGTH_SHORT).show();
    }
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
}