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
import com.emv.qrcode.model.mpm.MerchantInformationLanguage;

public class MerchantInformationLanguageDecoderTest {

  @Test
  public void testSuccessDecode() throws PresentedModeException {
    final MerchantInformationLanguage merchantInformationLanguage = DecoderMpm.decode("64280002ZH0104最佳运输0202北京0304abcd", MerchantInformationLanguage.class);

    assertThat(merchantInformationLanguage.getLanguagePreference(), not(nullValue()));
    assertThat(merchantInformationLanguage.getMerchantName(), not(nullValue()));
    assertThat(merchantInformationLanguage.getMerchantCity(), not(nullValue()));
    assertThat(merchantInformationLanguage.getRFUforEMVCo().size(), equalTo(1));

    assertThat(merchantInformationLanguage.getLanguagePreference().getTag(), equalTo("00"));
    assertThat(merchantInformationLanguage.getLanguagePreference().getLength(), equalTo(2));
    assertThat(merchantInformationLanguage.getLanguagePreference().getValue(), equalTo("ZH"));

    assertThat(merchantInformationLanguage.getMerchantName().getTag(), equalTo("01"));
    assertThat(merchantInformationLanguage.getMerchantName().getLength(), equalTo(4));
    assertThat(merchantInformationLanguage.getMerchantName().getValue(), equalTo("最佳运输"));

    assertThat(merchantInformationLanguage.getMerchantCity().getTag(), equalTo("02"));
    assertThat(merchantInformationLanguage.getMerchantCity().getLength(), equalTo(2));
    assertThat(merchantInformationLanguage.getMerchantCity().getValue(), equalTo("北京"));

    assertThat(merchantInformationLanguage.getRFUforEMVCo().get("03").getTag(), equalTo("03"));
    assertThat(merchantInformationLanguage.getRFUforEMVCo().get("03").getLength(), equalTo(4));
    assertThat(merchantInformationLanguage.getRFUforEMVCo().get("03").getValue(), equalTo("abcd"));

  }

  @Test
  public void testFailDecode() throws PresentedModeException {
    final DuplicateTagException duplicateTagException = catchThrowableOfType(() -> DecoderMpm.decode("64340002ZH0002ZH0104最佳运输0202北京0304abcd", MerchantInformationLanguage.class), DuplicateTagException.class);
    assertThat(duplicateTagException.getTag(), equalTo("00"));
    assertThat(duplicateTagException.getValue(), equalTo("0002ZH"));
    assertThat(duplicateTagException.getMessage(), equalTo("Scope: 'MerchantInformationLanguage' informed already contains '00' tag"));

    final InvalidTagException invalidTagException = catchThrowableOfType(() -> DecoderMpm.decode("6428AA02ZH0104最佳运输0202北京0304abcd", MerchantInformationLanguage.class), InvalidTagException.class);
    assertThat(invalidTagException.getTag(), equalTo("AA"));
    assertThat(invalidTagException.getValue(), equalTo("AA02ZH"));
    assertThat(invalidTagException.getMessage(), equalTo("Scope: 'MerchantInformationLanguage' invalid 'AA' tag"));
  }

  @Test
  public void testSuccessDecodeEncode() throws PresentedModeException {
    final MerchantInformationLanguage merchantInformationLanguage = DecoderMpm.decode("64280002ZH0104最佳运输0202北京0304abcd", MerchantInformationLanguage.class);

    assertThat(merchantInformationLanguage.toString(), equalTo("0002ZH0104最佳运输0202北京0304abcd"));
  }

  @Test
  public void testSuccessEncode() {

    final TagLengthString rFUforEMVCo = new TagLengthString();
    rFUforEMVCo.setTag("03");
    rFUforEMVCo.setValue("abcd");

    final MerchantInformationLanguage merchantInformationLanguage = new MerchantInformationLanguage();
    merchantInformationLanguage.setLanguagePreference("ZH");
    merchantInformationLanguage.setMerchantCity("最佳运输");
    merchantInformationLanguage.setMerchantName("北京");
    merchantInformationLanguage.addRFUforEMVCo(rFUforEMVCo);

    assertThat(merchantInformationLanguage.toString(), equalTo("0002ZH0102北京0204最佳运输0304abcd"));
  }

}
