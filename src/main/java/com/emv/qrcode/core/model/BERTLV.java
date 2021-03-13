package com.emv.qrcode.core.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

import org.apache.commons.codec.binary.Hex;

public class BERTLV implements TLV<Integer, byte[]> {

  private static final long serialVersionUID = 1286326121944790325L;

  public static final byte[] EMPTY_BYTES = new byte[0];

  private Integer tag;

  private byte[] value;

  public BERTLV(final Integer tag, final byte[] value) {
    setTag(tag);
    setValue(value);
  }

  @Override
  public Integer getTag() {
    return tag;
  }

  public final void setTag(final Integer tag) {
    this.tag = Optional.ofNullable(tag).orElse(0);
  }

  @Override
  public byte[] getValue() {
    return value;
  }

  public final void setValue(final byte[] value) {
    this.value = Optional.ofNullable(value).orElse(EMPTY_BYTES);
  }

  @Override
  public final Integer getLength() {
    return value.length;
  }

  public final String toHex() throws IOException {
    return Hex.encodeHexString(getBytes(), false);
  }

  public byte[] getBytes() throws IOException {

    if (getLength() == 0) {
      return EMPTY_BYTES;
    }

    try (final ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
      stream.write(getTag());
      stream.write(getLength());
      stream.write(getValue());
      return stream.toByteArray();
    }

  }

}
