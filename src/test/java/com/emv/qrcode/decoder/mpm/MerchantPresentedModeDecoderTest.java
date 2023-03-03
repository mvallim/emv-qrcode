/*
 * Copyright 2019 the original author or authors.
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

package com.emv.qrcode.decoder.mpm;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Test;

import com.emv.qrcode.core.exception.DuplicateTagException;
import com.emv.qrcode.core.exception.InvalidTagException;
import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.model.mpm.MerchantPresentedMode;

public class MerchantPresentedModeDecoderTest {

  @Test
  public void testSuccessDecodePayloadFormatIndicator() throws PresentedModeException {
    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode("000201", MerchantPresentedMode.class);

    assertThat(merchantPresentMode, not(nullValue()));
    assertThat(merchantPresentMode.getPayloadFormatIndicator(), not(nullValue()));
    assertThat(merchantPresentMode.getPayloadFormatIndicator().getTag(), equalTo("00"));
    assertThat(merchantPresentMode.getPayloadFormatIndicator().getLength(), equalTo(02));
    assertThat(merchantPresentMode.getPayloadFormatIndicator().getValue(), equalTo("01"));
  }

  @Test
  public void testSuccessDecodePointOfInitiationMethod() throws PresentedModeException {
    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode("010211", MerchantPresentedMode.class);

    assertThat(merchantPresentMode, not(nullValue()));
    assertThat(merchantPresentMode.getPointOfInitiationMethod(), not(nullValue()));
    assertThat(merchantPresentMode.getPointOfInitiationMethod().getTag(), equalTo("01"));
    assertThat(merchantPresentMode.getPointOfInitiationMethod().getLength(), equalTo(02));
    assertThat(merchantPresentMode.getPointOfInitiationMethod().getValue(), equalTo("11"));
  }

  @Test
  public void testSuccessDecodeMerchantCategoryCode() throws PresentedModeException {
    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode("52044111", MerchantPresentedMode.class);

    assertThat(merchantPresentMode, not(nullValue()));
    assertThat(merchantPresentMode.getMerchantCategoryCode(), not(nullValue()));
    assertThat(merchantPresentMode.getMerchantCategoryCode().getTag(), equalTo("52"));
    assertThat(merchantPresentMode.getMerchantCategoryCode().getLength(), equalTo(04));
    assertThat(merchantPresentMode.getMerchantCategoryCode().getValue(), equalTo("4111"));
  }

  @Test
  public void testSuccessDecodeTransactionCurrency() throws PresentedModeException {
    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode("5303156", MerchantPresentedMode.class);

    assertThat(merchantPresentMode, not(nullValue()));
    assertThat(merchantPresentMode.getTransactionCurrency(), not(nullValue()));
    assertThat(merchantPresentMode.getTransactionCurrency().getTag(), equalTo("53"));
    assertThat(merchantPresentMode.getTransactionCurrency().getLength(), equalTo(03));
    assertThat(merchantPresentMode.getTransactionCurrency().getValue(), equalTo("156"));
  }

  @Test
  public void testSuccessDecodeTransactionAmount() throws PresentedModeException {
    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode("540523.72", MerchantPresentedMode.class);

    assertThat(merchantPresentMode, not(nullValue()));
    assertThat(merchantPresentMode.getTransactionAmount(), not(nullValue()));
    assertThat(merchantPresentMode.getTransactionAmount().getTag(), equalTo("54"));
    assertThat(merchantPresentMode.getTransactionAmount().getLength(), equalTo(05));
    assertThat(merchantPresentMode.getTransactionAmount().getValue(), equalTo("23.72"));
  }

  @Test
  public void testSuccessDecodeTipOrConvenienceIndicator() throws PresentedModeException {
    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode("550201", MerchantPresentedMode.class);

    assertThat(merchantPresentMode, not(nullValue()));
    assertThat(merchantPresentMode.getTipOrConvenienceIndicator(), not(nullValue()));
    assertThat(merchantPresentMode.getTipOrConvenienceIndicator().getTag(), equalTo("55"));
    assertThat(merchantPresentMode.getTipOrConvenienceIndicator().getLength(), equalTo(02));
    assertThat(merchantPresentMode.getTipOrConvenienceIndicator().getValue(), equalTo("01"));
  }

  @Test
  public void testSuccessDecodeValueOfConvenienceFeeFixed() throws PresentedModeException {
    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode("5603500", MerchantPresentedMode.class);

    assertThat(merchantPresentMode, not(nullValue()));
    assertThat(merchantPresentMode.getValueOfConvenienceFeeFixed(), not(nullValue()));
    assertThat(merchantPresentMode.getValueOfConvenienceFeeFixed().getTag(), equalTo("56"));
    assertThat(merchantPresentMode.getValueOfConvenienceFeeFixed().getLength(), equalTo(03));
    assertThat(merchantPresentMode.getValueOfConvenienceFeeFixed().getValue(), equalTo("500"));
  }

  @Test
  public void testSuccessDecodeValueOfConvenienceFeePercentage() throws PresentedModeException {
    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode("57015", MerchantPresentedMode.class);

    assertThat(merchantPresentMode, not(nullValue()));
    assertThat(merchantPresentMode.getValueOfConvenienceFeePercentage(), not(nullValue()));
    assertThat(merchantPresentMode.getValueOfConvenienceFeePercentage().getTag(), equalTo("57"));
    assertThat(merchantPresentMode.getValueOfConvenienceFeePercentage().getLength(), equalTo(01));
    assertThat(merchantPresentMode.getValueOfConvenienceFeePercentage().getValue(), equalTo("5"));
  }

  @Test
  public void testSuccessDecodeCountryCode() throws PresentedModeException {
    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode("5802CN", MerchantPresentedMode.class);

    assertThat(merchantPresentMode, not(nullValue()));
    assertThat(merchantPresentMode.getCountryCode(), not(nullValue()));
    assertThat(merchantPresentMode.getCountryCode().getTag(), equalTo("58"));
    assertThat(merchantPresentMode.getCountryCode().getLength(), equalTo(02));
    assertThat(merchantPresentMode.getCountryCode().getValue(), equalTo("CN"));
  }

  @Test
  public void testSuccessDecodeMerchantName() throws PresentedModeException {
    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode("5914BEST TRANSPORT", MerchantPresentedMode.class);

    assertThat(merchantPresentMode, not(nullValue()));
    assertThat(merchantPresentMode.getMerchantName(), not(nullValue()));
    assertThat(merchantPresentMode.getMerchantName().getTag(), equalTo("59"));
    assertThat(merchantPresentMode.getMerchantName().getLength(), equalTo(14));
    assertThat(merchantPresentMode.getMerchantName().getValue(), equalTo("BEST TRANSPORT"));
  }

  @Test
  public void testSuccessDecodeMerchantCity() throws PresentedModeException {
    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode("6007BEIJING", MerchantPresentedMode.class);

    assertThat(merchantPresentMode, not(nullValue()));
    assertThat(merchantPresentMode.getMerchantCity(), not(nullValue()));
    assertThat(merchantPresentMode.getMerchantCity().getTag(), equalTo("60"));
    assertThat(merchantPresentMode.getMerchantCity().getLength(), equalTo(07));
    assertThat(merchantPresentMode.getMerchantCity().getValue(), equalTo("BEIJING"));
  }

  @Test
  public void testSuccessDecodePostalCode() throws PresentedModeException {
    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode("61071234567", MerchantPresentedMode.class);

    assertThat(merchantPresentMode, not(nullValue()));
    assertThat(merchantPresentMode.getPostalCode(), not(nullValue()));
    assertThat(merchantPresentMode.getPostalCode().getTag(), equalTo("61"));
    assertThat(merchantPresentMode.getPostalCode().getLength(), equalTo(07));
    assertThat(merchantPresentMode.getPostalCode().getValue(), equalTo("1234567"));
  }

  @Test
  public void testSuccessDecodeCRC() throws PresentedModeException {
    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode("6304A13A", MerchantPresentedMode.class);

    assertThat(merchantPresentMode, not(nullValue()));
    assertThat(merchantPresentMode.getCRC(), not(nullValue()));
    assertThat(merchantPresentMode.getCRC().getTag(), equalTo("63"));
    assertThat(merchantPresentMode.getCRC().getLength(), equalTo(04));
    assertThat(merchantPresentMode.getCRC().getValue(), equalTo("A13A"));
  }

  @Test
  public void testSuccessDecodeAdditionalDataFieldTemplate() throws PresentedModeException {
    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode("6233030412340603***0708A60086670902ME", MerchantPresentedMode.class);

    assertThat(merchantPresentMode, not(nullValue()));

    assertThat(merchantPresentMode.getAdditionalDataField(), not(nullValue()));
    assertThat(merchantPresentMode.getAdditionalDataField().getTag(), equalTo("62"));
    assertThat(merchantPresentMode.getAdditionalDataField().getLength(), equalTo(33));
    assertThat(merchantPresentMode.getAdditionalDataField().getValue(), not(nullValue()));

    assertThat(merchantPresentMode.getAdditionalDataField().getValue().getStoreLabel(), not(nullValue()));
    assertThat(merchantPresentMode.getAdditionalDataField().getValue().getStoreLabel().getTag(), equalTo("03"));
    assertThat(merchantPresentMode.getAdditionalDataField().getValue().getStoreLabel().getLength(), equalTo(04));
    assertThat(merchantPresentMode.getAdditionalDataField().getValue().getStoreLabel().getValue(), equalTo("1234"));

    assertThat(merchantPresentMode.getAdditionalDataField().getValue().getCustomerLabel(), not(nullValue()));
    assertThat(merchantPresentMode.getAdditionalDataField().getValue().getCustomerLabel().getTag(), equalTo("06"));
    assertThat(merchantPresentMode.getAdditionalDataField().getValue().getCustomerLabel().getLength(), equalTo(03));
    assertThat(merchantPresentMode.getAdditionalDataField().getValue().getCustomerLabel().getValue(), equalTo("***"));

    assertThat(merchantPresentMode.getAdditionalDataField().getValue().getTerminalLabel(), not(nullValue()));
    assertThat(merchantPresentMode.getAdditionalDataField().getValue().getTerminalLabel().getTag(), equalTo("07"));
    assertThat(merchantPresentMode.getAdditionalDataField().getValue().getTerminalLabel().getLength(), equalTo(8));
    assertThat(merchantPresentMode.getAdditionalDataField().getValue().getTerminalLabel().getValue(), equalTo("A6008667"));

    assertThat(merchantPresentMode.getAdditionalDataField().getValue().getAdditionalConsumerDataRequest(), not(nullValue()));
    assertThat(merchantPresentMode.getAdditionalDataField().getValue().getAdditionalConsumerDataRequest().getTag(), equalTo("09"));
    assertThat(merchantPresentMode.getAdditionalDataField().getValue().getAdditionalConsumerDataRequest().getLength(), equalTo(02));
    assertThat(merchantPresentMode.getAdditionalDataField().getValue().getAdditionalConsumerDataRequest().getValue(), equalTo("ME"));

  }

  @Test
  public void testSuccessDecodeMerchantInformationLanguageTemplate() throws PresentedModeException {
    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode("64200002ZH0104最佳运输0202北京", MerchantPresentedMode.class);

    assertThat(merchantPresentMode, not(nullValue()));
    assertThat(merchantPresentMode.getMerchantInformationLanguage(), not(nullValue()));
    assertThat(merchantPresentMode.getMerchantInformationLanguage().getTag(), equalTo("64"));
    assertThat(merchantPresentMode.getMerchantInformationLanguage().getLength(), equalTo(20));
    assertThat(merchantPresentMode.getMerchantInformationLanguage().getValue(), not(nullValue()));

    assertThat(merchantPresentMode.getMerchantInformationLanguage().getValue().getLanguagePreference(), not(nullValue()));
    assertThat(merchantPresentMode.getMerchantInformationLanguage().getValue().getLanguagePreference().getTag(), equalTo("00"));
    assertThat(merchantPresentMode.getMerchantInformationLanguage().getValue().getLanguagePreference().getLength(), equalTo(02));
    assertThat(merchantPresentMode.getMerchantInformationLanguage().getValue().getLanguagePreference().getValue(), equalTo("ZH"));

    assertThat(merchantPresentMode.getMerchantInformationLanguage().getValue().getMerchantName(), not(nullValue()));
    assertThat(merchantPresentMode.getMerchantInformationLanguage().getValue().getMerchantName().getTag(), equalTo("01"));
    assertThat(merchantPresentMode.getMerchantInformationLanguage().getValue().getMerchantName().getLength(), equalTo(04));
    assertThat(merchantPresentMode.getMerchantInformationLanguage().getValue().getMerchantName().getValue(), equalTo("最佳运输"));

    assertThat(merchantPresentMode.getMerchantInformationLanguage().getValue().getMerchantCity(), not(nullValue()));
    assertThat(merchantPresentMode.getMerchantInformationLanguage().getValue().getMerchantCity().getTag(), equalTo("02"));
    assertThat(merchantPresentMode.getMerchantInformationLanguage().getValue().getMerchantCity().getLength(), equalTo(2));
    assertThat(merchantPresentMode.getMerchantInformationLanguage().getValue().getMerchantCity().getValue(), equalTo("北京"));
  }

  @Test
  public void testeSuccessToString() throws PresentedModeException {
    final String encoded = "00020101021102160004hoge0104abcd520441115303156540523.725502015603500570155802CN5914BEST TRANSPORT6007BEIJING6107123456762950105123450205678900305098760405543210505abcde0605fghij0705klmno0805pqres0905tuvxy5010000110101i64280002ZH0102北京0204最佳运输0304abcd65020080320016A01122334499887707081234567863044220";

    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode(encoded, MerchantPresentedMode.class);

    assertThat(merchantPresentMode.toString().length(), equalTo(301));
    assertThat(merchantPresentMode.toString(), equalTo(
        "00020101021102160004hoge0104abcd520441115303156540523.725502015603500570155802CN5914BEST TRANSPORT6007BEIJING6107123456762950105123450205678900305098760405543210505abcde0605fghij0705klmno0805pqres0905tuvxy5010000110101i64280002ZH0102北京0204最佳运输0304abcd65020080320016A01122334499887707081234567863044220"));
  }

  @Test
  public void testeSuccessToStringWithoutCRC() throws PresentedModeException {
    final String encoded = "00020101021102160004hoge0104abcd520441115303156540523.725502015603500570155802CN5914BEST TRANSPORT6007BEIJING6107123456762950105123450205678900305098760405543210505abcde0605fghij0705klmno0805pqres0905tuvxy5010000110101i64280002ZH0102北京0204最佳运输0304abcd65020080320016A011223344998877070812345678";

    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode(encoded, MerchantPresentedMode.class);

    assertThat(merchantPresentMode.toString().length(), equalTo(301));
    assertThat(merchantPresentMode.toString(), equalTo(
        "00020101021102160004hoge0104abcd520441115303156540523.725502015603500570155802CN5914BEST TRANSPORT6007BEIJING6107123456762950105123450205678900305098760405543210505abcde0605fghij0705klmno0805pqres0905tuvxy5010000110101i64280002ZH0102北京0204最佳运输0304abcd65020080320016A01122334499887707081234567863044220"));
  }

  @Test
  public void testFailDecode() throws PresentedModeException {
    final String encoded1 = "00020101021102160004hoge0104abcd5204411153031565303156540523.725502015603500570155802CN5914BEST TRANSPORT6007BEIJING6107123456762950105123450205678900305098760405543210505abcde0605fghij0705klmno0805pqres0905tuvxy5010000110101i64280002ZH0102北京0204最佳运输0304abcd65020080320016A011223344998877070812345678";
    final DuplicateTagException duplicateTagException = catchThrowableOfType(() -> DecoderMpm.decode(encoded1, MerchantPresentedMode.class), DuplicateTagException.class);
    assertThat(duplicateTagException.getTag(), equalTo("53"));
    assertThat(duplicateTagException.getValue(), equalTo("5303156"));
    assertThat(duplicateTagException.getMessage(), equalTo("Scope: 'MerchantPresentedMode' informed already contains '53' tag"));

    final String encoded2 = "00020101021102160004hoge0104abcd52044111AA031565303156540523.725502015603500570155802CN5914BEST TRANSPORT6007BEIJING6107123456762950105123450205678900305098760405543210505abcde0605fghij0705klmno0805pqres0905tuvxy5010000110101i64280002ZH0102北京0204最佳运输0304abcd65020080320016A011223344998877070812345678";
    final InvalidTagException invalidTagException = catchThrowableOfType(() -> DecoderMpm.decode(encoded2, MerchantPresentedMode.class), InvalidTagException.class);
    assertThat(invalidTagException.getTag(), equalTo("AA"));
    assertThat(invalidTagException.getValue(), equalTo("AA03156"));
    assertThat(invalidTagException.getMessage(), equalTo("Scope: 'MerchantPresentedMode' invalid 'AA' tag"));
  }

  @Test
  public void testeBrCode() throws PresentedModeException {
    final String encoded1 = "00020126570014BR.GOV.BCB.PIX0114607011900001040217Mensagem opcional52040000530398654041.225802BR5920Teste Batch atualiza6009SAO PAULO622605221t9OGEuUWlqWQiY0CU2YmA63048179";
    final String encoded2 = "00020126830014br.gov.bcb.pix01364004901d-bd85-4769-8e52-cb4c42c506dc0221Jornada pagador 57768520400005303986540573.625802BR5903Pix62080504oooo63048E87";
    final String encoded3 = "00020126830014br.gov.bcb.pix01364004901d-bd85-4769-8e52-cb4c42c506dc0221Jornada pagador 57768520400005303986540573.625802BR5903Pix6016Sao Joao del Rei62080504oooo63044682";
    final String encoded4 = "00020104141234567890123426580014BR.GOV.BCB.PIX0136123e4567-e12b-12d1-a456-42665544000027300012BR.COM.OUTRO011001234567895204000053039865406123.455802BR5917NOME DO RECEBEDOR6008BRASILIA61087007490080390012BR.COM.OUTRO01190123.ABCD.3456.WXYZ6304549C";
    final String encoded5 = "00020126830014br.gov.bcb.pix01364004901d-bd85-4769-8e52-cb4c42c506dc0221Jornada pagador 57768520400005303986540105802BR5903Pix6008BRASILIA62080504oooo63041F70";

    assertThatCode(() -> DecoderMpm.decode(encoded1, MerchantPresentedMode.class)).doesNotThrowAnyException();
    assertThatCode(() -> DecoderMpm.decode(encoded2, MerchantPresentedMode.class)).doesNotThrowAnyException();
    assertThatCode(() -> DecoderMpm.decode(encoded3, MerchantPresentedMode.class)).doesNotThrowAnyException();
    assertThatCode(() -> DecoderMpm.decode(encoded4, MerchantPresentedMode.class)).doesNotThrowAnyException();
    assertThatCode(() -> DecoderMpm.decode(encoded5, MerchantPresentedMode.class)).doesNotThrowAnyException();
  }

}
