package com.emv.qrcode.core.model;

import java.util.LinkedList;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListTagLengthString extends LinkedList<TagLengthValue<String>> {

  private static final long serialVersionUID = 2348054348146256148L;

  public ListTagLengthString() {
    super();
  }

  @Override
  public String toString() {

    if (isEmpty()) {
      return StringUtils.EMPTY;
    }

    final StringBuilder sb = new StringBuilder();

    for (final TagLengthValue<String> tagLengthValue : this) {
      sb.append(String.format("%s%02d%s", tagLengthValue.getTag(), tagLengthValue.getLength(), tagLengthValue.getValue()));
    }

    final String string = sb.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    return string;
  }

}
