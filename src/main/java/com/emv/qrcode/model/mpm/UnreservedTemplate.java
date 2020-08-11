package com.emv.qrcode.model.mpm;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.model.TLV;

import lombok.Setter;

@Setter
public class UnreservedTemplate implements TLV<String, Unreserved> {

  private static final long serialVersionUID = -1445641777082739037L;

  private String tag;

  private Unreserved value;

  @Override
  public String getTag() {
    return tag;
  }

  @Override
  public Unreserved getValue() {
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

    return String.format("%s%02d%s", tag, string.length(), string);
  }

}
