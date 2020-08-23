package com.emv.qrcode.decoder;

import java.lang.reflect.Constructor;
import java.util.AbstractMap.SimpleEntry;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.function.BiConsumer;

import com.emv.qrcode.core.configuration.DecodersCpmMap;

// @formatter:off
public abstract class DecoderCpm<T> {

  protected final Iterator<byte[]> iterator;

  protected DecoderCpm(final byte[] source) {
    this.iterator = new DecodeCpmIterator(source);
  }

  protected abstract T decode();

  protected static <C, T> Entry<Class<?>, BiConsumer<C, ?>> consumerTagLengthValue(final Class<T> clazz, final BiConsumer<C, T> consumer) {
    return new SimpleEntry<>(clazz, consumer);
  }

  public static <T> T decode(final byte[] source, final Class<T> clazz) {
    try {
      final Class<? extends DecoderCpm<?>> parserClass = DecodersCpmMap.getDecoder(clazz);
      final Constructor<? extends DecoderCpm<?>> ctor = parserClass.getConstructor(byte[].class);
      final DecoderCpm<?> parser = ctor.newInstance(source);
      return clazz.cast(parser.decode());
    } catch (final Exception ex) {
      throw new RuntimeException(ex);
    }
  }

}
// @formatter:on
