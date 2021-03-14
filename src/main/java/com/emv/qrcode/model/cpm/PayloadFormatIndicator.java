package com.emv.qrcode.model.cpm;

import com.emv.qrcode.core.model.BERTLAlphanumeric;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;

public class PayloadFormatIndicator extends BERTLAlphanumeric {

  private static final long serialVersionUID = 780284119561670846L;

  public PayloadFormatIndicator() {
    super(ConsumerPresentedModeFieldCodes.ID_PAYLOAD_FORMAT_INDICATOR, "CPV01");
  }

}
