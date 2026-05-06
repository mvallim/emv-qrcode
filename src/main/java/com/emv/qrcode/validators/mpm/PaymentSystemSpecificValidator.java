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
import static br.com.fluentvalidator.predicate.ComparablePredicate.greaterThan;
import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.StringPredicate.isNumeric;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEquals;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSize;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSizeLessThanOrEqual;

import java.util.Collection;
import java.util.Map;

import com.emv.qrcode.core.model.mpm.TagLengthString;
import com.emv.qrcode.model.mpm.PaymentSystemSpecific;
import com.emv.qrcode.model.mpm.constants.PaymentSystemSpecificFieldCodes;

import br.com.fluentvalidator.AbstractValidator;

/**
 * Validator for PaymentSystemSpecific data in Merchant Presented Mode.
 * Validates the Globally Unique Identifier and any payment system-specific
 * data elements within the Payment System Specific template.
 *
 * @see com.emv.qrcode.model.mpm.PaymentSystemSpecific
 * @see com.emv.qrcode.model.mpm.constants.PaymentSystemSpecificFieldCodes
 */
// @formatter:off
class PaymentSystemSpecificValidator extends AbstractValidator<PaymentSystemSpecific> {

  /**
   * Defines validation rules for PaymentSystemSpecific.
   * Validates GloballyUniqueIdentifier (mandatory, tag "00", max 32 chars)
   * and PaymentSystemSpecific data (optional, tags 01-99, max 99 chars each).
   */
  @Override
  public void rules() {

    ruleFor("GloballyUniqueIdentifier", PaymentSystemSpecific::getGloballyUniqueIdentifier)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .withMessage("GloballyUniqueIdentifier tag is mandatory")
        .withAttempedValue(of(PaymentSystemSpecific::getGloballyUniqueIdentifier).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .withMessage("GloballyUniqueIdentifier tag must be size equal two")
        .withAttempedValue(of(PaymentSystemSpecific::getGloballyUniqueIdentifier).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .withMessage("GloballyUniqueIdentifier tag must be number")
        .withAttempedValue(of(PaymentSystemSpecific::getGloballyUniqueIdentifier).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, PaymentSystemSpecificFieldCodes.ID_GLOBALLY_UNIQUE_IDENTIFIER))
        .withMessage(String.format("GloballyUniqueIdentifier tag must be '%s'", PaymentSystemSpecificFieldCodes.ID_GLOBALLY_UNIQUE_IDENTIFIER))
        .withAttempedValue(of(PaymentSystemSpecific::getGloballyUniqueIdentifier).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("GloballyUniqueIdentifier value is mandatory")
        .withAttempedValue(of(PaymentSystemSpecific::getGloballyUniqueIdentifier).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 32))
        .withMessage("GloballyUniqueIdentifier value must less then or equal size thirty-two")
        .withAttempedValue(of(PaymentSystemSpecific::getGloballyUniqueIdentifier).andThen(TagLengthString::getValue))
        .critical();

    ruleForEach(of(PaymentSystemSpecific::getPaymentSystemSpecific).andThen(Map::values))
      .whenever(greaterThan(Collection::size, 0))
        .withValidator(new TagLengthStringValidator("PaymentSystemSpecific.PaymentSystemSpecific", "01", "99", 99));
  }

}

// @formatter:on
