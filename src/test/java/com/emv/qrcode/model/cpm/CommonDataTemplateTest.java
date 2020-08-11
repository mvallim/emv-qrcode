package com.emv.qrcode.model.cpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.emv.qrcode.core.model.BERTLString;

public class CommonDataTemplateTest {

  @Test
  public void testSuccessToHex() throws IOException {

    final CommonDataTransparentTemplate commonDataTransparentTemplate = new CommonDataTransparentTemplate();

    commonDataTransparentTemplate.setValue(new BERTLString(0x0, "1234"));

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
    commonDataTemplate.addCommonDataTransparentTemplate(commonDataTransparentTemplate);

    assertThat(commonDataTemplate.getTag(), equalTo(0x62));
    assertThat(commonDataTemplate.toHex(), equalTo(
        "624F04313233345004313233345704313233345A04313233342004313233342D04313233345004313233340804313233341904313233342404313233342504313233342704313233343604313233342604313233341004313233346406000431323334"));

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

    assertThat(commonDataTemplate.getTag(), equalTo(0x62));
    assertThat(commonDataTemplate.toHex(), equalTo(StringUtils.EMPTY));

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

    assertThat(commonDataTemplate.getTag(), equalTo(0x62));
    assertThat(commonDataTemplate.toHex(), equalTo(StringUtils.EMPTY));

  }

}
