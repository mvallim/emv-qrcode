package com.emv.qrcode.validators.mpm;

import static br.com.fluentvalidator.predicate.StringPredicate.stringSizeBetween;

import com.emv.qrcode.model.mpm.MerchantAccountInformationReserved;

import br.com.fluentvalidator.AbstractValidator;

// @formatter:off
class MerchantAccountInformationReservedValidator extends AbstractValidator<MerchantAccountInformationReserved> {

  @Override
  public void rules() {

    ruleFor(MerchantAccountInformationReserved::getValue)
      .must(stringSizeBetween(1, 99))
        .withFieldName("value")
        .withMessage("MerchantAccountInformation value must be between one and ninety-nine")
        .withAttempedValue(MerchantAccountInformationReserved::getValue)
        .critical();
  }

}
// @formatter:on
