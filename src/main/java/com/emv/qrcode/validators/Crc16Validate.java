package com.emv.qrcode.validators;

import static br.com.fluentvalidator.predicate.StringPredicate.stringEquals;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSizeGreaterThanOrEqual;

import java.nio.charset.StandardCharsets;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.CRC;

import br.com.fluentvalidator.AbstractValidator;
import br.com.fluentvalidator.Validator;
import br.com.fluentvalidator.context.ValidationResult;

// @formatter:off
public final class Crc16Validate {

  private static final Validator<String> VALIDATOR = new Crc16Validator();

  private Crc16Validate() {
    super();
  }

  public static final ValidationResult validate(final String instance) {
    return VALIDATOR.validate(instance);
  }

  static class Crc16Validator extends AbstractValidator<String> {

    @Override
    public void rules() {

      failFastRule();

      /**
       * Validate CRC16
       */
      ruleFor("CRC", value -> value)
        .must(stringSizeGreaterThanOrEqual(4))
          .withMessage("Invalid EMV, size less than four")
          .withAttempedValue(StringUtils::length);

      ruleFor("CRC", value -> value)
        .must(stringEquals(calcCrc16(), extractCrc16()))
        .when(stringSizeGreaterThanOrEqual(4))
          .withMessage("Invalid CRC16")
          .withAttempedValue(value -> StringUtils.right(value, 4));

    }

    private static Function<String, String> extractCrc16() {
      return value -> StringUtils.right(value, 4);
    }

    private static Function<String, String> calcCrc16() {
      return value -> String.format("%04X", CRC.crc16(value.substring(0, value.length() - 4).getBytes(StandardCharsets.UTF_8)));
    }

  }

}
// @formatter:on
