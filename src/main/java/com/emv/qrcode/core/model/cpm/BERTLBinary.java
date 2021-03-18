package com.emv.qrcode.core.model.cpm;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.exception.DecodeValueException;

public class BERTLBinary extends BERTLV {

  private static final long serialVersionUID = -2791656176543560953L;

  public BERTLBinary(final BERTag tag, final byte[] value) {
    super(tag, value);
  }

  public BERTLBinary(final byte[] tag, final byte[] value) {
    super(tag, value);
  }

  public BERTLBinary(final byte[] tag, final String value) {
    super(tag, StringUtils.isNotEmpty(value) ? toPrimitives(value) : EMPTY_BYTES);
  }

  public BERTLBinary(final BERTag tag, final String value) {
    super(tag, StringUtils.isNotEmpty(value) ? toPrimitives(value) : EMPTY_BYTES);
  }

  public final void setValue(final String value) {
    setValue(StringUtils.isNotEmpty(value) ? toPrimitives(value) : EMPTY_BYTES);
  }

  private static byte[] toPrimitives(final String value) {
    try {
      return Hex.decodeHex(value);
    } catch (final DecoderException ex) {
      throw new DecodeValueException(value);
    }
  }

  @Override
  public String getValue() {
    return Hex.encodeHexString(value, false);
  }

}
