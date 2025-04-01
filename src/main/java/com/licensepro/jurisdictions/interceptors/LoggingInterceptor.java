package com.licensepro.jurisdictions.interceptors;

import com.google.cloud.ServiceOptions;
import com.google.cloud.logging.TraceLoggingEnhancer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class LoggingInterceptor implements HandlerInterceptor, WebMvcConfigurer {

  private static final String TRACED_VALUE = "1";

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    TraceLoggingEnhancer.setCurrentTraceId(getTrace(request.getHeader("x-b3-traceid")));
    TraceLoggingEnhancer.setCurrentSpanId(request.getHeader("x-b3-spanid"));
    TraceLoggingEnhancer.setCurrentTraceSampled(TRACED_VALUE.equals(request.getHeader("x-b3-sampled")));
    return true;
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    TraceLoggingEnhancer.setCurrentTraceId(null);
    TraceLoggingEnhancer.setCurrentSpanId(null);
    TraceLoggingEnhancer.setCurrentTraceSampled(null);
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(this);
  }

  private static String getTrace(String traceId) {
    String projectId = ServiceOptions.getDefaultProjectId();
    if (projectId != null && traceId != null) {
      return String.format("projects/%s/traces/%s", projectId, traceId);
    }
    return null;
  }
}