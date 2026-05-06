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

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Configuration class that maps Consumer Presented Mode (CPM) model classes to
 * their corresponding decoder classes. This registry allows the framework to
 * dynamically select the appropriate decoder for a given BER-TLV data type.
 *
 * @see DecoderCpm
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
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

  /**
   * Returns an unmodifiable view of the CPM decoder configuration map.
   * @return an unmodifiable map of model classes to their corresponding decoder classes
   */
  public static Map<Class<?>, Class<? extends DecoderCpm<?>>> getConfiguration() {
    return Collections.unmodifiableMap(MAP_DECODERS);
  }

  /**
   * Replaces the decoder class for the specified model class if it is already registered.
   * @param tagClass the model class whose decoder is to be replaced
   * @param decoderClass the new decoder class to associate with the model class
   */
  public static void replaceDecoder(final Class<?> tagClass, final Class<? extends DecoderCpm<?>> decoderClass) {
    if (MAP_DECODERS.containsKey(tagClass)) {
      MAP_DECODERS.replace(tagClass, decoderClass);
    }
  }

  /**
   * Registers the specified decoder class for the given model class.
   * If the model class is not yet registered, it will be added; otherwise, its decoder will be replaced.
   * @param tagClass the model class to associate with the decoder
   * @param decoderClass the decoder class to register
   */
  public static void putDecoder(final Class<?> tagClass, final Class<? extends DecoderCpm<?>> decoderClass) {
    MAP_DECODERS.put(tagClass, decoderClass);
  }

  /**
   * Returns the decoder class registered for the given model class.
   *
   * @param clazz the model class to look up
   * @return the corresponding decoder class, or null if not found
   */
  public static Class<? extends DecoderCpm<?>> getDecoder(final Class<?> clazz) {
    return MAP_DECODERS.get(clazz);
  }

}
