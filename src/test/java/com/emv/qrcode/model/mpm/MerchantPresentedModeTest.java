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

package com.emv.qrcode.model.mpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.core.model.mpm.TagLengthString;
import com.emv.qrcode.decoder.mpm.DecoderMpm;

// @formatter:off
public class MerchantPresentedModeTest {

  @Test
  public void testSuccessToString() {

    final AdditionalDataFieldTemplate additionalDataField = getAddtionalDataField();
    final MerchantAccountInformationTemplate merchanAccountInformationReservedAdditional = getMerchanAccountInformationReservedAdditional();
    final MerchantAccountInformationTemplate merchanAccountInformationReserved = getMerchanAccountInformationReserved();
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
    merchantPresentMode.setTipOrConvenienceIndicator("01");
    merchantPresentMode.setTransactionAmount("23.72");
    merchantPresentMode.setTransactionCurrency("156");
    merchantPresentMode.setValueOfConvenienceFeeFixed("500");
    merchantPresentMode.setValueOfConvenienceFeePercentage("5");
    merchantPresentMode.addMerchantAccountInformation(merchanAccountInformationReserved);
    merchantPresentMode.addMerchantAccountInformation(merchanAccountInformationReservedAdditional);
    merchantPresentMode.addRFUforEMVCo(rFUforEMVCo);
    merchantPresentMode.addUnreserved(unreserved);

    assertThat(merchantPresentMode.toString(), equalTo("0002010102110204000426160004"
        + "hoge0104abcd520441115303156540523.725502015603500570155802CN5914BEST TRAN"
        + "SPORT6007BEIJING6107123456762950105123450205678900305098760405543210505ab"
        + "cde0605fghij0705klmno0805pqres0905tuvxy5010000110101i64280002ZH0102北京020"
        + "4最佳运输0304abcd65020080320016A011223344998877070812345678630432B3"));

  }

  // Primitive Payment System Merchant Account Information (IDs "02" to "25")
  private MerchantAccountInformationTemplate getMerchanAccountInformationReserved() {

    final MerchantAccountInformationReserved merchantAccountInformationValue = new MerchantAccountInformationReserved("0004");

    return new MerchantAccountInformationTemplate("02", merchantAccountInformationValue);
  }

  // Merchant Account Information Template (IDs "26" to "51")
  private MerchantAccountInformationTemplate getMerchanAccountInformationReservedAdditional() {

    final TagLengthString paymentNetworkSpecific = new TagLengthString();
    paymentNetworkSpecific.setTag("01");
    paymentNetworkSpecific.setValue("abcd");

    final MerchantAccountInformationReservedAdditional merchantAccountInformationValue = new MerchantAccountInformationReservedAdditional();
    merchantAccountInformationValue.setGloballyUniqueIdentifier("hoge");
    merchantAccountInformationValue.addPaymentNetworkSpecific(paymentNetworkSpecific);

    return new MerchantAccountInformationTemplate("26", merchantAccountInformationValue);
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
    paymentSystemSpecific.setGloballyUniqueIdentifier("1");
    paymentSystemSpecific.addPaymentSystemSpecific(new TagLengthString("01", "i"));

    final PaymentSystemSpecificTemplate paymentSystemSpecificTemplate = new PaymentSystemSpecificTemplate();
    paymentSystemSpecificTemplate.setTag("50");
    paymentSystemSpecificTemplate.setValue(paymentSystemSpecific);

    final AdditionalDataField additionalDataFieldValue = new AdditionalDataField();
    additionalDataFieldValue.setAdditionalConsumerDataRequest("tuvxy");
    additionalDataFieldValue.setBillNumber("12345");
    additionalDataFieldValue.setCustomerLabel("fghij");
    additionalDataFieldValue.setLoyaltyNumber("54321");
    additionalDataFieldValue.setMobileNumber("67890");
    additionalDataFieldValue.setPurposeTransaction("pqres");
    additionalDataFieldValue.setReferenceLabel("abcde");
    additionalDataFieldValue.setStoreLabel("09876");
    additionalDataFieldValue.setTerminalLabel("klmno");
    additionalDataFieldValue.addPaymentSystemSpecific(paymentSystemSpecificTemplate);

    final AdditionalDataFieldTemplate additionalDataField = new AdditionalDataFieldTemplate();
    additionalDataField.setValue(additionalDataFieldValue);

    return additionalDataField;
  }

  @Test
  public void testSuccessToStringWhenValueIsNull() {

    final MerchantPresentedMode merchantPresentMode = new MerchantPresentedMode();
    merchantPresentMode.setAdditionalDataField(null);
    merchantPresentMode.setCountryCode(null);
    merchantPresentMode.setCRC(null);
    merchantPresentMode.setMerchantCategoryCode(null);
    merchantPresentMode.setMerchantCity(null);
    merchantPresentMode.setMerchantInformationLanguage(null);
    merchantPresentMode.setMerchantName(null);
    merchantPresentMode.setPayloadFormatIndicator(null);
    merchantPresentMode.setPointOfInitiationMethod(null);
    merchantPresentMode.setPostalCode(null);
    merchantPresentMode.setTipOrConvenienceIndicator(null);
    merchantPresentMode.setTransactionAmount(null);
    merchantPresentMode.setTransactionCurrency(null);
    merchantPresentMode.setValueOfConvenienceFeeFixed(null);
    merchantPresentMode.setValueOfConvenienceFeePercentage(null);

    assertThat(merchantPresentMode.toString(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToStringWhenValueIsEmpty() {
    final MerchantPresentedMode merchantPresentMode = new MerchantPresentedMode();

    merchantPresentMode.setAdditionalDataField(new AdditionalDataFieldTemplate());
    merchantPresentMode.setCountryCode(StringUtils.EMPTY);
    merchantPresentMode.setCRC(StringUtils.EMPTY);
    merchantPresentMode.setMerchantCategoryCode(StringUtils.EMPTY);
    merchantPresentMode.setMerchantCity(StringUtils.EMPTY);
    merchantPresentMode.setMerchantInformationLanguage(new MerchantInformationLanguageTemplate());
    merchantPresentMode.setMerchantName(StringUtils.EMPTY);
    merchantPresentMode.setPayloadFormatIndicator(StringUtils.EMPTY);
    merchantPresentMode.setPointOfInitiationMethod(StringUtils.EMPTY);
    merchantPresentMode.setPostalCode(StringUtils.EMPTY);
    merchantPresentMode.setTipOrConvenienceIndicator(StringUtils.EMPTY);
    merchantPresentMode.setTransactionAmount(StringUtils.EMPTY);
    merchantPresentMode.setTransactionCurrency(StringUtils.EMPTY);
    merchantPresentMode.setValueOfConvenienceFeeFixed(StringUtils.EMPTY);
    merchantPresentMode.setValueOfConvenienceFeePercentage(StringUtils.EMPTY);
    merchantPresentMode.addMerchantAccountInformation(new MerchantAccountInformationTemplate());
    merchantPresentMode.addRFUforEMVCo(new TagLengthString());
    merchantPresentMode.addUnreserved(new UnreservedTemplate());

    assertThat(merchantPresentMode.toString(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToBase64() throws PresentedModeException {
    final String encoded = "00020101021102160004hoge0104abcd5204411153031565405"
        + "23.725502015603500570155802CN5914BEST TRANSPORT6007BEIJING6107123456"
        + "762950105123450205678900305098760405543210505abcde0605fghij0705klmno"
        + "0805pqres0905tuvxy5010000110101i64280002ZH0102北京0204最佳运输0304abcd"
        + "65020080320016A01122334499887707081234567863044220";

    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode(encoded, MerchantPresentedMode.class);

    assertThat(merchantPresentMode.toBase64(), equalTo("MDAwMjAxMDEwMjExMDIxNjA"
        + "wMDRob2dlMDEwNGFiY2Q1MjA0NDExMTUzMDMxNTY1NDA1MjMuNzI1NTAyMDE1NjAzNTA"
        + "wNTcwMTU1ODAyQ041OTE0QkVTVCBUUkFOU1BPUlQ2MDA3QkVJSklORzYxMDcxMjM0NTY"
        + "3NjI5NTAxMDUxMjM0NTAyMDU2Nzg5MDAzMDUwOTg3NjA0MDU1NDMyMTA1MDVhYmNkZTA"
        + "2MDVmZ2hpajA3MDVrbG1ubzA4MDVwcXJlczA5MDV0dXZ4eTUwMTAwMDAxMTAxMDFpNjQ"
        + "yODAwMDJaSDAxMDLljJfkuqwwMjA05pyA5L2z6L+Q6L6TMDMwNGFiY2Q2NTAyMDA4MDM"
        + "yMDAxNkEwMTEyMjMzNDQ5OTg4NzcwNzA4MTIzNDU2Nzg2MzA0NDIyMA=="));

    assertThat(new String(Base64.decodeBase64(merchantPresentMode.toBase64()), StandardCharsets.UTF_8), equalTo(merchantPresentMode.toString()));

  }

  @Test
  public void testSuccessToBase64WhenValueIsNull() {

    final MerchantPresentedMode merchantPresentMode = new MerchantPresentedMode();
    merchantPresentMode.setAdditionalDataField(null);
    merchantPresentMode.setCountryCode(null);
    merchantPresentMode.setCRC(null);
    merchantPresentMode.setMerchantCategoryCode(null);
    merchantPresentMode.setMerchantCity(null);
    merchantPresentMode.setMerchantInformationLanguage(null);
    merchantPresentMode.setMerchantName(null);
    merchantPresentMode.setPayloadFormatIndicator(null);
    merchantPresentMode.setPointOfInitiationMethod(null);
    merchantPresentMode.setPostalCode(null);
    merchantPresentMode.setTipOrConvenienceIndicator(null);
    merchantPresentMode.setTransactionAmount(null);
    merchantPresentMode.setTransactionCurrency(null);
    merchantPresentMode.setValueOfConvenienceFeeFixed(null);
    merchantPresentMode.setValueOfConvenienceFeePercentage(null);

    assertThat(merchantPresentMode.toBase64(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToBase64WhenValueIsEmpty() {
    final MerchantPresentedMode merchantPresentMode = new MerchantPresentedMode();

    merchantPresentMode.setAdditionalDataField(new AdditionalDataFieldTemplate());
    merchantPresentMode.setCountryCode(StringUtils.EMPTY);
    merchantPresentMode.setCRC(StringUtils.EMPTY);
    merchantPresentMode.setMerchantCategoryCode(StringUtils.EMPTY);
    merchantPresentMode.setMerchantCity(StringUtils.EMPTY);
    merchantPresentMode.setMerchantInformationLanguage(new MerchantInformationLanguageTemplate());
    merchantPresentMode.setMerchantName(StringUtils.EMPTY);
    merchantPresentMode.setPayloadFormatIndicator(StringUtils.EMPTY);
    merchantPresentMode.setPointOfInitiationMethod(StringUtils.EMPTY);
    merchantPresentMode.setPostalCode(StringUtils.EMPTY);
    merchantPresentMode.setTipOrConvenienceIndicator(StringUtils.EMPTY);
    merchantPresentMode.setTransactionAmount(StringUtils.EMPTY);
    merchantPresentMode.setTransactionCurrency(StringUtils.EMPTY);
    merchantPresentMode.setValueOfConvenienceFeeFixed(StringUtils.EMPTY);
    merchantPresentMode.setValueOfConvenienceFeePercentage(StringUtils.EMPTY);
    merchantPresentMode.addMerchantAccountInformation(new MerchantAccountInformationTemplate());
    merchantPresentMode.addRFUforEMVCo(new TagLengthString());
    merchantPresentMode.addUnreserved(new UnreservedTemplate());

    assertThat(merchantPresentMode.toBase64(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToHex() throws DecoderException, PresentedModeException {
    final String encoded = "00020101021102160004hoge0104abcd520441115303156540523.72"
        + "5502015603500570155802CN5914BEST TRANSPORT6007BEIJING61071234567629501051"
        + "23450205678900305098760405543210505abcde0605fghij0705klmno0805pqres0905tu"
        + "vxy5010000110101i64280002ZH0102北京0204最佳运输0304abcd65020080320016A01122"
        + "334499887707081234567863044220";

    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode(encoded, MerchantPresentedMode.class);

    assertThat(merchantPresentMode.toHex(), equalTo("3030303230313031303231313032313"
        + "630303034686F676530313034616263643532303434313131353330333135363534303532"
        + "332E373235353032303135363033353030353730313535383032434E35393134424553542"
        + "05452414E53504F5254363030374245494A494E4736313037313233343536373632393530"
        + "3130353132333435303230353637383930303330353039383736303430353534333231303"
        + "53035616263646530363035666768696A303730356B6C6D6E6F3038303570717265733039"
        + "30357475767879353031303030303131303130316936343238303030325A4830313032E58"
        + "C97E4BAAC30323034E69C80E4BDB3E8BF90E8BE9330333034616263643635303230303830"
        + "3332303031364130313132323333343439393838373730373038313233343536373836333"
        + "03434323230"));

    assertThat(new String(Hex.decodeHex(merchantPresentMode.toHex()), StandardCharsets.UTF_8), equalTo(merchantPresentMode.toString()));

  }

  @Test
  public void testSuccessToHexWhenValueIsNull() {

    final MerchantPresentedMode merchantPresentMode = new MerchantPresentedMode();
    merchantPresentMode.setAdditionalDataField(null);
    merchantPresentMode.setCountryCode(null);
    merchantPresentMode.setCRC(null);
    merchantPresentMode.setMerchantCategoryCode(null);
    merchantPresentMode.setMerchantCity(null);
    merchantPresentMode.setMerchantInformationLanguage(null);
    merchantPresentMode.setMerchantName(null);
    merchantPresentMode.setPayloadFormatIndicator(null);
    merchantPresentMode.setPointOfInitiationMethod(null);
    merchantPresentMode.setPostalCode(null);
    merchantPresentMode.setTipOrConvenienceIndicator(null);
    merchantPresentMode.setTransactionAmount(null);
    merchantPresentMode.setTransactionCurrency(null);
    merchantPresentMode.setValueOfConvenienceFeeFixed(null);
    merchantPresentMode.setValueOfConvenienceFeePercentage(null);

    assertThat(merchantPresentMode.toHex(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToHexWhenValueIsEmpty() {
    final MerchantPresentedMode merchantPresentMode = new MerchantPresentedMode();

    merchantPresentMode.setAdditionalDataField(new AdditionalDataFieldTemplate());
    merchantPresentMode.setCountryCode(StringUtils.EMPTY);
    merchantPresentMode.setCRC(StringUtils.EMPTY);
    merchantPresentMode.setMerchantCategoryCode(StringUtils.EMPTY);
    merchantPresentMode.setMerchantCity(StringUtils.EMPTY);
    merchantPresentMode.setMerchantInformationLanguage(new MerchantInformationLanguageTemplate());
    merchantPresentMode.setMerchantName(StringUtils.EMPTY);
    merchantPresentMode.setPayloadFormatIndicator(StringUtils.EMPTY);
    merchantPresentMode.setPointOfInitiationMethod(StringUtils.EMPTY);
    merchantPresentMode.setPostalCode(StringUtils.EMPTY);
    merchantPresentMode.setTipOrConvenienceIndicator(StringUtils.EMPTY);
    merchantPresentMode.setTransactionAmount(StringUtils.EMPTY);
    merchantPresentMode.setTransactionCurrency(StringUtils.EMPTY);
    merchantPresentMode.setValueOfConvenienceFeeFixed(StringUtils.EMPTY);
    merchantPresentMode.setValueOfConvenienceFeePercentage(StringUtils.EMPTY);
    merchantPresentMode.addMerchantAccountInformation(new MerchantAccountInformationTemplate());
    merchantPresentMode.addRFUforEMVCo(new TagLengthString());
    merchantPresentMode.addUnreserved(new UnreservedTemplate());

    assertThat(merchantPresentMode.toHex(), equalTo(StringUtils.EMPTY));
  }

}
// @formatter:on
