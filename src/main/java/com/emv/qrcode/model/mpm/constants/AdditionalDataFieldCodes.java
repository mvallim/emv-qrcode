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
 * Constants for Additional Data Field template sub-field tags. These constants
 * define the EMVCo-assigned tag values for fields within the Additional Data
 * Field Template (ID 62) in Merchant Presented Mode.
 *
 * @see com.emv.qrcode.model.mpm.AdditionalDataField
 * @since EMVCo QR Code Specification v1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AdditionalDataFieldCodes {

  /**
   * (O) Bill Number - reference number for the bill or invoice.
   */
  public static final String ID_BILL_NUMBER = "01";

  /**
   * (O) Mobile Number - customer's mobile phone number.
   */
  public static final String ID_MOBILE_NUMBER = "02";

  /**
   * (O) Store Label - identifier or label for the store.
   */
  public static final String ID_STORE_LABEL = "03";

  /**
   * (O) Loyalty Number - customer's loyalty program identifier.
   */
  public static final String ID_LOYALTY_NUMBER = "04";

  /**
   * (O) Reference Label - additional reference information.
   */
  public static final String ID_REFERENCE_LABEL = "05";

  /**
   * (O) Customer Label - identifier for the customer.
   */
  public static final String ID_CUSTOMER_LABEL = "06";

  /**
   * (O) Terminal Label - identifier for the terminal.
   */
  public static final String ID_TERMINAL_LABEL = "07";

  /**
   * (O) Purpose Transaction - purpose of the transaction.
   */
  public static final String ID_PURPOSE_TRANSACTION = "08";

  /**
   * (O) Additional Consumer Data Request - requests additional consumer data.
   */
  public static final String ID_ADDITIONAL_CONSUMER_DATA_REQUEST = "09";

  /**
   * (O) Start of RFU (Reserved for Future Use) range for EMVCo (10-49).
   */
  public static final String ID_RFU_FOR_EMVCO_RANGE_START = "10";

  /**
   * (O) End of RFU (Reserved for Future Use) range for EMVCo (10-49).
   */
  public static final String ID_RFU_FOR_EMVCO_RANGE_END = "49";

  /**
   * (O) Start of Payment System Specific Templates range (50-99).
   */
  public static final String ID_PAYMENT_SYSTEM_SPECIFIC_TEMPLATES_RANGE_START = "50";

  /**
   * (O) End of Payment System Specific Templates range (50-99).
   */
  public static final String ID_PAYMENT_SYSTEM_SPECIFIC_TEMPLATES_RANGE_END = "99";

  /**
   * Reserved: Alias for RFU for EMVCo range start.
   */
  public static final String ID_RFU_FOR_EMVCO = ID_RFU_FOR_EMVCO_RANGE_START;

  /**
   * Reserved: Alias for Payment System Specific range start.
   */
  public static final String ID_PAYMENT_SYSTEM_SPECIFIC = ID_PAYMENT_SYSTEM_SPECIFIC_TEMPLATES_RANGE_START;

}
