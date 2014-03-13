/*
 * An XML document type.
 * Localname: Purchase
 * Namespace: http://Namespacetest.com/Purchase
 * Java type: com.namespacetest.purchase.PurchaseDocument
 *
 * Automatically generated - do not modify.
 */
package com.namespacetest.purchase;


/**
 * A document containing one Purchase(@http://Namespacetest.com/Purchase) element.
 *
 * This is a complex type.
 */
public interface PurchaseDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(PurchaseDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sF228554153254A645118C1694D8A5CD5").resolveHandle("purchaseb75cdoctype");
    
    /**
     * Gets the "Purchase" element
     */
    com.namespacetest.purchase.PurchaseDocument.Purchase getPurchase();
    
    /**
     * Sets the "Purchase" element
     */
    void setPurchase(com.namespacetest.purchase.PurchaseDocument.Purchase purchase);
    
    /**
     * Appends and returns a new empty "Purchase" element
     */
    com.namespacetest.purchase.PurchaseDocument.Purchase addNewPurchase();
    
    /**
     * An XML Purchase(@http://Namespacetest.com/Purchase).
     *
     * This is a complex type.
     */
    public interface Purchase extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Purchase.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sF228554153254A645118C1694D8A5CD5").resolveHandle("purchase8cbfelemtype");
        
        /**
         * Gets the "OrderDetail" element
         */
        com.namespacetest.orderTypes.OrderType getOrderDetail();
        
        /**
         * Sets the "OrderDetail" element
         */
        void setOrderDetail(com.namespacetest.orderTypes.OrderType orderDetail);
        
        /**
         * Appends and returns a new empty "OrderDetail" element
         */
        com.namespacetest.orderTypes.OrderType addNewOrderDetail();
        
        /**
         * Gets the "PaymentMethod" element
         */
        com.namespacetest.commonTypes.PaymentMethodType.Enum getPaymentMethod();
        
        /**
         * Gets (as xml) the "PaymentMethod" element
         */
        com.namespacetest.commonTypes.PaymentMethodType xgetPaymentMethod();
        
        /**
         * Sets the "PaymentMethod" element
         */
        void setPaymentMethod(com.namespacetest.commonTypes.PaymentMethodType.Enum paymentMethod);
        
        /**
         * Sets (as xml) the "PaymentMethod" element
         */
        void xsetPaymentMethod(com.namespacetest.commonTypes.PaymentMethodType paymentMethod);
        
        /**
         * Gets the "CustomerDetails" element
         */
        com.namespacetest.customerTypes.CustomerType getCustomerDetails();
        
        /**
         * Sets the "CustomerDetails" element
         */
        void setCustomerDetails(com.namespacetest.customerTypes.CustomerType customerDetails);
        
        /**
         * Appends and returns a new empty "CustomerDetails" element
         */
        com.namespacetest.customerTypes.CustomerType addNewCustomerDetails();
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static com.namespacetest.purchase.PurchaseDocument.Purchase newInstance() {
              return (com.namespacetest.purchase.PurchaseDocument.Purchase) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static com.namespacetest.purchase.PurchaseDocument.Purchase newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (com.namespacetest.purchase.PurchaseDocument.Purchase) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static com.namespacetest.purchase.PurchaseDocument newInstance() {
          return (com.namespacetest.purchase.PurchaseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static com.namespacetest.purchase.PurchaseDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (com.namespacetest.purchase.PurchaseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static com.namespacetest.purchase.PurchaseDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (com.namespacetest.purchase.PurchaseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static com.namespacetest.purchase.PurchaseDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.namespacetest.purchase.PurchaseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static com.namespacetest.purchase.PurchaseDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.namespacetest.purchase.PurchaseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static com.namespacetest.purchase.PurchaseDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.namespacetest.purchase.PurchaseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static com.namespacetest.purchase.PurchaseDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.namespacetest.purchase.PurchaseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static com.namespacetest.purchase.PurchaseDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.namespacetest.purchase.PurchaseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static com.namespacetest.purchase.PurchaseDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.namespacetest.purchase.PurchaseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static com.namespacetest.purchase.PurchaseDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.namespacetest.purchase.PurchaseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static com.namespacetest.purchase.PurchaseDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.namespacetest.purchase.PurchaseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static com.namespacetest.purchase.PurchaseDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.namespacetest.purchase.PurchaseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static com.namespacetest.purchase.PurchaseDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (com.namespacetest.purchase.PurchaseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static com.namespacetest.purchase.PurchaseDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.namespacetest.purchase.PurchaseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static com.namespacetest.purchase.PurchaseDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (com.namespacetest.purchase.PurchaseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static com.namespacetest.purchase.PurchaseDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.namespacetest.purchase.PurchaseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        @Deprecated
        public static com.namespacetest.purchase.PurchaseDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.namespacetest.purchase.PurchaseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        @Deprecated
        public static com.namespacetest.purchase.PurchaseDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.namespacetest.purchase.PurchaseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        @Deprecated
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        @Deprecated
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
