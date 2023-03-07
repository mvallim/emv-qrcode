/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.emv.qrcode.decoder.cpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.junit.Test;

import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.core.model.cpm.BERTLCompressedNumeric;
import com.emv.qrcode.core.model.cpm.BERTLV;
import com.emv.qrcode.core.model.cpm.BERTag;

public class BERTLCompressedNumericDecoderTest {

  @Test
  public void testSuccessDecode() throws PresentedModeException, IOException {

    // 00051234567890

    final byte[] source = { 0x00, 0x05, 0x12, 0x34, 0x56, 0x78, (byte) 0x90 };

    final BERTLV bertlv = DecoderCpm.decode(source, BERTLCompressedNumeric.class);

    assertThat(bertlv, not(nullValue()));

    assertThat(bertlv.getTag(), equalTo(new BERTag(new byte[] { 0x00 })));
    assertThat(bertlv.getLength(), equalTo(5));
    assertThat(bertlv.getBytes(), equalTo(source));
    assertThat(bertlv.getStringValue(), equalTo("1234567890"));
  }

  @Test
  public void testSuccessDecodeEncode() throws PresentedModeException, IOException {
    final byte[] source = { 0x00, 0x05, 0x12, 0x34, 0x56, 0x78, (byte) 0x90 };
    final BERTLV bertlv = DecoderCpm.decode(source, BERTLCompressedNumeric.class);
    assertThat(bertlv.getBytes(), equalTo(source));
  }

}
