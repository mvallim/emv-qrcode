package com.emv.qrcode.core;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import com.emv.qrcode.mpm.constants.MerchantInformationFieldCodes;
import com.emv.qrcode.mpm.model.MerchantInformationLanguageTemplate;

import lombok.Getter;

@Getter
public class ParserMerchantInformationLanguageTemplate extends Parser {

  private static final Map<String, BiConsumer<MerchantInformationLanguageTemplate, String>> mapConsumers = new HashMap<>();

  static {
    mapConsumers.put(MerchantInformationFieldCodes.MERCHANT_INFORMATION_ID_LANGUAGE_PREFERENCE, MerchantInformationLanguageTemplate::setLanguagePreference);
    mapConsumers.put(MerchantInformationFieldCodes.MERCHANT_INFORMATION_ID_MERCHANT_NAME, MerchantInformationLanguageTemplate::setMerchantName);
    mapConsumers.put(MerchantInformationFieldCodes.MERCHANT_INFORMATION_ID_MERCHANT_CITY, MerchantInformationLanguageTemplate::setMerchantCity);
    mapConsumers.put(MerchantInformationFieldCodes.MERCHANT_INFORMATION_ID_RFUFOR_EMVCO, MerchantInformationLanguageTemplate::addRFUforEMVCO);
  }

  ParserMerchantInformationLanguageTemplate(final String source) {
    super(source);
  }

  public static void parse(final String source, final MerchantInformationLanguageTemplate merchantInformationLanguageTemplate) {
    final Parser parser = new ParserMerchantInformationLanguageTemplate(source);
    while (parser.hasNext()) {
      mapConsumers.get(derivateId(parser.getId())).accept(merchantInformationLanguageTemplate, parser.next());
    }
  }

  private static String derivateId(final String id) {

    if (betweenRFUForEMVCORange(id)) {
      return MerchantInformationFieldCodes.MERCHANT_INFORMATION_ID_RFUFOR_EMVCO;
    }

    return id;
  }

  private static boolean betweenRFUForEMVCORange(final String value) {
    return value.compareTo(MerchantInformationFieldCodes.MERCHANT_INFORMATION_ID_RFUFOR_EMVCO_RANGE_START) >= 0 && value.compareTo(MerchantInformationFieldCodes.MERCHANT_INFORMATION_ID_RFUFOR_EMVCO_RANGE_END) <= 0;
  }

}
