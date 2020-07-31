package com.emv.qrcode.parsers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.emv.qrcode.mpm.model.EMVQR;

public class ParserTest {

  @Test
  public void testSuccessParsePayloadFormatIndicator() {
    final EMVQR emvqr = Parser.parse("000201", EMVQR.class);

    assertThat(emvqr, not(nullValue()));
    assertThat(emvqr.getPayloadFormatIndicator(), not(nullValue()));
    assertThat(emvqr.getPayloadFormatIndicator().getTag(), equalTo("00"));
    assertThat(emvqr.getPayloadFormatIndicator().getLength(), equalTo(2));
    assertThat(emvqr.getPayloadFormatIndicator().getValue(), equalTo("01"));
  }

  @Test
  public void testSuccessPointOfInitiationMethod() {
    final EMVQR emvqr = Parser.parse("010211", EMVQR.class);

    assertThat(emvqr, not(nullValue()));
    assertThat(emvqr.getPointOfInitiationMethod(), not(nullValue()));
    assertThat(emvqr.getPointOfInitiationMethod().getTag(), equalTo("01"));
    assertThat(emvqr.getPointOfInitiationMethod().getLength(), equalTo(2));
    assertThat(emvqr.getPointOfInitiationMethod().getValue(), equalTo("11"));
  }

  @Test
  public void testSuccessMerchantCategoryCode() {
    final EMVQR emvqr = Parser.parse("52044111", EMVQR.class);

    assertThat(emvqr, not(nullValue()));
    assertThat(emvqr.getMerchantCategoryCode(), not(nullValue()));
    assertThat(emvqr.getMerchantCategoryCode().getTag(), equalTo("52"));
    assertThat(emvqr.getMerchantCategoryCode().getLength(), equalTo(4));
    assertThat(emvqr.getMerchantCategoryCode().getValue(), equalTo("4111"));
  }

}
