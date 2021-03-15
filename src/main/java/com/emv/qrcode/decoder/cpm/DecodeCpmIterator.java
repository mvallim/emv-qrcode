package com.emv.qrcode.decoder.cpm;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

import com.emv.qrcode.core.model.BERTag;

// @formatter:off
final class DecodeCpmIterator implements Iterator<byte[]> {

  private static final int MAX_BYTE_VALUE = 0x80;

  private static final int NUMBER_OF_BYTES_MASK = 0x7F;

  private Integer current;

  private final Integer max;

  private final byte[] source;

  public DecodeCpmIterator(final byte[] source) {
    current = 0;
    max = source.length;
    this.source = source;
  }

  /**
   * Length byte
   * Depending on the value we have one or more length bytes.
   * The byte has the following structure:
   * @return length
   */
  private Integer valueLength() {

    final Integer start = current + countBytesOfTag();

    final Integer countBytesOfLength = countBytesOfLength(start);

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

  private Integer countBytesOfTag() {
    Integer count = 0;

    final boolean hasNextByte = BERTag.hasNextByte(source[current + count]);

    if (hasNextByte) {
      count++;
    }

    while (hasNextByte && BERTag.isNotLastByte(source[current + count])) {
      count++;
    }

    return count + 1;
  }

  private Integer countBytesOfLength(final Integer start) {
    if ((source[start] & MAX_BYTE_VALUE) == MAX_BYTE_VALUE) {
      final int numberOfBytes = (source[start] & NUMBER_OF_BYTES_MASK) + 1;

      if (numberOfBytes > 3 ) {
        throw new IllegalStateException("Decode the length is more then 2 bytes (65535)");
      }

      return numberOfBytes;
    }

    return 1;
  }

  @Override
  public boolean hasNext() {

    if (current >= max) {
      return false;
    }

    final Integer countBytesOfTag = countBytesOfTag();
    final Integer countBytesOfLength = countBytesOfLength(current + countBytesOfTag);
    final Integer valueLength = valueLength();

    return current + countBytesOfTag + countBytesOfLength + valueLength <= max;
  }

  @Override
  public void forEachRemaining(final Consumer<? super byte[]> action) {
    while (hasNext()) {
      action.accept(next());
    }
  }

  @Override
  public byte[] next() {

    if(!hasNext()){
      throw new NoSuchElementException();
    }

    final Integer countBytesOfTag = countBytesOfTag();
    final Integer countBytesOfLength = countBytesOfLength(current + countBytesOfTag);
    final Integer valueLength = valueLength();
    final Integer end = current + countBytesOfTag + countBytesOfLength + valueLength;

    final byte[] value = Arrays.copyOfRange(source, current, end);

    current += end;

    return value;
  }

}
// @formatter:on
