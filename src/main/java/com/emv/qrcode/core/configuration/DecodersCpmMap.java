/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.emv.qrcode.core.configuration;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.emv.qrcode.core.model.cpm.BERTLAlphanumeric;
import com.emv.qrcode.core.model.cpm.BERTLBinary;
import com.emv.qrcode.core.model.cpm.BERTLCompressedNumeric;
import com.emv.qrcode.core.model.cpm.BERTLNumeric;
import com.emv.qrcode.decoder.cpm.ApplicationSpecificTransparentTemplateDecoder;
import com.emv.qrcode.decoder.cpm.ApplicationTemplateDecoder;
import com.emv.qrcode.decoder.cpm.BERTLAlphanumericDecoder;
import com.emv.qrcode.decoder.cpm.BERTLBinaryDecoder;
import com.emv.qrcode.decoder.cpm.BERTLCompressedNumericDecoder;
import com.emv.qrcode.decoder.cpm.BERTLNumericDecoder;
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
    MAP_DECODERS.put(BERTLAlphanumeric.class, BERTLAlphanumericDecoder.class);
    MAP_DECODERS.put(BERTLCompressedNumeric.class, BERTLCompressedNumericDecoder.class);
    MAP_DECODERS.put(BERTLNumeric.class, BERTLNumericDecoder.class);
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

  public static Map<Class<?>, Class<? extends DecoderCpm<?>>> getConfiguration() {
    return Collections.unmodifiableMap(MAP_DECODERS);
  }

  public static void replaceDecoder(final Class<?> tagClass, final Class<? extends DecoderCpm<?>> decoderClass) {
    if (MAP_DECODERS.containsKey(tagClass)) {
      MAP_DECODERS.replace(tagClass, decoderClass);
    }
  }

  public static void putDecoder(final Class<?> tagClass, final Class<? extends DecoderCpm<?>> decoderClass) {
    MAP_DECODERS.put(tagClass, decoderClass);
  }

  public static Class<? extends DecoderCpm<?>> getDecoder(final Class<?> clazz) {
    return MAP_DECODERS.get(clazz);
  }

}
