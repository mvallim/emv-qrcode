package com.emv.qrcode.core;

import java.util.Iterator;
import java.util.NoSuchElementException;

import lombok.Getter;

@Getter
public final class Parser implements Iterator<String> {
  
  private static final Integer ID_WORD_COUNT = 2; // 01 - 99
  
  private static final Integer VALUE_LENGTH_WORD_COUNT = 2; // 01 - 99

  private Integer current;

  private Integer max;

  private final String source;

  private Parser(final String source) {
    this.current = 0;
    this.max = source.length();
    this.source = source;
  }
  
  private Integer valueLength() {
    final Integer start = current + ID_WORD_COUNT;
    final Integer end = start + VALUE_LENGTH_WORD_COUNT;
    return Integer.valueOf(source.substring(start, end));
  }
  
  public String getId() {
    final Integer start = current;
    final Integer end = start + ID_WORD_COUNT;
    
    return source.substring(start, end);
  }

  @Override
  public boolean hasNext() {
    return current + valueLength() + ID_WORD_COUNT + VALUE_LENGTH_WORD_COUNT <= max;
  }

  @Override
  public String next() {
    
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    
    final Integer start = current + ID_WORD_COUNT + VALUE_LENGTH_WORD_COUNT;
    final Integer end = start + valueLength();
    
    current = end;
    
    return source.substring(start, end);
  }

  public static Parser parse(final String sequence) {
    return new Parser(sequence);
  }
  
}
