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

public class InvalidTagException extends PresentedModeException {

  private static final long serialVersionUID = 2158566424345876356L;

  private final String tag;

  private final String value;

  public InvalidTagException(final String scope, final String tag, final String value) {
    super(MessageFormat.format("Scope: ''{0}'' invalid ''{1}'' tag", scope, tag));
    this.tag = tag;
    this.value = value;
  }

  public String getTag() {
    return tag;
  }

  public String getValue() {
    return value;
  }

}
