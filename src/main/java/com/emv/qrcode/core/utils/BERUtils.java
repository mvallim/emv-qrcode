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

import java.util.Arrays;

import com.emv.qrcode.core.model.cpm.BERTag;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

// @formatter:off
/**
 * Utility class for parsing Basic Encoding Rules (BER) TLV data used in Consumer Presented Mode (CPM) QR codes.
 * Provides methods to extract tag, length, and value from BER-TLV-encoded byte arrays.
 *
 * @see <a href="https://en.wikipedia.org/wiki/X.690">ITU-T X.690 (BER, CER, DER)</a>
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BERUtils {

  private static final int MAX_BYTE_VALUE = 0x80;

  private static final int NUMBER_OF_BYTES_MASK = 0x7F;

  /**
   * Extracts the tag bytes from a BER-TLV-encoded byte array starting at position 0.
   *
   * @param source the BER-TLV-encoded byte array
   * @return the tag bytes as a new array
   */
  public static final byte[] valueOfTag(final byte[] source) {
    return valueOfTag(source, 0);
  }

  /**
   * Extracts the tag bytes from a BER-TLV-encoded byte array starting at the specified position.
   *
   * @param source the BER-TLV-encoded byte array
   * @param from the starting position in the array
   * @return the tag bytes as a new array
   */
  public static final byte[] valueOfTag(final byte[] source, final Integer from) {
    return Arrays.copyOfRange(source, from, countBytesOfTag(source));
  }

  /**
   * Extracts the length value from a BER-TLV-encoded byte array starting at position 0.
   *
   * @param source the BER-TLV-encoded byte array
   * @return the length as an integer
   */
  public static final Integer valueOfLength(final byte[] source) {
    return valueOfLength(source, 0);
  }

  /**
   * Extracts the length value from a BER-TLV-encoded byte array starting at the specified position.
   *
   * @param source the BER-TLV-encoded byte array
   * @param from the starting position in the array
   * @return the length as an integer
   */
  public static final Integer valueOfLength(final byte[] source, final Integer from) {

    final Integer countBytesOfTag = countBytesOfTag(source, from);

    final Integer countBytesOfLength = countBytesOfLength(source, from);

    final Integer skipFirstByte = countBytesOfLength > 1 ? 1 : 0;

    final Integer numberOfBytes = countBytesOfLength - skipFirstByte;

    final byte[] bytes = new byte[2];

    for (int i = 0; i < numberOfBytes; i++) {
      bytes[(2 - numberOfBytes) + i] = source[from + countBytesOfTag + skipFirstByte + i];
    }

    // BIG ENDIAN array to Integer
    return ((bytes[0] & 0xFF) << 8) | (bytes[1] & 0xFF);
  }

  /**
   * Extracts the value bytes from a BER-TLV-encoded byte array starting at position 0.
   *
   * @param source the BER-TLV-encoded byte array
   * @return the value bytes as a new array
   */
  public static final byte[] valueOf(final byte[] source) {
    return valueOf(source, 0);
  }

  /**
   * Extracts the value bytes from a BER-TLV-encoded byte array starting at the specified position.
   *
   * @param source the BER-TLV-encoded byte array
   * @param from the starting position in the array
   * @return the value bytes as a new array
   */
  public static final byte[] valueOf(final byte[] source, final Integer from) {
    final int numberOfBytesTag = countBytesOfTag(source, from);
    final int numberOfBytesLength = countBytesOfLength(source, from);
    final int start = from + numberOfBytesTag + numberOfBytesLength;
    final int end = start + valueOfLength(source, from);
    return Arrays.copyOfRange(source, start, end);
  }

  /**
   * Extracts a complete BER-TLV chunk (tag + length + value) starting at the specified position.
   *
   * @param source the BER-TLV-encoded byte array
   * @param from the starting position in the array
   * @return the complete TLV chunk as a new array
   */
  public static final byte[] chunk(final byte[] source, final Integer from) {
    final int numberOfBytesTag = countBytesOfTag(source, from);
    final int numberOfBytesLength = countBytesOfLength(source, from);
    final int start = from + numberOfBytesTag + numberOfBytesLength;
    final int end = start + valueOfLength(source, from);
    return Arrays.copyOfRange(source, from, end);
  }

  /**
   * Encodes a length value into BER-TLV length bytes.
   *
   * @param value the length value to encode
   * @return the encoded length bytes
   * @throws IllegalStateException if the length requires more than 2 bytes (greater 65535)
   */
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
        bytes[numberOfBytes - i] = (byte) (value >> (i * 8));
      }

      return bytes;
    }

    return new byte[] { value.byteValue() };
  }

  /**
   * Counts the number of bytes needed to represent the given integer value.
   *
   * @param value the integer value
   * @return the number of bytes required
   */
  private static final Integer countBytes(final Integer value) {
    if (value == 0) {
      return 0;
    } else {
      return countBytes(value >> 8) + 1;
    }
  }

  /**
   * Counts the number of bytes that make up the tag in a BER-TLV-encoded byte array starting at position 0.
   *
   * @param source the BER-TLV-encoded byte array
   * @return the number of bytes in the tag
   */
  public static final Integer countBytesOfTag(final byte[] source) {
    return countBytesOfTag(source, 0);
  }

  /**
   * Counts the number of bytes that make up the tag in a BER-TLV-encoded byte array starting at the specified position.
   *
   * @param source the BER-TLV-encoded byte array
   * @param from the starting position in the array
   * @return the number of bytes in the tag
   */
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

  /**
   * Counts the number of bytes that make up the length in a BER-TLV-encoded byte array starting at position 0.
   *
   * @param source the BER-TLV-encoded byte array
   * @return the number of bytes in the length field
   */
  public static final Integer countBytesOfLength(final byte[] source) {
    return countBytesOfLength(source, 0);
  }

  /**
   * Counts the number of bytes that make up the length in a BER-TLV-encoded byte array starting at the specified position.
   *
   * @param source the BER-TLV-encoded byte array
   * @param from the starting position in the array
   * @return the number of bytes in the length field
   * @throws IllegalStateException if the length requires more than 2 bytes (greater 65535)
   */
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
