package com.emv.qrcode.core.model.cpm;

import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.junit.Test;

import com.emv.qrcode.core.exception.DecodeValueException;
import com.emv.qrcode.model.cpm.constants.TagTransactionProcessingCodes;

public class BERTLBinaryTest {

  @Test
  public void testSuccess() throws IOException {
    final BERTLBinary bertlv = new BERTLBinary(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME, "A0000000666666");
    assertThat(bertlv.toHex(), equalTo("4F07A0000000666666"));
    assertThat(bertlv.getStringValue(), equalTo("A0000000666666"));

    bertlv.setValue("A0000000555555");
    assertThat(bertlv.toHex(), equalTo("4F07A0000000555555"));
    assertThat(bertlv.getStringValue(), equalTo("A0000000555555"));
  }

  @Test
  public void testFail() throws IOException {
    final DecodeValueException decodeValueException = catchThrowableOfType(() -> new BERTLBinary(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME, "AG000000666666"), DecodeValueException.class);
    assertThat(decodeValueException.getMessage(), equalTo("Characters outside of the expected range Hex '[0-9a-fA-F]'. Invalid value 'AG000000666666'"));
    assertThat(decodeValueException.getValue(), equalTo("AG000000666666"));
  }

}
