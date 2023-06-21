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

import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import com.emv.qrcode.core.exception.DuplicateTagException;
import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.model.cpm.CommonDataTransparentTemplate;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;
import com.emv.qrcode.model.cpm.constants.TagTransactionProcessingCodes;

public class CommonDataTransparentTemplateDecoderTest {

  @Test
  public void testSuccessDecode() throws PresentedModeException, IOException, DecoderException {

    final byte[] source1 = Hex.decodeHex("641A4F07A0000000555555570F1234567890123458D191220112345F");

    final CommonDataTransparentTemplate commonDataTransparentTemplate = DecoderCpm.decode(source1, CommonDataTransparentTemplate.class);

    assertThat(commonDataTransparentTemplate, not(nullValue()));

    assertThat(commonDataTransparentTemplate.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TRANSPARENT_TEMPLATE));
    assertThat(commonDataTransparentTemplate.getApplicationDefinitionFileName().getTag(), equalTo(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME));
    assertThat(commonDataTransparentTemplate.getTrack2EquivalentData().getTag(), equalTo(TagTransactionProcessingCodes.ID_TRACK_2_EQUIVALENT_DATA));

    final byte[] source3 = Hex.decodeHex("64234F07A00000005555554F07A0000000555555570F1234567890123458D191220112345F");

    final DuplicateTagException duplicateTagException = catchThrowableOfType(() -> DecoderCpm.decode(source3, CommonDataTransparentTemplate.class), DuplicateTagException.class);

    assertThat(duplicateTagException.getMessage(), equalTo("Scope: 'CommonDataTransparentTemplate' informed already contains '4F' tag"));
    assertThat(duplicateTagException.getTag(), equalTo("4F"));
    assertThat(duplicateTagException.getValue(), equalTo("4F07A0000000555555"));

  }

  @Test
  public void testSuccessDecodeEncode() throws PresentedModeException, IOException, DecoderException {
    final byte[] source = Hex.decodeHex("641A4F07A0000000555555570F1234567890123458D191220112345F");
    final CommonDataTransparentTemplate commonDataTransparentTemplate = DecoderCpm.decode(source, CommonDataTransparentTemplate.class);
    assertThat(commonDataTransparentTemplate.getBytes(), equalTo(source));
  }

}
