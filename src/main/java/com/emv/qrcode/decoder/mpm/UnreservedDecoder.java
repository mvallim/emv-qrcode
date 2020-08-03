package com.emv.qrcode.decoder.mpm;

import com.emv.qrcode.decoder.Decoder;
import com.emv.qrcode.model.mpm.Unreserved;
import com.emv.qrcode.model.mpm.UnreservedValue;

// @formatter:off
public final class UnreservedDecoder extends Decoder<Unreserved> {

  public UnreservedDecoder(final String source) {
    super(source);
  }

  @Override
  protected Unreserved decode() {
    final Unreserved result = new Unreserved();

    forEachRemaining(value -> {
      final String tag = value.substring(0, Decoder.ID_WORD_COUNT);
      final Integer length = Integer.valueOf(value.substring(Decoder.ID_WORD_COUNT, Decoder.ID_WORD_COUNT + Decoder.VALUE_LENGTH_WORD_COUNT));
      final String string = value.substring(Decoder.ID_WORD_COUNT + Decoder.VALUE_LENGTH_WORD_COUNT, Decoder.ID_WORD_COUNT + Decoder.VALUE_LENGTH_WORD_COUNT + length);
      result.setTag(tag);
      result.setLength(length);
      result.setValue(Decoder.decode(string, UnreservedValue.class));
    });

    return result;
  }

}
// @formatter:on
