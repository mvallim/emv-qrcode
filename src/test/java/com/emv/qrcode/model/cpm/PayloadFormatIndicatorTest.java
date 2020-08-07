package com.emv.qrcode.model.cpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Test;

public class PayloadFormatIndicatorTest {

  @Test
  public void testSuccessToHex() throws IOException {

    final PayloadFormatIndicator payloadFormatIndicator = new PayloadFormatIndicator();

    assertThat(payloadFormatIndicator.toHex(), equalTo("85054350563031"));

  }

}
