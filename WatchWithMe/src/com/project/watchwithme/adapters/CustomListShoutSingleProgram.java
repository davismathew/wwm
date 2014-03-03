package com.project.watchwithme.adapters;

import com.jovita.whatson.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Message;
import android.support.v4.view.ViewPager.LayoutParams;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CustomListShoutSingleProgram extends ArrayAdapter<String>
/**
 * @author JASMINE
 *
 *
 */

{
		
	private final Activity context;
	private final String[] web;
	private final String[] web1;
	
	private final Bitmap[] rounded;
	
	//private final Integer[] imageId1;
	private final Integer[] imageId2;
	private final Integer[] imageId3;
	Bitmap image;
	Bitmap image1;
	
	public CustomListShoutSingleProgram(Activity context,String[] web1,String[] web,Bitmap[] rounded,Integer[] imageId2,Integer[] imageId3,Bitmap image1,Bitmap image) 
	{
		super(context, R.layout.list_single_shout_single, web);
		this.context = context;
		this.web = web;
		this.web1 = web1;
		//this.imageId1 = imageId1;	
		
		this.rounded=rounded;
		this.imageId2 = imageId2;
		this.imageId3 = imageId3;
		this.image=image;
		this.image1=image1;
	
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent)
	{
		
	LayoutInflater inflater = context.getLayoutInflater();
	View rowView= inflater.inflate(R.layout.list_single_shout_single, null, true);
	
	/*name*/ 
	//TextView txtTitle1 = (TextView) rowView.findViewById(R.id.txt1);
	/*msg*/
	TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
	
	//pic
	ImageView imageView1 = (ImageView) rowView.findViewById(R.id.img1);
	ImageView imageView3 = (ImageView) rowView.findViewById(R.id.img);
	
	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
			LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params.gravity = Gravity.RIGHT;
			txtTitle.setLayoutParams(params);
			
	
			
	txtTitle.setText(web[position]);
	//txtTitle.setBackgroundResource(R.drawable.chat);
	//txtTitle1.setText(web1[position]);
	
	imageView1.setImageBitmap(rounded[position]); 
	
	
	imageView3.setImageBitmap(image1);
	
	
	return rowView;
	}
	
}

