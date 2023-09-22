package org.arquillian.example.common;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;


public class PerformanceInterceptor {
	
	private static final Logger LOGGER = Logger.getLogger(PerformanceInterceptor.class);
	
	@AroundInvoke
    public Object measureTime(InvocationContext ctx) throws Exception {
        long startTime = 0;
        long endTime = 0;
        StringBuilder sb;
        try {
            startTime = System.currentTimeMillis();
            return ctx.proceed();
        } finally {
            endTime = System.currentTimeMillis() - startTime;
        	sb = new StringBuilder();
            sb.append(ctx.getTarget().getClass().getName());
            sb.append("->");
            sb.append(ctx.getMethod().getName());
            sb.append("()");
            sb.append(" executed in ");
            sb.append(endTime);
            sb.append(" ms");
        	LOGGER.info(sb.toString());
        }
    }
}
