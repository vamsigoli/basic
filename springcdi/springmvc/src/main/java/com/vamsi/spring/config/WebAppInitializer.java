package com.vamsi.spring.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

// {!begin top}
public class WebAppInitializer extends
    AbstractAnnotationConfigDispatcherServletInitializer {
// {!end top}

  // {!begin root}
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[] { RootAppConfig.class};
  }
  // {!end root}

  // {!begin servletContext}
  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[] { DispatcherServletConfig.class };
  }
  // {!end servletContext}

  // {!begin servletConfig}
  @Override
  protected String[] getServletMappings() {
    return new String[] { "/" };
  }

  @Override
  protected Filter[] getServletFilters() {

    CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
    characterEncodingFilter.setEncoding("UTF-8");
    return new Filter[] { characterEncodingFilter};
  }
  // {!end servletConfig}
}
