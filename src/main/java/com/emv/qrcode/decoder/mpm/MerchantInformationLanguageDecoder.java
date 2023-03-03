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

package com.emv.qrcode.decoder.mpm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;

import com.emv.qrcode.core.exception.DuplicateTagException;
import com.emv.qrcode.core.exception.InvalidTagException;
import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.core.model.mpm.TagLengthString;
import com.emv.qrcode.core.utils.TLVUtils;
import com.emv.qrcode.model.mpm.MerchantInformationLanguage;
import com.emv.qrcode.model.mpm.constants.MerchantInformationLanguageFieldCodes;

// @formatter:off
public final class MerchantInformationLanguageDecoder extends DecoderMpm<MerchantInformationLanguage> {

  private static final Map<String, Entry<Class<?>, BiConsumer<MerchantInformationLanguage, ?>>> mapConsumers = new HashMap<>();

  static {
    mapConsumers.put(MerchantInformationLanguageFieldCodes.ID_LANGUAGE_PREFERENCE, consumerTagLengthValue(String.class, MerchantInformationLanguage::setLanguagePreference));
    mapConsumers.put(MerchantInformationLanguageFieldCodes.ID_MERCHANT_NAME, consumerTagLengthValue(String.class, MerchantInformationLanguage::setMerchantName));
    mapConsumers.put(MerchantInformationLanguageFieldCodes.ID_MERCHANT_CITY, consumerTagLengthValue(String.class, MerchantInformationLanguage::setMerchantCity));
    mapConsumers.put(MerchantInformationLanguageFieldCodes.ID_RFU_FOR_EMVCO, consumerTagLengthValue(TagLengthString.class, MerchantInformationLanguage::addRFUforEMVCo));
  }

  public MerchantInformationLanguageDecoder(final String source) {
    super(TLVUtils.valueOf(source));
  }

  @Override
  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected MerchantInformationLanguage decode() throws PresentedModeException {

    final Set<String> tags = new HashSet<>();

    final MerchantInformationLanguage result = new MerchantInformationLanguage();

    while(iterator.hasNext()) {
      final String value = iterator.next();

      final String tag = TLVUtils.valueOfTag(value);

      final String derivateId = derivateId(tag);

      if (tags.contains(tag)) {
        throw new DuplicateTagException("MerchantInformationLanguage", tag, value);
      }

      tags.add(tag);

      final Entry<Class<?>, BiConsumer<MerchantInformationLanguage, ?>> entry = mapConsumers.get(derivateId);

      if (Objects.isNull(entry)) {
        throw new InvalidTagException("MerchantInformationLanguage", tag, value);
      }

      final Class<?> clazz = entry.getKey();

      final BiConsumer consumer = entry.getValue();

      consumer.accept(result, DecoderMpm.decode(value, clazz));
    }

    return result;
  }

  private String derivateId(final String id) {

    if (betweenRFUForEMVCORange(id)) {
      return MerchantInformationLanguageFieldCodes.ID_RFU_FOR_EMVCO;
    }

    return id;
  }

  private boolean betweenRFUForEMVCORange(final String value) {
    return value.compareTo(MerchantInformationLanguageFieldCodes.ID_RFU_FOR_EMVCO_RANGE_START) >= 0
        && value.compareTo(MerchantInformationLanguageFieldCodes.ID_RFU_FOR_EMVCO_RANGE_END) <= 0;
  }

}
// @formatter:on
