package com.emv.qrcode.validators.mpm;

import static br.com.fluentvalidator.function.FunctionBuilder.of;
import static br.com.fluentvalidator.predicate.ComparablePredicate.betweenInclusive;
import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSizeBetween;

import com.emv.qrcode.model.mpm.Unreserved;
import com.emv.qrcode.model.mpm.UnreservedTemplate;

import br.com.fluentvalidator.AbstractValidator;

// @formatter:off
class UnreservedTemplateValidator extends AbstractValidator<UnreservedTemplate> {

  private final String tagStart;
  private final String tagEnd;
  private final Integer maxSizeValue;

  public UnreservedTemplateValidator(final String tagStart, final String tagEnd, final Integer maxSizeValue) {
    this.tagStart = tagStart;
    this.tagEnd = tagEnd;
    this.maxSizeValue = maxSizeValue;
  }

  @Override
  public void rules() {

    ruleFor("UnreservedTemplate", UnreservedTemplate::getTag)
      .must(betweenInclusive(tagStart, tagEnd));

    ruleFor("UnreservedTemplate", of(UnreservedTemplate::getValue).andThen(Unreserved::toString))
      .must(stringSizeBetween(1, maxSizeValue));

    ruleFor("UnreservedTemplate", UnreservedTemplate::getValue)
      .whenever(not(nullValue()))
        .withValidator(new UnreservedValidator());

  }

}
// @formatter:on
