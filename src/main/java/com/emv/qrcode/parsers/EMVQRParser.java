package com.emv.qrcode.parsers;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import com.emv.qrcode.mpm.constants.EMVQRFieldCodes;
import com.emv.qrcode.mpm.model.EMVQR;

import lombok.Getter;

@Getter
class EMVQRParser extends Parser<EMVQR> {

  private static final Map<String, BiConsumer<EMVQR, String>> mapConsumers = new HashMap<>();

  static {
    mapConsumers.put(EMVQRFieldCodes.ID_PAYLOAD_FORMAT_INDICATOR, EMVQR::setPayloadFormatIndicator);
    mapConsumers.put(EMVQRFieldCodes.ID_POINT_OF_INITIATION_METHOD, EMVQR::setPointOfInitiationMethod);
    mapConsumers.put(EMVQRFieldCodes.ID_MERCHANT_CATEGORY_CODE, EMVQR::setMerchantCategoryCode);
    mapConsumers.put(EMVQRFieldCodes.ID_TRANSACTION_CURRENCY, EMVQR::setTransactionCurrency);
    mapConsumers.put(EMVQRFieldCodes.ID_TRANSACTION_AMOUNT, EMVQR::setTransactionAmount);
    mapConsumers.put(EMVQRFieldCodes.ID_TIP_OR_CONVENIENCE_INDICATOR, EMVQR::setTipOrConvenienceIndicator);
    mapConsumers.put(EMVQRFieldCodes.ID_VALUE_OF_CONVENIENCE_FEE_FIXED, EMVQR::setValueOfConvenienceFeeFixed);
    mapConsumers.put(EMVQRFieldCodes.ID_VALUE_OF_CONVENIENCE_FEE_PERCENTAGE, EMVQR::setValueOfConvenienceFeePercentage);
    mapConsumers.put(EMVQRFieldCodes.ID_COUNTRY_CODE, EMVQR::setCountryCode);
    mapConsumers.put(EMVQRFieldCodes.ID_MERCHANT_NAME, EMVQR::setMerchantName);
    mapConsumers.put(EMVQRFieldCodes.ID_MERCHANT_CITY, EMVQR::setMerchantCity);
    mapConsumers.put(EMVQRFieldCodes.ID_POSTAL_CODE, EMVQR::setPostalCode);
    mapConsumers.put(EMVQRFieldCodes.ID_CRC, EMVQR::setCRC);
    mapConsumers.put(EMVQRFieldCodes.ID_ADDITIONAL_DATA_FIELD_TEMPLATE, EMVQR::setAdditionalDataFieldTemplate);
    mapConsumers.put(EMVQRFieldCodes.ID_MERCHANT_INFORMATION_LANGUAGE_TEMPLATE, EMVQR::setMerchantInformationLanguageTemplate);
    mapConsumers.put(EMVQRFieldCodes.ID_MERCHANT_ACCOUNT_INFORMATION, EMVQR::addMerchantAccountInformation);
    mapConsumers.put(EMVQRFieldCodes.ID_RFU_FOR_EMVCO, EMVQR::addRFUforEMVCo);
    mapConsumers.put(EMVQRFieldCodes.ID_UNRESERVED_TEMPLATES, EMVQR::addUnreservedTemplates);
  }

  public EMVQRParser(final String source) {
    super(source);
  }

  @Override
  protected EMVQR parse() {
    final EMVQR result = new EMVQR();
    while (hasNext()) {
      mapConsumers.get(derivateId(getId())).accept(result, next());
    }
    return result;
  }

  private static String derivateId(final String id) {

    if (betweenAccountInformationRange(id)) {
      return EMVQRFieldCodes.ID_MERCHANT_ACCOUNT_INFORMATION;
    }

    if (betweenRFUForEMVCORange(id)) {
      return EMVQRFieldCodes.ID_RFU_FOR_EMVCO;
    }

    if (betweenUnreservedTemplatesRange(id)) {
      return EMVQRFieldCodes.ID_UNRESERVED_TEMPLATES;
    }

    return id;
  }

  private static boolean betweenAccountInformationRange(final String value) {
    return value.compareTo(EMVQRFieldCodes.ID_MERCHANT_ACCOUNT_INFORMATION_RANGE_START) >= 0 && value.compareTo(EMVQRFieldCodes.ID_MERCHANT_ACCOUNT_INFORMATION_RANGE_END) <= 0;
  }

  private static boolean betweenRFUForEMVCORange(final String value) {
    return value.compareTo(EMVQRFieldCodes.ID_RFU_FOR_EMVCO_RANGE_START) >= 0 && value.compareTo(EMVQRFieldCodes.ID_RFU_FOR_EMVCO_RANGE_END) <= 0;
  }

  private static boolean betweenUnreservedTemplatesRange(final String value) {
    return value.compareTo(EMVQRFieldCodes.ID_UNRESERVED_TEMPLATES_RANGE_START) >= 0 && value.compareTo(EMVQRFieldCodes.ID_UNRESERVED_TEMPLATES_RANGE_END) <= 0;
  }

}
