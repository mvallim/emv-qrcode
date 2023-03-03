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

package com.emv.qrcode.decoder.mpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.core.model.mpm.TagLengthString;

public class TagLengthStringDecoderTest {

  @Test
  public void testSuccessDecode() throws PresentedModeException {
    final TagLengthString tagLengthString = DecoderMpm.decode("02041234", TagLengthString.class);

    assertThat(tagLengthString, not(nullValue()));

    assertThat(tagLengthString.getTag(), equalTo("02"));
    assertThat(tagLengthString.getLength(), equalTo(4));
    assertThat(tagLengthString.getValue(), equalTo("1234"));
  }

  @Test
  public void testSuccessDecodeEncode() throws PresentedModeException {
    final TagLengthString tagLengthString = DecoderMpm.decode("02041234", TagLengthString.class);

    assertThat(tagLengthString.toString(), equalTo("02041234"));
  }

}
