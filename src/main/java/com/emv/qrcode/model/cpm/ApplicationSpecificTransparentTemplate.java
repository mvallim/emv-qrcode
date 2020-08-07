package com.emv.qrcode.model.cpm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

import org.apache.commons.codec.binary.Hex;

import com.emv.qrcode.core.model.BERTLV;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ApplicationSpecificTransparentTemplate implements BERTLV<Integer, BERTLV<Integer, String>> {

  private static final long serialVersionUID = -5306048635485515245L;

  @Setter
  private BERTLV<Integer, String> value;

  @Override
  public Integer getTag() {
    return ConsumerPresentedModeFieldCodes.ID_APPLICATION_SPECIFIC_TRANSPARENT_TEMPLATE;
  }

  @Override
  public Integer getLength() {
    return value.getValue().length();
  }

  @Override
  public byte[] getBytes() throws IOException {

    if (Objects.isNull(value)) {
      return EMPTY_BYTES;
    }

    final byte[] bytes = value.getBytes();

    if (bytes.length == 0) {
      return EMPTY_BYTES;
    }

    try (final ByteArrayOutputStream out = new ByteArrayOutputStream()) {
      out.write(getTag());
      out.write(bytes.length);
      out.write(bytes);
      return out.toByteArray();
    }

  }

  @Override
  public String toHex() throws IOException {
    return Hex.encodeHexString(getBytes(), false);
  }

}
