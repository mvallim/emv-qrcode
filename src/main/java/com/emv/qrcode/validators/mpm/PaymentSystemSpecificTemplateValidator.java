/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.emv.qrcode.validators.mpm;

import static br.com.fluentvalidator.function.FunctionBuilder.of;
import static br.com.fluentvalidator.predicate.ComparablePredicate.betweenInclusive;
import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSizeBetween;

import com.emv.qrcode.model.mpm.PaymentSystemSpecific;
import com.emv.qrcode.model.mpm.PaymentSystemSpecificTemplate;

import br.com.fluentvalidator.AbstractValidator;

/**
 * Validator for PaymentSystemSpecificTemplate in Merchant Presented Mode.
 * Validates the template tag range and delegates validation of the template
 * value to PaymentSystemSpecificValidator.
 *
 * @see com.emv.qrcode.model.mpm.PaymentSystemSpecificTemplate
 * @see com.emv.qrcode.model.mpm.PaymentSystemSpecific
 */
// @formatter:off
class PaymentSystemSpecificTemplateValidator extends AbstractValidator<PaymentSystemSpecificTemplate> {

  private final String tagStart;
  private final String tagEnd;
  private final Integer maxSizeValue;

  /**
   * Constructs a new PaymentSystemSpecificTemplateValidator.
   *
   * @param tagStart the starting tag value for the valid range
   * @param tagEnd the ending tag value for the valid range
   * @param maxSizeValue the maximum allowed size for the template value
   */
  public PaymentSystemSpecificTemplateValidator(final String tagStart, final String tagEnd, final Integer maxSizeValue) {
    this.tagStart = tagStart;
    this.tagEnd = tagEnd;
    this.maxSizeValue = maxSizeValue;
  }

  /**
   * Defines validation rules for PaymentSystemSpecificTemplate.
   * Validates that the template tag is within the specified range and
   * the value size is within limits, then delegates to PaymentSystemSpecificValidator.
   */
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
