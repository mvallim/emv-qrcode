package com.emv.qrcode.decoder.cpm;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;

import com.emv.qrcode.core.model.BERTag;
import com.emv.qrcode.model.cpm.PayloadFormatIndicator;

public final class PayloadFormatIndicatorDecoder extends DecoderCpm<PayloadFormatIndicator> {

  private static final Map<BERTag, Entry<Class<?>, BiConsumer<PayloadFormatIndicator, ?>>> mapConsumers = new HashMap<>();

  public PayloadFormatIndicatorDecoder(final byte[] source) {
    super(source);
  }

  @Override
  protected PayloadFormatIndicator decode() {

    return new PayloadFormatIndicator();

  }

}
