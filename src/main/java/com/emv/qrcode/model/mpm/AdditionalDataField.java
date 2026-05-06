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
import com.emv.qrcode.model.mpm.constants.AdditionalDataFieldCodes;

import lombok.Getter;

/**
 * Represents Additional Data Field in a Merchant Presented Mode (MPM) QR code.
 * This class contains various optional data fields like bill number, mobile number, store label, etc.
 *
 * @see TagLengthString
 * @see PaymentSystemSpecificTemplate
 */
@Getter
public class AdditionalDataField implements Serializable {

  private static final long serialVersionUID = -6651622119486438559L;

  // Bill Number
  private TagLengthString billNumber;

  // Mobile Number
  private TagLengthString mobileNumber;

  // Store Label
  private TagLengthString storeLabel;

  // Loyalty Number
  private TagLengthString loyaltyNumber;

  // Reference Label
  private TagLengthString referenceLabel;

  // Customer Label
  private TagLengthString customerLabel;

  // Terminal Label
  private TagLengthString terminalLabel;

  // Purpose of Transaction
  private TagLengthString purposeTransaction;

  // Additional Consumer Data Request
  private TagLengthString additionalConsumerDataRequest;

  // RFU for EMVCo
  private final Map<String, TagLengthString> rFUforEMVCo = new LinkedHashMap<>();

  // Payment System specific templates
  private final Map<String, PaymentSystemSpecificTemplate> paymentSystemSpecific = new LinkedHashMap<>();

  /**
   * Sets the Bill Number (tag 01).
   *
   * @param billNumber the bill number
   */
  public final void setBillNumber(final String billNumber) {
    this.billNumber = new TagLengthString(AdditionalDataFieldCodes.ID_BILL_NUMBER, billNumber);
  }

  /**
   * Sets the Mobile Number (tag 02).
   *
   * @param mobileNumber the mobile number
   */
  public final void setMobileNumber(final String mobileNumber) {
    this.mobileNumber = new TagLengthString(AdditionalDataFieldCodes.ID_MOBILE_NUMBER, mobileNumber);
  }

  /**
   * Sets the Store Label (tag 03).
   *
   * @param storeLabel the store label
   */
  public final void setStoreLabel(final String storeLabel) {
    this.storeLabel = new TagLengthString(AdditionalDataFieldCodes.ID_STORE_LABEL, storeLabel);
  }

  /**
   * Sets the Loyalty Number (tag 04).
   *
   * @param loyaltyNumber the loyalty number
   */
  public final void setLoyaltyNumber(final String loyaltyNumber) {
    this.loyaltyNumber = new TagLengthString(AdditionalDataFieldCodes.ID_LOYALTY_NUMBER, loyaltyNumber);
  }

  /**
   * Sets the Reference Label (tag 05).
   *
   * @param referenceLabel the reference label
   */
  public final void setReferenceLabel(final String referenceLabel) {
    this.referenceLabel = new TagLengthString(AdditionalDataFieldCodes.ID_REFERENCE_LABEL, referenceLabel);
  }

  /**
   * Sets the Customer Label (tag 06).
   *
   * @param customerLabel the customer label
   */
  public final void setCustomerLabel(final String customerLabel) {
    this.customerLabel = new TagLengthString(AdditionalDataFieldCodes.ID_CUSTOMER_LABEL, customerLabel);
  }

  /**
   * Sets the Terminal Label (tag 07).
   *
   * @param terminalLabel the terminal label
   */
  public final void setTerminalLabel(final String terminalLabel) {
    this.terminalLabel = new TagLengthString(AdditionalDataFieldCodes.ID_TERMINAL_LABEL, terminalLabel);
  }

  /**
   * Sets the Purpose of Transaction (tag 08).
   *
   * @param purposeTransaction the purpose of transaction
   */
  public final void setPurposeTransaction(final String purposeTransaction) {
    this.purposeTransaction = new TagLengthString(AdditionalDataFieldCodes.ID_PURPOSE_TRANSACTION, purposeTransaction);
  }

  /**
   * Sets the Additional Consumer Data Request (tag 09).
   *
   * @param additionalConsumerDataRequest the additional consumer data request
   */
  public final void setAdditionalConsumerDataRequest(final String additionalConsumerDataRequest) {
    this.additionalConsumerDataRequest = new TagLengthString(AdditionalDataFieldCodes.ID_ADDITIONAL_CONSUMER_DATA_REQUEST, additionalConsumerDataRequest);
  }

  /**
   * Adds a Reserved for Future Use (RFU) field for EMVCo.
   *
   * @param rFUforEMVCo the RFU field to add
   */
  public final void addRFUforEMVCo(final TagLengthString rFUforEMVCo) {
    this.rFUforEMVCo.put(rFUforEMVCo.getTag(), rFUforEMVCo);
  }

  /**
   * Adds a Payment System Specific template.
   *
   * @param paymentSystemSpecific the payment system specific template to add
   */
  public final void addPaymentSystemSpecific(final PaymentSystemSpecificTemplate paymentSystemSpecific) {
    this.paymentSystemSpecific.put(paymentSystemSpecific.getTag(), paymentSystemSpecific);
  }

  @Override
  public String toString() {

    final StringBuilder sb = new StringBuilder();

    Optional.ofNullable(billNumber).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(mobileNumber).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(storeLabel).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(loyaltyNumber).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(referenceLabel).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(customerLabel).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(terminalLabel).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(purposeTransaction).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(additionalConsumerDataRequest).ifPresent(tlv -> sb.append(tlv.toString()));

    for (final Entry<String, TagLengthString> entry : rFUforEMVCo.entrySet()) {
      Optional.ofNullable(entry.getValue()).ifPresent(tlv -> sb.append(tlv.toString()));
    }

    for (final Entry<String, PaymentSystemSpecificTemplate> entry : paymentSystemSpecific.entrySet()) {
      Optional.ofNullable(entry.getValue()).ifPresent(tlv -> sb.append(tlv.toString()));
    }

    final String string = sb.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    return string;
  }

}
