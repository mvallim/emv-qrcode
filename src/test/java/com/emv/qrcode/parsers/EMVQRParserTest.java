package com.emv.qrcode.parsers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.emv.qrcode.mpm.model.EMVQR;

public class EMVQRParserTest {

  @Test
  public void testSuccessParsePayloadFormatIndicator() {
    final EMVQR emvqr = Parser.parse("000201", EMVQR.class);

    assertThat(emvqr, not(nullValue()));
    assertThat(emvqr.getPayloadFormatIndicator(), not(nullValue()));
    assertThat(emvqr.getPayloadFormatIndicator().getTag(), equalTo("00"));
    assertThat(emvqr.getPayloadFormatIndicator().getLength(), equalTo(02));
    assertThat(emvqr.getPayloadFormatIndicator().getValue(), equalTo("01"));
  }

  @Test
  public void testSuccessParsePointOfInitiationMethod() {
    final EMVQR emvqr = Parser.parse("010211", EMVQR.class);

    assertThat(emvqr, not(nullValue()));
    assertThat(emvqr.getPointOfInitiationMethod(), not(nullValue()));
    assertThat(emvqr.getPointOfInitiationMethod().getTag(), equalTo("01"));
    assertThat(emvqr.getPointOfInitiationMethod().getLength(), equalTo(02));
    assertThat(emvqr.getPointOfInitiationMethod().getValue(), equalTo("11"));
  }

  @Test
  public void testSuccessParseMerchantCategoryCode() {
    final EMVQR emvqr = Parser.parse("52044111", EMVQR.class);

    assertThat(emvqr, not(nullValue()));
    assertThat(emvqr.getMerchantCategoryCode(), not(nullValue()));
    assertThat(emvqr.getMerchantCategoryCode().getTag(), equalTo("52"));
    assertThat(emvqr.getMerchantCategoryCode().getLength(), equalTo(04));
    assertThat(emvqr.getMerchantCategoryCode().getValue(), equalTo("4111"));
  }

  @Test
  public void testSuccessParseTransactionCurrency() {
    final EMVQR emvqr = Parser.parse("5303156", EMVQR.class);

    assertThat(emvqr, not(nullValue()));
    assertThat(emvqr.getTransactionCurrency(), not(nullValue()));
    assertThat(emvqr.getTransactionCurrency().getTag(), equalTo("53"));
    assertThat(emvqr.getTransactionCurrency().getLength(), equalTo(03));
    assertThat(emvqr.getTransactionCurrency().getValue(), equalTo("156"));
  }

  @Test
  public void testSuccessParseTransactionAmount() {
    final EMVQR emvqr = Parser.parse("540523.72", EMVQR.class);

    assertThat(emvqr, not(nullValue()));
    assertThat(emvqr.getTransactionAmount(), not(nullValue()));
    assertThat(emvqr.getTransactionAmount().getTag(), equalTo("54"));
    assertThat(emvqr.getTransactionAmount().getLength(), equalTo(05));
    assertThat(emvqr.getTransactionAmount().getValue(), equalTo("23.72"));
  }

  @Test
  public void testSuccessParseTipOrConvenienceIndicator() {
    final EMVQR emvqr = Parser.parse("550201", EMVQR.class);

    assertThat(emvqr, not(nullValue()));
    assertThat(emvqr.getTipOrConvenienceIndicator(), not(nullValue()));
    assertThat(emvqr.getTipOrConvenienceIndicator().getTag(), equalTo("55"));
    assertThat(emvqr.getTipOrConvenienceIndicator().getLength(), equalTo(02));
    assertThat(emvqr.getTipOrConvenienceIndicator().getValue(), equalTo("01"));
  }

  @Test
  public void testSuccessParseValueOfConvenienceFeeFixed() {
    final EMVQR emvqr = Parser.parse("5603500", EMVQR.class);

    assertThat(emvqr, not(nullValue()));
    assertThat(emvqr.getValueOfConvenienceFeeFixed(), not(nullValue()));
    assertThat(emvqr.getValueOfConvenienceFeeFixed().getTag(), equalTo("56"));
    assertThat(emvqr.getValueOfConvenienceFeeFixed().getLength(), equalTo(03));
    assertThat(emvqr.getValueOfConvenienceFeeFixed().getValue(), equalTo("500"));
  }

  @Test
  public void testSuccessParseValueOfConvenienceFeePercentage() {
    final EMVQR emvqr = Parser.parse("57015", EMVQR.class);

    assertThat(emvqr, not(nullValue()));
    assertThat(emvqr.getValueOfConvenienceFeePercentage(), not(nullValue()));
    assertThat(emvqr.getValueOfConvenienceFeePercentage().getTag(), equalTo("57"));
    assertThat(emvqr.getValueOfConvenienceFeePercentage().getLength(), equalTo(01));
    assertThat(emvqr.getValueOfConvenienceFeePercentage().getValue(), equalTo("5"));
  }

  @Test
  public void testSuccessParseCountryCode() {
    final EMVQR emvqr = Parser.parse("5802CN", EMVQR.class);

    assertThat(emvqr, not(nullValue()));
    assertThat(emvqr.getCountryCode(), not(nullValue()));
    assertThat(emvqr.getCountryCode().getTag(), equalTo("58"));
    assertThat(emvqr.getCountryCode().getLength(), equalTo(02));
    assertThat(emvqr.getCountryCode().getValue(), equalTo("CN"));
  }

  @Test
  public void testSuccessParseMerchantName() {
    final EMVQR emvqr = Parser.parse("5914BEST TRANSPORT", EMVQR.class);

    assertThat(emvqr, not(nullValue()));
    assertThat(emvqr.getMerchantName(), not(nullValue()));
    assertThat(emvqr.getMerchantName().getTag(), equalTo("59"));
    assertThat(emvqr.getMerchantName().getLength(), equalTo(14));
    assertThat(emvqr.getMerchantName().getValue(), equalTo("BEST TRANSPORT"));
  }

  @Test
  public void testSuccessParseMerchantCity() {
    final EMVQR emvqr = Parser.parse("6007BEIJING", EMVQR.class);

    assertThat(emvqr, not(nullValue()));
    assertThat(emvqr.getMerchantCity(), not(nullValue()));
    assertThat(emvqr.getMerchantCity().getTag(), equalTo("60"));
    assertThat(emvqr.getMerchantCity().getLength(), equalTo(07));
    assertThat(emvqr.getMerchantCity().getValue(), equalTo("BEIJING"));
  }

  @Test
  public void testSuccessParsePostalCode() {
    final EMVQR emvqr = Parser.parse("61071234567", EMVQR.class);

    assertThat(emvqr, not(nullValue()));
    assertThat(emvqr.getPostalCode(), not(nullValue()));
    assertThat(emvqr.getPostalCode().getTag(), equalTo("61"));
    assertThat(emvqr.getPostalCode().getLength(), equalTo(07));
    assertThat(emvqr.getPostalCode().getValue(), equalTo("1234567"));
  }

  @Test
  public void testSuccessParseCRC() {
    final EMVQR emvqr = Parser.parse("6304A13A", EMVQR.class);

    assertThat(emvqr, not(nullValue()));
    assertThat(emvqr.getCRC(), not(nullValue()));
    assertThat(emvqr.getCRC().getTag(), equalTo("63"));
    assertThat(emvqr.getCRC().getLength(), equalTo(04));
    assertThat(emvqr.getCRC().getValue(), equalTo("A13A"));
  }

  @Test
  public void testSuccessParseAdditionalDataFieldTemplate() {
    final EMVQR emvqr = Parser.parse("6233030412340603***0708A60086670902ME", EMVQR.class);

    assertThat(emvqr, not(nullValue()));
    assertThat(emvqr.getAdditionalDataFieldTemplate(), not(nullValue()));

    assertThat(emvqr.getAdditionalDataFieldTemplate().getStoreLabel(), not(nullValue()));
    assertThat(emvqr.getAdditionalDataFieldTemplate().getStoreLabel().getTag(), equalTo("03"));
    assertThat(emvqr.getAdditionalDataFieldTemplate().getStoreLabel().getLength(), equalTo(04));
    assertThat(emvqr.getAdditionalDataFieldTemplate().getStoreLabel().getValue(), equalTo("1234"));

    assertThat(emvqr.getAdditionalDataFieldTemplate().getCustomerLabel(), not(nullValue()));
    assertThat(emvqr.getAdditionalDataFieldTemplate().getCustomerLabel().getTag(), equalTo("06"));
    assertThat(emvqr.getAdditionalDataFieldTemplate().getCustomerLabel().getLength(), equalTo(03));
    assertThat(emvqr.getAdditionalDataFieldTemplate().getCustomerLabel().getValue(), equalTo("***"));

    assertThat(emvqr.getAdditionalDataFieldTemplate().getTerminalLabel(), not(nullValue()));
    assertThat(emvqr.getAdditionalDataFieldTemplate().getTerminalLabel().getTag(), equalTo("07"));
    assertThat(emvqr.getAdditionalDataFieldTemplate().getTerminalLabel().getLength(), equalTo(8));
    assertThat(emvqr.getAdditionalDataFieldTemplate().getTerminalLabel().getValue(), equalTo("A6008667"));

    assertThat(emvqr.getAdditionalDataFieldTemplate().getAdditionalConsumerDataRequest(), not(nullValue()));
    assertThat(emvqr.getAdditionalDataFieldTemplate().getAdditionalConsumerDataRequest().getTag(), equalTo("09"));
    assertThat(emvqr.getAdditionalDataFieldTemplate().getAdditionalConsumerDataRequest().getLength(), equalTo(02));
    assertThat(emvqr.getAdditionalDataFieldTemplate().getAdditionalConsumerDataRequest().getValue(), equalTo("ME"));

  }

  @Test
  public void testSuccessParseMerchantInformationLanguageTemplate() {
    final EMVQR emvqr = Parser.parse("64200002ZH0104最佳运输0202北京", EMVQR.class);

    assertThat(emvqr, not(nullValue()));
    assertThat(emvqr.getMerchantInformationLanguageTemplate(), not(nullValue()));

    assertThat(emvqr.getMerchantInformationLanguageTemplate().getLanguagePreference(), not(nullValue()));
    assertThat(emvqr.getMerchantInformationLanguageTemplate().getLanguagePreference().getTag(), equalTo("00"));
    assertThat(emvqr.getMerchantInformationLanguageTemplate().getLanguagePreference().getLength(), equalTo(02));
    assertThat(emvqr.getMerchantInformationLanguageTemplate().getLanguagePreference().getValue(), equalTo("ZH"));

    assertThat(emvqr.getMerchantInformationLanguageTemplate().getMerchantName(), not(nullValue()));
    assertThat(emvqr.getMerchantInformationLanguageTemplate().getMerchantName().getTag(), equalTo("01"));
    assertThat(emvqr.getMerchantInformationLanguageTemplate().getMerchantName().getLength(), equalTo(04));
    assertThat(emvqr.getMerchantInformationLanguageTemplate().getMerchantName().getValue(), equalTo("最佳运输"));

    assertThat(emvqr.getMerchantInformationLanguageTemplate().getMerchantCity(), not(nullValue()));
    assertThat(emvqr.getMerchantInformationLanguageTemplate().getMerchantCity().getTag(), equalTo("02"));
    assertThat(emvqr.getMerchantInformationLanguageTemplate().getMerchantCity().getLength(), equalTo(2));
    assertThat(emvqr.getMerchantInformationLanguageTemplate().getMerchantCity().getValue(), equalTo("北京"));

    System.out.println(emvqr.rawData());
    System.out.println(emvqr.binaryData());
  }

  @Test
  public void test() {
    final EMVQR emvqr = Parser.parse("000201010211520441115303156540523.725502015603500570155802CN5914BEST TRANSPORT6007BEIJING610712345676304A13A6233030412340603***0708A60086670902ME64200002ZH0104最佳运输0202北京", EMVQR.class);

    System.out.println(emvqr.toString());
    System.out.println(emvqr.rawData());
    System.out.println(emvqr.binaryData());

  }

}
