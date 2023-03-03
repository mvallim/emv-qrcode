/*
 * Copyright 2023 the original author or authors.
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

package com.emv.qrcode.core.model.cpm;

import java.io.Serializable;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;

public class BERTag implements Serializable {

  private static final long serialVersionUID = -4165695218130492616L;

  private static final int LAST_BYTE_MASK = 0x80;
  private static final int NEXT_BYTE_BITMASK = 0x1F; // 00011111
  private static final int CLASS_BITMASK = 0xC0; // 11000000 Tag class: bits 7-8 of the initial octet
  private static final int TYPE_BITMASK = 0x20; // 00100000 Tag type: bit 6 of the initial octet

  private final byte[] bytes;

  public BERTag(final byte[] bytes) {
    this.bytes = bytes;
  }

  public TagClass getTagClass() {
    return TagClass.entryOf(bytes[0] & CLASS_BITMASK);
  }

  public TagType getTagType() {
    return TagType.entryOf(bytes[0] & TYPE_BITMASK);
  }

  public byte[] getBytes() {
    return bytes;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(bytes);
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof BERTag)) {
      return false;
    }
    final BERTag other = (BERTag) obj;
    return Arrays.equals(bytes, other.bytes);
  }

  @Override
  public String toString() {
    return Hex.encodeHexString(bytes, false);
  }

  public static boolean hasNextByte(final byte value) {
    return NEXT_BYTE_BITMASK == (value & NEXT_BYTE_BITMASK);
  }

  public static boolean isNotLastByte(final byte value) {
    return LAST_BYTE_MASK == (value & LAST_BYTE_MASK);
  }

  public enum TagClass {

    UNIVERSAL(0x00), // 00000000
    APPLICATION(0x40), // 01000000
    CONTEXT_SPECIFIC(0x80), // 10000000
    PRIVATE(0xC0); // 11000000

    private static Map<Integer, TagClass> mapInteger = new HashMap<>();

    static {
      for (final TagClass tagClass : EnumSet.allOf(TagClass.class)) {
        mapInteger.put(tagClass.getValue(), tagClass);
      }
    }

    private final int value;

    private TagClass(final int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }

    public static TagClass entryOf(final int value) {
      return mapInteger.get(value);
    }

  }

  public enum TagType {

    PRIMITIVE(0x00), // 00000000
    CONSTRUCTED(0x20); // 00100000

    private static Map<Integer, TagType> mapInteger = new HashMap<>();

    static {
      for (final TagType tagType : EnumSet.allOf(TagType.class)) {
        mapInteger.put(tagType.getValue(), tagType);
      }
    }

    private final int value;

    private TagType(final int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }

    public static TagType entryOf(final int value) {
      return mapInteger.get(value);
    }

  }

}
