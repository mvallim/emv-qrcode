package com.emv.qrcode.model.mpm;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.model.SimpleTLV;
import com.emv.qrcode.core.model.TagLengthString;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdditionalDataFieldValue implements Serializable {

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
  private final List<TagLengthString> rFUforEMVCo = new LinkedList<>();

  // Payment System specific templates
  private final List<TagLengthString> paymentSystemSpecific = new LinkedList<>();

  public void addRFUforEMVCo(final TagLengthString rFUforEMVCo) {
    this.rFUforEMVCo.add(rFUforEMVCo);
  }

  public void addPaymentSystemSpecific(final TagLengthString paymentSystemSpecific) {
    this.paymentSystemSpecific.add(paymentSystemSpecific);
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

    for (final SimpleTLV<String> tagLengthString : rFUforEMVCo) {
      Optional.ofNullable(tagLengthString).ifPresent(tlv -> sb.append(tlv.toString()));
    }

    for (final SimpleTLV<String> tagLengthString : paymentSystemSpecific) {
      Optional.ofNullable(tagLengthString).ifPresent(tlv -> sb.append(tlv.toString()));
    }

    final String string = sb.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    return string;
  }

}
