package com.emv.qrcode.mpm.constants;

public class MerchantInformationFieldCodes {

  public static final String MERCHANT_INFORMATION_ID_LANGUAGE_PREFERENCE = "00"; // (M) Language Preference
  public static final String MERCHANT_INFORMATION_ID_MERCHANT_NAME = "01"; // (M) Merchant Name
  public static final String MERCHANT_INFORMATION_ID_MERCHANT_CITY = "02"; // (O) Merchant City
  public static final String MERCHANT_INFORMATION_ID_RFUFOR_EMVCO_RANGE_START = "03"; // (O) 03-99 RFU for EMVCo
  public static final String MERCHANT_INFORMATION_ID_RFUFOR_EMVCO_RANGE_END = "99"; // (O) 03-99 RFU for EMVCo

  private MerchantInformationFieldCodes() {
    super();
  }

}
