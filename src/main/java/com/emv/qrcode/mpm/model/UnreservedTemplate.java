package com.emv.qrcode.mpm.model;

import java.io.Serializable;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.model.TagLengthValue;
import com.emv.qrcode.parsers.Parser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnreservedTemplate implements Serializable, TagLengthValue<UnreservedTemplateValue> {

  private static final long serialVersionUID = -1445641777082739037L;

  private String tag;

  private Integer length;

  private UnreservedTemplateValue value;

  public UnreservedTemplate(final String tag, final String value) {
    this.tag = tag;
    this.length = value.length();
    this.value = Parser.parse(value, UnreservedTemplateValue.class);
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
