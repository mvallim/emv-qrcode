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

package com.emv.qrcode.decoder.mpm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.NoSuchElementException;

import org.junit.Test;

public class DecodeMpmIteratorTest {

  @Test
  public void testSuccessParse() {
    final String encoded = "00020101021102160004hoge0104abcd520441115303156540523.7255020256035005802CN5914BEST TRANSPORT6007BEIJING6107123456762950105123450205678900305098760405543210505abcde0605fghij0705klmno0805pqres0903tuv1004abcd5004ijkl64280002ZH0102北京0204最佳运输0304abcd65020080320016A0112233449988770708123456786304C659";

    final DecodeMpmIterator decodeIterator = new DecodeMpmIterator(encoded);

    assertThat(decodeIterator.hasNext(), equalTo(true));

    assertThatCode(() -> decodeIterator.forEachRemaining(stub -> {
    })).doesNotThrowAnyException();

    assertThat(decodeIterator.hasNext(), equalTo(false));
  }

  @Test
  public void testFailParse() {
    final String encoded = "00020101021102160004hoge0104abcd520441115303156540523.7255020256035005802CN5914BEST TRANSPORT6007BEIJING6107123456762950105123450205678900305098760405543210505abcde0605fghij0705klmno0805pqres0903tuv1004abcd5004ijkl64280002ZH0102北京0204最佳运输0304abcd65020080320016A0112233449988770708123456786304C659";

    final DecodeMpmIterator decodeIterator = new DecodeMpmIterator(encoded);

    assertThat(decodeIterator.hasNext(), equalTo(true));

    assertThatCode(() -> decodeIterator.forEachRemaining(stub -> {
    })).doesNotThrowAnyException();

    final Throwable throwable = catchThrowable(() -> decodeIterator.next());

    assertThat(throwable).isInstanceOf(NoSuchElementException.class);

  }

}
