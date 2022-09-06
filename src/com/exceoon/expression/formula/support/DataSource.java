/**
 * 
 */
package com.exceoon.expression.formula.support;


import org.apache.log4j.Logger;

import com.exceoon.expression.extend.ObjectArrayMathematicalElement;

/**
 * @author exceoonadmin
 *
 */
public class DataSource
{
	private static final Logger logger = Logger.getLogger(DataSource.class);
	
	private static DataFetcher dataF;
	
	static
	{
		try
		{
			dataF = (DataFetcher)Class.forName(Configuration.getConfiguration().getDataFetcher()).newInstance();
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new UnsupportedOperationException("Not supprt this DataFetcher", e);
		}
	}
	
	public static boolean isDefined(String fieldname)
	{
		if(logger.isDebugEnabled())
			logger.debug("Check field defined "+ fieldname);
		
		if(!dataF.needFetch(fieldname))			
			return false;
		
		RelatedFieldInfo finfo = new RelatedFieldInfo();
		
		finfo.fieldname = fieldname;
		finfo.fetchCount = 100;
		finfo.fetchType = 1;
		
		RelatedFieldContext.getContext().addField(finfo);
		
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public static ObjectArrayMathematicalElement<Double> getObjectValues(String field, int n)
	{
		DataFieldValueContext dfvc = DataFieldValueContext.getContext();
		
		if(dfvc.containFieldValue(field))
			return (ObjectArrayMathematicalElement<Double>)dfvc.getFieldValue(field);
		else
		{
			@SuppressWarnings("rawtypes")
			ObjectArrayMathematicalElement<Double> rtn = new ObjectArrayMathematicalElement(dataF.getDatas(field.toLowerCase(), 0, 100));
			
			dfvc.addFieldValue(field, rtn);
			
			return rtn;
		}
	}
}
