package com.emv.qrcode.decoder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.AbstractMap.SimpleEntry;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.function.BiConsumer;

import com.emv.qrcode.core.configuration.DecodersMap;

// @formatter:off
public abstract class Decoder<T> {

  protected final Iterator<String> iterator;

  protected Decoder(final String source) {
    this.iterator = new DecodeIterator(source);
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
