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

public class PaymentSystemSpecificTemplateTest {

  @Test
  public void testSuccessToString() {

    final PaymentSystemSpecific value = new PaymentSystemSpecific();
    value.setGloballyUniqueIdentifier("hoge");
    value.addPaymentSystemSpecific(new TagLengthString("01", "abcd"));

    final PaymentSystemSpecificTemplate paymentSystemSpecificTemplate = new PaymentSystemSpecificTemplate();
    paymentSystemSpecificTemplate.setValue(value);
    paymentSystemSpecificTemplate.setTag("50");

    assertThat(paymentSystemSpecificTemplate.toString(), equalTo("50160004hoge0104abcd"));

  }

  @Test
  public void testSuccessToStringWhenValueIsNull() {

    final PaymentSystemSpecificTemplate paymentSystemSpecificTemplate = new PaymentSystemSpecificTemplate();
    paymentSystemSpecificTemplate.setTag("50");
    paymentSystemSpecificTemplate.setValue(null);

    assertThat(paymentSystemSpecificTemplate.toString(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToStringWhenValueIsEmpty() {

    final PaymentSystemSpecificTemplate PaymentSystemSpecificTemplate = new PaymentSystemSpecificTemplate();
    PaymentSystemSpecificTemplate.setValue(new PaymentSystemSpecific());

    assertThat(PaymentSystemSpecificTemplate.toString(), equalTo(StringUtils.EMPTY));
  }

}
