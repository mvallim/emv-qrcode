package com.emv.qrcode.model.mpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.emv.qrcode.core.model.mpm.TagLengthString;

public class UnreservedTest {

  @Test
  public void testSuccessToString() {

    final TagLengthString contextSpecificData = new TagLengthString();
    contextSpecificData.setTag("07");
    contextSpecificData.setValue("12345678");

    final Unreserved unreserved = new Unreserved();
    unreserved.setGloballyUniqueIdentifier("A011223344998877");
    unreserved.addContextSpecificData(contextSpecificData);

    assertThat(unreserved.toString(), equalTo("0016A011223344998877070812345678"));
  }

  @Test
  public void testSuccessConstructorTag() {

    final TagLengthString contextSpecificData = new TagLengthString();
    contextSpecificData.setTag("07");
    contextSpecificData.setValue("12345678");

    final Unreserved unreserved = new Unreserved("07");
    unreserved.setGloballyUniqueIdentifier("A011223344998877");
    unreserved.addContextSpecificData(contextSpecificData);

    assertThat(unreserved.toString(), equalTo("0016A011223344998877070812345678"));
  }

  @Test
  public void testSuccessConstructorTagAndGloballyUniqueIdentifier() {

    final TagLengthString contextSpecificData = new TagLengthString();
    contextSpecificData.setTag("07");
    contextSpecificData.setValue("12345678");

    final Unreserved unreserved = new Unreserved("A011223344998877");
    unreserved.addContextSpecificData("07", "12345678");

    assertThat(unreserved.toString(), equalTo("0016A011223344998877070812345678"));
  }

  @Test
  public void testSuccessToStringWhenValueIsNull() {

    final Unreserved unreserved = new Unreserved();

    assertThat(unreserved.toString(), equalTo(StringUtils.EMPTY));
  }

}
