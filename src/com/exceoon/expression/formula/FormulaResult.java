/**
 * 
 */
package com.exceoon.expression.formula;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author exceoonadmin
 *
 */
public class FormulaResult implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4127444512685535938L;
	private Map<String, Object> o = new HashMap<String, Object>(10);
	
	void _add(String key, Object obj)
	{
		o.put(key, obj);
	}
	
	public Map<String, Object> getResult()
	{
		return o;
	}
	
	public Object getValue(String parameter)
	{
		return o.get(parameter.toLowerCase());
	}
	
	public int getInt(String parameter)
	{
		return Integer.parseInt(String.valueOf(getValue(parameter)));
	}
	
	public short getShort(String parameter)
	{
		return Short.parseShort(String.valueOf(getValue(parameter)));
	}
	
	public long getLong(String parameter)
	{
		return Long.parseLong(String.valueOf(getValue(parameter)));
	}
	
	public float getFloat(String parameter)
	{
		return Float.parseFloat(String.valueOf(getValue(parameter)));
	}
	
	public double getDouble(String parameter)
	{
		return Double.parseDouble(String.valueOf(getValue(parameter)));
	}
	
	public String getString(String parameter)
	{
		return String.valueOf(getValue(parameter));
	}
	
	public boolean getBoolean(String parameter)
	{
		return true;
	}
	
	public int[] getIntArray(String parameter)
	{
		return (int[])getValue(parameter);
	}
	
	public short[] getShortArray(String parameter)
	{
		return (short[])getValue(parameter);
	}
	
	public long[] getLongArray(String parameter)
	{
		return (long[])getValue(parameter);
	}
	
	public float[] getFloatArray(String parameter)
	{
		return (float[])getValue(parameter);
	}
	
	public double[] getDoubleArray(String parameter)
	{
		return (double[])getValue(parameter);
	}
	
	public String[] getStrArray(String parameter)
	{
		return (String[])getValue(parameter);
	}
	
	public boolean[] getBooleanArray(String parameter)
	{
		return (boolean[])getValue(parameter);
	}
}
