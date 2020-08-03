package com.emv.qrcode.decoder.mpm;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;

import com.emv.qrcode.core.model.ListTagLengthString;
import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.decoder.Decoder;
import com.emv.qrcode.mpm.constants.MerchantPresentModeCodes;
import com.emv.qrcode.mpm.model.AdditionalDataField;
import com.emv.qrcode.mpm.model.MapMerchantAccountInformation;
import com.emv.qrcode.mpm.model.MapUnreserved;
import com.emv.qrcode.mpm.model.MerchantInformationLanguage;
import com.emv.qrcode.mpm.model.MerchantPresentMode;

// @formatter:off
public final class MerchantPresentModeDecoder extends Decoder<MerchantPresentMode> {

  private static final Map<String, Entry<Class<?>, BiConsumer<MerchantPresentMode, ?>>> mapConsumers = new HashMap<>();

  static {
    mapConsumers.put(MerchantPresentModeCodes.ID_PAYLOAD_FORMAT_INDICATOR, consumerTagLengthValue(TagLengthString.class, MerchantPresentMode::setPayloadFormatIndicator));
    mapConsumers.put(MerchantPresentModeCodes.ID_POINT_OF_INITIATION_METHOD, consumerTagLengthValue(TagLengthString.class, MerchantPresentMode::setPointOfInitiationMethod));
    mapConsumers.put(MerchantPresentModeCodes.ID_MERCHANT_CATEGORY_CODE, consumerTagLengthValue(TagLengthString.class, MerchantPresentMode::setMerchantCategoryCode));
    mapConsumers.put(MerchantPresentModeCodes.ID_TRANSACTION_CURRENCY, consumerTagLengthValue(TagLengthString.class, MerchantPresentMode::setTransactionCurrency));
    mapConsumers.put(MerchantPresentModeCodes.ID_TRANSACTION_AMOUNT, consumerTagLengthValue(TagLengthString.class, MerchantPresentMode::setTransactionAmount));
    mapConsumers.put(MerchantPresentModeCodes.ID_TIP_OR_CONVENIENCE_INDICATOR, consumerTagLengthValue(TagLengthString.class, MerchantPresentMode::setTipOrConvenienceIndicator));
    mapConsumers.put(MerchantPresentModeCodes.ID_VALUE_OF_CONVENIENCE_FEE_FIXED, consumerTagLengthValue(TagLengthString.class, MerchantPresentMode::setValueOfConvenienceFeeFixed));
    mapConsumers.put(MerchantPresentModeCodes.ID_VALUE_OF_CONVENIENCE_FEE_PERCENTAGE, consumerTagLengthValue(TagLengthString.class, MerchantPresentMode::setValueOfConvenienceFeePercentage));
    mapConsumers.put(MerchantPresentModeCodes.ID_COUNTRY_CODE, consumerTagLengthValue(TagLengthString.class, MerchantPresentMode::setCountryCode));
    mapConsumers.put(MerchantPresentModeCodes.ID_MERCHANT_NAME, consumerTagLengthValue(TagLengthString.class, MerchantPresentMode::setMerchantName));
    mapConsumers.put(MerchantPresentModeCodes.ID_MERCHANT_CITY, consumerTagLengthValue(TagLengthString.class, MerchantPresentMode::setMerchantCity));
    mapConsumers.put(MerchantPresentModeCodes.ID_POSTAL_CODE, consumerTagLengthValue(TagLengthString.class, MerchantPresentMode::setPostalCode));
    mapConsumers.put(MerchantPresentModeCodes.ID_CRC, consumerTagLengthValue(TagLengthString.class, MerchantPresentMode::setCRC));
    mapConsumers.put(MerchantPresentModeCodes.ID_ADDITIONAL_DATA_FIELD_TEMPLATE, consumerTagLengthValue(AdditionalDataField.class, MerchantPresentMode::setAdditionalDataField));
    mapConsumers.put(MerchantPresentModeCodes.ID_MERCHANT_INFORMATION_LANGUAGE_TEMPLATE, consumerTagLengthValue(MerchantInformationLanguage.class, MerchantPresentMode::setMerchantInformationLanguage));
    mapConsumers.put(MerchantPresentModeCodes.ID_MERCHANT_ACCOUNT_INFORMATION, consumerTagLengthValue(MapMerchantAccountInformation.class, MerchantPresentMode::setMerchantAccountInformation));
    mapConsumers.put(MerchantPresentModeCodes.ID_RFU_FOR_EMVCO, consumerTagLengthValue(ListTagLengthString.class, MerchantPresentMode::setRFUforEMVCo));
    mapConsumers.put(MerchantPresentModeCodes.ID_UNRESERVED_TEMPLATES, consumerTagLengthValue(MapUnreserved.class, MerchantPresentMode::setUnreserveds));
  }

  public MerchantPresentModeDecoder(final String source) {
    super(source);
  }

  @Override
  @SuppressWarnings({ "unchecked", "rawtypes" })
  protected MerchantPresentMode decode() {
    final MerchantPresentMode result = new MerchantPresentMode();

    forEachRemaining(value -> {
      final String tag = derivateId(value.substring(0, Decoder.ID_WORD_COUNT));

      final Entry<Class<?>, BiConsumer<MerchantPresentMode, ?>> entry = mapConsumers.get(tag);

      final Class<?> clazz = entry.getKey();

      final BiConsumer consumer = entry.getValue();

      consumer.accept(result, Decoder.decode(value, clazz));
    });

    return result;
  }

  private String derivateId(final String id) {

    if (betweenAccountInformationRange(id)) {
      return MerchantPresentModeCodes.ID_MERCHANT_ACCOUNT_INFORMATION;
    }

    if (betweenRFUForEMVCORange(id)) {
      return MerchantPresentModeCodes.ID_RFU_FOR_EMVCO;
    }

    if (betweenUnreservedTemplatesRange(id)) {
      return MerchantPresentModeCodes.ID_UNRESERVED_TEMPLATES;
    }

    return id;
  }

  private boolean betweenAccountInformationRange(final String value) {
    return value.compareTo(MerchantPresentModeCodes.ID_MERCHANT_ACCOUNT_INFORMATION_RANGE_START) >= 0
        && value.compareTo(MerchantPresentModeCodes.ID_MERCHANT_ACCOUNT_INFORMATION_RANGE_END) <= 0;
  }

  private boolean betweenRFUForEMVCORange(final String value) {
    return value.compareTo(MerchantPresentModeCodes.ID_RFU_FOR_EMVCO_RANGE_START) >= 0
        && value.compareTo(MerchantPresentModeCodes.ID_RFU_FOR_EMVCO_RANGE_END) <= 0;
  }

  private boolean betweenUnreservedTemplatesRange(final String value) {
    return value.compareTo(MerchantPresentModeCodes.ID_UNRESERVED_TEMPLATES_RANGE_START) >= 0
        && value.compareTo(MerchantPresentModeCodes.ID_UNRESERVED_TEMPLATES_RANGE_END) <= 0;
  }

}
// @formatter:on
