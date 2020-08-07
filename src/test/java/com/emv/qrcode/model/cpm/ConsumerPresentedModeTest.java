package com.emv.qrcode.model.cpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class ConsumerPresentedModeTest {

  @Test
  public void testSuccessToHex() throws IOException {
    final ConsumerPresentedMode consumerPresentedMode = new ConsumerPresentedMode();

    consumerPresentedMode.setPayloadFormatIndicator(new PayloadFormatIndicator("CPV01"));
    consumerPresentedMode.addApplicationTemplate(new ApplicationTemplate());
    consumerPresentedMode.addCommonDataTemplate(new CommonDataTemplate());

    assertThat(consumerPresentedMode.toHex(), equalTo("85054350563031"));
  }

  @Test
  public void testSuccessToHexWhenValueIsNull() throws IOException {

    final ConsumerPresentedMode consumerPresentedMode = new ConsumerPresentedMode();

    consumerPresentedMode.setPayloadFormatIndicator(null);

    assertThat(consumerPresentedMode.toHex(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToHexWhenValueIsEmpty() throws IOException {

    final ConsumerPresentedMode consumerPresentedMode = new ConsumerPresentedMode();

    consumerPresentedMode.setPayloadFormatIndicator(new PayloadFormatIndicator(""));

    assertThat(consumerPresentedMode.toHex(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToBase64() throws IOException {
    final ConsumerPresentedMode consumerPresentedMode = new ConsumerPresentedMode();

    consumerPresentedMode.setPayloadFormatIndicator(new PayloadFormatIndicator("CPV01"));
    consumerPresentedMode.addApplicationTemplate(new ApplicationTemplate());
    consumerPresentedMode.addCommonDataTemplate(new CommonDataTemplate());

    assertThat(consumerPresentedMode.toBase64(), equalTo("hQVDUFYwMQ=="));
  }

  @Test
  public void testSuccessToBase64WhenValueIsNull() throws IOException {

    final ConsumerPresentedMode consumerPresentedMode = new ConsumerPresentedMode();

    consumerPresentedMode.setPayloadFormatIndicator(null);

    assertThat(consumerPresentedMode.toBase64(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToBase64WhenValueIsEmpty() throws IOException {

    final ConsumerPresentedMode consumerPresentedMode = new ConsumerPresentedMode();

    consumerPresentedMode.setPayloadFormatIndicator(new PayloadFormatIndicator(""));

    assertThat(consumerPresentedMode.toBase64(), equalTo(StringUtils.EMPTY));
  }

}
