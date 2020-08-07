package com.emv.qrcode.core.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class BERTLStringTest {

  @Test
  public void testSuccessToHex() throws IOException {
    final BERTLString bertlString = new BERTLString();

    bertlString.setTag(2);
    bertlString.setValue("1234");

    assertThat(bertlString.toHex(), equalTo("020431323334"));
    assertThat(new BERTLString(2, "1234").toHex(), equalTo("020431323334"));

  }

  @Test
  public void testSuccessToStringWhenValueIsNull() throws IOException {
    final BERTLString bertlString = new BERTLString();

    bertlString.setTag(2);
    bertlString.setValue(null);

    assertThat(bertlString.toHex(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToStringWhenValueIsEmpty() throws IOException {
    final BERTLString bertlString = new BERTLString();

    bertlString.setTag(2);
    bertlString.setValue(StringUtils.EMPTY);

    assertThat(bertlString.toHex(), equalTo(StringUtils.EMPTY));
  }

}
