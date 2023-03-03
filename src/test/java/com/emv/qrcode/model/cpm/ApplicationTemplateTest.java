/*
 * Copyright 2023 the original author or authors.
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

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.emv.qrcode.core.model.cpm.BERTLAlphanumeric;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;

// @formatter:off
public class ApplicationTemplateTest {

  @Test
  public void testSuccessToHex() throws IOException {

    final ApplicationSpecificTransparentTemplate applicationSpecificTransparentTemplate = new ApplicationSpecificTransparentTemplate();

    applicationSpecificTransparentTemplate.addAdditionalData(new BERTLAlphanumeric(new byte[] { 0x00 }, "1234"));

    final ApplicationTemplate applicationTemplate = new ApplicationTemplate();

    applicationTemplate.setApplicationDefinitionFileName("1234");
    applicationTemplate.setApplicationLabel("1234");
    applicationTemplate.setTrack2EquivalentData("1234");
    applicationTemplate.setApplicationPAN("1234");
    applicationTemplate.setCardholderName("1234");
    applicationTemplate.setLanguagePreference("1234");
    applicationTemplate.setIssuerURL("1234");
    applicationTemplate.setApplicationVersionNumber("1234");
    applicationTemplate.setTokenRequestorID("1234");
    applicationTemplate.setPaymentAccountReference("1234");
    applicationTemplate.setLast4DigitsOfPAN("1234");
    applicationTemplate.setCryptogramInformationData("1234");
    applicationTemplate.setApplicationTransactionCounter("1234");
    applicationTemplate.setApplicationCryptogram("1234");
    applicationTemplate.setIssuerApplicationData("1234");
    applicationTemplate.setApplicationSpecificTransparentTemplate(applicationSpecificTransparentTemplate);

    assertThat(applicationTemplate.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_APPLICATION_TEMPLATE));
    assertThat(Hex.encodeHexString(applicationTemplate.getBytes(), false), equalTo(
        "61594F021234500431323334570212345A0212345F2004313233345F2D04313233345F5004313233"
      + "349F080212349F190212349F2404313233349F250212349F270212349F360212349F260212349F10"
      + "0212346306000431323334"));
  }

  @Test
  public void testSuccessToHexWhenValueIsEmpty() throws IOException {

    final ApplicationTemplate applicationTemplate = new ApplicationTemplate();

    applicationTemplate.setApplicationDefinitionFileName(StringUtils.EMPTY);
    applicationTemplate.setApplicationLabel(StringUtils.EMPTY);
    applicationTemplate.setTrack2EquivalentData(StringUtils.EMPTY);
    applicationTemplate.setApplicationPAN(StringUtils.EMPTY);
    applicationTemplate.setCardholderName(StringUtils.EMPTY);
    applicationTemplate.setLanguagePreference(StringUtils.EMPTY);
    applicationTemplate.setIssuerURL(StringUtils.EMPTY);
    applicationTemplate.setApplicationVersionNumber(StringUtils.EMPTY);
    applicationTemplate.setTokenRequestorID(StringUtils.EMPTY);
    applicationTemplate.setPaymentAccountReference(StringUtils.EMPTY);
    applicationTemplate.setLast4DigitsOfPAN(StringUtils.EMPTY);
    applicationTemplate.setCryptogramInformationData(StringUtils.EMPTY);
    applicationTemplate.setApplicationTransactionCounter(StringUtils.EMPTY);
    applicationTemplate.setApplicationCryptogram(StringUtils.EMPTY);
    applicationTemplate.setIssuerApplicationData(StringUtils.EMPTY);

    assertThat(applicationTemplate.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_APPLICATION_TEMPLATE));
    assertThat(Hex.encodeHexString(applicationTemplate.getBytes(), false), equalTo(StringUtils.EMPTY));

  }

  @Test
  public void testSuccessToHexWhenValueIsNull() throws IOException {

    final ApplicationTemplate applicationTemplate = new ApplicationTemplate();

    applicationTemplate.setApplicationDefinitionFileName(null);
    applicationTemplate.setApplicationLabel(null);
    applicationTemplate.setTrack2EquivalentData(null);
    applicationTemplate.setApplicationPAN(null);
    applicationTemplate.setCardholderName(null);
    applicationTemplate.setLanguagePreference(null);
    applicationTemplate.setIssuerURL(null);
    applicationTemplate.setApplicationVersionNumber(null);
    applicationTemplate.setTokenRequestorID(null);
    applicationTemplate.setPaymentAccountReference(null);
    applicationTemplate.setLast4DigitsOfPAN(null);
    applicationTemplate.setCryptogramInformationData(null);
    applicationTemplate.setApplicationTransactionCounter(null);
    applicationTemplate.setApplicationCryptogram(null);
    applicationTemplate.setIssuerApplicationData(null);

    assertThat(applicationTemplate.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_APPLICATION_TEMPLATE));
    assertThat(Hex.encodeHexString(applicationTemplate.getBytes(), false), equalTo(StringUtils.EMPTY));

  }

}
// @formatter:on
