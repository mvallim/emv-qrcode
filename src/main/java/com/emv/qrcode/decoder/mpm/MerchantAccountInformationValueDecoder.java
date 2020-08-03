package com.emv.qrcode.decoder.mpm;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;

import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.decoder.Decoder;
import com.emv.qrcode.mpm.constants.MerchantAccountInformationFieldCodes;
import com.emv.qrcode.mpm.model.MerchantAccountInformationValue;

// @formatter:off
public final class MerchantAccountInformationValueDecoder extends Decoder<MerchantAccountInformationValue> {

  private static final Map<String, Entry<Class<?>, BiConsumer<MerchantAccountInformationValue, ?>>> mapConsumers = new HashMap<>();

  static {
    mapConsumers.put(MerchantAccountInformationFieldCodes.ID_GLOBALLY_UNIQUE_IDENTIFIER, consumerTagLengthValue(TagLengthString.class, MerchantAccountInformationValue::setGloballyUniqueIdentifier));
    mapConsumers.put(MerchantAccountInformationFieldCodes.ID_PAYMENT_NETWORK_SPECIFIC, consumerTagLengthValue(TagLengthString.class, MerchantAccountInformationValue::addPaymentNetworkSpecific));
  }

  public MerchantAccountInformationValueDecoder(final String source) {
    super(source);
  }

  @Override
  @SuppressWarnings({ "unchecked", "rawtypes" })
  protected MerchantAccountInformationValue decode() {
    final MerchantAccountInformationValue result = new MerchantAccountInformationValue();

    forEachRemaining(value -> {
      final String tag = derivateId(value.substring(0, Decoder.ID_WORD_COUNT));

      final Entry<Class<?>, BiConsumer<MerchantAccountInformationValue, ?>> entry = mapConsumers.get(tag);

      final Class<?> clazz = entry.getKey();

      final BiConsumer consumer = entry.getValue();

      consumer.accept(result, Decoder.decode(value, clazz));
    });

    return result;
  }

  private String derivateId(final String id) {

    if (betweenPaymentNetworkSpecificRange(id)) {
      return MerchantAccountInformationFieldCodes.ID_PAYMENT_NETWORK_SPECIFIC;
    }

    return id;
  }

  private boolean betweenPaymentNetworkSpecificRange(final String value) {
    return value.compareTo(MerchantAccountInformationFieldCodes.ID_PAYMENT_NETWORK_SPECIFIC_START) >= 0
        && value.compareTo(MerchantAccountInformationFieldCodes.ID_PAYMENT_NETWORK_SPECIFIC_END) <= 0;
  }

}
// @formatter:on
