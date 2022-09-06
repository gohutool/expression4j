/**
 * 
 */
package com.exceoon.expression.extend.function;

import java.util.ArrayList;
import java.util.List;

import com.exceoon.expression.extend.ObjectArrayMathematicalElement;

import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.OperatorManager;
import fr.expression4j.core.Catalog;
import fr.expression4j.core.Parameters;
import fr.expression4j.core.exception.EvalException;
import fr.expression4j.core.exception.ParametersException;

/**
 * @author geniusadmin
 *
 */
public class EMAFunction extends EconomicFunction
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
	@SuppressWarnings("unchecked")
	@Override
	public MathematicalElement evaluate(OperatorManager operatorManager,
	        Parameters parameters) throws EvalException
	{
		try {		
			
			MathematicalElement x = parameters.getParameter("x");
			MathematicalElement y = parameters.getParameter("y");
			//MathematicalElement z = parameters.getParameter("z");
			
			if (x.getType() != ObjectArrayMathematicalElement.TYPE) {
				
				throw new EvalException("Left element is not a ObjectArrayMathematicalElement");
			}
			
			//System.out.println(x);
			
			if (y.getType() != 1) {
				
				throw new EvalException("Left element is not a MathematicalElement");
			}
			
			/*if (z.getType() != 1) {
				
				throw new EvalException("Left element is not a MathematicalElement");
			}*/
			
			
			List<Double> data = (List<Double>)x.getValue();
			
			List<Double> rtn = new ArrayList<Double>(data.size());
			
			for(int ind = 0; ind < data.size(); ind ++)
			{
				double value;
				
				// EMA(9)=前一日EMA(9)*(9-1)/(9+1)+今日收盘价×2/(9+1)
				if(ind == 0)				
					value = 0;
				else
					value = rtn.get(ind-1)*(y.getRealValue()-1)/(y.getRealValue()+1);
				
				value = value + (data.get(ind)*2)/(y.getRealValue()+1);
				
				rtn.add(value);
 			}
			
			return new ObjectArrayMathematicalElement<Double>(rtn);
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
		return "ema";
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.core.Expression#getParameters()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List getParameters()
	{
		// TODO Auto-generated method stub
		List<String> parameter = new ArrayList<String>(2);
		parameter.add("x");
		parameter.add("y");
		//parameter.add("z");
		
		return parameter;
	}
}
