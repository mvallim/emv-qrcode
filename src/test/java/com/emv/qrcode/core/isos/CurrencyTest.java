/*
 * Copyright 2019 the original author or authors.
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

public class CurrencyTest {

  @Test
  public void testSuccessToString() {
    assertThat(Currency.BRL.toString(), equalTo("BRL"));
  }

  @Test
  public void testSuccessEntryOf() {
    assertThat(Currency.entryOf("BRL"), equalTo(Currency.BRL));
    assertThat(Currency.entryOf("brl"), equalTo(Currency.BRL));
    assertThat(Currency.entryOf("bRl"), equalTo(Currency.BRL));
  }

  @Test
  public void testFailEntryOf() {
    assertThat(Currency.entryOf(""), nullValue());
    assertThat(Currency.entryOf(null), nullValue());
    assertThat(Currency.entryOf("---"), nullValue());
  }

  @Test
  public void testSuccessExists() {
    assertThat(Currency.exists("BRL"), equalTo(true));
    assertThat(Currency.exists("brl"), equalTo(true));
    assertThat(Currency.exists("bRl"), equalTo(true));
  }

  @Test
  public void testFailExists() {
    assertThat(Currency.exists(""), equalTo(false));
    assertThat(Currency.exists(null), equalTo(false));
    assertThat(Currency.exists("---"), equalTo(false));
  }

}
