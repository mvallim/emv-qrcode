package com.emv.qrcode.model.cpm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.emv.qrcode.core.model.BERTLV;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;

import lombok.Getter;

@Getter
public class ApplicationTemplate extends AdditionalData implements BERTLV<Integer, List<ApplicationSpecificTransparentTemplate>> {

  private static final long serialVersionUID = 2418153324275018348L;

  private final List<ApplicationSpecificTransparentTemplate> value = new LinkedList<>();

  public void addApplicationSpecificTransparentTemplate(final ApplicationSpecificTransparentTemplate applicationSpecificTransparentTemplate) {
    value.add(applicationSpecificTransparentTemplate);
  }

  @Override
  public Integer getTag() {
    return ConsumerPresentedModeFieldCodes.ID_APPLICATION_TEMPLATE;
  }

  @Override
  public byte[] getBytes() throws IOException {
    try (final ByteArrayOutputStream out = new ByteArrayOutputStream()) {

      try (final ByteArrayOutputStream stream = new ByteArrayOutputStream()) {

        for (final ApplicationSpecificTransparentTemplate applicationSpecificTransparentTemplate : value) {
          stream.write(applicationSpecificTransparentTemplate.getBytes());
        }

        final byte[] selfBytes = super.getBytes();
        final byte[] valueBytes = stream.toByteArray();

        final int len = selfBytes.length + valueBytes.length;

        if (len == 0) {
          return EMPTY_BYTES;
        }

        out.write(len);
        out.write(selfBytes);
        out.write(valueBytes);

        return out.toByteArray();
      }
    }
  }

}
