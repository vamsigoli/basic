/*
 * XML Type:  ConfirmationType
 * Namespace: http://Namespacetest.com/OrderTypes
 * Java type: com.namespacetest.orderTypes.ConfirmationType
 *
 * Automatically generated - do not modify.
 */
package com.namespacetest.orderTypes.impl;
/**
 * An XML ConfirmationType(@http://Namespacetest.com/OrderTypes).
 *
 * This is a complex type.
 */
public class ConfirmationTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.namespacetest.orderTypes.ConfirmationType
{
    private static final long serialVersionUID = 1L;
    
    public ConfirmationTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ITEM$0 = 
        new javax.xml.namespace.QName("http://Namespacetest.com/OrderTypes", "Item");
    
    
    /**
     * Gets a List of "Item" elements
     */
    public java.util.List<com.namespacetest.orderTypes.ConfirmationType.Item> getItemList()
    {
        final class ItemList extends java.util.AbstractList<com.namespacetest.orderTypes.ConfirmationType.Item>
        {
            @Override
            public com.namespacetest.orderTypes.ConfirmationType.Item get(int i)
                { return ConfirmationTypeImpl.this.getItemArray(i); }
            
            @Override
            public com.namespacetest.orderTypes.ConfirmationType.Item set(int i, com.namespacetest.orderTypes.ConfirmationType.Item o)
            {
                com.namespacetest.orderTypes.ConfirmationType.Item old = ConfirmationTypeImpl.this.getItemArray(i);
                ConfirmationTypeImpl.this.setItemArray(i, o);
                return old;
            }
            
            @Override
            public void add(int i, com.namespacetest.orderTypes.ConfirmationType.Item o)
                { ConfirmationTypeImpl.this.insertNewItem(i).set(o); }
            
            @Override
            public com.namespacetest.orderTypes.ConfirmationType.Item remove(int i)
            {
                com.namespacetest.orderTypes.ConfirmationType.Item old = ConfirmationTypeImpl.this.getItemArray(i);
                ConfirmationTypeImpl.this.removeItem(i);
                return old;
            }
            
            @Override
            public int size()
                { return ConfirmationTypeImpl.this.sizeOfItemArray(); }
            
        }
        
        synchronized (monitor())
        {
            check_orphaned();
            return new ItemList();
        }
    }
    
    /**
     * Gets array of all "Item" elements
     * @deprecated
     */
    @Deprecated
    public com.namespacetest.orderTypes.ConfirmationType.Item[] getItemArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List<com.namespacetest.orderTypes.ConfirmationType.Item> targetList = new java.util.ArrayList<com.namespacetest.orderTypes.ConfirmationType.Item>();
            get_store().find_all_element_users(ITEM$0, targetList);
            com.namespacetest.orderTypes.ConfirmationType.Item[] result = new com.namespacetest.orderTypes.ConfirmationType.Item[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Item" element
     */
    public com.namespacetest.orderTypes.ConfirmationType.Item getItemArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.namespacetest.orderTypes.ConfirmationType.Item target = null;
            target = (com.namespacetest.orderTypes.ConfirmationType.Item)get_store().find_element_user(ITEM$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Item" element
     */
    public int sizeOfItemArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ITEM$0);
        }
    }
    
    /**
     * Sets array of all "Item" element  WARNING: This method is not atomicaly synchronized.
     */
    public void setItemArray(com.namespacetest.orderTypes.ConfirmationType.Item[] itemArray)
    {
        check_orphaned();
        arraySetterHelper(itemArray, ITEM$0);
    }
    
    /**
     * Sets ith "Item" element
     */
    public void setItemArray(int i, com.namespacetest.orderTypes.ConfirmationType.Item item)
    {
        generatedSetterHelperImpl(item, ITEM$0, i, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_ARRAYITEM);
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Item" element
     */
    public com.namespacetest.orderTypes.ConfirmationType.Item insertNewItem(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.namespacetest.orderTypes.ConfirmationType.Item target = null;
            target = (com.namespacetest.orderTypes.ConfirmationType.Item)get_store().insert_element_user(ITEM$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Item" element
     */
    public com.namespacetest.orderTypes.ConfirmationType.Item addNewItem()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.namespacetest.orderTypes.ConfirmationType.Item target = null;
            target = (com.namespacetest.orderTypes.ConfirmationType.Item)get_store().add_element_user(ITEM$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "Item" element
     */
    public void removeItem(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ITEM$0, i);
        }
    }
    /**
     * An XML Item(@http://Namespacetest.com/OrderTypes).
     *
     * This is a complex type.
     */
    public static class ItemImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.namespacetest.orderTypes.ConfirmationType.Item
    {
        private static final long serialVersionUID = 1L;
        
        public ItemImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName PRODUCTNAME$0 = 
            new javax.xml.namespace.QName("http://Namespacetest.com/OrderTypes", "ProductName");
        private static final javax.xml.namespace.QName UNITPRICE$2 = 
            new javax.xml.namespace.QName("http://Namespacetest.com/OrderTypes", "UnitPrice");
        private static final javax.xml.namespace.QName STATUS$4 = 
            new javax.xml.namespace.QName("http://Namespacetest.com/OrderTypes", "Status");
        
        
        /**
         * Gets the "ProductName" element
         */
        public java.lang.String getProductName()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PRODUCTNAME$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "ProductName" element
         */
        public org.apache.xmlbeans.XmlString xgetProductName()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PRODUCTNAME$0, 0);
                return target;
            }
        }
        
        /**
         * Sets the "ProductName" element
         */
        public void setProductName(java.lang.String productName)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PRODUCTNAME$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PRODUCTNAME$0);
                }
                target.setStringValue(productName);
            }
        }
        
        /**
         * Sets (as xml) the "ProductName" element
         */
        public void xsetProductName(org.apache.xmlbeans.XmlString productName)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PRODUCTNAME$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PRODUCTNAME$0);
                }
                target.set(productName);
            }
        }
        
        /**
         * Gets the "UnitPrice" element
         */
        public java.math.BigDecimal getUnitPrice()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(UNITPRICE$2, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getBigDecimalValue();
            }
        }
        
        /**
         * Gets (as xml) the "UnitPrice" element
         */
        public com.namespacetest.commonTypes.PriceType xgetUnitPrice()
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.namespacetest.commonTypes.PriceType target = null;
                target = (com.namespacetest.commonTypes.PriceType)get_store().find_element_user(UNITPRICE$2, 0);
                return target;
            }
        }
        
        /**
         * Sets the "UnitPrice" element
         */
        public void setUnitPrice(java.math.BigDecimal unitPrice)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(UNITPRICE$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(UNITPRICE$2);
                }
                target.setBigDecimalValue(unitPrice);
            }
        }
        
        /**
         * Sets (as xml) the "UnitPrice" element
         */
        public void xsetUnitPrice(com.namespacetest.commonTypes.PriceType unitPrice)
        {
            synchronized (monitor())
            {
                check_orphaned();
                com.namespacetest.commonTypes.PriceType target = null;
                target = (com.namespacetest.commonTypes.PriceType)get_store().find_element_user(UNITPRICE$2, 0);
                if (target == null)
                {
                    target = (com.namespacetest.commonTypes.PriceType)get_store().add_element_user(UNITPRICE$2);
                }
                target.set(unitPrice);
            }
        }
        
        /**
         * Gets the "Status" element
         */
        public java.lang.String getStatus()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUS$4, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "Status" element
         */
        public org.apache.xmlbeans.XmlString xgetStatus()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(STATUS$4, 0);
                return target;
            }
        }
        
        /**
         * Sets the "Status" element
         */
        public void setStatus(java.lang.String status)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUS$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(STATUS$4);
                }
                target.setStringValue(status);
            }
        }
        
        /**
         * Sets (as xml) the "Status" element
         */
        public void xsetStatus(org.apache.xmlbeans.XmlString status)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(STATUS$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(STATUS$4);
                }
                target.set(status);
            }
        }
    }
}
