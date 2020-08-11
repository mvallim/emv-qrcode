package com.emv.qrcode.core.model;

import java.io.IOException;

import org.apache.commons.codec.binary.Hex;

@SuppressWarnings("java:S1214")
public interface BERTLV<T, V> extends TLV<T, V> {

  public static final byte[] EMPTY_BYTES = new byte[0];

  public byte[] getBytes() throws IOException;

  default String toHex() throws IOException {
    return Hex.encodeHexString(getBytes(), false);
  }

}
