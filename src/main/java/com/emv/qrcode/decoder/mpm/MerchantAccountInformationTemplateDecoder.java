package com.emv.qrcode.decoder.mpm;

import com.emv.qrcode.model.mpm.MerchantAccountInformation;
import com.emv.qrcode.model.mpm.MerchantAccountInformationTemplate;

// @formatter:off
public final class MerchantAccountInformationTemplateDecoder extends DecoderMpm<MerchantAccountInformationTemplate> {

  public MerchantAccountInformationTemplateDecoder(final String source) {
    super(source);
  }

  @Override
  protected MerchantAccountInformationTemplate decode() {
    final MerchantAccountInformationTemplate result = new MerchantAccountInformationTemplate();

    iterator.forEachRemaining(value -> {
      final String tag = value.substring(0, DecodeMpmIterator.ID_WORD_COUNT);
      final Integer length = Integer.valueOf(value.substring(DecodeMpmIterator.ID_WORD_COUNT, DecodeMpmIterator.ID_WORD_COUNT + DecodeMpmIterator.VALUE_LENGTH_WORD_COUNT));
      final String string = value.substring(DecodeMpmIterator.ID_WORD_COUNT + DecodeMpmIterator.VALUE_LENGTH_WORD_COUNT, DecodeMpmIterator.ID_WORD_COUNT + DecodeMpmIterator.VALUE_LENGTH_WORD_COUNT + length);
      result.setTag(tag);
      result.setValue(DecoderMpm.decode(string, MerchantAccountInformation.class));
    });

    return result;
  }

}
// @formatter:on
