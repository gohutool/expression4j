/**
 * 
 */
package com.exceoon.expression.formula;

import java.io.Serializable;
import java.util.List;

import com.exceoon.expression.extend.ObjectArrayMathematicalElement;

import fr.expression4j.core.Parameters;
import fr.expression4j.factory.NumberFactory;
import fr.expression4j.sample.custom.element.StringMathematicalElement;
import fr.expression4j.sample.custom.operator.BooleanMathematicalElement;

/**
 * @author exceoonadmin
 *
 */
public class FormulaParameters implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2528037715211005114L;

	public void addParameter(String pname, String value)
	{
		p.addParameter(pname, new StringMathematicalElement(value));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addParameter(String pname, List value)
	{
		p.addParameter(pname, new ObjectArrayMathematicalElement(value));
	}
	
	public void addParameter(String pname, short value)
	{
		p.addParameter(pname, NumberFactory.createReal(value));
	}
	
	public void addParameter(String pname, int value)
	{
		p.addParameter(pname, NumberFactory.createReal(value));
	}
	
	public void addParameter(String pname, long value)
	{
		p.addParameter(pname, NumberFactory.createReal(value));
	}
	
	public void addParameter(String pname, float value)
	{
		p.addParameter(pname, NumberFactory.createReal(value));
	}
	
	public void addParameter(String pname, double value)
	{
		p.addParameter(pname, NumberFactory.createReal(value));
	}
	
	public void addParameter(String pname, boolean value)
	{
		p.addParameter(pname, new BooleanMathematicalElement(value));
	}
	
	public void addParameter(String pname, Integer[] values)
	{
		addNumericParameter(pname, values);
	}
	
	public void addParameter(String pname, Long[] values)
	{
		addNumericParameter(pname, values);
	}
	
	public void addParameter(String pname, Short[] values)
	{
		addNumericParameter(pname, values);
	}
	
	public void addParameter(String pname, Float[] values)
	{
		addNumericParameter(pname, values);
	}
	
	public void addParameter(String pname, Double[] values)
	{
		addNumericParameter(pname, values);
	}
	
	public void addParameter(String pname, Boolean[] values)
	{
		p.addParameter(pname, new ObjectArrayMathematicalElement<Boolean>(ExpressionUtils.array2list(values)));
	}
	
	public <T> void addArrayParameter(String pname, T[] values)
	{
		p.addParameter(pname, new ObjectArrayMathematicalElement<T>(ExpressionUtils.array2list(values)));
	}
	
	public <T> void addNumericParameter(String pname, T[] values)
	{
		p.addParameter(pname, new ObjectArrayMathematicalElement<Double>(ExpressionUtils.array2Doublelist(values)));
	}
	
	public void addParameter(String pname, String[] values)
	{
		addArrayParameter(pname, values);
	}
	
	Parameters _getParameters()
	{
		return p;
	}
	
	private Parameters p =  ExpressionUtils.createParameters();
}
