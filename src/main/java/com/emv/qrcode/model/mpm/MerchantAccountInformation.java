package com.emv.qrcode.model.mpm;

import java.io.Serializable;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.model.SimpleTLV;
import com.emv.qrcode.decoder.Decoder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchantAccountInformation extends SimpleTLV<MerchantAccountInformationValue> implements Serializable {

  private static final long serialVersionUID = 1504801865799183162L;

  private String tag;

  private Integer length;

  private MerchantAccountInformationValue value;

  public MerchantAccountInformation() {
    super();
  }

  public MerchantAccountInformation(final String tag, final String value) {
    this.tag = tag;
    this.length = value.length();
    this.value = Decoder.decode(value, MerchantAccountInformationValue.class);
  }

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
