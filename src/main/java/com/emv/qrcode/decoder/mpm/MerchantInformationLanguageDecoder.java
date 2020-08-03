package com.emv.qrcode.decoder.mpm;

import com.emv.qrcode.decoder.Decoder;
import com.emv.qrcode.mpm.model.MerchantInformationLanguage;
import com.emv.qrcode.mpm.model.MerchantInformationLanguageValue;

// @formatter:off
public final class MerchantInformationLanguageDecoder extends Decoder<MerchantInformationLanguage> {

  public MerchantInformationLanguageDecoder(final String source) {
    super(source);
  }

  @Override
  protected MerchantInformationLanguage decode() {
    final MerchantInformationLanguage result = new MerchantInformationLanguage();

    forEachRemaining(value -> {
      final Integer length = Integer.valueOf(value.substring(Decoder.ID_WORD_COUNT, Decoder.ID_WORD_COUNT + Decoder.VALUE_LENGTH_WORD_COUNT));
      final String string = value.substring(Decoder.ID_WORD_COUNT + Decoder.VALUE_LENGTH_WORD_COUNT, Decoder.ID_WORD_COUNT + Decoder.VALUE_LENGTH_WORD_COUNT + length);
      result.setLength(length);
      result.setValue(Decoder.decode(string, MerchantInformationLanguageValue.class));
    });

    return result;
  }

}

// @formatter:on
