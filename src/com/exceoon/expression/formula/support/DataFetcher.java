/**
 * 
 */
package com.exceoon.expression.formula.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author geniusadmin
 *
 */
public interface DataFetcher
{
	@SuppressWarnings("rawtypes")
	public List getDatas(String field, int type, int n);
	
	public boolean needFetch(String feild);
}

class SimpleDataFetcher implements DataFetcher
{
	@SuppressWarnings("rawtypes")
	public List getDatas(String field, int type, int n)
	{
		int i, m;		
		
		if(field.equals("close")||field.equals("open")||field.equals("price"))
		{
			i = 100; m = 100;
		}
		else
		{
			i = 10000; m = 10000;
		}
		
		List<Double> data = new ArrayList<Double>(50);
		
		for(int ind = 0 ; ind < n ; ind ++)
		{
			data.add(Math.random()*m+i);
		}
				
		return data;
	}
	
	private static HashMap<String, String> fields = new HashMap<String, String>();
	
	static
	{
		fields.put("close", "close");
		fields.put("open", "open");
		fields.put("volumn", "volumn");
		fields.put("price", "price");
	}
	
	public boolean needFetch(String feild)
	{
		return fields.containsKey(feild.toLowerCase());
	}
}
