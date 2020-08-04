package com.emv.qrcode.decoder.mpm;

import com.emv.qrcode.decoder.DecodeIterator;
import com.emv.qrcode.decoder.Decoder;
import com.emv.qrcode.model.mpm.AdditionalDataField;
import com.emv.qrcode.model.mpm.AdditionalDataFieldValue;

// @formatter:off
public final class AdditionalDataFieldDecoder extends Decoder<AdditionalDataField> {

  public AdditionalDataFieldDecoder(final String source) {
    super(source);
  }

  @Override
  protected AdditionalDataField decode() {
    final AdditionalDataField result = new AdditionalDataField();

    iterator.forEachRemaining(value -> {
      final Integer length = Integer.valueOf(value.substring(DecodeIterator.ID_WORD_COUNT, DecodeIterator.ID_WORD_COUNT + DecodeIterator.VALUE_LENGTH_WORD_COUNT));
      final String string = value.substring(DecodeIterator.ID_WORD_COUNT + DecodeIterator.VALUE_LENGTH_WORD_COUNT, DecodeIterator.ID_WORD_COUNT + DecodeIterator.VALUE_LENGTH_WORD_COUNT + length);
      result.setLength(length);
      result.setValue(Decoder.decode(string, AdditionalDataFieldValue.class));
    });

    return result;
  }

}
// @formatter:on
