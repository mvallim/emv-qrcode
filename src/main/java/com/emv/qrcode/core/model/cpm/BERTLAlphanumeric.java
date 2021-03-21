package com.emv.qrcode.core.model.cpm;

import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.StringUtils;

public class BERTLAlphanumeric extends BERTLV {

  private static final long serialVersionUID = 4743677275454890695L;

  public BERTLAlphanumeric(final BERTag tag, final byte[] value) {
    super(tag, value);
  }

  public BERTLAlphanumeric(final byte[] tag, final byte[] value) {
    super(tag, value);
  }

  public BERTLAlphanumeric(final byte[] tag, final String value) {
    super(tag, StringUtils.isNotEmpty(value) ? value.getBytes(StandardCharsets.UTF_8) : EMPTY_BYTES);
  }

  public BERTLAlphanumeric(final BERTag tag, final String value) {
    super(tag, StringUtils.isNotEmpty(value) ? value.getBytes(StandardCharsets.UTF_8) : EMPTY_BYTES);
  }

  public final void setValue(final String value) {
    setValue(StringUtils.isNotEmpty(value) ? value.getBytes(StandardCharsets.UTF_8) : EMPTY_BYTES);
  }

  @Override
  public String getStringValue() {
    return new String(value, StandardCharsets.UTF_8);
  }

}
