package com.emv.qrcode.decoder.cpm;

import com.emv.qrcode.core.model.cpm.BERTLCompressedNumeric;
import com.emv.qrcode.core.utils.BERUtils;

public final class BERTLCompressedNumericDecoder extends DecoderCpm<BERTLCompressedNumeric> {

  public BERTLCompressedNumericDecoder(final byte[] source) {
    super(source);
  }

  @Override
  protected BERTLCompressedNumeric decode() {
    final byte[] value = iterator.next();
    return new BERTLCompressedNumeric(BERUtils.valueOfTag(value), BERUtils.valueOf(value));
  }

}