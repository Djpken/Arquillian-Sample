package org.arquillian.example.common;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;


public class LoggingInterceptor {
	
	private static final Logger LOGGER = Logger.getLogger(LoggingInterceptor.class);
	
	@AroundInvoke
    public Object logging(InvocationContext ctx) throws Exception {
		int[] temp;
		Object result = null;
		String className = ctx.getTarget().getClass().getName();
		String methodName = ctx.getMethod().getName();
        StringBuilder sb;
        try {
        	sb = new StringBuilder();
			sb.append(className);
			sb.append("->");
			sb.append(methodName);
			sb.append("()");
			Object[] objs = ctx.getParameters();
			if (objs != null) {
				Object obj;
				if (objs.length > 0) {
					sb.append(" with parameter(s): ");
				}
				for (int i = 0; i < objs.length; i++) {
					obj = objs[i];
					if (obj instanceof int[]) {
						sb.append(obj.getClass().getSimpleName());
						temp = (int[]) obj;
						if (temp.length == 0) {
							sb.append("=null");
						} else {
							for (int j = 0; j < temp.length; j++) {
								sb.append(" ");
								sb.append(temp[j]);
							}
						}
					} else {
						if (obj != null) {
							sb.append(obj.getClass().getSimpleName());
							sb.append("->");
							sb.append(obj.toString());
						}
					}
					if (i < objs.length - 1) {
						sb.append(", ");
					} 
				}
			}
        	LOGGER.info(sb.toString());
            result = ctx.proceed();
        } catch (Exception e) {
        	sb = new StringBuilder();
        	sb.append(className);
			sb.append("->");
        	sb.append(methodName);
        	sb.append("()");
        	sb.append(" failed");
        	LOGGER.fatal(sb.toString(), e);
        	throw e;
        } 
    	sb = new StringBuilder();
    	sb.append(className);
		sb.append("->");
    	sb.append(methodName);
    	sb.append("()");
    	sb.append(" executed ");
    	if (result != null) {
    		sb.append("with returning object ");
    		sb.append(result.getClass().getSimpleName());
    		sb.append("->");
    		sb.append(result.toString());
    	}
    	LOGGER.debug(sb.toString());
        return result;
    }
}
