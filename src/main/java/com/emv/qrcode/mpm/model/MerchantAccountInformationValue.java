package com.emv.qrcode.mpm.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.mpm.constants.MerchantAccountInformationFieldCodes;
import com.emv.qrcode.parsers.Parser;

import lombok.Getter;

@Getter
public class MerchantAccountInformationValue implements Serializable {

  private static final long serialVersionUID = 3394308551644415429L;

  // Globally Unique Identifier
  private TagLengthString globallyUniqueIdentifier;

  // Payment network specific
  private final List<TagLengthString> paymentNetworkSpecific = new LinkedList<>();

  public void setGloballyUniqueIdentifier(final String value) {
    globallyUniqueIdentifier = new TagLengthString(MerchantAccountInformationFieldCodes.MERCHANT_ACCOUNT_INFORMATION_ID_GLOBALLY_UNIQUE_IDENTIFIER, value);
  }

  public void addPaymentNetworkSpecific(final String value) {
    paymentNetworkSpecific.add(new TagLengthString(value.substring(0, Parser.ID_WORD_COUNT), value.substring(Parser.ID_WORD_COUNT)));
  }

  @Override
  public String toString() {

    final StringBuilder sb = new StringBuilder();

    Optional.ofNullable(globallyUniqueIdentifier).ifPresent(tlv -> sb.append(tlv.toString()));

    for (final TagLengthString tagLengthString : paymentNetworkSpecific) {
      Optional.ofNullable(tagLengthString).ifPresent(tlv -> sb.append(tlv.toString()));
    }

    final String string = sb.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    return string;
  }

}
