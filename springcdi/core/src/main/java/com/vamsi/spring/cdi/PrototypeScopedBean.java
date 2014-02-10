package com.vamsi.spring.cdi;

import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="prototype",proxyMode=ScopedProxyMode.TARGET_CLASS)
class PrototypeScopedBean{
 private final String state;
 
 public PrototypeScopedBean(){
  this.state = UUID.randomUUID().toString();
 }

 public String getState() {
  return state;
 }
}
