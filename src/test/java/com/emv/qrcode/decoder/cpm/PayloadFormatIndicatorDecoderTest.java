package com.emv.qrcode.decoder.cpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.junit.Test;

import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.model.cpm.PayloadFormatIndicator;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;

public class PayloadFormatIndicatorDecoderTest {

  @Test
  public void testSuccessDecode() throws PresentedModeException, IOException {

    // 8505CPV01

    final byte[] source = { (byte) 0x85, 0x05, 0x43, 0x50, 0x56, 0x30, 0x31 };

    final PayloadFormatIndicator payloadFormatIndicator = DecoderCpm.decode(source, PayloadFormatIndicator.class);

    assertThat(payloadFormatIndicator, not(nullValue()));

    assertThat(payloadFormatIndicator.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_PAYLOAD_FORMAT_INDICATOR));
    assertThat(payloadFormatIndicator.getLength(), equalTo(5));
    assertThat(payloadFormatIndicator.getBytes(), equalTo(source));
    assertThat(payloadFormatIndicator.getStringValue(), equalTo("CPV01"));
  }

  @Test
  public void testSuccessDecodeEncode() throws PresentedModeException, IOException {
    final byte[] source = { (byte) 0x85, 0x05, 0x43, 0x50, 0x56, 0x30, 0x31 };
    final PayloadFormatIndicator payloadFormatIndicator = DecoderCpm.decode(source, PayloadFormatIndicator.class);
    assertThat(payloadFormatIndicator.getBytes(), equalTo(source));
  }

}
