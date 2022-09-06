/**
 * 
 */
package com.exceoon.expression.extend.function;

import java.util.List;

import org.apache.log4j.Logger;

import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.OperatorManager;
import fr.expression4j.core.Catalog;
import fr.expression4j.core.Parameters;
import fr.expression4j.core.exception.EvalException;
import fr.expression4j.core.exception.ParametersException;
import fr.expression4j.core.predefine.AbstractFunction;
import fr.expression4j.factory.NumberFactory;
import fr.expression4j.util.ParameterUtil;

/**
 * @author geniusadmin
 *
 */
public class GetFiledFunction extends AbstractFunction
{
	private static final Logger logger = Logger.getLogger(GetFiledFunction.class);
	
	/* (non-Javadoc)
	 * @see fr.expression4j.core.Expression#evaluate(fr.expression4j.core.Parameters)
	 */
	@Override
	public MathematicalElement evaluate(Parameters parameters)
	        throws EvalException
	{
		// TODO Auto-generated method stub
		return evaluate(null,parameters);
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.core.Expression#evaluate(fr.expression4j.basic.OperatorManager, fr.expression4j.core.Parameters)
	 */
	@Override
	public MathematicalElement evaluate(OperatorManager operatorManager,
	        Parameters parameters) throws EvalException
	{
		// TODO Auto-generated method stub
		try {		
			
			MathematicalElement x = parameters.getParameter("x");
			
			if (x.getType() != 5) {
				
				throw new EvalException("Left element is not a StringMathematicalElement");
			}
			
			if(logger.isDebugEnabled())
				logger.debug(x);
			
			return NumberFactory.createReal(String.valueOf(x.getValue()).length());
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
		return "get";
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.core.Expression#getParameters()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List getParameters()
	{
		// TODO Auto-generated method stub
		return ParameterUtil.generateXParameters();
	}

}
