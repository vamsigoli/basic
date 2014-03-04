package com.vamsi.core;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapTester {
	
	
	public static void main(String ... argv) {
		
		HashMap<String,String> paymentAmountMap = new HashMap<String,String>();
		
		paymentAmountMap.put("payment0", "59595");
		paymentAmountMap.put("payment1", "59595");
		paymentAmountMap.put("payment2", "59595|abc");
		paymentAmountMap.put("payment3", "59,59667");
		
		
		if (paymentAmountMap != null){
			
			Map paymentAmountMapTemp =  new HashMap(paymentAmountMap);
			
		
		Set<String> keys = paymentAmountMapTemp.keySet();
		
		for (String key : keys) {
			String value = (String)paymentAmountMapTemp.get(key);
			
			if (value != null && value!="" && !value.isEmpty()) {
				try {
					
				Double d = Double.parseDouble(value);
				}
				catch (Exception e) {
					paymentAmountMap.put(key, "0.0");
				}
				
				
			}
			
		}
		
		System.out.println("values " + paymentAmountMap.values());
		
		
	}
	}

}
