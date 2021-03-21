package com.emv.qrcode.decoder.cpm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import com.emv.qrcode.core.exception.DuplicateTagException;
import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.model.cpm.ApplicationTemplate;
import com.emv.qrcode.model.cpm.CommonDataTemplate;
import com.emv.qrcode.model.cpm.CommonDataTransparentTemplate;
import com.emv.qrcode.model.cpm.ConsumerPresentedMode;
import com.emv.qrcode.model.cpm.PayloadFormatIndicator;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;
import com.emv.qrcode.model.cpm.constants.TagTransactionProcessingCodes;

// @formatter:off
public class ConsumerPresentedModeDecoderTest {

  @Test
  public void testSuccessDecode() throws Exception {
    final String encoded = "hQVDUFYwMWETTwegAAAAVVVVUAhQcm9kdWN0MWETTwegAAAAZmZmUA"
      + "hQcm9kdWN0MmJJWggSNFZ4kBI0WF8gDkNBUkRIT0xERVIvRU1WXy0IcnVlc2RlZW5kIZ8QBwY"
      + "BCgMAAACfJghYT9OF+iNLzJ82AgABnzcEbVjvEw==";

    final ConsumerPresentedMode consumerPresentedMode = DecoderCpm.decode(encoded, ConsumerPresentedMode.class);

    final PayloadFormatIndicator payloadFormatIndicator = consumerPresentedMode.getPayloadFormatIndicator();
    assertThat(payloadFormatIndicator.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_PAYLOAD_FORMAT_INDICATOR));
    assertThat(payloadFormatIndicator.getStringValue(), equalTo("CPV01"));

    final ApplicationTemplate applicationTemplate1 = consumerPresentedMode.getApplicationTemplates().get(0);
    assertThat(applicationTemplate1.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_APPLICATION_TEMPLATE));
    assertThat(applicationTemplate1.getApplicationDefinitionFileName().getTag(), equalTo(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME));
    assertThat(applicationTemplate1.getApplicationDefinitionFileName().getStringValue(), equalTo("A0000000555555"));
    assertThat(applicationTemplate1.getApplicationLabel().getTag(), equalTo(TagTransactionProcessingCodes.ID_APPLICATION_LABEL));
    assertThat(applicationTemplate1.getApplicationLabel().getStringValue(), equalTo("Product1"));

    final ApplicationTemplate applicationTemplate2 = consumerPresentedMode.getApplicationTemplates().get(1);
    assertThat(applicationTemplate2.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_APPLICATION_TEMPLATE));
    assertThat(applicationTemplate2.getApplicationDefinitionFileName().getTag(), equalTo(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME));
    assertThat(applicationTemplate2.getApplicationDefinitionFileName().getStringValue(), equalTo("A0000000666666"));
    assertThat(applicationTemplate2.getApplicationLabel().getTag(), equalTo(TagTransactionProcessingCodes.ID_APPLICATION_LABEL));
    assertThat(applicationTemplate2.getApplicationLabel().getStringValue(), equalTo("Product2"));

    final CommonDataTemplate commonDataTemplate = consumerPresentedMode.getCommonDataTemplate();
    assertThat(commonDataTemplate.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TEMPLATE));
    assertThat(commonDataTemplate.getApplicationPAN().getTag(), equalTo(TagTransactionProcessingCodes.ID_APPLICATION_PAN));
    assertThat(commonDataTemplate.getApplicationPAN().getStringValue(), equalTo("1234567890123458"));
    assertThat(commonDataTemplate.getCardholderName().getTag(), equalTo(TagTransactionProcessingCodes.ID_CARDHOLDER_NAME));
    assertThat(commonDataTemplate.getCardholderName().getStringValue(), equalTo("CARDHOLDER/EMV"));
    assertThat(commonDataTemplate.getLanguagePreference().getTag(), equalTo(TagTransactionProcessingCodes.ID_LANGUAGE_PREFERENCE));
    assertThat(commonDataTemplate.getLanguagePreference().getStringValue(), equalTo("ruesdeen"));

    final CommonDataTransparentTemplate commonDataTransparentTemplate = commonDataTemplate.getCommonDataTransparentTemplate();
    assertThat(commonDataTransparentTemplate.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TRANSPARENT_TEMPLATE));
    assertThat(commonDataTransparentTemplate.getIssuerApplicationData().getTag(), equalTo(TagTransactionProcessingCodes.ID_ISSUER_APPLICATION_DATA));
    assertThat(commonDataTransparentTemplate.getIssuerApplicationData().getStringValue(), equalTo("06010A03000000"));
    assertThat(commonDataTransparentTemplate.getApplicationCryptogram().getTag(), equalTo(TagTransactionProcessingCodes.ID_APPLICATION_CRYPTOGRAM));
    assertThat(commonDataTransparentTemplate.getApplicationCryptogram().getStringValue(), equalTo("584FD385FA234BCC"));
    assertThat(commonDataTransparentTemplate.getApplicationTransactionCounter().getTag(), equalTo(TagTransactionProcessingCodes.ID_APPLICATION_TRANSACTION_COUNTER));
    assertThat(commonDataTransparentTemplate.getApplicationTransactionCounter().getStringValue(), equalTo("0001"));
    assertThat(commonDataTransparentTemplate.getUnpredictableNumber().getTag(), equalTo(TagTransactionProcessingCodes.ID_UNPREDICTABLE_NUMBER));
    assertThat(commonDataTransparentTemplate.getUnpredictableNumber().getStringValue(), equalTo("6D58EF13"));

    final byte[] source2 = Hex.decodeHex("8505435056303185054350563031");

    final DuplicateTagException duplicateTagException = catchThrowableOfType(() -> DecoderCpm.decode(source2, ConsumerPresentedMode.class), DuplicateTagException.class);

    assertThat(duplicateTagException.getMessage(), equalTo("Scope: 'ConsumerPresentedMode' informed already contains '85' tag"));
    assertThat(duplicateTagException.getTag(), equalTo("85"));
    assertThat(duplicateTagException.getValue(), equalTo("85054350563031"));
  }

  @Test
  public void testSuccessDecodeUnionPay() throws PresentedModeException, IOException, DecoderException {
    final String encoded = "hQVDUFYwMWFWTwigAAADMwEBAlcRYmNgAQBxNnLSMSIBAAABiAFfNAEAYzOfJghWite85mtxi58nAYCfEBEHAAEDoAAAAQgwMDAwMDAwMZ82AhEiggIAAJ83BCJgSI4=";

    final ConsumerPresentedMode consumerPresentedMode = DecoderCpm.decode(encoded, ConsumerPresentedMode.class);

    assertThat(consumerPresentedMode.getPayloadFormatIndicator().getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_PAYLOAD_FORMAT_INDICATOR));
    assertThat(consumerPresentedMode.getPayloadFormatIndicator().getStringValue(), equalTo("CPV01"));

    final ApplicationTemplate applicationTemplate = consumerPresentedMode.getApplicationTemplates().get(0);
    assertThat(applicationTemplate.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_APPLICATION_TEMPLATE));
    assertThat(applicationTemplate.getApplicationDefinitionFileName().getTag(), equalTo(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME));
    assertThat(applicationTemplate.getApplicationDefinitionFileName().getStringValue(), equalTo("A000000333010102"));
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
