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

package com.emv.qrcode.core.exception;

import java.text.MessageFormat;

/**
 * Exception thrown when an invalid tag is encountered during EMV QR code decoding.
 * This exception provides details about the scope, tag identifier, and value that caused the error.
 */
public class InvalidTagException extends PresentedModeException {

  private static final long serialVersionUID = 2158566424345876356L;

  private final String tag;

  private final String value;

  /**
   * Constructs a new InvalidTagException with the specified scope, tag, and value.
   *
   * @param scope the scope where the invalid tag was found
   * @param tag the invalid tag identifier
   * @param value the value associated with the invalid tag
   */
  public InvalidTagException(final String scope, final String tag, final String value) {
    super(MessageFormat.format("Scope: ''{0}'' invalid ''{1}'' tag", scope, tag));
    this.tag = tag;
    this.value = value;
  }

  /**
   * Returns the invalid tag identifier that caused this exception.
   *
   * @return the invalid tag
   */
  public String getTag() {
    return tag;
  }

  /**
   * Returns the value associated with the invalid tag.
   *
   * @return the value of the invalid tag
   */
  public String getValue() {
    return value;
  }

}
