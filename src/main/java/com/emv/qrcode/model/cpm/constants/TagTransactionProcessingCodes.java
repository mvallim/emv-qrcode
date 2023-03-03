/*
 * Copyright 2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.emv.qrcode.model.cpm.constants;

import com.emv.qrcode.core.model.cpm.BERTag;

public final class TagTransactionProcessingCodes {

  public static final BERTag ID_APPLICATION_DEFINITION_FILE_NAME = new BERTag(new byte[] { 0x4F }); // Application Definition File (ADF) Name (M)
  public static final BERTag ID_APPLICATION_LABEL = new BERTag(new byte[] { 0x50 }); // Application Label (O)
  public static final BERTag ID_TRACK_2_EQUIVALENT_DATA = new BERTag(new byte[] { 0x57 }); // Track 2 Equivalent Data (C)
  public static final BERTag ID_APPLICATION_PAN = new BERTag(new byte[] { 0x5A }); // Application PAN (C)
  public static final BERTag ID_CARDHOLDER_NAME = new BERTag(new byte[] { 0x5F, 0x20 }); // Cardholder Name (O)
  public static final BERTag ID_LANGUAGE_PREFERENCE = new BERTag(new byte[] { 0x5F, 0x2D }); // Language Preference (O)
  public static final BERTag ID_ISSUER_URL = new BERTag(new byte[] { 0x5F, 0x50 }); // Issuer URL (O)
  public static final BERTag ID_APPLICATION_VERSION_NUMBER = new BERTag(new byte[] { (byte) 0x9F, 0x08 }); // Application Version Number (O)
  public static final BERTag ID_TOKEN_REQUESTOR_ID = new BERTag(new byte[] { (byte) 0x9F, 0x19 }); // Token Requestor ID (O)
  public static final BERTag ID_PAYMENT_ACCOUNT_REFERENCE = new BERTag(new byte[] { (byte) 0x9F, 0x24 }); // Payment Account Reference (O)
  public static final BERTag ID_LAST_4_DIGITS_OF_PAN = new BERTag(new byte[] { (byte) 0x9F, 0x25 }); // Last 4 Digits of PAN (O)
  public static final BERTag ID_CRYPTOGRAM_INFORMATION_DATA = new BERTag(new byte[] { (byte) 0x9F, 0x27 });
  public static final BERTag ID_APPLICATION_TRANSACTION_COUNTER = new BERTag(new byte[] { (byte) 0x9F, 0x36 }); // Application Transaction Counter (ATC)
  public static final BERTag ID_APPLICATION_CRYPTOGRAM = new BERTag(new byte[] { (byte) 0x9F, 0x26 }); // Application Cryptogram
  public static final BERTag ID_ISSUER_APPLICATION_DATA = new BERTag(new byte[] { (byte) 0x9F, 0x10 }); // Issuer Application Data
  public static final BERTag ID_UNPREDICTABLE_NUMBER = new BERTag(new byte[] { (byte) 0x9F, 0x37 }); // Unpredictable Number

  private TagTransactionProcessingCodes() {
    super();
  }

}
