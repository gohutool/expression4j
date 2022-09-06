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
import fr.expression4j.factory.NumberFactory;

/**
 * @author geniusadmin
 *
 */
public class MINFunction extends EconomicFunction
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
			
			if(x.getType() == ObjectArrayMathematicalElement.TYPE && y.getType() ==  ObjectArrayMathematicalElement.TYPE)
			{
				List<Double> data1 = null;
				List<Double> data2 = null;
				
				data1 = (List<Double>)x.getValue();
				data2 = (List<Double>)x.getValue();				
				
				List<Double> rtn = new ArrayList<Double>(data1.size()>data2.size()?data1.size():data2.size());
				
				for(int ind = 0 ; ind < data1.size() || ind < data2.size(); ind ++)
				{
					double a,b;
					double value;
					
					if(ind<data1.size())
					{
						a = data1.get(ind);
						
						if(ind < data2.size())
						{
							b = data2.get(ind);
							value = a<b?a:b;
						}
						else
							value = a;						
					}
					else
					{
						value = data2.get(ind);
					}
					
					rtn.add(value);
				}
				
				return new ObjectArrayMathematicalElement<Double>(rtn);
			}
			else if(x.getType()!= 1 && y.getType()!= 1)
			{
				return NumberFactory.createReal(x.getRealValue()<y.getRealValue()?x.getRealValue():y.getRealValue());
			}
			else
			{
				throw new EvalException("Left element is not a ObjectArrayMathematicalElement");
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
		return "min";
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
		return parameter;
	}
}
