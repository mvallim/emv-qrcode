package com.emv.qrcode.core.configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.decoder.Decoder;
import com.emv.qrcode.decoder.common.TagLengthStringDecoder;
import com.emv.qrcode.decoder.mpm.AdditionalDataFieldDecoder;
import com.emv.qrcode.decoder.mpm.AdditionalDataFieldValueDecoder;
import com.emv.qrcode.decoder.mpm.MerchantAccountInformationDecoder;
import com.emv.qrcode.decoder.mpm.MerchantAccountInformationValueDecoder;
import com.emv.qrcode.decoder.mpm.MerchantInformationLanguageDecoder;
import com.emv.qrcode.decoder.mpm.MerchantInformationLanguageValueDecoder;
import com.emv.qrcode.decoder.mpm.MerchantPresentModeDecoder;
import com.emv.qrcode.decoder.mpm.UnreservedDecoder;
import com.emv.qrcode.decoder.mpm.UnreservedValueDecoder;
import com.emv.qrcode.model.mpm.AdditionalDataField;
import com.emv.qrcode.model.mpm.AdditionalDataFieldValue;
import com.emv.qrcode.model.mpm.MerchantAccountInformation;
import com.emv.qrcode.model.mpm.MerchantAccountInformationValue;
import com.emv.qrcode.model.mpm.MerchantInformationLanguage;
import com.emv.qrcode.model.mpm.MerchantInformationLanguageValue;
import com.emv.qrcode.model.mpm.MerchantPresentMode;
import com.emv.qrcode.model.mpm.Unreserved;
import com.emv.qrcode.model.mpm.UnreservedValue;

public final class DecodersMap {

  private static final Map<Class<?>, Class<? extends Decoder<?>>> MAP_DECODERS = new ConcurrentHashMap<>();

  static {
    MAP_DECODERS.put(TagLengthString.class, TagLengthStringDecoder.class);
    MAP_DECODERS.put(MerchantPresentMode.class, MerchantPresentModeDecoder.class);
    MAP_DECODERS.put(AdditionalDataField.class, AdditionalDataFieldDecoder.class);
    MAP_DECODERS.put(AdditionalDataFieldValue.class, AdditionalDataFieldValueDecoder.class);
    MAP_DECODERS.put(MerchantInformationLanguage.class, MerchantInformationLanguageDecoder.class);
    MAP_DECODERS.put(MerchantAccountInformation.class, MerchantAccountInformationDecoder.class);
    MAP_DECODERS.put(MerchantAccountInformationValue.class, MerchantAccountInformationValueDecoder.class);
    MAP_DECODERS.put(MerchantInformationLanguageValue.class, MerchantInformationLanguageValueDecoder.class);
    MAP_DECODERS.put(Unreserved.class, UnreservedDecoder.class);
    MAP_DECODERS.put(UnreservedValue.class, UnreservedValueDecoder.class);
  }

  private DecodersMap() {
    super();
  }

  public static synchronized <T> void addDecoder(final Class<T> clazz, final Class<? extends Decoder<T>> decoder) {
    MAP_DECODERS.put(clazz, decoder);
  }

  public static Class<? extends Decoder<?>> getDecoder(final Class<?> clazz) {
    return MAP_DECODERS.get(clazz);
  }

}
