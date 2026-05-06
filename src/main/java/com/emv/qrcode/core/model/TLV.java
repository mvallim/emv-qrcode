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

package com.emv.qrcode.core.model;

import java.io.Serializable;
import java.util.Optional;

/**
 * Represents a Tag-Length-Value (TLV) data structure used in EMV QR code specifications.
 * This interface provides a generic way to handle TLV-encoded data elements.
 *
 * @param <T> the type of the tag
 * @param <V> the type of the value
 */
public interface TLV<T, V> extends Serializable {

  /**
   * Returns the tag identifier for this TLV element.
   *
   * @return the tag value
   */
  public T getTag();

  /**
   * Returns the value stored in this TLV element.
   *
   * @return the value
   */
  public V getValue();

  /**
   * Returns the length of the value in this TLV element.
   * Defaults to the string length of the value if not overridden.
   *
   * @return the length of the value, or 0 if value is null
   */
  default Integer getLength() {
    return Optional.ofNullable(getValue()).map(V::toString).map(String::length).orElse(0);
  }

}
