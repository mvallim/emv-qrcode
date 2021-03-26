package com.emv.qrcode.model.mpm;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.model.TLV;

import lombok.Setter;

@Setter
public class MerchantAccountInformationTemplate implements TLV<String, MerchantAccountInformation> {

  private static final long serialVersionUID = 1504801865799183162L;

  private String tag;

  private MerchantAccountInformation value;

  public MerchantAccountInformationTemplate() {
    super();
  }

  public MerchantAccountInformationTemplate(final String tag) {
    this(tag, null);
  }

  public MerchantAccountInformationTemplate(final String tag, final MerchantAccountInformation value) {
    setTag(tag);
    setValue(value);
  }

  @Override
  public String getTag() {
    return tag;
  }

  @Override
  public MerchantAccountInformation getValue() {
    return value;
  }

  public <T extends MerchantAccountInformation> T getTypeValue(final Class<T> clazz) {
    return clazz.isInstance(value) ? clazz.cast(value) : null;
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
