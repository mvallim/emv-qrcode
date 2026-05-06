/*
 * Copyright 2019 the original author or authors.
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

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * BER-TLV tag constants for Consumer Presented Mode (CPM) transaction processing.
 * These constants define the EMVCo-assigned BER-TLV tags for data objects
 * used in transaction processing within Consumer Presented Mode QR codes.
 *
 * <p>Each constant is a {@link BERTag} instance representing a specific transaction-related
 * data object. The notation (M), (O), and (C) indicate mandatory, optional, or conditional fields.</p>
 *
 * @see com.emv.qrcode.core.model.cpm.BERTag
 * @see com.emv.qrcode.model.cpm.ConsumerPresentedMode
 * @since EMVCo QR Code Specification v1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TagTransactionProcessingCodes {

  /**
   * (M) Application Definition File (ADF) Name - identifies the application.
   */
  public static final BERTag ID_APPLICATION_DEFINITION_FILE_NAME = new BERTag(new byte[] { 0x4F });

  /**
   * (O) Application Label - human-readable application name.
   */
  public static final BERTag ID_APPLICATION_LABEL = new BERTag(new byte[] { 0x50 });

  /**
   * (C) Track 2 Equivalent Data - contains magnetic stripe equivalent data.
   */
  public static final BERTag ID_TRACK_2_EQUIVALENT_DATA = new BERTag(new byte[] { 0x57 });

  /**
   * (C) Application PAN (Primary Account Number) - the card number.
   */
  public static final BERTag ID_APPLICATION_PAN = new BERTag(new byte[] { 0x5A });

  /**
   * (O) Cardholder Name - name of the cardholder.
   */
  public static final BERTag ID_CARDHOLDER_NAME = new BERTag(new byte[] { 0x5F, 0x20 });

  /**
   * (O) Language Preference - preferred language for display (ISO 639-1).
   */
  public static final BERTag ID_LANGUAGE_PREFERENCE = new BERTag(new byte[] { 0x5F, 0x2D });

  /**
   * (O) Issuer URL - web address of the card issuer.
   */
  public static final BERTag ID_ISSUER_URL = new BERTag(new byte[] { 0x5F, 0x50 });

  /**
   * (O) Application Version Number - version of the application.
   */
  public static final BERTag ID_APPLICATION_VERSION_NUMBER = new BERTag(new byte[] { (byte) 0x9F, 0x08 });

  /**
   * (O) Token Requestor ID - identifies the token requestor.
   */
  public static final BERTag ID_TOKEN_REQUESTOR_ID = new BERTag(new byte[] { (byte) 0x9F, 0x19 });

  /**
   * (O) Payment Account Reference - unique reference to the payment account.
   */
  public static final BERTag ID_PAYMENT_ACCOUNT_REFERENCE = new BERTag(new byte[] { (byte) 0x9F, 0x24 });

  /**
   * (O) Last 4 Digits of PAN - truncated card number for display.
   */
  public static final BERTag ID_LAST_4_DIGITS_OF_PAN = new BERTag(new byte[] { (byte) 0x9F, 0x25 });

  /**
   * Cryptogram Information Data - contains information about the cryptogram type.
   */
  public static final BERTag ID_CRYPTOGRAM_INFORMATION_DATA = new BERTag(new byte[] { (byte) 0x9F, 0x27 });

  /**
   * Application Transaction Counter (ATC) - increments with each transaction.
   */
  public static final BERTag ID_APPLICATION_TRANSACTION_COUNTER = new BERTag(new byte[] { (byte) 0x9F, 0x36 });

  /**
   * Application Cryptogram - cryptographic proof of transaction validity.
   */
  public static final BERTag ID_APPLICATION_CRYPTOGRAM = new BERTag(new byte[] { (byte) 0x9F, 0x26 });

  /**
   * Issuer Application Data - proprietary data from the card issuer.
   */
  public static final BERTag ID_ISSUER_APPLICATION_DATA = new BERTag(new byte[] { (byte) 0x9F, 0x10 });

  /**
   * Unpredictable Number - random number used in cryptogram generation.
   */
  public static final BERTag ID_UNPREDICTABLE_NUMBER = new BERTag(new byte[] { (byte) 0x9F, 0x37 });

}
