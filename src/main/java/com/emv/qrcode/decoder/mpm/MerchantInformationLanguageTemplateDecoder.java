package com.emv.qrcode.decoder.mpm;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import com.emv.qrcode.decoder.Decoder;
import com.emv.qrcode.mpm.constants.MerchantInformationFieldCodes;
import com.emv.qrcode.mpm.model.MerchantInformationLanguageTemplate;

public final class MerchantInformationLanguageTemplateDecoder extends Decoder<MerchantInformationLanguageTemplate> {

  private static final Map<String, BiConsumer<MerchantInformationLanguageTemplate, String>> mapConsumers = new HashMap<>();

  static {
    mapConsumers.put(MerchantInformationFieldCodes.MERCHANT_INFORMATION_ID_LANGUAGE_PREFERENCE, MerchantInformationLanguageTemplate::setLanguagePreference);
    mapConsumers.put(MerchantInformationFieldCodes.MERCHANT_INFORMATION_ID_MERCHANT_NAME, MerchantInformationLanguageTemplate::setMerchantName);
    mapConsumers.put(MerchantInformationFieldCodes.MERCHANT_INFORMATION_ID_MERCHANT_CITY, MerchantInformationLanguageTemplate::setMerchantCity);
    mapConsumers.put(MerchantInformationFieldCodes.MERCHANT_INFORMATION_ID_RFUFOR_EMVCO, MerchantInformationLanguageTemplate::addRFUforEMVCO);
  }

  public MerchantInformationLanguageTemplateDecoder(final String source) {
    super(source);
  }

  @Override
  protected MerchantInformationLanguageTemplate decode() {
    final MerchantInformationLanguageTemplate result = new MerchantInformationLanguageTemplate();
    while (super.hasNext()) {
      mapConsumers.get(derivateId(super.getId())).accept(result, super.next());
    }
    return result;
  }

  private String derivateId(final String id) {

    if (betweenRFUForEMVCORange(id)) {
      return MerchantInformationFieldCodes.MERCHANT_INFORMATION_ID_RFUFOR_EMVCO;
    }

    return id;
  }

  private boolean betweenRFUForEMVCORange(final String value) {
    return value.compareTo(MerchantInformationFieldCodes.MERCHANT_INFORMATION_ID_RFUFOR_EMVCO_RANGE_START) >= 0 && value.compareTo(MerchantInformationFieldCodes.MERCHANT_INFORMATION_ID_RFUFOR_EMVCO_RANGE_END) <= 0;
  }

}
