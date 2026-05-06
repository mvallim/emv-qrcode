/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.emv.qrcode.decoder.mpm;

import java.lang.reflect.Constructor;
import java.util.AbstractMap.SimpleEntry;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

import com.emv.qrcode.core.configuration.DecodersMpmMap;
import com.emv.qrcode.core.exception.PresentedModeException;

// @formatter:off
/**
 * Abstract base class for decoding Merchant Presented Mode (MPM) QR code data.
 * This class provides the foundation for all MPM decoders that parse TLV-encoded strings.
 *
 * @param <T> the type of decoded object
 * @see DecodeMpmIterator
 * @see DecodersMpmMap
 */
public abstract class DecoderMpm<T> {

  private static final Map<Class<?>, Constructor<? extends DecoderMpm<?>>> ctorMap = new ConcurrentHashMap<>();

  protected final Iterator<String> iterator;

  /**
   * Constructs a DecoderMpm with the specified source string.
   *
   * @param source the MPM QR code string to decode
   */
  protected DecoderMpm(final String source) {
    this.iterator = new DecodeMpmIterator(source);
  }

  /**
   * Decodes the source string and returns the decoded object.
   *
   * @return the decoded object
   * @throws PresentedModeException if decoding fails
   */
  protected abstract T decode() throws PresentedModeException;

  /**
   * Creates a map entry that associates a class with a consumer for tag-length-value decoding.
   *
   * @param <C> the type of the consumer's first argument
   * @param <T> the type of the consumer's second argument
   * @param clazz the class to use as a key
   * @param consumer the consumer to associate with the class
   * @return a map entry pairing the class and consumer
   */
  protected static <C, T> Entry<Class<?>, BiConsumer<C, ?>> consumerTagLengthValue(final Class<T> clazz, final BiConsumer<C, T> consumer) {
    return new SimpleEntry<>(clazz, consumer);
  }

  /**
   * Decodes an MPM string into the specified target class.
   *
   * @param <T> the type of the target class
   * @param source the MPM QR code string to decode
   * @param clazz the target class to decode into
   * @return the decoded object of type T
   * @throws PresentedModeException if decoding fails
   */
  public static <T> T decode(final String source, final Class<T> clazz) throws PresentedModeException {
    try {
      final Class<? extends DecoderMpm<?>> parserClass = DecodersMpmMap.getDecoder(clazz);

      if (!ctorMap.containsKey(clazz)) {
        ctorMap.put(clazz, parserClass.getConstructor(String.class));
      }

      final Constructor<? extends DecoderMpm<?>> ctor = ctorMap.get(clazz);
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
