package com.emv.qrcode.decoder.mpm;

import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.core.utils.TLVUtils;
import com.emv.qrcode.model.mpm.MerchantAccountInformationReserved;

// @formatter:off
public final class MerchantAccountInformationReservedDecoder extends DecoderMpm<MerchantAccountInformationReserved> {

  public MerchantAccountInformationReservedDecoder(final String source) {
    super(source);
  }

  @Override
  protected MerchantAccountInformationReserved decode() throws PresentedModeException {
    final String value = iterator.next();
    return new MerchantAccountInformationReserved(TLVUtils.valueOf(value));
  }

}
// @formatter:on
