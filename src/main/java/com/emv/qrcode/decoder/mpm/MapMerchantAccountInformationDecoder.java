package com.emv.qrcode.decoder.mpm;

import com.emv.qrcode.decoder.Decoder;
import com.emv.qrcode.mpm.model.MapMerchantAccountInformation;
import com.emv.qrcode.mpm.model.MerchantAccountInformation;

// @formatter:off
public final class MapMerchantAccountInformationDecoder extends Decoder<MapMerchantAccountInformation> {

  public MapMerchantAccountInformationDecoder(final String source) {
    super(source);
  }

  @Override
  protected MapMerchantAccountInformation decode() {
    final MapMerchantAccountInformation result = new MapMerchantAccountInformation();

    forEachRemaining(value -> {
      final MerchantAccountInformation merchantAccountInformation = Decoder.decode(value, MerchantAccountInformation.class);
      result.put(merchantAccountInformation.getTag(), merchantAccountInformation);
    });

    return result;
  }

}

// @formatter:on
