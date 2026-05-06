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
import static br.com.fluentvalidator.predicate.ObjectPredicate.instanceOf;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSizeBetween;

import java.util.function.Function;

import com.emv.qrcode.model.mpm.MerchantAccountInformation;
import com.emv.qrcode.model.mpm.MerchantAccountInformationReserved;
import com.emv.qrcode.model.mpm.MerchantAccountInformationReservedAdditional;
import com.emv.qrcode.model.mpm.MerchantAccountInformationTemplate;

import br.com.fluentvalidator.AbstractValidator;

/**
 * Validator for MerchantAccountInformationTemplate in Merchant Presented Mode.
 * Validates the template tag range and delegates validation to appropriate
 * validators based on the type of MerchantAccountInformation contained.
 *
 * <p>Templates with tags 02-25 are validated as MerchantAccountInformationReserved,
 * while tags 26-51 are validated as MerchantAccountInformationReservedAdditional.</p>
 *
 * @see com.emv.qrcode.model.mpm.MerchantAccountInformationTemplate
 * @see com.emv.qrcode.model.mpm.MerchantAccountInformationReserved
 * @see com.emv.qrcode.model.mpm.MerchantAccountInformationReservedAdditional
 */
// @formatter:off
class MerchantAccountInformationTemplateValidator extends AbstractValidator<MerchantAccountInformationTemplate> {

  private final String tagStart;
  private final String tagEnd;
  private final Integer maxSizeValue;

  /**
   * Constructs a new MerchantAccountInformationTemplateValidator.
   *
   * @param tagStart the starting tag value for the valid range
   * @param tagEnd the ending tag value for the valid range
   * @param maxSizeValue the maximum allowed size for the template value
   */
  public MerchantAccountInformationTemplateValidator(final String tagStart, final String tagEnd, final Integer maxSizeValue) {
    this.tagStart = tagStart;
    this.tagEnd = tagEnd;
    this.maxSizeValue = maxSizeValue;
  }

  /**
   * Defines validation rules for MerchantAccountInformationTemplate.
   * Validates tag range, value size, and delegates to specific validators
   * based on whether the template contains reserved or additional data.
   */
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
