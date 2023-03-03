/*
 * Copyright 2023 the original author or authors.
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

import static br.com.fluentvalidator.predicate.ComparablePredicate.betweenInclusive;
import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.StringPredicate.isNumeric;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSize;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSizeBetween;

import com.emv.qrcode.core.model.mpm.TagLengthString;

import br.com.fluentvalidator.AbstractValidator;

// @formatter:off
class TagLengthStringValidator extends AbstractValidator<TagLengthString> {

  private final String tagStart;
  private final String tagEnd;
  private final Integer maxSizeValue;
  private final String fieldName;

  public TagLengthStringValidator(final String fieldName, final String tagStart, final String tagEnd, final Integer maxSizeValue) {
    this.fieldName = fieldName;
    this.tagStart = tagStart;
    this.tagEnd = tagEnd;
    this.maxSizeValue = maxSizeValue;
  }

  @Override
  public void rules() {

    ruleFor(TagLengthString::getTag)

      .must(not(stringEmptyOrNull()))
        .withFieldName("tag")
        .withMessage(String.format("%s tag is mandatory", fieldName))
        .withAttempedValue(TagLengthString::getTag)
        .critical()

      .must(stringSize(2))
        .withFieldName("tag")
        .withMessage(String.format("%s tag must be size equal two", fieldName))
        .withAttempedValue(TagLengthString::getTag)
        .critical()

      .must(isNumeric())
        .withFieldName("tag")
        .withMessage(String.format("%s tag must be number", fieldName))
        .withAttempedValue(TagLengthString::getTag)
        .critical()

      .must(betweenInclusive(tagStart, tagEnd))
        .withFieldName("tag")
        .withMessage(String.format("%s tag must be betwwen '%s' and '%s'", fieldName, tagStart, tagEnd))
        .withAttempedValue(TagLengthString::getTag)
        .critical();

    ruleFor(TagLengthString::getValue)
      .must(stringSizeBetween(1, maxSizeValue))
        .withFieldName("value")
        .withMessage(String.format("%s value must less then or equal size %d", fieldName, maxSizeValue))
        .withAttempedValue(TagLengthString::getValue)
        .critical();

  }

}
// @formatter:on
