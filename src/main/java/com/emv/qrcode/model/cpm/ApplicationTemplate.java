package com.emv.qrcode.model.cpm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

import com.emv.qrcode.core.model.BERTLV;
import com.emv.qrcode.core.model.BERTag;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;

import lombok.Getter;

@Getter
public class ApplicationTemplate extends AdditionalData {

  private static final long serialVersionUID = 2418153324275018348L;

  private static final BERTag tag = ConsumerPresentedModeFieldCodes.ID_APPLICATION_TEMPLATE;

  private ApplicationSpecificTransparentTemplate applicationSpecificTransparentTemplate;

  public BERTag getTag() {
    return tag;
  }

  public void setApplicationSpecificTransparentTemplate(final ApplicationSpecificTransparentTemplate applicationSpecificTransparentTemplate) {
    this.applicationSpecificTransparentTemplate = applicationSpecificTransparentTemplate;
  }

  @Override
  public byte[] getBytes() throws IOException {
    try (final ByteArrayOutputStream out = new ByteArrayOutputStream()) {
      try (final ByteArrayOutputStream stream = new ByteArrayOutputStream()) {

        if (Objects.nonNull(applicationSpecificTransparentTemplate)) {
          stream.write(applicationSpecificTransparentTemplate.getBytes());
        }

        final byte[] selfBytes = super.getBytes();
        final byte[] valueBytes = stream.toByteArray();

        final int len = selfBytes.length + valueBytes.length;

        if (len == 0) {
          return BERTLV.EMPTY_BYTES;
        }

        out.write(tag.getBytes());
        out.write(len);
        out.write(selfBytes);
        out.write(valueBytes);

        return out.toByteArray();
      }
    }
  }

}
