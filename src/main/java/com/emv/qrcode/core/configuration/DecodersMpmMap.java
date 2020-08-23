package com.emv.qrcode.core.configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.decoder.DecoderMpm;
import com.emv.qrcode.decoder.common.StringDecoder;
import com.emv.qrcode.decoder.common.TagLengthStringDecoder;
import com.emv.qrcode.decoder.mpm.AdditionalDataFieldDecoder;
import com.emv.qrcode.decoder.mpm.AdditionalDataFieldTemplateDecoder;
import com.emv.qrcode.decoder.mpm.MerchantAccountInformationDecoder;
import com.emv.qrcode.decoder.mpm.MerchantAccountInformationTemplateDecoder;
import com.emv.qrcode.decoder.mpm.MerchantInformationLanguageDecoder;
import com.emv.qrcode.decoder.mpm.MerchantInformationLanguageTemplateDecoder;
import com.emv.qrcode.decoder.mpm.MerchantPresentedModeDecoder;
import com.emv.qrcode.decoder.mpm.PaymentSystemSpecificDecoder;
import com.emv.qrcode.decoder.mpm.PaymentSystemSpecificTemplateDecoder;
import com.emv.qrcode.decoder.mpm.UnreservedDecoder;
import com.emv.qrcode.decoder.mpm.UnreservedTemplateDecoder;
import com.emv.qrcode.model.mpm.AdditionalDataField;
import com.emv.qrcode.model.mpm.AdditionalDataFieldTemplate;
import com.emv.qrcode.model.mpm.MerchantAccountInformation;
import com.emv.qrcode.model.mpm.MerchantAccountInformationTemplate;
import com.emv.qrcode.model.mpm.MerchantInformationLanguage;
import com.emv.qrcode.model.mpm.MerchantInformationLanguageTemplate;
import com.emv.qrcode.model.mpm.MerchantPresentedMode;
import com.emv.qrcode.model.mpm.PaymentSystemSpecific;
import com.emv.qrcode.model.mpm.PaymentSystemSpecificTemplate;
import com.emv.qrcode.model.mpm.Unreserved;
import com.emv.qrcode.model.mpm.UnreservedTemplate;

public final class DecodersMpmMap {

  private static final Map<Class<?>, Class<? extends DecoderMpm<?>>> MAP_DECODERS = new ConcurrentHashMap<>();

  static {
    MAP_DECODERS.put(String.class, StringDecoder.class);
    MAP_DECODERS.put(TagLengthString.class, TagLengthStringDecoder.class);
    MAP_DECODERS.put(MerchantPresentedMode.class, MerchantPresentedModeDecoder.class);
    MAP_DECODERS.put(AdditionalDataFieldTemplate.class, AdditionalDataFieldTemplateDecoder.class);
    MAP_DECODERS.put(AdditionalDataField.class, AdditionalDataFieldDecoder.class);
    MAP_DECODERS.put(MerchantInformationLanguageTemplate.class, MerchantInformationLanguageTemplateDecoder.class);
    MAP_DECODERS.put(MerchantInformationLanguage.class, MerchantInformationLanguageDecoder.class);
    MAP_DECODERS.put(MerchantAccountInformationTemplate.class, MerchantAccountInformationTemplateDecoder.class);
    MAP_DECODERS.put(MerchantAccountInformation.class, MerchantAccountInformationDecoder.class);
    MAP_DECODERS.put(UnreservedTemplate.class, UnreservedTemplateDecoder.class);
    MAP_DECODERS.put(Unreserved.class, UnreservedDecoder.class);
    MAP_DECODERS.put(PaymentSystemSpecificTemplate.class, PaymentSystemSpecificTemplateDecoder.class);
    MAP_DECODERS.put(PaymentSystemSpecific.class, PaymentSystemSpecificDecoder.class);
  }

  private DecodersMpmMap() {
    super();
  }

  public static Class<? extends DecoderMpm<?>> getDecoder(final Class<?> clazz) {
    return MAP_DECODERS.get(clazz);
  }

}
