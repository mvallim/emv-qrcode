/*
 * Copyright 2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.emv.qrcode.model.mpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.emv.qrcode.core.model.mpm.TagLengthString;

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
