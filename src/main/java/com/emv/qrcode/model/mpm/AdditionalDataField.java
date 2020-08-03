package com.emv.qrcode.model.mpm;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.model.SimpleTLV;
import com.emv.qrcode.model.mpm.constants.MerchantPresentModeCodes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdditionalDataField implements SimpleTLV<AdditionalDataFieldValue> {

  private static final long serialVersionUID = 2232991556283235445L;

  private final String tag = MerchantPresentModeCodes.ID_ADDITIONAL_DATA_FIELD_TEMPLATE;

  private Integer length;

  private AdditionalDataFieldValue value;

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
