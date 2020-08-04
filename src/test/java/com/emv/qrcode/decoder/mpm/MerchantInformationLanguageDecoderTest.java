package com.emv.qrcode.decoder.mpm;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.decoder.Decoder;
import com.emv.qrcode.model.mpm.MerchantInformationLanguage;
import com.emv.qrcode.model.mpm.MerchantInformationLanguageValue;

public class MerchantInformationLanguageDecoderTest {

  @Test
  public void testSuccessDecode() {
    final MerchantInformationLanguage merchantInformationLanguage = Decoder.decode("64280002ZH0104最佳运输0202北京0304abcd", MerchantInformationLanguage.class);

    assertThat(merchantInformationLanguage.getValue(), not(nullValue()));

    assertThat(merchantInformationLanguage.getTag(), equalTo("64"));
    assertThat(merchantInformationLanguage.getLength(), equalTo(28));

    assertThat(merchantInformationLanguage.getValue().getLanguagePreference(), not(nullValue()));
    assertThat(merchantInformationLanguage.getValue().getMerchantName(), not(nullValue()));
    assertThat(merchantInformationLanguage.getValue().getMerchantCity(), not(nullValue()));
    assertThat(merchantInformationLanguage.getValue().getRFUforEMVCo(), hasSize(1));

    assertThat(merchantInformationLanguage.getValue().getLanguagePreference().getTag(), equalTo("00"));
    assertThat(merchantInformationLanguage.getValue().getLanguagePreference().getLength(), equalTo(2));
    assertThat(merchantInformationLanguage.getValue().getLanguagePreference().getValue(), equalTo("ZH"));

    assertThat(merchantInformationLanguage.getValue().getMerchantName().getTag(), equalTo("01"));
    assertThat(merchantInformationLanguage.getValue().getMerchantName().getLength(), equalTo(4));
    assertThat(merchantInformationLanguage.getValue().getMerchantName().getValue(), equalTo("最佳运输"));

    assertThat(merchantInformationLanguage.getValue().getMerchantCity().getTag(), equalTo("02"));
    assertThat(merchantInformationLanguage.getValue().getMerchantCity().getLength(), equalTo(2));
    assertThat(merchantInformationLanguage.getValue().getMerchantCity().getValue(), equalTo("北京"));

    assertThat(merchantInformationLanguage.getValue().getRFUforEMVCo().get(0).getTag(), equalTo("03"));
    assertThat(merchantInformationLanguage.getValue().getRFUforEMVCo().get(0).getLength(), equalTo(4));
    assertThat(merchantInformationLanguage.getValue().getRFUforEMVCo().get(0).getValue(), equalTo("abcd"));

  }

  @Test
  public void testSuccessDecodeEncode() {
    final MerchantInformationLanguage merchantInformationLanguage = Decoder.decode("64280002ZH0104最佳运输0202北京0304abcd", MerchantInformationLanguage.class);

    assertThat(merchantInformationLanguage.toString(), equalTo("64280002ZH0104最佳运输0202北京0304abcd"));
  }

  @Test
  public void testSuccessEncode() {

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

}
