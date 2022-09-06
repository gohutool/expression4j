/**
 * 
 */
package com.exceoon.expression.extend.function;

import java.util.ArrayList;
import java.util.List;

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
public class LENFunction extends EconomicFunction
{
	/* (non-Javadoc)
	 * @see fr.expression4j.core.Expression#evaluate(fr.expression4j.core.Parameters)
	 */
	@Override
	public MathematicalElement evaluate(Parameters parameters)
	        throws EvalException
	{
		// TODO Auto-generated method stub
		return evaluate(null, parameters);
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.core.Expression#evaluate(fr.expression4j.basic.OperatorManager, fr.expression4j.core.Parameters)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public MathematicalElement evaluate(OperatorManager operatorManager,
	        Parameters parameters) throws EvalException
	{
		try {		
			
			MathematicalElement x = parameters.getParameter("x");
			
			if (x.getType() != ObjectArrayMathematicalElement.TYPE) {
				
				throw new EvalException("Left element is not a ObjectArrayMathematicalElement");
			}
			
			if(logger.isDebugEnabled())
				logger.debug(x);
			
			List<Double> data = (List<Double>)x.getValue();			
			
			return NumberFactory.createReal(data.size()==0?0:data.size());
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
		return "len";
	}
	
	private static final Logger logger = Logger.getLogger(LENFunction.class);

	/* (non-Javadoc)
	 * @see fr.expression4j.core.Expression#getParameters()
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List getParameters()
	{
		// TODO Auto-generated method stub
		List<String> parameter = new ArrayList<String>(2);
		parameter.add("x");
		
		return parameter;
	}
}
