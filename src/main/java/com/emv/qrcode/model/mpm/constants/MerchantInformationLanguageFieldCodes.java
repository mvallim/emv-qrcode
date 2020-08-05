package com.emv.qrcode.model.mpm.constants;

public class MerchantInformationLanguageFieldCodes {

  public static final String ID_LANGUAGE_PREFERENCE = "00"; // (M) Language Preference
  public static final String ID_MERCHANT_NAME = "01"; // (M) Merchant Name
  public static final String ID_MERCHANT_CITY = "02"; // (O) Merchant City
  public static final String ID_RFU_FOR_EMVCO_RANGE_START = "03"; // (O) 03-99 RFU for EMVCo
  public static final String ID_RFU_FOR_EMVCO_RANGE_END = "99"; // (O) 03-99 RFU for EMVCo

  // Reserved
  public static final String ID_RFU_FOR_EMVCO = ID_RFU_FOR_EMVCO_RANGE_START;

  private MerchantInformationLanguageFieldCodes() {
    super();
  }

}
