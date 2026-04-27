package com.promptoptimizer.security;

import org.springframework.context.annotation.*;
import org.springframework.boot.web.servlet.*;

@Configuration
public class FilterConfig {

@Bean
FilterRegistrationBean<JwtFilter> jwtFilter(){

FilterRegistrationBean<JwtFilter> reg=
new FilterRegistrationBean<>();

reg.setFilter(new JwtFilter());

reg.addUrlPatterns("/api/*");

return reg;
}
}
