package com.emv.qrcode.decoder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.AbstractMap.SimpleEntry;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.emv.qrcode.core.configuration.DecodersMap;

// @formatter:off
public abstract class Decoder<T> implements Iterator<String> {

  public static final Integer ID_WORD_COUNT = 2; // 01 - 99

  public static final Integer VALUE_LENGTH_WORD_COUNT = 2; // 01 - 99

  private Integer current;

  private final Integer max;

  private final String source;

  protected Decoder(final String source) {
    this.current = 0;
    this.max = source.length();
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

    if (!hasNext()) {
      throw new NoSuchElementException();
    }

    final Integer valueLength = valueLength();

    final String value = source.substring(current, current + ID_WORD_COUNT + VALUE_LENGTH_WORD_COUNT + valueLength);

    current += ID_WORD_COUNT + VALUE_LENGTH_WORD_COUNT + valueLength;

    return value;
  }

  protected abstract T decode();

  protected static <C, T> Entry<Class<?>, BiConsumer<C, ?>> consumerTagLengthValue(final Class<T> clazz, final BiConsumer<C, T> consumer) {
    return new SimpleEntry<>(clazz, consumer);
  }

  public static <T> T decode(final String source, final Class<T> clazz) {
    try {
      final Class<? extends Decoder<?>> parserClass = DecodersMap.getDecoder(clazz);
      final Constructor<? extends Decoder<?>> ctor = parserClass.getConstructor(String.class);
      final Decoder<?> parser = ctor.newInstance(source);
      return clazz.cast(parser.decode());
    } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }

}
// @formatter:on
