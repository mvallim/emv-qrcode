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

import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Test;

import com.emv.qrcode.core.exception.DuplicateTagException;
import com.emv.qrcode.core.exception.InvalidTagException;
import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.core.model.mpm.TagLengthString;
import com.emv.qrcode.model.mpm.Unreserved;

public class UnreservedDecoderTest {

  @Test
  public void testSuccessDecode() throws PresentedModeException {
    final Unreserved unreserved = DecoderMpm.decode("91320016A011223344998877070812345678", Unreserved.class);

    assertThat(unreserved.getContextSpecificData(), not(nullValue()));
    assertThat(unreserved.getGloballyUniqueIdentifier(), not(nullValue()));

    assertThat(unreserved.getContextSpecificData().size(), equalTo(1));
    assertThat(unreserved.getContextSpecificData().get("07").getTag(), equalTo("07"));
    assertThat(unreserved.getContextSpecificData().get("07").getLength(), equalTo(8));
    assertThat(unreserved.getContextSpecificData().get("07").getValue(), equalTo("12345678"));

    assertThat(unreserved.getGloballyUniqueIdentifier().getTag(), equalTo("00"));
    assertThat(unreserved.getGloballyUniqueIdentifier().getLength(), equalTo(16));
    assertThat(unreserved.getGloballyUniqueIdentifier().getValue(), equalTo("A011223344998877"));
  }

  @Test
  public void testFailDecode() throws PresentedModeException {
    final DuplicateTagException duplicateTagException = catchThrowableOfType(() -> DecoderMpm.decode("91440016A011223344998877070812345678070812345678", Unreserved.class), DuplicateTagException.class);
    assertThat(duplicateTagException.getTag(), equalTo("07"));
    assertThat(duplicateTagException.getValue(), equalTo("070812345678"));
    assertThat(duplicateTagException.getMessage(), equalTo("Scope: 'Unreserved' informed already contains '07' tag"));

    final InvalidTagException invalidTagException = catchThrowableOfType(() -> DecoderMpm.decode("91320016A011223344998877AA0812345678", Unreserved.class), InvalidTagException.class);
    assertThat(invalidTagException.getTag(), equalTo("AA"));
    assertThat(invalidTagException.getValue(), equalTo("AA0812345678"));
    assertThat(invalidTagException.getMessage(), equalTo("Scope: 'Unreserved' invalid 'AA' tag"));
  }

  @Test
  public void testSuccessDecodeEncode() throws PresentedModeException {
    final Unreserved unreserved = DecoderMpm.decode("91320016A011223344998877070812345678", Unreserved.class);

    assertThat(unreserved.toString(), equalTo("0016A011223344998877070812345678"));
  }

  @Test
  public void testSuccessEncode() {

    final TagLengthString contextSpecificData = new TagLengthString();
    contextSpecificData.setTag("07");
    contextSpecificData.setValue("12345678");

    final Unreserved unreserved = new Unreserved();
    unreserved.setGloballyUniqueIdentifier("A011223344998877");
    unreserved.addContextSpecificData(contextSpecificData);

    assertThat(unreserved.toString(), equalTo("0016A011223344998877070812345678"));
  }

}
