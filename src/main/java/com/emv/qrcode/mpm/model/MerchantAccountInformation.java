package com.emv.qrcode.mpm.model;

import java.io.Serializable;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.model.DataType;
import com.emv.qrcode.core.model.DrawData;
import com.emv.qrcode.core.model.TagLengthValue;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchantAccountInformation implements Serializable, DrawData, TagLengthValue<MerchantAccountInformationValue> {

  private static final long serialVersionUID = 1504801865799183162L;

  private String tag;

  private Integer length;

  private MerchantAccountInformationValue value;
  
  public MerchantAccountInformation(final String value) {
    // TODO Auto-generated constructor stub
  }

  @Override
  public String toString() {
    return String.format("%s%s%s", tag, length, value.toString());
  }

  @Override
  @SuppressWarnings("all")
  public String draw(final DataType type) {
    
    if (Objects.isNull(value)) {
      return StringUtils.EMPTY;
    }
    
    return String.format("%s %02d\n%s", tag, length, value.draw(type));
    
  }

}
