package com.emv.qrcode.validators.mpm;

import static br.com.fluentvalidator.function.FunctionBuilder.of;
import static br.com.fluentvalidator.predicate.ComparablePredicate.betweenInclusive;
import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSizeBetween;

import com.emv.qrcode.model.mpm.PaymentSystemSpecific;
import com.emv.qrcode.model.mpm.PaymentSystemSpecificTemplate;

import br.com.fluentvalidator.AbstractValidator;

// @formatter:off
class PaymentSystemSpecificTemplateValidator extends AbstractValidator<PaymentSystemSpecificTemplate> {

  private final String tagStart;
  private final String tagEnd;
  private final Integer maxSizeValue;

  public PaymentSystemSpecificTemplateValidator(final String tagStart, final String tagEnd, final Integer maxSizeValue) {
    this.tagStart = tagStart;
    this.tagEnd = tagEnd;
    this.maxSizeValue = maxSizeValue;
  }

  @Override
  public void rules() {

    ruleFor("PaymentSystemSpecificTemplate", PaymentSystemSpecificTemplate::getTag)
      .must(betweenInclusive(tagStart, tagEnd))
      .critical();

    ruleFor("PaymentSystemSpecificTemplate", of(PaymentSystemSpecificTemplate::getValue).andThen(PaymentSystemSpecific::toString))
      .must(stringSizeBetween(1, maxSizeValue))
      .critical();

    ruleFor(PaymentSystemSpecificTemplate::getValue)
      .whenever(not(nullValue()))
        .withValidator(new PaymentSystemSpecificValidator());

  }

}
// @formatter:on
