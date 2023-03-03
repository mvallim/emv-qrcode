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

package com.emv.qrcode.core.utils;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class TLVUtilsTest {

  @Test
  public void testSuccessValueOfTag() {
    assertThat(TLVUtils.valueOfTag("01070103100"), equalTo("01"));
    assertThat(TLVUtils.valueOfTag("XXXX01070103100", 4), equalTo("01"));
  }

  @Test
  public void testSuccessValueOfLength() {
    assertThat(TLVUtils.valueOfLength("01070103100"), equalTo(7));
    assertThat(TLVUtils.valueOfLength("XXXX01070103100", 4), equalTo(7));
  }

  @Test
  public void testSuccessValueOf() {
    assertThat(TLVUtils.valueOf("01070103100"), equalTo("0103100"));
    assertThat(TLVUtils.valueOf("XXXX01070103100", 4), equalTo("0103100"));
  }

  @Test
  public void testSuccessChunk() {
    assertThat(TLVUtils.chunk("01070103100", 0), equalTo("01070103100"));
    assertThat(TLVUtils.chunk("01070103100020512345", 11), equalTo("020512345"));
  }

}
