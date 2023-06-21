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

import static br.com.fluentvalidator.predicate.StringPredicate.stringEquals;

import com.emv.qrcode.model.cpm.PayloadFormatIndicator;

import br.com.fluentvalidator.AbstractValidator;

// @formatter:off
class PayloadFormatIndicatorValidator extends AbstractValidator<PayloadFormatIndicator> {

  @Override
  public void rules() {

    ruleFor("PayloadFormatIndicator.value", PayloadFormatIndicator::getStringValue)
      .must(stringEquals("CPV01"))
        .withMessage("PayloadFormatIndicator value must be equal 'CPV01'");

  }

}
// @formatter:on
