package com.emv.qrcode.model.mpm;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.model.TLV;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchantAccountInformationTemplate implements TLV<String, MerchantAccountInformation> {

  private static final long serialVersionUID = 1504801865799183162L;

  private String tag;

  private Integer length;

  private MerchantAccountInformation value;

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
