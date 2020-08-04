package com.emv.qrcode.validators.mpm;

import static br.com.fluentvalidator.predicate.ComparablePredicate.betweenInclusive;
import static br.com.fluentvalidator.predicate.ComparablePredicate.equalTo;
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

    ruleFor(MerchantPresentMode::getPayloadFormatIndicator)
      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .withMessage("PayloadFormatIndicator tag is mandatory")
        .critical()
      .must(stringSize(TagLengthString::getTag, 2))
        .withMessage("PayloadFormatIndicator tag must be size equal two")
        .critical()
      .must(isNumeric(TagLengthString::getTag))
        .withMessage("PayloadFormatIndicator tag must be number")
        .critical()
      .must(stringEquals(TagLengthString::getTag, MerchantPresentModeCodes.ID_PAYLOAD_FORMAT_INDICATOR))
        .withMessage(String.format("PayloadFormatIndicator tag must be '%s'", MerchantPresentModeCodes.ID_PAYLOAD_FORMAT_INDICATOR))
        .critical()
      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("PayloadFormatIndicator value is mandatory")
        .critical()
      .must(isNumeric(TagLengthString::getValue))
        .withMessage("PayloadFormatIndicator value must be number")
        .critical()
      .must(stringSize(TagLengthString::getValue, 2))
        .withMessage("PayloadFormatIndicator value must be size equal two")
        .critical();

    ruleFor(MerchantPresentMode::getPointOfInitiationMethod)
      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .when(not(nullValue()))
        .withMessage("PointOfInitiationMethod tag is mandatory")
        .critical()
      .must(stringSize(TagLengthString::getTag, 2))
        .when(not(nullValue()))
        .withMessage("PointOfInitiationMethod tag must be size equal two")
        .critical()
      .must(isNumeric(TagLengthString::getTag))
        .when(not(nullValue()))
        .withMessage("PointOfInitiationMethod tag must be number")
        .critical()
      .must(stringEquals(TagLengthString::getTag, MerchantPresentModeCodes.ID_POINT_OF_INITIATION_METHOD))
        .when(not(nullValue()))
        .withMessage(String.format("PointOfInitiationMethod tag must be '%s'", MerchantPresentModeCodes.ID_POINT_OF_INITIATION_METHOD))
        .critical()
      .must(isNumeric(TagLengthString::getValue))
        .when(not(nullValue()))
        .withMessage("PointOfInitiationMethod value should be numeric")
        .critical()
      .must(stringSize(TagLengthString::getValue, 2))
        .when(not(nullValue()))
        .withMessage("PointOfInitiationMethod value must be size equal two")
        .critical()
      .must(equalTo(TagLengthString::getValue, "11").or(equalTo(TagLengthString::getValue, "12")))
        .when(not(nullValue()))
        .withMessage("PointOfInitiationMethod value should be '11' or '12'")
        .critical();

    ruleFor(MerchantPresentMode::getMerchantCategoryCode)
      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .withMessage("MerchantCategoryCode tag is mandatory")
        .critical()
      .must(stringSize(TagLengthString::getTag, 2))
        .withMessage("MerchantCategoryCode tag must be size equal two")
        .critical()
      .must(isNumeric(TagLengthString::getTag))
        .withMessage("MerchantCategoryCode tag must be number")
        .critical()
      .must(stringEquals(TagLengthString::getTag, MerchantPresentModeCodes.ID_MERCHANT_CATEGORY_CODE))
        .withMessage(String.format("MerchantCategoryCode tag must be '%s'", MerchantPresentModeCodes.ID_MERCHANT_CATEGORY_CODE))
        .critical()
      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("MerchantCategoryCode value is mandatory")
        .critical()
      .must(isNumeric(TagLengthString::getValue))
        .withMessage("MerchantCategoryCode value must be number")
        .critical()
      .must(stringSize(TagLengthString::getValue, 4))
        .withMessage("MerchantCategoryCode value must be size equal four")
        .critical();

    ruleFor(MerchantPresentMode::getTransactionCurrency)
      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .withMessage("TransactionCurrency tag is mandatory")
        .critical()
      .must(stringSize(TagLengthString::getTag, 2))
        .withMessage("TransactionCurrency tag must be size equal two")
        .critical()
      .must(isNumeric(TagLengthString::getTag))
        .withMessage("TransactionCurrency tag must be number")
        .critical()
      .must(stringEquals(TagLengthString::getTag, MerchantPresentModeCodes.ID_TRANSACTION_CURRENCY))
        .withMessage(String.format("TransactionCurrency tag must be '%s'", MerchantPresentModeCodes.ID_TRANSACTION_CURRENCY))
        .critical()
      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("TransactionCurrency value is mandatory")
        .critical()
      .must(isNumeric(TagLengthString::getValue))
        .withMessage("TransactionCurrency value must be number")
        .critical()
      .must(stringSize(TagLengthString::getValue, 3))
        .withMessage("TransactionCurrency value must be size equal three")
        .critical();

    ruleFor(MerchantPresentMode::getTipOrConvenienceIndicator)
      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .when(not(nullValue()))
        .withMessage("TipOrConvenienceIndicator tag is mandatory")
        .critical()
      .must(stringSize(TagLengthString::getTag, 2))
        .when(not(nullValue()))
        .withMessage("TipOrConvenienceIndicator tag must be size equal two")
        .critical()
      .must(isNumeric(TagLengthString::getTag))
        .when(not(nullValue()))
        .withMessage("TipOrConvenienceIndicator tag must be number")
        .critical()
      .must(stringEquals(TagLengthString::getTag, MerchantPresentModeCodes.ID_TIP_OR_CONVENIENCE_INDICATOR))
        .when(not(nullValue()))
        .withMessage(String.format("TipOrConvenienceIndicator tag must be '%s'", MerchantPresentModeCodes.ID_TIP_OR_CONVENIENCE_INDICATOR))
        .critical()
      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .when(not(nullValue()))
        .withMessage("TipOrConvenienceIndicator value is mandatory")
        .critical()
      .must(isNumeric(TagLengthString::getValue))
        .when(not(nullValue()))
        .withMessage("TipOrConvenienceIndicator value must be number")
        .critical()
      .must(stringSize(TagLengthString::getValue, 2))
        .when(not(nullValue()))
        .withMessage("TipOrConvenienceIndicator value must be size equal two")
        .critical();

    ruleFor(MerchantPresentMode::getCountryCode)
      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .withMessage("CountryCode tag is mandatory")
        .critical()
      .must(stringSize(TagLengthString::getTag, 2))
        .withMessage("CountryCode tag must be size equal two")
        .critical()
      .must(isNumeric(TagLengthString::getTag))
        .withMessage("CountryCode tag must be number")
        .critical()
      .must(stringEquals(TagLengthString::getTag, MerchantPresentModeCodes.ID_COUNTRY_CODE))
        .withMessage(String.format("CountryCode tag must be '%s'", MerchantPresentModeCodes.ID_COUNTRY_CODE))
        .critical()
      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("CountryCode value is mandatory")
        .critical()
      .must(stringSize(TagLengthString::getValue, 2))
        .withMessage("CountryCode value must be size equal two")
        .critical();

    ruleFor(MerchantPresentMode::getMerchantName)
      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .withMessage("MerchantName tag is mandatory")
        .critical()
      .must(stringSize(TagLengthString::getTag, 2))
        .withMessage("MerchantName tag must be size equal two")
        .critical()
      .must(isNumeric(TagLengthString::getTag))
        .withMessage("MerchantName tag must be number")
        .critical()
      .must(stringEquals(TagLengthString::getTag, MerchantPresentModeCodes.ID_MERCHANT_NAME))
        .withMessage(String.format("MerchantName tag must be '%s'", MerchantPresentModeCodes.ID_MERCHANT_NAME))
        .critical()
      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("MerchantName value is mandatory")
        .critical()
      .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 25))
        .withMessage("MerchantName value must less than or equal size equal twenty-five")
        .critical();

    ruleFor(MerchantPresentMode::getMerchantCity)
      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .withMessage("MerchantCity tag is mandatory")
        .critical()
      .must(stringSize(TagLengthString::getValue, 2))
        .withMessage("MerchantCity tag must be size equal two")
        .critical()
      .must(isNumeric(TagLengthString::getTag))
        .withMessage("MerchantCity tag must be number")
        .critical()
      .must(stringEquals(TagLengthString::getTag, MerchantPresentModeCodes.ID_MERCHANT_CITY))
        .withMessage(String.format("MerchantCity tag must be '%s'", MerchantPresentModeCodes.ID_MERCHANT_CITY))
        .critical()
      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("MerchantCity value is mandatory")
        .critical()
      .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 15))
        .withMessage("MerchantCity value is must less than or equal size fifteen")
        .critical();

    ruleFor(MerchantPresentMode::getPostalCode)
      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .when(not(nullValue()))
        .withMessage("PostalCode tag is mandatory")
        .critical()
      .must(stringSize(TagLengthString::getTag, 2))
        .when(not(nullValue()))
        .withMessage("PostalCode tag must be size equal two")
        .critical()
      .must(isNumeric(TagLengthString::getTag))
        .when(not(nullValue()))
        .withMessage("PostalCode tag must be number")
        .critical()
      .must(stringEquals(TagLengthString::getTag, MerchantPresentModeCodes.ID_POSTAL_CODE))
        .when(not(nullValue()))
        .withMessage(String.format("PostalCode tag must be '%s'", MerchantPresentModeCodes.ID_POSTAL_CODE))
        .critical()
      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .when(not(nullValue()))
        .withMessage("PostalCode value is mandatory")
        .critical()
      .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 10))
        .when(not(nullValue()))
        .withMessage("PostalCode value must less then or equal size ten")
        .critical();

    ruleFor(MerchantPresentMode::getAdditionalDataField)
      .must(not(stringEmptyOrNull(AdditionalDataField::getTag)))
        .when(not(nullValue()))
        .withMessage("AdditionalDataField tag is mandatory")
        .critical()
      .must(stringSize(AdditionalDataField::getTag, 2))
        .when(not(nullValue()))
        .withMessage("AdditionalDataField tag must be size equal two")
        .critical()
      .must(isNumeric(AdditionalDataField::getTag))
        .when(not(nullValue()))
        .withMessage("AdditionalDataField tag must be number")
        .critical()
      .must(stringEquals(AdditionalDataField::getTag, MerchantPresentModeCodes.ID_ADDITIONAL_DATA_FIELD_TEMPLATE))
        .when(not(nullValue()))
        .withMessage(String.format("AdditionalDataField tag must be '%s'", MerchantPresentModeCodes.ID_ADDITIONAL_DATA_FIELD_TEMPLATE))
        .critical()
      .whenever(not(nullValue()))
        .withValidator(new AdditionalDataFieldValidator());

    ruleFor(MerchantPresentMode::getMerchantInformationLanguage)
      .must(not(stringEmptyOrNull(MerchantInformationLanguage::getTag)))
        .when(not(nullValue()))
        .withMessage("MerchantInformationLanguage tag is mandatory")
        .critical()
      .must(stringSize(MerchantInformationLanguage::getTag, 2))
        .when(not(nullValue()))
        .withMessage("MerchantInformationLanguage tag must be size equal two")
        .critical()
      .must(isNumeric(MerchantInformationLanguage::getTag))
        .when(not(nullValue()))
        .withMessage("MerchantInformationLanguage tag must be number")
        .critical()
      .must(stringEquals(MerchantInformationLanguage::getTag, MerchantPresentModeCodes.ID_MERCHANT_INFORMATION_LANGUAGE_TEMPLATE))
        .when(not(nullValue()))
        .withMessage(String.format("MerchantInformationLanguage tag must be '%s'", MerchantPresentModeCodes.ID_MERCHANT_INFORMATION_LANGUAGE_TEMPLATE))
        .critical()
      .whenever(not(nullValue()))
        .withValidator(new MerchantInformationLanguageValidator());

    ruleFor(MerchantPresentMode::getMerchantAccountInformation)
      .must(greaterThan(Map::size, 0))
        .withMessage("MerchantAccountInformation must have at least one")
        .critical();

    ruleFor(MerchantPresentMode::getRFUforEMVCo)
      .must(betweenInclusive(Collection::size, 1, 17))
        .when(greaterThan(Collection::size, 0))
        .withMessage("RFUforEMVCo list must be between one and seventeen")
        .critical();

    ruleFor(MerchantPresentMode::getUnreserveds)
      .must(betweenInclusive(Map::size, 1, 17))
        .when(greaterThan(Map::size, 0))
        .withMessage("RFUforEMVCo list must be between one and seventeen");

  }

}

// @formatter:on
