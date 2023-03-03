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

package com.emv.qrcode.validators;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;

import org.junit.Test;

import br.com.fluentvalidator.context.ValidationResult;

public class Crc16ValidatorTest {

  @Test
  public void testSuccessCrc16Sample1() {
    final String encoded = "00020101021229300012D156000000000510A93FO3230Q31280012D15600000001030812345678520441115802CN5914BEST TRANSPORT6007BEIJING64200002ZH0104最佳运输0202北京540523.7253031565502016233030412340603***0708A60086670902ME91320016A0112233449988770708123456786304A13A";

    final ValidationResult validationResult = Crc16Validate.validate(encoded);

    assertThat(validationResult.isValid(), equalTo(true));
  }

  @Test
  public void testFailValidateWhenWithoutCRCDecoded() {
    final String encoded = "00020101021102160004hoge0104abcd520441115303156540523.7255020256035005802CN5914BEST TRANSPORT6007BEIJING6107123456762800205678900305098760505abcde0705klmno0805pqres0903tuv1004abcd5016000412340104ijkl64280002ZH0102北京0204最佳运输0304abcd65020080320016A011223344998877070812345678";

    final ValidationResult validationResult = Crc16Validate.validate(encoded);

    assertThat(validationResult.isValid(), equalTo(false));
    assertThat(validationResult.getErrors(), hasSize(1));
    assertThat(validationResult.getErrors(), hasItem(hasProperty("message", equalTo("Invalid CRC16"))));
    assertThat(validationResult.getErrors(), hasItem(hasProperty("attemptedValue", equalTo("5678"))));
  }

  @Test
  public void testFailValidateWhenIncompleteCRCDecoded() {
    final String encoded = "00020101021102160004hoge0104abcd520441115303156540523.7255020256035005802CN5914BEST TRANSPORT6007BEIJING6107123456762800205678900305098760505abcde0705klmno0805pqres0903tuv1004abcd5016000412340104ijkl64280002ZH0102北京0204最佳运输0304abcd65020080320016A01122334499887707081234567863";

    final ValidationResult validationResult = Crc16Validate.validate(encoded);

    assertThat(validationResult.isValid(), equalTo(false));
    assertThat(validationResult.getErrors(), hasSize(1));
    assertThat(validationResult.getErrors(), hasItem(hasProperty("message", equalTo("Invalid CRC16"))));
    assertThat(validationResult.getErrors(), hasItem(hasProperty("attemptedValue", equalTo("7863"))));
  }

  @Test
  public void testFailValidateWhenIncomplete2CRCDecoded() {
    final String encoded = "00020101021102160004hoge0104abcd520441115303156540523.7255020256035005802CN5914BEST TRANSPORT6007BEIJING6107123456762800205678900305098760505abcde0705klmno0805pqres0903tuv1004abcd5016000412340104ijkl64280002ZH0102北京0204最佳运输0304abcd65020080320016A0112233449988770708123456786304";

    final ValidationResult validationResult = Crc16Validate.validate(encoded);

    assertThat(validationResult.isValid(), equalTo(false));
    assertThat(validationResult.getErrors(), hasSize(1));
    assertThat(validationResult.getErrors(), hasItem(hasProperty("message", equalTo("Invalid CRC16"))));
    assertThat(validationResult.getErrors(), hasItem(hasProperty("attemptedValue", equalTo("6304"))));
  }

  @Test
  public void testFailValidateWhenInvalidCRCDecoded() {
    final String encoded = "00020101021102160004hoge0104abcd520441115303156540523.7255020256035005802CN5914BEST TRANSPORT6007BEIJING6107123456762800205678900305098760505abcde0705klmno0805pqres0903tuv1004abcd5016000412340104ijkl64280002ZH0102北京0204最佳运输0304abcd65020080320016A0112233449988770708123456786304XXXX";

    final ValidationResult validationResult = Crc16Validate.validate(encoded);

    assertThat(validationResult.isValid(), equalTo(false));
    assertThat(validationResult.getErrors(), hasSize(1));
    assertThat(validationResult.getErrors(), hasItem(hasProperty("message", equalTo("Invalid CRC16"))));
    assertThat(validationResult.getErrors(), hasItem(hasProperty("attemptedValue", equalTo("XXXX"))));
  }

  @Test
  public void testFailValidateWhenInvalidDecoded() {
    final String encoded = "000";

    final ValidationResult validationResult = Crc16Validate.validate(encoded);

    assertThat(validationResult.isValid(), equalTo(false));
    assertThat(validationResult.getErrors(), hasSize(1));
    assertThat(validationResult.getErrors(), hasItem(hasProperty("message", equalTo("Invalid EMV, size less than four"))));
    assertThat(validationResult.getErrors(), hasItem(hasProperty("attemptedValue", equalTo(3))));
  }

}
