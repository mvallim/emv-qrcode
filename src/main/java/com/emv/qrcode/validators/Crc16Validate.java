package com.emv.qrcode.validators;

import static br.com.fluentvalidator.predicate.StringPredicate.stringEquals;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSizeGreaterThanOrEqual;

import java.nio.charset.StandardCharsets;

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
        .must(stringEquals(value -> calcCrc16(StringUtils.right(value, 4)), value -> StringUtils.right(value, 4)))
        .when(stringSizeGreaterThanOrEqual(4))
          .withMessage("Invalid CRC16")
          .withAttempedValue(value -> StringUtils.right(value, 4));

    }

    private static String calcCrc16(final String crc) {
      return String.format("%04X", CRC.crc16(crc.getBytes(StandardCharsets.UTF_8)));
    }

  }

}
// @formatter:on
