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

package com.emv.qrcode.model.mpm;

import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.model.TLV;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class UnreservedTemplate implements TLV<String, Unreserved> {

  private static final long serialVersionUID = -1445641777082739037L;

  private String tag;

  private Unreserved value;

  /**
   * Constructs an UnreservedTemplate with the specified tag.
   *
   * @param tag the tag identifier
   */
  public UnreservedTemplate(final String tag) {
    setTag(tag);
  }

  /**
   * Constructs an UnreservedTemplate with the specified tag and Globally Unique Identifier.
   *
   * @param tag the tag identifier
   * @param globallyUniqueIdentifier the globally unique identifier value
   */
  public UnreservedTemplate(final String tag, final String globallyUniqueIdentifier) {
    setTag(tag);
    setValue(new Unreserved(globallyUniqueIdentifier));
  }

  /**
   * Adds context-specific data to the Unreserved template.
   *
   * @param tag the context-specific data tag
   * @param value the context-specific data value
   */
  public void addContextSpecificData(final String tag, final String value) {
    setValue(Optional.ofNullable(getValue()).orElse(new Unreserved()));
    getValue().addContextSpecificData(tag, value);
  }

  @Override
  public String getTag() {
    return tag;
  }

  @Override
  public Unreserved getValue() {
    return value;
  }

  @Override
  public String toString() {

    if (Objects.isNull(value)) {
      return StringUtils.EMPTY;
    }

    final String string = value.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    return String.format("%s%02d%s", tag, string.length(), string);
  }
}
