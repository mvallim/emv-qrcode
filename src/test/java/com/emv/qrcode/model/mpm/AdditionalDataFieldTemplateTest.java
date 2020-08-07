package com.emv.qrcode.model.mpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.emv.qrcode.core.model.TagLengthString;

public class AdditionalDataFieldTemplateTest {

  @Test
  public void testSuccessToString() {

    final TagLengthString paymentSystemSpecific = new TagLengthString();
    paymentSystemSpecific.setTag("50");
    paymentSystemSpecific.setValue("ijkl");

    final TagLengthString rFUforEMVCo = new TagLengthString();
    rFUforEMVCo.setTag("10");
    rFUforEMVCo.setValue("abcd");

    final AdditionalDataField value = new AdditionalDataField();
    value.setAdditionalConsumerDataRequest("tuvxy");
    value.setBillNumber("12345");
    value.setCustomerLabel("fghij");
    value.setLoyaltyNumber("54321");
    value.setMobileNumber("67890");
    value.setPurposeTransaction("pqres");
    value.setReferenceLabel("abcde");
    value.setStoreLabel("09876");
    value.setTerminalLabel("klmno");
    value.addPaymentSystemSpecific(paymentSystemSpecific);
    value.addRFUforEMVCo(rFUforEMVCo);

    final AdditionalDataFieldTemplate additionalDataField = new AdditionalDataFieldTemplate();
    additionalDataField.setValue(value);

    assertThat(additionalDataField.toString(), equalTo("62970105123450205678900305098760405543210505abcde0605fghij0705klmno0805pqres0905tuvxy1004abcd5004ijkl"));

  }

  @Test
  public void testSuccessToStringWhenValueIsNull() {

    final AdditionalDataFieldTemplate additionalDataField = new AdditionalDataFieldTemplate();
    additionalDataField.setValue(null);

    assertThat(additionalDataField.toString(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToStringWhenValueIsEmpty() {

    final AdditionalDataFieldTemplate additionalDataField = new AdditionalDataFieldTemplate();
    additionalDataField.setValue(new AdditionalDataField());

    assertThat(additionalDataField.toString(), equalTo(StringUtils.EMPTY));
  }

}
