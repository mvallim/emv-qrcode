package com.emv.qrcode.decoder.mpm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.BiConsumer;

import com.emv.qrcode.core.exception.DuplicateTagException;
import com.emv.qrcode.core.exception.MerchantPresentedModeException;
import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.model.mpm.AdditionalDataField;
import com.emv.qrcode.model.mpm.PaymentSystemSpecificTemplate;
import com.emv.qrcode.model.mpm.constants.AdditionalDataFieldCodes;

// @formatter:off
public final class AdditionalDataFieldDecoder extends DecoderMpm<AdditionalDataField> {

  private static final Map<String, Entry<Class<?>, BiConsumer<AdditionalDataField, ?>>> mapConsumers = new HashMap<>();

  static {
    mapConsumers.put(AdditionalDataFieldCodes.ID_BILL_NUMBER, consumerTagLengthValue(String.class, AdditionalDataField::setBillNumber));
    mapConsumers.put(AdditionalDataFieldCodes.ID_MOBILE_NUMBER, consumerTagLengthValue(String.class, AdditionalDataField::setMobileNumber));
    mapConsumers.put(AdditionalDataFieldCodes.ID_STORE_LABEL, consumerTagLengthValue(String.class, AdditionalDataField::setStoreLabel));
    mapConsumers.put(AdditionalDataFieldCodes.ID_LOYALTY_NUMBER, consumerTagLengthValue(String.class, AdditionalDataField::setLoyaltyNumber));
    mapConsumers.put(AdditionalDataFieldCodes.ID_REFERENCE_LABEL, consumerTagLengthValue(String.class, AdditionalDataField::setReferenceLabel));
    mapConsumers.put(AdditionalDataFieldCodes.ID_CUSTOMER_LABEL, consumerTagLengthValue(String.class, AdditionalDataField::setCustomerLabel));
    mapConsumers.put(AdditionalDataFieldCodes.ID_TERMINAL_LABEL, consumerTagLengthValue(String.class, AdditionalDataField::setTerminalLabel));
    mapConsumers.put(AdditionalDataFieldCodes.ID_PURPOSE_TRANSACTION, consumerTagLengthValue(String.class, AdditionalDataField::setPurposeTransaction));
    mapConsumers.put(AdditionalDataFieldCodes.ID_RFU_FOR_EMVCO, consumerTagLengthValue(TagLengthString.class, AdditionalDataField::addRFUforEMVCo));
    mapConsumers.put(AdditionalDataFieldCodes.ID_PAYMENT_SYSTEM_SPECIFIC, consumerTagLengthValue(PaymentSystemSpecificTemplate.class, AdditionalDataField::addPaymentSystemSpecific));
    mapConsumers.put(AdditionalDataFieldCodes.ID_ADDITIONAL_CONSUMER_DATA_REQUEST, consumerTagLengthValue(String.class, AdditionalDataField::setAdditionalConsumerDataRequest));
  }

  public AdditionalDataFieldDecoder(final String source) {
    super(source);
  }

  @Override
  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected AdditionalDataField decode() throws MerchantPresentedModeException {

    final Set<String> tags = new HashSet<>();

    final AdditionalDataField result = new AdditionalDataField();

    while(iterator.hasNext()) {
      final String value = iterator.next();

      final String tag = value.substring(0, DecodeMpmIterator.ID_WORD_COUNT);

      final String derivateId = derivateId(tag);

      if (tags.contains(tag)) {
        throw new DuplicateTagException("AdditionalDataField", tag, value);
      }

      tags.add(tag);

      final Entry<Class<?>, BiConsumer<AdditionalDataField, ?>> entry = mapConsumers.get(derivateId);

      final Class<?> clazz = entry.getKey();

      final BiConsumer consumer = entry.getValue();

      consumer.accept(result, DecoderMpm.decode(value, clazz));
    }

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
