/*
 * An XML document type.
 * Localname: CustomerDetails
 * Namespace: http://Namespacetest.com/Purchase
 * Java type: com.namespacetest.purchase.CustomerDetailsDocument
 *
 * Automatically generated - do not modify.
 */
package com.namespacetest.purchase.impl;
/**
 * A document containing one CustomerDetails(@http://Namespacetest.com/Purchase) element.
 *
 * This is a complex type.
 */
public class CustomerDetailsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.namespacetest.purchase.CustomerDetailsDocument
{
    private static final long serialVersionUID = 1L;
    
    public CustomerDetailsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CUSTOMERDETAILS$0 = 
        new javax.xml.namespace.QName("http://Namespacetest.com/Purchase", "CustomerDetails");
    
    
    /**
     * Gets the "CustomerDetails" element
     */
    public com.namespacetest.customerTypes.CustomerType getCustomerDetails()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.namespacetest.customerTypes.CustomerType target = null;
            target = (com.namespacetest.customerTypes.CustomerType)get_store().find_element_user(CUSTOMERDETAILS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "CustomerDetails" element
     */
    public void setCustomerDetails(com.namespacetest.customerTypes.CustomerType customerDetails)
    {
        generatedSetterHelperImpl(customerDetails, CUSTOMERDETAILS$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "CustomerDetails" element
     */
    public com.namespacetest.customerTypes.CustomerType addNewCustomerDetails()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.namespacetest.customerTypes.CustomerType target = null;
            target = (com.namespacetest.customerTypes.CustomerType)get_store().add_element_user(CUSTOMERDETAILS$0);
            return target;
        }
    }
}
