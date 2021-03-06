package com.suitong.devplatform.common.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.suitong.devplatform.config.Constants;
import org.apache.commons.lang3.StringUtils;

public class PropertiesUtil {

	private Properties props = new Properties();
	
	public PropertiesUtil(String fileName)
	{
		String expandName = ".properties";
		String name = "";
		if(fileName.indexOf(expandName) > -1)
		{
			name = fileName;
		}
		else
		{
			name = fileName + expandName;
		}
		try
		{
			InputStream in = this.getClass().getClassLoader().getResourceAsStream(name);
			props.load(new InputStreamReader(in, Constants.CHAR_SET));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public PropertiesUtil(InputStream is) {
		try {
			props.load(new InputStreamReader(is, Constants.CHAR_SET));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getValue(String key)
	{
		String value = props.getProperty(key);
		if(StringUtils.isBlank(value)) {
			return key;
		}
		return value;
	}
	
}