package com.vamsi.spring.springmvc;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

//Not required anymore. Spring mvc 3.2 has provided Jackson2ObjectMapperFactoryBean for configuration purposes

public class CustomJacksonObjectMapper extends ObjectMapper {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public CustomJacksonObjectMapper() {
    super();
    this.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
    this.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
    this.setSerializationInclusion(Include.NON_NULL);
   }
}
