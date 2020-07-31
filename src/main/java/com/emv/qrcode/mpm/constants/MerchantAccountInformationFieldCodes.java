package com.emv.qrcode.mpm.constants;

public final class MerchantAccountInformationFieldCodes {

  public static final String MERCHANT_ACCOUNT_INFORMATION_ID_GLOBALLY_UNIQUE_IDENTIFIER = "00";
  public static final String MERCHANT_ACCOUNT_INFORMATION_ID_PAYMENT_NETWORK_SPECIFIC_START = "01";
  public static final String MERCHANT_ACCOUNT_INFORMATION_ID_PAYMENT_NETWORK_SPECIFIC_END = "99";

  // Reserved
  public static final String MERCHANT_ACCOUNT_INFORMATION_ID_PAYMENT_NETWORK_SPECIFIC = MERCHANT_ACCOUNT_INFORMATION_ID_PAYMENT_NETWORK_SPECIFIC_START;

  private MerchantAccountInformationFieldCodes() {
    super();
  }

}
