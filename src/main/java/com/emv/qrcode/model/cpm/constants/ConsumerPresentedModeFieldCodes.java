package com.emv.qrcode.model.cpm.constants;

public final class ConsumerPresentedModeFieldCodes {

  public static final Integer ID_PAYLOAD_FORMAT_INDICATOR = 0x85; // (M) Payload Format Indicator
  public static final Integer ID_APPLICATION_TEMPLATE = 0x61; // (M) Application Template
  public static final Integer ID_COMMON_DATA_TEMPLATE = 0x62; // (O) Common Data Template
  public static final Integer ID_APPLICATION_SPECIFIC_TRANSPARENT_TEMPLATE = 0x63; // (O) Application Specific Transparent Template
  public static final Integer ID_COMMON_DATA_TRANSPARENT_TEMPLATE = 0x64; // (O) Common Data Transparent Template

  private ConsumerPresentedModeFieldCodes() {
    super();
  }

}
