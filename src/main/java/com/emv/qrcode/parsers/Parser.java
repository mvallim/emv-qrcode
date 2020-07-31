package com.emv.qrcode.parsers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import com.emv.qrcode.mpm.model.AdditionalDataFieldTemplate;
import com.emv.qrcode.mpm.model.EMVQR;
import com.emv.qrcode.mpm.model.MerchantInformationLanguageTemplate;
import com.emv.qrcode.mpm.model.UnreservedTemplateValue;

public abstract class Parser<T> implements Iterator<String> {

  private static final Map<Class<?>, Class<? extends Parser<?>>> mapParsers = new HashMap<>();

  static {
    mapParsers.put(EMVQR.class, EMVQRParser.class);
    mapParsers.put(AdditionalDataFieldTemplate.class, AdditionalDataFieldTemplateParser.class);
    mapParsers.put(MerchantInformationLanguageTemplate.class, MerchantInformationLanguageTemplateParser.class);
    mapParsers.put(UnreservedTemplateValue.class, UnreservedTemplateValueParser.class);
  }

  public static final Integer ID_WORD_COUNT = 2; // 01 - 99

  public static final Integer VALUE_LENGTH_WORD_COUNT = 2; // 01 - 99

  private Integer current;

  private final Integer max;

  private final String source;

  Parser(final String source) {
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

  protected abstract T parse();

  @SuppressWarnings("unchecked")
  public static <T> T parse(final String source, final Class<T> clazz) {
    try {
      final Class<? extends Parser<?>> parserClass = mapParsers.get(clazz);
      final Constructor<? extends Parser<?>> ctor = parserClass.getConstructor(String.class);
      final Parser<?> parser = ctor.newInstance(source);
      return (T) parser.parse();
    } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }

}
