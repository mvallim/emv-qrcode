package com.emv.qrcode.decoder.mpm;

import com.emv.qrcode.core.exception.InvalidMerchantPresentedModeException;
import com.emv.qrcode.model.mpm.MerchantInformationLanguage;
import com.emv.qrcode.model.mpm.MerchantInformationLanguageTemplate;

// @formatter:off
public final class MerchantInformationLanguageTemplateDecoder extends DecoderMpm<MerchantInformationLanguageTemplate> {

  public MerchantInformationLanguageTemplateDecoder(final String source) {
    super(source);
  }

  @Override
  protected MerchantInformationLanguageTemplate decode() throws InvalidMerchantPresentedModeException {
    final MerchantInformationLanguageTemplate result = new MerchantInformationLanguageTemplate();

    while(iterator.hasNext()) {
      final String value = iterator.next();

      final Integer length = Integer.valueOf(value.substring(DecodeMpmIterator.ID_WORD_COUNT, DecodeMpmIterator.ID_WORD_COUNT + DecodeMpmIterator.VALUE_LENGTH_WORD_COUNT));
      final String string = value.substring(DecodeMpmIterator.ID_WORD_COUNT + DecodeMpmIterator.VALUE_LENGTH_WORD_COUNT, DecodeMpmIterator.ID_WORD_COUNT + DecodeMpmIterator.VALUE_LENGTH_WORD_COUNT + length);
      result.setValue(DecoderMpm.decode(string, MerchantInformationLanguage.class));
    }

    return result;
  }

}

// @formatter:on
