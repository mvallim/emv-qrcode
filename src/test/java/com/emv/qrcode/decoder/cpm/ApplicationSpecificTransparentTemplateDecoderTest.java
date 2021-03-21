package com.emv.qrcode.decoder.cpm;

import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import com.emv.qrcode.core.exception.DuplicateTagException;
import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.model.cpm.ApplicationSpecificTransparentTemplate;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;
import com.emv.qrcode.model.cpm.constants.TagTransactionProcessingCodes;

public class ApplicationSpecificTransparentTemplateDecoderTest {

  @Test
  public void testSuccessDecode() throws PresentedModeException, IOException, DecoderException {

    final byte[] source2 = Hex.decodeHex("631A4F07A0000000555555570F1234567890123458D191220112345F");

    final ApplicationSpecificTransparentTemplate applicationSpecificTransparentTemplate = DecoderCpm.decode(source2, ApplicationSpecificTransparentTemplate.class);

    assertThat(applicationSpecificTransparentTemplate, not(nullValue()));

    assertThat(applicationSpecificTransparentTemplate.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_APPLICATION_SPECIFIC_TRANSPARENT_TEMPLATE));
    assertThat(applicationSpecificTransparentTemplate.getApplicationDefinitionFileName().getTag(), equalTo(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME));
    assertThat(applicationSpecificTransparentTemplate.getTrack2EquivalentData().getTag(), equalTo(TagTransactionProcessingCodes.ID_TRACK_2_EQUIVALENT_DATA));

    final byte[] source3 = Hex.decodeHex("63234F07A00000005555554F07A0000000555555570F1234567890123458D191220112345F");

    final DuplicateTagException duplicateTagException = catchThrowableOfType(() -> DecoderCpm.decode(source3, ApplicationSpecificTransparentTemplate.class), DuplicateTagException.class);

    assertThat(duplicateTagException.getMessage(), equalTo("Scope: 'ApplicationSpecificTransparentTemplate' informed already contains '4F' tag"));
    assertThat(duplicateTagException.getTag(), equalTo("4F"));
    assertThat(duplicateTagException.getValue(), equalTo("4F07A0000000555555"));

  }

  @Test
  public void testSuccessDecodeEncode() throws PresentedModeException, IOException, DecoderException {
    final byte[] source = Hex.decodeHex("631A4F07A0000000555555570F1234567890123458D191220112345F");
    final ApplicationSpecificTransparentTemplate applicationSpecificTransparentTemplate = DecoderCpm.decode(source, ApplicationSpecificTransparentTemplate.class);
    assertThat(applicationSpecificTransparentTemplate.getBytes(), equalTo(source));
  }

}
