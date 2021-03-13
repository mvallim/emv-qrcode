package com.emv.qrcode.decoder.mpm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import com.emv.qrcode.core.exception.InvalidMerchantPresentedModeException;
import com.emv.qrcode.model.mpm.MerchantPresentedMode;

public class DecoderMpmTest {

  @Test
  public void testSuccessDecode() throws InvalidMerchantPresentedModeException {
    final String encoded = "00020101021102160004hoge0104abcd520441115303156540523.725502015603500570155802CN5914BEST TRANSPORT6007BEIJING6107123456762950105123450205678900305098760405543210505abcde0605fghij0705klmno0805pqres0905tuvxy5010000110101i64280002ZH0102北京0204最佳运输0304abcd65020080320016A01122334499887707081234567863044220";

    final MerchantPresentedMode merchantPresentedMode = DecoderMpm.decode(encoded, MerchantPresentedMode.class);

    assertThat(merchantPresentedMode.getCountryCode().getValue(), equalTo("CN"));
    assertThat(merchantPresentedMode.getMerchantCategoryCode().getValue(), equalTo("4111"));
    assertThat(merchantPresentedMode.getMerchantCity().getValue(), equalTo("BEIJING"));
    assertThat(merchantPresentedMode.getMerchantName().getValue(), equalTo("BEST TRANSPORT"));
    assertThat(merchantPresentedMode.getPayloadFormatIndicator().getValue(), equalTo("01"));
    assertThat(merchantPresentedMode.getPointOfInitiationMethod().getValue(), equalTo("11"));
    assertThat(merchantPresentedMode.getPostalCode().getValue(), equalTo("1234567"));
    assertThat(merchantPresentedMode.getTipOrConvenienceIndicator().getValue(), equalTo("01"));
    assertThat(merchantPresentedMode.getTransactionAmount().getValue(), equalTo("23.72"));
    assertThat(merchantPresentedMode.getTransactionCurrency().getValue(), equalTo("156"));
    assertThat(merchantPresentedMode.getValueOfConvenienceFeeFixed().getValue(), equalTo("500"));
  }

  @Test
  public void testFailDecode() {
    final String encoded = "00020101021102160004hoge0104abcd520441115303156540523.7255020256035005802CN5914BEST TRANSPORT6007BEIJING6107123456762950105123450205678900305098760405543210505abcde0605fghij0705klmno0805pqres0903tuv1004abcd5004ijkl64280002ZH0102北京0204最佳运输0304abcd65020080320016A0112233449988770708123456786304C659";

    final Throwable throwable = catchThrowable(() -> DecoderMpm.decode(encoded, Object.class));

    assertThat(throwable).isInstanceOf(RuntimeException.class);

    final RuntimeException runtimeException = RuntimeException.class.cast(throwable);

    assertThat(runtimeException.getCause()).isInstanceOf(NullPointerException.class);

  }

}
