package com.emv.qrcode.mpm.model;

import java.io.Serializable;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.model.ListTagLengthString;
import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.core.model.TagLengthValue;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnreservedValue implements Serializable {

  private static final long serialVersionUID = -3465559955367881407L;

  // Globally Unique Identifier
  private TagLengthString globallyUniqueIdentifier;

  // Context Specific Data
  private ListTagLengthString contextSpecificData = new ListTagLengthString();

  @Override
  public String toString() {

    final StringBuilder sb = new StringBuilder();

    Optional.ofNullable(globallyUniqueIdentifier).ifPresent(tlv -> sb.append(tlv.toString()));

    for (final TagLengthValue<String> tagLengthString : contextSpecificData) {
      Optional.ofNullable(tagLengthString).ifPresent(tlv -> sb.append(tlv.toString()));
    }

    final String string = sb.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    return string;
  }

}
