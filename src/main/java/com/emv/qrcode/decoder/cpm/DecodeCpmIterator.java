package com.emv.qrcode.decoder.cpm;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

import com.emv.qrcode.core.model.BERTag;

// @formatter:off
final class DecodeCpmIterator implements Iterator<byte[]> {

  private static final int MAX_BYTE_VALUE = 0x80;

  private static final int BYTE_MULTIPLY_POWER = 0x100;

  private static final int NUMBER_OF_BYTES_MASK = 0x7F;

  private static final int POSITIVE_MASK = 0xFF;

  private Integer current;

  private final Integer max;

  private final byte[] source;

  public DecodeCpmIterator(final byte[] source) {
    current = 0;
    max = source.length;
    this.source = source;
  }

  private Integer valueLength() {

    final Integer start = current + countBytesOfTag();

    final Integer countBytesOfLength = countBytesOfLength(start);

    Integer length = 0;

    if (countBytesOfLength == 1) {
      length = source[start] & POSITIVE_MASK;
    } else {
      for (int i = start + 1; i < start + countBytesOfLength; i++) {
        length = length * BYTE_MULTIPLY_POWER + (source[i] & POSITIVE_MASK);
      }
    }

    return length;
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
        throw new IllegalStateException(MessageFormat.format("At position {0} the len is more then 3 [{1}]", start, numberOfBytes));
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
