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

import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import org.junit.jupiter.api.Test;

import com.emv.qrcode.core.exception.DuplicateTagException;
import com.emv.qrcode.core.exception.InvalidTagException;
import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.core.model.mpm.TagLengthString;
import com.emv.qrcode.model.mpm.AdditionalDataField;
import com.emv.qrcode.model.mpm.PaymentSystemSpecific;
import com.emv.qrcode.model.mpm.PaymentSystemSpecificTemplate;

public class AdditionalDataFieldDecoderTest {

  @Test
  public void testSuccessDecode() throws PresentedModeException {
    final AdditionalDataField additionalDataField = DecoderMpm.decode("62800102120202670302090402540502ab0602fg0702kl0802pq0902tu1002TX1102MC5010000110101i", AdditionalDataField.class);

    assertThat(additionalDataField.getAdditionalConsumerDataRequest(), not(nullValue()));
    assertThat(additionalDataField.getBillNumber(), not(nullValue()));
    assertThat(additionalDataField.getCustomerLabel(), not(nullValue()));
    assertThat(additionalDataField.getLoyaltyNumber(), not(nullValue()));
    assertThat(additionalDataField.getMobileNumber(), not(nullValue()));
    assertThat(additionalDataField.getPaymentSystemSpecific().entrySet(), hasSize(1));
    assertThat(additionalDataField.getPurposeTransaction(), not(nullValue()));
    assertThat(additionalDataField.getReferenceLabel(), not(nullValue()));
    assertThat(additionalDataField.getRFUforEMVCo().size(), equalTo(0));
    assertThat(additionalDataField.getStoreLabel(), not(nullValue()));
    assertThat(additionalDataField.getTerminalLabel(), not(nullValue()));

    assertThat(additionalDataField.getAdditionalConsumerDataRequest().getTag(), equalTo("09"));
    assertThat(additionalDataField.getAdditionalConsumerDataRequest().getLength(), equalTo(2));
    assertThat(additionalDataField.getAdditionalConsumerDataRequest().getValue(), equalTo("tu"));

    assertThat(additionalDataField.getBillNumber().getTag(), equalTo("01"));
    assertThat(additionalDataField.getBillNumber().getLength(), equalTo(2));
    assertThat(additionalDataField.getBillNumber().getValue(), equalTo("12"));

    assertThat(additionalDataField.getCustomerLabel().getTag(), equalTo("06"));
    assertThat(additionalDataField.getCustomerLabel().getLength(), equalTo(2));
    assertThat(additionalDataField.getCustomerLabel().getValue(), equalTo("fg"));

    assertThat(additionalDataField.getLoyaltyNumber().getTag(), equalTo("04"));
    assertThat(additionalDataField.getLoyaltyNumber().getLength(), equalTo(2));
    assertThat(additionalDataField.getLoyaltyNumber().getValue(), equalTo("54"));

    assertThat(additionalDataField.getMobileNumber().getTag(), equalTo("02"));
    assertThat(additionalDataField.getMobileNumber().getLength(), equalTo(2));
    assertThat(additionalDataField.getMobileNumber().getValue(), equalTo("67"));

    assertThat(additionalDataField.getPurposeTransaction().getTag(), equalTo("08"));
    assertThat(additionalDataField.getPurposeTransaction().getLength(), equalTo(2));
    assertThat(additionalDataField.getPurposeTransaction().getValue(), equalTo("pq"));

    assertThat(additionalDataField.getReferenceLabel().getTag(), equalTo("05"));
    assertThat(additionalDataField.getReferenceLabel().getLength(), equalTo(2));
    assertThat(additionalDataField.getReferenceLabel().getValue(), equalTo("ab"));

    assertThat(additionalDataField.getStoreLabel().getTag(), equalTo("03"));
    assertThat(additionalDataField.getStoreLabel().getLength(), equalTo(2));
    assertThat(additionalDataField.getStoreLabel().getValue(), equalTo("09"));

    assertThat(additionalDataField.getTerminalLabel().getTag(), equalTo("07"));
    assertThat(additionalDataField.getTerminalLabel().getLength(), equalTo(2));
    assertThat(additionalDataField.getTerminalLabel().getValue(), equalTo("kl"));

    assertThat(additionalDataField.getMerchantTaxId(), not(nullValue()));
    assertThat(additionalDataField.getMerchantTaxId().getTag(), equalTo("10"));
    assertThat(additionalDataField.getMerchantTaxId().getLength(), equalTo(2));
    assertThat(additionalDataField.getMerchantTaxId().getValue(), equalTo("TX"));

    assertThat(additionalDataField.getMerchantChannel(), not(nullValue()));
    assertThat(additionalDataField.getMerchantChannel().getTag(), equalTo("11"));
    assertThat(additionalDataField.getMerchantChannel().getLength(), equalTo(2));
    assertThat(additionalDataField.getMerchantChannel().getValue(), equalTo("MC"));
  }

  @Test
  public void testFailDecode() throws PresentedModeException {
    final DuplicateTagException duplicateTagException = catchThrowableOfType(DuplicateTagException.class, () -> DecoderMpm.decode("622311011010512345010512345", AdditionalDataField.class));
    assertThat(duplicateTagException.getTag(), equalTo("01"));
    assertThat(duplicateTagException.getValue(), equalTo("010512345"));
    assertThat(duplicateTagException.getMessage(), equalTo("Scope: 'AdditionalDataField' informed already contains '01' tag"));

    final InvalidTagException invalidTagException = catchThrowableOfType(InvalidTagException.class, () -> DecoderMpm.decode("621411011AA0512345", AdditionalDataField.class));
    assertThat(invalidTagException.getTag(), equalTo("AA"));
    assertThat(invalidTagException.getValue(), equalTo("AA0512345"));
    assertThat(invalidTagException.getMessage(), equalTo("Scope: 'AdditionalDataField' invalid 'AA' tag"));
  }

  @Test
  public void testSuccessDecodeEncode() throws PresentedModeException {
    final AdditionalDataField additionalDataField = DecoderMpm.decode("62800102120202670302090402540502ab0602fg0702kl0802pq0902tu1002TX1102MC5010000110101i", AdditionalDataField.class);

    assertThat(additionalDataField.toString(), equalTo("0102120202670302090402540502ab0602fg0702kl0802pq0902tu1002TX1102MC5010000110101i"));
  }

  @Test
  public void testSuccessEncode() {

    final PaymentSystemSpecific paymentSystemSpecific = new PaymentSystemSpecific();
    paymentSystemSpecific.setGloballyUniqueIdentifier("1");
    paymentSystemSpecific.addPaymentSystemSpecific(new TagLengthString("01", "i"));

    final PaymentSystemSpecificTemplate paymentSystemSpecificTemplate = new PaymentSystemSpecificTemplate();
    paymentSystemSpecificTemplate.setTag("50");
    paymentSystemSpecificTemplate.setValue(paymentSystemSpecific);

    final AdditionalDataField additionalDataField = new AdditionalDataField();
    additionalDataField.setAdditionalConsumerDataRequest("tuvxy");
    additionalDataField.setBillNumber("12345");
    additionalDataField.setCustomerLabel("fghij");
    additionalDataField.setLoyaltyNumber("54321");
    additionalDataField.setMobileNumber("67890");
    additionalDataField.setPurposeTransaction("pqres");
    additionalDataField.setReferenceLabel("abcde");
    additionalDataField.setStoreLabel("09876");
    additionalDataField.setTerminalLabel("klmno");
    additionalDataField.addPaymentSystemSpecific(paymentSystemSpecificTemplate);

    assertThat(additionalDataField.toString(), equalTo("0105123450205678900305098760405543210505abcde0605fghij0705klmno0805pqres0905tuvxy5010000110101i"));

  }

}
