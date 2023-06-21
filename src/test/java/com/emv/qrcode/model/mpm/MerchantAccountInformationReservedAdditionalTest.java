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

public class MerchantAccountInformationReservedAdditionalTest {

  @Test
  public void testSuccessToString() {

    final TagLengthString tagLengthString = new TagLengthString();
    tagLengthString.setTag("01");
    tagLengthString.setValue("abcd");

    final MerchantAccountInformationReservedAdditional merchantAccountInformation = new MerchantAccountInformationReservedAdditional();
    merchantAccountInformation.setGloballyUniqueIdentifier("hoge");
    merchantAccountInformation.addPaymentNetworkSpecific(tagLengthString);

    assertThat(merchantAccountInformation.toString(), equalTo("0004hoge0104abcd"));
  }

  @Test
  public void testSuccessToStringConstructorGloballyUniqueIdentifier() {
    final MerchantAccountInformationReservedAdditional merchantAccountInformation = new MerchantAccountInformationReservedAdditional("hoge");
    assertThat(merchantAccountInformation.toString(), equalTo("0004hoge"));
  }

  @Test
  public void testSuccessToStringConstructorGloballyUniqueIdentifierAndPaymentNetworkSpecific() {
    final MerchantAccountInformationReservedAdditional merchantAccountInformation = new MerchantAccountInformationReservedAdditional("hoge", "01", "abcd");
    assertThat(merchantAccountInformation.toString(), equalTo("0004hoge0104abcd"));
  }

  @Test
  public void testSuccessToStringConstructorGloballyUniqueIdentifierIsNull() {
    final MerchantAccountInformationReservedAdditional merchantAccountInformation = new MerchantAccountInformationReservedAdditional(null);
    assertThat(merchantAccountInformation.toString(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToStringConstructorGloballyUniqueIdentifierAndPaymentNetworkSpecificIsNull() {
    final MerchantAccountInformationReservedAdditional merchantAccountInformation = new MerchantAccountInformationReservedAdditional(null, null, null);
    assertThat(merchantAccountInformation.toString(), equalTo(StringUtils.EMPTY));
  }

}
