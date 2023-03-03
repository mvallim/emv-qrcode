/*
 * Copyright 2023 the original author or authors.
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

package com.emv.qrcode.decoder.cpm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.BiConsumer;

import org.apache.commons.codec.binary.Hex;

import com.emv.qrcode.core.exception.DuplicateTagException;
import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.core.model.cpm.BERTLAlphanumeric;
import com.emv.qrcode.core.model.cpm.BERTLBinary;
import com.emv.qrcode.core.model.cpm.BERTLCompressedNumeric;
import com.emv.qrcode.core.model.cpm.BERTLNumeric;
import com.emv.qrcode.core.model.cpm.BERTag;
import com.emv.qrcode.core.utils.BERUtils;
import com.emv.qrcode.model.cpm.CommonDataTransparentTemplate;
import com.emv.qrcode.model.cpm.constants.TagTransactionProcessingCodes;

public final class CommonDataTransparentTemplateDecoder extends DecoderCpm<CommonDataTransparentTemplate> {

  private static final Entry<Class<?>, BiConsumer<CommonDataTransparentTemplate, ?>> defaultEntry = consumerTagLengthValue(BERTLBinary.class, CommonDataTransparentTemplate::addAdditionalData);

  private static final Map<BERTag, Entry<Class<?>, BiConsumer<CommonDataTransparentTemplate, ?>>> mapConsumers = new HashMap<>();

  static {
    mapConsumers.put(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME, consumerTagLengthValue(BERTLBinary.class, CommonDataTransparentTemplate::addAdditionalData));
    mapConsumers.put(TagTransactionProcessingCodes.ID_APPLICATION_LABEL, consumerTagLengthValue(BERTLAlphanumeric.class, CommonDataTransparentTemplate::addAdditionalData));
    mapConsumers.put(TagTransactionProcessingCodes.ID_TRACK_2_EQUIVALENT_DATA, consumerTagLengthValue(BERTLBinary.class, CommonDataTransparentTemplate::addAdditionalData));
    mapConsumers.put(TagTransactionProcessingCodes.ID_APPLICATION_PAN, consumerTagLengthValue(BERTLCompressedNumeric.class, CommonDataTransparentTemplate::addAdditionalData));
    mapConsumers.put(TagTransactionProcessingCodes.ID_CARDHOLDER_NAME, consumerTagLengthValue(BERTLAlphanumeric.class, CommonDataTransparentTemplate::addAdditionalData));
    mapConsumers.put(TagTransactionProcessingCodes.ID_LANGUAGE_PREFERENCE, consumerTagLengthValue(BERTLAlphanumeric.class, CommonDataTransparentTemplate::addAdditionalData));
    mapConsumers.put(TagTransactionProcessingCodes.ID_ISSUER_URL, consumerTagLengthValue(BERTLAlphanumeric.class, CommonDataTransparentTemplate::addAdditionalData));
    mapConsumers.put(TagTransactionProcessingCodes.ID_APPLICATION_VERSION_NUMBER, consumerTagLengthValue(BERTLBinary.class, CommonDataTransparentTemplate::addAdditionalData));
    mapConsumers.put(TagTransactionProcessingCodes.ID_TOKEN_REQUESTOR_ID, consumerTagLengthValue(BERTLNumeric.class, CommonDataTransparentTemplate::addAdditionalData));
    mapConsumers.put(TagTransactionProcessingCodes.ID_PAYMENT_ACCOUNT_REFERENCE, consumerTagLengthValue(BERTLAlphanumeric.class, CommonDataTransparentTemplate::addAdditionalData));
    mapConsumers.put(TagTransactionProcessingCodes.ID_LAST_4_DIGITS_OF_PAN, consumerTagLengthValue(BERTLNumeric.class, CommonDataTransparentTemplate::addAdditionalData));
    mapConsumers.put(TagTransactionProcessingCodes.ID_CRYPTOGRAM_INFORMATION_DATA, consumerTagLengthValue(BERTLBinary.class, CommonDataTransparentTemplate::addAdditionalData));
    mapConsumers.put(TagTransactionProcessingCodes.ID_APPLICATION_TRANSACTION_COUNTER, consumerTagLengthValue(BERTLNumeric.class, CommonDataTransparentTemplate::addAdditionalData));
    mapConsumers.put(TagTransactionProcessingCodes.ID_APPLICATION_CRYPTOGRAM, consumerTagLengthValue(BERTLBinary.class, CommonDataTransparentTemplate::addAdditionalData));
    mapConsumers.put(TagTransactionProcessingCodes.ID_ISSUER_APPLICATION_DATA, consumerTagLengthValue(BERTLBinary.class, CommonDataTransparentTemplate::addAdditionalData));
    mapConsumers.put(TagTransactionProcessingCodes.ID_UNPREDICTABLE_NUMBER, consumerTagLengthValue(BERTLBinary.class, CommonDataTransparentTemplate::addAdditionalData));
  }

  public CommonDataTransparentTemplateDecoder(final byte[] source) {
    super(BERUtils.valueOf(source));
  }

  @Override
  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected CommonDataTransparentTemplate decode() throws PresentedModeException {

    final Set<BERTag> tags = new HashSet<>();

    final CommonDataTransparentTemplate result = new CommonDataTransparentTemplate();

    while (iterator.hasNext()) {
      final byte[] value = iterator.next();

      final BERTag tag = new BERTag(BERUtils.valueOfTag(value));

      if (tags.contains(tag)) {
        throw new DuplicateTagException("CommonDataTransparentTemplate", tag.toString(), Hex.encodeHexString(value, false));
      }

      tags.add(tag);

      final Entry<Class<?>, BiConsumer<CommonDataTransparentTemplate, ?>> entry = mapConsumers.getOrDefault(tag, defaultEntry);

      final Class<?> clazz = entry.getKey();

      final BiConsumer consumer = entry.getValue();

      consumer.accept(result, DecoderCpm.decode(value, clazz));
    }

    return result;

  }

}
