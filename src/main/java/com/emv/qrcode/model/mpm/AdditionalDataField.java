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

@Getter
public class AdditionalDataField implements Serializable {

  private static final long serialVersionUID = -6651622119486438559L;

  // Bill Number
  private TagLengthString billNumber;

  // Country Code
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

  // Additional TagLengthString Data Request
  private TagLengthString additionalConsumerDataRequest;

  // RFU for EMVCo
  private final Map<String, TagLengthString> rFUforEMVCo = new LinkedHashMap<>();

  // Payment System specific templates
  private final Map<String, PaymentSystemSpecificTemplate> paymentSystemSpecific = new LinkedHashMap<>();

  public final void setBillNumber(final String billNumber) {
    this.billNumber = new TagLengthString(AdditionalDataFieldCodes.ID_BILL_NUMBER, billNumber);
  }

  public final void setMobileNumber(final String mobileNumber) {
    this.mobileNumber = new TagLengthString(AdditionalDataFieldCodes.ID_MOBILE_NUMBER, mobileNumber);
  }

  public final void setStoreLabel(final String storeLabel) {
    this.storeLabel = new TagLengthString(AdditionalDataFieldCodes.ID_STORE_LABEL, storeLabel);
  }

  public final void setLoyaltyNumber(final String loyaltyNumber) {
    this.loyaltyNumber = new TagLengthString(AdditionalDataFieldCodes.ID_LOYALTY_NUMBER, loyaltyNumber);
  }

  public final void setReferenceLabel(final String referenceLabel) {
    this.referenceLabel = new TagLengthString(AdditionalDataFieldCodes.ID_REFERENCE_LABEL, referenceLabel);
  }

  public final void setCustomerLabel(final String customerLabel) {
    this.customerLabel = new TagLengthString(AdditionalDataFieldCodes.ID_CUSTOMER_LABEL, customerLabel);
  }

  public final void setTerminalLabel(final String terminalLabel) {
    this.terminalLabel = new TagLengthString(AdditionalDataFieldCodes.ID_TERMINAL_LABEL, terminalLabel);
  }

  public final void setPurposeTransaction(final String purposeTransaction) {
    this.purposeTransaction = new TagLengthString(AdditionalDataFieldCodes.ID_PURPOSE_TRANSACTION, purposeTransaction);
  }

  public final void setAdditionalConsumerDataRequest(final String additionalConsumerDataRequest) {
    this.additionalConsumerDataRequest = new TagLengthString(AdditionalDataFieldCodes.ID_ADDITIONAL_CONSUMER_DATA_REQUEST, additionalConsumerDataRequest);
  }

  public final void addRFUforEMVCo(final TagLengthString rFUforEMVCo) {
    this.rFUforEMVCo.put(rFUforEMVCo.getTag(), rFUforEMVCo);
  }

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
