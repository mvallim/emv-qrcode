package com.emv.qrcode.core.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class TagLengthStringTest {

  @Test
  public void testSuccessToString() {
    final TagLengthString tagLengthString = new TagLengthString();

    tagLengthString.setTag("02");
    tagLengthString.setValue("1234");

    assertThat(tagLengthString.toString(), equalTo("02041234"));
    assertThat(new TagLengthString("02", "1234").toString(), equalTo("02041234"));
  }

  @Test
  public void testSuccessToStringWhenValueIsNull() {
    final TagLengthString tagLengthString = new TagLengthString();

    tagLengthString.setTag("02");
    tagLengthString.setValue(null);

    assertThat(tagLengthString.toString(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToStringWhenValueIsEmpty() {
    final TagLengthString tagLengthString = new TagLengthString();

    tagLengthString.setTag("02");
    tagLengthString.setValue(StringUtils.EMPTY);

    assertThat(tagLengthString.toString(), equalTo(StringUtils.EMPTY));
  }

}
