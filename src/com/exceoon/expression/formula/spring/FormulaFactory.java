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

package com.exceoon.expression.formula.spring;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;

import com.exceoon.expression.formula.Formula;
import com.exceoon.expression.formula.FormulaBuilder;



/**
 * 
 * Author	David.Liu 
 * Mail		inthirties@gmail.com
 * Copyright	Exceoon corporation
 * Date			2011-7-22 上午08:37:34
 * 
 */

public class FormulaFactory
{
	private Map<String, Formula> formulas = new HashMap<String, Formula>();
	private static final Logger logger = Logger.getLogger(FormulaFactory.class);
	
	public void setResources(Resource[] rs)
	{
		try
		{
			if(rs!=null)
			{
				for(Resource r : rs)
				{
					String filename = r.getFilename();
					int index = filename.lastIndexOf(".");
					String name = index<=0?filename:filename.substring(0, index);
					
					if(logger.isInfoEnabled())
						logger.info("Found formula["+name+"] at "+ r.toString());
					
					addFormula(name, r.getInputStream());
				}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new IllegalArgumentException("Can not parse the formula", e);
		}
	}
	
	public void addFormula(String formulaName, InputStream is) throws Exception
	{
		if(formulaName == null)
		{
			throw new IllegalArgumentException("Formula name can not be null.");
		}
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));						
		StringBuffer content = new StringBuffer();
		
		while(true)
		{
			String line = reader.readLine();
			
			if(line == null)
				break;
			
			if(line.trim().length()>0)
			{
				content.append(line);
			}
		}
		reader.close();
		addFormula(formulaName, content.toString());		
	}
	
	public void addFormula(String formulaName, String formula) throws Exception
	{
		if(formulaName == null)
		{
			throw new IllegalArgumentException("Formula name can not be null.");
		}
		
		Formula f = FormulaBuilder.constructFormula(formula);
		
		String key = formulaName.trim().toLowerCase();
		
		if(formulas.containsKey(key))
		{
			logger.warn("Formula["+key+"] is defined. The old will be overrid.");
		}
		
		formulas.put(key, f);
		
		if(logger.isInfoEnabled())
		{
			logger.info("Formula["+key+"] is added into formula factory successfully.");
		}
	}
	
	public Formula getFormula(String formulaName)
	{
		if(formulaName != null)
		{
			String key = formulaName.trim().toLowerCase();
			
			if(formulas.containsKey(key))
			{
				return formulas.get(key);
			}
		}
		
		throw new IllegalArgumentException("Can not find the formula["+formulaName+"]");
	}
}
