package com.emv.qrcode.model.mpm;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.model.SimpleTLV;
import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.model.mpm.constants.MerchantAccountInformationFieldCodes;

import lombok.Getter;

@Getter
public class MerchantAccountInformation implements Serializable {

  private static final long serialVersionUID = 3394308551644415429L;

  // Globally Unique Identifier
  private TagLengthString globallyUniqueIdentifier;

  // Payment network specific
  private final List<TagLengthString> paymentNetworkSpecific = new LinkedList<>();

  public final void setGloballyUniqueIdentifier(final String globallyUniqueIdentifier) {
    this.globallyUniqueIdentifier = new TagLengthString(MerchantAccountInformationFieldCodes.ID_GLOBALLY_UNIQUE_IDENTIFIER, globallyUniqueIdentifier);
  }

  public void setGloballyUniqueIdentifier(final TagLengthString globallyUniqueIdentifier) {
    this.globallyUniqueIdentifier = globallyUniqueIdentifier;
  }

  public void addPaymentNetworkSpecific(final TagLengthString tagLengthString) {
    paymentNetworkSpecific.add(tagLengthString);
  }

  @Override
  public String toString() {

    final StringBuilder sb = new StringBuilder();

    Optional.ofNullable(globallyUniqueIdentifier).ifPresent(tlv -> sb.append(tlv.toString()));

    for (final SimpleTLV<String> tagLengthString : paymentNetworkSpecific) {
      Optional.ofNullable(tagLengthString).ifPresent(tlv -> sb.append(tlv.toString()));
    }

    final String string = sb.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    return string;
  }

}
