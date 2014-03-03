package com.project.watchwithme;

import java.lang.reflect.Array;

import com.jovita.whatson.R;
import com.project.watchwithme.adapters.CustomListShoutSingleProgram;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ShoutSingleProgram extends Shout
/**
 * @author JASMINE
 *
 *
 */

{
	private  int color;
	private  Paint paint;
	private  Rect rect;
	private  RectF rectF;
	
	private  Bitmap result;
	private  Bitmap round;
	
	private  Bitmap targetBitmap;
	private  Bitmap result1;
	private  Bitmap result2;
	
	private  Canvas canvas;
	private  float roundPx;
	
	
	ListView list_shout_screen;
    
	String[] web1 = {
			"Juanna","Norah","Juanna","Norah","Juanna","Norah"
	};
	
	String[] web = {
		    "Are you checked-in to the show ?",
			"This program is amazing",
			"Day by day they are improving the quality of contents.I love to watch this show because it gives lot of informations about the current issues in our society and the discussions regarding them",
			"Yeah !",
			"How is the show ?",
			"I will invite my friends"                                               
			
			
	};
	
	
	Integer[] imageId1 = {
			R.drawable.prof_pic_a,                      
			R.drawable.prof_pic_b,R.drawable.prof_pic_a,
			R.drawable.prof_pic_b,R.drawable.prof_pic_a,R.drawable.prof_pic_b
				
	};
	
	//Chat bubble
	Integer[] imageId2 = {
			R.drawable.aone,
			R.drawable.aone,R.drawable.aone,R.drawable.aone,R.drawable.aone,R.drawable.aone
			
	};
	
	Integer[] imageId3 = {
			R.drawable.white,
			R.drawable.white,R.drawable.white,R.drawable.white,R.drawable.white,R.drawable.white
			
	};
	
	public EditText editText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_pgm_shout);
		
		String data = getIntent().getStringExtra("txt");
		getSupportActionBar().setTitle(
				Html.fromHtml("<font color='#058ec7'>Shout Box</font>"));
		TextView title=(TextView)findViewById(R.id.title);
		title.setText(data);
		
		
	   Bitmap bmp=BitmapFactory.decodeResource(getResources(), R.drawable.white);
	   Bitmap bmp1=BitmapFactory.decodeResource(getResources(), R.drawable.prof_pic_a);
	     
	   Bitmap[] images = { BitmapFactory.decodeResource(getResources
			   (),R.drawable.prof_pic_a),BitmapFactory.decodeResource(getResources   
			   (),R.drawable.prof_pic_b),BitmapFactory.decodeResource(getResources(),R.drawable.prof_pic_a),BitmapFactory.decodeResource(getResources
					   (),R.drawable.prof_pic_b),BitmapFactory.decodeResource(getResources   
							   (),R.drawable.prof_pic_a),BitmapFactory.decodeResource(getResources(),R.drawable.prof_pic_b)}; 
	   
	   
	   	   
	   Bitmap[] rounded=new Bitmap[images.length];
	   
	   for(int i=0;i<images.length;i++)
	   {
		  round=getCircularBitmapFrom(images[i]);
		  rounded[i]=round;
		  
	   }
	   
	     result1=getCircularBitmapFrom(bmp);
	     result2=getCircularBitmapFrom(bmp1);
	     
	     CustomListShoutSingleProgram adapter1 = new CustomListShoutSingleProgram(ShoutSingleProgram.this, web1,web,rounded,imageId2,imageId3,result1,result2);
	     CustomListShoutSingleProgram adapter = new CustomListShoutSingleProgram(ShoutSingleProgram.this, web1,web,rounded,imageId2,imageId3,result1,result2);
	     
	     list_shout_screen=(ListView)findViewById(R.id.list_shout_screen);
		 list_shout_screen.setAdapter(adapter1);
		 list_shout_screen.setAdapter(adapter);
		 list_shout_screen.setOnItemClickListener(new AdapterView.OnItemClickListener()
			{
              @Override
		            public void onItemClick(AdapterView<?> parent, View view,
		                                    int position, long id) 
                        {
		               // Toast.makeText(ShoutSingleProgram.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();
		                TextView txtTitle = (TextView)findViewById(R.id.txt);
		                txtTitle.length();
		                
		                Toast.makeText(ShoutSingleProgram.this, "Length is " + txtTitle.getMeasuredWidth(), Toast.LENGTH_SHORT).show();
		                txtTitle.getTextSize();
		                }
		       
			}
		    );
		 
		    editText = (EditText)findViewById(R.id.EditText1);
            editText.setOnClickListener(this);
           }
	
	

	
	
	public static Bitmap getCircularBitmapFrom(Bitmap bitmap) {
	    if (bitmap == null) {
	        return null;
	    }
	    float radius = bitmap.getWidth() > bitmap.getHeight() ? ((float) bitmap
	            .getHeight()) / 2f : ((float) bitmap.getWidth()) / 2f;
	    if (radius < 0) {
	        radius = 0;
	    }
	    Bitmap canvasBitmap = Bitmap.createBitmap(bitmap.getWidth(),
	            bitmap.getHeight(), Bitmap.Config.ARGB_8888);
	    BitmapShader shader = new BitmapShader(bitmap, TileMode.CLAMP,
	            TileMode.CLAMP);
	    Paint paint = new Paint();
	    paint.setAntiAlias(true);
	    paint.setShader(shader);

	    Canvas canvas = new Canvas(canvasBitmap);

	    canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
	            radius, paint);

	    return canvasBitmap;
	}
	
	
	@Override
	public void onClick(View v)
	{
	    editText.setText("");
	}
		
	@Override
	public void onBackPressed() 
	{
	    finish();
	}	
	
}
