package com.kumarvikas1.core.assets.metrics;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by vikakumar on 1/6/18.
 */

@Aspect
@Component
public class Measure {

	@Autowired
	MetricRegistry metricRegistry;

	@Around("@annotation(timeTaken)")
	public Object profile(ProceedingJoinPoint pjp, TimeTaken timeTaken) throws Throwable {
		Timer timer= this.metricRegistry.timer(timeTaken.name());;
		final Timer.Context context = timer.time();
		Object result = null;
		try {
			result = pjp.proceed();
		} finally {
			context.stop();
		}
		return result;
	}

}
