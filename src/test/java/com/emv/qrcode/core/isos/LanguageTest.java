package com.emv.qrcode.core.isos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class LanguageTest {

  @Test
  public void testSuccessToString() {
    assertThat(Language.PT.toString(), equalTo("PT"));
  }

  @Test
  public void testSuccessEntryOf() {
    assertThat(Language.entryOf("PT"), equalTo(Language.PT));
    assertThat(Language.entryOf("pt"), equalTo(Language.PT));
    assertThat(Language.entryOf("Pt"), equalTo(Language.PT));
  }

  @Test
  public void testFailEntryOf() {
    assertThat(Language.entryOf(""), nullValue());
    assertThat(Language.entryOf(null), nullValue());
    assertThat(Language.entryOf("--"), nullValue());
  }

  @Test
  public void testSuccessExists() {
    assertThat(Language.exists("PT"), equalTo(true));
    assertThat(Language.exists("pt"), equalTo(true));
    assertThat(Language.exists("pT"), equalTo(true));
  }

  @Test
  public void testFailExists() {
    assertThat(Language.exists(""), equalTo(false));
    assertThat(Language.exists(null), equalTo(false));
    assertThat(Language.exists("--"), equalTo(false));
  }

}
