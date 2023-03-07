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
import com.emv.qrcode.model.mpm.PaymentSystemSpecific;
import com.emv.qrcode.model.mpm.PaymentSystemSpecificTemplate;

public class PaymentSystemSpecificTemplateDecoderTest {

  @Test
  public void testSuccessDecode() throws PresentedModeException {
    final PaymentSystemSpecificTemplate paymentSystemSpecificTemplate = DecoderMpm.decode("51160004abcd10041234", PaymentSystemSpecificTemplate.class);

    assertThat(paymentSystemSpecificTemplate.getValue(), not(nullValue()));

    assertThat(paymentSystemSpecificTemplate.getTag(), equalTo("51"));
    assertThat(paymentSystemSpecificTemplate.getLength(), equalTo(16));

    assertThat(paymentSystemSpecificTemplate.getValue().getGloballyUniqueIdentifier(), not(nullValue()));
    assertThat(paymentSystemSpecificTemplate.getValue().getGloballyUniqueIdentifier().getTag(), equalTo("00"));
    assertThat(paymentSystemSpecificTemplate.getValue().getGloballyUniqueIdentifier().getLength(), equalTo(4));
    assertThat(paymentSystemSpecificTemplate.getValue().getGloballyUniqueIdentifier().getValue(), equalTo("abcd"));

    assertThat(paymentSystemSpecificTemplate.getValue().getPaymentSystemSpecific(), not(nullValue()));
    assertThat(paymentSystemSpecificTemplate.getValue().getPaymentSystemSpecific().get("10").getTag(), equalTo("10"));
    assertThat(paymentSystemSpecificTemplate.getValue().getPaymentSystemSpecific().get("10").getLength(), equalTo(4));
    assertThat(paymentSystemSpecificTemplate.getValue().getPaymentSystemSpecific().get("10").getValue(), equalTo("1234"));

  }

  @Test
  public void testSuccessDecodeEncode() throws PresentedModeException {
    final PaymentSystemSpecificTemplate paymentSystemSpecificTemplate = DecoderMpm.decode("51160004abcd10041234", PaymentSystemSpecificTemplate.class);

    assertThat(paymentSystemSpecificTemplate.toString(), equalTo("51160004abcd10041234"));
  }

  @Test
  public void testSuccessEncode() {

    final PaymentSystemSpecific value = new PaymentSystemSpecific();
    value.setGloballyUniqueIdentifier("abcd");
    value.addPaymentSystemSpecific("10", "1234");

    final PaymentSystemSpecificTemplate paymentSystemSpecificTemplate = new PaymentSystemSpecificTemplate();
    paymentSystemSpecificTemplate.setTag("51");
    paymentSystemSpecificTemplate.setValue(value);

    assertThat(paymentSystemSpecificTemplate.toString(), equalTo("51160004abcd10041234"));
  }

}
