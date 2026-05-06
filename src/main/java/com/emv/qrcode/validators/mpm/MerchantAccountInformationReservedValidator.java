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

package com.emv.qrcode.validators.mpm;

import static br.com.fluentvalidator.predicate.StringPredicate.stringSizeBetween;

import com.emv.qrcode.model.mpm.MerchantAccountInformationReserved;

import br.com.fluentvalidator.AbstractValidator;

/**
 * Validator for MerchantAccountInformationReserved in Merchant Presented Mode.
 * Validates the reserved merchant account information value length.
 *
 * <p>This validator handles tags 02-25 which are reserved for specific
 * payment network use as defined by EMVCo.</p>
 *
 * @see com.emv.qrcode.model.mpm.MerchantAccountInformationReserved
 */
// @formatter:off
class MerchantAccountInformationReservedValidator extends AbstractValidator<MerchantAccountInformationReserved> {

  /**
   * Defines validation rules for MerchantAccountInformationReserved.
   * Validates that the value length is between 1 and 99 characters.
   */
  @Override
  public void rules() {

    ruleFor(MerchantAccountInformationReserved::getValue)
      .must(stringSizeBetween(1, 99))
        .withFieldName("value")
        .withMessage("MerchantAccountInformation value must be between one and ninety-nine")
        .withAttempedValue(MerchantAccountInformationReserved::getValue)
        .critical();
  }

}
// @formatter:on
