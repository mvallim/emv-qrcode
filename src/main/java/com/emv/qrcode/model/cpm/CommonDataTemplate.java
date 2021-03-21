package com.emv.qrcode.model.cpm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

import com.emv.qrcode.core.model.cpm.BERTLV;
import com.emv.qrcode.core.model.cpm.BERTag;
import com.emv.qrcode.core.model.cpm.BERTemplate;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;

import lombok.Getter;

@Getter
public class CommonDataTemplate extends AdditionalData implements BERTemplate<byte[]> {

  private static final long serialVersionUID = -4642312662946053298L;

  private static final BERTag tag = ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TEMPLATE;

  private CommonDataTransparentTemplate commonDataTransparentTemplate;

  public BERTag getTag() {
    return tag;
  }

  public void setCommonDataTransparentTemplate(final CommonDataTransparentTemplate commonDataTransparentTemplate) {
    this.commonDataTransparentTemplate = commonDataTransparentTemplate;
  }

  @Override
  public byte[] getBytes() throws IOException {
    try (final ByteArrayOutputStream out = new ByteArrayOutputStream()) {
      try (final ByteArrayOutputStream stream = new ByteArrayOutputStream()) {

        if (Objects.nonNull(commonDataTransparentTemplate)) {
          stream.write(commonDataTransparentTemplate.getBytes());
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
