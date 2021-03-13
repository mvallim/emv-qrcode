package com.emv.qrcode.model.cpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.junit.Test;

public class PayloadFormatIndicatorTest {

  @Test
  public void testSuccessToHex() throws IOException {

    final PayloadFormatIndicator payloadFormatIndicator = new PayloadFormatIndicator();

    assertThat(payloadFormatIndicator.toHex(), equalTo("85054350563031"));

  }

  @Test
  public void testSuccessToHexWhenSetTagIsUsed() throws IOException {

    final PayloadFormatIndicator payloadFormatIndicator = new PayloadFormatIndicator();

    payloadFormatIndicator.setTag(0x0);

    assertThat(payloadFormatIndicator.toHex(), equalTo("00054350563031"));

  }

  @Test
  public void testSuccessToHexWhenSetValueIsUsed() throws IOException {

    final PayloadFormatIndicator payloadFormatIndicator = new PayloadFormatIndicator();

    payloadFormatIndicator.setValue("whatever");

    assertThat(payloadFormatIndicator.toHex(), equalTo("85087768617465766572"));

  }

}
