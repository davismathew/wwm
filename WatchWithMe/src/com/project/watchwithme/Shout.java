package com.project.watchwithme;

import com.jovita.whatson.R;
import com.project.watchwithme.adapters.CustomListShout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Shout extends BaseActivity
/**
 * @author JASMINE
 *
 *
 */


{
	
	ListView list;
	Intent i;
	
	String[] web = {
		"Men vs Wild",
			"Push Girls","Dormitoryo","Zach Stone Is Gonna Be Famous","Running Wild",
			"Kaun Banega Crorepathi","Asia Edge","George Clarke's Amazing Spaces",
			"London Dreams","Go Diego Go!","Immortal Songs 2","Untamed Americas",
			"Reel Time","Daphne Hazard"	};
	
	String[] web1 = {
			"People:92,Comments:729",
				"People:76,Comments:505","People:98,Comments:327","People:33,Comments:786","People:69,Comments:368","People:92,Comments:729",
				"People:76,Comments:505","People:98,Comments:327","People:33,Comments:786","People:69,Comments:368","People:92,Comments:729",
				"People:76,Comments:505","People:98,Comments:327","People:33,Comments:786"
				
		};
	
	Integer[] imageId = {
			R.drawable.a,
			R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,
			R.drawable.g,R.drawable.h,R.drawable.i,R.drawable.j,R.drawable.k,
			R.drawable.l,R.drawable.m,R.drawable.n
				
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shout);

		 //Code for customlist
		
		CustomListShout adapter = new CustomListShout(Shout.this, web,web1, imageId);
		
		getSupportActionBar().setTitle(
				Html.fromHtml("<font color='#058ec7'>Shout Box</font>"));
		TextView title=(TextView)findViewById(R.id.title);
		title.setText("SHOUT BOX");
		
        list=(ListView)findViewById(R.id.list_shout);
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{

			
			 @Override
	            public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
			 {
	                Toast.makeText(Shout.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();
	                i= new Intent(Shout.this,ShoutSingleProgram.class);
	              
	                i.putExtra("txt",web[+ position]);
		            startActivity(i);   
	               

	         }
	       
		});

		
	}
		
	@Override
	public void onBackPressed() 
	{
	    finish();
	}	
}
	
	
		
