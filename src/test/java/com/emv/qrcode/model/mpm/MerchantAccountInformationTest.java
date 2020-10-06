package com.emv.qrcode.model.mpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.emv.qrcode.core.model.TagLengthString;

public class MerchantAccountInformationTest {

  @Test
  public void testSuccessToString() {

    final TagLengthString tagLengthString = new TagLengthString();
    tagLengthString.setTag("01");
    tagLengthString.setValue("abcd");

    final MerchantAccountInformation merchantAccountInformation = new MerchantAccountInformation();
    merchantAccountInformation.setGloballyUniqueIdentifier("hoge");
    merchantAccountInformation.addPaymentNetworkSpecific(tagLengthString);

    assertThat(merchantAccountInformation.toString(), equalTo("0004hoge0104abcd"));
  }

  @Test
  public void testSuccessToStringConstructorGloballyUniqueIdentifier() {
    final MerchantAccountInformation merchantAccountInformation = new MerchantAccountInformation("hoge");
    assertThat(merchantAccountInformation.toString(), equalTo("0004hoge"));
  }

  @Test
  public void testSuccessToStringConstructorGloballyUniqueIdentifierAndPaymentNetworkSpecific() {
    final MerchantAccountInformation merchantAccountInformation = new MerchantAccountInformation("hoge", "01", "abcd");
    assertThat(merchantAccountInformation.toString(), equalTo("0004hoge0104abcd"));
  }

  @Test
  public void testSuccessToStringConstructorGloballyUniqueIdentifierIsNull() {
    final MerchantAccountInformation merchantAccountInformation = new MerchantAccountInformation(null);
    assertThat(merchantAccountInformation.toString(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToStringConstructorGloballyUniqueIdentifierAndPaymentNetworkSpecificIsNull() {
    final MerchantAccountInformation merchantAccountInformation = new MerchantAccountInformation(null, null, null);
    assertThat(merchantAccountInformation.toString(), equalTo(StringUtils.EMPTY));
  }

}
