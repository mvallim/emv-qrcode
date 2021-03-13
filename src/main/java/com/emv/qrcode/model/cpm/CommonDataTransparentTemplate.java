package com.emv.qrcode.model.cpm;

import com.emv.qrcode.core.model.BERTLV;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;

import lombok.Getter;

@Getter
public class CommonDataTransparentTemplate extends BERTLV {

  private static final long serialVersionUID = 5072500891200624780L;

  public CommonDataTransparentTemplate(final byte[] value) {
    super(ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TRANSPARENT_TEMPLATE, value);
  }

}
