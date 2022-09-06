/**
 * 
 */
package com.exceoon.expression.extend;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.MathematicalException;

/**
 * @author exceoonadmin
 *
 */
public class ObjectArrayMathematicalElement<T> implements MathematicalElement
{
	public static final int TYPE = 7;
	public static final String KEY = "arrayValue";
	
	/**
	 * 
	 */
	public ObjectArrayMathematicalElement(List<T> list)
	{
		// TODO Auto-generated constructor stub
		if(list!=null)
			this.value = list;
	}
	
	public ObjectArrayMathematicalElement()
	{
		this.value = new ArrayList<T>(10);
	}
	
	public void addValues(List<T> list)
	{
		this.value.addAll(list);
	}
	
	public void addValue(T value)
	{
		this.value.add(value);
	}
	
	private List<T> value;	

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.MathematicalElement#getComplexValue()
	 */
	@Override
	public double getComplexValue() throws MathematicalException
	{
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.MathematicalElement#getProperties()
	 */
	@Override
	public Properties getProperties()
	{
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.MathematicalElement#getRealValue()
	 */
	@Override
	public double getRealValue()
	{
		// TODO Auto-generated method stub
		return this.value.size();
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.MathematicalElement#getType()
	 */
	@Override
	public int getType()
	{
		// TODO Auto-generated method stub
		return TYPE;
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.MathematicalElement#getValue()
	 */
	@Override
	public Object getValue()
	{
		// TODO Auto-generated method stub
		return this.value;
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.MathematicalElement#setProperties(java.util.Properties)
	 */
	@Override
	public void setProperties(Properties properties)
	{
		throw new UnsupportedOperationException();
	}

	public String toString()
	{
		return String.valueOf(value);
	}
}
