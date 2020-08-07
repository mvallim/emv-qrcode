package com.emv.qrcode.model.cpm;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.model.BERTLV;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;

import lombok.Getter;

@Getter
public class CommonDataTransparentTemplate implements BERTLV<String, BERTLV<String, String>> {

  private static final long serialVersionUID = 5072500891200624780L;

  private final String tag = ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TRANSPARENT_TEMPLATE;

  private Integer length;

  private BERTLV<String, String> value;

  @Override
  public String toString() {

    if (Objects.isNull(value)) {
      return StringUtils.EMPTY;
    }

    final String string = value.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    return String.format("%s%02X%s", tag, string.length(), Hex.encodeHex(string.getBytes(StandardCharsets.UTF_8), false));

  }

}
