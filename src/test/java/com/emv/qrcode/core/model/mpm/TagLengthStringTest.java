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

package com.emv.qrcode.core.model.mpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class TagLengthStringTest {

  @Test
  public void testSuccessToString() {
    final TagLengthString tagLengthString = new TagLengthString();

    tagLengthString.setTag("02");
    tagLengthString.setValue("1234");

    assertThat(tagLengthString.toString(), equalTo("02041234"));
    assertThat(new TagLengthString("02", "1234").toString(), equalTo("02041234"));
  }

  @Test
  public void testSuccessToStringWhenValueIsNull() {
    final TagLengthString tagLengthString = new TagLengthString();

    tagLengthString.setTag("02");
    tagLengthString.setValue(null);

    assertThat(tagLengthString.toString(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToStringWhenValueIsEmpty() {
    final TagLengthString tagLengthString = new TagLengthString();

    tagLengthString.setTag("02");
    tagLengthString.setValue(StringUtils.EMPTY);

    assertThat(tagLengthString.toString(), equalTo(StringUtils.EMPTY));
  }

}
