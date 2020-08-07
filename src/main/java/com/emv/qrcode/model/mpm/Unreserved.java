package com.emv.qrcode.model.mpm;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.model.TLV;
import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.model.mpm.constants.UnreservedTemplateFieldCodes;

import lombok.Getter;

@Getter
public class Unreserved implements Serializable {

  private static final long serialVersionUID = -3465559955367881407L;

  // Globally Unique Identifier
  private TagLengthString globallyUniqueIdentifier;

  // Context Specific Data
  private final List<TagLengthString> contextSpecificData = new LinkedList<>();

  public void setGloballyUniqueIdentifier(final TagLengthString globallyUniqueIdentifier) {
    this.globallyUniqueIdentifier = globallyUniqueIdentifier;
  }

  public final void setGloballyUniqueIdentifier(final String globallyUniqueIdentifier) {
    this.globallyUniqueIdentifier = new TagLengthString(UnreservedTemplateFieldCodes.ID_GLOBALLY_UNIQUE_IDENTIFIER, globallyUniqueIdentifier);
  }

  public void addContextSpecificData(final TagLengthString tagLengthString) {
    contextSpecificData.add(tagLengthString);
  }

  @Override
  public String toString() {

    final StringBuilder sb = new StringBuilder();

    Optional.ofNullable(globallyUniqueIdentifier).ifPresent(tlv -> sb.append(tlv.toString()));

    for (final TLV<String, String> tagLengthString : contextSpecificData) {
      Optional.ofNullable(tagLengthString).ifPresent(tlv -> sb.append(tlv.toString()));
    }

    final String string = sb.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    return string;
  }

}
