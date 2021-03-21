package com.emv.qrcode.validators.mpm;

import static br.com.fluentvalidator.function.FunctionBuilder.of;
import static br.com.fluentvalidator.predicate.ComparablePredicate.greaterThan;
import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import static br.com.fluentvalidator.predicate.StringPredicate.isNumeric;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEquals;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSize;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSizeLessThanOrEqual;

import java.util.Collection;
import java.util.Map;

import com.emv.qrcode.core.model.mpm.TagLengthString;
import com.emv.qrcode.model.mpm.MerchantInformationLanguage;
import com.emv.qrcode.model.mpm.constants.MerchantInformationLanguageFieldCodes;

import br.com.fluentvalidator.AbstractValidator;

// @formatter:off
class MerchantInformationLanguageValidator extends AbstractValidator<MerchantInformationLanguage> {

  @Override
  public void rules() {

    /**
     *
     */
    ruleFor(MerchantInformationLanguage::getLanguagePreference)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .withMessage("LanguagePreference tag is mandatory")
        .withAttempedValue(of(MerchantInformationLanguage::getLanguagePreference).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .withMessage("LanguagePreference tag must be size equal two")
        .withAttempedValue(of(MerchantInformationLanguage::getLanguagePreference).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .withMessage("LanguagePreference tag must be number")
        .withAttempedValue(of(MerchantInformationLanguage::getLanguagePreference).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, MerchantInformationLanguageFieldCodes.ID_LANGUAGE_PREFERENCE))
        .withMessage(String.format("LanguagePreference tag must be '%s'", MerchantInformationLanguageFieldCodes.ID_LANGUAGE_PREFERENCE))
        .withAttempedValue(of(MerchantInformationLanguage::getLanguagePreference).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("LanguagePreference value is mandatory")
        .withAttempedValue(of(MerchantInformationLanguage::getLanguagePreference).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSize(TagLengthString::getValue, 2))
        .withMessage("LanguagePreference value must be equal size two")
        .withAttempedValue(of(MerchantInformationLanguage::getLanguagePreference).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor(MerchantInformationLanguage::getMerchantName)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .withMessage("MerchantName tag is mandatory")
        .withAttempedValue(of(MerchantInformationLanguage::getMerchantName).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .withMessage("MerchantName tag must be size equal two")
        .withAttempedValue(of(MerchantInformationLanguage::getMerchantName).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .withMessage("MerchantName tag must be number")
        .withAttempedValue(of(MerchantInformationLanguage::getMerchantName).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, MerchantInformationLanguageFieldCodes.ID_MERCHANT_NAME))
        .withMessage(String.format("MerchantName tag must be '%s'", MerchantInformationLanguageFieldCodes.ID_MERCHANT_NAME))
        .withAttempedValue(of(MerchantInformationLanguage::getMerchantName).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("MerchantName value is mandatory")
        .withAttempedValue(of(MerchantInformationLanguage::getMerchantName).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 25))
        .withMessage("MerchantName value must be equal size twenty-five")
        .withAttempedValue(of(MerchantInformationLanguage::getMerchantName).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor(MerchantInformationLanguage::getMerchantCity)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .when(not(nullValue()))
        .withMessage("MerchantCity tag is mandatory")
        .withAttempedValue(of(MerchantInformationLanguage::getMerchantCity).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .when(not(nullValue()))
        .withMessage("MerchantCity tag must be size equal two")
        .withAttempedValue(of(MerchantInformationLanguage::getMerchantCity).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .when(not(nullValue()))
        .withMessage("MerchantCity tag must be number")
        .withAttempedValue(of(MerchantInformationLanguage::getMerchantCity).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, MerchantInformationLanguageFieldCodes.ID_MERCHANT_CITY))
        .when(not(nullValue()))
        .withMessage(String.format("MerchantCity tag must be '%s'", MerchantInformationLanguageFieldCodes.ID_MERCHANT_CITY))
        .withAttempedValue(of(MerchantInformationLanguage::getMerchantCity).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .when(not(nullValue()))
        .withMessage("MerchantCity value is mandatory")
        .withAttempedValue(of(MerchantInformationLanguage::getMerchantCity).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 15))
        .when(not(nullValue()))
        .withMessage("MerchantCity value must be equal size fifteen")
        .withAttempedValue(of(MerchantInformationLanguage::getMerchantCity).andThen(TagLengthString::getValue))
        .critical();

    ruleForEach(of(MerchantInformationLanguage::getRFUforEMVCo).andThen(Map::values))
      .whenever(greaterThan(Collection::size, 0))
        .withValidator(new TagLengthStringValidator("MerchantInformationLanguage.RFUforEMVCo", "03", "99", 99));

  }

}
// @formatter:on
