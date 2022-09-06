/**
 * 
 */
package com.exceoon.expression.formula.support;

import java.util.ArrayList;
import java.util.List;



/**
 * @author exceoonadmin
 *
 */
public class RelatedFieldContext extends ThreadLocal<List<RelatedFieldInfo>>
{
	private static RelatedFieldContext rfc = new RelatedFieldContext();
	
	public static RelatedFieldContext getContext()
	{
		return rfc;
	}
	
	public void addField(RelatedFieldInfo fieldInfo)
	{
		if(!get().contains(fieldInfo))
			get().add(fieldInfo);		
	}
	
	public List<RelatedFieldInfo> getRelatedFields()
	{
		List<RelatedFieldInfo> rtn = new ArrayList<RelatedFieldInfo>(get().size());
		
		rtn.addAll(get());
		
		return rtn;
	}
	
	public List<RelatedFieldInfo> initialValue()
	{
		return new ArrayList<RelatedFieldInfo>(10);
	}
	
	public void clear()
	{
		get().clear();
	}
}
