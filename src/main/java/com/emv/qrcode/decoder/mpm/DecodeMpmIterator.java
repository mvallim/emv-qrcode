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

package com.emv.qrcode.decoder.mpm;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

import com.emv.qrcode.core.utils.TLVUtils;

// @formatter:off
final class DecodeMpmIterator implements Iterator<String> {

  private Integer current;

  private final Integer max;

  private final String source;

  public DecodeMpmIterator(final String source) {
    current = 0;
    max = source.length();
    this.source = source;
  }

  @Override
  public boolean hasNext() {

    if (current >= max) {
      return false;
    }

    final Integer valueLength = TLVUtils.valueOfLength(source, current);

    return current + TLVUtils.ID_WORD_COUNT + TLVUtils.VALUE_LENGTH_WORD_COUNT + valueLength <= max;
  }

  @Override
  public void forEachRemaining(final Consumer<? super String> action) {
    while (hasNext()) {
      action.accept(next());
    }
  }

  @Override
  public String next() {

    if(!hasNext()){
      throw new NoSuchElementException();
    }

    final String value = TLVUtils.chunk(source, current);

    current += value.length();

    return value;
  }

}
// @formatter:on
