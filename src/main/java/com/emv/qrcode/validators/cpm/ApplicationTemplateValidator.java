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

package com.emv.qrcode.validators.cpm;

import static br.com.fluentvalidator.function.FunctionBuilder.of;
import static br.com.fluentvalidator.predicate.ComparablePredicate.betweenInclusive;
import static br.com.fluentvalidator.predicate.ComparablePredicate.equalTo;
import static br.com.fluentvalidator.predicate.ComparablePredicate.lessThanOrEqual;
import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;

import com.emv.qrcode.core.model.cpm.BERTLV;
import com.emv.qrcode.model.cpm.ApplicationTemplate;

import br.com.fluentvalidator.AbstractValidator;

// @formatter:off
class ApplicationTemplateValidator extends AbstractValidator<ApplicationTemplate> {

	@Override
  public void rules() {

		ruleFor("ApplicationDefinitionFileName", ApplicationTemplate::getApplicationDefinitionFileName)
			.must(not(stringEmptyOrNull(BERTLV::getStringValue)))
  		  .when(not(nullValue()))
  			.withMessage("ApplicationDefinitionFileName value must be present")
  			.withAttempedValue(of(BERTLV::getStringValue))
  		.must(betweenInclusive(BERTLV::getLength, 5, 16))
  		  .when(not(nullValue()))
  			.withMessage("ApplicationDefinitionFileName value size must be between five and sixteen")
  			.withAttempedValue(of(BERTLV::getStringValue));

    ruleFor("ApplicationLabel", ApplicationTemplate::getApplicationLabel)
      .must(betweenInclusive(BERTLV::getLength, 1, 16))
        .when(not(nullValue()))
        .withMessage("ApplicationLabel value size must be between five and sixteen")
        .withAttempedValue(of(BERTLV::getStringValue));

    ruleFor("ApplicationPAN", ApplicationTemplate::getApplicationPAN)
      .must(lessThanOrEqual(BERTLV::getLength, 10))
        .when(not(nullValue()))
        .withMessage("ApplicationPAN value size must be less than or equal ten")
        .withAttempedValue(of(BERTLV::getStringValue));

    ruleFor("ApplicationVersionNumber", ApplicationTemplate::getApplicationVersionNumber)
      .must(equalTo(BERTLV::getLength, 2))
        .when(not(nullValue()))
        .withMessage("ApplicationVersionNumber value size must be equal two")
        .withAttempedValue(of(BERTLV::getStringValue));

    ruleFor("CardholderName", ApplicationTemplate::getCardholderName)
      .must(betweenInclusive(BERTLV::getLength, 2, 26))
        .when(not(nullValue()))
        .withMessage("CardholderName value size must be between two and twenty-six")
        .withAttempedValue(of(BERTLV::getStringValue));

    ruleFor("Last4DigitsOfPAN", ApplicationTemplate::getLast4DigitsOfPAN)
      .must(equalTo(BERTLV::getLength, 2))
        .when(not(nullValue()))
        .withMessage("Last4DigitsOfPAN value size must be equal two")
        .withAttempedValue(of(BERTLV::getStringValue));

    ruleFor("LanguagePreference", ApplicationTemplate::getLanguagePreference)
      .must(betweenInclusive(BERTLV::getLength, 2, 8))
        .when(not(nullValue()))
        .withMessage("LanguagePreference value size must be between two and eight")
        .withAttempedValue(of(BERTLV::getStringValue));

    ruleFor("Track2EquivalentData", ApplicationTemplate::getTrack2EquivalentData)
      .must(lessThanOrEqual(BERTLV::getLength, 19))
        .when(not(nullValue()))
        .withMessage("Track2EquivalentData value size must be less than or equal nineteen")
        .withAttempedValue(of(BERTLV::getStringValue));

    ruleFor("TokenRequestorID", ApplicationTemplate::getTokenRequestorID)
      .must(equalTo(BERTLV::getLength, 6))
        .when(not(nullValue()))
        .withMessage("TokenRequestorID value size must be equal six")
        .withAttempedValue(of(BERTLV::getStringValue));

    ruleFor("PaymentAccountReference", ApplicationTemplate::getPaymentAccountReference)
      .must(equalTo(BERTLV::getLength, 29))
        .when(not(nullValue()))
        .withMessage("PaymentAccountReference value size must be equal twenty-nine")
        .withAttempedValue(of(BERTLV::getStringValue));

	}
}
// @formatter:on