package com.ruoyi.web.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@Slf4j
public class MvcConfig extends WebMvcConfigurerAdapter {

  @Bean
  public H5MemberInterceptor memberInterceptor() {
    return new H5MemberInterceptor();
  }


  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(memberInterceptor());
  }
}
