//Copyright 2006 Stephane GINER
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.

package com.exceoon.expression.formula.support;

import java.util.List;



import fr.expression4j.basic.ExpressionElement;
import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.OperatorManager;
import fr.expression4j.basic.ParseInfo;
import fr.expression4j.core.Catalog;
import fr.expression4j.core.ExpressionModel;
import fr.expression4j.core.Parameters;
import fr.expression4j.core.exception.EvalException;
import fr.expression4j.core.exception.ParsingException;
import fr.expression4j.core.impl.TreeElement;
import fr.expression4j.util.ExpressionElementUtil;

/**
 * Parse a constant or a variable element.
 * @author SGINER
 *
 */
public class DefinedVariableExpressionElement implements ExpressionElement {

	public DefinedVariableExpressionElement() {
		super();
	}

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.ExpressionElement#parseElement(java.lang.String, fr.expression4j.basic.ParseInfo)
	 */
	public boolean parseElement(String expression, ExpressionModel expressionModel, ParseInfo parseInfo, Catalog catalog, @SuppressWarnings("rawtypes") List functionParameters, int priorityOperatorLevel) throws ParsingException {
		int startPos = parseInfo.getEndPos();
		int currentPos = parseInfo.getEndPos();
		
		if (ExpressionElementUtil.checkPosition(expression,currentPos) && "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".indexOf(expression.charAt(currentPos)) != -1) {
			currentPos ++;
			while (ExpressionElementUtil.checkPosition(expression,currentPos) && "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_".indexOf(expression.charAt(currentPos)) != -1) {
				currentPos ++;
			}
		}
		
		if (currentPos == startPos) {
			return false;
		}
		
		String constantOrVariableName = expression.substring(startPos,currentPos);

		//check if it is a constant
		if (VariableContext.getContext().isVariableDefined(constantOrVariableName)) {
			//it is a constant
			TreeElement result = new TreeElement(TreeElement.TREE_ELEMENT_TYPE_EXPRESSION_ELEMENT,null,null,null,getName(),constantOrVariableName,null);
			parseInfo.setTreeElement(result);
			parseInfo.setEndPos(currentPos);
			return true;
		}		
		else
		{
			return false;
		}		
	}
	

	/* (non-Javadoc)
	 * @see fr.expression4j.basic.ExpressionElement#getName()
	 */
	public String getName() {
		return "DefinedVairableExpressionElement";
	}

	public MathematicalElement evaluate(TreeElement element, Catalog catalog, OperatorManager operatorManager, Parameters parameters, ExpressionModel model) throws EvalException {
		try
		{
			return parameters.getParameter(element.getStringElement());
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new EvalException("variable evaluate error", e);
		}								
	}

	public String toString(TreeElement element, ExpressionModel expressionModel) {
		return element.getStringElement();
	}

}
