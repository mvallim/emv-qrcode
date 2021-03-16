package com.emv.qrcode.decoder.mpm;

import com.emv.qrcode.core.exception.PresentedModeException;

// @formatter:off
public final class StringDecoder extends DecoderMpm<String> {

  public StringDecoder(final String source) {
    super(source);
  }

  @Override
  protected String decode() throws PresentedModeException {
    final StringBuilder result = new StringBuilder();

    while(iterator.hasNext()) {
      final String value = iterator.next();

      final Integer length = Integer.valueOf(value.substring(DecodeMpmIterator.ID_WORD_COUNT, DecodeMpmIterator.ID_WORD_COUNT + DecodeMpmIterator.VALUE_LENGTH_WORD_COUNT));
      final String string = value.substring(DecodeMpmIterator.ID_WORD_COUNT + DecodeMpmIterator.VALUE_LENGTH_WORD_COUNT, DecodeMpmIterator.ID_WORD_COUNT + DecodeMpmIterator.VALUE_LENGTH_WORD_COUNT + length);
      result.append(string);
    }

    return result.toString();
  }

}
// @formatter:on
