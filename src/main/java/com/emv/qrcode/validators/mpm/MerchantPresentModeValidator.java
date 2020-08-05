package com.emv.qrcode.validators.mpm;

import static br.com.fluentvalidator.function.FunctionBuilder.of;
import static br.com.fluentvalidator.predicate.ComparablePredicate.betweenInclusive;
import static br.com.fluentvalidator.predicate.ComparablePredicate.equalTo;
import static br.com.fluentvalidator.predicate.ComparablePredicate.greaterThan;
import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import static br.com.fluentvalidator.predicate.StringPredicate.isNumber;
import static br.com.fluentvalidator.predicate.StringPredicate.isNumeric;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEquals;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSize;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSizeLessThanOrEqual;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.model.mpm.AdditionalDataField;
import com.emv.qrcode.model.mpm.MerchantInformationLanguage;
import com.emv.qrcode.model.mpm.MerchantPresentMode;
import com.emv.qrcode.model.mpm.constants.MerchantPresentModeCodes;

import br.com.fluentvalidator.AbstractValidator;

// @formatter:off
public class MerchantPresentModeValidator extends AbstractValidator<MerchantPresentMode> {

  @Override
  public void rules() {

    /**
     *
     */
    ruleFor(MerchantPresentMode::getPayloadFormatIndicator)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .withMessage("PayloadFormatIndicator tag is mandatory")
        .withAttempedValue(of(MerchantPresentMode::getPayloadFormatIndicator).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .withMessage("PayloadFormatIndicator tag must be size equal two")
        .withAttempedValue(of(MerchantPresentMode::getPayloadFormatIndicator).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .withMessage("PayloadFormatIndicator tag must be number")
        .withAttempedValue(of(MerchantPresentMode::getPayloadFormatIndicator).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, MerchantPresentModeCodes.ID_PAYLOAD_FORMAT_INDICATOR))
        .withMessage(String.format("PayloadFormatIndicator tag must be '%s'", MerchantPresentModeCodes.ID_PAYLOAD_FORMAT_INDICATOR))
        .withAttempedValue(of(MerchantPresentMode::getPayloadFormatIndicator).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("PayloadFormatIndicator value is mandatory")
        .withAttempedValue(of(MerchantPresentMode::getPayloadFormatIndicator).andThen(TagLengthString::getValue))
        .critical()

      .must(isNumeric(TagLengthString::getValue))
        .withMessage("PayloadFormatIndicator value must be number")
        .withAttempedValue(of(MerchantPresentMode::getPayloadFormatIndicator).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSize(TagLengthString::getValue, 2))
        .withMessage("PayloadFormatIndicator value must be size equal two")
        .withAttempedValue(of(MerchantPresentMode::getPayloadFormatIndicator).andThen(TagLengthString::getValue))
        .critical()

      .must(equalTo(TagLengthString::getValue, "01"))
        .withMessage("PayloadFormatIndicator value must be '01'")
        .withAttempedValue(of(MerchantPresentMode::getPayloadFormatIndicator).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor(MerchantPresentMode::getPointOfInitiationMethod)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .when(not(nullValue()))
        .withMessage("PointOfInitiationMethod tag is mandatory")
        .withAttempedValue(of(MerchantPresentMode::getPointOfInitiationMethod).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .when(not(nullValue()))
        .withMessage("PointOfInitiationMethod tag must be size equal two")
        .withAttempedValue(of(MerchantPresentMode::getPointOfInitiationMethod).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .when(not(nullValue()))
        .withMessage("PointOfInitiationMethod tag must be number")
        .withAttempedValue(of(MerchantPresentMode::getPointOfInitiationMethod).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, MerchantPresentModeCodes.ID_POINT_OF_INITIATION_METHOD))
        .when(not(nullValue()))
        .withMessage(String.format("PointOfInitiationMethod tag must be '%s'", MerchantPresentModeCodes.ID_POINT_OF_INITIATION_METHOD))
        .withAttempedValue(of(MerchantPresentMode::getPointOfInitiationMethod).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getValue))
        .when(not(nullValue()))
        .withMessage("PointOfInitiationMethod value should be numeric")
        .withAttempedValue(of(MerchantPresentMode::getPointOfInitiationMethod).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSize(TagLengthString::getValue, 2))
        .when(not(nullValue()))
        .withMessage("PointOfInitiationMethod value must be size equal two")
        .withAttempedValue(of(MerchantPresentMode::getPointOfInitiationMethod).andThen(TagLengthString::getValue))
        .critical()

      .must(equalTo(TagLengthString::getValue, "11").or(equalTo(TagLengthString::getValue, "12")))
        .when(not(nullValue()))
        .withMessage("PointOfInitiationMethod value should be '11' or '12'")
        .withAttempedValue(of(MerchantPresentMode::getPointOfInitiationMethod).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor(MerchantPresentMode::getMerchantCategoryCode)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .withMessage("MerchantCategoryCode tag is mandatory")
        .withAttempedValue(of(MerchantPresentMode::getMerchantCategoryCode).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .withMessage("MerchantCategoryCode tag must be size equal two")
        .withAttempedValue(of(MerchantPresentMode::getMerchantCategoryCode).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .withMessage("MerchantCategoryCode tag must be number")
        .withAttempedValue(of(MerchantPresentMode::getMerchantCategoryCode).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, MerchantPresentModeCodes.ID_MERCHANT_CATEGORY_CODE))
        .withMessage(String.format("MerchantCategoryCode tag must be '%s'", MerchantPresentModeCodes.ID_MERCHANT_CATEGORY_CODE))
        .withAttempedValue(of(MerchantPresentMode::getMerchantCategoryCode).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("MerchantCategoryCode value is mandatory")
        .withAttempedValue(of(MerchantPresentMode::getMerchantCategoryCode).andThen(TagLengthString::getValue))
        .critical()

      .must(isNumeric(TagLengthString::getValue))
        .withMessage("MerchantCategoryCode value must be number")
        .withAttempedValue(of(MerchantPresentMode::getMerchantCategoryCode).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSize(TagLengthString::getValue, 4))
        .withMessage("MerchantCategoryCode value must be size equal four")
        .withAttempedValue(of(MerchantPresentMode::getMerchantCategoryCode).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor(MerchantPresentMode::getTransactionCurrency)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .withMessage("TransactionCurrency tag is mandatory")
        .withAttempedValue(of(MerchantPresentMode::getTransactionCurrency).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .withMessage("TransactionCurrency tag must be size equal two")
        .withAttempedValue(of(MerchantPresentMode::getTransactionCurrency).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .withMessage("TransactionCurrency tag must be number")
        .withAttempedValue(of(MerchantPresentMode::getTransactionCurrency).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, MerchantPresentModeCodes.ID_TRANSACTION_CURRENCY))
        .withMessage(String.format("TransactionCurrency tag must be '%s'", MerchantPresentModeCodes.ID_TRANSACTION_CURRENCY))
        .withAttempedValue(of(MerchantPresentMode::getTransactionCurrency).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("TransactionCurrency value is mandatory")
        .withAttempedValue(of(MerchantPresentMode::getTransactionCurrency).andThen(TagLengthString::getValue))
        .critical()

      .must(isNumeric(TagLengthString::getValue))
        .withMessage("TransactionCurrency value must be number")
        .withAttempedValue(of(MerchantPresentMode::getTransactionCurrency).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSize(TagLengthString::getValue, 3))
        .withMessage("TransactionCurrency value must be size equal three")
        .withAttempedValue(of(MerchantPresentMode::getTransactionCurrency).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor(MerchantPresentMode::getTransactionAmount)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .when(not(nullValue()))
        .withMessage("TransactionAmount tag is mandatory")
        .withAttempedValue(of(MerchantPresentMode::getTransactionAmount).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .when(not(nullValue()))
        .withMessage("TransactionAmount tag must be size equal two")
        .withAttempedValue(of(MerchantPresentMode::getTransactionAmount).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .when(not(nullValue()))
        .withMessage("TransactionAmount tag must be number")
        .withAttempedValue(of(MerchantPresentMode::getTransactionAmount).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, MerchantPresentModeCodes.ID_TRANSACTION_AMOUNT))
        .when(not(nullValue()))
        .withMessage(String.format("TransactionAmount tag must be '%s'", MerchantPresentModeCodes.ID_TRANSACTION_AMOUNT))
        .withAttempedValue(of(MerchantPresentMode::getTransactionAmount).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .when(not(nullValue()))
        .withMessage("TransactionAmount value is mandatory")
        .withAttempedValue(of(MerchantPresentMode::getTransactionAmount).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 13))
        .when(not(nullValue()))
        .withMessage("TransactionAmount value must be less then or equal size thirteen")
        .withAttempedValue(of(MerchantPresentMode::getTransactionAmount).andThen(TagLengthString::getValue))
        .critical()

      .must(greaterThan(convertToBigDecimal(TagLengthString::getValue), BigDecimal.ZERO))
        .when(not(nullValue()))
        .withMessage("TransactionAmount value must be number")
        .withAttempedValue(of(MerchantPresentMode::getTransactionAmount).andThen(TagLengthString::getValue))
        .critical()

      .must(isNumber(TagLengthString::getValue))
        .when(not(nullValue()))
        .withMessage("TransactionAmount value must be number")
        .withAttempedValue(of(MerchantPresentMode::getTransactionAmount).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor(MerchantPresentMode::getTipOrConvenienceIndicator)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .when(not(nullValue()))
        .withMessage("TipOrConvenienceIndicator tag is mandatory")
        .withAttempedValue(of(MerchantPresentMode::getTipOrConvenienceIndicator).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .when(not(nullValue()))
        .withMessage("TipOrConvenienceIndicator tag must be size equal two")
        .withAttempedValue(of(MerchantPresentMode::getTipOrConvenienceIndicator).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .when(not(nullValue()))
        .withMessage("TipOrConvenienceIndicator tag must be number")
        .withAttempedValue(of(MerchantPresentMode::getTipOrConvenienceIndicator).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, MerchantPresentModeCodes.ID_TIP_OR_CONVENIENCE_INDICATOR))
        .when(not(nullValue()))
        .withMessage(String.format("TipOrConvenienceIndicator tag must be '%s'", MerchantPresentModeCodes.ID_TIP_OR_CONVENIENCE_INDICATOR))
        .withAttempedValue(of(MerchantPresentMode::getTipOrConvenienceIndicator).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .when(not(nullValue()))
        .withMessage("TipOrConvenienceIndicator value is mandatory")
        .withAttempedValue(of(MerchantPresentMode::getTipOrConvenienceIndicator).andThen(TagLengthString::getValue))
        .critical()

      .must(isNumeric(TagLengthString::getValue))
        .when(not(nullValue()))
        .withMessage("TipOrConvenienceIndicator value must be number")
        .withAttempedValue(of(MerchantPresentMode::getTipOrConvenienceIndicator).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSize(TagLengthString::getValue, 2))
        .when(not(nullValue()))
        .withMessage("TipOrConvenienceIndicator value must be size equal two")
        .withAttempedValue(of(MerchantPresentMode::getTipOrConvenienceIndicator).andThen(TagLengthString::getValue))
        .critical()

      .must(stringEquals(TagLengthString::getValue, "01").or(stringEquals(TagLengthString::getValue, "02").or(stringEquals(TagLengthString::getValue, "03"))))
        .when(not(nullValue()))
        .withMessage("TipOrConvenienceIndicator value shall contain a value of '01', '02' or '03'")
        .withAttempedValue(of(MerchantPresentMode::getTipOrConvenienceIndicator).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor(MerchantPresentMode::getValueOfConvenienceFeeFixed)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .when(not(nullValue()))
        .withMessage("ValueOfConvenienceFeeFixed tag is mandatory")
        .withAttempedValue(of(MerchantPresentMode::getValueOfConvenienceFeeFixed).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .when(not(nullValue()))
        .withMessage("ValueOfConvenienceFeeFixed tag must be size equal two")
        .withAttempedValue(of(MerchantPresentMode::getValueOfConvenienceFeeFixed).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .when(not(nullValue()))
        .withMessage("ValueOfConvenienceFeeFixed tag must be number")
        .withAttempedValue(of(MerchantPresentMode::getValueOfConvenienceFeeFixed).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, MerchantPresentModeCodes.ID_VALUE_OF_CONVENIENCE_FEE_FIXED))
        .when(not(nullValue()))
        .withMessage(String.format("ValueOfConvenienceFeeFixed tag must be '%s'", MerchantPresentModeCodes.ID_VALUE_OF_CONVENIENCE_FEE_FIXED))
        .withAttempedValue(of(MerchantPresentMode::getValueOfConvenienceFeeFixed).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .when(not(nullValue()))
        .withMessage("ValueOfConvenienceFeeFixed value is mandatory")
        .withAttempedValue(of(MerchantPresentMode::getValueOfConvenienceFeeFixed).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 13))
        .when(not(nullValue()))
        .withMessage("ValueOfConvenienceFeeFixed value must be less then or equal size thirteen")
        .withAttempedValue(of(MerchantPresentMode::getValueOfConvenienceFeeFixed).andThen(TagLengthString::getValue))
        .critical()

      .must(isNumber(TagLengthString::getValue))
        .when(not(nullValue()))
        .withMessage("ValueOfConvenienceFeeFixed value must be a valid number")
        .withAttempedValue(of(MerchantPresentMode::getValueOfConvenienceFeeFixed).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor(MerchantPresentMode::getValueOfConvenienceFeePercentage)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .when(not(nullValue()))
        .withMessage("ValueOfConvenienceFeePercentage tag is mandatory")
        .withAttempedValue(of(MerchantPresentMode::getValueOfConvenienceFeePercentage).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .when(not(nullValue()))
        .withMessage("ValueOfConvenienceFeePercentage tag must be size equal two")
        .withAttempedValue(of(MerchantPresentMode::getValueOfConvenienceFeePercentage).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .when(not(nullValue()))
        .withMessage("ValueOfConvenienceFeePercentage tag must be number")
        .withAttempedValue(of(MerchantPresentMode::getValueOfConvenienceFeePercentage).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, MerchantPresentModeCodes.ID_VALUE_OF_CONVENIENCE_FEE_PERCENTAGE))
        .when(not(nullValue()))
        .withMessage(String.format("ValueOfConvenienceFeePercentage tag must be '%s'", MerchantPresentModeCodes.ID_VALUE_OF_CONVENIENCE_FEE_PERCENTAGE))
        .withAttempedValue(of(MerchantPresentMode::getValueOfConvenienceFeePercentage).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .when(not(nullValue()))
        .withMessage("ValueOfConvenienceFeePercentage value is mandatory")
        .withAttempedValue(of(MerchantPresentMode::getValueOfConvenienceFeePercentage).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 5))
        .when(not(nullValue()))
        .withMessage("ValueOfConvenienceFeePercentage value must be less then or equal size five")
        .withAttempedValue(of(MerchantPresentMode::getValueOfConvenienceFeePercentage).andThen(TagLengthString::getValue))
        .critical()

      .must(isNumber(TagLengthString::getValue))
        .when(not(nullValue()))
        .withMessage("ValueOfConvenienceFeePercentage value must be a valid number")
        .withAttempedValue(of(MerchantPresentMode::getValueOfConvenienceFeePercentage).andThen(TagLengthString::getValue))
        .critical()

      .must(betweenInclusive(convertToBigDecimal(TagLengthString::getValue), new BigDecimal("00.01"), new BigDecimal("99.99")))
        .when(not(nullValue()))
        .withMessage("ValueOfConvenienceFeePercentage value must be between '00.01' and '99.99'")
        .withAttempedValue(of(MerchantPresentMode::getValueOfConvenienceFeePercentage).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor(MerchantPresentMode::getCountryCode)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .withMessage("CountryCode tag is mandatory")
        .withAttempedValue(of(MerchantPresentMode::getCountryCode).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .withMessage("CountryCode tag must be size equal two")
        .withAttempedValue(of(MerchantPresentMode::getCountryCode).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .withMessage("CountryCode tag must be number")
        .withAttempedValue(of(MerchantPresentMode::getCountryCode).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, MerchantPresentModeCodes.ID_COUNTRY_CODE))
        .withMessage(String.format("CountryCode tag must be '%s'", MerchantPresentModeCodes.ID_COUNTRY_CODE))
        .withAttempedValue(of(MerchantPresentMode::getCountryCode).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("CountryCode value is mandatory")
        .withAttempedValue(of(MerchantPresentMode::getCountryCode).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSize(TagLengthString::getValue, 2))
        .withMessage("CountryCode value must be size equal two")
        .withAttempedValue(of(MerchantPresentMode::getCountryCode).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor(MerchantPresentMode::getMerchantName)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .withMessage("MerchantName tag is mandatory")
        .withAttempedValue(of(MerchantPresentMode::getMerchantName).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .withMessage("MerchantName tag must be size equal two")
        .withAttempedValue(of(MerchantPresentMode::getMerchantName).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .withMessage("MerchantName tag must be number")
        .withAttempedValue(of(MerchantPresentMode::getMerchantName).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, MerchantPresentModeCodes.ID_MERCHANT_NAME))
        .withMessage(String.format("MerchantName tag must be '%s'", MerchantPresentModeCodes.ID_MERCHANT_NAME))
        .withAttempedValue(of(MerchantPresentMode::getMerchantName).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("MerchantName value is mandatory")
        .withAttempedValue(of(MerchantPresentMode::getMerchantName).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 25))
        .withMessage("MerchantName value must less than or equal size equal twenty-five")
        .withAttempedValue(of(MerchantPresentMode::getMerchantName).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor(MerchantPresentMode::getMerchantCity)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .withMessage("MerchantCity tag is mandatory")
        .withAttempedValue(of(MerchantPresentMode::getMerchantCity).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .withMessage("MerchantCity tag must be size equal two")
        .withAttempedValue(of(MerchantPresentMode::getMerchantCity).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .withMessage("MerchantCity tag must be number")
        .withAttempedValue(of(MerchantPresentMode::getMerchantCity).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, MerchantPresentModeCodes.ID_MERCHANT_CITY))
        .withMessage(String.format("MerchantCity tag must be '%s'", MerchantPresentModeCodes.ID_MERCHANT_CITY))
        .withAttempedValue(of(MerchantPresentMode::getMerchantCity).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("MerchantCity value is mandatory")
        .withAttempedValue(of(MerchantPresentMode::getMerchantCity).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 15))
        .withMessage("MerchantCity value is must less than or equal size fifteen")
        .withAttempedValue(of(MerchantPresentMode::getMerchantCity).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor(MerchantPresentMode::getPostalCode)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .when(not(nullValue()))
        .withMessage("PostalCode tag is mandatory")
        .withAttempedValue(of(MerchantPresentMode::getPostalCode).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .when(not(nullValue()))
        .withMessage("PostalCode tag must be size equal two")
        .withAttempedValue(of(MerchantPresentMode::getPostalCode).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .when(not(nullValue()))
        .withMessage("PostalCode tag must be number")
        .withAttempedValue(of(MerchantPresentMode::getPostalCode).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, MerchantPresentModeCodes.ID_POSTAL_CODE))
        .when(not(nullValue()))
        .withMessage(String.format("PostalCode tag must be '%s'", MerchantPresentModeCodes.ID_POSTAL_CODE))
        .withAttempedValue(of(MerchantPresentMode::getPostalCode).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .when(not(nullValue()))
        .withMessage("PostalCode value is mandatory")
        .withAttempedValue(of(MerchantPresentMode::getPostalCode).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 10))
        .when(not(nullValue()))
        .withMessage("PostalCode value must less then or equal size ten")
        .withAttempedValue(of(MerchantPresentMode::getPostalCode).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor(MerchantPresentMode::getAdditionalDataField)

      .must(not(stringEmptyOrNull(AdditionalDataField::getTag)))
        .when(not(nullValue()))
        .withMessage("AdditionalDataField tag is mandatory")
        .withAttempedValue(of(MerchantPresentMode::getAdditionalDataField).andThen(AdditionalDataField::getTag))
        .critical()

      .must(stringSize(AdditionalDataField::getTag, 2))
        .when(not(nullValue()))
        .withMessage("AdditionalDataField tag must be size equal two")
        .withAttempedValue(of(MerchantPresentMode::getAdditionalDataField).andThen(AdditionalDataField::getTag))
        .critical()

      .must(isNumeric(AdditionalDataField::getTag))
        .when(not(nullValue()))
        .withMessage("AdditionalDataField tag must be number")
        .withAttempedValue(of(MerchantPresentMode::getAdditionalDataField).andThen(AdditionalDataField::getTag))
        .critical()

      .must(stringEquals(AdditionalDataField::getTag, MerchantPresentModeCodes.ID_ADDITIONAL_DATA_FIELD_TEMPLATE))
        .when(not(nullValue()))
        .withMessage(String.format("AdditionalDataField tag must be '%s'", MerchantPresentModeCodes.ID_ADDITIONAL_DATA_FIELD_TEMPLATE))
        .withAttempedValue(of(MerchantPresentMode::getAdditionalDataField).andThen(AdditionalDataField::getTag))
        .critical()

      .must(not(nullValue(AdditionalDataField::getValue)))
        .when(not(nullValue()))
        .withMessage("AdditionalDataField value must be not null")
        .withAttempedValue(of(MerchantPresentMode::getAdditionalDataField).andThen(AdditionalDataField::getValue))
        .critical()

      .whenever(not(nullValue(AdditionalDataField::getValue)))
        .withValidator(new AdditionalDataFieldValidator());

    /**
     *
     */
    ruleFor(MerchantPresentMode::getMerchantInformationLanguage)

      .must(not(stringEmptyOrNull(MerchantInformationLanguage::getTag)))
        .when(not(nullValue()))
        .withMessage("MerchantInformationLanguage tag is mandatory")
        .withAttempedValue(of(MerchantPresentMode::getMerchantInformationLanguage).andThen(MerchantInformationLanguage::getTag))
        .critical()

      .must(stringSize(MerchantInformationLanguage::getTag, 2))
        .when(not(nullValue()))
        .withMessage("MerchantInformationLanguage tag must be size equal two")
        .withAttempedValue(of(MerchantPresentMode::getMerchantInformationLanguage).andThen(MerchantInformationLanguage::getTag))
        .critical()

      .must(isNumeric(MerchantInformationLanguage::getTag))
        .when(not(nullValue()))
        .withMessage("MerchantInformationLanguage tag must be number")
        .withAttempedValue(of(MerchantPresentMode::getMerchantInformationLanguage).andThen(MerchantInformationLanguage::getTag))
        .critical()

      .must(stringEquals(MerchantInformationLanguage::getTag, MerchantPresentModeCodes.ID_MERCHANT_INFORMATION_LANGUAGE_TEMPLATE))
        .when(not(nullValue()))
        .withMessage(String.format("MerchantInformationLanguage tag must be '%s'", MerchantPresentModeCodes.ID_MERCHANT_INFORMATION_LANGUAGE_TEMPLATE))
        .withAttempedValue(of(MerchantPresentMode::getMerchantInformationLanguage).andThen(MerchantInformationLanguage::getTag))
        .critical()

      .whenever(not(nullValue()))
        .withValidator(new MerchantInformationLanguageValidator());

    /**
     *
     */
    ruleFor(merchantPresentMode -> merchantPresentMode)

      .must(nullValue(of(MerchantPresentMode::getValueOfConvenienceFeeFixed)).and(nullValue(of(MerchantPresentMode::getValueOfConvenienceFeePercentage))))
        .when(stringEquals(of(MerchantPresentMode::getTipOrConvenienceIndicator).andThen(TagLengthString::getValue), "01"))
        .withMessage("When TipOrConvenienceIndicator is '01' ValueOfConvenienceFeeFixed and ValueOfConvenienceFeePercentage must be null")
        .withAttempedValue(of(MerchantPresentMode::getTipOrConvenienceIndicator).andThen(TagLengthString::getValue))

      .must(nullValue(of(MerchantPresentMode::getValueOfConvenienceFeePercentage)))
        .when(stringEquals(of(MerchantPresentMode::getTipOrConvenienceIndicator).andThen(TagLengthString::getValue), "02"))
        .withMessage("When TipOrConvenienceIndicator is '02' ValueOfConvenienceFeePercentage must be null")
        .withAttempedValue(of(MerchantPresentMode::getTipOrConvenienceIndicator).andThen(TagLengthString::getValue))

      .must(nullValue(of(MerchantPresentMode::getValueOfConvenienceFeeFixed)))
        .when(stringEquals(of(MerchantPresentMode::getTipOrConvenienceIndicator).andThen(TagLengthString::getValue), "03"))
        .withMessage("When TipOrConvenienceIndicator is '03' ValueOfConvenienceFeeFixed must be null")
        .withAttempedValue(of(MerchantPresentMode::getTipOrConvenienceIndicator).andThen(TagLengthString::getValue));

    /**
     *
     */
    ruleFor(MerchantPresentMode::getMerchantAccountInformation)
      .must(greaterThan(Map::size, 0))
        .withMessage("MerchantAccountInformation size must have at least one")
        .critical();

    ruleFor(MerchantPresentMode::getRFUforEMVCo)
      .must(betweenInclusive(Collection::size, 1, 17))
        .when(greaterThan(Collection::size, 0))
        .withMessage("RFUforEMVCo list size must be between one and seventeen")
        .critical();

    ruleFor(MerchantPresentMode::getUnreserveds)
      .must(betweenInclusive(Map::size, 1, 17))
        .when(greaterThan(Map::size, 0))
        .withMessage("RFUforEMVCo list size must be between one and seventeen");

  }

  private static Function<TagLengthString, BigDecimal> convertToBigDecimal(final Function<TagLengthString, String> fnc) {
    return obj -> new BigDecimal(fnc.apply(obj));
  }

}

// @formatter:on
