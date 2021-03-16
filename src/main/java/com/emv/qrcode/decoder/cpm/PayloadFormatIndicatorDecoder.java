package com.emv.qrcode.decoder.cpm;

import com.emv.qrcode.core.model.BERTag;
import com.emv.qrcode.core.utils.BERUtils;
import com.emv.qrcode.model.cpm.PayloadFormatIndicator;

public final class PayloadFormatIndicatorDecoder extends DecoderCpm<PayloadFormatIndicator> {

  public PayloadFormatIndicatorDecoder(final byte[] source) {
    super(source);
  }

  @Override
  protected PayloadFormatIndicator decode() {

    final PayloadFormatIndicator result = new PayloadFormatIndicator();

    while (iterator.hasNext()) {
      final byte[] value = iterator.next();

      result.setTag(new BERTag(BERUtils.copyBytesOfTag(value)));
      result.setValue(BERUtils.copyBytesOfLength(value));
    }

    return result;

  }

}
