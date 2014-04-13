package com.vamsi.util;

import javax.servlet.jsp.JspException; 
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
 
public class RandomNumberGenerator extends SimpleTagSupport{
 private int greaterThan; //stores the value of the greaterThan attribute of the custom tag
 
 @Override
 public void doTag() throws JspException, IOException{
	 //this is the method that is called when the custom tag <mytaglibrary:randomnumber greaterthan="2"> is called from a JSP page
  int randomNumber = (int)(Math.random() * (greaterThan+1)); //generate a random number, which is greater than the 'greaterThan' attribute's value
  getJspContext().getOut().print(randomNumber);
 }
 
 public void setGreaterThan(int number){ //the method name must match the attribute name defined in the TLD file
  greaterThan = number;
  }
 }