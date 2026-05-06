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
import com.emv.qrcode.model.mpm.constants.MerchantAccountInformationFieldCodes;

import lombok.Getter;

/**
 * Represents Payment System Specific data in a Merchant Presented Mode (MPM) QR code.
 * This class contains a Globally Unique Identifier and payment system-specific data fields.
 *
 * @see TagLengthString
 */
@Getter
public class PaymentSystemSpecific implements Serializable {

  private static final long serialVersionUID = 6244729218605588349L;

  // Globally Unique Identifier
  private TagLengthString globallyUniqueIdentifier;

  // Context Specific Data
  private final Map<String, TagLengthString> paymentSystemSpecific = new LinkedHashMap<>();

  /**
   * Constructs an empty PaymentSystemSpecific.
   */
  public PaymentSystemSpecific() {
    super();
  }

  /**
   * Constructs a PaymentSystemSpecific with the specified Globally Unique Identifier.
   *
   * @param globallyUniqueIdentifier the globally unique identifier value
   */
  public PaymentSystemSpecific(final String globallyUniqueIdentifier) {
    this.setGloballyUniqueIdentifier(globallyUniqueIdentifier);
  }

  /**
   * Constructs a PaymentSystemSpecific with the specified values.
   *
   * @param globallyUniqueIdentifier the globally unique identifier value
   * @param tag the payment system specific field tag
   * @param value the payment system specific field value
   */
  public PaymentSystemSpecific(final String globallyUniqueIdentifier, final String tag, final String value) {
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
   * Sets the Globally Unique Identifier and adds a payment system specific field.
   *
   * @param globallyUniqueIdentifier the globally unique identifier value
   * @param paymentSystemSpecific the payment system specific field to add
   */
  public final void setGloballyUniqueIdentifier(final String globallyUniqueIdentifier, final TagLengthString paymentSystemSpecific) {
    this.globallyUniqueIdentifier = new TagLengthString(MerchantAccountInformationFieldCodes.ID_GLOBALLY_UNIQUE_IDENTIFIER, globallyUniqueIdentifier);
    this.addPaymentSystemSpecific(paymentSystemSpecific);
  }

  /**
   * Sets the Globally Unique Identifier and adds a payment system specific field.
   *
   * @param globallyUniqueIdentifier the globally unique identifier value
   * @param tag the payment system specific field tag
   * @param value the payment system specific field value
   */
  public final void setGloballyUniqueIdentifier(final String globallyUniqueIdentifier, final String tag, final String value) {
    this.globallyUniqueIdentifier = new TagLengthString(MerchantAccountInformationFieldCodes.ID_GLOBALLY_UNIQUE_IDENTIFIER, globallyUniqueIdentifier);
    this.addPaymentSystemSpecific(tag, value);
  }

  /**
   * Adds a payment system specific field.
   *
   * @param tagLengthString the TagLengthString to add
   */
  public void addPaymentSystemSpecific(final TagLengthString tagLengthString) {
    paymentSystemSpecific.put(tagLengthString.getTag(), tagLengthString);
  }

  /**
   * Adds a payment system specific field with the specified tag and value.
   *
   * @param tag the field tag
   * @param value the field value
   */
  public void addPaymentSystemSpecific(final String tag, final String value) {
    paymentSystemSpecific.put(tag, new TagLengthString(tag, value));
  }

  @Override
  public String toString() {

    final StringBuilder sb = new StringBuilder();

    Optional.ofNullable(globallyUniqueIdentifier).ifPresent(tlv -> sb.append(tlv.toString()));

    for (final Entry<String, TagLengthString> entry : paymentSystemSpecific.entrySet()) {
      Optional.ofNullable(entry.getValue()).ifPresent(tlv -> sb.append(tlv.toString()));
    }

    final String string = sb.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    return string;
  }

}
