package com.emv.qrcode.decoder.mpm;

import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Test;

import com.emv.qrcode.core.exception.DuplicateTagException;
import com.emv.qrcode.core.exception.InvalidTagException;
import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.core.model.mpm.TagLengthString;
import com.emv.qrcode.model.mpm.MerchantAccountInformationReservedAdditional;

public class MerchantAccountInformationReservedAdditionalDecoderTest {

  @Test
  public void testSuccessDecode() throws PresentedModeException {
    final MerchantAccountInformationReservedAdditional merchantAccountInformation = DecoderMpm.decode("02160004hoge0104abcd", MerchantAccountInformationReservedAdditional.class);

    assertThat(merchantAccountInformation.getGloballyUniqueIdentifier(), not(nullValue()));
    assertThat(merchantAccountInformation.getPaymentNetworkSpecific().size(), equalTo(1));

    assertThat(merchantAccountInformation.getGloballyUniqueIdentifier().getTag(), equalTo("00"));
    assertThat(merchantAccountInformation.getGloballyUniqueIdentifier().getLength(), equalTo(4));
    assertThat(merchantAccountInformation.getGloballyUniqueIdentifier().getValue(), equalTo("hoge"));

    assertThat(merchantAccountInformation.getPaymentNetworkSpecific().get("01").getTag(), equalTo("01"));
    assertThat(merchantAccountInformation.getPaymentNetworkSpecific().get("01").getLength(), equalTo(4));
    assertThat(merchantAccountInformation.getPaymentNetworkSpecific().get("01").getValue(), equalTo("abcd"));

  }

  @Test
  public void testFailDecode() throws PresentedModeException {
    final DuplicateTagException duplicateTagException = catchThrowableOfType(() -> DecoderMpm.decode("02160104abcd0104abcd", MerchantAccountInformationReservedAdditional.class), DuplicateTagException.class);
    assertThat(duplicateTagException.getTag(), equalTo("01"));
    assertThat(duplicateTagException.getValue(), equalTo("0104abcd"));
    assertThat(duplicateTagException.getMessage(), equalTo("Scope: 'MerchantAccountInformation' informed already contains '01' tag"));

    final InvalidTagException invalidTagException = catchThrowableOfType(() -> DecoderMpm.decode("02160104abcdAA04abcd", MerchantAccountInformationReservedAdditional.class), InvalidTagException.class);
    assertThat(invalidTagException.getTag(), equalTo("AA"));
    assertThat(invalidTagException.getValue(), equalTo("AA04abcd"));
    assertThat(invalidTagException.getMessage(), equalTo("Scope: 'MerchantAccountInformation' invalid 'AA' tag"));
  }

  @Test
  public void testSuccessDecodeEncode() throws PresentedModeException {
    final MerchantAccountInformationReservedAdditional merchantAccountInformation = DecoderMpm.decode("02160004hoge0104abcd", MerchantAccountInformationReservedAdditional.class);

    assertThat(merchantAccountInformation.toString(), equalTo("0004hoge0104abcd"));
  }

  @Test
  public void testSuccessEncode() {

    final TagLengthString paymentNetworkSpecific = new TagLengthString();
    paymentNetworkSpecific.setTag("01");
    paymentNetworkSpecific.setValue("abcd");

    final MerchantAccountInformationReservedAdditional merchantAccountInformation = new MerchantAccountInformationReservedAdditional();
    merchantAccountInformation.setGloballyUniqueIdentifier("hoge");
    merchantAccountInformation.addPaymentNetworkSpecific(paymentNetworkSpecific);

    assertThat(merchantAccountInformation.toString(), equalTo("0004hoge0104abcd"));
  }

}
