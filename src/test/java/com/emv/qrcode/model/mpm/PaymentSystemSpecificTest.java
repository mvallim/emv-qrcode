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

package com.emv.qrcode.model.mpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.emv.qrcode.core.model.mpm.TagLengthString;

public class PaymentSystemSpecificTest {

  @Test
  public void testSuccessToString() {

    final PaymentSystemSpecific paymentSystemSpecific = new PaymentSystemSpecific();
    paymentSystemSpecific.setGloballyUniqueIdentifier("hoge");
    paymentSystemSpecific.addPaymentSystemSpecific(new TagLengthString("01", "abcd"));

    assertThat(paymentSystemSpecific.toString(), equalTo("0004hoge0104abcd"));
  }

  @Test
  public void testSuccessToStringContructorGloballyUniqueIdentifier() {

    final PaymentSystemSpecific paymentSystemSpecific = new PaymentSystemSpecific("hoge");
    paymentSystemSpecific.addPaymentSystemSpecific(new TagLengthString("01", "abcd"));

    assertThat(paymentSystemSpecific.toString(), equalTo("0004hoge0104abcd"));
  }

  @Test
  public void testSuccessToStringContructorGloballyUniqueIdentifierAndPaymentSystemSpecific() {
    final PaymentSystemSpecific paymentSystemSpecific = new PaymentSystemSpecific("hoge", "01", "abcd");

    assertThat(paymentSystemSpecific.toString(), equalTo("0004hoge0104abcd"));
  }

  @Test
  public void testSuccessToStringSetGloballyUniqueIdentifierAndPaymentSystemSpecific() {
    final PaymentSystemSpecific paymentSystemSpecific = new PaymentSystemSpecific();
    paymentSystemSpecific.setGloballyUniqueIdentifier("hoge", new TagLengthString("01", "abcd"));

    assertThat(paymentSystemSpecific.toString(), equalTo("0004hoge0104abcd"));
  }

  @Test
  public void testSuccessToStringContructorGloballyUniqueIdentifierIsNull() {
    final PaymentSystemSpecific paymentSystemSpecific = new PaymentSystemSpecific(null);

    assertThat(paymentSystemSpecific.toString(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToStringContructorGloballyUniqueIdentifierIsNullAndPaymentSystemSpecificIsNull() {
    final PaymentSystemSpecific paymentSystemSpecific = new PaymentSystemSpecific(null, null, null);

    assertThat(paymentSystemSpecific.toString(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToStringIsEmpty() {
    final PaymentSystemSpecific paymentSystemSpecific = new PaymentSystemSpecific("");

    assertThat(paymentSystemSpecific.toString(), equalTo(StringUtils.EMPTY));
  }

}
