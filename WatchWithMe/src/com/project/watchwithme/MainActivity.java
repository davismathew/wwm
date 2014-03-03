package com.project.watchwithme;



import com.digitalaria.gama.carousel.Carousel;
import com.jovita.whatson.R;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements OnItemClickListener{

/**
 * @author JOVITA P J
 *
 *
 */

	private Carousel carousel;
	private ImageAdapter adapter;
	private int[] channel_icon = { R.drawable.ch_amrita, R.drawable.ch_animal_planet,
				R.drawable.ch_asianet, R.drawable.ch_asianet_movies, R.drawable.ch_asianet_news,
				R.drawable.ch_asianet_plus,R.drawable.ch_axn,R.drawable.ch_indiavision,
				R.drawable.ch_set,R.drawable.ch_sun};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.whatson);
		getSupportActionBar().setTitle(
		Html.fromHtml("<font color='#058ec7'>whatson</font>"));
		 init();
		 
		 Toast.makeText(getApplicationContext(), colorchangeFlag, Toast.LENGTH_LONG).show();
		
	}

	 private void init()
	    {
	    	 // create the carousel object.
	        carousel = (Carousel) findViewById(R.id.carousel);
	        
	        // configurations for the carousel.
	        carousel.setType(Carousel.TYPE_ROTARY);
	        carousel.setOverScrollBounceEnabled(true);
	        carousel.setInfiniteScrollEnabled(true);
	        
	        carousel.setItemRearrangeEnabled(false);
	      // carousel.setFadingEdgeLength(5);
	        carousel.setHorizontalFadingEdgeEnabled(false);
	       // carousel.setOverScrollMode(0x00000000);
	        carousel.setOnItemClickListener(this);
	        carousel.setSpacing(5);
	        // set images for the carousel.
	        adapter = new ImageAdapter(this);
	        carousel.setAdapter(adapter);
	       
	        
	        // change the first selected position.
	        carousel.setCenterPosition(3);
	    }
	 
	 public class ImageAdapter extends BaseAdapter {
	    	private Context mContext;
	    	
	    	public ImageAdapter(Context c)
	    	{
	    		mContext = c;
	    	}
	    	
	    	@Override
	    	public int getCount() {
	    		  return channel_icon.length;
	    	}

			@Override
			public Object getItem(int position) {
				return null;
			}

			@Override
			public long getItemId(int position) {
				return channel_icon[position];
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = convertView;
				if (view == null) {
					view = LayoutInflater.from(mContext).inflate(R.layout.carousel_item, parent, false);				
					view.setLayoutParams(new Carousel.LayoutParams(50, 70));
					
					ViewHolder holder = new ViewHolder();
					holder.imageView = (ImageView)view.findViewById(R.id.itemImage);
					
					view.setTag(holder);
				}
				
				ViewHolder holder = (ViewHolder)view.getTag();
				//holder.imageView.setImageResource(channel_icon[position]);
				Drawable chanel=getResources().getDrawable(channel_icon[position]);
				holder.imageView.setBackgroundDrawable(chanel);
				
				
				/*holder.imageView.setAdjustViewBounds(true);*/
				return view;
			}
			
			private class ViewHolder {
				ImageView imageView;
			}
	    }

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
		
		
	}


}
