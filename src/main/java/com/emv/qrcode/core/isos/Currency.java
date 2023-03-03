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

package com.emv.qrcode.core.isos;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;

/**
 *
 * ISO 4217 currency codes
 *
 */
@Getter
public enum Currency {

  AED("AED", "784"), AFN("AFN", "971"), ALL("ALL", "008"), AMD("AMD", "051"), ANG("ANG", "532"), AOA("AOA", "973"), ARS("ARS", "032"), AUD("AUD", "036"), AWG("AWG", "533"), AZN("AZN", "944"), BAM("BAM", "977"), BBD("BBD", "052"),
  BDT("BDT", "050"), BGN("BGN", "975"), BHD("BHD", "048"), BIF("BIF", "108"), BMD("BMD", "060"), BND("BND", "096"), BOB("BOB", "068"), BOV("BOV", "984"), BRL("BRL", "986"), BSD("BSD", "044"), BTN("BTN", "064"), BWP("BWP", "072"),
  BYN("BYN", "933"), BZD("BZD", "084"), CAD("CAD", "124"), CDF("CDF", "976"), CHE("CHE", "947"), CHF("CHF", "756"), CHW("CHW", "948"), CLF("CLF", "990"), CLP("CLP", "152"), CNY("CNY", "156"), COP("COP", "170"), COU("COU", "970"),
  CRC("CRC", "188"), CUC("CUC", "931"), CUP("CUP", "192"), CVE("CVE", "132"), CZK("CZK", "203"), DJF("DJF", "262"), DKK("DKK", "208"), DOP("DOP", "214"), DZD("DZD", "012"), EGP("EGP", "818"), ERN("ERN", "232"), ETB("ETB", "230"),
  EUR("EUR", "978"), FJD("FJD", "242"), FKP("FKP", "238"), GBP("GBP", "826"), GEL("GEL", "981"), GHS("GHS", "936"), GIP("GIP", "292"), GMD("GMD", "270"), GNF("GNF", "324"), GTQ("GTQ", "320"), GYD("GYD", "328"), HKD("HKD", "344"),
  HNL("HNL", "340"), HRK("HRK", "191"), HTG("HTG", "332"), HUF("HUF", "348"), IDR("IDR", "360"), ILS("ILS", "376"), INR("INR", "356"), IQD("IQD", "368"), IRR("IRR", "364"), ISK("ISK", "352"), JMD("JMD", "388"), JOD("JOD", "400"),
  JPY("JPY", "392"), KES("KES", "404"), KGS("KGS", "417"), KHR("KHR", "116"), KMF("KMF", "174"), KPW("KPW", "408"), KRW("KRW", "410"), KWD("KWD", "414"), KYD("KYD", "136"), KZT("KZT", "398"), LAK("LAK", "418"), LBP("LBP", "422"),
  LKR("LKR", "144"), LRD("LRD", "430"), LSL("LSL", "426"), LYD("LYD", "434"), MAD("MAD", "504"), MDL("MDL", "498"), MGA("MGA", "969"), MKD("MKD", "807"), MMK("MMK", "104"), MNT("MNT", "496"), MOP("MOP", "446"), MRU("MRU", "929"),
  MUR("MUR", "480"), MVR("MVR", "462"), MWK("MWK", "454"), MXN("MXN", "484"), MXV("MXV", "979"), MYR("MYR", "458"), MZN("MZN", "943"), NAD("NAD", "516"), NGN("NGN", "566"), NIO("NIO", "558"), NOK("NOK", "578"), NPR("NPR", "524"),
  NZD("NZD", "554"), OMR("OMR", "512"), PAB("PAB", "590"), PEN("PEN", "604"), PGK("PGK", "598"), PHP("PHP", "608"), PKR("PKR", "586"), PLN("PLN", "985"), PYG("PYG", "600"), QAR("QAR", "634"), RON("RON", "946"), RSD("RSD", "941"),
  RUB("RUB", "643"), RWF("RWF", "646"), SAR("SAR", "682"), SBD("SBD", "090"), SCR("SCR", "690"), SDG("SDG", "938"), SEK("SEK", "752"), SGD("SGD", "702"), SHP("SHP", "654"), SLL("SLL", "694"), SOS("SOS", "706"), SRD("SRD", "968"),
  SSP("SSP", "728"), STN("STN", "930"), SVC("SVC", "222"), SYP("SYP", "760"), SZL("SZL", "748"), THB("THB", "764"), TJS("TJS", "972"), TMT("TMT", "934"), TND("TND", "788"), TOP("TOP", "776"), TRY("TRY", "949"), TTD("TTD", "780"),
  TWD("TWD", "901"), TZS("TZS", "834"), UAH("UAH", "980"), UGX("UGX", "800"), USD("USD", "840"), USN("USN", "997"), UYI("UYI", "940"), UYU("UYU", "858"), UYW("UYW", "927"), UZS("UZS", "860"), VES("VES", "928"), VND("VND", "704"),
  VUV("VUV", "548"), WST("WST", "882"), XAF("XAF", "950"), XAG("XAG", "961"), XAU("XAU", "959"), XBA("XBA", "955"), XBB("XBB", "956"), XBC("XBC", "957"), XBD("XBD", "958"), XCD("XCD", "951"), XDR("XDR", "960"), XOF("XOF", "952"),
  XPD("XPD", "964"), XPF("XPF", "953"), XPT("XPT", "962"), XSU("XSU", "994"), XTS("XTS", "963"), XUA("XUA", "965"), XXX("XXX", "999"), YER("YER", "886"), ZAR("ZAR", "710"), ZMW("ZMW", "967"), ZWL("ZWL", "932");

  private static final Map<String, Currency> mapString = new HashMap<>();

  static {
    for (final Currency currency : EnumSet.allOf(Currency.class)) {
      mapString.put(currency.getCode(), currency);
    }
  }

  private final String code;
  private final String number;

  private Currency(final String code, final String number) {
    this.code = code;
    this.number = number;
  }

  @Override
  public String toString() {
    return code;
  }

  public static Currency entryOf(final String code) {
    return StringUtils.isNoneBlank(code) ? mapString.get(code.toUpperCase()) : null;
  }

  public static boolean exists(final String code) {
    return StringUtils.isNoneBlank(code) && mapString.containsKey(code.toUpperCase());
  }

}
