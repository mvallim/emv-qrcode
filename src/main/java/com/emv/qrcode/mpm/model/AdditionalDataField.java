package com.emv.qrcode.mpm.model;

import java.io.Serializable;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.model.TagLengthValue;
import com.emv.qrcode.mpm.constants.MerchantPresentModeCodes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdditionalDataField implements Serializable, TagLengthValue<AdditionalDataFieldValue>{

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
