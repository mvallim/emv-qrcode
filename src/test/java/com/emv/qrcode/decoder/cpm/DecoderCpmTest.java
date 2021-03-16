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

public class DecoderCpmTest {

  @Test
  public void testSuccessDecode() throws PresentedModeException, IOException {
    final String encoded = "hQVDUFYwMWEaTwegAAAAVVVVVw8SNFZ4kBI0WNGRIgESNF8=";

    final ConsumerPresentedMode merchantPresentedMode = DecoderCpm.decode(encoded, ConsumerPresentedMode.class);

    assertThat(merchantPresentedMode.toHex(), equalTo("85054350563031611A4F07A0000000555555570F1234567890123458D191220112345F"));

    assertThat(merchantPresentedMode.getPayloadFormatIndicator().getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_PAYLOAD_FORMAT_INDICATOR));
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
