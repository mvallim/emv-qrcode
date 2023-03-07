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

package com.emv.qrcode.model.mpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.emv.qrcode.core.model.mpm.TagLengthString;

public class MerchantInformationLanguageTest {

  @Test
  public void testSuccessToString() {

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

  @Test
  public void testSuccessToStringWhenValueIsNull() {

    final MerchantInformationLanguage merchantInformationLanguage = new MerchantInformationLanguage();

    assertThat(merchantInformationLanguage.toString(), equalTo(StringUtils.EMPTY));
  }

}
