package com.project.watchwithme.adapters;

import com.jovita.whatson.R;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListShout  extends ArrayAdapter<String>
/**
 * @author JASMINE
 *
 *
 */
{
	private ImageButton btn_go;
	private final Activity context;
	private final String[] web;
	private final String[] web1;
	private final Integer[] imageId;
	
	public CustomListShout(Activity context,String[] web, String[] web1, Integer[] imageId) 
	{
	super(context, R.layout.list_view_shout, web);
	this.context = context;
	this.web = web;
	this.web1=web1;
	this.imageId = imageId;
	}
	
	
	@Override
	public View getView(int position, View view, ViewGroup parent)
	{
	LayoutInflater inflater = context.getLayoutInflater();
	View rowView= inflater.inflate(R.layout.list_view_shout, null, true);
	
	TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
	TextView txtTitle1 = (TextView) rowView.findViewById(R.id.txt1);
	
	ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
	
	txtTitle.setText(web[position]);
	txtTitle1.setText(web1[position]);

	imageView.setImageResource(imageId[position]);
	return rowView;
	}
	}