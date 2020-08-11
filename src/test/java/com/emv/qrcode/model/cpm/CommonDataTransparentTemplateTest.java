package com.emv.qrcode.model.cpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.emv.qrcode.core.model.BERTLString;

public class CommonDataTransparentTemplateTest {

  @Test
  public void testSuccessToHex() throws IOException {

    final CommonDataTransparentTemplate commonDataTransparentTemplate = new CommonDataTransparentTemplate();

    final BERTLString value = new BERTLString(0x0, "1234");

    commonDataTransparentTemplate.setValue(value);

    assertThat(commonDataTransparentTemplate.getTag(), equalTo(0x64));
    assertThat(commonDataTransparentTemplate.getValue(), equalTo(value));
    assertThat(commonDataTransparentTemplate.toHex(), equalTo("6406000431323334"));

  }

  @Test
  public void testSuccessToHexWhenValueIsEmpty() throws IOException {

    final CommonDataTransparentTemplate commonDataTransparentTemplate = new CommonDataTransparentTemplate();

    final BERTLString value = new BERTLString(0x0, StringUtils.EMPTY);

    commonDataTransparentTemplate.setValue(value);

    assertThat(commonDataTransparentTemplate.toHex(), equalTo(StringUtils.EMPTY));

  }

  @Test
  public void testSuccessToHexWhenValueIsNull() throws IOException {

    final CommonDataTransparentTemplate commonDataTransparentTemplate = new CommonDataTransparentTemplate();

    commonDataTransparentTemplate.setValue(null);

    assertThat(commonDataTransparentTemplate.toHex(), equalTo(StringUtils.EMPTY));

  }

}
