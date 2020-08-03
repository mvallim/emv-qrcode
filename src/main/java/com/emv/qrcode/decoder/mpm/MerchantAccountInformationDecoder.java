package com.emv.qrcode.decoder.mpm;

import com.emv.qrcode.decoder.Decoder;
import com.emv.qrcode.model.mpm.MerchantAccountInformation;
import com.emv.qrcode.model.mpm.MerchantAccountInformationValue;

// @formatter:off
public final class MerchantAccountInformationDecoder extends Decoder<MerchantAccountInformation> {

  public MerchantAccountInformationDecoder(final String source) {
    super(source);
  }

  @Override
  protected MerchantAccountInformation decode() {
    final MerchantAccountInformation result = new MerchantAccountInformation();

    forEachRemaining(value -> {
      final String tag = value.substring(0, Decoder.ID_WORD_COUNT);
      final Integer length = Integer.valueOf(value.substring(Decoder.ID_WORD_COUNT, Decoder.ID_WORD_COUNT + Decoder.VALUE_LENGTH_WORD_COUNT));
      final String string = value.substring(Decoder.ID_WORD_COUNT + Decoder.VALUE_LENGTH_WORD_COUNT, Decoder.ID_WORD_COUNT + Decoder.VALUE_LENGTH_WORD_COUNT + length);
      result.setTag(tag);
      result.setLength(length);
      result.setValue(Decoder.decode(string, MerchantAccountInformationValue.class));
    });

    return result;
  }

}
// @formatter:on
