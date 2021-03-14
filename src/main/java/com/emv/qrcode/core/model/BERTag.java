package com.emv.qrcode.core.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class BERTag implements Serializable {

  private static final long serialVersionUID = -4165695218130492616L;

  private static int CLASS_BITMASK = 0xC0; // 11000000 Tag class: bits 7-8 of the initial octet
  private static int TYPE_BITMASK = 0x20; // 00100000 Tag type: bit 6 of the initial octet

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
