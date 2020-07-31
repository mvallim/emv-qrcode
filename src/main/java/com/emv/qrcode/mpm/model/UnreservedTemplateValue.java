package com.emv.qrcode.mpm.model;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import com.emv.qrcode.core.DataType;
import com.emv.qrcode.core.DrawData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnreservedTemplateValue implements Serializable, DrawData {

  private static final long serialVersionUID = -3465559955367881407L;

  // Globally Unique Identifier
  private TagLengthString globallyUniqueIdentifier;

  // Context Specific Data
  private List<TagLengthString> contextSpecificData;
  
  @Override
  public String toString() {
    
    final StringBuilder sb = new StringBuilder();
    
    Optional.ofNullable(globallyUniqueIdentifier).ifPresent(tlv -> sb.append(tlv.toString()));
    
    for (final TagLengthString tagLengthString : contextSpecificData) {
      Optional.ofNullable(tagLengthString).ifPresent(tlv -> sb.append(tlv.toString()));
    }
    
    return sb.toString();
  }

  @Override
  public String draw(DataType type) {
    
    final StringBuilder sb = new StringBuilder();
    
    Optional.ofNullable(globallyUniqueIdentifier).ifPresent(tlv -> sb.append(tlv.draw(type)));
    
    for (final TagLengthString tagLengthString : contextSpecificData) {
      Optional.ofNullable(tagLengthString).ifPresent(tlv -> sb.append(tlv.draw(type)));
    }
    
    return sb.toString();
  }

}
