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

import org.junit.Test;

public class AdditionalDataTest {

  public static class TestFromAdditionalData extends AdditionalData {

    private static final long serialVersionUID = 3758467283456785723L;

  }

  @Test
  public void testSuccess() {
    final TestFromAdditionalData additionalData = new TestFromAdditionalData();

    additionalData.setApplicationDefinitionFileName("1234567890ABCDEF");
    additionalData.setApplicationLabel("1234567890ABCDEF");
    additionalData.setTrack2EquivalentData("1234567890ABCDEF");
    additionalData.setApplicationPAN("1234567890ABCDEF");
    additionalData.setCardholderName("1234567890ABCDEF");
    additionalData.setLanguagePreference("1234567890ABCDEF");
    additionalData.setIssuerURL("1234567890ABCDEF");
    additionalData.setApplicationVersionNumber("1234567890ABCDEF");
    additionalData.setTokenRequestorID("1234567890ABCDEF");
    additionalData.setPaymentAccountReference("1234567890ABCDEF");
    additionalData.setLast4DigitsOfPAN("1234567890ABCDEF");
    additionalData.setCryptogramInformationData("1234567890ABCDEF");
    additionalData.setApplicationTransactionCounter("1234567890ABCDEF");
    additionalData.setApplicationCryptogram("1234567890ABCDEF");
    additionalData.setIssuerApplicationData("1234567890ABCDEF");
    additionalData.setUnpredictableNumber("1234567890ABCDEF");

    assertThat(additionalData.getApplicationDefinitionFileName().getStringValue(), equalTo("1234567890ABCDEF"));
    assertThat(additionalData.getApplicationLabel().getStringValue(), equalTo("1234567890ABCDEF"));
    assertThat(additionalData.getTrack2EquivalentData().getStringValue(), equalTo("1234567890ABCDEF"));
    assertThat(additionalData.getApplicationPAN().getStringValue(), equalTo("1234567890ABCDEF"));
    assertThat(additionalData.getCardholderName().getStringValue(), equalTo("1234567890ABCDEF"));
    assertThat(additionalData.getLanguagePreference().getStringValue(), equalTo("1234567890ABCDEF"));
    assertThat(additionalData.getIssuerURL().getStringValue(), equalTo("1234567890ABCDEF"));
    assertThat(additionalData.getApplicationVersionNumber().getStringValue(), equalTo("1234567890ABCDEF"));
    assertThat(additionalData.getTokenRequestorID().getStringValue(), equalTo("1234567890ABCDEF"));
    assertThat(additionalData.getPaymentAccountReference().getStringValue(), equalTo("1234567890ABCDEF"));
    assertThat(additionalData.getLast4DigitsOfPAN().getStringValue(), equalTo("1234567890ABCDEF"));
    assertThat(additionalData.getCryptogramInformationData().getStringValue(), equalTo("1234567890ABCDEF"));
    assertThat(additionalData.getApplicationTransactionCounter().getStringValue(), equalTo("1234567890ABCDEF"));
    assertThat(additionalData.getApplicationCryptogram().getStringValue(), equalTo("1234567890ABCDEF"));
    assertThat(additionalData.getIssuerApplicationData().getStringValue(), equalTo("1234567890ABCDEF"));
    assertThat(additionalData.getUnpredictableNumber().getStringValue(), equalTo("1234567890ABCDEF"));

  }

}
