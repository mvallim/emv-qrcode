package com.emv.qrcode.validators;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.emv.qrcode.decoder.cpm.DecoderCpm;
import com.emv.qrcode.model.cpm.ConsumerPresentedMode;

import br.com.fluentvalidator.context.ValidationResult;

public class ConsumerPresentedModeValidateTest {

  @Test
  public void testSuccessValidate() {
    final String encoded = "hQVDUFYwMWFWTwigAAADMwEBAlcRYmNgAQBxNnLSMSIBAAABiAFfNAEAYzOfJghWite85mtxi58nAYCfEBEHAAEDoAAAAQgwMDAwMDAwMZ82AhEiggIAAJ83BCJgSI4=";

    final ConsumerPresentedMode consumerPresentedMode = DecoderCpm.decode(encoded, ConsumerPresentedMode.class);

    final ValidationResult validationResult = ConsumerPresentedModeValidate.validate(consumerPresentedMode);

    assertTrue(validationResult.isValid());
  }

}
