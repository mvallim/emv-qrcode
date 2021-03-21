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
