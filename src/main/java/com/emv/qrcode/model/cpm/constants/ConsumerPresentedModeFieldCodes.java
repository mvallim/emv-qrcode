package com.emv.qrcode.model.cpm.constants;

public final class ConsumerPresentedModeFieldCodes {

  public static final String ID_PAYLOAD_FORMAT_INDICATOR = "85"; // (M) Payload Format Indicator
  public static final String ID_APPLICATION_TEMPLATE = "61"; // (M) Application Template
  public static final String ID_COMMON_DATA_TEMPLATE = "62"; // (O) Common Data Template
  public static final String ID_APPLICATION_SPECIFIC_TRANSPARENT_TEMPLATE = "63"; // (O) Application Specific Transparent Template
  public static final String ID_COMMON_DATA_TRANSPARENT_TEMPLATE = "64"; // (O) Common Data Transparent Template

  private ConsumerPresentedModeFieldCodes() {
    super();
  }

}
