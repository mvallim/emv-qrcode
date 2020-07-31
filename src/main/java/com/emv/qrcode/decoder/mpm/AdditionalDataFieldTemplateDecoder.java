package com.emv.qrcode.decoder.mpm;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import com.emv.qrcode.decoder.Decoder;
import com.emv.qrcode.mpm.constants.AdditionalDataFieldCodes;
import com.emv.qrcode.mpm.model.AdditionalDataFieldTemplate;

public final class AdditionalDataFieldTemplateDecoder extends Decoder<AdditionalDataFieldTemplate> {

  private static final Map<String, BiConsumer<AdditionalDataFieldTemplate, String>> mapConsumers = new HashMap<>();

  static {
    mapConsumers.put(AdditionalDataFieldCodes.ID_BILL_NUMBER, AdditionalDataFieldTemplate::setBillNumber);
    mapConsumers.put(AdditionalDataFieldCodes.ID_MOBILE_NUMBER, AdditionalDataFieldTemplate::setMobileNumber);
    mapConsumers.put(AdditionalDataFieldCodes.ID_STORE_LABEL, AdditionalDataFieldTemplate::setStoreLabel);
    mapConsumers.put(AdditionalDataFieldCodes.ID_LOYALTY_NUMBER, AdditionalDataFieldTemplate::setLoyaltyNumber);
    mapConsumers.put(AdditionalDataFieldCodes.ID_REFERENCE_LABEL, AdditionalDataFieldTemplate::setReferenceLabel);
    mapConsumers.put(AdditionalDataFieldCodes.ID_CUSTOMER_LABEL, AdditionalDataFieldTemplate::setCustomerLabel);
    mapConsumers.put(AdditionalDataFieldCodes.ID_TERMINAL_LABEL, AdditionalDataFieldTemplate::setTerminalLabel);
    mapConsumers.put(AdditionalDataFieldCodes.ID_PURPOSE_TRANSACTION, AdditionalDataFieldTemplate::setPurposeTransaction);
    mapConsumers.put(AdditionalDataFieldCodes.ID_RFU_FOR_EMVCO, AdditionalDataFieldTemplate::addRFUforEMVCo);
    mapConsumers.put(AdditionalDataFieldCodes.ID_PAYMENT_SYSTEM_SPECIFIC, AdditionalDataFieldTemplate::addPaymentSystemSpecific);
    mapConsumers.put(AdditionalDataFieldCodes.ID_ADDITIONAL_CONSUMER_DATA_REQUEST, AdditionalDataFieldTemplate::setAdditionalConsumerDataRequest);
  }

  public AdditionalDataFieldTemplateDecoder(final String source) {
    super(source);
  }

  @Override
  protected AdditionalDataFieldTemplate decode() {
    final AdditionalDataFieldTemplate result = new AdditionalDataFieldTemplate();
    while (super.hasNext()) {
      mapConsumers.get(derivateId(super.getId())).accept(result, super.next());
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
    return value.compareTo(AdditionalDataFieldCodes.ID_RFU_FOR_EMVCO_RANGE_START) >= 0 && value.compareTo(AdditionalDataFieldCodes.ID_RFU_FOR_EMVCO_RANGE_END) <= 0;
  }

  private boolean betweenPaymentSystemSpecificRange(final String value) {
    return value.compareTo(AdditionalDataFieldCodes.ID_PAYMENT_SYSTEM_SPECIFIC_TEMPLATES_RANGE_START) >= 0 && value.compareTo(AdditionalDataFieldCodes.ID_PAYMENT_SYSTEM_SPECIFIC_TEMPLATES_RANGE_END) <= 0;
  }

}
