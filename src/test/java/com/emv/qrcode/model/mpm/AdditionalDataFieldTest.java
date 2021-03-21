package com.emv.qrcode.model.mpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.emv.qrcode.core.model.mpm.TagLengthString;

public class AdditionalDataFieldTest {

  @Test
  public void testSuccessToString() {

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

  @Test
  public void testSuccessToStringWithoutPaymentSystemSpecific() {

    final TagLengthString rFUforEMVCo = new TagLengthString();
    rFUforEMVCo.setTag("10");
    rFUforEMVCo.setValue("abcd");

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
    additionalDataField.addRFUforEMVCo(rFUforEMVCo);

    assertThat(additionalDataField.toString(), equalTo("0105123450205678900305098760405543210505abcde0605fghij0705klmno0805pqres0905tuvxy1004abcd"));

  }

  @Test
  public void testSuccessToStringWhenValueIsNull() {

    final AdditionalDataField additionalDataField = new AdditionalDataField();

    assertThat(additionalDataField.toString(), equalTo(StringUtils.EMPTY));
  }

}
