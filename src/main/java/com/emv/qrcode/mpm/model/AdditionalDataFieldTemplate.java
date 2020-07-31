package com.emv.qrcode.mpm.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.emv.qrcode.core.DataType;
import com.emv.qrcode.core.DrawData;
import com.emv.qrcode.mpm.constants.EMVQRConstants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
  private List<TagLengthString> rFUforEMVCo = new LinkedList<>();

  // Payment System specific templates
  private List<TagLengthString> paymentSystemSpecific = new LinkedList<>();
  
  @Override
  public String toString() {
    
    final StringBuilder sb = new StringBuilder();
    
    sb.append(EMVQRConstants.ID_ADDITIONAL_DATA_FIELD_TEMPLATE);

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
    
    return String.format("%s %02d \n%s", EMVQRConstants.ID_ADDITIONAL_DATA_FIELD_TEMPLATE, toString().length(), sb.toString());
  }

}
