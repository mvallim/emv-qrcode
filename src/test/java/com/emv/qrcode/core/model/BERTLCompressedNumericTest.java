package com.emv.qrcode.core.model;

import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.apache.commons.codec.DecoderException;
import org.junit.Test;

import com.emv.qrcode.model.cpm.constants.TagTransactionProcessingCodes;

public class BERTLCompressedNumericTest {

  @Test
  public void testSuccess() throws IOException {
    final BERTLV bertlv = new BERTLCompressedNumeric(TagTransactionProcessingCodes.ID_APPLICATION_PAN, "0123456789");

    assertThat(bertlv.toHex(), equalTo("5A050123456789"));
  }

  @Test
  public void testFail() throws IOException {
    final RuntimeException runtimeException = catchThrowableOfType(() -> new BERTLCompressedNumeric(TagTransactionProcessingCodes.ID_APPLICATION_PAN, "AG000000666666"), RuntimeException.class);
    assertThat(runtimeException.getCause(), instanceOf(DecoderException.class));
  }

}
