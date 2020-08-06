package com.emv.qrcode.decoder.mpm;

import com.emv.qrcode.decoder.DecodeIterator;
import com.emv.qrcode.decoder.Decoder;
import com.emv.qrcode.model.mpm.MerchantInformationLanguageTemplate;
import com.emv.qrcode.model.mpm.MerchantInformationLanguage;

// @formatter:off
public final class MerchantInformationLanguageTemplateDecoder extends Decoder<MerchantInformationLanguageTemplate> {

  public MerchantInformationLanguageTemplateDecoder(final String source) {
    super(source);
  }

  @Override
  protected MerchantInformationLanguageTemplate decode() {
    final MerchantInformationLanguageTemplate result = new MerchantInformationLanguageTemplate();

    iterator.forEachRemaining(value -> {
      final Integer length = Integer.valueOf(value.substring(DecodeIterator.ID_WORD_COUNT, DecodeIterator.ID_WORD_COUNT + DecodeIterator.VALUE_LENGTH_WORD_COUNT));
      final String string = value.substring(DecodeIterator.ID_WORD_COUNT + DecodeIterator.VALUE_LENGTH_WORD_COUNT, DecodeIterator.ID_WORD_COUNT + DecodeIterator.VALUE_LENGTH_WORD_COUNT + length);
      result.setLength(length);
      result.setValue(Decoder.decode(string, MerchantInformationLanguage.class));
    });

    return result;
  }

}

// @formatter:on
