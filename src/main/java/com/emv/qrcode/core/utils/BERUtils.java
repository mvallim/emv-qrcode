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

import java.util.Arrays;

import com.emv.qrcode.core.model.cpm.BERTag;

// @formatter:off
public final class BERUtils {

  private static final int MAX_BYTE_VALUE = 0x80;

  private static final int NUMBER_OF_BYTES_MASK = 0x7F;

  private BERUtils() {
    super();
  }

  public static final byte[] valueOfTag(final byte[] source) {
    return valueOfTag(source, 0);
  }

  public static final byte[] valueOfTag(final byte[] source, final Integer from) {
    return Arrays.copyOfRange(source, from, countBytesOfTag(source));
  }

  public static final Integer valueOfLength(final byte[] source) {
    return valueOfLength(source, 0);
  }

  public static final Integer valueOfLength(final byte[] source, final Integer from) {

    final Integer countBytesOfTag = countBytesOfTag(source, from);

    final Integer countBytesOfLength = countBytesOfLength(source, from);

    final Integer skipFirstByte = countBytesOfLength > 1 ? 1 : 0;

    final Integer numberOfBytes = countBytesOfLength - skipFirstByte;

    final byte[] bytes = new byte[2];

    for (int i = 0; i < numberOfBytes; i++) {
      bytes[2 - numberOfBytes + i] = source[from + countBytesOfTag + skipFirstByte + i];
    }

    // BIG ENDIAN array to Integer
    return (bytes[0] & 0xFF) << 8 | bytes[1] & 0xFF;
  }

  public static final byte[] valueOf(final byte[] source) {
    return valueOf(source, 0);
  }

  public static final byte[] valueOf(final byte[] source, final Integer from) {
    final int numberOfBytesTag = countBytesOfTag(source, from);
    final int numberOfBytesLength = countBytesOfLength(source, from);
    final int start = from + numberOfBytesTag + numberOfBytesLength;
    final int end = start + valueOfLength(source, from);
    return Arrays.copyOfRange(source, start, end);
  }

  public static final byte[] chunk(final byte[] source, final Integer from) {
    final int numberOfBytesTag = countBytesOfTag(source, from);
    final int numberOfBytesLength = countBytesOfLength(source, from);
    final int start = from + numberOfBytesTag + numberOfBytesLength;
    final int end = start + valueOfLength(source, from);
    return Arrays.copyOfRange(source, from, end);
  }

  public static final byte[] lengthOfValue(final Integer value) {
    if (value > Byte.MAX_VALUE) {

      final Integer numberOfBytes = countBytes(value);

      if (numberOfBytes > 2) {
        throw new IllegalStateException("Encode the length is more then 2 bytes (65535)");
      }

      final byte[] bytes = new byte[numberOfBytes + 1];

      bytes[0] = (byte) (0x80 + numberOfBytes);

      // Integer to BIG ENDIAN array
      for (int i = 0; i < numberOfBytes; i++) {
        bytes[numberOfBytes - i] = (byte) (value >> i * 8);
      }

      return bytes;
    }

    return new byte[] { value.byteValue() };
  }

  private static final Integer countBytes(final Integer value) {
    if (value == 0) {
      return 0;
    } else {
      return countBytes(value >> 8) + 1;
    }
  }

  public static final Integer countBytesOfTag(final byte[] source) {
    return countBytesOfTag(source, 0);
  }

  public static final Integer countBytesOfTag(final byte[] source, final Integer from) {
    Integer count = 0;

    final boolean hasNextByte = BERTag.hasNextByte(source[from + count]);

    if (hasNextByte) {
      count++;
    }

    while (hasNextByte && BERTag.isNotLastByte(source[from + count])) {
      count++;
    }

    return count + 1;
  }

  public static final Integer countBytesOfLength(final byte[] source) {
    return countBytesOfLength(source, 0);
  }

  public static final Integer countBytesOfLength(final byte[] source, final Integer from) {

    final Integer countBytesOfTag = countBytesOfTag(source, from);

    if ((source[from + countBytesOfTag] & MAX_BYTE_VALUE) == MAX_BYTE_VALUE) {
      final int numberOfBytes = source[from + countBytesOfTag] & NUMBER_OF_BYTES_MASK;

      if (numberOfBytes > 2) {
        throw new IllegalStateException("Decode the length is more then 2 bytes (65535)");
      }

      return numberOfBytes + 1;
    }

    return 1;
  }

}

// @formatter:on
