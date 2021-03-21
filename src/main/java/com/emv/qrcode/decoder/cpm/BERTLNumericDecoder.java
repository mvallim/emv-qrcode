package com.emv.qrcode.decoder.cpm;

import com.emv.qrcode.core.model.cpm.BERTLNumeric;
import com.emv.qrcode.core.utils.BERUtils;

public final class BERTLNumericDecoder extends DecoderCpm<BERTLNumeric> {

  public BERTLNumericDecoder(final byte[] source) {
    super(source);
  }

  @Override
  protected BERTLNumeric decode() {
    final byte[] value = iterator.next();
    return new BERTLNumeric(BERUtils.valueOfTag(value), BERUtils.valueOf(value));
  }

}