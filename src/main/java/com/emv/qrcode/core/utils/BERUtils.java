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

  public static byte[] copyBytesOfTag(final byte[] source) {
    return Arrays.copyOfRange(source, 0, countBytesOfTag(source));
  }

  public static byte[] copyBytesOfValue(final byte[] source) {
    final int numberOfBytesTag = countBytesOfTag(source);
    final int numberOfBytesLength = countBytesOfLength(source, numberOfBytesTag);
    final int start = numberOfBytesTag + numberOfBytesLength;
    final int end = numberOfBytesTag + numberOfBytesLength + valueOfLength(source);
    return Arrays.copyOfRange(source, start, end);
  }

  public static Integer valueOfLength(final byte[] source) {
    return valueOfLength(source, 0);
  }

  /**
   * Length byte
   * Depending on the value we have one or more length bytes.
   * The byte has the following structure:
   * @return length
   */
  public static Integer valueOfLength(final byte[] source, final Integer from) {

    final Integer start = from + countBytesOfTag(source, from);

    final Integer countBytesOfLength = countBytesOfLength(source, start);

    final byte[] array = ByteBuffer.allocate(2).array();

    /**
     * |No of Bytes |   Length   |           Coding           | Mask |
     * |------------+------------+----------------------------+------+
     * |     1      |    0-127   |          0xxxxxxx          | 0x7F |
     * |     2      |   128-255  |      10000001 xxxxxxxx     | 0x81 |
     * |     3      |  256-65535 | 10000010 xxxxxxxx xxxxxxxx | 0x82 |
     */
    for (int j = 0, i = start + countBytesOfLength - 1; i < start + countBytesOfLength; j++, i++) {
      array[j] = source[i];
    }

    return (int) ByteBuffer.wrap(array).order(ByteOrder.LITTLE_ENDIAN).getShort();
  }

  public static byte[] lengthToBytes(final Integer value) {
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

  public static Integer countBytes(final Integer value) {
    if (value == 0) {
      return 0;
    } else {
      return countBytes(value >> 8) + 1;
    }
  }

  public static Integer countBytesOfTag(final byte[] source) {
    return countBytesOfTag(source, 0);
  }

  public static Integer countBytesOfTag(final byte[] source, final Integer from) {
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

  public static Integer countBytesOfLength(final byte[] source, final Integer from) {
    if ((source[from] & MAX_BYTE_VALUE) == MAX_BYTE_VALUE) {
      final int numberOfBytes = (source[from] & NUMBER_OF_BYTES_MASK) + 1;

      if (numberOfBytes > 3) {
        throw new IllegalStateException("Decode the length is more then 2 bytes (65535)");
      }

      return numberOfBytes;
    }

    return 1;
  }

}

// @formatter:on
