/**
 * 
 */
package com.exceoon.expression.formula;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.exceoon.expression.extend.function.ABSFunction;
import com.exceoon.expression.extend.function.AVGFunction;
import com.exceoon.expression.extend.function.EMAFunction;
import com.exceoon.expression.extend.function.EXPMEMAFunction;
import com.exceoon.expression.extend.function.GetFiledFunction;
import com.exceoon.expression.extend.function.IFFunction;
import com.exceoon.expression.extend.function.LENFunction;
import com.exceoon.expression.extend.function.MAFunction;
import com.exceoon.expression.extend.function.MAXFunction;
import com.exceoon.expression.extend.function.MINFunction;
import com.exceoon.expression.extend.function.POWERFunction;
import com.exceoon.expression.extend.function.REFFunction;
import com.exceoon.expression.extend.function.SUMFunction;
import com.exceoon.expression.extend.operator.OperatorArrayDivArrayRealImpl;
import com.exceoon.expression.extend.operator.OperatorArrayDivImpl;
import com.exceoon.expression.extend.operator.OperatorArrayDivRealArrayImpl;
import com.exceoon.expression.extend.operator.OperatorArrayMinusArrayRealImpl;
import com.exceoon.expression.extend.operator.OperatorArrayMinusImpl;
import com.exceoon.expression.extend.operator.OperatorArrayMinusRealArrayImpl;
import com.exceoon.expression.extend.operator.OperatorArrayMulArrayRealImpl;
import com.exceoon.expression.extend.operator.OperatorArrayMulImpl;
import com.exceoon.expression.extend.operator.OperatorArrayMulRealArrayImpl;
import com.exceoon.expression.extend.operator.OperatorArrayPlusArrayRealImpl;
import com.exceoon.expression.extend.operator.OperatorArrayPlusImpl;
import com.exceoon.expression.extend.operator.OperatorArrayPlusRealArrayImpl;
import com.exceoon.expression.extend.operator.OperatorConcatImpl;
import com.exceoon.expression.extend.operator.OperatorEqualImpl;
import com.exceoon.expression.extend.operator.OperatorLessImpl;
import com.exceoon.expression.extend.operator.OperatorMoreImpl;
import com.exceoon.expression.extend.operator.OperatorNotImpl;
import com.exceoon.expression.extend.operator.OperatorZhengChuImpl;
import com.exceoon.expression.formula.support.DefinedVariableExpressionElement;
import com.exceoon.expression.formula.support.FieldExpressionElement;

import fr.expression4j.basic.OperatorManager;
import fr.expression4j.basic.impl.ComplexOrRealExpressionElement;
import fr.expression4j.basic.impl.ConstantOrVariableExpressionElement;
import fr.expression4j.basic.impl.FunctionExpressionElement;
import fr.expression4j.basic.impl.ParenthesisExpressionElement;
import fr.expression4j.core.Catalog;
import fr.expression4j.core.Expression;
import fr.expression4j.core.ExpressionModel;
import fr.expression4j.core.Parameters;
import fr.expression4j.core.exception.ParsingException;
import fr.expression4j.factory.ExpressionFactory;
import fr.expression4j.factory.ExpressionModelFactory;
import fr.expression4j.factory.OperatorFactory;
import fr.expression4j.factory.OperatorManagerFactory;
import fr.expression4j.sample.custom.element.StringExpressionElement;

/**
 * @author geniusadmin
 *
 */
public class ExpressionUtils
{
	static
	{

		try
		{
			//create a specific expression model
			//ExpressionModel customOperatorExpressionModel = ExpressionModelFactory.createExpressionModel("CustomOperatorExpressionModel");
			ExpressionModel customOperatorExpressionModel = ExpressionModelFactory.getDefaultExpressionModel();
			
			customOperatorExpressionModel.addUnaryOperator(OperatorFactory.getOperator("UnaryMinus"));
			customOperatorExpressionModel.addUnaryOperator(OperatorFactory.getOperator("UnaryPlus"));
			
			customOperatorExpressionModel.addBinaryOperator(OperatorFactory.getOperator("Plus"),3);
			customOperatorExpressionModel.addBinaryOperator(OperatorFactory.getOperator("Minus"),3);
	
			customOperatorExpressionModel.addBinaryOperator(OperatorFactory.getOperator("Multiply"),4);
			customOperatorExpressionModel.addBinaryOperator(OperatorFactory.getOperator("Divide"),4);
			
			customOperatorExpressionModel.addBinaryOperator(OperatorFactory.getOperator("Pow"),5);
			
			
			customOperatorExpressionModel.addExpressionElement(new ComplexOrRealExpressionElement(),1);
			customOperatorExpressionModel.addExpressionElement(new FunctionExpressionElement(),2);
			customOperatorExpressionModel.addExpressionElement(new ConstantOrVariableExpressionElement(),3);
			customOperatorExpressionModel.addExpressionElement(new ParenthesisExpressionElement(),5);
			
			//OperatorManager supOperatorManager = OperatorManagerFactory.createOperatorManager("supOperatorManager");
			OperatorManager supOperatorManager = OperatorManagerFactory.getDefaultOperatorManager();
	
			//create the new operator			
			//add binary operator supported by the specific expression model
			customOperatorExpressionModel.addBinaryOperator(OperatorFactory.createOperator("mod","%",false),4);
			customOperatorExpressionModel.addBinaryOperator(OperatorFactory.createOperator("zhengchu","$",false),4);
			customOperatorExpressionModel.addBinaryOperator(OperatorFactory.createOperator("less","<",false),2);		
			customOperatorExpressionModel.addBinaryOperator(OperatorFactory.createOperator("more",">",false),2);
			customOperatorExpressionModel.addBinaryOperator(OperatorFactory.createOperator("equal","==",false),2);
			customOperatorExpressionModel.addBinaryOperator(OperatorFactory.createOperator("concat","||",false),2);				
			customOperatorExpressionModel.addUnaryOperator(OperatorFactory.createOperator("not","!", true));
			
			//add operator specific to previous model
			
			supOperatorManager.addOperatorImpl(new OperatorZhengChuImpl());
			supOperatorManager.addOperatorImpl(new OperatorLessImpl());
			supOperatorManager.addOperatorImpl(new OperatorMoreImpl());
			supOperatorManager.addOperatorImpl(new OperatorEqualImpl());
			supOperatorManager.addOperatorImpl(new OperatorNotImpl());
			supOperatorManager.addOperatorImpl(new OperatorConcatImpl());
			
			supOperatorManager.addOperatorImpl(new OperatorArrayMinusImpl());
			supOperatorManager.addOperatorImpl(new OperatorArrayMinusArrayRealImpl());
			supOperatorManager.addOperatorImpl(new OperatorArrayMinusRealArrayImpl());
			
			supOperatorManager.addOperatorImpl(new OperatorArrayMulImpl());
			supOperatorManager.addOperatorImpl(new OperatorArrayMulRealArrayImpl());
			supOperatorManager.addOperatorImpl(new OperatorArrayMulArrayRealImpl());
			
			supOperatorManager.addOperatorImpl(new OperatorArrayDivImpl());
			supOperatorManager.addOperatorImpl(new OperatorArrayDivArrayRealImpl());
			supOperatorManager.addOperatorImpl(new OperatorArrayDivRealArrayImpl());
			
			supOperatorManager.addOperatorImpl(new OperatorArrayPlusImpl());
			supOperatorManager.addOperatorImpl(new OperatorArrayPlusArrayRealImpl());
			supOperatorManager.addOperatorImpl(new OperatorArrayPlusRealArrayImpl());
			
			Catalog catalog = ExpressionFactory.getCatalog();
			catalog.addExpression(new GetFiledFunction());
			catalog.addExpression(new EMAFunction());
			catalog.addExpression(new MAFunction());
			catalog.addExpression(new AVGFunction());
			catalog.addExpression(new MAXFunction());
			catalog.addExpression(new MINFunction());
			catalog.addExpression(new ABSFunction());
			catalog.addExpression(new IFFunction());
			catalog.addExpression(new REFFunction());
			catalog.addExpression(new EXPMEMAFunction());
			catalog.addExpression(new SUMFunction());
			catalog.addExpression(new LENFunction());
			catalog.addExpression(new POWERFunction());
			
			customOperatorExpressionModel.addExpressionElement(new StringExpressionElement(),1);
			
			customOperatorExpressionModel.addExpressionElement(new FieldExpressionElement(),4);
			customOperatorExpressionModel.addExpressionElement(new DefinedVariableExpressionElement(),4);
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static Parameters createParameters()
	{
		return ExpressionFactory.createParameters();
	}
	
	public static Expression createExpression(String expression)
		throws ParsingException
	{
		return ExpressionFactory.createExpression(expression);
	}
	
	public static <T>  List<T> array2list(T[] obj)
	{
		return Arrays.asList(obj);
	}
	
	public static <T> List<Double> array2Doublelist(T[] obj)
	{
		ArrayList<Double> rtn = new ArrayList<Double>(obj.length);
		
		for(T o:obj)
		{
			rtn.add(Double.parseDouble(String.valueOf(o)));
		}
		
		return rtn;
	}
}
