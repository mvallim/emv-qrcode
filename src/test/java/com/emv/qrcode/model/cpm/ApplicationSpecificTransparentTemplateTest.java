package com.emv.qrcode.model.cpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.emv.qrcode.core.model.BERTLString;

public class ApplicationSpecificTransparentTemplateTest {

  @Test
  public void testSuccessToHex() throws IOException {

    final ApplicationSpecificTransparentTemplate applicationSpecificTransparentTemplate = new ApplicationSpecificTransparentTemplate();

    final BERTLString value = new BERTLString(0x0, "1234");

    applicationSpecificTransparentTemplate.setValue(value);

    assertThat(applicationSpecificTransparentTemplate.getTag(), equalTo(0x63));
    assertThat(applicationSpecificTransparentTemplate.getValue(), equalTo(value));
    assertThat(applicationSpecificTransparentTemplate.toHex(), equalTo("6306000431323334"));

  }

  @Test
  public void testSuccessToHexWhenValueIsEmpty() throws IOException {

    final ApplicationSpecificTransparentTemplate applicationSpecificTransparentTemplate = new ApplicationSpecificTransparentTemplate();

    final BERTLString value = new BERTLString(0x0, StringUtils.EMPTY);

    applicationSpecificTransparentTemplate.setValue(value);

    assertThat(applicationSpecificTransparentTemplate.toHex(), equalTo(StringUtils.EMPTY));

  }

  @Test
  public void testSuccessToHexWhenValueIsNull() throws IOException {

    final ApplicationSpecificTransparentTemplate applicationSpecificTransparentTemplate = new ApplicationSpecificTransparentTemplate();

    applicationSpecificTransparentTemplate.setValue(null);

    assertThat(applicationSpecificTransparentTemplate.toHex(), equalTo(StringUtils.EMPTY));

  }

}
