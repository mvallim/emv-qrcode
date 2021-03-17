package com.emv.qrcode.decoder.cpm;

import com.emv.qrcode.core.model.cpm.BERTLAlphanumeric;
import com.emv.qrcode.core.utils.BERUtils;

public final class BERTLAlphanumericDecoder extends DecoderCpm<BERTLAlphanumeric> {

  public BERTLAlphanumericDecoder(final byte[] source) {
    super(source);
  }

  @Override
  protected BERTLAlphanumeric decode() {
    final byte[] value = iterator.next();
    return new BERTLAlphanumeric(BERUtils.valueOfTag(value), BERUtils.valueOf(value));
  }

}