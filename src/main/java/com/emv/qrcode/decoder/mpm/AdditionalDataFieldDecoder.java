package com.emv.qrcode.decoder.mpm;

import com.emv.qrcode.decoder.Decoder;
import com.emv.qrcode.mpm.model.AdditionalDataField;
import com.emv.qrcode.mpm.model.AdditionalDataFieldValue;

// @formatter:off
public final class AdditionalDataFieldDecoder extends Decoder<AdditionalDataField> {

  public AdditionalDataFieldDecoder(final String source) {
    super(source);
  }

  @Override
  protected AdditionalDataField decode() {
    final AdditionalDataField result = new AdditionalDataField();

    forEachRemaining(value -> {
      final Integer length = Integer.valueOf(value.substring(Decoder.ID_WORD_COUNT, Decoder.ID_WORD_COUNT + Decoder.VALUE_LENGTH_WORD_COUNT));
      final String string = value.substring(Decoder.ID_WORD_COUNT + Decoder.VALUE_LENGTH_WORD_COUNT, Decoder.ID_WORD_COUNT + Decoder.VALUE_LENGTH_WORD_COUNT + length);
      result.setLength(length);
      result.setValue(Decoder.decode(string, AdditionalDataFieldValue.class));
    });

    return result;
  }

}
// @formatter:on
