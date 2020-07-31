package com.emv.qrcode.parsers;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import com.emv.qrcode.mpm.constants.MerchantAccountInformationFieldCodes;
import com.emv.qrcode.mpm.model.MerchantAccountInformationValue;

import lombok.Getter;

@Getter
class MerchantAccountInformationValueParser extends Parser<MerchantAccountInformationValue> {

  private static final Map<String, BiConsumer<MerchantAccountInformationValue, String>> mapConsumers = new HashMap<>();

  static {
    mapConsumers.put(MerchantAccountInformationFieldCodes.MERCHANT_ACCOUNT_INFORMATION_ID_GLOBALLY_UNIQUE_IDENTIFIER, MerchantAccountInformationValue::setGloballyUniqueIdentifier);
    mapConsumers.put(MerchantAccountInformationFieldCodes.MERCHANT_ACCOUNT_INFORMATION_ID_PAYMENT_NETWORK_SPECIFIC, MerchantAccountInformationValue::addPaymentNetworkSpecific);
  }

  MerchantAccountInformationValueParser(final String source) {
    super(source);
  }

  @Override
  protected MerchantAccountInformationValue parse() {
    final MerchantAccountInformationValue result = new MerchantAccountInformationValue();
    while (hasNext()) {
      mapConsumers.get(derivateId(getId())).accept(result, next());
    }
    return result;
  }

  private static String derivateId(final String id) {

    if (betweenPaymentNetworkSpecificRange(id)) {
      return MerchantAccountInformationFieldCodes.MERCHANT_ACCOUNT_INFORMATION_ID_PAYMENT_NETWORK_SPECIFIC;
    }

    return id;
  }

  private static boolean betweenPaymentNetworkSpecificRange(final String value) {
    return value.compareTo(MerchantAccountInformationFieldCodes.MERCHANT_ACCOUNT_INFORMATION_ID_PAYMENT_NETWORK_SPECIFIC_START) >= 0 && value.compareTo(MerchantAccountInformationFieldCodes.MERCHANT_ACCOUNT_INFORMATION_ID_PAYMENT_NETWORK_SPECIFIC_END) <= 0;
  }

}
