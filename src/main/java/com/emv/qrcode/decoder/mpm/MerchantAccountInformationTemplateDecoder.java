package com.emv.qrcode.decoder.mpm;

import com.emv.qrcode.decoder.DecodeIterator;
import com.emv.qrcode.decoder.Decoder;
import com.emv.qrcode.model.mpm.MerchantAccountInformationTemplate;
import com.emv.qrcode.model.mpm.MerchantAccountInformation;

// @formatter:off
public final class MerchantAccountInformationTemplateDecoder extends Decoder<MerchantAccountInformationTemplate> {

  public MerchantAccountInformationTemplateDecoder(final String source) {
    super(source);
  }

  @Override
  protected MerchantAccountInformationTemplate decode() {
    final MerchantAccountInformationTemplate result = new MerchantAccountInformationTemplate();

    iterator.forEachRemaining(value -> {
      final String tag = value.substring(0, DecodeIterator.ID_WORD_COUNT);
      final Integer length = Integer.valueOf(value.substring(DecodeIterator.ID_WORD_COUNT, DecodeIterator.ID_WORD_COUNT + DecodeIterator.VALUE_LENGTH_WORD_COUNT));
      final String string = value.substring(DecodeIterator.ID_WORD_COUNT + DecodeIterator.VALUE_LENGTH_WORD_COUNT, DecodeIterator.ID_WORD_COUNT + DecodeIterator.VALUE_LENGTH_WORD_COUNT + length);
      result.setTag(tag);
      result.setLength(length);
      result.setValue(Decoder.decode(string, MerchantAccountInformation.class));
    });

    return result;
  }

}
// @formatter:on
