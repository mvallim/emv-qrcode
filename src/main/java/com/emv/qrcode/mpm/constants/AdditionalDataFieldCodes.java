package com.emv.qrcode.mpm.constants;

public final class AdditionalDataFieldCodes {

  public static final String ID_BILL_NUMBER = "01"; // (O) Bill Number
  public static final String ID_MOBILE_NUMBER = "02"; // (O) Mobile Number
  public static final String ID_STORE_LABEL = "03"; // (O) Store Label
  public static final String ID_LOYALTY_NUMBER = "04"; // (O) Loyalty Number
  public static final String ID_REFERENCE_LABEL = "05"; // (O) Reference Label
  public static final String ID_CUSTOMER_LABEL = "06"; // (O) Customer Label
  public static final String ID_TERMINAL_LABEL = "07"; // (O) Terminal Label
  public static final String ID_PURPOSE_TRANSACTION = "08"; // (O) Purpose Transaction
  public static final String ID_ADDITIONAL_CONSUMER_DATA_REQUEST = "09"; // (O) Additional Consumer Data Request
  public static final String ID_RFU_FOR_EMVCO_RANGE_START = "10"; // (O) RFU for EMVCo
  public static final String ID_RFU_FOR_EMVCO_RANGE_END = "49"; // (O) RFU for EMVCo
  public static final String ID_PAYMENT_SYSTEM_SPECIFIC_TEMPLATES_RANGE_START = "50"; // (O) Payment System Specific Templates
  public static final String ID_PAYMENT_SYSTEM_SPECIFIC_TEMPLATES_RANGE_END = "99"; // (O) Payment System Specific Templates

  // Reserved
  public static final String ID_RFU_FOR_EMVCO = ID_RFU_FOR_EMVCO_RANGE_START;
  public static final String ID_PAYMENT_SYSTEM_SPECIFIC = ID_PAYMENT_SYSTEM_SPECIFIC_TEMPLATES_RANGE_START;

  private AdditionalDataFieldCodes() {
    super();
  }

}
