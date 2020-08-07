package com.emv.qrcode.model.cpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class PayloadFormatIndicatorTest {

  @Test
  public void testSuccessToHex() throws IOException {
    final PayloadFormatIndicator payloadFormatIndicator = new PayloadFormatIndicator();

    payloadFormatIndicator.setValue("CPV01");

    assertThat(payloadFormatIndicator.toHex(), equalTo("85054350563031"));
  }

  @Test
  public void testSuccessToHexWhenValueIsNull() throws IOException {

    final PayloadFormatIndicator unreserved = new PayloadFormatIndicator();
    unreserved.setValue(null);

    assertThat(unreserved.toHex(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToHexWhenValueIsEmpty() throws IOException {

    final PayloadFormatIndicator unreserved = new PayloadFormatIndicator();
    unreserved.setValue("");

    assertThat(unreserved.toHex(), equalTo(StringUtils.EMPTY));
  }

}
