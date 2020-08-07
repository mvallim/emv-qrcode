package com.emv.qrcode.model.cpm;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.model.BERTLV;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;

import lombok.Getter;

@Getter
public class ApplicationSpecificTransparentTemplate implements BERTLV<String, BERTLV<String, String>> {

  private static final long serialVersionUID = -5306048635485515245L;

  private Integer length;

  private BERTLV<String, String> value;

  @Override
  public String getTag() {
    return ConsumerPresentedModeFieldCodes.ID_APPLICATION_SPECIFIC_TRANSPARENT_TEMPLATE;
  }

  @Override
  public String toString() {

    if (Objects.isNull(value)) {
      return StringUtils.EMPTY;
    }

    final String string = value.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    return String.format("%s%02X%s", getTag(), string.length(), Hex.encodeHex(string.getBytes(StandardCharsets.UTF_8), false));

  }

}
