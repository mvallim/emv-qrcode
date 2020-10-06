package com.emv.qrcode.model.mpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.emv.qrcode.core.model.TagLengthString;

public class MerchantAccountInformationTemplateTest {

  @Test
  public void testSuccessToString() {

    final TagLengthString tagLengthString = new TagLengthString();
    tagLengthString.setTag("01");
    tagLengthString.setValue("abcd");

    final MerchantAccountInformation value = new MerchantAccountInformation();
    value.setGloballyUniqueIdentifier("hoge");
    value.addPaymentNetworkSpecific(tagLengthString);

    final MerchantAccountInformationTemplate merchantAccountInformation = new MerchantAccountInformationTemplate();
    merchantAccountInformation.setValue(value);
    merchantAccountInformation.setTag("02");

    assertThat(merchantAccountInformation.toString(), equalTo("02160004hoge0104abcd"));
  }

  @Test
  public void testSuccessToStringConstructorTag() {

    final TagLengthString tagLengthString = new TagLengthString();
    tagLengthString.setTag("01");
    tagLengthString.setValue("abcd");

    final MerchantAccountInformation value = new MerchantAccountInformation();
    value.setGloballyUniqueIdentifier("hoge");
    value.addPaymentNetworkSpecific(tagLengthString);

    final MerchantAccountInformationTemplate merchantAccountInformation = new MerchantAccountInformationTemplate("02");
    merchantAccountInformation.setValue(value);

    assertThat(merchantAccountInformation.toString(), equalTo("02160004hoge0104abcd"));
  }

  @Test
  public void testSuccessToStringConstructorTagAndGloballyUniqueIdentifier() {

    final MerchantAccountInformationTemplate merchantAccountInformation = new MerchantAccountInformationTemplate("02", "hoge");
    merchantAccountInformation.addPaymentNetworkSpecific("01", "abcd");

    assertThat(merchantAccountInformation.toString(), equalTo("02160004hoge0104abcd"));
  }

  @Test
  public void testSuccessToStringWhenValueIsNull() {

    final MerchantAccountInformationTemplate merchantAccountInformation = new MerchantAccountInformationTemplate();
    merchantAccountInformation.setTag("02");
    merchantAccountInformation.setValue(null);

    assertThat(merchantAccountInformation.toString(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToStringWhenValueIsEmpty() {

    final MerchantAccountInformationTemplate merchantAccountInformation = new MerchantAccountInformationTemplate();
    merchantAccountInformation.setValue(new MerchantAccountInformation());

    assertThat(merchantAccountInformation.toString(), equalTo(StringUtils.EMPTY));
  }

}
