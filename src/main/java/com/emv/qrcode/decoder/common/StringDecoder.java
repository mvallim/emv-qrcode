package com.emv.qrcode.decoder.common;

import com.emv.qrcode.decoder.DecodeIterator;
import com.emv.qrcode.decoder.Decoder;

// @formatter:off
public final class StringDecoder extends Decoder<String> {

  public StringDecoder(final String source) {
    super(source);
  }

  @Override
  protected String decode() {
    final StringBuilder result = new StringBuilder();

    iterator.forEachRemaining(value -> {
      final Integer length = Integer.valueOf(value.substring(DecodeIterator.ID_WORD_COUNT, DecodeIterator.ID_WORD_COUNT + DecodeIterator.VALUE_LENGTH_WORD_COUNT));
      final String string = value.substring(DecodeIterator.ID_WORD_COUNT + DecodeIterator.VALUE_LENGTH_WORD_COUNT, DecodeIterator.ID_WORD_COUNT + DecodeIterator.VALUE_LENGTH_WORD_COUNT + length);
      result.append(string);
    });

    return result.toString();
  }

}
// @formatter:on
