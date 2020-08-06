package com.emv.qrcode.validators.mpm;

import static br.com.fluentvalidator.function.FunctionBuilder.of;
import static br.com.fluentvalidator.predicate.ComparablePredicate.betweenInclusive;
import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSizeBetween;

import com.emv.qrcode.model.mpm.MerchantAccountInformation;
import com.emv.qrcode.model.mpm.MerchantAccountInformationTemplate;

import br.com.fluentvalidator.AbstractValidator;

// @formatter:off
class MerchantAccountInformationTemplateValidator extends AbstractValidator<MerchantAccountInformationTemplate> {

  private final String tagStart;
  private final String tagEnd;
  private final Integer maxSizeValue;

  public MerchantAccountInformationTemplateValidator(final String tagStart, final String tagEnd, final Integer maxSizeValue) {
    this.tagStart = tagStart;
    this.tagEnd = tagEnd;
    this.maxSizeValue = maxSizeValue;
  }

  @Override
  public void rules() {

    ruleFor("MerchantAccountInformationTemplate", MerchantAccountInformationTemplate::getTag)
     .must(betweenInclusive(tagStart, tagEnd))
       .critical();

    ruleFor("MerchantAccountInformationTemplate", of(MerchantAccountInformationTemplate::getValue).andThen(MerchantAccountInformation::toString))
     .must(stringSizeBetween(1, maxSizeValue))
       .critical();

    ruleFor("MerchantAccountInformationTemplate", MerchantAccountInformationTemplate::getValue)
      .whenever(not(nullValue()))
        .withValidator(new MerchantAccountInformationValidator());

  }

}
// @formatter:on
