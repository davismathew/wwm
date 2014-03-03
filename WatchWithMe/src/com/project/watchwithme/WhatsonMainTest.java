package com.project.watchwithme;






import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.digitalaria.gama.carousel.Carousel;
import com.project.watchwithme.domain.chanellistdomain;
import com.thehayro.view.InfinitePagerAdapter;
import com.thehayro.view.InfiniteViewPager;
import com.jovita.whatson.R;

import android.R.drawable;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * @author JOVITA P J
 *
 *
 */

public class WhatsonMainTest extends BaseActivity implements OnItemClickListener{
	private Carousel carousel;
	private ImageAdapter adapter;
	private int[] channel_icon = { R.drawable.ch_amrita, R.drawable.ch_animal_planet,
				R.drawable.ch_asianet, R.drawable.ch_asianet_movies, R.drawable.ch_asianet_news,
				R.drawable.ch_asianet_plus,R.drawable.ch_axn,R.drawable.ch_indiavision,
				R.drawable.ch_set,R.drawable.ch_sun};
	private Bitmap[] icons;
	public  ArrayList<chanellistdomain> CustomListViewValuesArr = new ArrayList<chanellistdomain>();
	public ArrayList<String> iconnames=new ArrayList<String>();
	int testarray[]={0,1};
	public  WhatsonMainTest whatsonthis = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getSupportActionBar().setTitle(
		Html.fromHtml("<font color='#058ec7'>whatson</font>"));
		
		new GetChannels().execute();
		//init();
		
		
	}
	
	public void tileview()
	{
		 final InfiniteViewPager viewPager = (InfiniteViewPager) findViewById(R.id.infinite_viewpager);
	        viewPager.setAdapter(new MyInfinitePagerAdapter(testarray[0]));
	        viewPager.setPageMargin(20);
		 
	        viewPager.setOnInfinitePageChangeListener(new InfiniteViewPager.OnInfinitePageChangeListener() {
	            @Override
	            public void onPageScrolled(final Object indicator, final float positionOffset,
	                                       final int positionOffsetPixels) {
	                Log.d("InfiniteViewPager", "onPageScrolled ".concat(String.valueOf(indicator)));
	            }

	            @Override
	            public void onPageSelected(final Object indicator) {
	                Log.d("InfiniteViewPager", "onPageSelected " + indicator.toString());
	            }

	            @Override
	            public void onPageScrollStateChanged(final int state) {
	                Log.d("InfiniteViewPager", "state " + String.valueOf(state));
	            }
	        });
	}
	
	LinearLayout layout;
    private class MyInfinitePagerAdapter extends InfinitePagerAdapter<Integer> {

        /**
         * Standard constructor.
         *
         * @param initValue the initial indicator value the ViewPager should start with.
         */
        public MyInfinitePagerAdapter(final Integer initValue) {
            super(initValue);
        }

        @Override
        public ViewGroup instantiateItem(Integer indicator) {
            Log.d("InfiniteViewPager", "instantiating page " + indicator);
   /*         if(indicator==testarray[1])
            {
            	 layout = (LinearLayout) ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE))
                         .inflate(R
                         .layout
                         .testlayout, null);
            	
            }
            else{*/
            layout = (LinearLayout) ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE))
                .inflate(R
                .layout
                .whatson_test, null);
           // }
           /* TextView pgno=(TextView)findViewById(R.id.txt_pgno);
            pgno.setText(String.format("Page %s", indicator));*/
          //  Toast.makeText(getApplicationContext(), indicator, Toast.LENGTH_SHORT).show();
          /*  final TextView text = (TextView) layout.findViewById(R.id.moving_view_x);
            text.setText(String.format("Page %s", indicator));
            Log.i("InfiniteViewPager", String.format("textView.text() == %s", text.getText()));
            layout.setTag(indicator);*/
            return layout;
        }

        @Override
        public Integer getNextIndicator() {
        	int pos=getCurrentIndicator() + 1;
        	if(pos==testarray.length)
        	{
        		pos=testarray[0];
        	}
        	//.........................................................................................
            return pos;
        }

        @Override
        public Integer getPreviousIndicator() {
        	
        	int pre=getCurrentIndicator() - 1;
        	if(pre<0)
        	{
        		pre=testarray[testarray.length-1];
        	}
        	
            return pre;
        }

        @Override
        public String getStringRepresentation(final Integer currentIndicator) {
            return String.valueOf(currentIndicator);
        }

        @Override
        public Integer convertToIndicator(final String representation) {
            return Integer.valueOf(representation);
        }
    }

	private void init()
    {
    	 // create the carousel object.
        carousel = (Carousel) findViewById(R.id.carousel);
        
        // configurations for the carousel.
        carousel.setType(Carousel.TYPE_CYLINDER);
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
    		  return iconnames.size();
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
				view.setLayoutParams(new Carousel.LayoutParams(90, 80));
				
				ViewHolder holder = new ViewHolder();
				holder.imageView = (ImageView)view.findViewById(R.id.itemImage);
		        

				view.setTag(holder);
			}
			
			ViewHolder holder = (ViewHolder)view.getTag();
			//holder.imageView.setImageResource(channel_icon[position]);
			/*Drawable chanel=getResources().getDrawable(channel_icon[1]);
			holder.imageView.setBackgroundDrawable(chanel);*/
			new DownloadImageTask(holder.imageView).execute(iconnames.get(position));
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


	
private ProgressDialog pDialog;

// URL to get contacts JSON
private static String url = "http://needletrack.com/apps/wwm/service/getChannelList.json.php";
private static final String TAG_Channels = "getChannelListResponse";
JSONArray channelsist = null;
JSONArray NAMES = null;
private static final String TAG_NAME = "name";


private class GetChannels extends AsyncTask<Void, Void, Void> {

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		// Showing progress dialog
		pDialog = new ProgressDialog(WhatsonMainTest.this);
		pDialog.setMessage("Please wait...");
		pDialog.setCancelable(false);
		pDialog.show();

	}

	@Override
	protected Void doInBackground(Void... arg0) {
		// Creating service handler class instance
		ServiceHandler sh = new ServiceHandler();

		// Making a request to url and getting response
		String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
		ArrayList<String> channelicon_url = null;
		Log.d("Response: ", "> " + jsonStr);

		
		if (jsonStr != null) {
			try {
				
				
				JSONObject jsonObj = new JSONObject(jsonStr);
				JSONObject object = jsonObj.getJSONObject(TAG_Channels);
				Log.d("inside object: ", "> " +object);
				//JSONObject json = object.getJSONObject("channelList");
				//Log.d("inside json: ", "> " +json);
				// Getting JSON Array node
				channelsist = object.getJSONArray("channelList");
				Log.d("inside contacts: ", "> " +channelsist);
				

				
				
				// looping through All Contacts..................................................................
				for (int i = 0; i < channelsist.length(); i++) {
					
					JSONObject c = channelsist.getJSONObject(i);
					
					int id= c.getInt("id");
					int chanelno=c.getInt("chanelNumber");
					String iconstring = c.getString("channelIcon");
					int Catid=c.getInt("categoryId");
					String name = c.getString(TAG_NAME);
					
					Log.d("###values###: ", "> " +id+"_"+chanelno+"_"+iconstring+"_"+Catid+"_"+name);

					String imgurl = "http://needletrack.com/apps/wwm/images/channelicons/"+iconstring;
					
					
					chanellistdomain contact =new chanellistdomain();

					
					contact.setId(id);
					contact.setChannelnumber(chanelno);
					contact.setIcon(imgurl);
					contact.setCatid(Catid);
					contact.setName(name);
					iconnames.add(imgurl);
					CustomListViewValuesArr.add(contact);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			} 
		} else {
			Log.e("ServiceHandler", "Couldn't get any data from the url");
		}

		
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		// Dismiss the progress dialog
		if (pDialog.isShowing())
			pDialog.dismiss();
		/**
		 * Updating parsed JSON data into ListView
		 * */
		
		Log.e("loading value to list", "Loading.......");
		setContentView(R.layout.whatsonmain_test);
	init();
		 tileview();
	}

}

private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
	  ImageView bmImage;

	  public DownloadImageTask(ImageView bmImage) {
	      this.bmImage = bmImage;
	  }

	  protected Bitmap doInBackground(String... urls) {
	      String urldisplay = urls[0];
	      Bitmap mIcon11 = null;
	      try {
	        InputStream in = new java.net.URL(urldisplay).openStream();
	        mIcon11 = BitmapFactory.decodeStream(in);
	      } catch (Exception e) {
	          Log.e("Error", e.getMessage());
	          e.printStackTrace();
	      }
	      return mIcon11;
	  }

	  protected void onPostExecute(Bitmap result) {
	    //  bmImage.setImageBitmap(result);
	    Drawable img=new BitmapDrawable(getResources(),result);
	    bmImage.setBackgroundDrawable(img);
	  }
	}




}
