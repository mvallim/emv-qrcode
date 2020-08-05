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
import com.emv.qrcode.model.mpm.AdditionalDataFieldValue;
import com.emv.qrcode.model.mpm.constants.AdditionalDataFieldCodes;

import br.com.fluentvalidator.AbstractValidator;

// @formatter:off
class AdditionalDataFieldValueValidator extends AbstractValidator<AdditionalDataFieldValue> {

  @Override
  public void rules() {

    /**
     *
     */
    ruleFor(AdditionalDataFieldValue::getBillNumber)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .when(not(nullValue()))
        .withMessage("BillNumber tag is mandatory")
        .withAttempedValue(of(AdditionalDataFieldValue::getBillNumber).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .when(not(nullValue()))
        .withMessage("BillNumber tag must be size equal two")
        .withAttempedValue(of(AdditionalDataFieldValue::getBillNumber).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .when(not(nullValue()))
        .withMessage("BillNumber tag must be number")
        .withAttempedValue(of(AdditionalDataFieldValue::getBillNumber).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, AdditionalDataFieldCodes.ID_BILL_NUMBER))
        .when(not(nullValue()))
        .withMessage(String.format("BillNumber tag must be '%s'", AdditionalDataFieldCodes.ID_BILL_NUMBER))
        .withAttempedValue(of(AdditionalDataFieldValue::getBillNumber).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .when(not(nullValue()))
        .withMessage("BillNumber value is mandatory")
        .withAttempedValue(of(AdditionalDataFieldValue::getBillNumber).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 25))
        .when(not(nullValue()))
        .withMessage("BillNumber value must less then or equal size twenty-five")
        .withAttempedValue(of(AdditionalDataFieldValue::getBillNumber).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor(AdditionalDataFieldValue::getMobileNumber)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .when(not(nullValue()))
        .withMessage("MobileNumber tag is mandatory")
        .withAttempedValue(of(AdditionalDataFieldValue::getMobileNumber).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .when(not(nullValue()))
        .withMessage("MobileNumber tag must be size equal two")
        .withAttempedValue(of(AdditionalDataFieldValue::getMobileNumber).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .when(not(nullValue()))
        .withMessage("MobileNumber tag must be number")
        .withAttempedValue(of(AdditionalDataFieldValue::getMobileNumber).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, AdditionalDataFieldCodes.ID_MOBILE_NUMBER))
        .when(not(nullValue()))
        .withMessage(String.format("MobileNumber tag must be '%s'", AdditionalDataFieldCodes.ID_MOBILE_NUMBER))
        .withAttempedValue(of(AdditionalDataFieldValue::getMobileNumber).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .when(not(nullValue()))
        .withMessage("MobileNumber value is mandatory")
        .withAttempedValue(of(AdditionalDataFieldValue::getMobileNumber).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 25))
        .when(not(nullValue()))
        .withMessage("MobileNumber value must less then or equal size twenty-five")
        .withAttempedValue(of(AdditionalDataFieldValue::getMobileNumber).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor(AdditionalDataFieldValue::getStoreLabel)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .when(not(nullValue()))
        .withMessage("StoreLabel tag is mandatory")
        .withAttempedValue(of(AdditionalDataFieldValue::getStoreLabel).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .when(not(nullValue()))
        .withMessage("StoreLabel tag must be size equal two")
        .withAttempedValue(of(AdditionalDataFieldValue::getStoreLabel).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .when(not(nullValue()))
        .withMessage("StoreLabel tag must be number")
        .withAttempedValue(of(AdditionalDataFieldValue::getStoreLabel).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, AdditionalDataFieldCodes.ID_STORE_LABEL))
        .when(not(nullValue()))
        .withMessage(String.format("StoreLabel tag must be '%s'", AdditionalDataFieldCodes.ID_STORE_LABEL))
        .withAttempedValue(of(AdditionalDataFieldValue::getStoreLabel).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .when(not(nullValue()))
        .withMessage("StoreLabel value is mandatory")
        .withAttempedValue(of(AdditionalDataFieldValue::getStoreLabel).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 25))
        .when(not(nullValue()))
        .withMessage("StoreLabel value must less then or equal size twenty-five")
        .withAttempedValue(of(AdditionalDataFieldValue::getStoreLabel).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor(AdditionalDataFieldValue::getLoyaltyNumber)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .when(not(nullValue()))
        .withMessage("LoyaltyNumber tag is mandatory")
        .withAttempedValue(of(AdditionalDataFieldValue::getLoyaltyNumber).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .when(not(nullValue()))
        .withMessage("LoyaltyNumber tag must be size equal two")
        .withAttempedValue(of(AdditionalDataFieldValue::getLoyaltyNumber).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .when(not(nullValue()))
        .withMessage("LoyaltyNumber tag must be number")
        .withAttempedValue(of(AdditionalDataFieldValue::getLoyaltyNumber).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, AdditionalDataFieldCodes.ID_LOYALTY_NUMBER))
        .when(not(nullValue()))
        .withMessage(String.format("LoyaltyNumber tag must be '%s'", AdditionalDataFieldCodes.ID_LOYALTY_NUMBER))
        .withAttempedValue(of(AdditionalDataFieldValue::getLoyaltyNumber).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .when(not(nullValue()))
        .withMessage("LoyaltyNumber value is mandatory")
        .withAttempedValue(of(AdditionalDataFieldValue::getLoyaltyNumber).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 25))
        .when(not(nullValue()))
        .withMessage("LoyaltyNumber value must less then or equal size twenty-five")
        .withAttempedValue(of(AdditionalDataFieldValue::getLoyaltyNumber).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor(AdditionalDataFieldValue::getReferenceLabel)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .when(not(nullValue()))
        .withMessage("ReferenceLabel tag is mandatory")
        .withAttempedValue(of(AdditionalDataFieldValue::getReferenceLabel).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .when(not(nullValue()))
        .withMessage("ReferenceLabel tag must be size equal two")
        .withAttempedValue(of(AdditionalDataFieldValue::getReferenceLabel).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .when(not(nullValue()))
        .withMessage("ReferenceLabel tag must be number")
        .withAttempedValue(of(AdditionalDataFieldValue::getReferenceLabel).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, AdditionalDataFieldCodes.ID_REFERENCE_LABEL))
        .when(not(nullValue()))
        .withMessage(String.format("ReferenceLabel tag must be '%s'", AdditionalDataFieldCodes.ID_REFERENCE_LABEL))
        .withAttempedValue(of(AdditionalDataFieldValue::getReferenceLabel).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .when(not(nullValue()))
        .withMessage("ReferenceLabel value is mandatory")
        .withAttempedValue(of(AdditionalDataFieldValue::getReferenceLabel).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 25))
        .when(not(nullValue()))
        .withMessage("ReferenceLabel value must less then or equal size twenty-five")
        .withAttempedValue(of(AdditionalDataFieldValue::getReferenceLabel).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor(AdditionalDataFieldValue::getCustomerLabel)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .when(not(nullValue()))
        .withMessage("CustomerLabel tag is mandatory")
        .withAttempedValue(of(AdditionalDataFieldValue::getCustomerLabel).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .when(not(nullValue()))
        .withMessage("CustomerLabel tag must be size equal two")
        .withAttempedValue(of(AdditionalDataFieldValue::getCustomerLabel).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .when(not(nullValue()))
        .withMessage("CustomerLabel tag must be number")
        .withAttempedValue(of(AdditionalDataFieldValue::getCustomerLabel).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, AdditionalDataFieldCodes.ID_CUSTOMER_LABEL))
        .when(not(nullValue()))
        .withMessage(String.format("CustomerLabel tag must be '%s'", AdditionalDataFieldCodes.ID_CUSTOMER_LABEL))
        .withAttempedValue(of(AdditionalDataFieldValue::getCustomerLabel).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .when(not(nullValue()))
        .withMessage("CustomerLabel value is mandatory")
        .withAttempedValue(of(AdditionalDataFieldValue::getCustomerLabel).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 25))
        .when(not(nullValue()))
        .withMessage("CustomerLabel value must less then or equal size twenty-five")
        .withAttempedValue(of(AdditionalDataFieldValue::getCustomerLabel).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor(AdditionalDataFieldValue::getTerminalLabel)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .when(not(nullValue()))
        .withMessage("TerminalLabel tag is mandatory")
        .withAttempedValue(of(AdditionalDataFieldValue::getTerminalLabel).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .when(not(nullValue()))
        .withMessage("TerminalLabel tag must be size equal two")
        .withAttempedValue(of(AdditionalDataFieldValue::getTerminalLabel).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .when(not(nullValue()))
        .withMessage("TerminalLabel tag must be number")
        .withAttempedValue(of(AdditionalDataFieldValue::getTerminalLabel).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, AdditionalDataFieldCodes.ID_TERMINAL_LABEL))
        .when(not(nullValue()))
        .withMessage(String.format("TerminalLabel tag must be '%s'", AdditionalDataFieldCodes.ID_TERMINAL_LABEL))
        .withAttempedValue(of(AdditionalDataFieldValue::getTerminalLabel).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .when(not(nullValue()))
        .withMessage("TerminalLabel value is mandatory")
        .withAttempedValue(of(AdditionalDataFieldValue::getTerminalLabel).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 25))
        .when(not(nullValue()))
        .withMessage("TerminalLabel value must less then or equal size twenty-five")
        .withAttempedValue(of(AdditionalDataFieldValue::getTerminalLabel).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor(AdditionalDataFieldValue::getPurposeTransaction)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .when(not(nullValue()))
        .withMessage("TerminalLabel tag is mandatory")
        .withAttempedValue(of(AdditionalDataFieldValue::getPurposeTransaction).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .when(not(nullValue()))
        .withMessage("TerminalLabel tag must be size equal two")
        .withAttempedValue(of(AdditionalDataFieldValue::getPurposeTransaction).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .when(not(nullValue()))
        .withMessage("TerminalLabel tag must be number")
        .withAttempedValue(of(AdditionalDataFieldValue::getPurposeTransaction).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, AdditionalDataFieldCodes.ID_PURPOSE_TRANSACTION))
        .when(not(nullValue()))
        .withMessage(String.format("TerminalLabel tag must be '%s'", AdditionalDataFieldCodes.ID_PURPOSE_TRANSACTION))
        .withAttempedValue(of(AdditionalDataFieldValue::getPurposeTransaction).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .when(not(nullValue()))
        .withMessage("TerminalLabel value is mandatory")
        .withAttempedValue(of(AdditionalDataFieldValue::getPurposeTransaction).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 25))
        .when(not(nullValue()))
        .withMessage("TerminalLabel value must less then or equal size twenty-five")
        .withAttempedValue(of(AdditionalDataFieldValue::getPurposeTransaction).andThen(TagLengthString::getValue))
        .critical();

    /**
     *
     */
    ruleFor(AdditionalDataFieldValue::getAdditionalConsumerDataRequest)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .when(not(nullValue()))
        .withMessage("AdditionalConsumerDataRequest tag is mandatory")
        .withAttempedValue(of(AdditionalDataFieldValue::getAdditionalConsumerDataRequest).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .when(not(nullValue()))
        .withMessage("AdditionalConsumerDataRequest tag must be size equal two")
        .withAttempedValue(of(AdditionalDataFieldValue::getAdditionalConsumerDataRequest).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .when(not(nullValue()))
        .withMessage("AdditionalConsumerDataRequest tag must be number")
        .withAttempedValue(of(AdditionalDataFieldValue::getAdditionalConsumerDataRequest).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, AdditionalDataFieldCodes.ID_ADDITIONAL_CONSUMER_DATA_REQUEST))
        .when(not(nullValue()))
        .withMessage(String.format("AdditionalConsumerDataRequest tag must be '%s'", AdditionalDataFieldCodes.ID_ADDITIONAL_CONSUMER_DATA_REQUEST))
        .withAttempedValue(of(AdditionalDataFieldValue::getAdditionalConsumerDataRequest).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .when(not(nullValue()))
        .withMessage("AdditionalConsumerDataRequest value is mandatory")
        .withAttempedValue(of(AdditionalDataFieldValue::getAdditionalConsumerDataRequest).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 3))
        .when(not(nullValue()))
        .withMessage("AdditionalConsumerDataRequest value must less then or equal size three")
        .withAttempedValue(of(AdditionalDataFieldValue::getAdditionalConsumerDataRequest).andThen(TagLengthString::getValue))
        .critical();
   }

}
// @formatter:on
