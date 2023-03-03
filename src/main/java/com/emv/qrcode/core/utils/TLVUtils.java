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

package com.emv.qrcode.core.utils;

public final class TLVUtils {

  public static final Integer ID_WORD_COUNT = 2; // 01 - 99

  public static final Integer VALUE_LENGTH_WORD_COUNT = 2; // 01 - 99

  private TLVUtils() {
    super();
  }

  public static final String valueOfTag(final String source) {
    return valueOfTag(source, 0);
  }

  public static final String valueOfTag(final String source, final Integer from) {
    final Integer start = from;
    final Integer end = start + ID_WORD_COUNT;
    return source.substring(start, end);
  }

  public static final Integer valueOfLength(final String source) {
    return valueOfLength(source, 0);
  }

  public static final Integer valueOfLength(final String source, final Integer from) {
    final Integer start = from + ID_WORD_COUNT;
    final Integer end = start + VALUE_LENGTH_WORD_COUNT;
    return Integer.valueOf(source.substring(start, end));
  }

  public static final String valueOf(final String source) {
    return valueOf(source, 0);
  }

  public static final String valueOf(final String source, final Integer from) {
    final Integer start = from + ID_WORD_COUNT + VALUE_LENGTH_WORD_COUNT;
    final Integer end = start + valueOfLength(source, from);
    return source.substring(start, end);
  }

  public static final String chunk(final String source, final Integer from) {
    final Integer start = from + ID_WORD_COUNT + VALUE_LENGTH_WORD_COUNT;
    final Integer end = start + valueOfLength(source, from);
    return source.substring(from, end);
  }

}
