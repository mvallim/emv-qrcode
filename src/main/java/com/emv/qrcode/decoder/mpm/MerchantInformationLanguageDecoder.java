package com.emv.qrcode.decoder.mpm;

import com.emv.qrcode.decoder.DecodeIterator;
import com.emv.qrcode.decoder.Decoder;
import com.emv.qrcode.model.mpm.MerchantInformationLanguage;
import com.emv.qrcode.model.mpm.MerchantInformationLanguageValue;

// @formatter:off
public final class MerchantInformationLanguageDecoder extends Decoder<MerchantInformationLanguage> {

  public MerchantInformationLanguageDecoder(final String source) {
    super(source);
  }

  @Override
  protected MerchantInformationLanguage decode() {
    final MerchantInformationLanguage result = new MerchantInformationLanguage();

    iterator.forEachRemaining(value -> {
      final Integer length = Integer.valueOf(value.substring(DecodeIterator.ID_WORD_COUNT, DecodeIterator.ID_WORD_COUNT + DecodeIterator.VALUE_LENGTH_WORD_COUNT));
      final String string = value.substring(DecodeIterator.ID_WORD_COUNT + DecodeIterator.VALUE_LENGTH_WORD_COUNT, DecodeIterator.ID_WORD_COUNT + DecodeIterator.VALUE_LENGTH_WORD_COUNT + length);
      result.setLength(length);
      result.setValue(Decoder.decode(string, MerchantInformationLanguageValue.class));
    });

    return result;
  }

}

// @formatter:on
