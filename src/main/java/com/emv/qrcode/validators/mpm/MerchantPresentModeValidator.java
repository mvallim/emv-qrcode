package com.emv.qrcode.validators.mpm;

import static br.com.fluentvalidator.predicate.ComparablePredicate.equalTo;
import static br.com.fluentvalidator.predicate.ComparablePredicate.greaterThan;
import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;

import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.model.mpm.MerchantInformationLanguage;
import com.emv.qrcode.model.mpm.MerchantPresentMode;

import br.com.fluentvalidator.AbstractValidator;

// @formatter:off
public class MerchantPresentModeValidator extends AbstractValidator<MerchantPresentMode> {

  @Override
  public void rules() {

    ruleFor(MerchantPresentMode::getPayloadFormatIndicator)
      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("PayloadFormatIndicator is mandatory");

    ruleFor(MerchantPresentMode::getMerchantAccountInformations)
      .must(map -> greaterThan(0).test(map.size()))
        .withMessage("MerchantAccountInformation is mandatory");

    ruleFor(MerchantPresentMode::getMerchantCategoryCode)
      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("MerchantCategoryCode is mandatory");

    ruleFor(MerchantPresentMode::getTransactionCurrency)
      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("TransactionCurrency is mandatory");

    ruleFor(MerchantPresentMode::getCountryCode)
      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("CountryCode is mandatory");

    ruleFor(MerchantPresentMode::getMerchantName)
      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("MerchantName is mandatory");

    ruleFor(MerchantPresentMode::getMerchantCity)
      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("MerchantCity is mandatory");

    ruleFor(MerchantPresentMode::getPointOfInitiationMethod)
      .must(equalTo(TagLengthString::getValue, "11").or(equalTo(TagLengthString::getValue, "12")))
        .when(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("PointOfInitiationMethod should be '11' or '12'");

    ruleFor(MerchantPresentMode::getMerchantInformationLanguage)
      .must(not(nullValue(MerchantInformationLanguage::getValue)))
        .withMessage("MerchantInformationLanguage is mandatory");

  }

}

// @formatter:on
