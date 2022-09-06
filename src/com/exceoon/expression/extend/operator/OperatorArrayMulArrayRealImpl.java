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
public class OperatorArrayMulArrayRealImpl implements OperatorImpl
{

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.OperatorImpl#compute(fr.expression4j.basic.MathematicalElement, fr.expression4j.basic.MathematicalElement)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public MathematicalElement compute(MathematicalElement leftElement,
	        MathematicalElement rightElement) throws EvalException
	{
		if (rightElement.getType() != ObjectArrayMathematicalElement.TYPE) {
			throw new EvalException("Right element is not a ObjectArrayMathematicalElement");
		}
		
		if (leftElement.getType() != 1) {
			throw new EvalException("Left element is not a MathematicalElement");
		}

		List<Double>  left = (List<Double>)leftElement.getValue();
		double right = rightElement.getRealValue();
		
		List<Double> rtn = new ArrayList<Double>(left.size());
		
		for(int ind = 0 ; ind < left.size() ; ind ++)
		{
			rtn.add(left.get(ind)*right);
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
		return 1;
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
