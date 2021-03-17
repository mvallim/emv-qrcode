package com.emv.qrcode.model.mpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.emv.qrcode.core.model.mpm.TagLengthString;

public class MerchantInformationLanguageTest {

  @Test
  public void testSuccessToString() {

    final TagLengthString rFUforEMVCo = new TagLengthString();
    rFUforEMVCo.setTag("03");
    rFUforEMVCo.setValue("abcd");

    final MerchantInformationLanguage merchantInformationLanguage = new MerchantInformationLanguage();
    merchantInformationLanguage.setLanguagePreference("ZH");
    merchantInformationLanguage.setMerchantCity("最佳运输");
    merchantInformationLanguage.setMerchantName("北京");
    merchantInformationLanguage.addRFUforEMVCo(rFUforEMVCo);

    assertThat(merchantInformationLanguage.toString(), equalTo("0002ZH0102北京0204最佳运输0304abcd"));
  }

  @Test
  public void testSuccessToStringWhenValueIsNull() {

    final MerchantInformationLanguage merchantInformationLanguage = new MerchantInformationLanguage();

    assertThat(merchantInformationLanguage.toString(), equalTo(StringUtils.EMPTY));
  }

}
