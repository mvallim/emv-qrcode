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

    assertThat(additionalData.getApplicationDefinitionFileName().getValue(), equalTo("1234567890ABCDEF"));
    assertThat(additionalData.getApplicationLabel().getValue(), equalTo("1234567890ABCDEF"));
    assertThat(additionalData.getTrack2EquivalentData().getValue(), equalTo("1234567890ABCDEF"));
    assertThat(additionalData.getApplicationPAN().getValue(), equalTo("1234567890ABCDEF"));
    assertThat(additionalData.getCardholderName().getValue(), equalTo("1234567890ABCDEF"));
    assertThat(additionalData.getLanguagePreference().getValue(), equalTo("1234567890ABCDEF"));
    assertThat(additionalData.getIssuerURL().getValue(), equalTo("1234567890ABCDEF"));
    assertThat(additionalData.getApplicationVersionNumber().getValue(), equalTo("1234567890ABCDEF"));
    assertThat(additionalData.getTokenRequestorID().getValue(), equalTo("1234567890ABCDEF"));
    assertThat(additionalData.getPaymentAccountReference().getValue(), equalTo("1234567890ABCDEF"));
    assertThat(additionalData.getLast4DigitsOfPAN().getValue(), equalTo("1234567890ABCDEF"));
    assertThat(additionalData.getCryptogramInformationData().getValue(), equalTo("1234567890ABCDEF"));
    assertThat(additionalData.getApplicationTransactionCounter().getValue(), equalTo("1234567890ABCDEF"));
    assertThat(additionalData.getApplicationCryptogram().getValue(), equalTo("1234567890ABCDEF"));
    assertThat(additionalData.getIssuerApplicationData().getValue(), equalTo("1234567890ABCDEF"));
    assertThat(additionalData.getUnpredictableNumber().getValue(), equalTo("1234567890ABCDEF"));

  }

}
