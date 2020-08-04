package com.emv.qrcode.decoder.mpm;

import com.emv.qrcode.decoder.DecodeIterator;
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

    iterator.forEachRemaining(value -> {
      final String tag = value.substring(0, DecodeIterator.ID_WORD_COUNT);
      final Integer length = Integer.valueOf(value.substring(DecodeIterator.ID_WORD_COUNT, DecodeIterator.ID_WORD_COUNT + DecodeIterator.VALUE_LENGTH_WORD_COUNT));
      final String string = value.substring(DecodeIterator.ID_WORD_COUNT + DecodeIterator.VALUE_LENGTH_WORD_COUNT, DecodeIterator.ID_WORD_COUNT + DecodeIterator.VALUE_LENGTH_WORD_COUNT + length);
      result.setTag(tag);
      result.setLength(length);
      result.setValue(Decoder.decode(string, UnreservedValue.class));
    });

    return result;
  }

}
// @formatter:on
