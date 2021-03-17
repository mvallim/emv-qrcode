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
import com.emv.qrcode.model.cpm.CommonDataTemplate;
import com.emv.qrcode.model.cpm.CommonDataTransparentTemplate;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;
import com.emv.qrcode.model.cpm.constants.TagTransactionProcessingCodes;

public final class CommonDataTemplateDecoder extends DecoderCpm<CommonDataTemplate> {

  private static final Entry<Class<?>, BiConsumer<CommonDataTemplate, ?>> defaultEntry = consumerTagLengthValue(BERTLBinary.class, CommonDataTemplate::addAdditionalData);

  private static final Map<BERTag, Entry<Class<?>, BiConsumer<CommonDataTemplate, ?>>> mapConsumers = new HashMap<>();

  static {
    mapConsumers.put(ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TRANSPARENT_TEMPLATE, consumerTagLengthValue(CommonDataTransparentTemplate.class, CommonDataTemplate::setCommonDataTransparentTemplate));
    mapConsumers.put(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME, consumerTagLengthValue(BERTLBinary.class, CommonDataTemplate::addAdditionalData));
    mapConsumers.put(TagTransactionProcessingCodes.ID_APPLICATION_LABEL, consumerTagLengthValue(BERTLAlphanumeric.class, CommonDataTemplate::addAdditionalData));
    mapConsumers.put(TagTransactionProcessingCodes.ID_TRACK_2_EQUIVALENT_DATA, consumerTagLengthValue(BERTLBinary.class, CommonDataTemplate::addAdditionalData));
    mapConsumers.put(TagTransactionProcessingCodes.ID_APPLICATION_PAN, consumerTagLengthValue(BERTLCompressedNumeric.class, CommonDataTemplate::addAdditionalData));
    mapConsumers.put(TagTransactionProcessingCodes.ID_CARDHOLDER_NAME, consumerTagLengthValue(BERTLAlphanumeric.class, CommonDataTemplate::addAdditionalData));
    mapConsumers.put(TagTransactionProcessingCodes.ID_LANGUAGE_PREFERENCE, consumerTagLengthValue(BERTLAlphanumeric.class, CommonDataTemplate::addAdditionalData));
    mapConsumers.put(TagTransactionProcessingCodes.ID_ISSUER_URL, consumerTagLengthValue(BERTLAlphanumeric.class, CommonDataTemplate::addAdditionalData));
    mapConsumers.put(TagTransactionProcessingCodes.ID_APPLICATION_VERSION_NUMBER, consumerTagLengthValue(BERTLBinary.class, CommonDataTemplate::addAdditionalData));
    mapConsumers.put(TagTransactionProcessingCodes.ID_TOKEN_REQUESTOR_ID, consumerTagLengthValue(BERTLNumeric.class, CommonDataTemplate::addAdditionalData));
    mapConsumers.put(TagTransactionProcessingCodes.ID_PAYMENT_ACCOUNT_REFERENCE, consumerTagLengthValue(BERTLAlphanumeric.class, CommonDataTemplate::addAdditionalData));
    mapConsumers.put(TagTransactionProcessingCodes.ID_LAST_4_DIGITS_OF_PAN, consumerTagLengthValue(BERTLAlphanumeric.class, CommonDataTemplate::addAdditionalData));
    mapConsumers.put(TagTransactionProcessingCodes.ID_CRYPTOGRAM_INFORMATION_DATA, consumerTagLengthValue(BERTLAlphanumeric.class, CommonDataTemplate::addAdditionalData));
    mapConsumers.put(TagTransactionProcessingCodes.ID_APPLICATION_TRANSACTION_COUNTER, consumerTagLengthValue(BERTLBinary.class, CommonDataTemplate::addAdditionalData));
    mapConsumers.put(TagTransactionProcessingCodes.ID_APPLICATION_CRYPTOGRAM, consumerTagLengthValue(BERTLBinary.class, CommonDataTemplate::addAdditionalData));
    mapConsumers.put(TagTransactionProcessingCodes.ID_ISSUER_APPLICATION_DATA, consumerTagLengthValue(BERTLBinary.class, CommonDataTemplate::addAdditionalData));
    mapConsumers.put(TagTransactionProcessingCodes.ID_UNPREDICTABLE_NUMBER, consumerTagLengthValue(BERTLBinary.class, CommonDataTemplate::addAdditionalData));
  }

  public CommonDataTemplateDecoder(final byte[] source) {
    super(BERUtils.valueOf(source));
  }

  @Override
  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected CommonDataTemplate decode() throws PresentedModeException {

    final Set<BERTag> tags = new HashSet<>();

    final CommonDataTemplate result = new CommonDataTemplate();

    while (iterator.hasNext()) {
      final byte[] value = iterator.next();

      final BERTag tag = new BERTag(BERUtils.valueOfTag(value));

      if (tags.contains(tag)) {
        throw new DuplicateTagException("CommonDataTemplate", tag.toString(), Hex.encodeHexString(value, false));
      }

      tags.add(tag);

      final Entry<Class<?>, BiConsumer<CommonDataTemplate, ?>> entry = mapConsumers.getOrDefault(tag, defaultEntry);

      final Class<?> clazz = entry.getKey();

      final BiConsumer consumer = entry.getValue();

      consumer.accept(result, DecoderCpm.decode(value, clazz));
    }

    return result;
  }

}
