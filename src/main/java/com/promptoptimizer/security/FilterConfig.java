@Bean
public FilterRegistrationBean<JwtFilter> jwtFilter() {
    FilterRegistrationBean<JwtFilter> reg = new FilterRegistrationBean<>();
    reg.setFilter(new JwtFilter());
    reg.addUrlPatterns("/api/*");
    return reg;
}
