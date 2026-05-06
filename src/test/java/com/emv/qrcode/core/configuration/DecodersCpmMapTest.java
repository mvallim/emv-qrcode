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

import com.emv.qrcode.core.model.cpm.BERTLAlphanumeric;
import com.emv.qrcode.core.model.cpm.BERTLBinary;
import com.emv.qrcode.core.model.cpm.BERTLCompressedNumeric;
import com.emv.qrcode.core.model.cpm.BERTLNumeric;
import com.emv.qrcode.decoder.cpm.ApplicationSpecificTransparentTemplateDecoder;
import com.emv.qrcode.decoder.cpm.ApplicationTemplateDecoder;
import com.emv.qrcode.decoder.cpm.BERTLAlphanumericDecoder;
import com.emv.qrcode.decoder.cpm.BERTLBinaryDecoder;
import com.emv.qrcode.decoder.cpm.BERTLCompressedNumericDecoder;
import com.emv.qrcode.decoder.cpm.BERTLNumericDecoder;
import com.emv.qrcode.decoder.cpm.CommonDataTemplateDecoder;
import com.emv.qrcode.decoder.cpm.CommonDataTransparentTemplateDecoder;
import com.emv.qrcode.decoder.cpm.ConsumerPresentedModeDecoder;
import com.emv.qrcode.decoder.cpm.DecoderCpm;
import com.emv.qrcode.decoder.cpm.PayloadFormatIndicatorDecoder;
import com.emv.qrcode.model.cpm.ApplicationSpecificTransparentTemplate;
import com.emv.qrcode.model.cpm.ApplicationTemplate;
import com.emv.qrcode.model.cpm.CommonDataTemplate;
import com.emv.qrcode.model.cpm.CommonDataTransparentTemplate;
import com.emv.qrcode.model.cpm.ConsumerPresentedMode;
import com.emv.qrcode.model.cpm.PayloadFormatIndicator;

class DecodersCpmMapTest {

  @Test
  void testGetConfigurationReturnsUnmodifiableMap() {
    final Map<Class<?>, Class<? extends DecoderCpm<?>>> config = DecodersCpmMap.getConfiguration();
    assertThat(config, is(notNullValue()));

    catchThrowableOfType(UnsupportedOperationException.class, () -> config.put(BERTLBinary.class, BERTLAlphanumericDecoder.class));
  }

  @Test
  void testGetConfigurationContainsExpectedInitialEntries() {
    final Map<Class<?>, Class<? extends DecoderCpm<?>>> config = DecodersCpmMap.getConfiguration();

    assertThat(config.size(), greaterThanOrEqualTo(10));
    assertThat(config.get(BERTLBinary.class), equalTo(BERTLBinaryDecoder.class));
    assertThat(config.get(BERTLAlphanumeric.class), equalTo(BERTLAlphanumericDecoder.class));
    assertThat(config.get(BERTLCompressedNumeric.class), equalTo(BERTLCompressedNumericDecoder.class));
    assertThat(config.get(BERTLNumeric.class), equalTo(BERTLNumericDecoder.class));
    assertThat(config.get(ApplicationSpecificTransparentTemplate.class), equalTo(ApplicationSpecificTransparentTemplateDecoder.class));
    assertThat(config.get(ApplicationTemplate.class), equalTo(ApplicationTemplateDecoder.class));
    assertThat(config.get(CommonDataTemplate.class), equalTo(CommonDataTemplateDecoder.class));
    assertThat(config.get(CommonDataTransparentTemplate.class), equalTo(CommonDataTransparentTemplateDecoder.class));
    assertThat(config.get(ConsumerPresentedMode.class), equalTo(ConsumerPresentedModeDecoder.class));
    assertThat(config.get(PayloadFormatIndicator.class), equalTo(PayloadFormatIndicatorDecoder.class));
  }

  @Test
  void testGetDecoderReturnsCorrectDecoderForExistingTagClass() {
    assertThat(DecodersCpmMap.getDecoder(BERTLBinary.class), equalTo(BERTLBinaryDecoder.class));
    assertThat(DecodersCpmMap.getDecoder(ConsumerPresentedMode.class), equalTo(ConsumerPresentedModeDecoder.class));
  }

  @Test
  void testGetDecoderReturnsNullForNonExistentTagClass() {
    assertThat(DecodersCpmMap.getDecoder(Number.class), is(nullValue()));
  }

  @Test
  void testReplaceDecoderUpdatesExistingEntry() {
    final Class<?> tagClass = BERTLBinary.class;
    final Class<? extends DecoderCpm<?>> originalDecoder = DecodersCpmMap.getDecoder(tagClass);
    final Class<? extends DecoderCpm<?>> newDecoder = BERTLAlphanumericDecoder.class;

    DecodersCpmMap.replaceDecoder(tagClass, newDecoder);

    assertThat(DecodersCpmMap.getDecoder(tagClass), equalTo(newDecoder));

    // Revert
    DecodersCpmMap.replaceDecoder(tagClass, originalDecoder);
  }

  @Test
  void testReplaceDecoderDoesNothingForNonExistentTagClass() {
    final Class<?> tagClass = String.class;
    DecodersCpmMap.replaceDecoder(tagClass, BERTLBinaryDecoder.class);
    assertThat(DecodersCpmMap.getDecoder(tagClass), is(nullValue()));
  }

  @Test
  void testPutDecoderAddsNewEntry() {
    final Class<?> tagClass = String.class;
    final Class<? extends DecoderCpm<?>> decoderClass = BERTLBinaryDecoder.class;

    DecodersCpmMap.putDecoder(tagClass, decoderClass);

    assertThat(DecodersCpmMap.getDecoder(tagClass), equalTo(decoderClass));
  }

  @Test
  void testPutDecoderReplacesExistingEntry() {
    final Class<?> tagClass = BERTLBinary.class;
    final Class<? extends DecoderCpm<?>> originalDecoder = DecodersCpmMap.getDecoder(tagClass);
    final Class<? extends DecoderCpm<?>> newDecoder = BERTLAlphanumericDecoder.class;

    DecodersCpmMap.putDecoder(tagClass, newDecoder);

    assertThat(DecodersCpmMap.getDecoder(tagClass), equalTo(newDecoder));

    // Revert
    DecodersCpmMap.putDecoder(tagClass, originalDecoder);
  }
}
