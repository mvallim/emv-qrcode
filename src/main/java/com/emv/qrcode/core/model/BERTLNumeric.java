package com.emv.qrcode.core.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

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
    final List<Byte> collect = Stream.of(value.split("[0-9]{2}")).map(Byte::parseByte).collect(Collectors.toList());
    final byte[] bytes = new byte[collect.size()];
    for (int i = 0; i < collect.size(); i++) {
      bytes[i] = collect.get(i);
    }
    return bytes;
  }

}
