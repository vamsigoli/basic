package com.vamsi.spring.cdi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class SingletonScopedBean{
 @Autowired private PrototypeScopedBean prototypeScopedBean;
 
 public String getState(){
  return this.prototypeScopedBean.getState();
 }
}
