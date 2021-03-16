package com.emv.qrcode.decoder.mpm;

import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.model.mpm.PaymentSystemSpecific;
import com.emv.qrcode.model.mpm.PaymentSystemSpecificTemplate;

// @formatter:off
public final class PaymentSystemSpecificTemplateDecoder extends DecoderMpm<PaymentSystemSpecificTemplate> {

  public PaymentSystemSpecificTemplateDecoder(final String source) {
    super(source);
  }

  @Override
  protected PaymentSystemSpecificTemplate decode() throws PresentedModeException {
    final PaymentSystemSpecificTemplate result = new PaymentSystemSpecificTemplate();

    while(iterator.hasNext()) {
      final String value = iterator.next();

      final String tag = value.substring(0, DecodeMpmIterator.ID_WORD_COUNT);
      final Integer length = Integer.valueOf(value.substring(DecodeMpmIterator.ID_WORD_COUNT, DecodeMpmIterator.ID_WORD_COUNT + DecodeMpmIterator.VALUE_LENGTH_WORD_COUNT));
      final String string = value.substring(DecodeMpmIterator.ID_WORD_COUNT + DecodeMpmIterator.VALUE_LENGTH_WORD_COUNT, DecodeMpmIterator.ID_WORD_COUNT + DecodeMpmIterator.VALUE_LENGTH_WORD_COUNT + length);
      result.setTag(tag);
      result.setValue(DecoderMpm.decode(string, PaymentSystemSpecific.class));
    }

    return result;
  }

}
// @formatter:on
