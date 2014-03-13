
package com.namespacetest.shopping;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 2.7.10
 * 2014-03-13T10:26:36.576-04:00
 * Generated source version: 2.7.10
 * 
 */
 
public class PurchasePortType_PurchasePort_Server{

    protected PurchasePortType_PurchasePort_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new PurchasePortTypeImpl();
        String address = "http://localhost:8080/purchase/services/purchaseorder";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws java.lang.Exception { 
        new PurchasePortType_PurchasePort_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}
