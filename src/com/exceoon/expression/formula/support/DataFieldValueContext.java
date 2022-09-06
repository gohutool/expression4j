/**
 * 
 */
package com.exceoon.expression.formula.support;

import java.util.HashMap;
import java.util.Map;



/**
 * @author geniusadmin
 *
 */
public class DataFieldValueContext extends ThreadLocal<Map<String, Object>>
{
	private static DataFieldValueContext rfc = new DataFieldValueContext();
	
	public static DataFieldValueContext getContext()
	{
		return rfc;
	}
	
	public void addFieldValue(String field, Object value)
	{
		get().put(field.toLowerCase(), value);		
	}	
	
	public Object getFieldValue(String field)
	{
		return get().get(field.toLowerCase());
	}
	
	public boolean containFieldValue(String field)
	{
		return get().containsKey(field.toLowerCase());
	}
	
	public Map<String, Object> initialValue()
	{
		return new HashMap<String, Object>(10);
	}
	
	public void clear()
	{
		get().clear();
	}
}
