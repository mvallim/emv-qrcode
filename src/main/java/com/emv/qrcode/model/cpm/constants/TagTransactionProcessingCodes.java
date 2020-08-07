package com.emv.qrcode.model.cpm.constants;

public final class TagTransactionProcessingCodes {

  public static final Integer ID_APPLICATION_DEFINITION_FILE_NAME = 0x4F; // Application Definition File (ADF) Name (M)
  public static final Integer ID_APPLICATION_LABEL = 0x50; // Application Label (O)
  public static final Integer ID_TRACK_2_EQUIVALENT_DATA = 0x57; // Track 2 Equivalent Data (C)
  public static final Integer ID_APPLICATION_PAN = 0x5A; // Application PAN (C)
  public static final Integer ID_CARDHOLDER_NAME = 0x5F20; // Cardholder Name (O)
  public static final Integer ID_LANGUAGE_PREFERENCE = 0x5F2D; // Language Preference (O)
  public static final Integer ID_ISSUER_URL = 0x5F50; // Issuer URL (O)
  public static final Integer ID_APPLICATION_VERSION_NUMBER = 0x9F08; // Application Version Number (O)
  public static final Integer ID_TOKEN_REQUESTOR_ID = 0x9F19; // Token Requestor ID (O)
  public static final Integer ID_PAYMENT_ACCOUNT_REFERENCE = 0x9F24; // Payment Account Reference (O)
  public static final Integer ID_LAST_4_DIGITS_OF_PAN = 0x9F25; // Last 4 Digits of PAN (O)
  public static final Integer ID_CRYPTOGRAM_INFORMATION_DATA = 0x9F27;
  public static final Integer ID_APPLICATION_TRANSACTION_COUNTER = 0x9F36;
  public static final Integer ID_APPLICATION_CRYPTOGRAM = 0x9F26;
  public static final Integer ID_ISSUER_APPLICATION_DATA = 0x9F10;

  private TagTransactionProcessingCodes() {
    super();
  }

}
