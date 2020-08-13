package com.emv.qrcode.model.mpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.emv.qrcode.core.model.TagLengthString;

public class PaymentSystemSpecificTemplateTest {

  @Test
  public void testSuccessToString() {

    final PaymentSystemSpecific value = new PaymentSystemSpecific();
    value.setGloballyUniqueIdentifier("hoge");
    value.addPaymentNetworkSpecific(new TagLengthString("01", "abcd"));

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
