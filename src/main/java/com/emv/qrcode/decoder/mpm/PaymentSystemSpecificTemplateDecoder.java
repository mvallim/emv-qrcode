package com.emv.qrcode.decoder.mpm;

import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.core.utils.TLVUtils;
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
      result.setTag(TLVUtils.valueOfTag(value));
      result.setValue(DecoderMpm.decode(value, PaymentSystemSpecific.class));
    }

    return result;
  }

}
// @formatter:on
