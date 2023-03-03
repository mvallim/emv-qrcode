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

package com.emv.qrcode.validators;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.emv.qrcode.core.model.mpm.TagLengthString;
import com.emv.qrcode.model.mpm.AdditionalDataField;
import com.emv.qrcode.model.mpm.AdditionalDataFieldTemplate;
import com.emv.qrcode.model.mpm.MerchantAccountInformationReservedAdditional;
import com.emv.qrcode.model.mpm.MerchantAccountInformationTemplate;
import com.emv.qrcode.model.mpm.MerchantInformationLanguage;
import com.emv.qrcode.model.mpm.MerchantInformationLanguageTemplate;
import com.emv.qrcode.model.mpm.MerchantPresentedMode;
import com.emv.qrcode.model.mpm.PaymentSystemSpecific;
import com.emv.qrcode.model.mpm.PaymentSystemSpecificTemplate;
import com.emv.qrcode.model.mpm.Unreserved;
import com.emv.qrcode.model.mpm.UnreservedTemplate;

import br.com.fluentvalidator.context.ValidationResult;

public class MerchantPresentedModeValidatorTest {

  @Test
  public void testSuccessValidateWhenWithoutCRC() {
    final MerchantPresentedMode merchantPresentMode = getValidMerchantPresentMode();

    final ValidationResult validationResult = MerchantPresentedModeValidate.validate(merchantPresentMode);

    assertTrue(validationResult.isValid());
  }

  private MerchantPresentedMode getValidMerchantPresentMode() {
    final AdditionalDataFieldTemplate additionalDataField = getAddtionalDataField();
    final MerchantAccountInformationTemplate merchantAccountInformation = getMerchanAccountInformation();
    final MerchantInformationLanguageTemplate merchantInformationLanguage = getMerchantInformationLanguage();
    final UnreservedTemplate unreserved = getUnreserved();
    final TagLengthString rFUforEMVCo = new TagLengthString("65", "00");

    final MerchantPresentedMode merchantPresentMode = new MerchantPresentedMode();
    merchantPresentMode.setAdditionalDataField(additionalDataField);
    merchantPresentMode.setCountryCode("CN");
    merchantPresentMode.setMerchantCategoryCode("4111");
    merchantPresentMode.setMerchantCity("BEIJING");
    merchantPresentMode.setMerchantInformationLanguage(merchantInformationLanguage);
    merchantPresentMode.setMerchantName("BEST TRANSPORT");
    merchantPresentMode.setPayloadFormatIndicator("01");
    merchantPresentMode.setPointOfInitiationMethod("11");
    merchantPresentMode.setPostalCode("1234567");
    merchantPresentMode.setTipOrConvenienceIndicator("02");
    merchantPresentMode.setTransactionAmount("23.72");
    merchantPresentMode.setTransactionCurrency("156");
    merchantPresentMode.setValueOfConvenienceFeeFixed("500");
    merchantPresentMode.addMerchantAccountInformation(merchantAccountInformation);
    merchantPresentMode.addRFUforEMVCo(rFUforEMVCo);
    merchantPresentMode.addUnreserved(unreserved);
    return merchantPresentMode;
  }

  private MerchantAccountInformationTemplate getMerchanAccountInformation() {

    final TagLengthString paymentNetworkSpecific = new TagLengthString();
    paymentNetworkSpecific.setTag("01");
    paymentNetworkSpecific.setValue("abcd");

    final MerchantAccountInformationReservedAdditional merchantAccountInformationValue = new MerchantAccountInformationReservedAdditional();
    merchantAccountInformationValue.setGloballyUniqueIdentifier("hoge");
    merchantAccountInformationValue.addPaymentNetworkSpecific(paymentNetworkSpecific);

    final MerchantAccountInformationTemplate merchantAccountInformation = new MerchantAccountInformationTemplate();
    merchantAccountInformation.setValue(merchantAccountInformationValue);
    merchantAccountInformation.setTag("26");
    return merchantAccountInformation;
  }

  private UnreservedTemplate getUnreserved() {

    final TagLengthString contextSpecificData = new TagLengthString();
    contextSpecificData.setTag("07");
    contextSpecificData.setValue("12345678");

    final Unreserved value = new Unreserved();
    value.setGloballyUniqueIdentifier("A011223344998877");
    value.addContextSpecificData(contextSpecificData);

    final UnreservedTemplate unreserved = new UnreservedTemplate();
    unreserved.setValue(value);
    unreserved.setTag("80");
    return unreserved;
  }

  private MerchantInformationLanguageTemplate getMerchantInformationLanguage() {

    final TagLengthString rFUforEMVCo = new TagLengthString();
    rFUforEMVCo.setTag("03");
    rFUforEMVCo.setValue("abcd");

    final MerchantInformationLanguage merchantInformationLanguageValue = new MerchantInformationLanguage();
    merchantInformationLanguageValue.setLanguagePreference("ZH");
    merchantInformationLanguageValue.setMerchantName("北京");
    merchantInformationLanguageValue.setMerchantCity("最佳运输");
    merchantInformationLanguageValue.addRFUforEMVCo(rFUforEMVCo);

    final MerchantInformationLanguageTemplate merchantInformationLanguage = new MerchantInformationLanguageTemplate();
    merchantInformationLanguage.setValue(merchantInformationLanguageValue);
    return merchantInformationLanguage;
  }

  private AdditionalDataFieldTemplate getAddtionalDataField() {

    final PaymentSystemSpecific paymentSystemSpecific = new PaymentSystemSpecific();
    paymentSystemSpecific.setGloballyUniqueIdentifier("1234");
    paymentSystemSpecific.addPaymentSystemSpecific(new TagLengthString("01", "ijkl"));

    final PaymentSystemSpecificTemplate paymentSystemSpecificTemplate = new PaymentSystemSpecificTemplate();
    paymentSystemSpecificTemplate.setTag("50");
    paymentSystemSpecificTemplate.setValue(paymentSystemSpecific);

    final TagLengthString rFUforEMVCo = new TagLengthString();
    rFUforEMVCo.setTag("10");
    rFUforEMVCo.setValue("abcd");

    final AdditionalDataField additionalDataFieldValue = new AdditionalDataField();
    additionalDataFieldValue.setAdditionalConsumerDataRequest("tuv");
    additionalDataFieldValue.setMobileNumber("67890");
    additionalDataFieldValue.setPurposeTransaction("pqres");
    additionalDataFieldValue.setReferenceLabel("abcde");
    additionalDataFieldValue.setStoreLabel("09876");
    additionalDataFieldValue.setTerminalLabel("klmno");
    additionalDataFieldValue.addPaymentSystemSpecific(paymentSystemSpecificTemplate);
    additionalDataFieldValue.addRFUforEMVCo(rFUforEMVCo);

    final AdditionalDataFieldTemplate additionalDataField = new AdditionalDataFieldTemplate();
    additionalDataField.setValue(additionalDataFieldValue);
    return additionalDataField;
  }

}
