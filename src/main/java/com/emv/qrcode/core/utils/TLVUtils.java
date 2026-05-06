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

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utility class for parsing Tag-Length-Value (TLV) encoded strings used in
 * Merchant Presented Mode (MPM) QR codes. Provides methods to extract tag,
 * length, and value from TLV-formatted strings.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TLVUtils {

  /**
   * Number of characters used to represent the tag identifier (2 digits: 01-99).
   */
  public static final Integer ID_WORD_COUNT = 2; // 01 - 99

  /**
   * Number of characters used to represent the value length (2 digits: 01-99).
   */
  public static final Integer VALUE_LENGTH_WORD_COUNT = 2; // 01 - 99

  /**
   * Extracts the tag from a TLV-encoded string starting at position 0.
   *
   * @param source the TLV-encoded string
   * @return the tag as a string
   */
  public static final String valueOfTag(final String source) {
    return valueOfTag(source, 0);
  }

  /**
   * Extracts the tag from a TLV-encoded string starting at the specified
   * position.
   *
   * @param source the TLV-encoded string
   * @param from   the starting position in the string
   * @return the tag as a string
   */
  public static final String valueOfTag(final String source, final Integer from) {
    final Integer start = from;
    final Integer end = start + ID_WORD_COUNT;
    return source.substring(start, end);
  }

  /**
   * Extracts the length from a TLV-encoded string starting at position 0.
   *
   * @param source the TLV-encoded string
   * @return the length as an integer
   */
  public static final Integer valueOfLength(final String source) {
    return valueOfLength(source, 0);
  }

  /**
   * Extracts the length from a TLV-encoded string starting at the specified
   * position.
   *
   * @param source the TLV-encoded string
   * @param from   the starting position in the string
   * @return the length as an integer
   */
  public static final Integer valueOfLength(final String source, final Integer from) {
    final Integer start = from + ID_WORD_COUNT;
    final Integer end = start + VALUE_LENGTH_WORD_COUNT;
    return Integer.valueOf(source.substring(start, end));
  }

  /**
   * Extracts the value from a TLV-encoded string starting at position 0.
   *
   * @param source the TLV-encoded string
   * @return the value as a string
   */
  public static final String valueOf(final String source) {
    return valueOf(source, 0);
  }

  /**
   * Extracts the value from a TLV-encoded string starting at the specified
   * position.
   *
   * @param source the TLV-encoded string
   * @param from   the starting position in the string
   * @return the value as a string
   */
  public static final String valueOf(final String source, final Integer from) {
    final Integer start = from + ID_WORD_COUNT + VALUE_LENGTH_WORD_COUNT;
    final Integer end = start + valueOfLength(source, from);
    return source.substring(start, end);
  }

  /**
   * Extracts a complete TLV chunk (tag + length + value) starting at the
   * specified position.
   *
   * @param source the TLV-encoded string
   * @param from   the starting position in the string
   * @return the complete TLV chunk as a string
   */
  public static final String chunk(final String source, final Integer from) {
    final Integer start = from + ID_WORD_COUNT + VALUE_LENGTH_WORD_COUNT;
    final Integer end = start + valueOfLength(source, from);
    return source.substring(from, end);
  }

}
