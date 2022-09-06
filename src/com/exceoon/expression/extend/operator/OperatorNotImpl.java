/**
 * 
 */
package com.exceoon.expression.extend.operator;

import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.OperatorImpl;
import fr.expression4j.core.exception.EvalException;
import fr.expression4j.sample.custom.operator.BooleanMathematicalElement;

/**
 * @author geniusadmin
 *
 */
public class OperatorNotImpl implements OperatorImpl
{

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.OperatorImpl#compute(fr.expression4j.basic.MathematicalElement, fr.expression4j.basic.MathematicalElement)
	 */
	@Override
	public MathematicalElement compute(MathematicalElement leftElement,
	        MathematicalElement rightElement) throws EvalException
	{
		
		if (leftElement.getType() != 6) {
			throw new EvalException("Right element is not a BooleanMathematicalElement");
		}

		BooleanMathematicalElement result = new BooleanMathematicalElement(!((Boolean)leftElement.getValue()).booleanValue());
		
		return result;
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.OperatorImpl#getLeftOperandeType()
	 */
	@Override
	public int getLeftOperandeType()
	{
		// TODO Auto-generated method stub
		return 6;
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.OperatorImpl#getOperatorName()
	 */
	@Override
	public String getOperatorName()
	{
		// TODO Auto-generated method stub
		return "not";
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.OperatorImpl#getRightOperandeType()
	 */
	@Override
	public int getRightOperandeType()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.OperatorImpl#isUnaryOperator()
	 */
	@Override
	public boolean isUnaryOperator()
	{
		// TODO Auto-generated method stub
		return true;
	}

}
