package com.emv.qrcode.decoder.mpm;

import com.emv.qrcode.decoder.DecodeIterator;
import com.emv.qrcode.decoder.Decoder;
import com.emv.qrcode.model.mpm.PaymentSystemSpecific;
import com.emv.qrcode.model.mpm.PaymentSystemSpecificTemplate;

// @formatter:off
public final class PaymentSystemSpecificTemplateDecoder extends Decoder<PaymentSystemSpecificTemplate> {

  public PaymentSystemSpecificTemplateDecoder(final String source) {
    super(source);
  }

  @Override
  protected PaymentSystemSpecificTemplate decode() {
    final PaymentSystemSpecificTemplate result = new PaymentSystemSpecificTemplate();

    iterator.forEachRemaining(value -> {
      final String tag = value.substring(0, DecodeIterator.ID_WORD_COUNT);
      final Integer length = Integer.valueOf(value.substring(DecodeIterator.ID_WORD_COUNT, DecodeIterator.ID_WORD_COUNT + DecodeIterator.VALUE_LENGTH_WORD_COUNT));
      final String string = value.substring(DecodeIterator.ID_WORD_COUNT + DecodeIterator.VALUE_LENGTH_WORD_COUNT, DecodeIterator.ID_WORD_COUNT + DecodeIterator.VALUE_LENGTH_WORD_COUNT + length);
      result.setTag(tag);
      result.setValue(Decoder.decode(string, PaymentSystemSpecific.class));
    });

    return result;
  }

}
// @formatter:on
