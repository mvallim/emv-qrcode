package com.emv.qrcode.validators;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.emv.qrcode.decoder.cpm.DecoderCpm;
import com.emv.qrcode.model.cpm.ConsumerPresentedMode;

import br.com.fluentvalidator.context.ValidationResult;

// @formatter:off
public class ConsumerPresentedModeValidateTest {

  @Test
  public void testSuccessValidate() {
    final String encoded = "hQVDUFYwMWETTwegAAAAVVVVUAhQcm9kdWN0MWETTwegAAAAZmZmUAhQcm9k"
        + "dWN0MmJJWggSNFZ4kBI0WF8gDkNBUkRIT0xERVIvRU1WXy0IcnVlc2RlZW5kIZ8QBwY"
        + "BCgMAAACfJghYT9OF+iNLzJ82AgABnzcEbVjvEw==";

    final ConsumerPresentedMode consumerPresentedMode = DecoderCpm.decode(encoded, ConsumerPresentedMode.class);

    final ValidationResult validationResult = ConsumerPresentedModeValidate.validate(consumerPresentedMode);

    assertTrue(validationResult.isValid());
  }

  @Test
  public void testFailValidate() throws IOException {
    final String encoded = "hQVDUFYwMWEiTwegAAAAVVVVUAhQcm9kdWN0MV8gDE1hc3RlciBDbGFz"
      + "c2ETTwegAAAAZmZmUAhQcm9kdWN0MmJJWggSNFZ4kBI0WF8gDkNBUkRIT0xERVIvRU1WXy0IcnV"
      + "lc2RlZW5kIZ8QBwYBCgMAAACfJghYT9OF+iNLzJ82AgABnzcEbVjvEw==";

    final ConsumerPresentedMode consumerPresentedMode = DecoderCpm.decode(encoded, ConsumerPresentedMode.class);

    final ValidationResult validationResult = ConsumerPresentedModeValidate.validate(consumerPresentedMode);

    assertFalse(validationResult.isValid());
    assertThat(validationResult.getErrors(), hasSize(1));
    assertThat(validationResult.getErrors(), hasItem(hasProperty("code", equalTo("5F20"))));
    assertThat(validationResult.getErrors(), hasItem(hasProperty("message", equalTo("Duplicate definition tag on CommonDataTemplate"))));
    assertThat(validationResult.getErrors(), hasItem(hasProperty("attemptedValue", equalTo("CARDHOLDER/EMV"))));
  }

}
// @formatter:on
