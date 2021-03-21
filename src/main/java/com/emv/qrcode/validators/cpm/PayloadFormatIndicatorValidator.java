package com.emv.qrcode.validators.cpm;

import static br.com.fluentvalidator.predicate.StringPredicate.stringEquals;

import com.emv.qrcode.model.cpm.PayloadFormatIndicator;

import br.com.fluentvalidator.AbstractValidator;

// @formatter:off
class PayloadFormatIndicatorValidator extends AbstractValidator<PayloadFormatIndicator> {

  @Override
  public void rules() {

    ruleFor("PayloadFormatIndicator.value", PayloadFormatIndicator::getStringValue)
      .must(stringEquals("CPV01"))
        .withMessage("PayloadFormatIndicator value must be equal 'CPV01'");

  }

}
// @formatter:on
