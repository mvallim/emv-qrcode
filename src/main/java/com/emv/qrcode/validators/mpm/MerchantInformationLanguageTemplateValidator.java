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

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

import com.emv.qrcode.model.mpm.MerchantInformationLanguageTemplate;

import br.com.fluentvalidator.AbstractValidator;

/**
 * Validator for MerchantInformationLanguageTemplate in Merchant Presented Mode.
 * Delegates validation of the template value to MerchantInformationLanguageValidator.
 *
 * @see com.emv.qrcode.model.mpm.MerchantInformationLanguageTemplate
 * @see com.emv.qrcode.model.mpm.MerchantInformationLanguage
 */
// @formatter:off
class MerchantInformationLanguageTemplateValidator extends AbstractValidator<MerchantInformationLanguageTemplate> {

  /**
   * Defines validation rules for MerchantInformationLanguageTemplate.
   * Delegates validation to MerchantInformationLanguageValidator when the value is present.
   */
  @Override
  public void rules() {
    ruleFor(MerchantInformationLanguageTemplate::getValue)
      .whenever(not(nullValue()))
        .withValidator(new MerchantInformationLanguageValidator());

  }

}
// @formatter:on
