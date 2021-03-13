package com.emv.qrcode.decoder.mpm;

import com.emv.qrcode.core.exception.InvalidMerchantPresentedModeException;
import com.emv.qrcode.model.mpm.AdditionalDataField;
import com.emv.qrcode.model.mpm.AdditionalDataFieldTemplate;

// @formatter:off
public final class AdditionalDataFieldTemplateDecoder extends DecoderMpm<AdditionalDataFieldTemplate> {

  public AdditionalDataFieldTemplateDecoder(final String source) {
    super(source);
  }

  @Override
  protected AdditionalDataFieldTemplate decode() throws InvalidMerchantPresentedModeException {
    final AdditionalDataFieldTemplate result = new AdditionalDataFieldTemplate();

    while(iterator.hasNext()) {
      final String value = iterator.next();

      final Integer length = Integer.valueOf(value.substring(DecodeMpmIterator.ID_WORD_COUNT, DecodeMpmIterator.ID_WORD_COUNT + DecodeMpmIterator.VALUE_LENGTH_WORD_COUNT));
      final String string = value.substring(DecodeMpmIterator.ID_WORD_COUNT + DecodeMpmIterator.VALUE_LENGTH_WORD_COUNT, DecodeMpmIterator.ID_WORD_COUNT + DecodeMpmIterator.VALUE_LENGTH_WORD_COUNT + length);
      result.setValue(DecoderMpm.decode(string, AdditionalDataField.class));
    }

    return result;
  }

}
// @formatter:on
