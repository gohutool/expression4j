/**
 * 
 */
package com.genius.expression.formula;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.exceoon.expression.formula.Formula;
import com.exceoon.expression.formula.FormulaBuilder;
import com.exceoon.expression.formula.FormulaParameters;
import com.exceoon.expression.formula.FormulaResult;
import com.exceoon.expression.formula.support.RelatedFieldInfo;
import com.exceoon.expression.formula.support.VariableContext;


import junit.framework.TestCase;

/**
 * @author geniusadmin
 *
 */
public class TestFormual extends TestCase
{
	static final String txt = "DIF:EMA(CLOSE,SHORT)-EMA(OPEN,LONG);"
		+ "DEA:EMA(DIF,MID);" 
		+ "MACD:2*(DIF-DEA)::COLORSTICK;";

	static final String[] defs = new String[]{"SHORT", "LONG", "MID"};
	
	static final String txt2 = 
		"TR:=EXPMEMA(MAX(MAX(HIGH-LOW,ABS(HIGH-REF(CLOSE,1))),ABS(REF(CLOSE,1)-LOW)),N);"
		+ "HD :=HIGH-REF(HIGH,1);" 
		+ "LD :=REF(LOW,1)-LOW;" 
		+ "DMP:=EXPMEMA(IF(HD>0&&HD>LD,HD,0),N);" 
		+ "DMM:=EXPMEMA(IF(LD>0&&LD>HD,LD,0),N);" 
		+ "PDI: DMP*100/TR;"
		+ "MDI: DMM*100/TR;" 
		+ "ADX: EXPMEMA(ABS(MDI-PDI)/(MDI+PDI)*100,M);" 
		+ "ADXR:EXPMEMA(ADX,M);";
	
	static final String[] defs2 = new String[]{"SHORT", "LONG"};

	public void testParser()
	{
		try
		{
			Formula f = null;
			
			f = FormulaBuilder.constructFormula(txt, defs);		
			System.out.println(f);
			
			List<RelatedFieldInfo> fs = f.getRelatedFields();
			System.out.println(fs);
			
			FormulaParameters fp = new FormulaParameters();
			fp.addParameter("short", 12);
			fp.addParameter("long", 26);
			fp.addParameter("mid", 9);
			
			FormulaResult fr = f.eval(fp);
			
			Map<String, Object> results = fr.getResult();
			
			System.out.println(results);
			
			//f = FormulaBuilder.constructFormula(txt);		
			//System.out.println(f);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
	}
	
	public void testConcurrent()
	{
		for(int ind = 0 ; ind < 10; ind ++)
		{
			new Thread(new Runnable()
			{
				public void sleep(long n)
				{
					try
					{Thread.sleep(n);}
					catch (Exception e) {e.printStackTrace();}
				}
				
				@Override
				public void run()
				{
					// TODO Auto-generated method stub
					VariableContext vc = VariableContext.getContext();
					
					vc.addVariable("1", Thread.currentThread());
					sleep(200);					
					System.out.println(Thread.currentThread() + " : " + vc.get());
					vc.addVariable("2", Thread.currentThread().getName());					
					sleep(200);					
					System.out.println(Thread.currentThread() + " : " + vc.get());
					vc.addVariable("3", System.currentTimeMillis());
					sleep(200);	
					System.out.println(Thread.currentThread() + " : " + vc.get());
					
					System.out.println(Thread.currentThread() + " over");
				}
			}).start();
		}
		
		try
		{
			Thread.sleep(20*1000);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	 
	public void testConcurrent4Formula()
	{
		try
		{
			final Formula f = FormulaBuilder.constructFormula(txt, defs);
			final int calCount = 10;
			
			int count = 1;
			
			List<RelatedFieldInfo> fs = f.getRelatedFields();
			System.out.println(fs);
			
			final AtomicInteger counter = new AtomicInteger(0);
			Thread[] all = new Thread[count];
			
			for(int ind = 0 ; ind < count; ind ++)
			{
				all[ind] = new Thread(new Runnable()
				{
					@Override
					public void run()
					{
						// TODO Auto-generated method stub
						try
						{
							for(int ind  = 0 ; ind < calCount ; ind ++)
							{		
								/*
								int a = (100*30-2312+123)*12312*(123123+12312-123132/123)*123123/1231/1231/132123/1231/123;
								int b = (100*30-2312+123)*12312*(123123+12312-123132/123)*123123/1231/1231/132123/1231/123;
								int c = a+b-123123123;
								int d = -c-2*a+b;
								//System.out.println(results.size());
								*/
								
								FormulaParameters fp = new FormulaParameters();
								fp.addParameter("short", 12);
								fp.addParameter("long", 26);
								fp.addParameter("mid", 9);
								
								@SuppressWarnings("unused")
								FormulaResult fr = f.eval(fp);
								
								//Map<String, Object> results = fr.getResult();
								
								//System.out.println(results.size());
							}
							
							counter.getAndAdd(1);
						}
						catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}					
					}
				});
			}
			
			long start = System.currentTimeMillis();
			
			for(Thread one : all)
				one.start();
			
			while(counter.get()<count)
			{
				TimeUnit.MILLISECONDS.sleep(200);
			}
			
			long end = System.currentTimeMillis();
			
			System.out.println("Thread count is " + count + ", and every thread count " + calCount+ " times and , cost time " + (end-start) + "ms.");
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
