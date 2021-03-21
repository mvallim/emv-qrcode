package com.emv.qrcode.decoder.mpm;

import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.core.utils.TLVUtils;
import com.emv.qrcode.model.mpm.MerchantAccountInformation;
import com.emv.qrcode.model.mpm.MerchantAccountInformationTemplate;

// @formatter:off
public final class MerchantAccountInformationTemplateDecoder extends DecoderMpm<MerchantAccountInformationTemplate> {

  public MerchantAccountInformationTemplateDecoder(final String source) {
    super(source);
  }

  @Override
  protected MerchantAccountInformationTemplate decode() throws PresentedModeException {

    final MerchantAccountInformationTemplate result = new MerchantAccountInformationTemplate();

    while(iterator.hasNext()) {
      final String value = iterator.next();
      result.setTag(TLVUtils.valueOfTag(value));
      result.setValue(DecoderMpm.decode(value, MerchantAccountInformation.class));
    }

    return result;
  }

}
// @formatter:on
