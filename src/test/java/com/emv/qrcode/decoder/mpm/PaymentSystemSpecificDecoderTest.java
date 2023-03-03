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
import com.emv.qrcode.model.mpm.PaymentSystemSpecific;

public class PaymentSystemSpecificDecoderTest {

  @Test
  public void testSuccessDecode() throws PresentedModeException {
    final PaymentSystemSpecific PaymentSystemSpecific = DecoderMpm.decode("51160004abcd10041234", PaymentSystemSpecific.class);

    assertThat(PaymentSystemSpecific.getGloballyUniqueIdentifier(), not(nullValue()));
    assertThat(PaymentSystemSpecific.getGloballyUniqueIdentifier().getTag(), equalTo("00"));
    assertThat(PaymentSystemSpecific.getGloballyUniqueIdentifier().getLength(), equalTo(4));
    assertThat(PaymentSystemSpecific.getGloballyUniqueIdentifier().getValue(), equalTo("abcd"));

    assertThat(PaymentSystemSpecific.getPaymentSystemSpecific(), not(nullValue()));
    assertThat(PaymentSystemSpecific.getPaymentSystemSpecific().get("10").getTag(), equalTo("10"));
    assertThat(PaymentSystemSpecific.getPaymentSystemSpecific().get("10").getLength(), equalTo(4));
    assertThat(PaymentSystemSpecific.getPaymentSystemSpecific().get("10").getValue(), equalTo("1234"));

  }

  @Test
  public void testFailDecode() throws PresentedModeException {
    final DuplicateTagException duplicateTagException = catchThrowableOfType(() -> DecoderMpm.decode("51240004abcd0004abcd10041234", PaymentSystemSpecific.class), DuplicateTagException.class);
    assertThat(duplicateTagException.getTag(), equalTo("00"));
    assertThat(duplicateTagException.getValue(), equalTo("0004abcd"));
    assertThat(duplicateTagException.getMessage(), equalTo("Scope: 'PaymentSystemSpecific' informed already contains '00' tag"));

    final InvalidTagException invalidTagException = catchThrowableOfType(() -> DecoderMpm.decode("5116AA04abcd10041234", PaymentSystemSpecific.class), InvalidTagException.class);
    assertThat(invalidTagException.getTag(), equalTo("AA"));
    assertThat(invalidTagException.getValue(), equalTo("AA04abcd"));
    assertThat(invalidTagException.getMessage(), equalTo("Scope: 'PaymentSystemSpecific' invalid 'AA' tag"));
  }

  @Test
  public void testSuccessDecodeEncode() throws PresentedModeException {
    final PaymentSystemSpecific paymentSystemSpecific = DecoderMpm.decode("51160004abcd10041234", PaymentSystemSpecific.class);

    assertThat(paymentSystemSpecific.toString(), equalTo("0004abcd10041234"));
  }

  @Test
  public void testSuccessEncode() {

    final PaymentSystemSpecific paymentSystemSpecific = new PaymentSystemSpecific();
    paymentSystemSpecific.setGloballyUniqueIdentifier("abcd");
    paymentSystemSpecific.addPaymentSystemSpecific("10", "1234");

    assertThat(paymentSystemSpecific.toString(), equalTo("0004abcd10041234"));
  }

}
