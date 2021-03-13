package com.emv.qrcode.model.cpm.constants;

public final class TagTransactionProcessingCodes {

  public static final byte[] ID_APPLICATION_DEFINITION_FILE_NAME = new byte[] { 0x4F }; // Application Definition File (ADF) Name (M)
  public static final byte[] ID_APPLICATION_LABEL = new byte[] { 0x50 }; // Application Label (O)
  public static final byte[] ID_TRACK_2_EQUIVALENT_DATA = new byte[] { 0x57 }; // Track 2 Equivalent Data (C)
  public static final byte[] ID_APPLICATION_PAN = new byte[] { 0x5A }; // Application PAN (C)
  public static final byte[] ID_CARDHOLDER_NAME = new byte[] { 0x5F, 0x20 }; // Cardholder Name (O)
  public static final byte[] ID_LANGUAGE_PREFERENCE = new byte[] { 0x5F, 0x2D }; // Language Preference (O)
  public static final byte[] ID_ISSUER_URL = new byte[] { 0x5F, 0x50 }; // Issuer URL (O)
  public static final byte[] ID_APPLICATION_VERSION_NUMBER = new byte[] { (byte) 0x9F, 0x08 }; // Application Version Number (O)
  public static final byte[] ID_TOKEN_REQUESTOR_ID = new byte[] { (byte) 0x9F, 0x19 }; // Token Requestor ID (O)
  public static final byte[] ID_PAYMENT_ACCOUNT_REFERENCE = new byte[] { (byte) 0x9F, 0x24 }; // Payment Account Reference (O)
  public static final byte[] ID_LAST_4_DIGITS_OF_PAN = new byte[] { (byte) 0x9F, 0x25 }; // Last 4 Digits of PAN (O)
  public static final byte[] ID_CRYPTOGRAM_INFORMATION_DATA = new byte[] { (byte) 0x9F, 0x27 };
  public static final byte[] ID_APPLICATION_TRANSACTION_COUNTER = new byte[] { (byte) 0x9F, 0x36 };
  public static final byte[] ID_APPLICATION_CRYPTOGRAM = new byte[] { (byte) 0x9F, 0x26 };
  public static final byte[] ID_ISSUER_APPLICATION_DATA = new byte[] { (byte) 0x9F, 0x10 };

  private TagTransactionProcessingCodes() {
    super();
  }

}
