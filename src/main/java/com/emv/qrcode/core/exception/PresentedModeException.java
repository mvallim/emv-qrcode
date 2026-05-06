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

/**
 * Base exception class for errors that occur during EMV QR code presented mode processing.
 * This exception is thrown when invalid operations or data are encountered in merchant or consumer presented modes.
 */
public class PresentedModeException extends RuntimeException {

  private static final long serialVersionUID = -3799054723266654250L;

  /**
   * Constructs a new PresentedModeException with the specified detail message.
   *
   * @param message the detail message explaining the reason for the exception
   */
  public PresentedModeException(final String message) {
    super(message);
  }

}
