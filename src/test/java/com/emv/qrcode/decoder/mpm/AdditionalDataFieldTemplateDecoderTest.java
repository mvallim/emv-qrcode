package com.emv.qrcode.decoder.mpm;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Test;

import com.emv.qrcode.core.exception.MerchantPresentedModeException;
import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.model.mpm.AdditionalDataField;
import com.emv.qrcode.model.mpm.AdditionalDataFieldTemplate;
import com.emv.qrcode.model.mpm.PaymentSystemSpecific;
import com.emv.qrcode.model.mpm.PaymentSystemSpecificTemplate;

public class AdditionalDataFieldTemplateDecoderTest {

  @Test
  public void testSuccessDecode() throws MerchantPresentedModeException {
    final AdditionalDataFieldTemplate additionalDataField = DecoderMpm.decode("62950105123450205678900305098760405543210505abcde0605fghij0705klmno0805pqres0905tuvxy5010000110101i", AdditionalDataFieldTemplate.class);

    assertThat(additionalDataField.getValue().getAdditionalConsumerDataRequest(), not(nullValue()));
    assertThat(additionalDataField.getValue().getBillNumber(), not(nullValue()));
    assertThat(additionalDataField.getValue().getCustomerLabel(), not(nullValue()));
    assertThat(additionalDataField.getValue().getLoyaltyNumber(), not(nullValue()));
    assertThat(additionalDataField.getValue().getMobileNumber(), not(nullValue()));
    assertThat(additionalDataField.getValue().getPaymentSystemSpecific().entrySet(), hasSize(1));
    assertThat(additionalDataField.getValue().getPurposeTransaction(), not(nullValue()));
    assertThat(additionalDataField.getValue().getReferenceLabel(), not(nullValue()));
    assertThat(additionalDataField.getValue().getRFUforEMVCo().size(), equalTo(0));
    assertThat(additionalDataField.getValue().getStoreLabel(), not(nullValue()));
    assertThat(additionalDataField.getValue().getTerminalLabel(), not(nullValue()));

    assertThat(additionalDataField.getValue().getAdditionalConsumerDataRequest().getTag(), equalTo("09"));
    assertThat(additionalDataField.getValue().getAdditionalConsumerDataRequest().getLength(), equalTo(5));
    assertThat(additionalDataField.getValue().getAdditionalConsumerDataRequest().getValue(), equalTo("tuvxy"));

    assertThat(additionalDataField.getValue().getBillNumber().getTag(), equalTo("01"));
    assertThat(additionalDataField.getValue().getBillNumber().getLength(), equalTo(5));
    assertThat(additionalDataField.getValue().getBillNumber().getValue(), equalTo("12345"));

    assertThat(additionalDataField.getValue().getCustomerLabel().getTag(), equalTo("06"));
    assertThat(additionalDataField.getValue().getCustomerLabel().getLength(), equalTo(5));
    assertThat(additionalDataField.getValue().getCustomerLabel().getValue(), equalTo("fghij"));

    assertThat(additionalDataField.getValue().getLoyaltyNumber().getTag(), equalTo("04"));
    assertThat(additionalDataField.getValue().getLoyaltyNumber().getLength(), equalTo(5));
    assertThat(additionalDataField.getValue().getLoyaltyNumber().getValue(), equalTo("54321"));

    assertThat(additionalDataField.getValue().getMobileNumber().getTag(), equalTo("02"));
    assertThat(additionalDataField.getValue().getMobileNumber().getLength(), equalTo(5));
    assertThat(additionalDataField.getValue().getMobileNumber().getValue(), equalTo("67890"));

    assertThat(additionalDataField.getValue().getPurposeTransaction().getTag(), equalTo("08"));
    assertThat(additionalDataField.getValue().getPurposeTransaction().getLength(), equalTo(5));
    assertThat(additionalDataField.getValue().getPurposeTransaction().getValue(), equalTo("pqres"));

    assertThat(additionalDataField.getValue().getReferenceLabel().getTag(), equalTo("05"));
    assertThat(additionalDataField.getValue().getReferenceLabel().getLength(), equalTo(5));
    assertThat(additionalDataField.getValue().getReferenceLabel().getValue(), equalTo("abcde"));

    assertThat(additionalDataField.getValue().getStoreLabel().getTag(), equalTo("03"));
    assertThat(additionalDataField.getValue().getStoreLabel().getLength(), equalTo(5));
    assertThat(additionalDataField.getValue().getStoreLabel().getValue(), equalTo("09876"));

    assertThat(additionalDataField.getValue().getTerminalLabel().getTag(), equalTo("07"));
    assertThat(additionalDataField.getValue().getTerminalLabel().getLength(), equalTo(5));
    assertThat(additionalDataField.getValue().getTerminalLabel().getValue(), equalTo("klmno"));

  }

  @Test
  public void testSuccessDecodeEncode() throws MerchantPresentedModeException {
    final AdditionalDataFieldTemplate additionalDataField = DecoderMpm.decode("62950105123450205678900305098760405543210505abcde0605fghij0705klmno0805pqres0905tuvxy5010000110101i", AdditionalDataFieldTemplate.class);

    assertThat(additionalDataField.toString(), equalTo("62950105123450205678900305098760405543210505abcde0605fghij0705klmno0805pqres0905tuvxy5010000110101i"));
  }

  @Test
  public void testSuccessEncode() {

    final PaymentSystemSpecific paymentSystemSpecific = new PaymentSystemSpecific();
    paymentSystemSpecific.setGloballyUniqueIdentifier("1");
    paymentSystemSpecific.addPaymentSystemSpecific(new TagLengthString("01", "i"));

    final PaymentSystemSpecificTemplate paymentSystemSpecificTemplate = new PaymentSystemSpecificTemplate();
    paymentSystemSpecificTemplate.setTag("50");
    paymentSystemSpecificTemplate.setValue(paymentSystemSpecific);

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
    value.addPaymentSystemSpecific(paymentSystemSpecificTemplate);

    final AdditionalDataFieldTemplate additionalDataField = new AdditionalDataFieldTemplate();
    additionalDataField.setValue(value);

    assertThat(additionalDataField.toString(), equalTo("62950105123450205678900305098760405543210505abcde0605fghij0705klmno0805pqres0905tuvxy5010000110101i"));

  }

}
