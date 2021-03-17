package com.emv.qrcode.core.model.cpm;

import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.apache.commons.codec.DecoderException;
import org.junit.Test;

import com.emv.qrcode.model.cpm.constants.TagTransactionProcessingCodes;

public class BERTLBinaryTest {

  @Test
  public void testSuccess() throws IOException {
    final BERTLBinary bertlv = new BERTLBinary(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME, "A0000000666666");
    assertThat(bertlv.toHex(), equalTo("4F07A0000000666666"));
    assertThat(bertlv.getValue(), equalTo("A0000000666666"));

    bertlv.setValue("A0000000555555");
    assertThat(bertlv.toHex(), equalTo("4F07A0000000555555"));
    assertThat(bertlv.getValue(), equalTo("A0000000555555"));
  }

  @Test
  public void testFail() throws IOException {
    final RuntimeException runtimeException = catchThrowableOfType(() -> new BERTLBinary(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME, "AG000000666666"), RuntimeException.class);
    assertThat(runtimeException.getCause(), instanceOf(DecoderException.class));
  }

}
