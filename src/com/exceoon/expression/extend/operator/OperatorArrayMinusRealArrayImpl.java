/**
 * 
 */
package com.exceoon.expression.extend.operator;

import java.util.ArrayList;
import java.util.List;

import com.exceoon.expression.extend.ObjectArrayMathematicalElement;

import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.Operator;
import fr.expression4j.basic.OperatorImpl;
import fr.expression4j.core.exception.EvalException;

/**
 * @author exceoonadmin
 *
 */
public class OperatorArrayMinusRealArrayImpl implements OperatorImpl
{

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.OperatorImpl#compute(fr.expression4j.basic.MathematicalElement, fr.expression4j.basic.MathematicalElement)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public MathematicalElement compute(MathematicalElement leftElement,
	        MathematicalElement rightElement) throws EvalException
	{
		if (leftElement.getType() != 1) {
			throw new EvalException("Left element is not a ObjectArrayMathematicalElement");
		}
		
		if (rightElement.getType() != ObjectArrayMathematicalElement.TYPE) {
			throw new EvalException("Right element is not a ObjectArrayMathematicalElement");
		}

		List<Double> right = (List<Double>)rightElement.getValue();
		double left = (Double)leftElement.getValue();
		int size = right.size();
		List<Double> rtn = new ArrayList<Double>(size);
		
		for(int ind = 0 ; ind < size; ind ++)
		{
			double a;			
			a = right.get(ind);			
			rtn.add(left - a);
		}
		
		return new ObjectArrayMathematicalElement<Double>(rtn);
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.OperatorImpl#getLeftOperandeType()
	 */
	@Override
	public int getLeftOperandeType()
	{
		// TODO Auto-generated method stub
		return 1;
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.OperatorImpl#getOperatorName()
	 */
	@Override
	public String getOperatorName()
	{
		// TODO Auto-generated method stub
		return Operator.OPERATOR_MINUS;
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.OperatorImpl#getRightOperandeType()
	 */
	@Override
	public int getRightOperandeType()
	{
		// TODO Auto-generated method stub
		return ObjectArrayMathematicalElement.TYPE;
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.OperatorImpl#isUnaryOperator()
	 */
	@Override
	public boolean isUnaryOperator()
	{
		// TODO Auto-generated method stub
		return false;
	}

}
