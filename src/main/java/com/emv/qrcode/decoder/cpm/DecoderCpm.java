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
   * @throws PresentedModeException
   */
  public static final <T> T decode(final String source, final Class<T> clazz) throws PresentedModeException {
    return decode(Base64.getDecoder().decode(source), clazz);
  }

  /**
   * Decode CPM using byte array
   *
   * @param <T> target class
   * @param source byte array CPM
   * @param clazz target class
   * @return target class result
   * @throws PresentedModeException
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
