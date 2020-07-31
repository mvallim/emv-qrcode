package com.emv.qrcode.mpm.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.emv.qrcode.core.Parser;
import com.emv.qrcode.core.ParserAdditionalDataFieldTemplate;
import com.emv.qrcode.core.model.DataType;
import com.emv.qrcode.core.model.DrawData;
import com.emv.qrcode.mpm.constants.AdditionalDataFieldCodes;
import com.emv.qrcode.mpm.constants.EMVQRFieldCodes;

import lombok.Getter;

@Getter
public class AdditionalDataFieldTemplate implements Serializable, DrawData {

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

  AdditionalDataFieldTemplate(final String value) {
    ParserAdditionalDataFieldTemplate.parse(value, this);
  }

  public void setBillNumber(final String value) {
    this.billNumber = new TagLengthString(AdditionalDataFieldCodes.ADDITIONAL_ID_BILL_NUMBER, value);
  }

  public void setMobileNumber(final String value) {
    this.mobileNumber = new TagLengthString(AdditionalDataFieldCodes.ADDITIONAL_ID_MOBILE_NUMBER, value);
  }

  public void setStoreLabel(final String value) {
    this.storeLabel = new TagLengthString(AdditionalDataFieldCodes.ADDITIONAL_ID_STORE_LABEL, value);
  }

  public void setLoyaltyNumber(final String value) {
    this.loyaltyNumber = new TagLengthString(AdditionalDataFieldCodes.ADDITIONAL_ID_LOYALTY_NUMBER, value);
  }

  public void setReferenceLabel(final String value) {
    this.referenceLabel = new TagLengthString(AdditionalDataFieldCodes.ADDITIONAL_ID_REFERENCE_LABEL, value);
  }

  public void setCustomerLabel(final String value) {
    this.customerLabel = new TagLengthString(AdditionalDataFieldCodes.ADDITIONAL_ID_CUSTOMER_LABEL, value);
  }

  public void setTerminalLabel(final String value) {
    this.terminalLabel = new TagLengthString(AdditionalDataFieldCodes.ADDITIONAL_ID_TERMINAL_LABEL, value);
  }

  public void setPurposeTransaction(final String value) {
    this.purposeTransaction = new TagLengthString(AdditionalDataFieldCodes.ADDITIONAL_ID_PURPOSE_TRANSACTION, value);
  }

  public void setAdditionalConsumerDataRequest(final String value) {
    this.additionalConsumerDataRequest = new TagLengthString(AdditionalDataFieldCodes.ADDITIONAL_ID_ADDITIONAL_CONSUMER_DATA_REQUEST, value);
  }
  
  public void addRFUforEMVCo(final String value) {
    this.rFUforEMVCo.add(new TagLengthString(value.substring(0, Parser.ID_WORD_COUNT), value.substring(Parser.ID_WORD_COUNT)));
  }
  
  public void addPaymentSystemSpecific(final String value) {
    this.paymentSystemSpecific.add(new TagLengthString(value.substring(0, Parser.ID_WORD_COUNT), value.substring(Parser.ID_WORD_COUNT)));
  }
  
  @Override
  public String toString() {

    final StringBuilder sb = new StringBuilder();

    sb.append(EMVQRFieldCodes.ID_ADDITIONAL_DATA_FIELD_TEMPLATE);

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

    return sb.toString();
  }

  @Override
  @SuppressWarnings("all")
  public String draw(final DataType type) {

    final StringBuilder sb = new StringBuilder();

    Optional.ofNullable(billNumber).ifPresent(tlv -> sb.append(tlv.draw(type)));
    Optional.ofNullable(mobileNumber).ifPresent(tlv -> sb.append(tlv.draw(type)));
    Optional.ofNullable(storeLabel).ifPresent(tlv -> sb.append(tlv.draw(type)));
    Optional.ofNullable(loyaltyNumber).ifPresent(tlv -> sb.append(tlv.draw(type)));
    Optional.ofNullable(referenceLabel).ifPresent(tlv -> sb.append(tlv.draw(type)));
    Optional.ofNullable(customerLabel).ifPresent(tlv -> sb.append(tlv.draw(type)));
    Optional.ofNullable(terminalLabel).ifPresent(tlv -> sb.append(tlv.draw(type)));
    Optional.ofNullable(purposeTransaction).ifPresent(tlv -> sb.append(tlv.draw(type)));
    Optional.ofNullable(additionalConsumerDataRequest).ifPresent(tlv -> sb.append(tlv.draw(type)));

    for (final TagLengthString tagLengthString : rFUforEMVCo) {
      Optional.ofNullable(tagLengthString).ifPresent(tlv -> sb.append(tlv.draw(type)));
    }

    for (final TagLengthString tagLengthString : paymentSystemSpecific) {
      Optional.ofNullable(tagLengthString).ifPresent(tlv -> sb.append(tlv.draw(type)));
    }

    return String.format("%s %02d \n%s", EMVQRFieldCodes.ID_ADDITIONAL_DATA_FIELD_TEMPLATE, toString().length(), sb.toString());
  }

}
