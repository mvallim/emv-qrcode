package com.emv.qrcode.decoder.mpm;

// @formatter:off
public final class StringDecoder extends DecoderMpm<String> {

  public StringDecoder(final String source) {
    super(source);
  }

  @Override
  protected String decode() {
    final StringBuilder result = new StringBuilder();

    iterator.forEachRemaining(value -> {
      final Integer length = Integer.valueOf(value.substring(DecodeMpmIterator.ID_WORD_COUNT, DecodeMpmIterator.ID_WORD_COUNT + DecodeMpmIterator.VALUE_LENGTH_WORD_COUNT));
      final String string = value.substring(DecodeMpmIterator.ID_WORD_COUNT + DecodeMpmIterator.VALUE_LENGTH_WORD_COUNT, DecodeMpmIterator.ID_WORD_COUNT + DecodeMpmIterator.VALUE_LENGTH_WORD_COUNT + length);
      result.append(string);
    });

    return result.toString();
  }

}
// @formatter:on
