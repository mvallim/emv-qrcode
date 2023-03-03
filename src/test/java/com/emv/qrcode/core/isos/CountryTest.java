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

package com.emv.qrcode.core.isos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class CountryTest {

  @Test
  public void testSuccessToString() {
    assertThat(Country.BR.toString(), equalTo("BR"));
  }

  @Test
  public void testSuccessEntryOf() {
    assertThat(Country.entryOf("BR"), equalTo(Country.BR));
    assertThat(Country.entryOf("br"), equalTo(Country.BR));
    assertThat(Country.entryOf("bR"), equalTo(Country.BR));
  }

  @Test
  public void testFailEntryOf() {
    assertThat(Country.entryOf(""), nullValue());
    assertThat(Country.entryOf(null), nullValue());
    assertThat(Country.entryOf("--"), nullValue());
  }

  @Test
  public void testSuccessExists() {
    assertThat(Country.exists("BR"), equalTo(true));
    assertThat(Country.exists("br"), equalTo(true));
    assertThat(Country.exists("bR"), equalTo(true));
  }

  @Test
  public void testFailExists() {
    assertThat(Country.exists(""), equalTo(false));
    assertThat(Country.exists(null), equalTo(false));
    assertThat(Country.exists("--"), equalTo(false));
  }

}
