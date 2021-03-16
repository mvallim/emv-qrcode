package com.emv.qrcode.decoder.cpm;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

import com.emv.qrcode.core.utils.BERUtils;

// @formatter:off
final class DecodeCpmIterator implements Iterator<byte[]> {

  private Integer current;

  private final Integer max;

  private final byte[] source;

  public DecodeCpmIterator(final byte[] source) {
    current = 0;
    max = source.length;
    this.source = source;
  }

  @Override
  public boolean hasNext() {

    if (current >= max) {
      return false;
    }

    final Integer countBytesOfTag = BERUtils.countBytesOfTag(source, current);
    final Integer countBytesOfLength = BERUtils.countBytesOfLength(source, current + countBytesOfTag);
    final Integer valueLength = BERUtils.valueOfLength(source, current);

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

    final Integer countBytesOfTag = BERUtils.countBytesOfTag(source, current);
    final Integer countBytesOfLength = BERUtils.countBytesOfLength(source, current + countBytesOfTag);
    final Integer valueLength = BERUtils.valueOfLength(source, current);
    final Integer end = current + countBytesOfTag + countBytesOfLength + valueLength;

    final byte[] value = Arrays.copyOfRange(source, current, end);

    current += countBytesOfTag + countBytesOfLength + valueLength;

    return value;
  }

}
// @formatter:on
