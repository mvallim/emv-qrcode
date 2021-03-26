package com.emv.qrcode.validators.mpm;

import static br.com.fluentvalidator.function.FunctionBuilder.of;
import static br.com.fluentvalidator.predicate.ComparablePredicate.betweenInclusive;
import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.instanceOf;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSizeBetween;

import java.util.function.Function;

import com.emv.qrcode.model.mpm.MerchantAccountInformation;
import com.emv.qrcode.model.mpm.MerchantAccountInformationReserved;
import com.emv.qrcode.model.mpm.MerchantAccountInformationReservedAdditional;
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

    ruleFor("MerchantAccountInformationTemplate", merchantAccountInformationTemplate -> merchantAccountInformationTemplate)
      .must(betweenInclusive(MerchantAccountInformationTemplate::getTag, "02", "25"))
      .when(instanceOf(MerchantAccountInformationTemplate::getValue, MerchantAccountInformationReserved.class))
        .withMessage("MerchantAccountInformation reserved tag must be between '02' and '25'")
        .withAttempedValue(MerchantAccountInformationTemplate::getTag)
      .must(betweenInclusive(MerchantAccountInformationTemplate::getTag, "26", "51"))
      .when(instanceOf(MerchantAccountInformationTemplate::getValue, MerchantAccountInformationReservedAdditional.class))
        .withMessage("MerchantAccountInformation reserved additional tag must be between '26' and '51'")
        .withAttempedValue(MerchantAccountInformationTemplate::getTag);

    ruleFor(convert(MerchantAccountInformationReserved.class))
      .whenever(not(nullValue()))
        .withValidator(new MerchantAccountInformationReservedValidator());

    ruleFor(convert(MerchantAccountInformationReservedAdditional.class))
      .whenever(not(nullValue()))
        .withValidator(new MerchantAccountInformationReservedAdditionalValidator());

  }

  private <T extends MerchantAccountInformation> Function<MerchantAccountInformationTemplate, T> convert(final Class<T> clazz) {
    return fn -> clazz.isInstance(fn.getValue()) ? clazz.cast(fn.getValue()) : null;
  }

}
// @formatter:on
