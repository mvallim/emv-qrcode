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

package com.emv.qrcode.core.model.cpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.emv.qrcode.model.cpm.constants.TagTransactionProcessingCodes;

public class BERTLAlphanumericTest {

  @Test
  public void testSuccess() throws IOException {
    final BERTLAlphanumeric bertlv1 = new BERTLAlphanumeric(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME, "Master Card / EMV");
    assertThat(bertlv1.toHex(), equalTo("4F114D61737465722043617264202F20454D56"));
    assertThat(bertlv1.getStringValue(), equalTo("Master Card / EMV"));

    bertlv1.setValue("Visa Card / EMV");
    assertThat(bertlv1.toHex(), equalTo("4F0F566973612043617264202F20454D56"));
    assertThat(bertlv1.getStringValue(), equalTo("Visa Card / EMV"));

    bertlv1.setValue("");
    assertThat(bertlv1.toHex(), equalTo(StringUtils.EMPTY));
    assertThat(bertlv1.getStringValue(), equalTo(StringUtils.EMPTY));

    final BERTLAlphanumeric bertlv2 = new BERTLAlphanumeric(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME, "Master Card / EMV".getBytes());
    assertThat(bertlv2.toHex(), equalTo("4F114D61737465722043617264202F20454D56"));
    assertThat(bertlv2.getStringValue(), equalTo("Master Card / EMV"));
  }

}
