package com.emv.qrcode.validators.cpm;

import static br.com.fluentvalidator.predicate.CollectionPredicate.empty;
import static br.com.fluentvalidator.predicate.CollectionPredicate.hasSizeBetweenInclusive;
import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

import com.emv.qrcode.model.cpm.ConsumerPresentedMode;

import br.com.fluentvalidator.AbstractValidator;

// @formatter:off
public class ConsumerPresentedModeValidator extends AbstractValidator<ConsumerPresentedMode> {

  @Override
  public void rules() {

    failFastRule();

    ruleFor("PayloadFormatIndicator", ConsumerPresentedMode::getPayloadFormatIndicator)
      .must(not(nullValue()))
        .withMessage("PayloadFormatIndicator must be present")
      .whenever(not(nullValue()))
        .withValidator(new PayloadFormatIndicatorValidator());

    ruleFor("ApplicationTemplate", ConsumerPresentedMode::getApplicationTemplates)
      .must(not(empty()))
        .withMessage("ApplicationTemplate must be present")
      .must(hasSizeBetweenInclusive(1, 2))
        .when(not(empty()))
        .withMessage("ApplicationTemplate list size must be between one and two");

    ruleForEach(ConsumerPresentedMode::getApplicationTemplates)
      .whenever(not(empty()))
        .withValidator(new ApplicationTemplateValidator());

    ruleForEach(ConsumerPresentedMode::getCommonDataTemplates)
      .whenever(not(empty()))
        .withValidator(new CommonDataTemplateValidator());
  }

}

// @formatter:on
