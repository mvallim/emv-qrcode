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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Test;

import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.core.model.mpm.TagLengthString;
import com.emv.qrcode.model.mpm.MerchantInformationLanguage;
import com.emv.qrcode.model.mpm.MerchantInformationLanguageTemplate;

public class MerchantInformationLanguageTemplateDecoderTest {

  @Test
  public void testSuccessDecode() throws PresentedModeException {
    final MerchantInformationLanguageTemplate merchantInformationLanguage = DecoderMpm.decode("64280002ZH0104最佳运输0202北京0304abcd", MerchantInformationLanguageTemplate.class);

    assertThat(merchantInformationLanguage.getValue(), not(nullValue()));

    assertThat(merchantInformationLanguage.getTag(), equalTo("64"));
    assertThat(merchantInformationLanguage.getLength(), equalTo(28));

    assertThat(merchantInformationLanguage.getValue().getLanguagePreference(), not(nullValue()));
    assertThat(merchantInformationLanguage.getValue().getMerchantName(), not(nullValue()));
    assertThat(merchantInformationLanguage.getValue().getMerchantCity(), not(nullValue()));
    assertThat(merchantInformationLanguage.getValue().getRFUforEMVCo().size(), equalTo(1));

    assertThat(merchantInformationLanguage.getValue().getLanguagePreference().getTag(), equalTo("00"));
    assertThat(merchantInformationLanguage.getValue().getLanguagePreference().getLength(), equalTo(2));
    assertThat(merchantInformationLanguage.getValue().getLanguagePreference().getValue(), equalTo("ZH"));

    assertThat(merchantInformationLanguage.getValue().getMerchantName().getTag(), equalTo("01"));
    assertThat(merchantInformationLanguage.getValue().getMerchantName().getLength(), equalTo(4));
    assertThat(merchantInformationLanguage.getValue().getMerchantName().getValue(), equalTo("最佳运输"));

    assertThat(merchantInformationLanguage.getValue().getMerchantCity().getTag(), equalTo("02"));
    assertThat(merchantInformationLanguage.getValue().getMerchantCity().getLength(), equalTo(2));
    assertThat(merchantInformationLanguage.getValue().getMerchantCity().getValue(), equalTo("北京"));

    assertThat(merchantInformationLanguage.getValue().getRFUforEMVCo().get("03").getTag(), equalTo("03"));
    assertThat(merchantInformationLanguage.getValue().getRFUforEMVCo().get("03").getLength(), equalTo(4));
    assertThat(merchantInformationLanguage.getValue().getRFUforEMVCo().get("03").getValue(), equalTo("abcd"));

  }

  @Test
  public void testSuccessDecodeEncode() throws PresentedModeException {
    final MerchantInformationLanguageTemplate merchantInformationLanguage = DecoderMpm.decode("64280002ZH0104最佳运输0202北京0304abcd", MerchantInformationLanguageTemplate.class);

    assertThat(merchantInformationLanguage.toString(), equalTo("64280002ZH0104最佳运输0202北京0304abcd"));
  }

  @Test
  public void testSuccessEncode() {

    final TagLengthString rFUforEMVCo = new TagLengthString();
    rFUforEMVCo.setTag("03");
    rFUforEMVCo.setValue("abcd");

    final MerchantInformationLanguage value = new MerchantInformationLanguage();
    value.setLanguagePreference("ZH");
    value.setMerchantCity("最佳运输");
    value.setMerchantName("北京");
    value.addRFUforEMVCo(rFUforEMVCo);

    final MerchantInformationLanguageTemplate merchantInformationLanguage = new MerchantInformationLanguageTemplate();
    merchantInformationLanguage.setValue(value);

    assertThat(merchantInformationLanguage.toString(), equalTo("64280002ZH0102北京0204最佳运输0304abcd"));
  }

}
