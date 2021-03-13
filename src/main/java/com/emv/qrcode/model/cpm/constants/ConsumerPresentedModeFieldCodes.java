package com.emv.qrcode.model.cpm.constants;

public final class ConsumerPresentedModeFieldCodes {

  public static final byte[] ID_PAYLOAD_FORMAT_INDICATOR = new byte[] { (byte) 0x85 }; // (M) Payload Format Indicator
  public static final byte[] ID_APPLICATION_TEMPLATE = new byte[] { 0x61 }; // (M) Application Template
  public static final byte[] ID_COMMON_DATA_TEMPLATE = new byte[] { 0x62 }; // (O) Common Data Template
  public static final byte[] ID_APPLICATION_SPECIFIC_TRANSPARENT_TEMPLATE = new byte[] { 0x63 }; // (O) Application Specific Transparent Template
  public static final byte[] ID_COMMON_DATA_TRANSPARENT_TEMPLATE = new byte[] { 0x64 }; // (O) Common Data Transparent Template

  private ConsumerPresentedModeFieldCodes() {
    super();
  }

}
