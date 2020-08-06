package com.emv.qrcode.decoder.mpm;

import com.emv.qrcode.decoder.DecodeIterator;
import com.emv.qrcode.decoder.Decoder;
import com.emv.qrcode.model.mpm.AdditionalDataFieldTemplate;
import com.emv.qrcode.model.mpm.AdditionalDataField;

// @formatter:off
public final class AdditionalDataFieldTemplateDecoder extends Decoder<AdditionalDataFieldTemplate> {

  public AdditionalDataFieldTemplateDecoder(final String source) {
    super(source);
  }

  @Override
  protected AdditionalDataFieldTemplate decode() {
    final AdditionalDataFieldTemplate result = new AdditionalDataFieldTemplate();

    iterator.forEachRemaining(value -> {
      final Integer length = Integer.valueOf(value.substring(DecodeIterator.ID_WORD_COUNT, DecodeIterator.ID_WORD_COUNT + DecodeIterator.VALUE_LENGTH_WORD_COUNT));
      final String string = value.substring(DecodeIterator.ID_WORD_COUNT + DecodeIterator.VALUE_LENGTH_WORD_COUNT, DecodeIterator.ID_WORD_COUNT + DecodeIterator.VALUE_LENGTH_WORD_COUNT + length);
      result.setLength(length);
      result.setValue(Decoder.decode(string, AdditionalDataField.class));
    });

    return result;
  }

}
// @formatter:on
