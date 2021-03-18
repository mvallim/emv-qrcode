package com.emv.qrcode.core.model.cpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.junit.Test;

import com.emv.qrcode.model.cpm.constants.TagTransactionProcessingCodes;

public class BERTLAlphanumericTest {

  @Test
  public void testSuccess() throws IOException {
    final BERTLAlphanumeric bertlv = new BERTLAlphanumeric(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME, "Master Card / EMV");
    assertThat(bertlv.toHex(), equalTo("4F114D61737465722043617264202F20454D56"));
    assertThat(bertlv.getStringValue(), equalTo("Master Card / EMV"));

    bertlv.setValue("Visa Card / EMV");
    assertThat(bertlv.toHex(), equalTo("4F0F566973612043617264202F20454D56"));
    assertThat(bertlv.getStringValue(), equalTo("Visa Card / EMV"));
  }

}
