package com.emv.qrcode.core.isos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CurrencyTest {

  @Test
  public void testSuccessToString() {
    assertThat(Currency.BRL.toString(), equalTo("BRL"));
  }

  @Test
  public void testSuccessEntryOf() {
    assertThat(Currency.entryOf("BRL"), equalTo(Currency.BRL));
    assertThat(Currency.entryOf("brl"), equalTo(Currency.BRL));
    assertThat(Currency.entryOf("bRl"), equalTo(Currency.BRL));
  }

  @Test
  public void testFailEntryOf() {
    assertThat(Currency.entryOf(""), nullValue());
    assertThat(Currency.entryOf(null), nullValue());
    assertThat(Currency.entryOf("---"), nullValue());
  }

  @Test
  public void testSuccessExists() {
    assertThat(Currency.exists("BRL"), equalTo(true));
    assertThat(Currency.exists("brl"), equalTo(true));
    assertThat(Currency.exists("bRl"), equalTo(true));
  }

  @Test
  public void testFailExists() {
    assertThat(Currency.exists(""), equalTo(false));
    assertThat(Currency.exists(null), equalTo(false));
    assertThat(Currency.exists("---"), equalTo(false));
  }

}
