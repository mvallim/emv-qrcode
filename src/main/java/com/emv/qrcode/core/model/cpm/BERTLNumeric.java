package com.emv.qrcode.core.model.cpm;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.exception.DecodeValueException;

public class BERTLNumeric extends BERTLV {

  private static final long serialVersionUID = -2791656176543560953L;

  public BERTLNumeric(final BERTag tag, final byte[] value) {
    super(tag, value);
  }

  public BERTLNumeric(final byte[] tag, final byte[] value) {
    super(tag, value);
  }

  public BERTLNumeric(final byte[] tag, final String value) {
    super(tag, StringUtils.isNotEmpty(value) ? toPrimitives(value) : EMPTY_BYTES);
  }

  public BERTLNumeric(final BERTag tag, final String value) {
    super(tag, StringUtils.isNotEmpty(value) ? toPrimitives(value) : EMPTY_BYTES);
  }

  public final void setValue(final String value) {
    setValue(StringUtils.isNotEmpty(value) ? toPrimitives(value) : EMPTY_BYTES);
  }

  private static byte[] toPrimitives(final String value) {
    try {
      return Hex.decodeHex(value.length() % 2 == 0 ? value : "0" + value);
    } catch (final DecoderException ex) {
      throw new DecodeValueException(value);
    }
  }

  @Override
  public String getValue() {
    return Hex.encodeHexString(value, false);
  }

}
