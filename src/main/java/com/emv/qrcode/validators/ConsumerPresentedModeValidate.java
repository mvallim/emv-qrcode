package com.emv.qrcode.validators;

import com.emv.qrcode.model.cpm.ConsumerPresentedMode;
import com.emv.qrcode.validators.cpm.ConsumerPresentedModeValidator;

import br.com.fluentvalidator.Validator;
import br.com.fluentvalidator.context.ValidationResult;

public final class ConsumerPresentedModeValidate {

  private static final Validator<ConsumerPresentedMode> VALIDATOR = new ConsumerPresentedModeValidator();

  private ConsumerPresentedModeValidate() {
    super();
  }

  public static final ValidationResult validate(final ConsumerPresentedMode instance) {
    return VALIDATOR.validate(instance);
  }

}
