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

import com.emv.qrcode.core.model.mpm.TagLengthString;
import com.emv.qrcode.decoder.mpm.AdditionalDataFieldDecoder;
import com.emv.qrcode.decoder.mpm.AdditionalDataFieldTemplateDecoder;
import com.emv.qrcode.decoder.mpm.DecoderMpm;
import com.emv.qrcode.decoder.mpm.MerchantAccountInformationReservedAdditionalDecoder;
import com.emv.qrcode.decoder.mpm.MerchantAccountInformationReservedDecoder;
import com.emv.qrcode.decoder.mpm.MerchantAccountInformationTemplateDecoder;
import com.emv.qrcode.decoder.mpm.MerchantInformationLanguageDecoder;
import com.emv.qrcode.decoder.mpm.MerchantInformationLanguageTemplateDecoder;
import com.emv.qrcode.decoder.mpm.MerchantPresentedModeDecoder;
import com.emv.qrcode.decoder.mpm.PaymentSystemSpecificDecoder;
import com.emv.qrcode.decoder.mpm.PaymentSystemSpecificTemplateDecoder;
import com.emv.qrcode.decoder.mpm.StringDecoder;
import com.emv.qrcode.decoder.mpm.TagLengthStringDecoder;
import com.emv.qrcode.decoder.mpm.UnreservedDecoder;
import com.emv.qrcode.decoder.mpm.UnreservedTemplateDecoder;
import com.emv.qrcode.model.mpm.AdditionalDataField;
import com.emv.qrcode.model.mpm.AdditionalDataFieldTemplate;
import com.emv.qrcode.model.mpm.MerchantAccountInformationReserved;
import com.emv.qrcode.model.mpm.MerchantAccountInformationReservedAdditional;
import com.emv.qrcode.model.mpm.MerchantAccountInformationTemplate;
import com.emv.qrcode.model.mpm.MerchantInformationLanguage;
import com.emv.qrcode.model.mpm.MerchantInformationLanguageTemplate;
import com.emv.qrcode.model.mpm.MerchantPresentedMode;
import com.emv.qrcode.model.mpm.PaymentSystemSpecific;
import com.emv.qrcode.model.mpm.PaymentSystemSpecificTemplate;
import com.emv.qrcode.model.mpm.Unreserved;
import com.emv.qrcode.model.mpm.UnreservedTemplate;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Configuration class that maps Merchant Presented Mode (MPM) model classes to
 * their corresponding decoder classes. This registry allows the framework to
 * dynamically select the appropriate decoder for a given data type.
 *
 * @see DecoderMpm
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DecodersMpmMap {

  private static final Map<Class<?>, Class<? extends DecoderMpm<?>>> MAP_DECODERS = new ConcurrentHashMap<>();

  static {
    MAP_DECODERS.put(String.class, StringDecoder.class);
    MAP_DECODERS.put(TagLengthString.class, TagLengthStringDecoder.class);
    MAP_DECODERS.put(MerchantPresentedMode.class, MerchantPresentedModeDecoder.class);
    MAP_DECODERS.put(AdditionalDataFieldTemplate.class, AdditionalDataFieldTemplateDecoder.class);
    MAP_DECODERS.put(AdditionalDataField.class, AdditionalDataFieldDecoder.class);
    MAP_DECODERS.put(MerchantInformationLanguageTemplate.class, MerchantInformationLanguageTemplateDecoder.class);
    MAP_DECODERS.put(MerchantInformationLanguage.class, MerchantInformationLanguageDecoder.class);
    MAP_DECODERS.put(MerchantAccountInformationTemplate.class, MerchantAccountInformationTemplateDecoder.class);
    MAP_DECODERS.put(MerchantAccountInformationReserved.class, MerchantAccountInformationReservedDecoder.class);
    MAP_DECODERS.put(MerchantAccountInformationReservedAdditional.class, MerchantAccountInformationReservedAdditionalDecoder.class);
    MAP_DECODERS.put(UnreservedTemplate.class, UnreservedTemplateDecoder.class);
    MAP_DECODERS.put(Unreserved.class, UnreservedDecoder.class);
    MAP_DECODERS.put(PaymentSystemSpecificTemplate.class, PaymentSystemSpecificTemplateDecoder.class);
    MAP_DECODERS.put(PaymentSystemSpecific.class, PaymentSystemSpecificDecoder.class);
  }

  /**
   * Returns an unmodifiable view of the MPM decoder configuration map.
   * @return an unmodifiable map of model classes to their corresponding decoder classes
   */
  public static Map<Class<?>, Class<? extends DecoderMpm<?>>> getConfiguration() {
    return Collections.unmodifiableMap(MAP_DECODERS);
  }

  /**
   * Replaces the decoder class for the specified model class if it is already registered.
   * @param tagClass the model class whose decoder is to be replaced
   * @param decoderClass the new decoder class to associate with the model class
   */
  public static void replaceDecoder(final Class<?> tagClass, final Class<? extends DecoderMpm<?>> decoderClass) {
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
  public static void putDecoder(final Class<?> tagClass, final Class<? extends DecoderMpm<?>> decoderClass) {
    MAP_DECODERS.put(tagClass, decoderClass);
  }

  /**
   * Returns the decoder class registered for the given model class.
   *
   * @param clazz the model class to look up
   * @return the corresponding decoder class, or null if not found
   */
  public static Class<? extends DecoderMpm<?>> getDecoder(final Class<?> clazz) {
    return MAP_DECODERS.get(clazz);
  }

}
