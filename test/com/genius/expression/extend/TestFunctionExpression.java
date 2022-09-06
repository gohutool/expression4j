package com.genius.expression.extend;

import java.util.ArrayList;
import java.util.List;

import com.exceoon.expression.extend.ObjectArrayMathematicalElement;
import com.exceoon.expression.extend.function.AVGFunction;
import com.exceoon.expression.extend.function.EMAFunction;
import com.exceoon.expression.extend.function.GetFiledFunction;
import com.exceoon.expression.extend.function.MAFunction;
import com.exceoon.expression.extend.function.MAXFunction;
import com.exceoon.expression.extend.function.MINFunction;
import com.exceoon.expression.extend.operator.OperatorArrayDivRealArrayImpl;
import com.exceoon.expression.extend.operator.OperatorArrayMinusImpl;
import com.exceoon.expression.extend.operator.OperatorArrayMulImpl;
import com.exceoon.expression.extend.operator.OperatorArrayMulRealArrayImpl;
import com.exceoon.expression.extend.operator.OperatorConcatImpl;
import com.exceoon.expression.extend.operator.OperatorEqualImpl;
import com.exceoon.expression.extend.operator.OperatorLessImpl;
import com.exceoon.expression.extend.operator.OperatorMoreImpl;
import com.exceoon.expression.extend.operator.OperatorNotImpl;
import com.exceoon.expression.extend.operator.OperatorZhengChuImpl;
import com.exceoon.expression.formula.support.FieldExpressionElement;

import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.OperatorManager;
import fr.expression4j.basic.impl.ComplexOrRealExpressionElement;
import fr.expression4j.basic.impl.ConstantOrVariableExpressionElement;
import fr.expression4j.basic.impl.FunctionExpressionElement;
import fr.expression4j.basic.impl.ParenthesisExpressionElement;
import fr.expression4j.core.Catalog;
import fr.expression4j.core.Expression;
import fr.expression4j.core.ExpressionModel;
import fr.expression4j.core.Parameters;
import fr.expression4j.factory.ExpressionFactory;
import fr.expression4j.factory.ExpressionModelFactory;
import fr.expression4j.factory.NumberFactory;
import fr.expression4j.factory.OperatorFactory;
import fr.expression4j.factory.OperatorManagerFactory;
import fr.expression4j.sample.custom.element.StringExpressionElement;
import fr.expression4j.sample.custom.element.StringMathematicalElement;
import junit.framework.TestCase;

public class TestFunctionExpression extends TestCase
{
	public void testSample()
	{
		String expressionString = "foo(y)=abs(2*-y)";
		String expressionString2 = "f(y,x)=foo(x)+foo(y)-foo(y-1)";
		String expressionString3 = "f2(y,x)=f(abs(x),abs(x-1))+f(y,abs(y-1))";
		
		try
		{
			
			Expression expression = ExpressionFactory.createExpression(expressionString);						
			Catalog catalog = ExpressionFactory.createCatalog("Catalog");
			catalog.addExpression(expression);
			
			Expression expression2 = ExpressionFactory.createExpression(expressionString2, catalog);
			catalog.addExpression(expression2);
			
			Expression expression3 = ExpressionFactory.createExpression(expressionString3, catalog);
			Parameters parameters = ExpressionFactory.createParameters();
			parameters.addParameter("x",NumberFactory.createReal(5));
			parameters.addParameter("y",NumberFactory.createReal(-2));
			
			String xpress = expressionString3;
			Expression tempexpression = expression3;
			
			while(!xpress.trim().equalsIgnoreCase(tempexpression.toString()))
			{
				System.out.println("***  " + xpress + " : " + tempexpression.getExpressionModel());
				
				tempexpression = ExpressionFactory.createExpression(xpress, catalog);
				xpress = tempexpression.toString();
			}
			
			System.out.println(xpress);
			
			MathematicalElement obj = expression3.evaluate(parameters);
			
			System.out.println(obj.getRealValue());
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private List<Double> getData(int n, int basic, int right)
	{
		List<Double> data = new ArrayList<Double>(n);
		
		for(int ind = 0 ; ind < n ; ind ++)
		{
			data.add(Math.random()*right+basic);
		}
		
		return data;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void testEma()
	{
		String expressionString = "f(x,y)=ema(x,y)";
		String expressionString2 = "dif(x,a,b)=ema(x,a)-ema(x,b)";
		String expressionString3 = "f(x,a,b)=avg(ema(x,a)-ema(x,b))";
		String expressionString4 = "dif()=ema(close,12)-ema(close,26)";
		String expressionString5 = "dea()=ema(dif(),9)";
		String expressionString6 = "macd()=(dif()-dea())*2";
		String expressionString7 = "dif2()=ma(close,10)-ma(close,50)";
		String expressionString8 = "ama()=max(ma(dif2(),10),ma(dif2(),20))";
		
		try
		{
			
			addCustomizedOpertor();
			Expression expression = ExpressionFactory.createExpression(expressionString);						
			Expression expression2 = ExpressionFactory.createExpression(expressionString2);
			Expression expression3 = ExpressionFactory.createExpression(expressionString3);
			Expression expression4 = ExpressionFactory.createExpression(expressionString4);
			ExpressionFactory.getCatalog().addExpression(expression4);
			
			Expression expression5 = ExpressionFactory.createExpression(expressionString5);
			ExpressionFactory.getCatalog().addExpression(expression5);
			
			Expression expression6 = ExpressionFactory.createExpression(expressionString6);			
			
			Expression expression7 = ExpressionFactory.createExpression(expressionString7);
			ExpressionFactory.getCatalog().addExpression(expression7);
			
			Expression expression8 = ExpressionFactory.createExpression(expressionString8);			
			
			Parameters parameters = ExpressionFactory.createParameters();
			parameters.addParameter("x",new ObjectArrayMathematicalElement(getData(80, 20, 10)));			
			parameters.addParameter("y",NumberFactory.createReal(12));
			
			System.out.println(expression.evaluate(parameters).getValue());			
			
			parameters.addParameter("y",NumberFactory.createReal(26));
			
			System.out.println(expression.evaluate(parameters).getValue());
			
			parameters.addParameter("a",NumberFactory.createReal(12));
			parameters.addParameter("b",NumberFactory.createReal(24));
			MathematicalElement rtn = expression2.evaluate(parameters);
			System.out.println(rtn.getValue());
			
			parameters.addParameter("x",rtn);
			parameters.addParameter("y",NumberFactory.createReal(9));
			System.out.println(expression.evaluate(parameters).getValue());			
			
			parameters.addParameter("x",rtn);
			parameters.addParameter("y",NumberFactory.createReal(9));
			System.out.println(expression3.evaluate(parameters).getValue());
			
			System.out.println("dif");
			System.out.println(expression4.evaluate(parameters).getValue());
			System.out.println(expression4.evaluate(parameters).getValue());
			
			System.out.println("dea");
			System.out.println(expression5.evaluate(parameters).getValue());
			System.out.println(expression5.evaluate(parameters).getValue());
			
			System.out.println("macd");
			System.out.println(expression6.evaluate(parameters).getValue());
			System.out.println(expression6.evaluate(parameters).getValue());
			
			System.out.println("dif2");
			System.out.println(expression7.evaluate(parameters).getValue());
			System.out.println(expression7.evaluate(parameters).getValue());
			
			System.out.println("ama");
			System.out.println(expression8.evaluate(parameters).getValue());
			System.out.println(expression8.evaluate(parameters).getValue());
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void testString()
	{
		String expressionString = "f1(x)=get(x)+get('testsss')";
		String expressionString1 = "f2(x,y,z)=get(x)-y-get(z||x)";
		
		try
		{
			
			addCustomizedOpertor();
			Expression expression = ExpressionFactory.createExpression(expressionString);						
			
			Parameters parameters = ExpressionFactory.createParameters();
			parameters.addParameter("x",new StringMathematicalElement("hello"));
			parameters.addParameter("z",new StringMathematicalElement("hello world"));
			parameters.addParameter("y",NumberFactory.createReal(-2));
			
			MathematicalElement obj = expression.evaluate(parameters);
			
			System.out.println(obj.getValue());
			
			
			expression = ExpressionFactory.createExpression(expressionString1);			
			obj = expression.evaluate(parameters);			
			System.out.println(obj.getValue());
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void testLess()
	{
		String expressionString = "f1(x,y)=x+y";
		String expressionString1 = "f2(x,y)=x-y";
		String a = "f1(x,f2(x,y))";
		String b = "f2(x,y)+5";
		
		//String expressionString2 = "foo(x,y)=!(" + a + "==" + b +")";
		String expressionString2 = "foo(x,y)=12$5";
		String a1 = "f(x,y)="+a;
		String b1 = "f(x,y)="+b;
		
		try
		{
			addCustomizedOpertor();
			Expression expression = ExpressionFactory.createExpression(expressionString);						
			
			Parameters parameters = ExpressionFactory.createParameters();
			parameters.addParameter("x",NumberFactory.createReal(5));
			parameters.addParameter("y",NumberFactory.createReal(-2));
			
			MathematicalElement obj = expression.evaluate(parameters);
			
			System.out.println(obj.getValue());
			
			expression = ExpressionFactory.createExpression(expressionString);
			Catalog catalog = ExpressionFactory.getCatalog();
			catalog.addExpression(expression);
			expression = ExpressionFactory.createExpression(expressionString1);
			catalog.addExpression(expression);
			
			expression = ExpressionFactory.createExpression(expressionString2);			
			obj = expression.evaluate(parameters);			
			System.out.println(obj.getValue());
			
			System.out.println(ExpressionFactory.createExpression(a1).evaluate(parameters).getValue() + " == " + ExpressionFactory.createExpression(b1).evaluate(parameters).getValue());
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private void addCustomizedOpertor()
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
		supOperatorManager.addOperatorImpl(new OperatorArrayMulImpl());
		supOperatorManager.addOperatorImpl(new OperatorArrayMulRealArrayImpl());
		
		supOperatorManager.addOperatorImpl(new OperatorArrayDivRealArrayImpl());
		
		Catalog catalog = ExpressionFactory.getCatalog();
		catalog.addExpression(new GetFiledFunction());
		catalog.addExpression(new EMAFunction());
		catalog.addExpression(new MAFunction());
		catalog.addExpression(new AVGFunction());
		catalog.addExpression(new MAXFunction());
		catalog.addExpression(new MINFunction());
		
		customOperatorExpressionModel.addExpressionElement(new StringExpressionElement(),1);
		
		customOperatorExpressionModel.addExpressionElement(new FieldExpressionElement(),4);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
