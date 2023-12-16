package com.utils;
public class StringUtils {
	
	public static boolean isEmpty(String str)
	{
		if("".equals(str)||null==str)
		{
			return true;
		}else
		{
			return false;
		}
	}
	public static boolean isNotEmpty(String str)
	{
		if(!"".equals(str)&&null!=str)
		{
			return true;
		}else
		{
			return false;
		}
	}

}

