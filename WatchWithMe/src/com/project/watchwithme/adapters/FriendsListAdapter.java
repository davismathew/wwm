package com.project.watchwithme.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jovita.whatson.R;
import com.project.watchwithme.MyProfiles;
import com.project.watchwithme.NotificationCenter;
import com.project.watchwithme.adapters.NotificationListAdapter.ViewHolder;
import com.project.watchwithme.domain.FriendListDomain;
import com.project.watchwithme.domain.NotificationDomain;

public class FriendsListAdapter  extends BaseAdapter   implements OnClickListener{
	/**
	 * @author JOVITA P J
	 *
	 *
	 */

		/*********** Declare Used Variables *********/
	    private Activity activity;
	    private ArrayList data;
	    private static LayoutInflater inflater=null;

	    FriendListDomain tempValues=null;
	    int i=0;
	    
	    /*************  CustomAdapter Constructor *****************/
	    public FriendsListAdapter(Activity a, ArrayList d) {
	    	
	    	/********** Take passed values **********/
	        activity = a;
	        data=d;
	      
	        
	        /***********  Layout inflator to call external xml layout () **********************/
	        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        
	    }

	    /******** What is the size of Passed Arraylist Size ************/
	    public int getCount() {
	    	
	    	if(data.size()<=0)
	    		return 1;
	        return data.size();
	    }

	    public Object getItem(int position) {
	        return position;
	    }

	    public long getItemId(int position) {
	        return position;
	    }
	    
	    /********* Create a holder to contain inflated xml file elements ***********/
	    public static class ViewHolder{
	    	
	        public TextView FriendName;
	        public ImageView FriendImage;

	    }

	    /*********** Depends upon data size called for each row , Create each ListView row ***********/
	    public View getView(int position, View convertView, ViewGroup parent) {
	    	
	        View vi=convertView;
	        ViewHolder holder;
	        
	        if(convertView==null){ 
	        	
	        	/********** Inflate tabitem.xml file for each row ( Defined below ) ************/
	            vi = inflater.inflate(R.layout.friends_list_item, null); 
	            
	            /******** View Holder Object to contain tabitem.xml file elements ************/
	            holder=new ViewHolder();
	            holder.FriendName=(TextView)vi.findViewById(R.id.friend_name);
	            holder.FriendImage=(ImageView)vi.findViewById(R.id.friend_pic);
	            
	           /************  Set holder with LayoutInflater ************/
	            vi.setTag(holder);
	        }
	        else  
	            holder=(ViewHolder)vi.getTag();
	        
	        if(data.size()<=0)
	        {
	        	holder.FriendName.setText("No Data");
	            
	        }
	        else
	        {
	        	/***** Get each Model object from Arraylist ********/
		        tempValues=null;
		        tempValues = (FriendListDomain) data.get(position);
		        
		        /************  Set Model values in Holder elements ***********/
		         holder.FriendName.setText(tempValues.getpgName());
		         holder.FriendImage.setBackgroundResource(tempValues.getpgImg());
		         
		         /******** Set Item Click Listner for LayoutInflater for each row ***********/
		         vi.setOnClickListener(new OnItemClickListener(position));
	        }
	        return vi;
	    }
	    
	    @Override
	    public void onClick(View v) {
	            Log.v("Friend list adapter", "=====Row button clicked");
	    }
	    
	    /********* Called when Item click in ListView ************/
	    private class OnItemClickListener  implements OnClickListener{           
	        private int mPosition;
	        
	        OnItemClickListener(int position){
	        	 mPosition = position;
	        }
	        
	        @Override
	        public void onClick(View arg0) {
	            MyProfiles sct = (MyProfiles)activity;
	        	sct.onItemClick(mPosition);
	        }               
	    }  
		
}
