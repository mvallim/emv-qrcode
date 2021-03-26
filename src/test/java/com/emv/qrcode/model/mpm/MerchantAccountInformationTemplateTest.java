package com.emv.qrcode.model.mpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.emv.qrcode.core.model.mpm.TagLengthString;

public class MerchantAccountInformationTemplateTest {

  @Test
  public void testSuccessToString() {

    final TagLengthString tagLengthString = new TagLengthString();
    tagLengthString.setTag("01");
    tagLengthString.setValue("abcd");

    final MerchantAccountInformationReservedAdditional value = new MerchantAccountInformationReservedAdditional();
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

    final MerchantAccountInformationReservedAdditional value = new MerchantAccountInformationReservedAdditional();
    value.setGloballyUniqueIdentifier("hoge");
    value.addPaymentNetworkSpecific(tagLengthString);

    final MerchantAccountInformationTemplate merchantAccountInformation = new MerchantAccountInformationTemplate("02");
    merchantAccountInformation.setValue(value);

    assertThat(merchantAccountInformation.toString(), equalTo("02160004hoge0104abcd"));
  }

  @Test
  public void testSuccessToStringConstructorTagAndMerchantAccountInformation() {

    final TagLengthString tagLengthString = new TagLengthString();
    tagLengthString.setTag("01");
    tagLengthString.setValue("abcd");

    final MerchantAccountInformationReservedAdditional value = new MerchantAccountInformationReservedAdditional();
    value.setGloballyUniqueIdentifier("hoge");
    value.addPaymentNetworkSpecific(tagLengthString);

    final MerchantAccountInformationTemplate merchantAccountInformation = new MerchantAccountInformationTemplate("26", value);

    assertThat(merchantAccountInformation.toString(), equalTo("26160004hoge0104abcd"));
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
    merchantAccountInformation.setValue(new MerchantAccountInformationReservedAdditional());

    assertThat(merchantAccountInformation.toString(), equalTo(StringUtils.EMPTY));
  }

}
