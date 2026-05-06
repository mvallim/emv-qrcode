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

package com.emv.qrcode.model.mpm;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.model.mpm.TagLengthString;
import com.emv.qrcode.model.mpm.constants.MerchantInformationLanguageFieldCodes;

import lombok.Getter;

/**
 * Represents Merchant Information Language data in a Merchant Presented Mode (MPM) QR code.
 * This class contains language-specific merchant information like language preference, name, and city.
 *
 * @see TagLengthString
 */
@Getter
public class MerchantInformationLanguage implements Serializable {

  private static final long serialVersionUID = 6163271793010568887L;

  // Language Preference
  private TagLengthString languagePreference;

  // Merchant Name
  private TagLengthString merchantName;

  // Merchant City
  private TagLengthString merchantCity;

  // RFU for EMVCo
  private final Map<String, TagLengthString> rFUforEMVCo = new LinkedHashMap<>();

  /**
   * Sets the Language Preference (tag 00).
   *
   * @param languagePreference the language preference (ISO 639-1 code)
   */
  public final void setLanguagePreference(final String languagePreference) {
    this.languagePreference = new TagLengthString(MerchantInformationLanguageFieldCodes.ID_LANGUAGE_PREFERENCE, languagePreference);
  }

  /**
   * Sets the Merchant Name (tag 01).
   *
   * @param merchantName the merchant name
   */
  public final void setMerchantName(final String merchantName) {
    this.merchantName = new TagLengthString(MerchantInformationLanguageFieldCodes.ID_MERCHANT_NAME, merchantName);
  }

  /**
   * Sets the Merchant City (tag 02).
   *
   * @param merchantCity the merchant city
   */
  public final void setMerchantCity(final String merchantCity) {
    this.merchantCity = new TagLengthString(MerchantInformationLanguageFieldCodes.ID_MERCHANT_CITY, merchantCity);
  }

  /**
   * Adds a Reserved for Future Use (RFU) field for EMVCo.
   *
   * @param tagLengthString the RFU field to add
   */
  public final void addRFUforEMVCo(final TagLengthString tagLengthString) {
    rFUforEMVCo.put(tagLengthString.getTag(), tagLengthString);
  }

  @Override
  public String toString() {

    final StringBuilder sb = new StringBuilder();

    Optional.ofNullable(languagePreference).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(merchantName).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(merchantCity).ifPresent(tlv -> sb.append(tlv.toString()));

    for (final Entry<String, TagLengthString> entry : rFUforEMVCo.entrySet()) {
      Optional.ofNullable(entry.getValue()).ifPresent(tlv -> sb.append(tlv.toString()));
    }

    final String string = sb.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    return string;
  }

}
