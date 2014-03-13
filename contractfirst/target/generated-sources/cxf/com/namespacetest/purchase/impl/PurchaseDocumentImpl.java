/*
 * An XML document type.
 * Localname: Purchase
 * Namespace: http://Namespacetest.com/Purchase
 * Java type: com.namespacetest.purchase.PurchaseDocument
 *
 * Automatically generated - do not modify.
 */
package com.namespacetest.purchase.impl;
/**
 * A document containing one Purchase(@http://Namespacetest.com/Purchase) element.
 *
 * This is a complex type.
 */
public class PurchaseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.namespacetest.purchase.PurchaseDocument
{
    private static final long serialVersionUID = 1L;
    
    public PurchaseDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName PURCHASE$0 = 
        new javax.xml.namespace.QName("http://Namespacetest.com/Purchase", "Purchase");
    
    
    /**
     * Gets the "Purchase" element
     */
    public com.namespacetest.purchase.PurchaseDocument.Purchase getPurchase()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.namespacetest.purchase.PurchaseDocument.Purchase target = null;
            target = (com.namespacetest.purchase.PurchaseDocument.Purchase)get_store().find_element_user(PURCHASE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Purchase" element
     */
    public void setPurchase(com.namespacetest.purchase.PurchaseDocument.Purchase purchase)
    {
        generatedSetterHelperImpl(purchase, PURCHASE$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "Purchase" element
     */
    public com.namespacetest.purchase.PurchaseDocument.Purchase addNewPurchase()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.namespacetest.purchase.PurchaseDocument.Purchase target = null;
            target = (com.namespacetest.purchase.PurchaseDocument.Purchase)get_store().add_element_user(PURCHASE$0);
            return target;
        }
    }
    /**
     * An XML Purchase(@http://Namespacetest.com/Purchase).
     *
     * This is a complex type.
     */
    public static class PurchaseImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.namespacetest.purchase.PurchaseDocument.Purchase
    {
        private static final long serialVersionUID = 1L;
        
        public PurchaseImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName ORDERDETAIL$0 = 
            new javax.xml.namespace.QName("http://Namespacetest.com/Purchase", "OrderDetail");
        private static final javax.xml.namespace.QName PAYMENTMETHOD$2 = 
            new javax.xml.namespace.QName("http://Namespacetest.com/Purchase", "PaymentMethod");
        private static final javax.xml.namespace.QName CUSTOMERDETAILS$4 = 
            new javax.xml.namespace.QName("http://Namespacetest.com/Purchase", "CustomerDetails");
        
        
        /**
         * Gets the "OrderDetail" element
         */
        public com.namespacetest.orderTypes.OrderType getOrderDetail()
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.namespacetest.orderTypes.OrderType target = null;
                target = (com.namespacetest.orderTypes.OrderType)get_store().find_element_user(ORDERDETAIL$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "OrderDetail" element
         */
        public void setOrderDetail(com.namespacetest.orderTypes.OrderType orderDetail)
        {
            generatedSetterHelperImpl(orderDetail, ORDERDETAIL$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
        }
        
        /**
         * Appends and returns a new empty "OrderDetail" element
         */
        public com.namespacetest.orderTypes.OrderType addNewOrderDetail()
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.namespacetest.orderTypes.OrderType target = null;
                target = (com.namespacetest.orderTypes.OrderType)get_store().add_element_user(ORDERDETAIL$0);
                return target;
            }
        }
        
        /**
         * Gets the "PaymentMethod" element
         */
        public com.namespacetest.commonTypes.PaymentMethodType.Enum getPaymentMethod()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PAYMENTMETHOD$2, 0);
                if (target == null)
                {
                    return null;
                }
                return (com.namespacetest.commonTypes.PaymentMethodType.Enum)target.getEnumValue();
            }
        }
        
        /**
         * Gets (as xml) the "PaymentMethod" element
         */
        public com.namespacetest.commonTypes.PaymentMethodType xgetPaymentMethod()
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.namespacetest.commonTypes.PaymentMethodType target = null;
                target = (com.namespacetest.commonTypes.PaymentMethodType)get_store().find_element_user(PAYMENTMETHOD$2, 0);
                return target;
            }
        }
        
        /**
         * Sets the "PaymentMethod" element
         */
        public void setPaymentMethod(com.namespacetest.commonTypes.PaymentMethodType.Enum paymentMethod)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PAYMENTMETHOD$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PAYMENTMETHOD$2);
                }
                target.setEnumValue(paymentMethod);
            }
        }
        
        /**
         * Sets (as xml) the "PaymentMethod" element
         */
        public void xsetPaymentMethod(com.namespacetest.commonTypes.PaymentMethodType paymentMethod)
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.namespacetest.commonTypes.PaymentMethodType target = null;
                target = (com.namespacetest.commonTypes.PaymentMethodType)get_store().find_element_user(PAYMENTMETHOD$2, 0);
                if (target == null)
                {
                    target = (com.namespacetest.commonTypes.PaymentMethodType)get_store().add_element_user(PAYMENTMETHOD$2);
                }
                target.set(paymentMethod);
            }
        }
        
        /**
         * Gets the "CustomerDetails" element
         */
        public com.namespacetest.customerTypes.CustomerType getCustomerDetails()
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.namespacetest.customerTypes.CustomerType target = null;
                target = (com.namespacetest.customerTypes.CustomerType)get_store().find_element_user(CUSTOMERDETAILS$4, 0);
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
            generatedSetterHelperImpl(customerDetails, CUSTOMERDETAILS$4, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
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
                target = (com.namespacetest.customerTypes.CustomerType)get_store().add_element_user(CUSTOMERDETAILS$4);
                return target;
            }
        }
    }
}
