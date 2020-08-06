package com.emv.qrcode.validators.mpm;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

import com.emv.qrcode.model.mpm.AdditionalDataFieldTemplate;

import br.com.fluentvalidator.AbstractValidator;

// @formatter:off
class AdditionalDataFieldTemplateValidator extends AbstractValidator<AdditionalDataFieldTemplate> {

  @Override
  public void rules() {

    /**
    *
    */
   ruleFor(AdditionalDataFieldTemplate::getValue)
     .whenever(not(nullValue()))
       .withValidator(new AdditionalDataFieldValidator());

  }

}
// @formatter:on
