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

package com.emv.qrcode.validators;

import com.emv.qrcode.model.cpm.ConsumerPresentedMode;
import com.emv.qrcode.validators.cpm.ConsumerPresentedModeValidator;

import br.com.fluentvalidator.Validator;
import br.com.fluentvalidator.context.ValidationResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Validator class for ConsumerPresentedMode (CPM) QR code data. This class uses
 * FluentValidator to validate CPM data structures.
 *
 * @see ConsumerPresentedMode
 * @see ConsumerPresentedModeValidator
 * @see br.com.fluentvalidator.Validator
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConsumerPresentedModeValidate {

  private static final Validator<ConsumerPresentedMode> VALIDATOR = new ConsumerPresentedModeValidator();

  /**
   * Validates a ConsumerPresentedMode instance using the configured validator.
   *
   * @param instance the ConsumerPresentedMode instance to validate (must not be
   *                 null)
   * @return the validation result containing any validation errors
   * @see ConsumerPresentedModeValidator
   */
  public static final ValidationResult validate(final ConsumerPresentedMode instance) {
    return VALIDATOR.validate(instance);
  }

}
