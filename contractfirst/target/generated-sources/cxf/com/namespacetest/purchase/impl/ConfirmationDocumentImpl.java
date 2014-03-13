/*
 * An XML document type.
 * Localname: Confirmation
 * Namespace: http://Namespacetest.com/Purchase
 * Java type: com.namespacetest.purchase.ConfirmationDocument
 *
 * Automatically generated - do not modify.
 */
package com.namespacetest.purchase.impl;
/**
 * A document containing one Confirmation(@http://Namespacetest.com/Purchase) element.
 *
 * This is a complex type.
 */
public class ConfirmationDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.namespacetest.purchase.ConfirmationDocument
{
    private static final long serialVersionUID = 1L;
    
    public ConfirmationDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CONFIRMATION$0 = 
        new javax.xml.namespace.QName("http://Namespacetest.com/Purchase", "Confirmation");
    
    
    /**
     * Gets the "Confirmation" element
     */
    public com.namespacetest.orderTypes.ConfirmationType getConfirmation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.namespacetest.orderTypes.ConfirmationType target = null;
            target = (com.namespacetest.orderTypes.ConfirmationType)get_store().find_element_user(CONFIRMATION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Confirmation" element
     */
    public void setConfirmation(com.namespacetest.orderTypes.ConfirmationType confirmation)
    {
        generatedSetterHelperImpl(confirmation, CONFIRMATION$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "Confirmation" element
     */
    public com.namespacetest.orderTypes.ConfirmationType addNewConfirmation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.namespacetest.orderTypes.ConfirmationType target = null;
            target = (com.namespacetest.orderTypes.ConfirmationType)get_store().add_element_user(CONFIRMATION$0);
            return target;
        }
    }
}
