/**
 * 
 */
package com.exceoon.expression.formula.support;

import java.util.Properties;

/**
 * @author exceoonadmin
 *
 */
public class Configuration
{
	private static Configuration instance = new Configuration();
	
	private Properties p = new Properties();
	
	public static Configuration getConfiguration()
	{
		return instance;
	}
	
	Configuration()
    {
	    // TODO Auto-generated constructor stub
		try
		{
			p.load(getClass().getResourceAsStream("/com/exceoon/expression/formula/formula.properties"));
		}
		catch (Exception e) {
			throw new IllegalStateException("configuration error", e);
		}
    }
	
	public String getDataFetcher()
	{
		return p.getProperty("datafetcher"); 
	}
}
