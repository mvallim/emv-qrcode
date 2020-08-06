package com.emv.qrcode.model.mpm;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.model.SimpleTLV;
import com.emv.qrcode.model.mpm.constants.MerchantPresentedModeCodes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchantInformationLanguageTemplate implements SimpleTLV<MerchantInformationLanguage> {

  private static final long serialVersionUID = -5894790923682120529L;

  private final String tag = MerchantPresentedModeCodes.ID_MERCHANT_INFORMATION_LANGUAGE_TEMPLATE;

  private Integer length;

  private MerchantInformationLanguage value;

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
