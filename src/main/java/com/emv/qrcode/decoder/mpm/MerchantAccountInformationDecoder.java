package com.emv.qrcode.decoder.mpm;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;

import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.decoder.DecodeMpmIterator;
import com.emv.qrcode.decoder.DecoderMpm;
import com.emv.qrcode.model.mpm.MerchantAccountInformation;
import com.emv.qrcode.model.mpm.constants.MerchantAccountInformationFieldCodes;

// @formatter:off
public final class MerchantAccountInformationDecoder extends DecoderMpm<MerchantAccountInformation> {

  private static final Map<String, Entry<Class<?>, BiConsumer<MerchantAccountInformation, ?>>> mapConsumers = new HashMap<>();

  static {
    mapConsumers.put(MerchantAccountInformationFieldCodes.ID_GLOBALLY_UNIQUE_IDENTIFIER, consumerTagLengthValue(String.class, MerchantAccountInformation::setGloballyUniqueIdentifier));
    mapConsumers.put(MerchantAccountInformationFieldCodes.ID_PAYMENT_NETWORK_SPECIFIC, consumerTagLengthValue(TagLengthString.class, MerchantAccountInformation::addPaymentNetworkSpecific));
  }

  public MerchantAccountInformationDecoder(final String source) {
    super(source);
  }

  @Override
  @SuppressWarnings({ "rawtypes", "unchecked", "java:S3740" })
  protected MerchantAccountInformation decode() {
    final MerchantAccountInformation result = new MerchantAccountInformation();

    iterator.forEachRemaining(value -> {
      final String tag = derivateId(value.substring(0, DecodeMpmIterator.ID_WORD_COUNT));

      final Entry<Class<?>, BiConsumer<MerchantAccountInformation, ?>> entry = mapConsumers.get(tag);

      final Class<?> clazz = entry.getKey();

      final BiConsumer consumer = entry.getValue();

      consumer.accept(result, DecoderMpm.decode(value, clazz));
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
