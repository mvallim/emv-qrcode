package com.emv.qrcode.model.cpm;

//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.MatcherAssert.assertThat;
//
//import java.io.IOException;
//
//import org.apache.commons.lang3.StringUtils;
//import org.junit.Test;
//
//import com.emv.qrcode.core.model.BERTLString;
//
//public class ApplicationTemplateTest {
//
//  @Test
//  public void testSuccessToHex() throws IOException {
//
//    final ApplicationSpecificTransparentTemplate applicationSpecificTransparentTemplate = new ApplicationSpecificTransparentTemplate();
//
//    applicationSpecificTransparentTemplate.setValue(new BERTLString(0x0, "1234"));
//
//    final ApplicationTemplate applicationTemplate = new ApplicationTemplate();
//
//    applicationTemplate.setApplicationDefinitionFileName("1234");
//    applicationTemplate.setApplicationLabel("1234");
//    applicationTemplate.setTrack2EquivalentData("1234");
//    applicationTemplate.setApplicationPAN("1234");
//    applicationTemplate.setCardholderName("1234");
//    applicationTemplate.setLanguagePreference("1234");
//    applicationTemplate.setIssuerURL("1234");
//    applicationTemplate.setApplicationVersionNumber("1234");
//    applicationTemplate.setTokenRequestorID("1234");
//    applicationTemplate.setPaymentAccountReference("1234");
//    applicationTemplate.setLast4DigitsOfPAN("1234");
//    applicationTemplate.setCryptogramInformationData("1234");
//    applicationTemplate.setApplicationTransactionCounter("1234");
//    applicationTemplate.setApplicationCryptogram("1234");
//    applicationTemplate.setIssuerApplicationData("1234");
//    applicationTemplate.addApplicationSpecificTransparentTemplate(applicationSpecificTransparentTemplate);
//
//    assertThat(applicationTemplate.getTag(), equalTo(0x61));
//    assertThat(applicationTemplate.toHex(),
//        equalTo("624F04313233345004313233345704313233345A04313233342004313233342D04313233345004313233340804313233341904313233342404313233342504313233342704313233343604313233342604313233341004313233346306000431323334"));
//  }
//
//  @Test
//  public void testSuccessToHexWhenValueIsEmpty() throws IOException {
//
//    final ApplicationTemplate applicationTemplate = new ApplicationTemplate();
//
//    applicationTemplate.setApplicationDefinitionFileName(StringUtils.EMPTY);
//    applicationTemplate.setApplicationLabel(StringUtils.EMPTY);
//    applicationTemplate.setTrack2EquivalentData(StringUtils.EMPTY);
//    applicationTemplate.setApplicationPAN(StringUtils.EMPTY);
//    applicationTemplate.setCardholderName(StringUtils.EMPTY);
//    applicationTemplate.setLanguagePreference(StringUtils.EMPTY);
//    applicationTemplate.setIssuerURL(StringUtils.EMPTY);
//    applicationTemplate.setApplicationVersionNumber(StringUtils.EMPTY);
//    applicationTemplate.setTokenRequestorID(StringUtils.EMPTY);
//    applicationTemplate.setPaymentAccountReference(StringUtils.EMPTY);
//    applicationTemplate.setLast4DigitsOfPAN(StringUtils.EMPTY);
//    applicationTemplate.setCryptogramInformationData(StringUtils.EMPTY);
//    applicationTemplate.setApplicationTransactionCounter(StringUtils.EMPTY);
//    applicationTemplate.setApplicationCryptogram(StringUtils.EMPTY);
//    applicationTemplate.setIssuerApplicationData(StringUtils.EMPTY);
//
//    assertThat(applicationTemplate.getTag(), equalTo(0x61));
//    assertThat(applicationTemplate.toHex(), equalTo(StringUtils.EMPTY));
//
//  }
//
//  @Test
//  public void testSuccessToHexWhenValueIsNull() throws IOException {
//
//    final ApplicationTemplate applicationTemplate = new ApplicationTemplate();
//
//    applicationTemplate.setApplicationDefinitionFileName(null);
//    applicationTemplate.setApplicationLabel(null);
//    applicationTemplate.setTrack2EquivalentData(null);
//    applicationTemplate.setApplicationPAN(null);
//    applicationTemplate.setCardholderName(null);
//    applicationTemplate.setLanguagePreference(null);
//    applicationTemplate.setIssuerURL(null);
//    applicationTemplate.setApplicationVersionNumber(null);
//    applicationTemplate.setTokenRequestorID(null);
//    applicationTemplate.setPaymentAccountReference(null);
//    applicationTemplate.setLast4DigitsOfPAN(null);
//    applicationTemplate.setCryptogramInformationData(null);
//    applicationTemplate.setApplicationTransactionCounter(null);
//    applicationTemplate.setApplicationCryptogram(null);
//    applicationTemplate.setIssuerApplicationData(null);
//
//    assertThat(applicationTemplate.getTag(), equalTo(0x61));
//    assertThat(applicationTemplate.toHex(), equalTo(StringUtils.EMPTY));
//
//  }
//
//}
