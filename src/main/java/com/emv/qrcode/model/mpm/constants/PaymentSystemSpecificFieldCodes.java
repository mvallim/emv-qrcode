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
 * Constants for Payment System Specific template sub-field tags. These
 * constants define the EMVCo-assigned tag values for fields within Payment
 * System Specific templates in Merchant Presented Mode.
 *
 * @see com.emv.qrcode.model.mpm.PaymentSystemSpecific
 * @see com.emv.qrcode.model.mpm.PaymentSystemSpecificTemplate
 * @since EMVCo QR Code Specification v1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PaymentSystemSpecificFieldCodes {

  /**
   * Globally Unique Identifier - identifies the payment system.
   */
  public static final String ID_GLOBALLY_UNIQUE_IDENTIFIER = "00";

  /**
   * Start of Payment Network Specific data range (01-99).
   */
  public static final String ID_PAYMENT_NETWORK_SPECIFIC_START = "01";

  /**
   * End of Payment Network Specific data range (01-99).
   */
  public static final String ID_PAYMENT_NETWORK_SPECIFIC_END = "99";

  /**
   * Reserved: Alias for Payment Network Specific data range start.
   */
  public static final String ID_PAYMENT_NETWORK_SPECIFIC = ID_PAYMENT_NETWORK_SPECIFIC_START;

}
