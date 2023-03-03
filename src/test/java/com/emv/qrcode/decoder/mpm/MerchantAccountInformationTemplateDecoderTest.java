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
import com.emv.qrcode.model.mpm.MerchantAccountInformationReserved;
import com.emv.qrcode.model.mpm.MerchantAccountInformationReservedAdditional;
import com.emv.qrcode.model.mpm.MerchantAccountInformationTemplate;

public class MerchantAccountInformationTemplateDecoderTest {

  @Test
  public void testSuccessDecodeReserved() throws PresentedModeException {
    final MerchantAccountInformationTemplate merchantAccountInformation = DecoderMpm.decode("02040004", MerchantAccountInformationTemplate.class);

    assertThat(merchantAccountInformation.getValue(), not(nullValue()));

    assertThat(merchantAccountInformation.getTag(), equalTo("02"));
    assertThat(merchantAccountInformation.getLength(), equalTo(4));

    final MerchantAccountInformationReserved value = merchantAccountInformation.getTypeValue(MerchantAccountInformationReserved.class);

    assertThat(value, not(nullValue()));
    assertThat(value.getValue(), equalTo("0004"));
  }

  @Test
  public void testSuccessDecodeReservedAdditional() throws PresentedModeException {
    final MerchantAccountInformationTemplate merchantAccountInformation = DecoderMpm.decode("26160004hoge0104abcd", MerchantAccountInformationTemplate.class);

    assertThat(merchantAccountInformation.getValue(), not(nullValue()));

    assertThat(merchantAccountInformation.getTag(), equalTo("26"));
    assertThat(merchantAccountInformation.getLength(), equalTo(16));

    final MerchantAccountInformationReservedAdditional value = merchantAccountInformation.getTypeValue(MerchantAccountInformationReservedAdditional.class);

    assertThat(value.getGloballyUniqueIdentifier(), not(nullValue()));
    assertThat(value.getPaymentNetworkSpecific().size(), equalTo(1));

    assertThat(value.getGloballyUniqueIdentifier().getTag(), equalTo("00"));
    assertThat(value.getGloballyUniqueIdentifier().getLength(), equalTo(4));
    assertThat(value.getGloballyUniqueIdentifier().getValue(), equalTo("hoge"));

    assertThat(value.getPaymentNetworkSpecific().get("01").getTag(), equalTo("01"));
    assertThat(value.getPaymentNetworkSpecific().get("01").getLength(), equalTo(4));
    assertThat(value.getPaymentNetworkSpecific().get("01").getValue(), equalTo("abcd"));

  }

  @Test
  public void testSuccessDecodeEncode() throws PresentedModeException {
    final MerchantAccountInformationTemplate merchantAccountInformation1 = DecoderMpm.decode("02160004hoge0104abcd", MerchantAccountInformationTemplate.class);
    assertThat(merchantAccountInformation1.toString(), equalTo("02160004hoge0104abcd"));

    final MerchantAccountInformationTemplate merchantAccountInformation2 = DecoderMpm.decode("26160004hoge0104abcd", MerchantAccountInformationTemplate.class);
    assertThat(merchantAccountInformation2.toString(), equalTo("26160004hoge0104abcd"));
  }

  @Test
  public void testSuccessEncodeReserved() {

    final MerchantAccountInformationReserved value = new MerchantAccountInformationReserved("00004");

    final MerchantAccountInformationTemplate merchantAccountInformation = new MerchantAccountInformationTemplate();
    merchantAccountInformation.setValue(value);
    merchantAccountInformation.setTag("02");

    assertThat(merchantAccountInformation.toString(), equalTo("020500004"));
  }

  @Test
  public void testSuccessEncodeReservedAdditional() {

    final TagLengthString paymentNetworkSpecific = new TagLengthString();
    paymentNetworkSpecific.setTag("01");
    paymentNetworkSpecific.setValue("abcd");

    final MerchantAccountInformationReservedAdditional value = new MerchantAccountInformationReservedAdditional();
    value.setGloballyUniqueIdentifier("hoge");
    value.addPaymentNetworkSpecific(paymentNetworkSpecific);

    final MerchantAccountInformationTemplate merchantAccountInformation = new MerchantAccountInformationTemplate();
    merchantAccountInformation.setValue(value);
    merchantAccountInformation.setTag("26");

    assertThat(merchantAccountInformation.toString(), equalTo("26160004hoge0104abcd"));
  }

}
