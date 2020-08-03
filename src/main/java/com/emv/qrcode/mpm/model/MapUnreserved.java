package com.emv.qrcode.mpm.model;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

public class MapUnreserved extends HashMap<String, Unreserved> {

  private static final long serialVersionUID = -8978073076730091833L;

  public MapUnreserved() {
    super();
  }

  @Override
  public String toString() {

    if (isEmpty()) {
      return StringUtils.EMPTY;
    }

    final StringBuilder sb = new StringBuilder();

    for (final Entry<String, Unreserved> entry : entrySet()) {
      final Unreserved value = entry.getValue();
      final UnreservedValue informationValue = value.getValue();
      final String string = informationValue.toString();
      sb.append(String.format("%s%02d%s", value.getTag(), string.length(), string));
    }

    final String string = sb.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    return string;
  }

}
