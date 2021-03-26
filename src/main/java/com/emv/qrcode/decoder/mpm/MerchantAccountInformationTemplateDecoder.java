package com.emv.qrcode.decoder.mpm;

import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.core.utils.TLVUtils;
import com.emv.qrcode.model.mpm.MerchantAccountInformationReserved;
import com.emv.qrcode.model.mpm.MerchantAccountInformationReservedAdditional;
import com.emv.qrcode.model.mpm.MerchantAccountInformationTemplate;
import com.emv.qrcode.model.mpm.constants.MerchantPresentedModeCodes;

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

      final String tag = TLVUtils.valueOfTag(value);

      result.setTag(tag);

      if (betweenAccountInformationReservedRange(tag)) {
        result.setValue(DecoderMpm.decode(value, MerchantAccountInformationReserved.class));
      }

      if (betweenAccountInformationaReservedAdditionalRange(tag)) {
        result.setValue(DecoderMpm.decode(value, MerchantAccountInformationReservedAdditional.class));
      }

    }

    return result;
  }

  private boolean betweenAccountInformationReservedRange(final String value) {
    return value.compareTo(MerchantPresentedModeCodes.ID_MERCHANT_ACCOUNT_INFORMATION_RESERVED_RANGE_START) >= 0
        && value.compareTo(MerchantPresentedModeCodes.ID_MERCHANT_ACCOUNT_INFORMATION_RESERVED_RANGE_END) <= 0;
  }

  private boolean betweenAccountInformationaReservedAdditionalRange(final String value) {
    return value.compareTo(MerchantPresentedModeCodes.ID_MERCHANT_ACCOUNT_INFORMATION_RESERVED_ADDITIONAL_RANGE_START) >= 0
        && value.compareTo(MerchantPresentedModeCodes.ID_MERCHANT_ACCOUNT_INFORMATION_RESERVED_ADDITIONAL_RANGE_END) <= 0;
  }

}
// @formatter:on
