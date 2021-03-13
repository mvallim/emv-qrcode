package com.emv.qrcode.model.cpm;

import com.emv.qrcode.core.model.BERTLV;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;

import lombok.Getter;

@Getter
public class ApplicationSpecificTransparentTemplate extends BERTLV {

  private static final long serialVersionUID = -5306048635485515245L;

  public ApplicationSpecificTransparentTemplate(final byte[] value) {
    super(ConsumerPresentedModeFieldCodes.ID_APPLICATION_SPECIFIC_TRANSPARENT_TEMPLATE, value);
  }

}
