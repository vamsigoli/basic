/*
 * XML Type:  CustomerType
 * Namespace: http://Namespacetest.com/CustomerTypes
 * Java type: com.namespacetest.customerTypes.CustomerType
 *
 * Automatically generated - do not modify.
 */
package com.namespacetest.customerTypes.impl;
/**
 * An XML CustomerType(@http://Namespacetest.com/CustomerTypes).
 *
 * This is a complex type.
 */
public class CustomerTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.namespacetest.customerTypes.CustomerType
{
    private static final long serialVersionUID = 1L;
    
    public CustomerTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NAME$0 = 
        new javax.xml.namespace.QName("http://Namespacetest.com/CustomerTypes", "Name");
    private static final javax.xml.namespace.QName DELIVERYADDRESS$2 = 
        new javax.xml.namespace.QName("http://Namespacetest.com/CustomerTypes", "DeliveryAddress");
    private static final javax.xml.namespace.QName BILLINGADDRESS$4 = 
        new javax.xml.namespace.QName("http://Namespacetest.com/CustomerTypes", "BillingAddress");
    
    
    /**
     * Gets the "Name" element
     */
    public java.lang.String getName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NAME$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "Name" element
     */
    public org.apache.xmlbeans.XmlString xgetName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(NAME$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "Name" element
     */
    public void setName(java.lang.String name)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NAME$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(NAME$0);
            }
            target.setStringValue(name);
        }
    }
    
    /**
     * Sets (as xml) the "Name" element
     */
    public void xsetName(org.apache.xmlbeans.XmlString name)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(NAME$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(NAME$0);
            }
            target.set(name);
        }
    }
    
    /**
     * Gets the "DeliveryAddress" element
     */
    public com.namespacetest.commonTypes.AddressType getDeliveryAddress()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.namespacetest.commonTypes.AddressType target = null;
            target = (com.namespacetest.commonTypes.AddressType)get_store().find_element_user(DELIVERYADDRESS$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "DeliveryAddress" element
     */
    public void setDeliveryAddress(com.namespacetest.commonTypes.AddressType deliveryAddress)
    {
        generatedSetterHelperImpl(deliveryAddress, DELIVERYADDRESS$2, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "DeliveryAddress" element
     */
    public com.namespacetest.commonTypes.AddressType addNewDeliveryAddress()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.namespacetest.commonTypes.AddressType target = null;
            target = (com.namespacetest.commonTypes.AddressType)get_store().add_element_user(DELIVERYADDRESS$2);
            return target;
        }
    }
    
    /**
     * Gets the "BillingAddress" element
     */
    public com.namespacetest.commonTypes.AddressType getBillingAddress()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.namespacetest.commonTypes.AddressType target = null;
            target = (com.namespacetest.commonTypes.AddressType)get_store().find_element_user(BILLINGADDRESS$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "BillingAddress" element
     */
    public void setBillingAddress(com.namespacetest.commonTypes.AddressType billingAddress)
    {
        generatedSetterHelperImpl(billingAddress, BILLINGADDRESS$4, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "BillingAddress" element
     */
    public com.namespacetest.commonTypes.AddressType addNewBillingAddress()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.namespacetest.commonTypes.AddressType target = null;
            target = (com.namespacetest.commonTypes.AddressType)get_store().add_element_user(BILLINGADDRESS$4);
            return target;
        }
    }
}
