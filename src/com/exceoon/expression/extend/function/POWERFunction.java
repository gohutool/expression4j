/**
 * 
 */
package com.exceoon.expression.extend.function;

import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.exceoon.expression.extend.ObjectArrayMathematicalElement;

import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.OperatorManager;
import fr.expression4j.core.Catalog;
import fr.expression4j.core.Parameters;
import fr.expression4j.core.exception.EvalException;
import fr.expression4j.core.exception.ParametersException;
import fr.expression4j.factory.NumberFactory;

/**
 * @author exceoonadmin
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class POWERFunction extends EconomicFunction
{

	/* (non-Javadoc)
	 * @see fr.expression4j.core.Expression#evaluate(fr.expression4j.core.Parameters)
	 */
	@Override
	public MathematicalElement evaluate(Parameters parameters) throws EvalException
	{
		// TODO Auto-generated method stub
		return evaluate(null, parameters);
	}
	
	private static final Logger logger = Logger.getLogger(POWERFunction.class);

	/* (non-Javadoc)
	 * @see fr.expression4j.core.Expression#evaluate(fr.expression4j.basic.OperatorManager, fr.expression4j.core.Parameters)
	 */
	@Override
	public MathematicalElement evaluate(OperatorManager arg0, Parameters parameters)
	        throws EvalException
	{
		try {					
			MathematicalElement x = parameters.getParameter("x");
			MathematicalElement y = parameters.getParameter("y");

			if(logger.isDebugEnabled())
				logger.debug(x + "  " + y);
			
			if(x instanceof ObjectArrayMathematicalElement)
			{
				ObjectArrayMathematicalElement<Double> newArray = new ObjectArrayMathematicalElement<Double>();				
				
				List vs = (List)x.getValue();
				
				for(Object v : vs)
				{
					newArray.addValue(v==null?0:Math.pow(Double.parseDouble(String.valueOf(v)), y.getRealValue()));
				}
				
				return newArray;
			}
			else{		
				return NumberFactory.createReal(Math.pow(x.getRealValue(), y.getRealValue()));
			}
		}
		catch (ParametersException pe) {
			throw new EvalException("Could not find parameter x for function " + getName());
		}
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.core.Expression#getCatalog()
	 */
	@Override
	public Catalog getCatalog()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.core.Expression#getName()
	 */
	@Override
	public String getName()
	{
		// TODO Auto-generated method stub
		return "power";
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.core.Expression#getParameters()
	 */
	@Override
	public List getParameters()
	{
		return xParameters;
	}

	private static List xParameters;
	
	
	static {	
		xParameters = new Vector(2);
		xParameters.add("x");
		xParameters.add("y");
	}
}
