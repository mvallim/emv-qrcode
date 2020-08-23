package com.emv.qrcode.decoder.mpm;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

// @formatter:off
final class DecodeMpmIterator implements Iterator<String> {

  public static final Integer ID_WORD_COUNT = 2; // 01 - 99

  public static final Integer VALUE_LENGTH_WORD_COUNT = 2; // 01 - 99

  private Integer current;

  private final Integer max;

  private final String source;

  public DecodeMpmIterator(final String source) {
    current = 0;
    max = source.length();
    this.source = source;
  }

  private Integer valueLength() {
    final Integer start = current + ID_WORD_COUNT;
    final Integer end = start + VALUE_LENGTH_WORD_COUNT;
    return Integer.valueOf(source.substring(start, end));
  }

  @Override
  public boolean hasNext() {
    return current + ID_WORD_COUNT + VALUE_LENGTH_WORD_COUNT <= max
        && current + ID_WORD_COUNT + VALUE_LENGTH_WORD_COUNT + valueLength() <= max;
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

    final Integer valueLength = valueLength();

    final String value = source.substring(current, current + ID_WORD_COUNT + VALUE_LENGTH_WORD_COUNT + valueLength);

    current += ID_WORD_COUNT + VALUE_LENGTH_WORD_COUNT + valueLength;

    return value;
  }

}
// @formatter:on
