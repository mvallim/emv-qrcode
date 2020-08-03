package com.emv.qrcode.decoder.mpm;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;

import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.decoder.Decoder;
import com.emv.qrcode.model.mpm.AdditionalDataFieldValue;
import com.emv.qrcode.model.mpm.constants.AdditionalDataFieldCodes;

// @formatter:off
public final class AdditionalDataFieldValueDecoder extends Decoder<AdditionalDataFieldValue> {

  private static final Map<String, Entry<Class<?>, BiConsumer<AdditionalDataFieldValue, ?>>> mapConsumers = new HashMap<>();

  static {
    mapConsumers.put(AdditionalDataFieldCodes.ID_BILL_NUMBER, consumerTagLengthValue(TagLengthString.class, AdditionalDataFieldValue::setBillNumber));
    mapConsumers.put(AdditionalDataFieldCodes.ID_MOBILE_NUMBER, consumerTagLengthValue(TagLengthString.class, AdditionalDataFieldValue::setMobileNumber));
    mapConsumers.put(AdditionalDataFieldCodes.ID_STORE_LABEL, consumerTagLengthValue(TagLengthString.class, AdditionalDataFieldValue::setStoreLabel));
    mapConsumers.put(AdditionalDataFieldCodes.ID_LOYALTY_NUMBER, consumerTagLengthValue(TagLengthString.class, AdditionalDataFieldValue::setLoyaltyNumber));
    mapConsumers.put(AdditionalDataFieldCodes.ID_REFERENCE_LABEL, consumerTagLengthValue(TagLengthString.class, AdditionalDataFieldValue::setReferenceLabel));
    mapConsumers.put(AdditionalDataFieldCodes.ID_CUSTOMER_LABEL, consumerTagLengthValue(TagLengthString.class, AdditionalDataFieldValue::setCustomerLabel));
    mapConsumers.put(AdditionalDataFieldCodes.ID_TERMINAL_LABEL, consumerTagLengthValue(TagLengthString.class, AdditionalDataFieldValue::setTerminalLabel));
    mapConsumers.put(AdditionalDataFieldCodes.ID_PURPOSE_TRANSACTION, consumerTagLengthValue(TagLengthString.class, AdditionalDataFieldValue::setPurposeTransaction));
    mapConsumers.put(AdditionalDataFieldCodes.ID_RFU_FOR_EMVCO, consumerTagLengthValue(TagLengthString.class, AdditionalDataFieldValue::addRFUforEMVCo));
    mapConsumers.put(AdditionalDataFieldCodes.ID_PAYMENT_SYSTEM_SPECIFIC, consumerTagLengthValue(TagLengthString.class, AdditionalDataFieldValue::addPaymentSystemSpecific));
    mapConsumers.put(AdditionalDataFieldCodes.ID_ADDITIONAL_CONSUMER_DATA_REQUEST, consumerTagLengthValue(TagLengthString.class, AdditionalDataFieldValue::setAdditionalConsumerDataRequest));
  }

  public AdditionalDataFieldValueDecoder(final String source) {
    super(source);
  }

  @Override
  @SuppressWarnings({ "unchecked", "rawtypes" })
  protected AdditionalDataFieldValue decode() {
    final AdditionalDataFieldValue result = new AdditionalDataFieldValue();

    forEachRemaining(value -> {
      final String tag = derivateId(value.substring(0, Decoder.ID_WORD_COUNT));

      final Entry<Class<?>, BiConsumer<AdditionalDataFieldValue, ?>> entry = mapConsumers.get(tag);

      final Class<?> clazz = entry.getKey();

      final BiConsumer consumer = entry.getValue();

      consumer.accept(result, Decoder.decode(value, clazz));
    });

    return result;
  }

  private String derivateId(final String id) {

    if (betweenPaymentSystemSpecificRange(id)) {
      return AdditionalDataFieldCodes.ID_PAYMENT_SYSTEM_SPECIFIC;
    }

    if (betweenRFUForEMVCORange(id)) {
      return AdditionalDataFieldCodes.ID_RFU_FOR_EMVCO;
    }

    return id;
  }

  private boolean betweenRFUForEMVCORange(final String value) {
    return value.compareTo(AdditionalDataFieldCodes.ID_RFU_FOR_EMVCO_RANGE_START) >= 0
        && value.compareTo(AdditionalDataFieldCodes.ID_RFU_FOR_EMVCO_RANGE_END) <= 0;
  }

  private boolean betweenPaymentSystemSpecificRange(final String value) {
    return value.compareTo(AdditionalDataFieldCodes.ID_PAYMENT_SYSTEM_SPECIFIC_TEMPLATES_RANGE_START) >= 0
        && value.compareTo(AdditionalDataFieldCodes.ID_PAYMENT_SYSTEM_SPECIFIC_TEMPLATES_RANGE_END) <= 0;
  }

}
// @formatter:on
