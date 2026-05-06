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

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.model.mpm.TagLengthString;
import com.emv.qrcode.model.mpm.constants.MerchantAccountInformationFieldCodes;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Represents Merchant Account Information with additional fields in a Merchant Presented Mode (MPM) QR code.
 * This class includes a Globally Unique Identifier and payment network-specific data.
 *
 * @see MerchantAccountInformation
 */
@Getter
@NoArgsConstructor
public class MerchantAccountInformationReservedAdditional implements MerchantAccountInformation {

  private static final long serialVersionUID = 3394308551644415429L;

  // Globally Unique Identifier
  private TagLengthString globallyUniqueIdentifier;

  // Payment network specific
  private final Map<String, TagLengthString> paymentNetworkSpecific = new LinkedHashMap<>();

  /**
   * Constructs a MerchantAccountInformationReservedAdditional with the specified Globally Unique Identifier.
   *
   * @param globallyUniqueIdentifier the globally unique identifier value
   */
  public MerchantAccountInformationReservedAdditional(final String globallyUniqueIdentifier) {
    this.setGloballyUniqueIdentifier(globallyUniqueIdentifier);
  }

  /**
   * Constructs a MerchantAccountInformationReservedAdditional with the specified values.
   *
   * @param globallyUniqueIdentifier the globally unique identifier value
   * @param tag the payment network specific field tag
   * @param value the payment network specific field value
   */
  public MerchantAccountInformationReservedAdditional(final String globallyUniqueIdentifier, final String tag, final String value) {
    this.setGloballyUniqueIdentifier(globallyUniqueIdentifier, tag, value);
  }

  /**
   * Sets the Globally Unique Identifier (tag 00).
   *
   * @param globallyUniqueIdentifier the globally unique identifier value
   */
  public final void setGloballyUniqueIdentifier(final String globallyUniqueIdentifier) {
    this.globallyUniqueIdentifier = new TagLengthString(MerchantAccountInformationFieldCodes.ID_GLOBALLY_UNIQUE_IDENTIFIER, globallyUniqueIdentifier);
  }

  /**
   * Sets the Globally Unique Identifier and adds a payment network specific field.
   *
   * @param globallyUniqueIdentifier the globally unique identifier value
   * @param tag the payment network specific field tag
   * @param value the payment network specific field value
   */
  public final void setGloballyUniqueIdentifier(final String globallyUniqueIdentifier, final String tag, final String value) {
    this.globallyUniqueIdentifier = new TagLengthString(MerchantAccountInformationFieldCodes.ID_GLOBALLY_UNIQUE_IDENTIFIER, globallyUniqueIdentifier);
    this.addPaymentNetworkSpecific(tag, value);
  }

  /**
   * Adds a payment network specific field.
   *
   * @param tagLengthString the TagLengthString to add
   */
  public void addPaymentNetworkSpecific(final TagLengthString tagLengthString) {
    paymentNetworkSpecific.put(tagLengthString.getTag(), tagLengthString);
  }

  /**
   * Adds a payment network specific field with the specified tag and value.
   *
   * @param tag the field tag
   * @param value the field value
   */
  public void addPaymentNetworkSpecific(final String tag, final String value) {
    paymentNetworkSpecific.put(tag, new TagLengthString(tag, value));
  }

  @Override
  public String toString() {

    final StringBuilder sb = new StringBuilder();

    Optional.ofNullable(globallyUniqueIdentifier).ifPresent(tlv -> sb.append(tlv.toString()));

    for (final Entry<String, TagLengthString> entry : paymentNetworkSpecific.entrySet()) {
      Optional.ofNullable(entry.getValue()).ifPresent(tlv -> sb.append(tlv.toString()));
    }

    final String string = sb.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    return string;
  }

}
