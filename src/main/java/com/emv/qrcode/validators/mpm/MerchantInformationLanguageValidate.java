package com.emv.qrcode.validators.mpm;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;

import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.model.mpm.MerchantInformationLanguageValue;

import br.com.fluentvalidator.AbstractValidator;

// @formatter:off
public class MerchantInformationLanguageValidate extends AbstractValidator<MerchantInformationLanguageValue> {

  @Override
  public void rules() {

    ruleFor(MerchantInformationLanguageValue::getLanguagePreference)
      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("LanguagePreference is mandatory");

    ruleFor(MerchantInformationLanguageValue::getMerchantName)
      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("MerchantName is mandatory");

  }

}
// @formatter:on
