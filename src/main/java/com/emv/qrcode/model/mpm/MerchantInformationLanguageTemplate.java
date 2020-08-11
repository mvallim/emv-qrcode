package com.emv.qrcode.model.mpm;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.model.TLV;
import com.emv.qrcode.model.mpm.constants.MerchantPresentedModeCodes;

import lombok.Setter;

@Setter
public class MerchantInformationLanguageTemplate implements TLV<String, MerchantInformationLanguage> {

  private static final long serialVersionUID = -5894790923682120529L;

  private MerchantInformationLanguage value;

  @Override
  public String getTag() {
    return MerchantPresentedModeCodes.ID_MERCHANT_INFORMATION_LANGUAGE_TEMPLATE;
  }

  @Override
  public MerchantInformationLanguage getValue() {
    return value;
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

    return String.format("%s%02d%s", getTag(), string.length(), string);
  }

}
