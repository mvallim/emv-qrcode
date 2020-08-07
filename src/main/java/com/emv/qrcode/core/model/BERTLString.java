package com.emv.qrcode.core.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BERTLString implements BERTLV<Integer, String> {

  private static final long serialVersionUID = -6482977134879939277L;

  private Integer tag;

  private String value;
  
  public BERTLString() {
    super();
  }
  
  public BERTLString(final Integer tag, final String value) {
    this.tag = tag;
    this.value = value;
  }

  @Override
  public Integer getLength() {
    return value.length();
  }
  
  @Override
  public byte[] getBytes() throws IOException {
    
    if (StringUtils.isBlank(value)) {
      return EMPTY_BYTES;
    }

    try (final ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
      stream.write(getTag());
      stream.write(getLength());
      stream.write(getValue().getBytes(StandardCharsets.UTF_8));     
      return stream.toByteArray();
    }
    
  }
  
  @Override
  public String toHex() throws IOException {
    return Hex.encodeHexString(getBytes(), false);
  }

}
