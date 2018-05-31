package com.jms.message;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MessageUtil {
    private final static ScriptEngine JSE = new ScriptEngineManager().getEngineByName("JavaScript");
    
	public static Object cal(String expression){
        try {
			return JSE.eval(expression);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
        return null;
    }

}
