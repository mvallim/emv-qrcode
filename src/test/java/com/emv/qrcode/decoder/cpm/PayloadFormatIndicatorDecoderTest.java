/*
 * Copyright 2023 the original author or authors.
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

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.model.cpm.PayloadFormatIndicator;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;

public class PayloadFormatIndicatorDecoderTest {

  @Test
  public void testSuccessDecode() throws PresentedModeException, IOException, DecoderException {

    final byte[] source1 = Hex.decodeHex("85054350563031");

    final PayloadFormatIndicator payloadFormatIndicator = DecoderCpm.decode(source1, PayloadFormatIndicator.class);

    assertThat(payloadFormatIndicator, not(nullValue()));

    assertThat(payloadFormatIndicator.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_PAYLOAD_FORMAT_INDICATOR));
    assertThat(payloadFormatIndicator.getLength(), equalTo(5));
    assertThat(payloadFormatIndicator.getBytes(), equalTo(source1));
    assertThat(payloadFormatIndicator.getStringValue(), equalTo("CPV01"));
  }

  @Test
  public void testSuccessDecodeEncode() throws PresentedModeException, IOException, DecoderException {
    final byte[] source = Hex.decodeHex("85054350563031");
    final PayloadFormatIndicator payloadFormatIndicator = DecoderCpm.decode(source, PayloadFormatIndicator.class);
    assertThat(payloadFormatIndicator.getBytes(), equalTo(source));
  }

}
