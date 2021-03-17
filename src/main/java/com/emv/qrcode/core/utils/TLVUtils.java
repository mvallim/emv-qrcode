package com.emv.qrcode.core.utils;

public final class TLVUtils {

  public static final Integer ID_WORD_COUNT = 2; // 01 - 99

  public static final Integer VALUE_LENGTH_WORD_COUNT = 2; // 01 - 99

  private TLVUtils() {
    super();
  }

  public static final String valueOfTag(final String source) {
    return valueOfTag(source, 0);
  }

  public static final String valueOfTag(final String source, final Integer from) {
    final Integer start = from;
    final Integer end = start + ID_WORD_COUNT;
    return source.substring(start, end);
  }

  public static final Integer valueOfLength(final String source) {
    return valueOfLength(source, 0);
  }

  public static final Integer valueOfLength(final String source, final Integer from) {
    final Integer start = from + ID_WORD_COUNT;
    final Integer end = start + VALUE_LENGTH_WORD_COUNT;
    return Integer.valueOf(source.substring(start, end));
  }

  public static final String valueOf(final String source) {
    return valueOf(source, 0);
  }

  public static final String valueOf(final String source, final Integer from) {
    final Integer start = from + ID_WORD_COUNT + VALUE_LENGTH_WORD_COUNT;
    final Integer end = start + valueOfLength(source, from);
    return source.substring(start, end);
  }

  public static final String bucket(final String source, final Integer from) {
    final Integer start = from + ID_WORD_COUNT + VALUE_LENGTH_WORD_COUNT;
    final Integer end = start + valueOfLength(source, from);
    return source.substring(from, end);
  }

}
