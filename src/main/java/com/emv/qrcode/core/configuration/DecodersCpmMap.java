package com.emv.qrcode.core.configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.emv.qrcode.core.model.cpm.BERTLBinary;
import com.emv.qrcode.decoder.cpm.ApplicationSpecificTransparentTemplateDecoder;
import com.emv.qrcode.decoder.cpm.ApplicationTemplateDecoder;
import com.emv.qrcode.decoder.cpm.BERTLBinaryDecoder;
import com.emv.qrcode.decoder.cpm.CommonDataTemplateDecoder;
import com.emv.qrcode.decoder.cpm.CommonDataTransparentTemplateDecoder;
import com.emv.qrcode.decoder.cpm.ConsumerPresentedModeDecoder;
import com.emv.qrcode.decoder.cpm.DecoderCpm;
import com.emv.qrcode.decoder.cpm.PayloadFormatIndicatorDecoder;
import com.emv.qrcode.model.cpm.ApplicationSpecificTransparentTemplate;
import com.emv.qrcode.model.cpm.ApplicationTemplate;
import com.emv.qrcode.model.cpm.CommonDataTemplate;
import com.emv.qrcode.model.cpm.CommonDataTransparentTemplate;
import com.emv.qrcode.model.cpm.ConsumerPresentedMode;
import com.emv.qrcode.model.cpm.PayloadFormatIndicator;

public final class DecodersCpmMap {

  private static final Map<Class<?>, Class<? extends DecoderCpm<?>>> MAP_DECODERS = new ConcurrentHashMap<>();

  static {
    MAP_DECODERS.put(BERTLBinary.class, BERTLBinaryDecoder.class);
    MAP_DECODERS.put(ApplicationSpecificTransparentTemplate.class, ApplicationSpecificTransparentTemplateDecoder.class);
    MAP_DECODERS.put(ApplicationTemplate.class, ApplicationTemplateDecoder.class);
    MAP_DECODERS.put(CommonDataTemplate.class, CommonDataTemplateDecoder.class);
    MAP_DECODERS.put(CommonDataTransparentTemplate.class, CommonDataTransparentTemplateDecoder.class);
    MAP_DECODERS.put(ConsumerPresentedMode.class, ConsumerPresentedModeDecoder.class);
    MAP_DECODERS.put(PayloadFormatIndicator.class, PayloadFormatIndicatorDecoder.class);
  }

  private DecodersCpmMap() {
    super();
  }

  public static Class<? extends DecoderCpm<?>> getDecoder(final Class<?> clazz) {
    return MAP_DECODERS.get(clazz);
  }

}
