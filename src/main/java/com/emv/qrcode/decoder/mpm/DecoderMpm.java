package com.emv.qrcode.decoder.mpm;

import java.lang.reflect.Constructor;
import java.util.AbstractMap.SimpleEntry;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.function.BiConsumer;

import com.emv.qrcode.core.configuration.DecodersMpmMap;
import com.emv.qrcode.core.exception.PresentedModeException;

// @formatter:off
public abstract class DecoderMpm<T> {

  protected final Iterator<String> iterator;

  protected DecoderMpm(final String source) {
    this.iterator = new DecodeMpmIterator(source);
  }

  protected abstract T decode() throws PresentedModeException;

  protected static <C, T> Entry<Class<?>, BiConsumer<C, ?>> consumerTagLengthValue(final Class<T> clazz, final BiConsumer<C, T> consumer) {
    return new SimpleEntry<>(clazz, consumer);
  }

  public static <T> T decode(final String source, final Class<T> clazz) throws PresentedModeException {
    try {
      final Class<? extends DecoderMpm<?>> parserClass = DecodersMpmMap.getDecoder(clazz);
      final Constructor<? extends DecoderMpm<?>> ctor = parserClass.getConstructor(String.class);
      final DecoderMpm<?> parser = ctor.newInstance(source);
      return clazz.cast(parser.decode());
    } catch (final PresentedModeException ex) {
      throw ex;
    } catch (final Exception ex) {
      throw new RuntimeException(ex);
    }
  }

}
// @formatter:on
