package com.emv.qrcode.mpm.model;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.model.TagLengthValue;

import lombok.Getter;

@Getter
public class TagLengthString implements Serializable, TagLengthValue<String> {

  private static final long serialVersionUID = -6482977134879939277L;

  private String tag;

  private Integer length;

  private String value;
  
  public TagLengthString(final String tag, final String value) {
    this.tag = tag;
    this.value = value;
    this.length = value.length();
  }
  
  @Override
  public String toString() {
    
    if (StringUtils.isBlank(value)) {
      return StringUtils.EMPTY;
    }
    
    return String.format("%s%02d%s", tag, value.length(), value);
  }

}
