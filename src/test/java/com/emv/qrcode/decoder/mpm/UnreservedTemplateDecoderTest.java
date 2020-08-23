package com.emv.qrcode.decoder.mpm;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.decoder.DecoderMpm;
import com.emv.qrcode.model.mpm.Unreserved;
import com.emv.qrcode.model.mpm.UnreservedTemplate;

public class UnreservedTemplateDecoderTest {

  @Test
  public void testSuccessDecode() {
    final UnreservedTemplate unreserved = DecoderMpm.decode("91320016A011223344998877070812345678", UnreservedTemplate.class);

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
    final UnreservedTemplate unreserved = DecoderMpm.decode("91320016A011223344998877070812345678", UnreservedTemplate.class);

    assertThat(unreserved.toString(), equalTo("91320016A011223344998877070812345678"));
  }

  @Test
  public void testSuccessEncode() {

    final TagLengthString contextSpecificData = new TagLengthString();
    contextSpecificData.setTag("07");
    contextSpecificData.setValue("12345678");

    final Unreserved value = new Unreserved();
    value.setGloballyUniqueIdentifier("A011223344998877");
    value.addContextSpecificData(contextSpecificData);

    final UnreservedTemplate unreserved = new UnreservedTemplate();
    unreserved.setValue(value);
    unreserved.setTag("91");

    assertThat(unreserved.toString(), equalTo("91320016A011223344998877070812345678"));
  }

}
