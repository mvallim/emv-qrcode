package com.emv.qrcode.core.configuration;

import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.emv.qrcode.core.model.mpm.TagLengthString;
import com.emv.qrcode.decoder.mpm.AdditionalDataFieldDecoder;
import com.emv.qrcode.decoder.mpm.AdditionalDataFieldTemplateDecoder;
import com.emv.qrcode.decoder.mpm.DecoderMpm;
import com.emv.qrcode.decoder.mpm.MerchantAccountInformationReservedAdditionalDecoder;
import com.emv.qrcode.decoder.mpm.MerchantAccountInformationReservedDecoder;
import com.emv.qrcode.decoder.mpm.MerchantAccountInformationTemplateDecoder;
import com.emv.qrcode.decoder.mpm.MerchantInformationLanguageDecoder;
import com.emv.qrcode.decoder.mpm.MerchantInformationLanguageTemplateDecoder;
import com.emv.qrcode.decoder.mpm.MerchantPresentedModeDecoder;
import com.emv.qrcode.decoder.mpm.PaymentSystemSpecificDecoder;
import com.emv.qrcode.decoder.mpm.PaymentSystemSpecificTemplateDecoder;
import com.emv.qrcode.decoder.mpm.StringDecoder;
import com.emv.qrcode.decoder.mpm.TagLengthStringDecoder;
import com.emv.qrcode.decoder.mpm.UnreservedDecoder;
import com.emv.qrcode.decoder.mpm.UnreservedTemplateDecoder;
import com.emv.qrcode.model.mpm.AdditionalDataField;
import com.emv.qrcode.model.mpm.AdditionalDataFieldTemplate;
import com.emv.qrcode.model.mpm.MerchantAccountInformationReserved;
import com.emv.qrcode.model.mpm.MerchantAccountInformationReservedAdditional;
import com.emv.qrcode.model.mpm.MerchantAccountInformationTemplate;
import com.emv.qrcode.model.mpm.MerchantInformationLanguage;
import com.emv.qrcode.model.mpm.MerchantInformationLanguageTemplate;
import com.emv.qrcode.model.mpm.MerchantPresentedMode;
import com.emv.qrcode.model.mpm.PaymentSystemSpecific;
import com.emv.qrcode.model.mpm.PaymentSystemSpecificTemplate;
import com.emv.qrcode.model.mpm.Unreserved;
import com.emv.qrcode.model.mpm.UnreservedTemplate;

class DecodersMpmMapTest {

  @Test
  void testGetConfigurationReturnsUnmodifiableMap() {
    final Map<Class<?>, Class<? extends DecoderMpm<?>>> config = DecodersMpmMap.getConfiguration();
    assertThat(config, is(notNullValue()));

    catchThrowableOfType(UnsupportedOperationException.class, () -> config.put(TagLengthString.class, StringDecoder.class));
  }

  @Test
  void testGetConfigurationContainsExpectedInitialEntries() {
    final Map<Class<?>, Class<? extends DecoderMpm<?>>> config = DecodersMpmMap.getConfiguration();

    assertThat(config.size(), greaterThanOrEqualTo(14));
    assertThat(config.get(String.class), equalTo(StringDecoder.class));
    assertThat(config.get(TagLengthString.class), equalTo(TagLengthStringDecoder.class));
    assertThat(config.get(MerchantPresentedMode.class), equalTo(MerchantPresentedModeDecoder.class));
    assertThat(config.get(AdditionalDataFieldTemplate.class), equalTo(AdditionalDataFieldTemplateDecoder.class));
    assertThat(config.get(AdditionalDataField.class), equalTo(AdditionalDataFieldDecoder.class));
    assertThat(config.get(MerchantInformationLanguageTemplate.class), equalTo(MerchantInformationLanguageTemplateDecoder.class));
    assertThat(config.get(MerchantInformationLanguage.class), equalTo(MerchantInformationLanguageDecoder.class));
    assertThat(config.get(MerchantAccountInformationTemplate.class), equalTo(MerchantAccountInformationTemplateDecoder.class));
    assertThat(config.get(MerchantAccountInformationReserved.class), equalTo(MerchantAccountInformationReservedDecoder.class));
    assertThat(config.get(MerchantAccountInformationReservedAdditional.class), equalTo(MerchantAccountInformationReservedAdditionalDecoder.class));
    assertThat(config.get(UnreservedTemplate.class), equalTo(UnreservedTemplateDecoder.class));
    assertThat(config.get(Unreserved.class), equalTo(UnreservedDecoder.class));
    assertThat(config.get(PaymentSystemSpecificTemplate.class), equalTo(PaymentSystemSpecificTemplateDecoder.class));
    assertThat(config.get(PaymentSystemSpecific.class), equalTo(PaymentSystemSpecificDecoder.class));
  }

  @Test
  void testGetDecoderReturnsCorrectDecoderForExistingTagClass() {
    assertThat(DecodersMpmMap.getDecoder(String.class), equalTo(StringDecoder.class));
    assertThat(DecodersMpmMap.getDecoder(MerchantPresentedMode.class), equalTo(MerchantPresentedModeDecoder.class));
  }

  @Test
  void testGetDecoderReturnsNullForNonExistentTagClass() {
    assertThat(DecodersMpmMap.getDecoder(Number.class), is(nullValue()));
  }

  @Test
  void testReplaceDecoderUpdatesExistingEntry() {
    final Class<?> tagClass = String.class;
    final Class<? extends DecoderMpm<?>> originalDecoder = DecodersMpmMap.getDecoder(tagClass);
    final Class<? extends DecoderMpm<?>> newDecoder = TagLengthStringDecoder.class;

    DecodersMpmMap.replaceDecoder(tagClass, newDecoder);

    assertThat(DecodersMpmMap.getDecoder(tagClass), equalTo(newDecoder));

    // Revert
    DecodersMpmMap.replaceDecoder(tagClass, originalDecoder);
  }

  @Test
  void testReplaceDecoderDoesNothingForNonExistentTagClass() {
    final Class<?> tagClass = Integer.class;
    DecodersMpmMap.replaceDecoder(tagClass, StringDecoder.class);
    assertThat(DecodersMpmMap.getDecoder(tagClass), is(nullValue()));
  }

  @Test
  void testPutDecoderAddsNewEntry() {
    final Class<?> tagClass = Integer.class;
    final Class<? extends DecoderMpm<?>> decoderClass = StringDecoder.class;

    DecodersMpmMap.putDecoder(tagClass, decoderClass);

    assertThat(DecodersMpmMap.getDecoder(tagClass), equalTo(decoderClass));
  }

  @Test
  void testPutDecoderReplacesExistingEntry() {
    final Class<?> tagClass = String.class;
    final Class<? extends DecoderMpm<?>> originalDecoder = DecodersMpmMap.getDecoder(tagClass);
    final Class<? extends DecoderMpm<?>> newDecoder = TagLengthStringDecoder.class;

    DecodersMpmMap.putDecoder(tagClass, newDecoder);

    assertThat(DecodersMpmMap.getDecoder(tagClass), equalTo(newDecoder));

    // Revert
    DecodersMpmMap.putDecoder(tagClass, originalDecoder);
  }
}
