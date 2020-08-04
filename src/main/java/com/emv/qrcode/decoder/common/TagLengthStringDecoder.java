package com.emv.qrcode.decoder.common;

import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.decoder.DecodeIterator;
import com.emv.qrcode.decoder.Decoder;

// @formatter:off
public final class TagLengthStringDecoder extends Decoder<TagLengthString> {

  public TagLengthStringDecoder(final String source) {
    super(source);
  }

  @Override
  protected TagLengthString decode() {
    final TagLengthString result = new TagLengthString();

    iterator.forEachRemaining(value -> {
      final String tag = value.substring(0, DecodeIterator.ID_WORD_COUNT);
      final Integer length = Integer.valueOf(value.substring(DecodeIterator.ID_WORD_COUNT, DecodeIterator.ID_WORD_COUNT + DecodeIterator.VALUE_LENGTH_WORD_COUNT));
      final String string = value.substring(DecodeIterator.ID_WORD_COUNT + DecodeIterator.VALUE_LENGTH_WORD_COUNT, DecodeIterator.ID_WORD_COUNT + DecodeIterator.VALUE_LENGTH_WORD_COUNT + length);
      result.setTag(tag);
      result.setLength(length);
      result.setValue(string);
    });

    return result;
  }

}

// @formatter:on
