package com.emv.qrcode.decoder.cpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.junit.Test;

import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.core.model.cpm.BERTLNumeric;
import com.emv.qrcode.core.model.cpm.BERTLV;
import com.emv.qrcode.core.model.cpm.BERTag;

public class BERTLNumericDecoderTest {

  @Test
  public void testSuccessDecode() throws PresentedModeException, IOException {

    // 00051234567890

    final byte[] source = { 0x00, 0x05, 0x12, 0x34, 0x56, 0x78, (byte) 0x90 };

    final BERTLV bertlv = DecoderCpm.decode(source, BERTLNumeric.class);

    assertThat(bertlv, not(nullValue()));

    assertThat(bertlv.getTag(), equalTo(new BERTag(new byte[] { 0x00 })));
    assertThat(bertlv.getLength(), equalTo(5));
    assertThat(bertlv.getBytes(), equalTo(source));
    assertThat(bertlv.getStringValue(), equalTo("1234567890"));
  }

  @Test
  public void testSuccessDecodeEncode() throws PresentedModeException, IOException {
    final byte[] source = { 0x00, 0x05, 0x12, 0x34, 0x56, 0x78, (byte) 0x90 };
    final BERTLV bertlv = DecoderCpm.decode(source, BERTLNumeric.class);
    assertThat(bertlv.getBytes(), equalTo(source));
  }

}
