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
import com.emv.qrcode.model.cpm.ApplicationTemplate;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;
import com.emv.qrcode.model.cpm.constants.TagTransactionProcessingCodes;

// @formatter:off
public class ApplicationTemplateDecoderTest {

  @Test
  public void testSuccessDecode() throws PresentedModeException, IOException, DecoderException {

    final byte[] source1 = Hex.decodeHex("611A4F07A0000000555555570F1234567890123458D191220112345F");

    final ApplicationTemplate applicationTemplate1 = DecoderCpm.decode(source1, ApplicationTemplate.class);

    assertThat(applicationTemplate1, not(nullValue()));

    assertThat(applicationTemplate1.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_APPLICATION_TEMPLATE));
    assertThat(applicationTemplate1.getApplicationDefinitionFileName().getTag(), equalTo(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME));
    assertThat(applicationTemplate1.getTrack2EquivalentData().getTag(), equalTo(TagTransactionProcessingCodes.ID_TRACK_2_EQUIVALENT_DATA));

    final byte[] source2 = Hex.decodeHex("611C631A4F07A0000000555555570F1234567890123458D191220112345F");

    final ApplicationTemplate applicationTemplate2 = DecoderCpm.decode(source2, ApplicationTemplate.class);

    assertThat(applicationTemplate2, not(nullValue()));

    assertThat(applicationTemplate2.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_APPLICATION_TEMPLATE));
    assertThat(applicationTemplate2.getApplicationSpecificTransparentTemplate().getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_APPLICATION_SPECIFIC_TRANSPARENT_TEMPLATE));
    assertThat(applicationTemplate2.getApplicationSpecificTransparentTemplate().getApplicationDefinitionFileName().getTag(), equalTo(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME));
    assertThat(applicationTemplate2.getApplicationSpecificTransparentTemplate().getTrack2EquivalentData().getTag(), equalTo(TagTransactionProcessingCodes.ID_TRACK_2_EQUIVALENT_DATA));

    final byte[] source3 = Hex.decodeHex("61234F07A00000005555554F07A0000000555555570F1234567890123458D191220112345F");

    final DuplicateTagException duplicateTagException = catchThrowableOfType(() -> DecoderCpm.decode(source3, ApplicationTemplate.class), DuplicateTagException.class);

    assertThat(duplicateTagException.getMessage(), equalTo("Scope: 'ApplicationTemplate' informed already contains '4F' tag"));
    assertThat(duplicateTagException.getTag(), equalTo("4F"));
    assertThat(duplicateTagException.getValue(), equalTo("4F07A0000000555555"));

  }

  @Test
  public void testSuccessDecodeEncode() throws PresentedModeException, IOException, DecoderException {
    final byte[] source = Hex.decodeHex("611A4F07A0000000555555570F1234567890123458D191220112345F");
    final ApplicationTemplate applicationTemplate = DecoderCpm.decode(source, ApplicationTemplate.class);
    assertThat(applicationTemplate.getBytes(), equalTo(source));
  }

}
// @formatter:on
