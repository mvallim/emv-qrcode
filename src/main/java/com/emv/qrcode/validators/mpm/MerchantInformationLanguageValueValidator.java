package com.emv.qrcode.validators.mpm;

import static br.com.fluentvalidator.function.FunctionBuilder.of;
import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import static br.com.fluentvalidator.predicate.StringPredicate.isNumeric;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEquals;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSize;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSizeLessThanOrEqual;

import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.model.mpm.MerchantInformationLanguageValue;
import com.emv.qrcode.model.mpm.constants.MerchantInformationLanguageFieldCodes;

import br.com.fluentvalidator.AbstractValidator;

// @formatter:off
class MerchantInformationLanguageValueValidator extends AbstractValidator<MerchantInformationLanguageValue> {

  @Override
  public void rules() {

    /**
     *
     */
    ruleFor(MerchantInformationLanguageValue::getLanguagePreference)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .withMessage("LanguagePreference tag is mandatory")
        .withAttempedValue(of(MerchantInformationLanguageValue::getLanguagePreference).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .withMessage("LanguagePreference tag must be size equal two")
        .withAttempedValue(of(MerchantInformationLanguageValue::getLanguagePreference).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .withMessage("LanguagePreference tag must be number")
        .withAttempedValue(of(MerchantInformationLanguageValue::getLanguagePreference).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, MerchantInformationLanguageFieldCodes.ID_LANGUAGE_PREFERENCE))
        .withMessage(String.format("LanguagePreference tag must be '%s'", MerchantInformationLanguageFieldCodes.ID_LANGUAGE_PREFERENCE))
        .withAttempedValue(of(MerchantInformationLanguageValue::getLanguagePreference).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("LanguagePreference value is mandatory")
        .withAttempedValue(of(MerchantInformationLanguageValue::getLanguagePreference).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSize(TagLengthString::getValue, 2))
        .withMessage("LanguagePreference value must be equal size two")
        .withAttempedValue(of(MerchantInformationLanguageValue::getLanguagePreference).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor(MerchantInformationLanguageValue::getMerchantName)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .withMessage("MerchantName tag is mandatory")
        .withAttempedValue(of(MerchantInformationLanguageValue::getMerchantName).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .withMessage("MerchantName tag must be size equal two")
        .withAttempedValue(of(MerchantInformationLanguageValue::getMerchantName).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .withMessage("MerchantName tag must be number")
        .withAttempedValue(of(MerchantInformationLanguageValue::getMerchantName).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, MerchantInformationLanguageFieldCodes.ID_MERCHANT_NAME))
        .withMessage(String.format("MerchantName tag must be '%s'", MerchantInformationLanguageFieldCodes.ID_MERCHANT_NAME))
        .withAttempedValue(of(MerchantInformationLanguageValue::getMerchantName).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("MerchantName value is mandatory")
        .withAttempedValue(of(MerchantInformationLanguageValue::getMerchantName).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 25))
        .withMessage("MerchantName value must be equal size twenty-five")
        .withAttempedValue(of(MerchantInformationLanguageValue::getMerchantName).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor(MerchantInformationLanguageValue::getMerchantCity)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .when(not(nullValue()))
        .withMessage("MerchantCity tag is mandatory")
        .withAttempedValue(of(MerchantInformationLanguageValue::getMerchantCity).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .when(not(nullValue()))
        .withMessage("MerchantCity tag must be size equal two")
        .withAttempedValue(of(MerchantInformationLanguageValue::getMerchantCity).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .when(not(nullValue()))
        .withMessage("MerchantCity tag must be number")
        .withAttempedValue(of(MerchantInformationLanguageValue::getMerchantCity).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, MerchantInformationLanguageFieldCodes.ID_MERCHANT_CITY))
        .when(not(nullValue()))
        .withMessage(String.format("MerchantCity tag must be '%s'", MerchantInformationLanguageFieldCodes.ID_MERCHANT_CITY))
        .withAttempedValue(of(MerchantInformationLanguageValue::getMerchantCity).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .when(not(nullValue()))
        .withMessage("MerchantCity value is mandatory")
        .withAttempedValue(of(MerchantInformationLanguageValue::getMerchantCity).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 25))
        .when(not(nullValue()))
        .withMessage("MerchantCity value must be equal size twenty-five")
        .withAttempedValue(of(MerchantInformationLanguageValue::getMerchantCity).andThen(TagLengthString::getValue))
        .critical();

  }

}
// @formatter:on
