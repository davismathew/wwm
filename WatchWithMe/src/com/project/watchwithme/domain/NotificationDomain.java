package com.project.watchwithme.domain;

public class NotificationDomain {


/**
 * @author JOVITA P J
 *
 *
 */

	int pgImg;
	String pgName;
	String pgDesc;
	
	public void setpgImg(int image)
	{
		this.pgImg = image;
	}
	public void setpgName(String programName)
	{
		this.pgName=programName;
	}
	public void setpgDesc(String programDescription)
	{
		this.pgDesc=programDescription;
	}
	
	public int getpgImg()
	{
		return pgImg;
		
	}
	public String getpgName()
	{
		return pgName;
	}
	public String getpgDesc()
	{
		return pgDesc;
	}
	
}
