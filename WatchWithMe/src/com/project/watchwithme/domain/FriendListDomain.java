package com.project.watchwithme.domain;

public class FriendListDomain {
	/**
	 * @author JOVITA P J
	 *
	 *
	 */

		int frndImg;
		String frndName;
		
		public void setpgImg(int image)
		{
			this.frndImg = image;
		}
		public void setpgName(String programName)
		{
			this.frndName=programName;
		}
		
		public int getpgImg()
		{
			return frndImg;
			
		}
		public String getpgName()
		{
			return frndName;
		}
		
}
