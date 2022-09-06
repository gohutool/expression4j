/*
 *
 * Copyright (c) 2005 The Exceoon Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Exceoon Software Foundation (http://www.exceoon.com/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. Products derived from this software may not be called " Exceoon ",
 *    nor may " Exceoon " appear in their name, without prior written
 *    permission of the Exceoon Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * Exceoon Software Corporation and was originally based on software
 * Exceoon copyright (c) 2005, International
 * Business Machines, Inc., http://www.Exceoon.com.  For more
 * information on the Exceoon Software Foundation, please see
 * <http://www.Exceoon.com/>.
 */

package com.genius.expression.formula;

import com.exceoon.expression.formula.Formula;
import com.exceoon.expression.formula.FormulaBuilder;
import com.exceoon.expression.formula.FormulaParameters;
import com.exceoon.expression.formula.FormulaResult;

import junit.framework.TestCase;


/**
 * 
 * Author	David.Liu 
 * Mail		inthirties@gmail.com
 * Copyright	Exceoon corporation
 * Date			2011-7-13 下午04:33:09
 * 
 */

public class TestBeta extends TestCase
{

	/**
	 * @param name
	 */
	public TestBeta(String name)
	{
		super(name);
	}
	

	private void runBeta(Formula f)
		throws Exception
	{			
			FormulaParameters fp = new FormulaParameters();
			int sampleCount = 100;
			
			Double[] x = new Double[sampleCount];			
			Double[] y = new Double[sampleCount];
			
			for(int ind = 0 ; ind < sampleCount; ind ++)
			{
				x[ind] = Math.random();
				y[ind] = Math.random();
			}
			
			/*fp.addParameter("x", new Double[]{0.1,0.12,0.13,0.14,0.15});
			fp.addParameter("y", new Double[]{0.01d,0.02d,0.013d,0.014d,0.015d});
			*/
			fp.addParameter("x", x);
			fp.addParameter("y", y);
			
			fp.addParameter("z", 10000d);
			
			long start = System.currentTimeMillis();
			FormulaResult fr = null;
			for(int ind = 0; ind < 1;  ind ++)
			{
				 fr = f.eval(fp);
				 System.out.println(fr.getValue("cov"));
			}
			
			long end = System.currentTimeMillis();
			
			System.out.println("Cost " + (end-start) + "ms");
			System.out.println(fr.getResult());
			//System.out.println(fr.getDouble("dif"));		
	}
	
	public void testBeta2()
	{
		try
		{
			//final String txt = "A1:AVG(x*x);A2:-AVG(y*y);A3:-SQRT(z);A4:len(y);DIF:A1+A2+A3;";
			String txt = "" +
					"N:LEN(#x#);" +
					"A1:N*SUM(#x#*#y#)-SUM(#x#)*SUM(#y#);" +
					"A2:N*SUM(#x#*#x#)-POWER(SUM(#x#),2);" +
					"Beta:A1/A2;" +
					"AVGx:AVG(#x#);" +
					"AVGy:AVG(#y#);" +
					"Alpha:AVGx-Beta*AVGy;" +
					"COV:(1/N-1)*SUM(((#x#-AVGx)*(#y#-AVGy)));" +
					"Sigmax:SQRT(SUM((POWER(#x#-AVGx,2)/N)));" +
					"Sigmay:SQRT(SUM(POWER(#y#-AVGy,2))/N);" +
					"Pxy:COV/(Sigmax*Sigmay);" +
					"RSquare:POWER(Pxy,2);" +
					"Sy:SQRT(SUM(POWER(#y#-AVGy,2)))/(N-2);" +
					"Beta2:Sy/SQRT(SUM(POWER(#x#,2))-POWER(SUM(#x#),2)/n);";
			
			Formula f = FormulaBuilder.constructFormula(txt);
			
			runBeta(f);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void testBeta()
	{
		try
		{
			//final String txt = "A1:AVG(x*x);A2:-AVG(y*y);A3:-SQRT(z);A4:len(y);DIF:A1+A2+A3;";
			String txt = "" +
					"N:LEN(x);" +
					"A1:N*SUM(x*y)-SUM(x)*SUM(y);" +
					"A2:N*SUM(x*x)-POWER(SUM(x),2);" +
					"Beta:A1/A2;" +
					"AVGx:AVG(x);" +
					"AVGy:AVG(y);" +
					"Alpha:AVGx-Beta*AVGy;" +
					"COV:(1/N-1)*SUM(((x-AVGx)*(y-AVGy)));" +
					"Sigmax:SQRT(SUM((POWER(x-AVGx,2)/N)));" +
					"Sigmay:SQRT(SUM(POWER(y-AVGy,2))/N);" +
					"Pxy:COV/(Sigmax*Sigmay);" +
					"RSquare:POWER(Pxy,2);" +
					"Sy:SQRT(SUM(POWER(y-AVGy,2)))/(N-2);" +
					"Beta2:Sy/SQRT(SUM(POWER(x,2))-POWER(SUM(x),2)/n);";
			
			Formula f = FormulaBuilder.constructFormula(txt, new String[]{"x","y","z"});
			
			runBeta(f);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void testForumal()
	{
		//final String txt = "A1:AVG(x*x);A2:-AVG(y*y);A3:-SQRT(z);A4:len(y);DIF:A1+A2+A3;";
		final String txt = "" +
				"N:LEN(x);" +
				"A1:N*SUM(x*y)-SUM(x)*SUM(y);" +
				"A2:N*SUM(x*x)-POWER(SUM(x),2);" +
				"Beta:A1/A2;" +
				"AVGx:AVG(x);" +
				"AVGy:AVG(y);" +
				"Alpha:AVGx-Beta*AVGy;" +
				"COV:(1/N-1)*SUM(((x-AVGx)*(y-AVGy)));" +
				"Sigmax:SQRT(SUM((POWER(x-AVGx,2)/N)));" +
				"Sigmay:SQRT(SUM(POWER(y-AVGy,2))/N);" +
				"Pxy:COV/(Sigmax*Sigmay);" +
				"RSquare:POWER(Pxy,2);" +
				"Sy:SQRT(SUM(POWER(y-AVGy,2)))/(N-2);" +
				"Beta2:Sy/SQRT(SUM(POWER(x,2))-POWER(SUM(x),2)/n);";
		try
		{
			Formula f = FormulaBuilder.constructFormula(txt, new String[]{"x","y","z"});
			FormulaParameters fp = new FormulaParameters();
			int sampleCount = 100;
			
			Double[] x = new Double[sampleCount];			
			Double[] y = new Double[sampleCount];
			
			for(int ind = 0 ; ind < sampleCount; ind ++)
			{
				x[ind] = Math.random();
				y[ind] = Math.random();
			}
			
			/*fp.addParameter("x", new Double[]{0.1,0.12,0.13,0.14,0.15});
			fp.addParameter("y", new Double[]{0.01d,0.02d,0.013d,0.014d,0.015d});
			*/
			fp.addParameter("x", x);
			fp.addParameter("y", y);
			
			fp.addParameter("z", 10000d);
			
			long start = System.currentTimeMillis();
			FormulaResult fr = null;
			for(int ind = 0; ind < 1;  ind ++)
			{
				 fr = f.eval(fp);
				 System.out.println(fr.getValue("cov"));
			}
			
			long end = System.currentTimeMillis();
			
			System.out.println("Cost " + (end-start) + "ms");
			System.out.println(fr.getResult());
			//System.out.println(fr.getDouble("dif"));
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
