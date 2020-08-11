package com.emv.qrcode.core.model;

import java.io.IOException;

@SuppressWarnings("java:S1214")
public interface BERTLV<T, V> extends TLV<T, V> {

  public static final byte[] EMPTY_BYTES = new byte[0];

  public byte[] getBytes() throws IOException;

  public String toHex() throws IOException;

}
