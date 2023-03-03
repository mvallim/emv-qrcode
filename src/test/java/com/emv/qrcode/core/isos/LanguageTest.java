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

public class LanguageTest {

  @Test
  public void testSuccessToString() {
    assertThat(Language.PT.toString(), equalTo("PT"));
  }

  @Test
  public void testSuccessEntryOf() {
    assertThat(Language.entryOf("PT"), equalTo(Language.PT));
    assertThat(Language.entryOf("pt"), equalTo(Language.PT));
    assertThat(Language.entryOf("Pt"), equalTo(Language.PT));
  }

  @Test
  public void testFailEntryOf() {
    assertThat(Language.entryOf(""), nullValue());
    assertThat(Language.entryOf(null), nullValue());
    assertThat(Language.entryOf("--"), nullValue());
  }

  @Test
  public void testSuccessExists() {
    assertThat(Language.exists("PT"), equalTo(true));
    assertThat(Language.exists("pt"), equalTo(true));
    assertThat(Language.exists("pT"), equalTo(true));
  }

  @Test
  public void testFailExists() {
    assertThat(Language.exists(""), equalTo(false));
    assertThat(Language.exists(null), equalTo(false));
    assertThat(Language.exists("--"), equalTo(false));
  }

}
