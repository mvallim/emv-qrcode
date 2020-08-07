package com.emv.qrcode.model.cpm;

import com.emv.qrcode.core.model.BERTLV;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;

import lombok.Getter;

@Getter
public class PayloadFormatIndicator implements BERTLV<String, String> {

  private static final long serialVersionUID = 780284119561670846L;

  private final String tag = ConsumerPresentedModeFieldCodes.ID_PAYLOAD_FORMAT_INDICATOR;

  private Integer length;

  private String value;

}
