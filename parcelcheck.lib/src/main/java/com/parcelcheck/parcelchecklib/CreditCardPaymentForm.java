package com.parcelcheck.parcelchecklib;

import java.math.BigDecimal;

public class CreditCardPaymentForm {

    private BigDecimal amount;

    private BillingAddressForm billingAddress;

    private int cardId;

    private Integer cardTypeId;

    private String cardNumber;

    private String cvv;

    private String expirationMonth;

    private String expirationYear;

    private String nameOnCard;

    private Boolean isGiftCard;

    private boolean storeCard;

    private Double tip;

    private double tax;

}
