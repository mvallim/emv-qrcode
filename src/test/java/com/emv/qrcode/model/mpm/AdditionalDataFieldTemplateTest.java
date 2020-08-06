package com.emv.qrcode.model.mpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.emv.qrcode.core.model.TagLengthString;

public class AdditionalDataFieldTemplateTest {

  @Test
  public void testSuccessToString() {

    final TagLengthString additionalConsumerDataRequest = new TagLengthString();
    additionalConsumerDataRequest.setTag("09");
    additionalConsumerDataRequest.setValue("tuvxy");

    final TagLengthString billNumber = new TagLengthString();
    billNumber.setTag("01");
    billNumber.setValue("12345");

    final TagLengthString customerLabel = new TagLengthString();
    customerLabel.setTag("06");
    customerLabel.setValue("fghij");

    final TagLengthString loyaltyNumber = new TagLengthString();
    loyaltyNumber.setTag("04");
    loyaltyNumber.setValue("54321");

    final TagLengthString mobileNumber = new TagLengthString();
    mobileNumber.setTag("02");
    mobileNumber.setValue("67890");

    final TagLengthString purposeTransaction = new TagLengthString();
    purposeTransaction.setTag("08");
    purposeTransaction.setValue("pqres");

    final TagLengthString referenceLabel = new TagLengthString();
    referenceLabel.setTag("05");
    referenceLabel.setValue("abcde");

    final TagLengthString storeLabel = new TagLengthString();
    storeLabel.setTag("03");
    storeLabel.setValue("09876");

    final TagLengthString terminalLabel = new TagLengthString();
    terminalLabel.setTag("07");
    terminalLabel.setValue("klmno");

    final TagLengthString paymentSystemSpecific = new TagLengthString();
    paymentSystemSpecific.setTag("50");
    paymentSystemSpecific.setValue("ijkl");

    final TagLengthString rFUforEMVCo = new TagLengthString();
    rFUforEMVCo.setTag("10");
    rFUforEMVCo.setValue("abcd");

    final AdditionalDataField value = new AdditionalDataField();
    value.setAdditionalConsumerDataRequest(additionalConsumerDataRequest);
    value.setBillNumber(billNumber);
    value.setCustomerLabel(customerLabel);
    value.setLoyaltyNumber(loyaltyNumber);
    value.setMobileNumber(mobileNumber);
    value.setPurposeTransaction(purposeTransaction);
    value.setReferenceLabel(referenceLabel);
    value.setStoreLabel(storeLabel);
    value.setTerminalLabel(terminalLabel);
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
