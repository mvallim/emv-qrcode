package com.emv.qrcode.model.cpm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.emv.qrcode.core.model.BERTLV;
import com.emv.qrcode.core.model.BERTag;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;

import lombok.Getter;

@Getter
public class CommonDataTransparentTemplate extends AdditionalData {

  private static final long serialVersionUID = 5072500891200624780L;

  private static final BERTag tag = ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TRANSPARENT_TEMPLATE;

  @Override
  public byte[] getBytes() throws IOException {
    try (final ByteArrayOutputStream out = new ByteArrayOutputStream()) {
      final byte[] selfBytes = super.getBytes();

      final int len = selfBytes.length;

      if (len == 0) {
        return BERTLV.EMPTY_BYTES;
      }

      out.write(tag.getBytes());
      out.write(len);
      out.write(selfBytes);

      return out.toByteArray();
    }
  }

}
