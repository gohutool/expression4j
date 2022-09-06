/**
 * 
 */
package com.exceoon.expression.formula;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author geniusadmin
 *
 */
public class FormulaBuilder
{
	/**
	 * Gets the formula with your string and variable.
	 * @param str
	 * @param varNames
	 * @return
	 */
	public static Formula constructFormula(String str, String[] varNames) throws FormulaParsingExcption
	{
		str = str.toLowerCase();
		
		// TODO add cache support
		// if str is exists in pool, get the formula from pool, otherwise construct and parse them and put into pool
		Formula f = new Formula(str, varNames);		
		f.parseExpression();
		
		return f;
	}
	
	private static String[] parseAgrument(String str)
	{
		Pattern pattern = Pattern.compile("#([a-zA-Z0-9_]+)#");
		
		Matcher matcher = pattern.matcher(str);
		Map<String, String> keys = new HashMap<String, String>();
		
		while(matcher.find()){    
			keys.put(matcher.group(1), null);
		}
		
		if(keys.size()==0)
			return new String[]{};
		
		List<String> rtn = new ArrayList<String>(keys.keySet());
		
		return rtn.toArray(new String[rtn.size()]);
	}
	
	public static void main(String[] args)
	{
		args = parseAgrument("#ABC# #DFG#");
		System.out.println(args);
	}
	
	public static Formula constructFormula(String str) throws FormulaParsingExcption
	{
		str = str.toLowerCase();
		// TODO add cache support
		// if str is exists in pool, get the formula from pool, otherwise construct and parse them and put into pool
		String[] vs = parseAgrument(str);
		
		Formula f = null;
		
		if(vs==null || vs.length == 0)
		{
			f = new Formula(str, vs);	
		}
		else
		{
			for(String one : vs)
			{
				str = str.replaceAll("#"+one+"#", one);
			}
			
			f = new Formula(str, vs);	
		}
		 	
		f.parseExpression();
		
		return f;
	}
}
