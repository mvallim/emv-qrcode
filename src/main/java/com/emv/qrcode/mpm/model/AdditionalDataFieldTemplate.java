package com.emv.qrcode.mpm.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.decoder.Decoder;
import com.emv.qrcode.mpm.constants.AdditionalDataFieldCodes;
import com.emv.qrcode.mpm.constants.EMVQRFieldCodes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdditionalDataFieldTemplate implements Serializable {

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

  // Additional Consumer Data Request
  private TagLengthString additionalConsumerDataRequest;

  // RFU for EMVCo
  private final List<TagLengthString> rFUforEMVCo = new LinkedList<>();

  // Payment System specific templates
  private final List<TagLengthString> paymentSystemSpecific = new LinkedList<>();

  public void setBillNumber(final String value) {
    this.billNumber = new TagLengthString(AdditionalDataFieldCodes.ID_BILL_NUMBER, value);
  }

  public void setMobileNumber(final String value) {
    this.mobileNumber = new TagLengthString(AdditionalDataFieldCodes.ID_MOBILE_NUMBER, value);
  }

  public void setStoreLabel(final String value) {
    this.storeLabel = new TagLengthString(AdditionalDataFieldCodes.ID_STORE_LABEL, value);
  }

  public void setLoyaltyNumber(final String value) {
    this.loyaltyNumber = new TagLengthString(AdditionalDataFieldCodes.ID_LOYALTY_NUMBER, value);
  }

  public void setReferenceLabel(final String value) {
    this.referenceLabel = new TagLengthString(AdditionalDataFieldCodes.ID_REFERENCE_LABEL, value);
  }

  public void setCustomerLabel(final String value) {
    this.customerLabel = new TagLengthString(AdditionalDataFieldCodes.ID_CUSTOMER_LABEL, value);
  }

  public void setTerminalLabel(final String value) {
    this.terminalLabel = new TagLengthString(AdditionalDataFieldCodes.ID_TERMINAL_LABEL, value);
  }

  public void setPurposeTransaction(final String value) {
    this.purposeTransaction = new TagLengthString(AdditionalDataFieldCodes.ID_PURPOSE_TRANSACTION, value);
  }

  public void setAdditionalConsumerDataRequest(final String value) {
    this.additionalConsumerDataRequest = new TagLengthString(AdditionalDataFieldCodes.ID_ADDITIONAL_CONSUMER_DATA_REQUEST, value);
  }
  
  public void addRFUforEMVCo(final String value) {
    this.rFUforEMVCo.add(new TagLengthString(value.substring(0, Decoder.ID_WORD_COUNT), value.substring(Decoder.ID_WORD_COUNT)));
  }
  
  public void addPaymentSystemSpecific(final String value) {
    this.paymentSystemSpecific.add(new TagLengthString(value.substring(0, Decoder.ID_WORD_COUNT), value.substring(Decoder.ID_WORD_COUNT)));
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

    for (final TagLengthString tagLengthString : rFUforEMVCo) {
      Optional.ofNullable(tagLengthString).ifPresent(tlv -> sb.append(tlv.toString()));
    }

    for (final TagLengthString tagLengthString : paymentSystemSpecific) {
      Optional.ofNullable(tagLengthString).ifPresent(tlv -> sb.append(tlv.toString()));
    }

    final String string = sb.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }
    
    return String.format("%s%02d%s", EMVQRFieldCodes.ID_ADDITIONAL_DATA_FIELD_TEMPLATE, string.length(), string);
  }

}
