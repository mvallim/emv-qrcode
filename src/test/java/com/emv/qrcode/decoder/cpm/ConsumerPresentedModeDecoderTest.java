package com.emv.qrcode.decoder.cpm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.junit.Test;

import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.model.cpm.ConsumerPresentedMode;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;
import com.emv.qrcode.model.cpm.constants.TagTransactionProcessingCodes;

// @formatter:off
public class ConsumerPresentedModeDecoderTest {

  @Test
  public void testSuccessDecode() throws PresentedModeException, IOException {
    final String encoded = "hQVDUFYwMWETTwegAAAAVVVVUAhQcm9kdWN0MWETTwegAAAAZmZmUAhQcm9kdWN0MmJJWggSNFZ4kBI0WF8gDkNBUkRIT0xERVIvRU1WXy0IcnVlc2RlZW5kIZ8QBwYBCgMAAACfJghYT9OF+iNLzJ82AgABnzcEbVjvEw==";

    final ConsumerPresentedMode merchantPresentedMode = DecoderCpm.decode(encoded, ConsumerPresentedMode.class);

    assertThat(merchantPresentedMode.toHex(), equalTo("8505435056303161134F07A0000000555555500850726F647563743161134F07A0000000666666500850726F647563743262495A0812345678901234585F200E43415244484F4C4445522F454D565F2D08727565736465656E64219F100706010A030000009F2608584FD385FA234BCC9F360200019F37046D58EF13"));

    assertThat(merchantPresentedMode.getPayloadFormatIndicator().getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_PAYLOAD_FORMAT_INDICATOR));
    assertThat(merchantPresentedMode.getApplicationTemplates().get(0).getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_APPLICATION_TEMPLATE));
    assertThat(merchantPresentedMode.getApplicationTemplates().get(0).getApplicationDefinitionFileName().getTag(), equalTo(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME));
    assertThat(merchantPresentedMode.getApplicationTemplates().get(0).getApplicationLabel().getTag(), equalTo(TagTransactionProcessingCodes.ID_APPLICATION_LABEL));
    assertThat(merchantPresentedMode.getApplicationTemplates().get(1).getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_APPLICATION_TEMPLATE));
    assertThat(merchantPresentedMode.getApplicationTemplates().get(1).getApplicationDefinitionFileName().getTag(), equalTo(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME));
    assertThat(merchantPresentedMode.getApplicationTemplates().get(1).getApplicationLabel().getTag(), equalTo(TagTransactionProcessingCodes.ID_APPLICATION_LABEL));
    assertThat(merchantPresentedMode.getCommonDataTemplates().get(0).getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TEMPLATE));
    assertThat(merchantPresentedMode.getCommonDataTemplates().get(0).getApplicationPAN().getTag(), equalTo(TagTransactionProcessingCodes.ID_APPLICATION_PAN));
    assertThat(merchantPresentedMode.getCommonDataTemplates().get(0).getCardholderName().getTag(), equalTo(TagTransactionProcessingCodes.ID_CARDHOLDER_NAME));
    assertThat(merchantPresentedMode.getCommonDataTemplates().get(0).getLanguagePreference().getTag(), equalTo(TagTransactionProcessingCodes.ID_LANGUAGE_PREFERENCE));
    assertThat(merchantPresentedMode.getCommonDataTemplates().get(0).getCommonDataTransparentTemplate().getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TRANSPARENT_TEMPLATE));
    assertThat(merchantPresentedMode.getCommonDataTemplates().get(0).getCommonDataTransparentTemplate().getIssuerApplicationData().getTag(), equalTo(TagTransactionProcessingCodes.ID_ISSUER_APPLICATION_DATA));
    assertThat(merchantPresentedMode.getCommonDataTemplates().get(0).getCommonDataTransparentTemplate().getApplicationCryptogram().getTag(), equalTo(TagTransactionProcessingCodes.ID_APPLICATION_CRYPTOGRAM));
    assertThat(merchantPresentedMode.getCommonDataTemplates().get(0).getCommonDataTransparentTemplate().getApplicationTransactionCounter().getTag(), equalTo(TagTransactionProcessingCodes.ID_APPLICATION_TRANSACTION_COUNTER));
    assertThat(merchantPresentedMode.getCommonDataTemplates().get(0).getCommonDataTransparentTemplate().getUnpredictableNumber().getTag(), equalTo(TagTransactionProcessingCodes.ID_UNPREDICTABLE_NUMBER));
  }

  @Test
  public void testFailDecode() {
    final String encoded = "hQVDUFYwMWEaTwegAAAAVVVVVw8SNFZ4kBI0WNGRIgESNF8=";

    final Throwable throwable = catchThrowable(() -> DecoderCpm.decode(encoded, Object.class));

    assertThat(throwable).isInstanceOf(RuntimeException.class);

    final RuntimeException runtimeException = RuntimeException.class.cast(throwable);

    assertThat(runtimeException.getCause()).isInstanceOf(NullPointerException.class);

  }
}
// @formatter:on
