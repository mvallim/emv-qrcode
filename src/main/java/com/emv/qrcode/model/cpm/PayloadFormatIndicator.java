package com.emv.qrcode.model.cpm;

import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.model.BERTLV;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;

import lombok.Getter;

@Getter
public class PayloadFormatIndicator implements BERTLV<String, String> {

  private static final long serialVersionUID = 780284119561670846L;

  private Integer length;

  private String value;

  @Override
  public String getTag() {
    return ConsumerPresentedModeFieldCodes.ID_PAYLOAD_FORMAT_INDICATOR;
  }

  @Override
  public String toString() {

    if (StringUtils.isBlank(value)) {
      return StringUtils.EMPTY;
    }

    return String.format("%s%02X%s", getTag(), value.length(), Hex.encodeHex(value.getBytes(StandardCharsets.UTF_8), false));

  }

}
