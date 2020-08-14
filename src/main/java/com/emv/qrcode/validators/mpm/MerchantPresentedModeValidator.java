package com.emv.qrcode.validators.mpm;

import static br.com.fluentvalidator.function.FunctionBuilder.of;
import static br.com.fluentvalidator.predicate.ComparablePredicate.betweenInclusive;
import static br.com.fluentvalidator.predicate.ComparablePredicate.equalTo;
import static br.com.fluentvalidator.predicate.ComparablePredicate.greaterThan;
import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import static br.com.fluentvalidator.predicate.StringPredicate.isNumeric;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEquals;
import static br.com.fluentvalidator.predicate.StringPredicate.stringMatches;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSize;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSizeLessThanOrEqual;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.model.mpm.AdditionalDataField;
import com.emv.qrcode.model.mpm.AdditionalDataFieldTemplate;
import com.emv.qrcode.model.mpm.MerchantInformationLanguage;
import com.emv.qrcode.model.mpm.MerchantInformationLanguageTemplate;
import com.emv.qrcode.model.mpm.MerchantPresentedMode;
import com.emv.qrcode.model.mpm.constants.MerchantPresentedModeCodes;

import br.com.fluentvalidator.AbstractValidator;

// @formatter:off
public class MerchantPresentedModeValidator extends AbstractValidator<MerchantPresentedMode> {

  private static final String REGEX_NUMBER = "^((\\d*)(\\.)?(\\d{0,2})?)$";

  private static final String REGEX_PERCENTAGE = "^(\\d{0,2}(\\.)?(\\.\\d{0,2})?|100(\\.)?(\\.00?)?)$";

  @Override
  public void rules() {

    failFastRule();

    /**
     *
     */
    ruleFor("PayloadFormatIndicator", MerchantPresentedMode::getPayloadFormatIndicator)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .withMessage("PayloadFormatIndicator tag is mandatory")
        .withAttempedValue(of(MerchantPresentedMode::getPayloadFormatIndicator).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .withMessage("PayloadFormatIndicator tag must be size equal two")
        .withAttempedValue(of(MerchantPresentedMode::getPayloadFormatIndicator).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .withMessage("PayloadFormatIndicator tag must be number")
        .withAttempedValue(of(MerchantPresentedMode::getPayloadFormatIndicator).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, MerchantPresentedModeCodes.ID_PAYLOAD_FORMAT_INDICATOR))
        .withMessage(String.format("PayloadFormatIndicator tag must be '%s'", MerchantPresentedModeCodes.ID_PAYLOAD_FORMAT_INDICATOR))
        .withAttempedValue(of(MerchantPresentedMode::getPayloadFormatIndicator).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("PayloadFormatIndicator value is mandatory")
        .withAttempedValue(of(MerchantPresentedMode::getPayloadFormatIndicator).andThen(TagLengthString::getValue))
        .critical()

      .must(isNumeric(TagLengthString::getValue))
        .withMessage("PayloadFormatIndicator value must be number")
        .withAttempedValue(of(MerchantPresentedMode::getPayloadFormatIndicator).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSize(TagLengthString::getValue, 2))
        .withMessage("PayloadFormatIndicator value must be size equal two")
        .withAttempedValue(of(MerchantPresentedMode::getPayloadFormatIndicator).andThen(TagLengthString::getValue))
        .critical()

      .must(equalTo(TagLengthString::getValue, "01"))
        .withMessage("PayloadFormatIndicator value must be '01'")
        .withAttempedValue(of(MerchantPresentedMode::getPayloadFormatIndicator).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor("PointOfInitiationMethod", MerchantPresentedMode::getPointOfInitiationMethod)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .when(not(nullValue()))
        .withMessage("PointOfInitiationMethod tag is mandatory")
        .withAttempedValue(of(MerchantPresentedMode::getPointOfInitiationMethod).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .when(not(nullValue()))
        .withMessage("PointOfInitiationMethod tag must be size equal two")
        .withAttempedValue(of(MerchantPresentedMode::getPointOfInitiationMethod).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .when(not(nullValue()))
        .withMessage("PointOfInitiationMethod tag must be number")
        .withAttempedValue(of(MerchantPresentedMode::getPointOfInitiationMethod).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, MerchantPresentedModeCodes.ID_POINT_OF_INITIATION_METHOD))
        .when(not(nullValue()))
        .withMessage(String.format("PointOfInitiationMethod tag must be '%s'", MerchantPresentedModeCodes.ID_POINT_OF_INITIATION_METHOD))
        .withAttempedValue(of(MerchantPresentedMode::getPointOfInitiationMethod).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getValue))
        .when(not(nullValue()))
        .withMessage("PointOfInitiationMethod value should be numeric")
        .withAttempedValue(of(MerchantPresentedMode::getPointOfInitiationMethod).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSize(TagLengthString::getValue, 2))
        .when(not(nullValue()))
        .withMessage("PointOfInitiationMethod value must be size equal two")
        .withAttempedValue(of(MerchantPresentedMode::getPointOfInitiationMethod).andThen(TagLengthString::getValue))
        .critical()

      .must(equalTo(TagLengthString::getValue, "11").or(equalTo(TagLengthString::getValue, "12")))
        .when(not(nullValue()))
        .withMessage("PointOfInitiationMethod value should be '11' or '12'")
        .withAttempedValue(of(MerchantPresentedMode::getPointOfInitiationMethod).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor("MerchantCategoryCode", MerchantPresentedMode::getMerchantCategoryCode)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .withMessage("MerchantCategoryCode tag is mandatory")
        .withAttempedValue(of(MerchantPresentedMode::getMerchantCategoryCode).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .withMessage("MerchantCategoryCode tag must be size equal two")
        .withAttempedValue(of(MerchantPresentedMode::getMerchantCategoryCode).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .withMessage("MerchantCategoryCode tag must be number")
        .withAttempedValue(of(MerchantPresentedMode::getMerchantCategoryCode).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, MerchantPresentedModeCodes.ID_MERCHANT_CATEGORY_CODE))
        .withMessage(String.format("MerchantCategoryCode tag must be '%s'", MerchantPresentedModeCodes.ID_MERCHANT_CATEGORY_CODE))
        .withAttempedValue(of(MerchantPresentedMode::getMerchantCategoryCode).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("MerchantCategoryCode value is mandatory")
        .withAttempedValue(of(MerchantPresentedMode::getMerchantCategoryCode).andThen(TagLengthString::getValue))
        .critical()

      .must(isNumeric(TagLengthString::getValue))
        .withMessage("MerchantCategoryCode value must be number")
        .withAttempedValue(of(MerchantPresentedMode::getMerchantCategoryCode).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSize(TagLengthString::getValue, 4))
        .withMessage("MerchantCategoryCode value must be size equal four")
        .withAttempedValue(of(MerchantPresentedMode::getMerchantCategoryCode).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor("TransactionCurrency", MerchantPresentedMode::getTransactionCurrency)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .withMessage("TransactionCurrency tag is mandatory")
        .withAttempedValue(of(MerchantPresentedMode::getTransactionCurrency).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .withMessage("TransactionCurrency tag must be size equal two")
        .withAttempedValue(of(MerchantPresentedMode::getTransactionCurrency).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .withMessage("TransactionCurrency tag must be number")
        .withAttempedValue(of(MerchantPresentedMode::getTransactionCurrency).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, MerchantPresentedModeCodes.ID_TRANSACTION_CURRENCY))
        .withMessage(String.format("TransactionCurrency tag must be '%s'", MerchantPresentedModeCodes.ID_TRANSACTION_CURRENCY))
        .withAttempedValue(of(MerchantPresentedMode::getTransactionCurrency).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("TransactionCurrency value is mandatory")
        .withAttempedValue(of(MerchantPresentedMode::getTransactionCurrency).andThen(TagLengthString::getValue))
        .critical()

      .must(isNumeric(TagLengthString::getValue))
        .withMessage("TransactionCurrency value must be number")
        .withAttempedValue(of(MerchantPresentedMode::getTransactionCurrency).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSize(TagLengthString::getValue, 3))
        .withMessage("TransactionCurrency value must be size equal three")
        .withAttempedValue(of(MerchantPresentedMode::getTransactionCurrency).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor("TransactionAmount", MerchantPresentedMode::getTransactionAmount)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .when(not(nullValue()))
        .withMessage("TransactionAmount tag is mandatory")
        .withAttempedValue(of(MerchantPresentedMode::getTransactionAmount).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .when(not(nullValue()))
        .withMessage("TransactionAmount tag must be size equal two")
        .withAttempedValue(of(MerchantPresentedMode::getTransactionAmount).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .when(not(nullValue()))
        .withMessage("TransactionAmount tag must be number")
        .withAttempedValue(of(MerchantPresentedMode::getTransactionAmount).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, MerchantPresentedModeCodes.ID_TRANSACTION_AMOUNT))
        .when(not(nullValue()))
        .withMessage(String.format("TransactionAmount tag must be '%s'", MerchantPresentedModeCodes.ID_TRANSACTION_AMOUNT))
        .withAttempedValue(of(MerchantPresentedMode::getTransactionAmount).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .when(not(nullValue()))
        .withMessage("TransactionAmount value is mandatory")
        .withAttempedValue(of(MerchantPresentedMode::getTransactionAmount).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 13))
        .when(not(nullValue()))
        .withMessage("TransactionAmount value must be less then or equal size thirteen")
        .withAttempedValue(of(MerchantPresentedMode::getTransactionAmount).andThen(TagLengthString::getValue))
        .critical()

      .must(stringMatches(TagLengthString::getValue, REGEX_NUMBER))
        .when(not(nullValue()))
        .withMessage("TransactionAmount value must be a valid number")
        .withAttempedValue(of(MerchantPresentedMode::getTransactionAmount).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor("TipOrConvenienceIndicator", MerchantPresentedMode::getTipOrConvenienceIndicator)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .when(not(nullValue()))
        .withMessage("TipOrConvenienceIndicator tag is mandatory")
        .withAttempedValue(of(MerchantPresentedMode::getTipOrConvenienceIndicator).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .when(not(nullValue()))
        .withMessage("TipOrConvenienceIndicator tag must be size equal two")
        .withAttempedValue(of(MerchantPresentedMode::getTipOrConvenienceIndicator).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .when(not(nullValue()))
        .withMessage("TipOrConvenienceIndicator tag must be number")
        .withAttempedValue(of(MerchantPresentedMode::getTipOrConvenienceIndicator).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, MerchantPresentedModeCodes.ID_TIP_OR_CONVENIENCE_INDICATOR))
        .when(not(nullValue()))
        .withMessage(String.format("TipOrConvenienceIndicator tag must be '%s'", MerchantPresentedModeCodes.ID_TIP_OR_CONVENIENCE_INDICATOR))
        .withAttempedValue(of(MerchantPresentedMode::getTipOrConvenienceIndicator).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .when(not(nullValue()))
        .withMessage("TipOrConvenienceIndicator value is mandatory")
        .withAttempedValue(of(MerchantPresentedMode::getTipOrConvenienceIndicator).andThen(TagLengthString::getValue))
        .critical()

      .must(isNumeric(TagLengthString::getValue))
        .when(not(nullValue()))
        .withMessage("TipOrConvenienceIndicator value must be number")
        .withAttempedValue(of(MerchantPresentedMode::getTipOrConvenienceIndicator).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSize(TagLengthString::getValue, 2))
        .when(not(nullValue()))
        .withMessage("TipOrConvenienceIndicator value must be size equal two")
        .withAttempedValue(of(MerchantPresentedMode::getTipOrConvenienceIndicator).andThen(TagLengthString::getValue))
        .critical()

      .must(stringEquals(TagLengthString::getValue, "01").or(stringEquals(TagLengthString::getValue, "02").or(stringEquals(TagLengthString::getValue, "03"))))
        .when(not(nullValue()))
        .withMessage("TipOrConvenienceIndicator value shall contain a value of '01', '02' or '03'")
        .withAttempedValue(of(MerchantPresentedMode::getTipOrConvenienceIndicator).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor("ValueOfConvenienceFeeFixed", MerchantPresentedMode::getValueOfConvenienceFeeFixed)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .when(not(nullValue()))
        .withMessage("ValueOfConvenienceFeeFixed tag is mandatory")
        .withAttempedValue(of(MerchantPresentedMode::getValueOfConvenienceFeeFixed).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .when(not(nullValue()))
        .withMessage("ValueOfConvenienceFeeFixed tag must be size equal two")
        .withAttempedValue(of(MerchantPresentedMode::getValueOfConvenienceFeeFixed).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .when(not(nullValue()))
        .withMessage("ValueOfConvenienceFeeFixed tag must be number")
        .withAttempedValue(of(MerchantPresentedMode::getValueOfConvenienceFeeFixed).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, MerchantPresentedModeCodes.ID_VALUE_OF_CONVENIENCE_FEE_FIXED))
        .when(not(nullValue()))
        .withMessage(String.format("ValueOfConvenienceFeeFixed tag must be '%s'", MerchantPresentedModeCodes.ID_VALUE_OF_CONVENIENCE_FEE_FIXED))
        .withAttempedValue(of(MerchantPresentedMode::getValueOfConvenienceFeeFixed).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .when(not(nullValue()))
        .withMessage("ValueOfConvenienceFeeFixed value is mandatory")
        .withAttempedValue(of(MerchantPresentedMode::getValueOfConvenienceFeeFixed).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 13))
        .when(not(nullValue()))
        .withMessage("ValueOfConvenienceFeeFixed value must be less then or equal size thirteen")
        .withAttempedValue(of(MerchantPresentedMode::getValueOfConvenienceFeeFixed).andThen(TagLengthString::getValue))
        .critical()

      .must(stringMatches(TagLengthString::getValue, REGEX_NUMBER))
        .when(not(nullValue()))
        .withMessage("ValueOfConvenienceFeeFixed value must be a valid number")
        .withAttempedValue(of(MerchantPresentedMode::getValueOfConvenienceFeeFixed).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor("ValueOfConvenienceFeePercentage", MerchantPresentedMode::getValueOfConvenienceFeePercentage)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .when(not(nullValue()))
        .withMessage("ValueOfConvenienceFeePercentage tag is mandatory")
        .withAttempedValue(of(MerchantPresentedMode::getValueOfConvenienceFeePercentage).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .when(not(nullValue()))
        .withMessage("ValueOfConvenienceFeePercentage tag must be size equal two")
        .withAttempedValue(of(MerchantPresentedMode::getValueOfConvenienceFeePercentage).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .when(not(nullValue()))
        .withMessage("ValueOfConvenienceFeePercentage tag must be number")
        .withAttempedValue(of(MerchantPresentedMode::getValueOfConvenienceFeePercentage).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, MerchantPresentedModeCodes.ID_VALUE_OF_CONVENIENCE_FEE_PERCENTAGE))
        .when(not(nullValue()))
        .withMessage(String.format("ValueOfConvenienceFeePercentage tag must be '%s'", MerchantPresentedModeCodes.ID_VALUE_OF_CONVENIENCE_FEE_PERCENTAGE))
        .withAttempedValue(of(MerchantPresentedMode::getValueOfConvenienceFeePercentage).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .when(not(nullValue()))
        .withMessage("ValueOfConvenienceFeePercentage value is mandatory")
        .withAttempedValue(of(MerchantPresentedMode::getValueOfConvenienceFeePercentage).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 5))
        .when(not(nullValue()))
        .withMessage("ValueOfConvenienceFeePercentage value must be less then or equal size five")
        .withAttempedValue(of(MerchantPresentedMode::getValueOfConvenienceFeePercentage).andThen(TagLengthString::getValue))
        .critical()

      .must(stringMatches(TagLengthString::getValue, REGEX_PERCENTAGE))
        .when(not(nullValue()))
        .withMessage("ValueOfConvenienceFeePercentage value must be a valid percentage")
        .withAttempedValue(of(MerchantPresentedMode::getValueOfConvenienceFeePercentage).andThen(TagLengthString::getValue))
        .critical()

      .must(betweenInclusive(convertToBigDecimal(TagLengthString::getValue), new BigDecimal("00.01"), new BigDecimal("99.99")))
        .when(not(nullValue()))
        .withMessage("ValueOfConvenienceFeePercentage value must be between '00.01' and '99.99'")
        .withAttempedValue(of(MerchantPresentedMode::getValueOfConvenienceFeePercentage).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor("CountryCode", MerchantPresentedMode::getCountryCode)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .withMessage("CountryCode tag is mandatory")
        .withAttempedValue(of(MerchantPresentedMode::getCountryCode).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .withMessage("CountryCode tag must be size equal two")
        .withAttempedValue(of(MerchantPresentedMode::getCountryCode).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .withMessage("CountryCode tag must be number")
        .withAttempedValue(of(MerchantPresentedMode::getCountryCode).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, MerchantPresentedModeCodes.ID_COUNTRY_CODE))
        .withMessage(String.format("CountryCode tag must be '%s'", MerchantPresentedModeCodes.ID_COUNTRY_CODE))
        .withAttempedValue(of(MerchantPresentedMode::getCountryCode).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("CountryCode value is mandatory")
        .withAttempedValue(of(MerchantPresentedMode::getCountryCode).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSize(TagLengthString::getValue, 2))
        .withMessage("CountryCode value must be size equal two")
        .withAttempedValue(of(MerchantPresentedMode::getCountryCode).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor("MerchantName", MerchantPresentedMode::getMerchantName)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .withMessage("MerchantName tag is mandatory")
        .withAttempedValue(of(MerchantPresentedMode::getMerchantName).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .withMessage("MerchantName tag must be size equal two")
        .withAttempedValue(of(MerchantPresentedMode::getMerchantName).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .withMessage("MerchantName tag must be number")
        .withAttempedValue(of(MerchantPresentedMode::getMerchantName).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, MerchantPresentedModeCodes.ID_MERCHANT_NAME))
        .withMessage(String.format("MerchantName tag must be '%s'", MerchantPresentedModeCodes.ID_MERCHANT_NAME))
        .withAttempedValue(of(MerchantPresentedMode::getMerchantName).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("MerchantName value is mandatory")
        .withAttempedValue(of(MerchantPresentedMode::getMerchantName).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 25))
        .withMessage("MerchantName value must less than or equal size equal twenty-five")
        .withAttempedValue(of(MerchantPresentedMode::getMerchantName).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor("MerchantCity", MerchantPresentedMode::getMerchantCity)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .withMessage("MerchantCity tag is mandatory")
        .withAttempedValue(of(MerchantPresentedMode::getMerchantCity).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .withMessage("MerchantCity tag must be size equal two")
        .withAttempedValue(of(MerchantPresentedMode::getMerchantCity).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .withMessage("MerchantCity tag must be number")
        .withAttempedValue(of(MerchantPresentedMode::getMerchantCity).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, MerchantPresentedModeCodes.ID_MERCHANT_CITY))
        .withMessage(String.format("MerchantCity tag must be '%s'", MerchantPresentedModeCodes.ID_MERCHANT_CITY))
        .withAttempedValue(of(MerchantPresentedMode::getMerchantCity).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("MerchantCity value is mandatory")
        .withAttempedValue(of(MerchantPresentedMode::getMerchantCity).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 15))
        .withMessage("MerchantCity value is must less than or equal size fifteen")
        .withAttempedValue(of(MerchantPresentedMode::getMerchantCity).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor("PostalCode", MerchantPresentedMode::getPostalCode)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .when(not(nullValue()))
        .withMessage("PostalCode tag is mandatory")
        .withAttempedValue(of(MerchantPresentedMode::getPostalCode).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .when(not(nullValue()))
        .withMessage("PostalCode tag must be size equal two")
        .withAttempedValue(of(MerchantPresentedMode::getPostalCode).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .when(not(nullValue()))
        .withMessage("PostalCode tag must be number")
        .withAttempedValue(of(MerchantPresentedMode::getPostalCode).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, MerchantPresentedModeCodes.ID_POSTAL_CODE))
        .when(not(nullValue()))
        .withMessage(String.format("PostalCode tag must be '%s'", MerchantPresentedModeCodes.ID_POSTAL_CODE))
        .withAttempedValue(of(MerchantPresentedMode::getPostalCode).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .when(not(nullValue()))
        .withMessage("PostalCode value is mandatory")
        .withAttempedValue(of(MerchantPresentedMode::getPostalCode).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 10))
        .when(not(nullValue()))
        .withMessage("PostalCode value must less then or equal size ten")
        .withAttempedValue(of(MerchantPresentedMode::getPostalCode).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor("AdditionalDataField", MerchantPresentedMode::getAdditionalDataField)

      .must(not(stringEmptyOrNull(AdditionalDataFieldTemplate::getTag)))
        .when(not(nullValue()))
        .withMessage("AdditionalDataField tag is mandatory")
        .withAttempedValue(of(MerchantPresentedMode::getAdditionalDataField).andThen(AdditionalDataFieldTemplate::getTag))
        .critical()

      .must(stringSize(AdditionalDataFieldTemplate::getTag, 2))
        .when(not(nullValue()))
        .withMessage("AdditionalDataField tag must be size equal two")
        .withAttempedValue(of(MerchantPresentedMode::getAdditionalDataField).andThen(AdditionalDataFieldTemplate::getTag))
        .critical()

      .must(isNumeric(AdditionalDataFieldTemplate::getTag))
        .when(not(nullValue()))
        .withMessage("AdditionalDataField tag must be number")
        .withAttempedValue(of(MerchantPresentedMode::getAdditionalDataField).andThen(AdditionalDataFieldTemplate::getTag))
        .critical()

      .must(stringEquals(AdditionalDataFieldTemplate::getTag, MerchantPresentedModeCodes.ID_ADDITIONAL_DATA_FIELD_TEMPLATE))
        .when(not(nullValue()))
        .withMessage(String.format("AdditionalDataField tag must be '%s'", MerchantPresentedModeCodes.ID_ADDITIONAL_DATA_FIELD_TEMPLATE))
        .withAttempedValue(of(MerchantPresentedMode::getAdditionalDataField).andThen(AdditionalDataFieldTemplate::getTag))
        .critical()

      .must(not(nullValue(AdditionalDataFieldTemplate::getValue)))
        .when(not(nullValue()))
        .withMessage("AdditionalDataField value must be not null")
        .withAttempedValue(of(MerchantPresentedMode::getAdditionalDataField).andThen(AdditionalDataFieldTemplate::getValue))
        .critical()

      .must(stringSizeLessThanOrEqual(of(AdditionalDataFieldTemplate::getValue).andThen(AdditionalDataField::toString), 99))
        .when(not(nullValue()))
        .withMessage("AdditionalDataField value must less then or equal size ninety-nine")
        .withAttempedValue(of(MerchantPresentedMode::getAdditionalDataField).andThen(AdditionalDataFieldTemplate::toString))
        .critical()

      .whenever(not(nullValue(AdditionalDataFieldTemplate::getValue)))
        .withValidator(new AdditionalDataFieldTemplateValidator());

    /**
     *
     */
    ruleFor("MerchantInformationLanguage", MerchantPresentedMode::getMerchantInformationLanguage)

      .must(not(stringEmptyOrNull(MerchantInformationLanguageTemplate::getTag)))
        .when(not(nullValue()))
        .withMessage("MerchantInformationLanguage tag is mandatory")
        .withAttempedValue(of(MerchantPresentedMode::getMerchantInformationLanguage).andThen(MerchantInformationLanguageTemplate::getTag))
        .critical()

      .must(stringSize(MerchantInformationLanguageTemplate::getTag, 2))
        .when(not(nullValue()))
        .withMessage("MerchantInformationLanguage tag must be size equal two")
        .withAttempedValue(of(MerchantPresentedMode::getMerchantInformationLanguage).andThen(MerchantInformationLanguageTemplate::getTag))
        .critical()

      .must(isNumeric(MerchantInformationLanguageTemplate::getTag))
        .when(not(nullValue()))
        .withMessage("MerchantInformationLanguage tag must be number")
        .withAttempedValue(of(MerchantPresentedMode::getMerchantInformationLanguage).andThen(MerchantInformationLanguageTemplate::getTag))
        .critical()

      .must(stringEquals(MerchantInformationLanguageTemplate::getTag, MerchantPresentedModeCodes.ID_MERCHANT_INFORMATION_LANGUAGE_TEMPLATE))
        .when(not(nullValue()))
        .withMessage(String.format("MerchantInformationLanguage tag must be '%s'", MerchantPresentedModeCodes.ID_MERCHANT_INFORMATION_LANGUAGE_TEMPLATE))
        .withAttempedValue(of(MerchantPresentedMode::getMerchantInformationLanguage).andThen(MerchantInformationLanguageTemplate::getTag))
        .critical()

      .must(stringSizeLessThanOrEqual(of(MerchantInformationLanguageTemplate::getValue).andThen(MerchantInformationLanguage::toString), 99))
        .when(not(nullValue()))
        .withMessage("MerchantInformationLanguage value must less then or equal size ninety-nine")
        .withAttempedValue(of(MerchantPresentedMode::getAdditionalDataField).andThen(AdditionalDataFieldTemplate::toString))
        .critical()

      .whenever(not(nullValue()))
        .withValidator(new MerchantInformationLanguageTemplateValidator());

    /**
     * Tip or Convenience Indicator
     *
     * If present, the Tip or Convenience Indicator shall contain a value of "01", "02" or "03".
     *   - A value of "01" shall be used if the mobile application should prompt the consumer to enter a tip to be paid to the merchant.
     *   - A value of "02" shall be used to indicate inclusion of the data object Value of Convenience Fee Fixed (ID "56").
     *   - A value of “03” shall be used to indicate inclusion of the data object Value of Convenience Fee Percentage (ID “57”).
     */
    ruleFor(merchantPresentMode -> merchantPresentMode)

      .must(nullValue(of(MerchantPresentedMode::getValueOfConvenienceFeeFixed)).and(nullValue(of(MerchantPresentedMode::getValueOfConvenienceFeePercentage))))
        .when(stringEquals(of(MerchantPresentedMode::getTipOrConvenienceIndicator).andThen(TagLengthString::getValue), "01"))
        .withMessage("When TipOrConvenienceIndicator is '01' ValueOfConvenienceFeeFixed and ValueOfConvenienceFeePercentage must be null")
        .withAttempedValue(merchantPresentMode -> {
          final List<String> attemptedValue = new LinkedList<>();
          Optional.ofNullable(merchantPresentMode.getValueOfConvenienceFeeFixed()).ifPresent(obj -> attemptedValue.add(obj.toString()));
          Optional.ofNullable(merchantPresentMode.getValueOfConvenienceFeePercentage()).ifPresent(obj -> attemptedValue.add(obj.toString()));
          return attemptedValue;
        })
        .withFieldName(merchantPresentMode -> {
          final List<String> fieldNames = new LinkedList<>();
          Optional.ofNullable(merchantPresentMode.getValueOfConvenienceFeeFixed()).ifPresent(obj -> fieldNames.add("ValueOfConvenienceFeeFixed"));
          Optional.ofNullable(merchantPresentMode.getValueOfConvenienceFeePercentage()).ifPresent(obj -> fieldNames.add("ValueOfConvenienceFeePercentage"));
          return String.join(",", fieldNames);
        })

      .must(nullValue(of(MerchantPresentedMode::getValueOfConvenienceFeePercentage)))
        .when(stringEquals(of(MerchantPresentedMode::getTipOrConvenienceIndicator).andThen(TagLengthString::getValue), "02"))
        .withMessage("When TipOrConvenienceIndicator is '02' ValueOfConvenienceFeePercentage must be null")
        .withAttempedValue(of(MerchantPresentedMode::getValueOfConvenienceFeePercentage))
        .withFieldName("ValueOfConvenienceFeePercentage")

      .must(nullValue(of(MerchantPresentedMode::getValueOfConvenienceFeeFixed)))
        .when(stringEquals(of(MerchantPresentedMode::getTipOrConvenienceIndicator).andThen(TagLengthString::getValue), "03"))
        .withMessage("When TipOrConvenienceIndicator is '03' ValueOfConvenienceFeeFixed must be null")
        .withAttempedValue(of(MerchantPresentedMode::getValueOfConvenienceFeeFixed))
        .withFieldName("ValueOfConvenienceFeeFixed")

      .must(not(nullValue(of(MerchantPresentedMode::getValueOfConvenienceFeeFixed))))
        .when(stringEquals(of(MerchantPresentedMode::getTipOrConvenienceIndicator).andThen(TagLengthString::getValue), "02"))
        .withMessage("When TipOrConvenienceIndicator is '02' ValueOfConvenienceFeeFixed is mandatory")
        .withAttempedValue(of(MerchantPresentedMode::getValueOfConvenienceFeeFixed))
        .withFieldName("ValueOfConvenienceFeeFixed")

      .must(not(nullValue(of(MerchantPresentedMode::getValueOfConvenienceFeePercentage))))
        .when(stringEquals(of(MerchantPresentedMode::getTipOrConvenienceIndicator).andThen(TagLengthString::getValue), "03"))
        .withMessage("When TipOrConvenienceIndicator is '03' ValueOfConvenienceFeePercentage is mandatory")
        .withAttempedValue(of(MerchantPresentedMode::getValueOfConvenienceFeePercentage))
        .withFieldName("ValueOfConvenienceFeePercentage");

    /**
     *
     */
    ruleFor("MerchantAccountInformation", MerchantPresentedMode::getMerchantAccountInformation)
      .must(greaterThan(Map::size, 0))
        .withMessage("MerchantAccountInformation size must have at least one")
        .critical()
      .must(betweenInclusive(Map::size, 1, 49))
        .when(greaterThan(Map::size, 0))
        .withMessage("MerchantAccountInformation list size must be between one and forty-nine")
        .critical();

    ruleForEach(of(MerchantPresentedMode::getMerchantAccountInformation).andThen(Map::values))
      .whenever(greaterThan(Collection::size, 0))
        .withValidator(new MerchantAccountInformationTemplateValidator("02", "51", 99));

    /**
     *
     */
    ruleFor("RFUforEMVCo", MerchantPresentedMode::getRFUforEMVCo)
      .must(betweenInclusive(Collection::size, 1, 14))
        .when(greaterThan(Collection::size, 0))
        .withMessage("RFUforEMVCo list size must be between one and fourteen")
        .critical();

    ruleForEach("RFUforEMVCo", MerchantPresentedMode::getRFUforEMVCo)
      .whenever(greaterThan(Collection::size, 0))
        .withValidator(new TagLengthStringValidator("MerchantPresentedMode.RFUforEMVCo", "65", "79", 99));

    /**
     *
     */
    ruleFor("Unreserveds", MerchantPresentedMode::getUnreserveds)
      .must(betweenInclusive(Map::size, 1, 19))
        .when(greaterThan(Map::size, 0))
        .withMessage("Unreserveds list size must be between one and nineteen");

    ruleForEach(of(MerchantPresentedMode::getUnreserveds).andThen(Map::values))
      .whenever(greaterThan(Collection::size, 0))
        .withValidator(new UnreservedTemplateValidator("80", "99", 99));

  }

  private static Function<TagLengthString, BigDecimal> convertToBigDecimal(final Function<TagLengthString, String> fnc) {
    return obj -> new BigDecimal(fnc.apply(obj));
  }

}

// @formatter:on
