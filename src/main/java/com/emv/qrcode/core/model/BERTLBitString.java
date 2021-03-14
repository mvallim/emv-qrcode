package com.emv.qrcode.core.model;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

public class BERTLBitString extends BERTLV {

  private static final long serialVersionUID = -2791656176543560953L;

  public BERTLBitString(final BERTag tag, final byte[] value) {
    super(tag, value);
  }

  public BERTLBitString(final byte[] tag, final byte[] value) {
    super(tag, value);
  }

  public BERTLBitString(final byte[] tag, final String value) {
    super(tag, StringUtils.isNotEmpty(value) ? toPrimitives(value) : EMPTY_BYTES);
  }

  public BERTLBitString(final BERTag tag, final String value) {
    super(tag, StringUtils.isNotEmpty(value) ? toPrimitives(value) : EMPTY_BYTES);
  }

  public final void setValue(final String value) {
    setValue(StringUtils.isNotEmpty(value) ? toPrimitives(value) : EMPTY_BYTES);
  }

  private static byte[] toPrimitives(final String value) {
    try {
      return Hex.decodeHex(value);
    } catch (final DecoderException ex) {
      throw new RuntimeException(ex);
    }
  }

}
