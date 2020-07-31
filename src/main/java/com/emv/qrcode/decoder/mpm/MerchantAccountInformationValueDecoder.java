package com.emv.qrcode.decoder.mpm;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import com.emv.qrcode.decoder.Decoder;
import com.emv.qrcode.mpm.constants.MerchantAccountInformationFieldCodes;
import com.emv.qrcode.mpm.model.MerchantAccountInformationValue;

public final class MerchantAccountInformationValueDecoder extends Decoder<MerchantAccountInformationValue> {

  private static final Map<String, BiConsumer<MerchantAccountInformationValue, String>> mapConsumers = new HashMap<>();

  static {
    mapConsumers.put(MerchantAccountInformationFieldCodes.ID_GLOBALLY_UNIQUE_IDENTIFIER, MerchantAccountInformationValue::setGloballyUniqueIdentifier);
    mapConsumers.put(MerchantAccountInformationFieldCodes.ID_PAYMENT_NETWORK_SPECIFIC, MerchantAccountInformationValue::addPaymentNetworkSpecific);
  }

  public MerchantAccountInformationValueDecoder(final String source) {
    super(source);
  }

  @Override
  protected MerchantAccountInformationValue decode() {
    final MerchantAccountInformationValue result = new MerchantAccountInformationValue();
    while (super.hasNext()) {
      mapConsumers.get(derivateId(super.getId())).accept(result, super.next());
    }
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
