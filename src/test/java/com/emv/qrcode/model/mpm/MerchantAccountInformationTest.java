package com.emv.qrcode.model.mpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.emv.qrcode.core.model.TagLengthString;

public class MerchantAccountInformationTest {

  @Test
  public void testSuccessToString() {

    final TagLengthString globallyUniqueIdentifier = new TagLengthString();
    globallyUniqueIdentifier.setTag("00");
    globallyUniqueIdentifier.setValue("hoge");

    final TagLengthString tagLengthString = new TagLengthString();
    tagLengthString.setTag("01");
    tagLengthString.setValue("abcd");

    final MerchantAccountInformationValue value = new MerchantAccountInformationValue();
    value.setGloballyUniqueIdentifier(globallyUniqueIdentifier);
    value.addPaymentNetworkSpecific(tagLengthString);

    final MerchantAccountInformation merchantAccountInformation = new MerchantAccountInformation();
    merchantAccountInformation.setValue(value);
    merchantAccountInformation.setTag("02");

    assertThat(merchantAccountInformation.toString(), equalTo("02160004hoge0104abcd"));
  }

  @Test
  public void testSuccessToStringWhenValueIsNull() {

    final MerchantAccountInformation merchantAccountInformation = new MerchantAccountInformation();
    merchantAccountInformation.setTag("02");
    merchantAccountInformation.setValue(null);

    assertThat(merchantAccountInformation.toString(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToStringWhenValueIsEmpty() {

    final MerchantAccountInformation merchantAccountInformation = new MerchantAccountInformation();
    merchantAccountInformation.setValue(new MerchantAccountInformationValue());

    assertThat(merchantAccountInformation.toString(), equalTo(StringUtils.EMPTY));
  }

}
