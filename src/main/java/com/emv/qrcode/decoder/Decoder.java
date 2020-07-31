package com.emv.qrcode.decoder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

import com.emv.qrcode.decoder.mpm.AdditionalDataFieldTemplateDecoder;
import com.emv.qrcode.decoder.mpm.EMVQRDecoder;
import com.emv.qrcode.decoder.mpm.MerchantInformationLanguageTemplateDecoder;
import com.emv.qrcode.decoder.mpm.UnreservedTemplateValueDecoder;
import com.emv.qrcode.mpm.model.AdditionalDataFieldTemplate;
import com.emv.qrcode.mpm.model.EMVQR;
import com.emv.qrcode.mpm.model.MerchantInformationLanguageTemplate;
import com.emv.qrcode.mpm.model.UnreservedTemplateValue;

public abstract class Decoder<T> implements Iterator<String> {

  private static final Map<Class<?>, Class<? extends Decoder<?>>> mapParsers = new ConcurrentHashMap<>();

  static {
    mapParsers.put(EMVQR.class, EMVQRDecoder.class);
    mapParsers.put(AdditionalDataFieldTemplate.class, AdditionalDataFieldTemplateDecoder.class);
    mapParsers.put(MerchantInformationLanguageTemplate.class, MerchantInformationLanguageTemplateDecoder.class);
    mapParsers.put(UnreservedTemplateValue.class, UnreservedTemplateValueDecoder.class);
  }

  public static final Integer ID_WORD_COUNT = 2; // 01 - 99

  public static final Integer VALUE_LENGTH_WORD_COUNT = 2; // 01 - 99

  private Integer current;

  private final Integer max;

  private final String source;

  protected Decoder(final String source) {
    this.current = 0;
    this.max = source.length();
    this.source = source;
  }

  private Integer valueLength() {
    final Integer start = current + ID_WORD_COUNT;
    final Integer end = start + VALUE_LENGTH_WORD_COUNT;
    return Integer.valueOf(source.substring(start, end));
  }

  protected String getId() {
    final Integer start = current;
    final Integer end = start + ID_WORD_COUNT;
    return source.substring(start, end);
  }

  @Override
  public boolean hasNext() {
    final Integer end = current + ID_WORD_COUNT + VALUE_LENGTH_WORD_COUNT;
    return end <= max && current + valueLength() + ID_WORD_COUNT + VALUE_LENGTH_WORD_COUNT <= max;
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

  protected abstract T decode();

  public static synchronized void addDecoder(final Class<?> clazz, final Class<? extends Decoder<?>> decoder) {
    mapParsers.put(clazz, decoder);
  }

  public static <T> T decode(final String source, final Class<T> clazz) {
    try {
      final Class<? extends Decoder<?>> parserClass = mapParsers.get(clazz);
      final Constructor<? extends Decoder<?>> ctor = parserClass.getConstructor(String.class);
      final Decoder<?> parser = ctor.newInstance(source);
      return clazz.cast(parser.decode());
    } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }

}
