package com.emv.qrcode.mpm.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.emv.qrcode.core.DataType;
import com.emv.qrcode.core.DrawData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchantAccountInformationValue implements Serializable, DrawData {

  private static final long serialVersionUID = 3394308551644415429L;

  // Globally Unique Identifier
  private TagLengthString globallyUniqueIdentifier;

  // Payment network specific
  private List<TagLengthString> paymentNetworkSpecific = new LinkedList<>();
  
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
