package com.emv.qrcode.model.cpm;

import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.model.BERTLV;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;

public class PayloadFormatIndicator extends BERTLV {

  private static final long serialVersionUID = 780284119561670846L;

  public PayloadFormatIndicator() {
    super(ConsumerPresentedModeFieldCodes.ID_PAYLOAD_FORMAT_INDICATOR, "CPV01".getBytes());
  }

  public PayloadFormatIndicator(final Integer tag, final String value) {
    super(tag, StringUtils.isNotEmpty(value) ? value.getBytes() : new byte[0]);
  }

  public final void setValue(final String value) {
    setValue(StringUtils.isNotEmpty(value) ? value.getBytes() : new byte[0]);
  }

}
