package com.emv.qrcode.validators.mpm;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

import com.emv.qrcode.model.mpm.MerchantInformationLanguageTemplate;

import br.com.fluentvalidator.AbstractValidator;

// @formatter:off
class MerchantInformationLanguageTemplateValidator extends AbstractValidator<MerchantInformationLanguageTemplate> {

  @Override
  public void rules() {

    /**
     *
     */
    ruleFor(MerchantInformationLanguageTemplate::getValue)
      .whenever(not(nullValue()))
        .withValidator(new MerchantInformationLanguageValidator());

  }

}
// @formatter:on
