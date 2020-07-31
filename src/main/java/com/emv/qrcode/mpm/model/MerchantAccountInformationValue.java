package com.emv.qrcode.mpm.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.emv.qrcode.core.model.DataType;
import com.emv.qrcode.core.model.DrawData;
import com.emv.qrcode.mpm.constants.MerchantAccountInformationFieldCodes;

import lombok.Getter;

@Getter
public class MerchantAccountInformationValue implements Serializable, DrawData {

  private static final long serialVersionUID = 3394308551644415429L;

  // Globally Unique Identifier
  private TagLengthString globallyUniqueIdentifier;

  // Payment network specific
  private final List<TagLengthString> paymentNetworkSpecific = new LinkedList<>();

  public void setGloballyUniqueIdentifier(final String value) {
    globallyUniqueIdentifier = new TagLengthString(MerchantAccountInformationFieldCodes.MERCHANT_ACCOUNT_INFORMATION_ID_GLOBALLY_UNIQUE_IDENTIFIER, value);
  }

  // TODO: Implements this after
  public void addPaymentNetworkSpecific(final String value) {
    paymentNetworkSpecific.add(new TagLengthString("", value));
  }

  public void addPaymentNetworkSpecifics(final List<String> values) {
    if (Objects.nonNull(values) && !values.isEmpty()) {
      values.forEach(this::addPaymentNetworkSpecific);
    }
  }

  @Override
  public String toString() {

    final StringBuilder sb = new StringBuilder();

    Optional.ofNullable(globallyUniqueIdentifier).ifPresent(tlv -> sb.append(tlv.toString()));

    for (final TagLengthString tagLengthString : paymentNetworkSpecific) {
      Optional.ofNullable(tagLengthString).ifPresent(tlv -> sb.append(tlv.toString()));
    }

    return sb.toString();
  }

  @Override
  public String draw(final DataType type) {

    final StringBuilder sb = new StringBuilder();

    Optional.ofNullable(globallyUniqueIdentifier).ifPresent(tlv -> sb.append(tlv.draw(type)));

    for (final TagLengthString tagLengthString : paymentNetworkSpecific) {
      Optional.ofNullable(tagLengthString).ifPresent(tlv -> sb.append(tlv.draw(type)));
    }

    return sb.toString();
  }

}
