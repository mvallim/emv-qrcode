package com.emv.qrcode.decoder.mpm;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.decoder.Decoder;
import com.emv.qrcode.model.mpm.Unreserved;
import com.emv.qrcode.model.mpm.UnreservedValue;

public class UnreservedDecoderTest {

  @Test
  public void testSuccessDecode() {
    final Unreserved unreserved = Decoder.decode("91320016A011223344998877070812345678", Unreserved.class);

    assertThat(unreserved.getValue(), not(nullValue()));

    assertThat(unreserved.getTag(), equalTo("91"));
    assertThat(unreserved.getLength(), equalTo(32));

    assertThat(unreserved.getValue().getContextSpecificData(), not(nullValue()));
    assertThat(unreserved.getValue().getGloballyUniqueIdentifier(), not(nullValue()));

    assertThat(unreserved.getValue().getContextSpecificData(), hasSize(1));
    assertThat(unreserved.getValue().getContextSpecificData().get(0).getTag(), equalTo("07"));
    assertThat(unreserved.getValue().getContextSpecificData().get(0).getLength(), equalTo(8));
    assertThat(unreserved.getValue().getContextSpecificData().get(0).getValue(), equalTo("12345678"));

    assertThat(unreserved.getValue().getGloballyUniqueIdentifier().getTag(), equalTo("00"));
    assertThat(unreserved.getValue().getGloballyUniqueIdentifier().getLength(), equalTo(16));
    assertThat(unreserved.getValue().getGloballyUniqueIdentifier().getValue(), equalTo("A011223344998877"));
  }

  @Test
  public void testSuccessDecodeEncode() {
    final Unreserved unreserved = Decoder.decode("91320016A011223344998877070812345678", Unreserved.class);

    assertThat(unreserved.toString(), equalTo("91320016A011223344998877070812345678"));
  }

  @Test
  public void testSuccessEncode() {

    final TagLengthString globallyUniqueIdentifier = new TagLengthString();
    globallyUniqueIdentifier.setTag("00");
    globallyUniqueIdentifier.setValue("A011223344998877");

    final TagLengthString contextSpecificData = new TagLengthString();
    contextSpecificData.setTag("07");
    contextSpecificData.setValue("12345678");

    final UnreservedValue value = new UnreservedValue();
    value.setGloballyUniqueIdentifier(globallyUniqueIdentifier);
    value.addContextSpecificData(contextSpecificData);

    final Unreserved unreserved = new Unreserved();
    unreserved.setValue(value);
    unreserved.setTag("91");

    assertThat(unreserved.toString(), equalTo("91320016A011223344998877070812345678"));
  }

}
