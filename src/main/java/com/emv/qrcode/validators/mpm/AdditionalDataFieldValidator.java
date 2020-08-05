package com.emv.qrcode.validators.mpm;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

import com.emv.qrcode.model.mpm.AdditionalDataField;

import br.com.fluentvalidator.AbstractValidator;

// @formatter:off
class AdditionalDataFieldValidator extends AbstractValidator<AdditionalDataField> {

  @Override
  public void rules() {

    /**
    *
    */
   ruleFor(AdditionalDataField::getValue)
     .whenever(not(nullValue()))
       .withValidator(new AdditionalDataFieldValueValidator());

  }

}
// @formatter:on
