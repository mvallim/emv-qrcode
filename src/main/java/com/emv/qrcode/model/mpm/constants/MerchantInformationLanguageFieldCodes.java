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

public class MerchantInformationLanguageFieldCodes {

  public static final String ID_LANGUAGE_PREFERENCE = "00"; // (M) Language Preference
  public static final String ID_MERCHANT_NAME = "01"; // (M) Merchant Name
  public static final String ID_MERCHANT_CITY = "02"; // (O) Merchant City
  public static final String ID_RFU_FOR_EMVCO_RANGE_START = "03"; // (O) 03-99 RFU for EMVCo
  public static final String ID_RFU_FOR_EMVCO_RANGE_END = "99"; // (O) 03-99 RFU for EMVCo

  // Reserved
  public static final String ID_RFU_FOR_EMVCO = ID_RFU_FOR_EMVCO_RANGE_START;

  private MerchantInformationLanguageFieldCodes() {
    super();
  }

}
