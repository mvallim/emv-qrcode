package com.emv.qrcode.mpm.model;

import java.io.Serializable;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.DataType;
import com.emv.qrcode.core.DrawData;
import com.emv.qrcode.core.TagLengthValue;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnreservedTemplate implements Serializable, DrawData, TagLengthValue<UnreservedTemplateValue> {

  private static final long serialVersionUID = -1445641777082739037L;

  private String tag;

  private Integer length;

  private UnreservedTemplateValue value;

  @Override
  public String toString() {
    return String.format("%s%02d%s", tag, length, value.toString());
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
