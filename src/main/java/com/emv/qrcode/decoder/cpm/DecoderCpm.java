package com.emv.qrcode.decoder.cpm;

import java.lang.reflect.Constructor;
import java.util.AbstractMap.SimpleEntry;
import java.util.Base64;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

import com.emv.qrcode.core.configuration.DecodersCpmMap;
import com.emv.qrcode.core.exception.PresentedModeException;

// @formatter:off
public abstract class DecoderCpm<T> {

  private static final Map<Class<?>, Constructor<? extends DecoderCpm<?>>> ctorMap = new ConcurrentHashMap<>();

  protected final Iterator<byte[]> iterator;

  protected DecoderCpm(final byte[] source) {
    this.iterator = new DecodeCpmIterator(source);
  }

  protected abstract T decode() throws PresentedModeException;

  protected static <C, T> Entry<Class<?>, BiConsumer<C, ?>> consumerTagLengthValue(final Class<T> clazz, final BiConsumer<C, T> consumer) {
    return new SimpleEntry<>(clazz, consumer);
  }

  /**
   * Decode CPM using Base64 string encoded
   *
   * @param <T> target class
   * @param source base64 string CPM
   * @param clazz target class
   * @return target class result
   */
  public static final <T> T decode(final String source, final Class<T> clazz) {
    return decode(Base64.getDecoder().decode(source), clazz);
  }

  /**
   * Decode CPM using byte array
   *
   * @param <T> target class
   * @param source byte array CPM
   * @param clazz target class
   * @return target class result
   */
  public static final <T> T decode(final byte[] source, final Class<T> clazz) {
    try {
      final Class<? extends DecoderCpm<?>> parserClass = DecodersCpmMap.getDecoder(clazz);

      if (!ctorMap.containsKey(clazz)) {
        ctorMap.put(clazz, parserClass.getConstructor(byte[].class));
      }

      final Constructor<? extends DecoderCpm<?>> ctor = ctorMap.get(clazz);
      final DecoderCpm<?> parser = ctor.newInstance(source);
      return clazz.cast(parser.decode());
    } catch (final Exception ex) {
      throw new RuntimeException(ex);
    }
  }

}
// @formatter:on
