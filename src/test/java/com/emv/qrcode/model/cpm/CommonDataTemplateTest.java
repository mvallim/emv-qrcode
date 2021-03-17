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
public class CommonDataTemplateTest {

  @Test
  public void testSuccessToHex() throws IOException {

    final CommonDataTransparentTemplate commonDataTransparentTemplate = new CommonDataTransparentTemplate();
    commonDataTransparentTemplate.addAdditionalData(new BERTLAlphanumeric(new byte[] { 0x00 }, "1234"));

    final CommonDataTemplate commonDataTemplate = new CommonDataTemplate();

    commonDataTemplate.setApplicationDefinitionFileName("1234");
    commonDataTemplate.setApplicationLabel("1234");
    commonDataTemplate.setTrack2EquivalentData("1234");
    commonDataTemplate.setApplicationPAN("1234");
    commonDataTemplate.setCardholderName("1234");
    commonDataTemplate.setLanguagePreference("1234");
    commonDataTemplate.setIssuerURL("1234");
    commonDataTemplate.setApplicationVersionNumber("1234");
    commonDataTemplate.setTokenRequestorID("1234");
    commonDataTemplate.setPaymentAccountReference("1234");
    commonDataTemplate.setLast4DigitsOfPAN("1234");
    commonDataTemplate.setCryptogramInformationData("1234");
    commonDataTemplate.setApplicationTransactionCounter("1234");
    commonDataTemplate.setApplicationCryptogram("1234");
    commonDataTemplate.setIssuerApplicationData("1234");
    commonDataTemplate.setUnpredictableNumber("1234");
    commonDataTemplate.setCommonDataTransparentTemplate(commonDataTransparentTemplate);

    assertThat(commonDataTemplate.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TEMPLATE));
    assertThat(Hex.encodeHexString(commonDataTemplate.getBytes(), false), equalTo(
        "62624F021234500431323334570212345A0212345F2004313233345F2D04313233345F5004313233"
      + "349F080212349F190212349F2404313233349F2504313233349F2704313233349F360212349F2602"
      + "12349F100212349F370212346406000431323334"));
  }

  @Test
  public void testSuccessToHexWhenValueIsEmpty() throws IOException {

    final CommonDataTemplate commonDataTemplate = new CommonDataTemplate();

    commonDataTemplate.setApplicationDefinitionFileName(StringUtils.EMPTY);
    commonDataTemplate.setApplicationLabel(StringUtils.EMPTY);
    commonDataTemplate.setTrack2EquivalentData(StringUtils.EMPTY);
    commonDataTemplate.setApplicationPAN(StringUtils.EMPTY);
    commonDataTemplate.setCardholderName(StringUtils.EMPTY);
    commonDataTemplate.setLanguagePreference(StringUtils.EMPTY);
    commonDataTemplate.setIssuerURL(StringUtils.EMPTY);
    commonDataTemplate.setApplicationVersionNumber(StringUtils.EMPTY);
    commonDataTemplate.setTokenRequestorID(StringUtils.EMPTY);
    commonDataTemplate.setPaymentAccountReference(StringUtils.EMPTY);
    commonDataTemplate.setLast4DigitsOfPAN(StringUtils.EMPTY);
    commonDataTemplate.setCryptogramInformationData(StringUtils.EMPTY);
    commonDataTemplate.setApplicationTransactionCounter(StringUtils.EMPTY);
    commonDataTemplate.setApplicationCryptogram(StringUtils.EMPTY);
    commonDataTemplate.setIssuerApplicationData(StringUtils.EMPTY);

    assertThat(commonDataTemplate.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TEMPLATE));
    assertThat(Hex.encodeHexString(commonDataTemplate.getBytes(), false), equalTo(StringUtils.EMPTY));

  }

  @Test
  public void testSuccessToHexWhenValueIsNull() throws IOException {

    final CommonDataTemplate commonDataTemplate = new CommonDataTemplate();

    commonDataTemplate.setApplicationDefinitionFileName(null);
    commonDataTemplate.setApplicationLabel(null);
    commonDataTemplate.setTrack2EquivalentData(null);
    commonDataTemplate.setApplicationPAN(null);
    commonDataTemplate.setCardholderName(null);
    commonDataTemplate.setLanguagePreference(null);
    commonDataTemplate.setIssuerURL(null);
    commonDataTemplate.setApplicationVersionNumber(null);
    commonDataTemplate.setTokenRequestorID(null);
    commonDataTemplate.setPaymentAccountReference(null);
    commonDataTemplate.setLast4DigitsOfPAN(null);
    commonDataTemplate.setCryptogramInformationData(null);
    commonDataTemplate.setApplicationTransactionCounter(null);
    commonDataTemplate.setApplicationCryptogram(null);
    commonDataTemplate.setIssuerApplicationData(null);

    assertThat(commonDataTemplate.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TEMPLATE));
    assertThat(Hex.encodeHexString(commonDataTemplate.getBytes(), false), equalTo(StringUtils.EMPTY));

  }

}
// @formatter:on
