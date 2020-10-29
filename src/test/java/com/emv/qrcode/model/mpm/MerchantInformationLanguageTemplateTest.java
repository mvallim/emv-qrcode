package com.emv.qrcode.model.mpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.emv.qrcode.core.model.TagLengthString;

public class MerchantInformationLanguageTemplateTest {

  @Test
  public void testSuccessToString() {

    final TagLengthString rFUforEMVCo = new TagLengthString();
    rFUforEMVCo.setTag("03");
    rFUforEMVCo.setValue("abcd");

    final MerchantInformationLanguage value = new MerchantInformationLanguage();
    value.setLanguagePreference("ZH");
    value.setMerchantCity("最佳运输");
    value.setMerchantName("北京");
    value.addRFUforEMVCo(rFUforEMVCo);

    final MerchantInformationLanguageTemplate merchantInformationLanguage = new MerchantInformationLanguageTemplate();
    merchantInformationLanguage.setValue(value);

    assertThat(merchantInformationLanguage.toString(), equalTo("64280002ZH0102北京0204最佳运输0304abcd"));
  }

  @Test
  public void testSuccessToStringWhenValueIsNull() {

    final MerchantInformationLanguageTemplate merchantInformationLanguage = new MerchantInformationLanguageTemplate();
    merchantInformationLanguage.setValue(null);

    assertThat(merchantInformationLanguage.toString(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToStringWhenValueIsEmpty() {

    final MerchantInformationLanguageTemplate merchantInformationLanguage = new MerchantInformationLanguageTemplate();
    merchantInformationLanguage.setValue(new MerchantInformationLanguage());

    assertThat(merchantInformationLanguage.toString(), equalTo(StringUtils.EMPTY));
  }

}
