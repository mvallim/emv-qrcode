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

package com.emv.qrcode.model.mpm.constants;

public final class MerchantPresentedModeCodes {

  public static final String ID_PAYLOAD_FORMAT_INDICATOR = "00"; // (M) Payload Format Indicator
  public static final String ID_POINT_OF_INITIATION_METHOD = "01"; // (O) Point of Initiation Method
  public static final String ID_MERCHANT_ACCOUNT_INFORMATION_RESERVED_RANGE_START = "02"; // (M) 2-25 Merchant Account Information
  public static final String ID_MERCHANT_ACCOUNT_INFORMATION_RESERVED_RANGE_END = "25"; // (M) 2-26 Merchant Account Information
  public static final String ID_MERCHANT_ACCOUNT_INFORMATION_RESERVED_ADDITIONAL_RANGE_START = "26"; // (M) 26-51 Merchant Account Information
  public static final String ID_MERCHANT_ACCOUNT_INFORMATION_RESERVED_ADDITIONAL_RANGE_END = "51"; // (M) 26-51 Merchant Account Information
  public static final String ID_MERCHANT_CATEGORY_CODE = "52"; // (M) Merchant Category Code
  public static final String ID_TRANSACTION_CURRENCY = "53"; // (M) Transaction Currency
  public static final String ID_TRANSACTION_AMOUNT = "54"; // (C) Transaction Amount
  public static final String ID_TIP_OR_CONVENIENCE_INDICATOR = "55"; // (O) Tip or Convenience Indicator
  public static final String ID_VALUE_OF_CONVENIENCE_FEE_FIXED = "56"; // (C) Value of Convenience Fee Fixed
  public static final String ID_VALUE_OF_CONVENIENCE_FEE_PERCENTAGE = "57"; // (C) Value of Convenience Fee Percentage
  public static final String ID_COUNTRY_CODE = "58"; // (M) Country Code
  public static final String ID_MERCHANT_NAME = "59"; // (M) Merchant Name
  public static final String ID_MERCHANT_CITY = "60"; // (M) Merchant City
  public static final String ID_POSTAL_CODE = "61"; // (O) Postal Code
  public static final String ID_ADDITIONAL_DATA_FIELD_TEMPLATE = "62"; // (O) Additional Data Field Template
  public static final String ID_CRC = "63"; // (M) CRC
  public static final String ID_MERCHANT_INFORMATION_LANGUAGE_TEMPLATE = "64"; // (O) Merchant Information— Language Template
  public static final String ID_RFU_FOR_EMVCO_RANGE_START = "65"; // (O) 65-79 RFU for EMVCo
  public static final String ID_RFU_FOR_EMVCO_RANGE_END = "79"; // (O) 65-79 RFU for EMVCo
  public static final String ID_UNRESERVED_TEMPLATES_RANGE_START = "80"; // (O) 80-99 Unreserved Templates
  public static final String ID_UNRESERVED_TEMPLATES_RANGE_END = "99"; // (O) 80-99 Unreserved Templates

  // Reserved
  public static final String ID_MERCHANT_ACCOUNT_INFORMATION_RESERVED = ID_MERCHANT_ACCOUNT_INFORMATION_RESERVED_RANGE_START;
  public static final String ID_MERCHANT_ACCOUNT_INFORMATION_RESERVED_ADDITIONAL = ID_MERCHANT_ACCOUNT_INFORMATION_RESERVED_ADDITIONAL_RANGE_START;
  public static final String ID_RFU_FOR_EMVCO = ID_RFU_FOR_EMVCO_RANGE_START;
  public static final String ID_UNRESERVED_TEMPLATES = ID_UNRESERVED_TEMPLATES_RANGE_START;

  private MerchantPresentedModeCodes() {
    super();
  }

}
