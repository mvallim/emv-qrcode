package com.emv.qrcode.model.cpm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.emv.qrcode.core.model.BERTLV;
import com.emv.qrcode.core.model.BERTag;
import com.emv.qrcode.core.model.TLV;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;

import lombok.Getter;

@Getter
public class CommonDataTemplate extends AdditionalData implements TLV<BERTag, List<CommonDataTransparentTemplate>> {

  private static final long serialVersionUID = -4642312662946053298L;

  private final BERTLV self;

  private final List<CommonDataTransparentTemplate> value = new LinkedList<>();

  public CommonDataTemplate() {
    self = new BERTLV(ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TEMPLATE, BERTLV.EMPTY_BYTES);
  }

  public void addCommonDataTransparentTemplate(final CommonDataTransparentTemplate commonDataTransparentTemplate) {
    value.add(commonDataTransparentTemplate);
  }

  @Override
  public BERTag getTag() {
    return self.getTag();
  }

  @Override
  public byte[] getBytes() throws IOException {
    try (final ByteArrayOutputStream out = new ByteArrayOutputStream()) {

      try (final ByteArrayOutputStream stream = new ByteArrayOutputStream()) {

        for (final CommonDataTransparentTemplate commonDataTransparentTemplate : value) {
          stream.write(commonDataTransparentTemplate.getBytes());
        }

        final byte[] selfBytes = super.getBytes();
        final byte[] valueBytes = stream.toByteArray();

        final int len = selfBytes.length + valueBytes.length;

        if (len == 0) {
          return BERTLV.EMPTY_BYTES;
        }

        out.write(len);
        out.write(selfBytes);
        out.write(valueBytes);

        return out.toByteArray();
      }
    }
  }

}
