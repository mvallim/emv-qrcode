package com.emv.qrcode.model.mpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.emv.qrcode.core.model.TagLengthString;

public class UnreservedTemplateTest {

  @Test
  public void testSuccessToString() {

    final TagLengthString contextSpecificData = new TagLengthString();
    contextSpecificData.setTag("07");
    contextSpecificData.setValue("12345678");

    final Unreserved value = new Unreserved();
    value.setGloballyUniqueIdentifier("A011223344998877");
    value.addContextSpecificData(contextSpecificData);

    final UnreservedTemplate unreservedTemplate = new UnreservedTemplate();
    unreservedTemplate.setValue(value);
    unreservedTemplate.setTag("91");

    assertThat(unreservedTemplate.toString(), equalTo("91320016A011223344998877070812345678"));
  }

  @Test
  public void testSuccessConstructorTag() {

    final TagLengthString contextSpecificData = new TagLengthString();
    contextSpecificData.setTag("07");
    contextSpecificData.setValue("12345678");

    final Unreserved value = new Unreserved("07");
    value.setGloballyUniqueIdentifier("A011223344998877");
    value.addContextSpecificData(contextSpecificData);

    final UnreservedTemplate unreservedTemplate = new UnreservedTemplate("91");
    unreservedTemplate.setValue(value);

    assertThat(unreservedTemplate.toString(), equalTo("91320016A011223344998877070812345678"));
  }

  @Test
  public void testSuccessConstructorTagAndGloballyUniqueIdentifier() {

    final TagLengthString contextSpecificData = new TagLengthString();
    contextSpecificData.setTag("07");
    contextSpecificData.setValue("12345678");

    final UnreservedTemplate unreservedTemplate = new UnreservedTemplate("91", "A011223344998877");
    unreservedTemplate.addContextSpecificData("07", "12345678");

    assertThat(unreservedTemplate.toString(), equalTo("91320016A011223344998877070812345678"));
  }

  @Test
  public void testSuccessToStringWhenValueIsNull() {

    final UnreservedTemplate unreservedTemplate = new UnreservedTemplate();
    unreservedTemplate.setTag("91");
    unreservedTemplate.setValue(null);

    assertThat(unreservedTemplate.toString(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToStringWhenValueIsEmpty() {

    final UnreservedTemplate unreservedTemplate = new UnreservedTemplate();
    unreservedTemplate.setTag("91");
    unreservedTemplate.setValue(new Unreserved());

    assertThat(unreservedTemplate.toString(), equalTo(StringUtils.EMPTY));
  }

}
