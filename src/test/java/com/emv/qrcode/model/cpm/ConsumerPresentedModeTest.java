/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.emv.qrcode.model.cpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.emv.qrcode.core.model.cpm.BERTemplate;

// @formatter:off
public class ConsumerPresentedModeTest {

  @Test
  public void testSuccessToHex() throws IOException {
    final ConsumerPresentedMode consumerPresentedMode = new ConsumerPresentedMode();

    consumerPresentedMode.setPayloadFormatIndicator(new PayloadFormatIndicator());
    consumerPresentedMode.addApplicationTemplate(new ApplicationTemplate());
    consumerPresentedMode.setCommonDataTemplate(new CommonDataTemplate());
    consumerPresentedMode.addOtherTemplate(new OtherTemplate());

    assertThat(consumerPresentedMode.toHex(), equalTo("85054350563031"));
  }

  @Test
  public void testSuccessToHexWithApplicationTemplate() throws IOException {
    final ConsumerPresentedMode consumerPresentedMode = new ConsumerPresentedMode();

    final ApplicationTemplate applicationTemplate1 = new ApplicationTemplate();
    applicationTemplate1.setApplicationDefinitionFileName("A0000000555555");
    applicationTemplate1.setApplicationLabel("Product1");

    final ApplicationTemplate applicationTemplate2 = new ApplicationTemplate();
    applicationTemplate2.setApplicationDefinitionFileName("A0000000666666");
    applicationTemplate2.setApplicationLabel("Product2");

    final CommonDataTransparentTemplate commonDataTransparentTemplate = new CommonDataTransparentTemplate();
    commonDataTransparentTemplate.setIssuerApplicationData("06010A03000000");
    commonDataTransparentTemplate.setApplicationCryptogram("584FD385FA234BCC");
    commonDataTransparentTemplate.setApplicationTransactionCounter("0001");
    commonDataTransparentTemplate.setUnpredictableNumber("6D58EF13");

    final CommonDataTemplate commonDataTemplate = new CommonDataTemplate();
    commonDataTemplate.setApplicationPAN("1234567890123458");
    commonDataTemplate.setCardholderName("CARDHOLDER/EMV");
    commonDataTemplate.setLanguagePreference("ruesdeen");
    commonDataTemplate.setCommonDataTransparentTemplate(commonDataTransparentTemplate);

    consumerPresentedMode.setPayloadFormatIndicator(new PayloadFormatIndicator());
    consumerPresentedMode.addApplicationTemplate(applicationTemplate1);
    consumerPresentedMode.addApplicationTemplate(applicationTemplate2);
    consumerPresentedMode.setCommonDataTemplate(commonDataTemplate);

    assertThat(consumerPresentedMode.toHex(), equalTo("8505435056303161134F07A000000"
        + "0555555500850726F647563743161134F07A0000000666666500850726F64756374326249"
        + "5A0812345678901234585F200E43415244484F4C4445522F454D565F2D087275657364656"
        + "56E64219F100706010A030000009F2608584FD385FA234BCC9F360200019F37046D58EF13"));

    assertThat(consumerPresentedMode.toBase64(), equalTo(
          "hQVDUFYwMWETTwegAAAAVVVVUAhQcm9kdWN0MWETTwegAAAAZmZmUAhQcm9kdWN0MmJJWggSN"
        + "FZ4kBI0WF8gDkNBUkRIT0xERVIvRU1WXy0IcnVlc2RlZW5kIZ8QBwYBCgMAAACfJghYT9OF+i"
        + "NLzJ82AgABnzcEbVjvEw=="));
  }

  @Test
  public void testSuccessToBase64WithApplicationTemplate() throws IOException {
    final ConsumerPresentedMode consumerPresentedMode = new ConsumerPresentedMode();

    final ApplicationTemplate applicationTemplate1 = new ApplicationTemplate();
    applicationTemplate1.setApplicationDefinitionFileName("A0000000555555");
    applicationTemplate1.setApplicationLabel("Product1");

    final ApplicationTemplate applicationTemplate2 = new ApplicationTemplate();
    applicationTemplate2.setApplicationDefinitionFileName("A0000000666666");
    applicationTemplate2.setApplicationLabel("Product2");

    final CommonDataTransparentTemplate commonDataTransparentTemplate = new CommonDataTransparentTemplate();
    commonDataTransparentTemplate.setIssuerApplicationData("06010A03000000");
    commonDataTransparentTemplate.setApplicationCryptogram("584FD385FA234BCC");
    commonDataTransparentTemplate.setApplicationTransactionCounter("0001");
    commonDataTransparentTemplate.setUnpredictableNumber("6D58EF13");

    final CommonDataTemplate commonDataTemplate = new CommonDataTemplate();
    commonDataTemplate.setApplicationPAN("1234567890123458");
    commonDataTemplate.setCardholderName("CARDHOLDER/EMV");
    commonDataTemplate.setLanguagePreference("ruesdeen");
    commonDataTemplate.setCommonDataTransparentTemplate(commonDataTransparentTemplate);

    consumerPresentedMode.setPayloadFormatIndicator(new PayloadFormatIndicator());
    consumerPresentedMode.addApplicationTemplate(applicationTemplate1);
    consumerPresentedMode.addApplicationTemplate(applicationTemplate2);
    consumerPresentedMode.setCommonDataTemplate(commonDataTemplate);

    assertThat(consumerPresentedMode.toBase64(), equalTo(
        "hQVDUFYwMWETTwegAAAAVVVVUAhQcm9kdWN0MWETTwegAAAAZmZmUAhQcm9kdWN0MmJJWggSNFZ4kBI0"
      + "WF8gDkNBUkRIT0xERVIvRU1WXy0IcnVlc2RlZW5kIZ8QBwYBCgMAAACfJghYT9OF+iNLzJ82AgABnzcE"
      + "bVjvEw=="));

  }

  @Test
  public void testSuccessToHexWhenValueIsNull() throws IOException {

    final ConsumerPresentedMode consumerPresentedMode = new ConsumerPresentedMode();

    consumerPresentedMode.setPayloadFormatIndicator(null);

    assertThat(consumerPresentedMode.toHex(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToBase64() throws IOException {
    final ConsumerPresentedMode consumerPresentedMode = new ConsumerPresentedMode();

    consumerPresentedMode.setPayloadFormatIndicator(new PayloadFormatIndicator());
    consumerPresentedMode.addApplicationTemplate(new ApplicationTemplate());
    consumerPresentedMode.setCommonDataTemplate(new CommonDataTemplate());

    assertThat(consumerPresentedMode.toBase64(), equalTo("hQVDUFYwMQ=="));
  }

  @Test
  public void testSuccessToBase64WhenValueIsNull() throws IOException {

    final ConsumerPresentedMode consumerPresentedMode = new ConsumerPresentedMode();

    consumerPresentedMode.setPayloadFormatIndicator(null);

    assertThat(consumerPresentedMode.toBase64(), equalTo(StringUtils.EMPTY));
  }

  public static class OtherTemplate implements BERTemplate<byte[]> {

    @Override
    public byte[] getBytes() throws IOException {
      return new byte[0];
    }

  }

}
// @formatter:on
