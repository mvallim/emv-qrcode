package com.emv.qrcode.validators;

import com.emv.qrcode.model.mpm.MerchantPresentedMode;
import com.emv.qrcode.validators.mpm.MerchantPresentedModeValidator;

import br.com.fluentvalidator.Validator;
import br.com.fluentvalidator.context.ValidationResult;

public final class MerchantPresentedModeValidate {

  private static final Validator<MerchantPresentedMode> VALIDATOR = new MerchantPresentedModeValidator();

  private MerchantPresentedModeValidate() {
    super();
  }

  public static final ValidationResult validate(final MerchantPresentedMode instance) {
    return VALIDATOR.validate(instance);
  }

}
