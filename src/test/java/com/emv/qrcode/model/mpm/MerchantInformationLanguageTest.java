package com.emv.qrcode.model.mpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.emv.qrcode.core.model.TagLengthString;

public class MerchantInformationLanguageTest {

  @Test
  public void testSuccessToString() {

    final TagLengthString languagePreference = new TagLengthString();
    languagePreference.setTag("00");
    languagePreference.setValue("ZH");

    final TagLengthString merchantCity = new TagLengthString();
    merchantCity.setTag("01");
    merchantCity.setValue("最佳运输");

    final TagLengthString merchantName = new TagLengthString();
    merchantName.setTag("02");
    merchantName.setValue("北京");

    final TagLengthString tagLengthString = new TagLengthString();
    tagLengthString.setTag("03");
    tagLengthString.setValue("abcd");

    final MerchantInformationLanguageValue value = new MerchantInformationLanguageValue();
    value.setLanguagePreference(languagePreference);
    value.setMerchantCity(merchantCity);
    value.setMerchantName(merchantName);
    value.addRFUforEMVCo(tagLengthString);

    final MerchantInformationLanguage merchantInformationLanguage = new MerchantInformationLanguage();
    merchantInformationLanguage.setValue(value);

    assertThat(merchantInformationLanguage.toString(), equalTo("64280002ZH0202北京0104最佳运输0304abcd"));
  }

  @Test
  public void testSuccessToStringWhenValueIsNull() {

    final MerchantInformationLanguage merchantInformationLanguage = new MerchantInformationLanguage();
    merchantInformationLanguage.setValue(null);

    assertThat(merchantInformationLanguage.toString(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToStringWhenValueIsEmpty() {

    final MerchantInformationLanguage merchantInformationLanguage = new MerchantInformationLanguage();
    merchantInformationLanguage.setValue(new MerchantInformationLanguageValue());

    assertThat(merchantInformationLanguage.toString(), equalTo(StringUtils.EMPTY));
  }

}
