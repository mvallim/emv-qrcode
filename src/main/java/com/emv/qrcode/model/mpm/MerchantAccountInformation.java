package com.emv.qrcode.model.mpm;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.model.SimpleTLV;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchantAccountInformation implements SimpleTLV<MerchantAccountInformationValue> {

  private static final long serialVersionUID = 1504801865799183162L;

  private String tag;

  private Integer length;

  private MerchantAccountInformationValue value;

  @Override
  public String toString() {

    if (Objects.isNull(value)) {
      return StringUtils.EMPTY;
    }

    final String string = value.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    return String.format("%s%02d%s", tag, string.length(), string);
  }

}
