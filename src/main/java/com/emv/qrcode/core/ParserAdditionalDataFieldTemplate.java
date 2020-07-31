package com.emv.qrcode.core;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import com.emv.qrcode.mpm.constants.AdditionalDataFieldCodes;
import com.emv.qrcode.mpm.model.AdditionalDataFieldTemplate;

import lombok.Getter;

@Getter
public class ParserAdditionalDataFieldTemplate extends Parser {

  private static final Map<String, BiConsumer<AdditionalDataFieldTemplate, String>> mapConsumers = new HashMap<>();

  static {
    mapConsumers.put(AdditionalDataFieldCodes.ADDITIONAL_ID_BILL_NUMBER, AdditionalDataFieldTemplate::setBillNumber);
    mapConsumers.put(AdditionalDataFieldCodes.ADDITIONAL_ID_MOBILE_NUMBER, AdditionalDataFieldTemplate::setMobileNumber);
    mapConsumers.put(AdditionalDataFieldCodes.ADDITIONAL_ID_STORE_LABEL, AdditionalDataFieldTemplate::setStoreLabel);
    mapConsumers.put(AdditionalDataFieldCodes.ADDITIONAL_ID_LOYALTY_NUMBER, AdditionalDataFieldTemplate::setLoyaltyNumber);
    mapConsumers.put(AdditionalDataFieldCodes.ADDITIONAL_ID_REFERENCE_LABEL, AdditionalDataFieldTemplate::setReferenceLabel);
    mapConsumers.put(AdditionalDataFieldCodes.ADDITIONAL_ID_CUSTOMER_LABEL, AdditionalDataFieldTemplate::setCustomerLabel);
    mapConsumers.put(AdditionalDataFieldCodes.ADDITIONAL_ID_TERMINAL_LABEL, AdditionalDataFieldTemplate::setTerminalLabel);
    mapConsumers.put(AdditionalDataFieldCodes.ADDITIONAL_ID_PURPOSE_TRANSACTION, AdditionalDataFieldTemplate::setPurposeTransaction);
    mapConsumers.put(AdditionalDataFieldCodes.ADDITIONAL_ID_RFUFOR_EMVCO, AdditionalDataFieldTemplate::addRFUforEMVCo);
    mapConsumers.put(AdditionalDataFieldCodes.ADDITIONAL_ID_PAYMENT_SYSTEM_SPECIFIC, AdditionalDataFieldTemplate::addPaymentSystemSpecific);
  }

  ParserAdditionalDataFieldTemplate(final String source) {
    super(source);
  }

  public static void parse(final String source, final AdditionalDataFieldTemplate additionalDataFieldTemplate) {
    final Parser parser = new ParserAdditionalDataFieldTemplate(source);
    while (parser.hasNext()) {
      mapConsumers.get(derivateId(parser.getId())).accept(additionalDataFieldTemplate, parser.next());
    }
  }

  private static String derivateId(final String id) {

    if (betweenPaymentSystemSpecificRange(id)) {
      return AdditionalDataFieldCodes.ADDITIONAL_ID_PAYMENT_SYSTEM_SPECIFIC;
    }

    if (betweenRFUForEMVCORange(id)) {
      return AdditionalDataFieldCodes.ADDITIONAL_ID_RFUFOR_EMVCO;
    }

    return id;
  }

  private static boolean betweenRFUForEMVCORange(final String value) {
    return value.compareTo(AdditionalDataFieldCodes.ADDITIONAL_ID_RFUFOR_EMVCO_RANGE_START) >= 0 && value.compareTo(AdditionalDataFieldCodes.ADDITIONAL_ID_RFUFOR_EMVCO_RANGE_END) <= 0;
  }

  private static boolean betweenPaymentSystemSpecificRange(final String value) {
    return value.compareTo(AdditionalDataFieldCodes.ADDITIONAL_ID_PAYMENT_SYSTEM_SPECIFIC_TEMPLATES_RANGE_START) >= 0 && value.compareTo(AdditionalDataFieldCodes.ADDITIONAL_ID_PAYMENT_SYSTEM_SPECIFIC_TEMPLATES_RANGE_END) <= 0;
  }

}
