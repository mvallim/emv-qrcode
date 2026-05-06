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

package com.emv.qrcode.validators.cpm;

import static br.com.fluentvalidator.predicate.CollectionPredicate.empty;
import static br.com.fluentvalidator.predicate.CollectionPredicate.hasSizeBetweenInclusive;
import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

import com.emv.qrcode.model.cpm.ConsumerPresentedMode;

import br.com.fluentvalidator.AbstractValidator;

/**
 * Main validator for ConsumerPresentedMode (CPM) QR code data.
 * This validator orchestrates validation of all components within a CPM QR code,
 * including the Payload Format Indicator, Application Templates, and Common Data Template.
 *
 * <p>According to EMVCo specifications, a CPM QR code must contain at least one
 * Application Template (up to 2) and may optionally contain a Common Data Template.</p>
 *
 * @see com.emv.qrcode.model.cpm.ConsumerPresentedMode
 * @see com.emv.qrcode.validators.cpm.PayloadFormatIndicatorValidator
 * @see com.emv.qrcode.validators.cpm.ApplicationTemplateValidator
 * @see com.emv.qrcode.validators.cpm.CommonDataTemplateValidator
 */
/**
 * Main validator for ConsumerPresentedMode (CPM) QR code data.
 * This validator orchestrates validation of all components within a CPM QR code,
 * including the Payload Format Indicator, Application Templates, and Common Data Template.
 *
 * <p>According to EMVCo specifications, a CPM QR code must contain at least one
 * Application Template (up to 2) and may optionally contain a Common Data Template.</p>
 *
 * @see com.emv.qrcode.model.cpm.ConsumerPresentedMode
 * @see com.emv.qrcode.validators.cpm.PayloadFormatIndicatorValidator
 * @see com.emv.qrcode.validators.cpm.ApplicationTemplateValidator
 * @see com.emv.qrcode.validators.cpm.CommonDataTemplateValidator
 */
// @formatter:off
public class ConsumerPresentedModeValidator extends AbstractValidator<ConsumerPresentedMode> {

  /**
   * Defines validation rules for ConsumerPresentedMode.
   * Validates that PayloadFormatIndicator is present and valid,
   * ApplicationTemplates are present (1-2) and valid, and
   * CommonDataTemplate is valid if present.
   */
  @Override
  public void rules() {

    setPropertyOnContext("cpm");

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

    ruleFor(ConsumerPresentedMode::getCommonDataTemplate)
      .whenever(not(nullValue()))
        .withValidator(new CommonDataTemplateValidator());
  }

}

// @formatter:on
