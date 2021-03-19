package com.emv.qrcode.decoder.cpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.junit.Test;

import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.core.model.cpm.BERTLAlphanumeric;
import com.emv.qrcode.core.model.cpm.BERTLV;
import com.emv.qrcode.core.model.cpm.BERTag;

public class BERTLAlphanumericDecoderTest {

  @Test
  public void testSuccessDecode() throws PresentedModeException, IOException {

    // 0005ABC123

    final byte[] source = { 0x00, 0x06, 0x41, 0x42, 0x43, 0x31, 0x32, 0x33 };

    final BERTLV bertlv = DecoderCpm.decode(source, BERTLAlphanumeric.class);

    assertThat(bertlv, not(nullValue()));

    assertThat(bertlv.getTag(), equalTo(new BERTag(new byte[] { 0x00 })));
    assertThat(bertlv.getLength(), equalTo(6));
    assertThat(bertlv.getBytes(), equalTo(source));
    assertThat(bertlv.getStringValue(), equalTo("ABC123"));
  }

  @Test
  public void testSuccessDecodeEncode() throws PresentedModeException, IOException {
    final byte[] source = { 0x00, 0x06, 0x41, 0x42, 0x43, 0x31, 0x32, 0x33 };
    final BERTLV bertlv = DecoderCpm.decode(source, BERTLAlphanumeric.class);
    assertThat(bertlv.getBytes(), equalTo(source));
  }

}
