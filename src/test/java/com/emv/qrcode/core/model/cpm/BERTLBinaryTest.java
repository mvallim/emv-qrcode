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

package com.emv.qrcode.core.model.cpm;

import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.emv.qrcode.core.exception.DecodeValueException;
import com.emv.qrcode.model.cpm.constants.TagTransactionProcessingCodes;

public class BERTLBinaryTest {

  @Test
  public void testSuccess() throws IOException {
    final BERTLBinary bertlv = new BERTLBinary(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME, "A0000000666666");
    assertThat(bertlv.toHex(), equalTo("4F07A0000000666666"));
    assertThat(bertlv.getStringValue(), equalTo("A0000000666666"));

    bertlv.setValue("A0000000555555");
    assertThat(bertlv.toHex(), equalTo("4F07A0000000555555"));
    assertThat(bertlv.getStringValue(), equalTo("A0000000555555"));

    bertlv.setValue("");
    assertThat(bertlv.toHex(), equalTo(StringUtils.EMPTY));
    assertThat(bertlv.getStringValue(), equalTo(StringUtils.EMPTY));

    final BERTLBinary bertlv3 = new BERTLBinary(TagTransactionProcessingCodes.ID_APPLICATION_PAN, new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89 });
    assertThat(bertlv3.toHex(), equalTo("5A050123456789"));
    assertThat(bertlv3.getStringValue(), equalTo("0123456789"));

    final BERTLBinary bertlv4 = new BERTLBinary(TagTransactionProcessingCodes.ID_APPLICATION_PAN.getBytes(), "0123456789");
    assertThat(bertlv4.toHex(), equalTo("5A050123456789"));
    assertThat(bertlv4.getStringValue(), equalTo("0123456789"));

    final BERTLBinary bertlv5 = new BERTLBinary(TagTransactionProcessingCodes.ID_APPLICATION_PAN.getBytes(), "");
    assertThat(bertlv5.toHex(), equalTo(StringUtils.EMPTY));
    assertThat(bertlv5.getStringValue(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testFail() throws IOException {
    final DecodeValueException decodeValueException = catchThrowableOfType(() -> new BERTLBinary(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME, "AG000000666666"), DecodeValueException.class);
    assertThat(decodeValueException.getMessage(), equalTo("Characters outside of the expected range Hex '[0-9a-fA-F]'. Invalid value 'AG000000666666'"));
    assertThat(decodeValueException.getValue(), equalTo("AG000000666666"));
  }

}
