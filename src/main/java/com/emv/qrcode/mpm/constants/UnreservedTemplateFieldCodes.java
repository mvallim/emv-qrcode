package com.emv.qrcode.mpm.constants;

public class UnreservedTemplateFieldCodes {

  public static final String UNRESERVED_TEMPLATE_ID_GLOBALLY_UNIQUE_IDENTIFIER = "00";
  public static final String UNRESERVED_TEMPLATE_ID_CONTEXT_SPECIFIC_DATA_START = "01"; // (O) 03-99 RFU for EMVCo
  public static final String UNRESERVED_TEMPLATE_ID_CONTEXT_SPECIFIC_DATA_END = "99"; // (O) 03-99 RFU for EMVCo

  private UnreservedTemplateFieldCodes() {
    super();
  }

}
