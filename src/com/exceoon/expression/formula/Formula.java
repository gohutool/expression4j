package com.exceoon.expression.formula;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.exceoon.expression.formula.support.DataFieldValueContext;
import com.exceoon.expression.formula.support.RelatedFieldContext;
import com.exceoon.expression.formula.support.RelatedFieldInfo;
import com.exceoon.expression.formula.support.VariableContext;


import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.core.Expression;

public class Formula
{
	private static final Logger logger = Logger.getLogger(Formula.class);
	
	private String v;
	private String[] varnames;
	
	private Map<String, Expression> returns = new HashMap<String, Expression>();
	private Map<String,Expression> variables = new HashMap<String, Expression>();	
	private List<String> variablenames = new ArrayList<String>();	
	private List<RelatedFieldInfo> relatedFieldInfo;
	
	Formula(String v, String[] varnames)
	{
		this.v = v;	
		this.varnames = varnames;
	}
	
	void parseExpression() throws FormulaParsingExcption
	{
		VariableContext.getContext().clear();
		RelatedFieldContext.getContext().clear();
		
		if(varnames != null)
		{
			for(String var:varnames)
			{
				VariableContext.getContext().addVariableDefinition(var);
			}
		}
		
		try
		{
			String[] str = v.split(";");
			
			for(int ind = 0; ind < str.length ; ind ++)
			{
				if(str[ind].trim().length()!=0)
				{
					if(logger.isDebugEnabled())
						logger.debug(str[ind]);
					
					Expression exp = null;
					String name = null;
					String expStr = null;
					
					if(str[ind].indexOf(":=")<=0)
					{
						if(str[ind].indexOf(":")>0)
						{
							name = str[ind].substring(0, str[ind].indexOf(":"));
							int endindx = str[ind].indexOf("::");
							expStr = str[ind].substring(str[ind].indexOf(":")+1, endindx>0?endindx:(str[ind].length()));
							
							exp = ExpressionUtils.createExpression(name+"()="+expStr);
							returns.put(name, exp);
							// TODO support other properties for example COLORSTICK color
						}
					}
					else
					{
						name = str[ind].substring(0, str[ind].indexOf(":="));
						expStr = str[ind].substring(str[ind].indexOf(":=")+2, str[ind].length());
						
						exp = ExpressionUtils.createExpression(name+"()="+expStr);		
					}
					
					if(exp!=null)
					{
						// it is a intermediate variable for result.
						VariableContext.getContext().addVariableDefinition(name);
						
						variablenames.add(name);
						variables.put(name, exp);
					}
				}
			}
			
			relatedFieldInfo = RelatedFieldContext.getContext().getRelatedFields();
			
			if(relatedFieldInfo==null)
				relatedFieldInfo = new ArrayList<RelatedFieldInfo>(0);
			
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new IllegalStateException("parse expression error.",e);
		}	
		finally
		{
			VariableContext.getContext().clear();
			RelatedFieldContext.getContext().clear();
		}
	}
	
	public FormulaResult eval(FormulaParameters p) throws FormulaEvalException
	{
		FormulaResult result = new FormulaResult();
		DataFieldValueContext.getContext().clear();
		
		for(String varExp:variablenames)
		{
			try
			{
				MathematicalElement m = this.variables.get(varExp).evaluate(p._getParameters());
				
				p._getParameters().addParameter(varExp, m);
				
				if(this.returns.containsKey(varExp))
				{
					result._add(varExp, m.getValue());
				}
			}
			catch (Exception e) {
				// TODO: handle exception
				throw new FormulaEvalException("evaluation error", e);
			}
		}
		
		DataFieldValueContext.getContext().clear();
		
		return result;
	}
	
	public List<RelatedFieldInfo> getRelatedFields()
	{
		return relatedFieldInfo;
	}
}
