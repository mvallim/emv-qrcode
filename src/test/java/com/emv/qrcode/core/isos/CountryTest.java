package com.emv.qrcode.core.isos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CountryTest {

  @Test
  public void testSuccessToString() {
    assertThat(Country.BR.toString(), equalTo("BR"));
  }

  @Test
  public void testSuccessEntryOf() {
    assertThat(Country.entryOf("BR"), equalTo(Country.BR));
    assertThat(Country.entryOf("br"), equalTo(Country.BR));
    assertThat(Country.entryOf("bR"), equalTo(Country.BR));
  }

  @Test
  public void testFailEntryOf() {
    assertThat(Country.entryOf(""), nullValue());
    assertThat(Country.entryOf(null), nullValue());
    assertThat(Country.entryOf("--"), nullValue());
  }

  @Test
  public void testSuccessExists() {
    assertThat(Country.exists("BR"), equalTo(true));
    assertThat(Country.exists("br"), equalTo(true));
    assertThat(Country.exists("bR"), equalTo(true));
  }

  @Test
  public void testFailExists() {
    assertThat(Country.exists(""), equalTo(false));
    assertThat(Country.exists(null), equalTo(false));
    assertThat(Country.exists("--"), equalTo(false));
  }

}
