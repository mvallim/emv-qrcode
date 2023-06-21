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

package com.emv.qrcode.decoder.mpm;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Test;

import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.core.model.mpm.TagLengthString;
import com.emv.qrcode.model.mpm.Unreserved;
import com.emv.qrcode.model.mpm.UnreservedTemplate;

public class UnreservedTemplateDecoderTest {

  @Test
  public void testSuccessDecode() throws PresentedModeException {
    final UnreservedTemplate unreserved = DecoderMpm.decode("91320016A011223344998877070812345678", UnreservedTemplate.class);

    assertThat(unreserved.getValue(), not(nullValue()));

    assertThat(unreserved.getTag(), equalTo("91"));
    assertThat(unreserved.getLength(), equalTo(32));

    assertThat(unreserved.getValue().getContextSpecificData(), not(nullValue()));
    assertThat(unreserved.getValue().getGloballyUniqueIdentifier(), not(nullValue()));

    assertThat(unreserved.getValue().getContextSpecificData().size(), equalTo(1));
    assertThat(unreserved.getValue().getContextSpecificData().get("07").getTag(), equalTo("07"));
    assertThat(unreserved.getValue().getContextSpecificData().get("07").getLength(), equalTo(8));
    assertThat(unreserved.getValue().getContextSpecificData().get("07").getValue(), equalTo("12345678"));

    assertThat(unreserved.getValue().getGloballyUniqueIdentifier().getTag(), equalTo("00"));
    assertThat(unreserved.getValue().getGloballyUniqueIdentifier().getLength(), equalTo(16));
    assertThat(unreserved.getValue().getGloballyUniqueIdentifier().getValue(), equalTo("A011223344998877"));
  }

  @Test
  public void testSuccessDecodeEncode() throws PresentedModeException {
    final UnreservedTemplate unreserved = DecoderMpm.decode("91320016A011223344998877070812345678", UnreservedTemplate.class);

    assertThat(unreserved.toString(), equalTo("91320016A011223344998877070812345678"));
  }

  @Test
  public void testSuccessEncode() {

    final TagLengthString contextSpecificData = new TagLengthString();
    contextSpecificData.setTag("07");
    contextSpecificData.setValue("12345678");

    final Unreserved value = new Unreserved();
    value.setGloballyUniqueIdentifier("A011223344998877");
    value.addContextSpecificData(contextSpecificData);

    final UnreservedTemplate unreserved = new UnreservedTemplate();
    unreserved.setValue(value);
    unreserved.setTag("91");

    assertThat(unreserved.toString(), equalTo("91320016A011223344998877070812345678"));
  }

}
