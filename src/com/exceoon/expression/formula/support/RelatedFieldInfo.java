/**
 * 
 */
package com.exceoon.expression.formula.support;

/**
 * @author exceoonadmin
 *
 */
public class RelatedFieldInfo
{
	public String fieldname;
	public int fetchCount;
	public int fetchType;
	
	public String toString()
	{
		return fieldname + ":" + fetchCount + ":" + fetchType;
	}
	
	public boolean equals(Object obj)
	{
		return toString().equals(obj.toString());
	}
}
