package com.emv.qrcode.validators.mpm;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

import com.emv.qrcode.model.mpm.MerchantInformationLanguage;

import br.com.fluentvalidator.AbstractValidator;

// @formatter:off
class MerchantInformationLanguageValidator extends AbstractValidator<MerchantInformationLanguage> {

  @Override
  public void rules() {

    /**
     *
     */
    ruleFor(MerchantInformationLanguage::getValue)
      .whenever(not(nullValue()))
        .withValidator(new MerchantInformationLanguageValueValidator());

  }

}
// @formatter:on
