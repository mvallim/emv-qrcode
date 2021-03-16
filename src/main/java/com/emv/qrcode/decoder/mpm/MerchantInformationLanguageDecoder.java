package com.emv.qrcode.decoder.mpm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.BiConsumer;

import com.emv.qrcode.core.exception.DuplicateTagException;
import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.core.model.mpm.TagLengthString;
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
    super(source);
  }

  @Override
  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected MerchantInformationLanguage decode() throws PresentedModeException {

    final Set<String> tags = new HashSet<>();

    final MerchantInformationLanguage result = new MerchantInformationLanguage();

    while(iterator.hasNext()) {
      final String value = iterator.next();

      final String tag = value.substring(0, DecodeMpmIterator.ID_WORD_COUNT);

      final String derivateId = derivateId(tag);

      if (tags.contains(tag)) {
        throw new DuplicateTagException("MerchantInformationLanguage", tag, value);
      }

      tags.add(tag);

      final Entry<Class<?>, BiConsumer<MerchantInformationLanguage, ?>> entry = mapConsumers.get(derivateId);

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
