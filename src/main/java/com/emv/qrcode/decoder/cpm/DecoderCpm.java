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
/**
 * Abstract base class for decoding Consumer Presented Mode (CPM) QR code data.
 * This class provides the foundation for all CPM decoders that parse BER-TLV-encoded byte arrays.
 *
 * @param <T> the type of decoded object
 * @see DecodeCpmIterator
 * @see DecodersCpmMap
 */
public abstract class DecoderCpm<T> {

  private static final Map<Class<?>, Constructor<? extends DecoderCpm<?>>> ctorMap = new ConcurrentHashMap<>();

  protected final Iterator<byte[]> iterator;

  /**
   * Constructs a DecoderCpm with the specified source byte array.
   *
   * @param source the CPM QR code byte array to decode
   */
  protected DecoderCpm(final byte[] source) {
    this.iterator = new DecodeCpmIterator(source);
  }

  /**
   * Decodes the source byte array and returns the decoded object.
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
   * Decodes a Base64-encoded string into the specified target class.
   *
   * @param <T> the type of the target class
   * @param source the Base64-encoded CPM QR code string to decode
   * @param clazz the target class to decode into
   * @return the decoded object of type T
   * @throws PresentedModeException if decoding fails
   */
  public static final <T> T decode(final String source, final Class<T> clazz) throws PresentedModeException {
    return decode(Base64.getDecoder().decode(source), clazz);
  }

  /**
   * Decodes a byte array into the specified target class.
   *
   * @param <T> the type of the target class
   * @param source the CPM QR code byte array to decode
   * @param clazz the target class to decode into
   * @return the decoded object of type T
   * @throws PresentedModeException if decoding fails
   */
  public static final <T> T decode(final byte[] source, final Class<T> clazz) throws PresentedModeException {
    try {
      final Class<? extends DecoderCpm<?>> parserClass = DecodersCpmMap.getDecoder(clazz);

      if (!ctorMap.containsKey(clazz)) {
        ctorMap.put(clazz, parserClass.getConstructor(byte[].class));
      }

      final Constructor<? extends DecoderCpm<?>> ctor = ctorMap.get(clazz);
      final DecoderCpm<?> parser = ctor.newInstance(source);
      return clazz.cast(parser.decode());
    } catch (final PresentedModeException ex) {
      throw ex;
    } catch (final Exception ex) {
      throw new RuntimeException(ex);
    }
  }

}
// @formatter:on
