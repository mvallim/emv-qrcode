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

package com.emv.qrcode.model.mpm.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Constants for Merchant Presented Mode (MPM) QR code field tags.
 * These constants define the EMVCo-assigned tag values for each data field
 * in the Merchant Presented Mode QR code specification.
 *
 * <p>Each constant represents a specific data field that can be present in an MPM QR code.
 * The notation (M), (O), and (C) indicate whether the field is Mandatory, Optional, or Conditional.</p>
 *
 * @see com.emv.qrcode.model.mpm.MerchantPresentedMode
 * @since EMVCo QR Code Specification v1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MerchantPresentedModeCodes {

  /**
   * (M) Payload Format Indicator - identifies the version of the QR code specification.
   */
  public static final String ID_PAYLOAD_FORMAT_INDICATOR = "00";

  /**
   * (O) Point of Initiation Method - indicates whether the QR code is static or dynamic.
   */
  public static final String ID_POINT_OF_INITIATION_METHOD = "01";

  /**
   * (M) Start of Merchant Account Information reserved range (02-25).
   */
  public static final String ID_MERCHANT_ACCOUNT_INFORMATION_RESERVED_RANGE_START = "02";

  /**
   * (M) End of Merchant Account Information reserved range (02-25).
   */
  public static final String ID_MERCHANT_ACCOUNT_INFORMATION_RESERVED_RANGE_END = "25";

  /**
   * (M) Start of Merchant Account Information reserved additional range (26-51).
   */
  public static final String ID_MERCHANT_ACCOUNT_INFORMATION_RESERVED_ADDITIONAL_RANGE_START = "26";

  /**
   * (M) End of Merchant Account Information reserved additional range (26-51).
   */
  public static final String ID_MERCHANT_ACCOUNT_INFORMATION_RESERVED_ADDITIONAL_RANGE_END = "51";

  /**
   * (M) Merchant Category Code - classifies the type of business.
   */
  public static final String ID_MERCHANT_CATEGORY_CODE = "52";

  /**
   * (M) Transaction Currency - ISO 4217 numeric currency code.
   */
  public static final String ID_TRANSACTION_CURRENCY = "53";

  /**
   * (C) Transaction Amount - the amount of the transaction.
   */
  public static final String ID_TRANSACTION_AMOUNT = "54";

  /**
   * (O) Tip or Convenience Indicator - indicates if tip or convenience fee applies.
   */
  public static final String ID_TIP_OR_CONVENIENCE_INDICATOR = "55";

  /**
   * (C) Value of Convenience Fee Fixed - fixed convenience fee amount.
   */
  public static final String ID_VALUE_OF_CONVENIENCE_FEE_FIXED = "56";

  /**
   * (C) Value of Convenience Fee Percentage - percentage-based convenience fee.
   */
  public static final String ID_VALUE_OF_CONVENIENCE_FEE_PERCENTAGE = "57";

  /**
   * (M) Country Code - ISO 3166-1 numeric country code.
   */
  public static final String ID_COUNTRY_CODE = "58";

  /**
   * (M) Merchant Name - the name of the merchant.
   */
  public static final String ID_MERCHANT_NAME = "59";

  /**
   * (M) Merchant City - the city where the merchant is located.
   */
  public static final String ID_MERCHANT_CITY = "60";

  /**
   * (O) Postal Code - the postal code of the merchant location.
   */
  public static final String ID_POSTAL_CODE = "61";

  /**
   * (O) Additional Data Field Template - contains additional transaction data.
   */
  public static final String ID_ADDITIONAL_DATA_FIELD_TEMPLATE = "62";

  /**
   * (M) CRC - Cyclic Redundancy Check value for data integrity.
   */
  public static final String ID_CRC = "63";

  /**
   * (O) Merchant Information Language Template - language-specific merchant info.
   */
  public static final String ID_MERCHANT_INFORMATION_LANGUAGE_TEMPLATE = "64";

  /**
   * (O) Start of RFU (Reserved for Future Use) range for EMVCo (65-79).
   */
  public static final String ID_RFU_FOR_EMVCO_RANGE_START = "65";

  /**
   * (O) End of RFU (Reserved for Future Use) range for EMVCo (65-79).
   */
  public static final String ID_RFU_FOR_EMVCO_RANGE_END = "79";

  /**
   * (O) Start of Unreserved Templates range (80-99).
   */
  public static final String ID_UNRESERVED_TEMPLATES_RANGE_START = "80";

  /**
   * (O) End of Unreserved Templates range (80-99).
   */
  public static final String ID_UNRESERVED_TEMPLATES_RANGE_END = "99";

  /**
   * Reserved: Alias for Merchant Account Information reserved range start.
   */
  public static final String ID_MERCHANT_ACCOUNT_INFORMATION_RESERVED = ID_MERCHANT_ACCOUNT_INFORMATION_RESERVED_RANGE_START;

  /**
   * Reserved: Alias for Merchant Account Information reserved additional range start.
   */
  public static final String ID_MERCHANT_ACCOUNT_INFORMATION_RESERVED_ADDITIONAL = ID_MERCHANT_ACCOUNT_INFORMATION_RESERVED_ADDITIONAL_RANGE_START;

  /**
   * Reserved: Alias for RFU for EMVCo range start.
   */
  public static final String ID_RFU_FOR_EMVCO = ID_RFU_FOR_EMVCO_RANGE_START;

  /**
   * Reserved: Alias for Unreserved Templates range start.
   */
  public static final String ID_UNRESERVED_TEMPLATES = ID_UNRESERVED_TEMPLATES_RANGE_START;

}
