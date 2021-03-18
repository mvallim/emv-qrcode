package com.emv.qrcode.validators.cpm;

import static br.com.fluentvalidator.function.FunctionBuilder.of;
import static br.com.fluentvalidator.predicate.ComparablePredicate.betweenInclusive;
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

  		.must(not(nullValue()))
  			.withMessage("ApplicationDefinitionFileName must be present")
  			.withAttempedValue(BERTLV.EMPTY_BYTES)

			.must(not(stringEmptyOrNull(BERTLV::getStringValue)))
  		  .when(not(nullValue()))
  			.withMessage("ApplicationDefinitionFileName value must be present")
  			.withAttempedValue(of(BERTLV::getStringValue))

  		.must(betweenInclusive(BERTLV::getLength, 5, 16))
  		  .when(not(nullValue()))
  			.withMessage("ApplicationDefinitionFileName value size must be between five and sixteen")
  			.withAttempedValue(of(BERTLV::getStringValue));
	}
}
// @formatter:on