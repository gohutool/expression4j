/**
 * 
 */
package com.exceoon.expression.extend.operator;

import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.OperatorImpl;
import fr.expression4j.core.exception.EvalException;
import fr.expression4j.sample.custom.element.StringMathematicalElement;

/**
 * @author geniusadmin
 *
 */
public class OperatorConcatImpl implements OperatorImpl
{

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.OperatorImpl#compute(fr.expression4j.basic.MathematicalElement, fr.expression4j.basic.MathematicalElement)
	 */
	@Override
	public MathematicalElement compute(MathematicalElement leftElement,
	        MathematicalElement rightElement) throws EvalException
	{
		if (leftElement.getType() != 5) {
			throw new EvalException("Left element is not a StringMathematicalElement");
		}
		
		if (rightElement.getType() != 5) {
			throw new EvalException("Right element is not a StringMathematicalElement");
		}

		StringMathematicalElement result = new StringMathematicalElement(String.valueOf(leftElement.getValue())+String.valueOf(rightElement.getValue()));
		return result;
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.OperatorImpl#getLeftOperandeType()
	 */
	@Override
	public int getLeftOperandeType()
	{
		// TODO Auto-generated method stub
		return 5;
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.OperatorImpl#getOperatorName()
	 */
	@Override
	public String getOperatorName()
	{
		// TODO Auto-generated method stub
		return "concat";
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.OperatorImpl#getRightOperandeType()
	 */
	@Override
	public int getRightOperandeType()
	{
		// TODO Auto-generated method stub
		return 5;
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
