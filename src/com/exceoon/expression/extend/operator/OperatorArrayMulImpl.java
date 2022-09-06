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
public class OperatorArrayMulImpl implements OperatorImpl
{

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.OperatorImpl#compute(fr.expression4j.basic.MathematicalElement, fr.expression4j.basic.MathematicalElement)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public MathematicalElement compute(MathematicalElement leftElement,
	        MathematicalElement rightElement) throws EvalException
	{
		if (leftElement.getType() != ObjectArrayMathematicalElement.TYPE) {
			throw new EvalException("Left element is not a ObjectArrayMathematicalElement");
		}
		
		if (rightElement.getType() != ObjectArrayMathematicalElement.TYPE) {
			throw new EvalException("Right element is not a ObjectArrayMathematicalElement");
		}

		List<Double> left = (List<Double>)leftElement.getValue();
		List<Double> right = (List<Double>)rightElement.getValue();
		List<Double> rtn = new ArrayList<Double>(left.size()>right.size()?left.size():right.size());
		
		for(int ind = 0 ; ind < left.size() || ind < right.size(); ind ++)
		{
			double a,b;
			
			if(ind<left.size())
				a = left.get(ind);
			else
				a = 0;
			
			if(ind < right.size())
				b = right.get(ind);
			else
				b = 0;
			
			rtn.add(a * b);
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
		return ObjectArrayMathematicalElement.TYPE;
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.OperatorImpl#getOperatorName()
	 */
	@Override
	public String getOperatorName()
	{
		// TODO Auto-generated method stub
		return Operator.OPERATOR_MULTIPLY;
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
