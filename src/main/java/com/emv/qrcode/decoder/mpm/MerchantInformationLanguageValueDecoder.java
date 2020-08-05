package com.emv.qrcode.decoder.mpm;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;

import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.decoder.DecodeIterator;
import com.emv.qrcode.decoder.Decoder;
import com.emv.qrcode.model.mpm.MerchantInformationLanguageValue;
import com.emv.qrcode.model.mpm.constants.MerchantInformationLanguageFieldCodes;

// @formatter:off
public final class MerchantInformationLanguageValueDecoder extends Decoder<MerchantInformationLanguageValue> {

  private static final Map<String, Entry<Class<?>, BiConsumer<MerchantInformationLanguageValue, ?>>> mapConsumers = new HashMap<>();

  static {
    mapConsumers.put(MerchantInformationLanguageFieldCodes.ID_LANGUAGE_PREFERENCE, consumerTagLengthValue(TagLengthString.class, MerchantInformationLanguageValue::setLanguagePreference));
    mapConsumers.put(MerchantInformationLanguageFieldCodes.ID_MERCHANT_NAME, consumerTagLengthValue(TagLengthString.class, MerchantInformationLanguageValue::setMerchantName));
    mapConsumers.put(MerchantInformationLanguageFieldCodes.ID_MERCHANT_CITY, consumerTagLengthValue(TagLengthString.class, MerchantInformationLanguageValue::setMerchantCity));
    mapConsumers.put(MerchantInformationLanguageFieldCodes.ID_RFU_FOR_EMVCO, consumerTagLengthValue(TagLengthString.class, MerchantInformationLanguageValue::addRFUforEMVCo));
  }

  public MerchantInformationLanguageValueDecoder(final String source) {
    super(source);
  }

  @Override
  @SuppressWarnings({ "unchecked", "rawtypes" })
  protected MerchantInformationLanguageValue decode() {
    final MerchantInformationLanguageValue result = new MerchantInformationLanguageValue();

    iterator.forEachRemaining(value -> {
      final String tag = derivateId(value.substring(0, DecodeIterator.ID_WORD_COUNT));

      final Entry<Class<?>, BiConsumer<MerchantInformationLanguageValue, ?>> entry = mapConsumers.get(tag);

      final Class<?> clazz = entry.getKey();

      final BiConsumer consumer = entry.getValue();

      consumer.accept(result, Decoder.decode(value, clazz));
    });

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
