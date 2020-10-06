package com.emv.qrcode.model.mpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.emv.qrcode.core.model.TagLengthString;

public class PaymentSystemSpecificTest {

  @Test
  public void testSuccessToString() {

    final PaymentSystemSpecific paymentSystemSpecific = new PaymentSystemSpecific();
    paymentSystemSpecific.setGloballyUniqueIdentifier("hoge");
    paymentSystemSpecific.addPaymentSystemSpecific(new TagLengthString("01", "abcd"));

    assertThat(paymentSystemSpecific.toString(), equalTo("0004hoge0104abcd"));
  }

  @Test
  public void testSuccessToStringContructorGloballyUniqueIdentifier() {

    final PaymentSystemSpecific paymentSystemSpecific = new PaymentSystemSpecific("hoge");
    paymentSystemSpecific.addPaymentSystemSpecific(new TagLengthString("01", "abcd"));

    assertThat(paymentSystemSpecific.toString(), equalTo("0004hoge0104abcd"));
  }

  @Test
  public void testSuccessToStringContructorGloballyUniqueIdentifierAndPaymentSystemSpecific() {
    final PaymentSystemSpecific paymentSystemSpecific = new PaymentSystemSpecific("hoge", "01", "abcd");

    assertThat(paymentSystemSpecific.toString(), equalTo("0004hoge0104abcd"));
  }

  @Test
  public void testSuccessToStringSetGloballyUniqueIdentifierAndPaymentSystemSpecific() {
    final PaymentSystemSpecific paymentSystemSpecific = new PaymentSystemSpecific();
    paymentSystemSpecific.setGloballyUniqueIdentifier("hoge", new TagLengthString("01", "abcd"));

    assertThat(paymentSystemSpecific.toString(), equalTo("0004hoge0104abcd"));
  }

  @Test
  public void testSuccessToStringContructorGloballyUniqueIdentifierIsNull() {
    final PaymentSystemSpecific paymentSystemSpecific = new PaymentSystemSpecific(null);

    assertThat(paymentSystemSpecific.toString(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToStringContructorGloballyUniqueIdentifierIsNullAndPaymentSystemSpecificIsNull() {
    final PaymentSystemSpecific paymentSystemSpecific = new PaymentSystemSpecific(null, null, null);

    assertThat(paymentSystemSpecific.toString(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToStringIsEmpty() {
    final PaymentSystemSpecific paymentSystemSpecific = new PaymentSystemSpecific("");

    assertThat(paymentSystemSpecific.toString(), equalTo(StringUtils.EMPTY));
  }

}
