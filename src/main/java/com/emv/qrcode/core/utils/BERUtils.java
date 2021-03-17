package com.emv.qrcode.core.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
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

    final Integer start = from + countBytesOfTag(source, from);

    final Integer countBytesOfLength = countBytesOfLength(source, start);

    final byte[] array = ByteBuffer.allocate(2).array();

    for (int j = 0, i = start + countBytesOfLength - 1; i < start + countBytesOfLength; j++, i++) {
      array[j] = source[i];
    }

    return (int) ByteBuffer.wrap(array).order(ByteOrder.LITTLE_ENDIAN).getShort();
  }

  public static final byte[] valueOf(final byte[] source) {
    return valueOf(source, 0);
  }

  public static final byte[] valueOf(final byte[] source, final Integer from) {
    final int numberOfBytesTag = countBytesOfTag(source);
    final int numberOfBytesLength = countBytesOfLength(source, numberOfBytesTag);
    final int start = from + numberOfBytesTag + numberOfBytesLength;
    final int end = start + valueOfLength(source);
    return Arrays.copyOfRange(source, start, end);
  }

  public static final byte[] bucket(final byte[] source, final Integer from) {
    final int numberOfBytesTag = countBytesOfTag(source, from);
    final int numberOfBytesLength = countBytesOfLength(source, from + numberOfBytesTag);
    final int start = from + numberOfBytesTag + numberOfBytesLength;
    final int end = start + valueOfLength(source, from);
    return Arrays.copyOfRange(source, from, end);
  }

  public static final byte[] lengthToBytes(final Integer value) {
    if (value > Byte.MAX_VALUE) {

      final Integer numberOfBytes = countBytes(value);

      if (numberOfBytes > 2) {
        throw new IllegalStateException("Encode the length is more then 2 bytes (65535)");
      }

      final byte[] bytes = new byte[numberOfBytes + 1];

      bytes[0] = (byte) (0x80 + numberOfBytes);

      final byte[] array = ByteBuffer.allocate(2).putShort(value.shortValue()).order(ByteOrder.LITTLE_ENDIAN).array();

      for (int i = 0; i < numberOfBytes; i++) {
        bytes[i + 1] = array[2 - numberOfBytes + i];
      }

      return bytes;
    }

    return new byte[] { value.byteValue() };
  }

  public static final Integer countBytes(final Integer value) {
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

  public static final Integer countBytesOfLength(final byte[] source, final Integer from) {
    if ((source[from] & MAX_BYTE_VALUE) == MAX_BYTE_VALUE) {
      final int numberOfBytes = source[from] & NUMBER_OF_BYTES_MASK;

      if (numberOfBytes > 2) {
        throw new IllegalStateException("Decode the length is more then 2 bytes (65535)");
      }

      return numberOfBytes + 1;
    }

    return 1;
  }

}

// @formatter:on
