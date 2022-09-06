/**
 * 
 */
package com.exceoon.expression.formula.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author geniusadmin
 *
 */
public class VariableContext extends ThreadLocal<Map<String, Object>>
{	
	private static VariableContext context = new VariableContext();
	
	public static VariableContext getContext()
	{
		return context;
	}
	
	public List<String> getVariableNames()
	{
		return new ArrayList<String>(get().keySet());
	}
	
	public Object getVariable(String variableName)
	{
		return get().get(variableName);
	}
	
	public void addVariable(String variableName, Object variable)
	{
		get().put(variableName.toLowerCase(), variable);
	}
	
	public Map<String, Object> initialValue()
	{
		return new HashMap<String, Object>(10);
	}
	
	public void clear()
	{
		get().clear();
	}
	
	public boolean isVariableDefined(String variableName)
	{
		return get().containsKey(variableName);
	}
	
	public void addVariableDefinition(String variableName)
	{
		this.addVariable(variableName, null);
	}
}
