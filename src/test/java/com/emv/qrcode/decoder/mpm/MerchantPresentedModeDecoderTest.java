package com.emv.qrcode.decoder.mpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.emv.qrcode.decoder.DecoderMpm;
import com.emv.qrcode.model.mpm.MerchantPresentedMode;

public class MerchantPresentedModeDecoderTest {

  @Test
  public void testSuccessDecodePayloadFormatIndicator() {
    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode("000201", MerchantPresentedMode.class);

    assertThat(merchantPresentMode, not(nullValue()));
    assertThat(merchantPresentMode.getPayloadFormatIndicator(), not(nullValue()));
    assertThat(merchantPresentMode.getPayloadFormatIndicator().getTag(), equalTo("00"));
    assertThat(merchantPresentMode.getPayloadFormatIndicator().getLength(), equalTo(02));
    assertThat(merchantPresentMode.getPayloadFormatIndicator().getValue(), equalTo("01"));
  }

  @Test
  public void testSuccessDecodePointOfInitiationMethod() {
    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode("010211", MerchantPresentedMode.class);

    assertThat(merchantPresentMode, not(nullValue()));
    assertThat(merchantPresentMode.getPointOfInitiationMethod(), not(nullValue()));
    assertThat(merchantPresentMode.getPointOfInitiationMethod().getTag(), equalTo("01"));
    assertThat(merchantPresentMode.getPointOfInitiationMethod().getLength(), equalTo(02));
    assertThat(merchantPresentMode.getPointOfInitiationMethod().getValue(), equalTo("11"));
  }

  @Test
  public void testSuccessDecodeMerchantCategoryCode() {
    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode("52044111", MerchantPresentedMode.class);

    assertThat(merchantPresentMode, not(nullValue()));
    assertThat(merchantPresentMode.getMerchantCategoryCode(), not(nullValue()));
    assertThat(merchantPresentMode.getMerchantCategoryCode().getTag(), equalTo("52"));
    assertThat(merchantPresentMode.getMerchantCategoryCode().getLength(), equalTo(04));
    assertThat(merchantPresentMode.getMerchantCategoryCode().getValue(), equalTo("4111"));
  }

  @Test
  public void testSuccessDecodeTransactionCurrency() {
    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode("5303156", MerchantPresentedMode.class);

    assertThat(merchantPresentMode, not(nullValue()));
    assertThat(merchantPresentMode.getTransactionCurrency(), not(nullValue()));
    assertThat(merchantPresentMode.getTransactionCurrency().getTag(), equalTo("53"));
    assertThat(merchantPresentMode.getTransactionCurrency().getLength(), equalTo(03));
    assertThat(merchantPresentMode.getTransactionCurrency().getValue(), equalTo("156"));
  }

  @Test
  public void testSuccessDecodeTransactionAmount() {
    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode("540523.72", MerchantPresentedMode.class);

    assertThat(merchantPresentMode, not(nullValue()));
    assertThat(merchantPresentMode.getTransactionAmount(), not(nullValue()));
    assertThat(merchantPresentMode.getTransactionAmount().getTag(), equalTo("54"));
    assertThat(merchantPresentMode.getTransactionAmount().getLength(), equalTo(05));
    assertThat(merchantPresentMode.getTransactionAmount().getValue(), equalTo("23.72"));
  }

  @Test
  public void testSuccessDecodeTipOrConvenienceIndicator() {
    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode("550201", MerchantPresentedMode.class);

    assertThat(merchantPresentMode, not(nullValue()));
    assertThat(merchantPresentMode.getTipOrConvenienceIndicator(), not(nullValue()));
    assertThat(merchantPresentMode.getTipOrConvenienceIndicator().getTag(), equalTo("55"));
    assertThat(merchantPresentMode.getTipOrConvenienceIndicator().getLength(), equalTo(02));
    assertThat(merchantPresentMode.getTipOrConvenienceIndicator().getValue(), equalTo("01"));
  }

  @Test
  public void testSuccessDecodeValueOfConvenienceFeeFixed() {
    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode("5603500", MerchantPresentedMode.class);

    assertThat(merchantPresentMode, not(nullValue()));
    assertThat(merchantPresentMode.getValueOfConvenienceFeeFixed(), not(nullValue()));
    assertThat(merchantPresentMode.getValueOfConvenienceFeeFixed().getTag(), equalTo("56"));
    assertThat(merchantPresentMode.getValueOfConvenienceFeeFixed().getLength(), equalTo(03));
    assertThat(merchantPresentMode.getValueOfConvenienceFeeFixed().getValue(), equalTo("500"));
  }

  @Test
  public void testSuccessDecodeValueOfConvenienceFeePercentage() {
    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode("57015", MerchantPresentedMode.class);

    assertThat(merchantPresentMode, not(nullValue()));
    assertThat(merchantPresentMode.getValueOfConvenienceFeePercentage(), not(nullValue()));
    assertThat(merchantPresentMode.getValueOfConvenienceFeePercentage().getTag(), equalTo("57"));
    assertThat(merchantPresentMode.getValueOfConvenienceFeePercentage().getLength(), equalTo(01));
    assertThat(merchantPresentMode.getValueOfConvenienceFeePercentage().getValue(), equalTo("5"));
  }

  @Test
  public void testSuccessDecodeCountryCode() {
    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode("5802CN", MerchantPresentedMode.class);

    assertThat(merchantPresentMode, not(nullValue()));
    assertThat(merchantPresentMode.getCountryCode(), not(nullValue()));
    assertThat(merchantPresentMode.getCountryCode().getTag(), equalTo("58"));
    assertThat(merchantPresentMode.getCountryCode().getLength(), equalTo(02));
    assertThat(merchantPresentMode.getCountryCode().getValue(), equalTo("CN"));
  }

  @Test
  public void testSuccessDecodeMerchantName() {
    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode("5914BEST TRANSPORT", MerchantPresentedMode.class);

    assertThat(merchantPresentMode, not(nullValue()));
    assertThat(merchantPresentMode.getMerchantName(), not(nullValue()));
    assertThat(merchantPresentMode.getMerchantName().getTag(), equalTo("59"));
    assertThat(merchantPresentMode.getMerchantName().getLength(), equalTo(14));
    assertThat(merchantPresentMode.getMerchantName().getValue(), equalTo("BEST TRANSPORT"));
  }

  @Test
  public void testSuccessDecodeMerchantCity() {
    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode("6007BEIJING", MerchantPresentedMode.class);

    assertThat(merchantPresentMode, not(nullValue()));
    assertThat(merchantPresentMode.getMerchantCity(), not(nullValue()));
    assertThat(merchantPresentMode.getMerchantCity().getTag(), equalTo("60"));
    assertThat(merchantPresentMode.getMerchantCity().getLength(), equalTo(07));
    assertThat(merchantPresentMode.getMerchantCity().getValue(), equalTo("BEIJING"));
  }

  @Test
  public void testSuccessDecodePostalCode() {
    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode("61071234567", MerchantPresentedMode.class);

    assertThat(merchantPresentMode, not(nullValue()));
    assertThat(merchantPresentMode.getPostalCode(), not(nullValue()));
    assertThat(merchantPresentMode.getPostalCode().getTag(), equalTo("61"));
    assertThat(merchantPresentMode.getPostalCode().getLength(), equalTo(07));
    assertThat(merchantPresentMode.getPostalCode().getValue(), equalTo("1234567"));
  }

  @Test
  public void testSuccessDecodeCRC() {
    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode("6304A13A", MerchantPresentedMode.class);

    assertThat(merchantPresentMode, not(nullValue()));
    assertThat(merchantPresentMode.getCRC(), not(nullValue()));
    assertThat(merchantPresentMode.getCRC().getTag(), equalTo("63"));
    assertThat(merchantPresentMode.getCRC().getLength(), equalTo(04));
    assertThat(merchantPresentMode.getCRC().getValue(), equalTo("A13A"));
  }

  @Test
  public void testSuccessDecodeAdditionalDataFieldTemplate() {
    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode("6233030412340603***0708A60086670902ME",
        MerchantPresentedMode.class);

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
    assertThat(merchantPresentMode.getAdditionalDataField().getValue().getTerminalLabel().getValue(),
        equalTo("A6008667"));

    assertThat(merchantPresentMode.getAdditionalDataField().getValue().getAdditionalConsumerDataRequest(),
        not(nullValue()));
    assertThat(merchantPresentMode.getAdditionalDataField().getValue().getAdditionalConsumerDataRequest().getTag(),
        equalTo("09"));
    assertThat(merchantPresentMode.getAdditionalDataField().getValue().getAdditionalConsumerDataRequest().getLength(),
        equalTo(02));
    assertThat(merchantPresentMode.getAdditionalDataField().getValue().getAdditionalConsumerDataRequest().getValue(),
        equalTo("ME"));

  }

  @Test
  public void testSuccessDecodeMerchantInformationLanguageTemplate() {
    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode("64200002ZH0104最佳运输0202北京",
        MerchantPresentedMode.class);

    assertThat(merchantPresentMode, not(nullValue()));
    assertThat(merchantPresentMode.getMerchantInformationLanguage(), not(nullValue()));
    assertThat(merchantPresentMode.getMerchantInformationLanguage().getTag(), equalTo("64"));
    assertThat(merchantPresentMode.getMerchantInformationLanguage().getLength(), equalTo(20));
    assertThat(merchantPresentMode.getMerchantInformationLanguage().getValue(), not(nullValue()));

    assertThat(merchantPresentMode.getMerchantInformationLanguage().getValue().getLanguagePreference(),
        not(nullValue()));
    assertThat(merchantPresentMode.getMerchantInformationLanguage().getValue().getLanguagePreference().getTag(),
        equalTo("00"));
    assertThat(merchantPresentMode.getMerchantInformationLanguage().getValue().getLanguagePreference().getLength(),
        equalTo(02));
    assertThat(merchantPresentMode.getMerchantInformationLanguage().getValue().getLanguagePreference().getValue(),
        equalTo("ZH"));

    assertThat(merchantPresentMode.getMerchantInformationLanguage().getValue().getMerchantName(), not(nullValue()));
    assertThat(merchantPresentMode.getMerchantInformationLanguage().getValue().getMerchantName().getTag(),
        equalTo("01"));
    assertThat(merchantPresentMode.getMerchantInformationLanguage().getValue().getMerchantName().getLength(),
        equalTo(04));
    assertThat(merchantPresentMode.getMerchantInformationLanguage().getValue().getMerchantName().getValue(),
        equalTo("最佳运输"));

    assertThat(merchantPresentMode.getMerchantInformationLanguage().getValue().getMerchantCity(), not(nullValue()));
    assertThat(merchantPresentMode.getMerchantInformationLanguage().getValue().getMerchantCity().getTag(),
        equalTo("02"));
    assertThat(merchantPresentMode.getMerchantInformationLanguage().getValue().getMerchantCity().getLength(),
        equalTo(2));
    assertThat(merchantPresentMode.getMerchantInformationLanguage().getValue().getMerchantCity().getValue(),
        equalTo("北京"));
  }

  @Test
  public void testeSuccessToString() {
    final String encoded = "00020101021102160004hoge0104abcd520441115303156540523.725502015603500570155802CN5914BEST TRANSPORT6007BEIJING6107123456762950105123450205678900305098760405543210505abcde0605fghij0705klmno0805pqres0905tuvxy5010000110101i64280002ZH0102北京0204最佳运输0304abcd65020080320016A01122334499887707081234567863044220";

    final MerchantPresentedMode merchantPresentMode = DecoderMpm.decode(encoded, MerchantPresentedMode.class);

    assertThat(merchantPresentMode.toString().length(), equalTo(301));
    assertThat(merchantPresentMode.toString(), equalTo("00020101021102160004hoge0104abcd520441115303156540523.725502015603500570155802CN5914BEST TRANSPORT6007BEIJING6107123456762950105123450205678900305098760405543210505abcde0605fghij0705klmno0805pqres0905tuvxy5010000110101i64280002ZH0102北京0204最佳运输0304abcd65020080320016A01122334499887707081234567863044220"));
  }

}
