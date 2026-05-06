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
 * Constants for Merchant Information Language Template sub-field tags. These
 * constants define the EMVCo-assigned tag values for fields within the Merchant
 * Information Language Template (ID 64) in Merchant Presented Mode.
 *
 * @see com.emv.qrcode.model.mpm.MerchantInformationLanguage
 * @see com.emv.qrcode.model.mpm.MerchantInformationLanguageTemplate
 * @since EMVCo QR Code Specification v1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MerchantInformationLanguageFieldCodes {

  /**
   * (M) Language Preference - ISO 639-1 language code.
   */
  public static final String ID_LANGUAGE_PREFERENCE = "00";

  /**
   * (M) Merchant Name - name of the merchant in the specified language.
   */
  public static final String ID_MERCHANT_NAME = "01";

  /**
   * (O) Merchant City - city where merchant is located in specified language.
   */
  public static final String ID_MERCHANT_CITY = "02";

  /**
   * (O) Start of RFU (Reserved for Future Use) range for EMVCo (03-99).
   */
  public static final String ID_RFU_FOR_EMVCO_RANGE_START = "03";

  /**
   * (O) End of RFU (Reserved for Future Use) range for EMVCo (03-99).
   */
  public static final String ID_RFU_FOR_EMVCO_RANGE_END = "99";

  /**
   * Reserved: Alias for RFU for EMVCo range start.
   */
  public static final String ID_RFU_FOR_EMVCO = ID_RFU_FOR_EMVCO_RANGE_START;

}
