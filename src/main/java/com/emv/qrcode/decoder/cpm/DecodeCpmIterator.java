package com.emv.qrcode.decoder.cpm;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

// @formatter:off
final class DecodeCpmIterator implements Iterator<byte[]> {

  private static final Map<Byte, Integer> ID_WORD_COUNT = new ConcurrentHashMap<>();

  static {
    ID_WORD_COUNT.put((byte)0x85, 0);
    ID_WORD_COUNT.put((byte)0x61, 0);
    ID_WORD_COUNT.put((byte)0x62, 0);
    ID_WORD_COUNT.put((byte)0x63, 0);
    ID_WORD_COUNT.put((byte)0x64, 0);
    ID_WORD_COUNT.put((byte)0x4F, 0);
    ID_WORD_COUNT.put((byte)0x50, 0);
    ID_WORD_COUNT.put((byte)0x57, 0);
    ID_WORD_COUNT.put((byte)0x5A, 0);
    ID_WORD_COUNT.put((byte)0x5F, 1);
    ID_WORD_COUNT.put((byte)0x9F, 1);
  }

  public static final Integer VALUE_LENGTH_WORD_COUNT = 1;

  private Integer current;

  private final Integer max;

  private final byte[] source;

  public DecodeCpmIterator(final byte[] source) {
    current = 0;
    max = source.length;
    this.source = source;
  }

  private Byte valueLength() {
    final Integer start = current + ID_WORD_COUNT.get(source[current]);
    final Integer end = start + VALUE_LENGTH_WORD_COUNT;
    return source[end];
  }

  @Override
  public boolean hasNext() {
    return current < max && current + ID_WORD_COUNT.get(source[current]) + VALUE_LENGTH_WORD_COUNT + valueLength() <= max;
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

    final Integer valueLength = valueLength() + 1;

    final byte[] value = Arrays.copyOfRange(source, current, current + ID_WORD_COUNT.get(source[current]) + VALUE_LENGTH_WORD_COUNT + valueLength);

    current += ID_WORD_COUNT.get(source[current]) + VALUE_LENGTH_WORD_COUNT + valueLength;

    return value;
  }

}
// @formatter:on
