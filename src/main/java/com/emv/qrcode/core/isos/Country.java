package com.emv.qrcode.core.isos;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;

/**
 *
 * ISO 3166 country codes
 *
 */
@Getter
public enum Country {

  AAX("X", "ALA", "248"), AL("AL", "ALB", "008"), DZ("DZ", "DZA", "012"), AS("AS", "ASM", "016"),
  AD("AD", "AND", "020"), AO("AO", "AGO", "024"), AI("AI", "AIA", "660"), AQ("AQ", "ATA", "010"),
  AG("AG", "ATG", "028"), AR("AR", "ARG", "032"), AM("AM", "ARM", "051"), AW("AW", "ABW", "533"),
  AU("AU", "AUS", "036"), AT("AT", "AUT", "040"), AZ("AZ", "AZE", "031"), BS("BS", "BHS", "044"),
  BH("BH", "BHR", "048"), BD("BD", "BGD", "050"), BB("BB", "BRB", "052"), BY("BY", "BLR", "112"),
  BE("BE", "BEL", "056"), BZ("BZ", "BLZ", "084"), BJ("BJ", "BEN", "204"), BM("BM", "BMU", "060"),
  BT("BT", "BTN", "064"), BO("BO", "BOL", "068"), BQ("BQ", "BES", "535"), BA("BA", "BIH", "070"),
  BW("BW", "BWA", "072"), BV("BV", "BVT", "074"), BR("BR", "BRA", "076"), IO("IO", "IOT", "086"),
  BN("BN", "BRN", "096"), BG("BG", "BGR", "100"), BF("BF", "BFA", "854"), BI("BI", "BDI", "108"),
  CV("CV", "CPV", "132"), KH("KH", "KHM", "116"), CM("CM", "CMR", "120"), CA("CA", "CAN", "124"),
  KY("KY", "CYM", "136"), CF("CF", "CAF", "140"), TD("TD", "TCD", "148"), CL("CL", "CHL", "152"),
  CN("CN", "CHN", "156"), CX("CX", "CXR", "162"), CC("CC", "CCK", "166"), CO("CO", "COL", "170"),
  KM("KM", "COM", "174"), CD("CD", "COD", "180"), CG("CG", "COG", "178"), CK("CK", "COK", "184"),
  CR("CR", "CRI", "188"), CI("CI", "CIV", "384"), HR("HR", "HRV", "191"), CU("CU", "CUB", "192"),
  CW("CW", "CUW", "531"), CY("CY", "CYP", "196"), CZ("CZ", "CZE", "203"), DK("DK", "DNK", "208"),
  DJ("DJ", "DJI", "262"), DM("DM", "DMA", "212"), DO("DO", "DOM", "214"), EC("EC", "ECU", "218"),
  EG("EG", "EGY", "818"), SV("SV", "SLV", "222"), GQ("GQ", "GNQ", "226"), ER("ER", "ERI", "232"),
  EE("EE", "EST", "233"), SZ("SZ", "SWZ", "748"), ET("ET", "ETH", "231"), FK("FK", "FLK", "238"),
  FO("FO", "FRO", "234"), FJ("FJ", "FJI", "242"), FI("FI", "FIN", "246"), FR("FR", "FRA", "250"),
  GF("GF", "GUF", "254"), PF("PF", "PYF", "258"), TF("TF", "ATF", "260"), GA("GA", "GAB", "266"),
  GM("GM", "GMB", "270"), GE("GE", "GEO", "268"), DE("DE", "DEU", "276"), GH("GH", "GHA", "288"),
  GI("GI", "GIB", "292"), GR("GR", "GRC", "300"), GL("GL", "GRL", "304"), GD("GD", "GRD", "308"),
  GP("GP", "GLP", "312"), GU("GU", "GUM", "316"), GT("GT", "GTM", "320"), GG("GG", "GGY", "831"),
  GN("GN", "GIN", "324"), GW("GW", "GNB", "624"), GY("GY", "GUY", "328"), HT("HT", "HTI", "332"),
  HM("HM", "HMD", "334"), VA("VA", "VAT", "336"), HN("HN", "HND", "340"), HK("HK", "HKG", "344"),
  HU("HU", "HUN", "348"), IS("IS", "ISL", "352"), IN("IN", "IND", "356"), ID("ID", "IDN", "360"),
  IR("IR", "IRN", "364"), IQ("IQ", "IRQ", "368"), IE("IE", "IRL", "372"), IM("IM", "IMN", "833"),
  IL("IL", "ISR", "376"), IT("IT", "ITA", "380"), JM("JM", "JAM", "388"), JP("JP", "JPN", "392"),
  JE("JE", "JEY", "832"), JO("JO", "JOR", "400"), KZ("KZ", "KAZ", "398"), KE("KE", "KEN", "404"),
  KI("KI", "KIR", "296"), KP("KP", "PRK", "408"), KR("KR", "KOR", "410"), KW("KW", "KWT", "414"),
  KG("KG", "KGZ", "417"), LA("LA", "LAO", "418"), LV("LV", "LVA", "428"), LB("LB", "LBN", "422"),
  LS("LS", "LSO", "426"), LR("LR", "LBR", "430"), LY("LY", "LBY", "434"), LI("LI", "LIE", "438"),
  LT("LT", "LTU", "440"), LU("LU", "LUX", "442"), MO("MO", "MAC", "446"), MK("MK", "MKD", "807"),
  MG("MG", "MDG", "450"), MW("MW", "MWI", "454"), MY("MY", "MYS", "458"), MV("MV", "MDV", "462"),
  ML("ML", "MLI", "466"), MT("MT", "MLT", "470"), MH("MH", "MHL", "584"), MQ("MQ", "MTQ", "474"),
  MR("MR", "MRT", "478"), MU("MU", "MUS", "480"), YT("YT", "MYT", "175"), MX("MX", "MEX", "484"),
  FM("FM", "FSM", "583"), MD("MD", "MDA", "498"), MC("MC", "MCO", "492"), MN("MN", "MNG", "496"),
  ME("ME", "MNE", "499"), MS("MS", "MSR", "500"), MA("MA", "MAR", "504"), MZ("MZ", "MOZ", "508"),
  MM("MM", "MMR", "104"), NA("NA", "NAM", "516"), NR("NR", "NRU", "520"), NP("NP", "NPL", "524"),
  NL("NL", "NLD", "528"), NC("NC", "NCL", "540"), NZ("NZ", "NZL", "554"), NI("NI", "NIC", "558"),
  NE("NE", "NER", "562"), NG("NG", "NGA", "566"), NU("NU", "NIU", "570"), NF("NF", "NFK", "574"),
  MP("MP", "MNP", "580"), NO("NO", "NOR", "578"), OM("OM", "OMN", "512"), PK("PK", "PAK", "586"),
  PW("PW", "PLW", "585"), PS("PS", "PSE", "275"), PA("PA", "PAN", "591"), PG("PG", "PNG", "598"),
  PY("PY", "PRY", "600"), PE("PE", "PER", "604"), PH("PH", "PHL", "608"), PN("PN", "PCN", "612"),
  PL("PL", "POL", "616"), PT("PT", "PRT", "620"), PR("PR", "PRI", "630"), QA("QA", "QAT", "634"),
  RE("RE", "REU", "638"), RO("RO", "ROU", "642"), RU("RU", "RUS", "643"), RW("RW", "RWA", "646"),
  BL("BL", "BLM", "652"), KN("KN", "KNA", "659"), LC("LC", "LCA", "662"), MF("MF", "MAF", "663"),
  PM("PM", "SPM", "666"), VC("VC", "VCT", "670"), SH("SH", "SHN", "654"), WS("WS", "WSM", "882"),
  SM("SM", "SMR", "674"), ST("ST", "STP", "678"), SA("SA", "SAU", "682"), SN("SN", "SEN", "686"),
  RS("RS", "SRB", "688"), SC("SC", "SYC", "690"), SL("SL", "SLE", "694"), SG("SG", "SGP", "702"),
  SX("SX", "SXM", "534"), SK("SK", "SVK", "703"), SI("SI", "SVN", "705"), SB("SB", "SLB", "090"),
  SO("SO", "SOM", "706"), ZA("ZA", "ZAF", "710"), GS("GS", "SGS", "239"), SS("SS", "SSD", "728"),
  ES("ES", "ESP", "724"), LK("LK", "LKA", "144"), SD("SD", "SDN", "729"), SR("SR", "SUR", "740"),
  SJ("SJ", "SJM", "744"), SE("SE", "SWE", "752"), CH("CH", "CHE", "756"), SY("SY", "SYR", "760"),
  TW("TW", "TWN", "158"), TJ("TJ", "TJK", "762"), TZ("TZ", "TZA", "834"), TH("TH", "THA", "764"),
  TL("TL", "TLS", "626"), TG("TG", "TGO", "768"), TK("TK", "TKL", "772"), TO("TO", "TON", "776"),
  TT("TT", "TTO", "780"), TN("TN", "TUN", "788"), TR("TR", "TUR", "792"), TM("TM", "TKM", "795"),
  TC("TC", "TCA", "796"), TV("TV", "TUV", "798"), UG("UG", "UGA", "800"), UA("UA", "UKR", "804"),
  AE("AE", "ARE", "784"), GB("GB", "GBR", "826"), UM("UM", "UMI", "581"), US("US", "USA", "840"),
  UY("UY", "URY", "858"), UZ("UZ", "UZB", "860"), VU("VU", "VUT", "548"), VE("VE", "VEN", "862"),
  VN("VN", "VNM", "704"), VG("VG", "VGB", "092"), VI("VI", "VIR", "850"), WF("WF", "WLF", "876"),
  EH("EH", "ESH", "732"), YE("YE", "YEM", "887"), ZM("ZM", "ZMB", "894"), ZW("ZW", "ZWE", "716");

  private static final Map<String, Country> mapString = new HashMap<>();

  static {
    for (final Country country : EnumSet.allOf(Country.class)) {
      mapString.put(country.getCode(), country);
    }
  }

  private final String alpha2;

  private final String alpha3;

  private final String code;

  private Country(final String alpha2, final String alpha3, final String code) {
    this.alpha2 = alpha2;
    this.alpha3 = alpha3;
    this.code = code;
  }

  @Override
  public String toString() {
    return code;
  }

  public static Country entryOf(final String code) {
    return StringUtils.isNoneBlank(code) ? mapString.get(code) : null;
  }

  public static boolean exists(final String lang) {
    return StringUtils.isNoneBlank(lang) && mapString.containsKey(lang);
  }

}
