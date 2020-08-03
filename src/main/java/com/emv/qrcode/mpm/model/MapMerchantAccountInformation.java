package com.emv.qrcode.mpm.model;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

public class MapMerchantAccountInformation extends HashMap<String, MerchantAccountInformation> {

  private static final long serialVersionUID = -8978073076730091833L;

  public MapMerchantAccountInformation() {
    super();
  }

  @Override
  public String toString() {

    if (isEmpty()) {
      return StringUtils.EMPTY;
    }

    final StringBuilder sb = new StringBuilder();

    for (final Entry<String, MerchantAccountInformation> entry : entrySet()) {
      final MerchantAccountInformation value = entry.getValue();
      final MerchantAccountInformationValue informationValue = value.getValue();
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
