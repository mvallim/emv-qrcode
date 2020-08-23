package com.emv.qrcode.core.configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.emv.qrcode.decoder.cpm.DecoderCpm;

public final class DecodersCpmMap {

  private static final Map<Class<?>, Class<? extends DecoderCpm<?>>> MAP_DECODERS = new ConcurrentHashMap<>();

  static {
  }

  private DecodersCpmMap() {
    super();
  }

  public static Class<? extends DecoderCpm<?>> getDecoder(final Class<?> clazz) {
    return MAP_DECODERS.get(clazz);
  }

}
