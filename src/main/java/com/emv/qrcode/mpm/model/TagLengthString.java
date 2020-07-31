package com.emv.qrcode.mpm.model;

import java.io.Serializable;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.DataType;
import com.emv.qrcode.core.DrawData;
import com.emv.qrcode.core.TagLengthValue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TagLengthString implements Serializable, DrawData, TagLengthValue<String> {

  private static final long serialVersionUID = -6482977134879939277L;

  private String tag;

  private Integer length;

  private String value;

  @Override
  public String toString() {
    return String.format("%s%02d%s", tag, length, value);
  }

  @Override
  @SuppressWarnings("all")
  public String draw(final DataType type) {
    
    if (StringUtils.isBlank(value)) {
      return StringUtils.EMPTY;
    }
    
    switch (type) {
      case BINARY:
        final String encodeHexString = Hex.encodeHexString(value.getBytes(), true);
        final String[] split = encodeHexString.split("(.{2})");
        return String.format("%s %02d %s\n", tag, length, String.join(" ", split));
      case RAW:
        return String.format("%s %02d %s\n", tag, length, value);
      default:
        throw new IllegalArgumentException(type.name());
    }
    
  }

}
