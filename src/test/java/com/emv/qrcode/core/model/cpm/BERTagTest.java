package com.emv.qrcode.core.model.cpm;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import java.io.IOException;

import org.junit.Test;

import com.emv.qrcode.core.model.cpm.BERTag.TagClass;
import com.emv.qrcode.core.model.cpm.BERTag.TagType;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;
import com.emv.qrcode.model.cpm.constants.TagTransactionProcessingCodes;

public class BERTagTest {

  @Test
  public void testPayloadFormatIndicator() throws IOException {
    final BERTag berTag = ConsumerPresentedModeFieldCodes.ID_PAYLOAD_FORMAT_INDICATOR;
    assertThat(berTag.toString(), equalTo("85"));
    assertThat(berTag.getTagClass(), equalTo(TagClass.CONTEXT_SPECIFIC));
    assertThat(berTag.getTagType(), equalTo(TagType.PRIMITIVE));
    assertThat(berTag.getBytes().length, equalTo(1));
  }

  @Test
  public void testApplicationTemplate() throws IOException {
    final BERTag berTag = ConsumerPresentedModeFieldCodes.ID_APPLICATION_TEMPLATE;
    assertThat(berTag.toString(), equalTo("61"));
    assertThat(berTag.getTagClass(), equalTo(TagClass.APPLICATION));
    assertThat(berTag.getTagType(), equalTo(TagType.CONSTRUCTED));
    assertThat(berTag.getBytes().length, equalTo(1));
  }

  @Test
  public void testCommonDataTemplate() throws IOException {
    final BERTag berTag = ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TEMPLATE;
    assertThat(berTag.toString(), equalTo("62"));
    assertThat(berTag.getTagClass(), equalTo(TagClass.APPLICATION));
    assertThat(berTag.getTagType(), equalTo(TagType.CONSTRUCTED));
    assertThat(berTag.getBytes().length, equalTo(1));
  }

  @Test
  public void testApplicationSpecificTransparentTemplate() throws IOException {
    final BERTag berTag = ConsumerPresentedModeFieldCodes.ID_APPLICATION_SPECIFIC_TRANSPARENT_TEMPLATE;
    assertThat(berTag.toString(), equalTo("63"));
    assertThat(berTag.getTagClass(), equalTo(TagClass.APPLICATION));
    assertThat(berTag.getTagType(), equalTo(TagType.CONSTRUCTED));
    assertThat(berTag.getBytes().length, equalTo(1));
  }

  @Test
  public void testCommonDataTransparentTemplate() throws IOException {
    final BERTag berTag = ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TRANSPARENT_TEMPLATE;
    assertThat(berTag.toString(), equalTo("64"));
    assertThat(berTag.getTagClass(), equalTo(TagClass.APPLICATION));
    assertThat(berTag.getTagType(), equalTo(TagType.CONSTRUCTED));
    assertThat(berTag.getBytes().length, equalTo(1));
  }

  @Test
  public void testApplicationDefinitionFileName() throws IOException {
    final BERTag berTag = TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME;
    assertThat(berTag.toString(), equalTo("4F"));
    assertThat(berTag.getTagClass(), equalTo(TagClass.APPLICATION));
    assertThat(berTag.getTagType(), equalTo(TagType.PRIMITIVE));
    assertThat(berTag.getBytes().length, equalTo(1));
  }

  @Test
  public void testApplicationLabel() throws IOException {
    final BERTag berTag = TagTransactionProcessingCodes.ID_APPLICATION_LABEL;
    assertThat(berTag.toString(), equalTo("50"));
    assertThat(berTag.getTagClass(), equalTo(TagClass.APPLICATION));
    assertThat(berTag.getTagType(), equalTo(TagType.PRIMITIVE));
    assertThat(berTag.getBytes().length, equalTo(1));
  }

  @Test
  public void testTrack2EquivalentData() throws IOException {
    final BERTag berTag = TagTransactionProcessingCodes.ID_TRACK_2_EQUIVALENT_DATA;
    assertThat(berTag.toString(), equalTo("57"));
    assertThat(berTag.getTagClass(), equalTo(TagClass.APPLICATION));
    assertThat(berTag.getTagType(), equalTo(TagType.PRIMITIVE));
    assertThat(berTag.getBytes().length, equalTo(1));
  }

  @Test
  public void testApplicationPan() throws IOException {
    final BERTag berTag = TagTransactionProcessingCodes.ID_APPLICATION_PAN;
    assertThat(berTag.toString(), equalTo("5A"));
    assertThat(berTag.getTagClass(), equalTo(TagClass.APPLICATION));
    assertThat(berTag.getTagType(), equalTo(TagType.PRIMITIVE));
    assertThat(berTag.getBytes().length, equalTo(1));
  }

  @Test
  public void testCardholderName() throws IOException {
    final BERTag berTag = TagTransactionProcessingCodes.ID_CARDHOLDER_NAME;
    assertThat(berTag.toString(), equalTo("5F20"));
    assertThat(berTag.getTagClass(), equalTo(TagClass.APPLICATION));
    assertThat(berTag.getTagType(), equalTo(TagType.PRIMITIVE));
    assertThat(berTag.getBytes().length, equalTo(2));
  }

  @Test
  public void testLanguagePreference() throws IOException {
    final BERTag berTag = TagTransactionProcessingCodes.ID_LANGUAGE_PREFERENCE;
    assertThat(berTag.toString(), equalTo("5F2D"));
    assertThat(berTag.getTagClass(), equalTo(TagClass.APPLICATION));
    assertThat(berTag.getTagType(), equalTo(TagType.PRIMITIVE));
    assertThat(berTag.getBytes().length, equalTo(2));
  }

  @Test
  public void testIssuerUrl() throws IOException {
    final BERTag berTag = TagTransactionProcessingCodes.ID_ISSUER_URL;
    assertThat(berTag.toString(), equalTo("5F50"));
    assertThat(berTag.getTagClass(), equalTo(TagClass.APPLICATION));
    assertThat(berTag.getTagType(), equalTo(TagType.PRIMITIVE));
    assertThat(berTag.getBytes().length, equalTo(2));
  }

  @Test
  public void testApplicationVersionNumber() throws IOException {
    final BERTag berTag = TagTransactionProcessingCodes.ID_APPLICATION_VERSION_NUMBER;
    assertThat(berTag.toString(), equalTo("9F08"));
    assertThat(berTag.getTagClass(), equalTo(TagClass.CONTEXT_SPECIFIC));
    assertThat(berTag.getTagType(), equalTo(TagType.PRIMITIVE));
    assertThat(berTag.getBytes().length, equalTo(2));
  }

  @Test
  public void testTokenRequestorId() throws IOException {
    final BERTag berTag = TagTransactionProcessingCodes.ID_TOKEN_REQUESTOR_ID;
    assertThat(berTag.toString(), equalTo("9F19"));
    assertThat(berTag.getTagClass(), equalTo(TagClass.CONTEXT_SPECIFIC));
    assertThat(berTag.getTagType(), equalTo(TagType.PRIMITIVE));
    assertThat(berTag.getBytes().length, equalTo(2));
  }

  @Test
  public void testPaymentAccountReference() throws IOException {
    final BERTag berTag = TagTransactionProcessingCodes.ID_PAYMENT_ACCOUNT_REFERENCE;
    assertThat(berTag.toString(), equalTo("9F24"));
    assertThat(berTag.getTagClass(), equalTo(TagClass.CONTEXT_SPECIFIC));
    assertThat(berTag.getTagType(), equalTo(TagType.PRIMITIVE));
    assertThat(berTag.getBytes().length, equalTo(2));
  }

  @Test
  public void testLast4DigitsOfPan() throws IOException {
    final BERTag berTag = TagTransactionProcessingCodes.ID_LAST_4_DIGITS_OF_PAN;
    assertThat(berTag.toString(), equalTo("9F25"));
    assertThat(berTag.getTagClass(), equalTo(TagClass.CONTEXT_SPECIFIC));
    assertThat(berTag.getTagType(), equalTo(TagType.PRIMITIVE));
    assertThat(berTag.getBytes().length, equalTo(2));
  }

  @Test
  public void testCryptogramInformationData() throws IOException {
    final BERTag berTag = TagTransactionProcessingCodes.ID_CRYPTOGRAM_INFORMATION_DATA;
    assertThat(berTag.toString(), equalTo("9F27"));
    assertThat(berTag.getTagClass(), equalTo(TagClass.CONTEXT_SPECIFIC));
    assertThat(berTag.getTagType(), equalTo(TagType.PRIMITIVE));
    assertThat(berTag.getBytes().length, equalTo(2));
  }

  @Test
  public void testApplicationTransactionCounter() throws IOException {
    final BERTag berTag = TagTransactionProcessingCodes.ID_APPLICATION_TRANSACTION_COUNTER;
    assertThat(berTag.toString(), equalTo("9F36"));
    assertThat(berTag.getTagClass(), equalTo(TagClass.CONTEXT_SPECIFIC));
    assertThat(berTag.getTagType(), equalTo(TagType.PRIMITIVE));
    assertThat(berTag.getBytes().length, equalTo(2));
  }

  @Test
  public void testApplicationCryptogram() throws IOException {
    final BERTag berTag = TagTransactionProcessingCodes.ID_APPLICATION_CRYPTOGRAM;
    assertThat(berTag.toString(), equalTo("9F26"));
    assertThat(berTag.getTagClass(), equalTo(TagClass.CONTEXT_SPECIFIC));
    assertThat(berTag.getTagType(), equalTo(TagType.PRIMITIVE));
    assertThat(berTag.getBytes().length, equalTo(2));
  }

  @Test
  public void testIssuerApplicationData() throws IOException {
    final BERTag berTag = TagTransactionProcessingCodes.ID_ISSUER_APPLICATION_DATA;
    assertThat(berTag.toString(), equalTo("9F10"));
    assertThat(berTag.getTagClass(), equalTo(TagClass.CONTEXT_SPECIFIC));
    assertThat(berTag.getTagType(), equalTo(TagType.PRIMITIVE));
    assertThat(berTag.getBytes().length, equalTo(2));
  }

  @Test
  public void testUnpredictableNumber() throws IOException {
    final BERTag berTag = TagTransactionProcessingCodes.ID_UNPREDICTABLE_NUMBER;
    assertThat(berTag.toString(), equalTo("9F37"));
    assertThat(berTag.getTagClass(), equalTo(TagClass.CONTEXT_SPECIFIC));
    assertThat(berTag.getTagType(), equalTo(TagType.PRIMITIVE));
    assertThat(berTag.getBytes().length, equalTo(2));
  }

  @Test
  public void testEquals() throws IOException {
    assertThat(TagTransactionProcessingCodes.ID_ISSUER_APPLICATION_DATA, equalTo(TagTransactionProcessingCodes.ID_ISSUER_APPLICATION_DATA));
    assertThat(TagTransactionProcessingCodes.ID_ISSUER_APPLICATION_DATA, not(equalTo(TagTransactionProcessingCodes.ID_APPLICATION_CRYPTOGRAM)));
    assertThat(TagTransactionProcessingCodes.ID_ISSUER_APPLICATION_DATA, not(equalTo(null)));
  }

  @Test
  public void testHashCode() throws IOException {
    assertThat(TagTransactionProcessingCodes.ID_ISSUER_APPLICATION_DATA.hashCode(), equalTo(TagTransactionProcessingCodes.ID_ISSUER_APPLICATION_DATA.hashCode()));
    assertThat(TagTransactionProcessingCodes.ID_ISSUER_APPLICATION_DATA.hashCode(), not(equalTo(TagTransactionProcessingCodes.ID_APPLICATION_CRYPTOGRAM.hashCode())));
  }

}
