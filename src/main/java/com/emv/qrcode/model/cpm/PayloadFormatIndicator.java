package com.emv.qrcode.model.cpm;

import com.emv.qrcode.core.model.BERTLString;
import com.emv.qrcode.core.model.BERTag;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;

public class PayloadFormatIndicator extends BERTLString {

  private static final long serialVersionUID = 780284119561670846L;

  public PayloadFormatIndicator() {
    super(new BERTag(ConsumerPresentedModeFieldCodes.ID_PAYLOAD_FORMAT_INDICATOR), "CPV01");
  }

}
