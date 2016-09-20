package com.epam.chuikov.constant;


public class Views {
    public static final String INDEX_JSP = "WEB-INF/jsp/index.jsp";

    public static final String COMMODITIES_JSP = "commodities.jsp";
    public static final String BAG_JSP = "WEB-INF/jsp/bag.jsp";
    public static final String PAY_WAY_JSP = "WEB-INF/jsp/payWay.jsp";
    public static final String DELIVERY_ADDRESS_JSP = "WEB-INF/jsp/deliveryAddress.jsp";
    public static final String CONFIRMATION = "WEB-INF/jsp/confirmation.jsp";
    public static final String ORDER_INFO = "WEB-INF/jsp/orderInfo.jsp";

    private Views() {
    }

    public static class Mapping {
        public static final String REGISTRATION = "registration";
        public static final String COMMODITIES = "commodityFilterUploader";
        public static final String LOGIN_CHECK = "loginCheck";
        public static final String BAG_UPLOADER = "basketUploader";
        public static final String BAG_REMOVE = "removeFromBag";
        public static final String BAG_CHANGE_AMOUNT = "changeAmount";
        public static final String BAG_CLEAR = "clearBasket";

        private Mapping() {
        }
    }

}
