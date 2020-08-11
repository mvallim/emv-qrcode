package com.emv.qrcode.model.cpm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

import org.apache.commons.lang3.ArrayUtils;

import com.emv.qrcode.core.model.BERTLV;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;

import lombok.Getter;
import lombok.Setter;

@Getter
public class CommonDataTransparentTemplate implements BERTLV<Integer, BERTLV<Integer, String>> {

  private static final long serialVersionUID = 5072500891200624780L;

  @Setter
  private BERTLV<Integer, String> value;

  @Override
  public Integer getTag() {
    return ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TRANSPARENT_TEMPLATE;
  }

  @Override
  public byte[] getBytes() throws IOException {

    if (Objects.isNull(value)) {
      return EMPTY_BYTES;
    }

    final byte[] bytes = value.getBytes();

    if (ArrayUtils.isEmpty(bytes)) {
      return EMPTY_BYTES;
    }

    try (final ByteArrayOutputStream out = new ByteArrayOutputStream()) {
      out.write(getTag());
      out.write(bytes.length);
      out.write(bytes);
      return out.toByteArray();
    }

  }

}
