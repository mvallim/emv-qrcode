package com.emv.qrcode.core.isos;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;

/**
 *
 * ISO 639-1 language codes
 *
 */
@Getter
public enum Language {

  AB("AB"), AA("AA"), AF("AF"), AK("AK"), SQ("SQ"), AM("AM"), AR("AR"), AN("AN"), HY("HY"), AS("AS"), AV("AV"),
  AE("AE"), AY("AY"), AZ("AZ"), BM("BM"), BA("BA"), EU("EU"), BE("BE"), BN("BN"), BH("BH"), BI("BI"), BS("BS"),
  BR("BR"), BG("BG"), MY("MY"), CA("CA"), CH("CH"), CE("CE"), NY("NY"), ZH("ZH"), CV("CV"), KW("KW"), CO("CO"),
  CR("CR"), HR("HR"), CS("CS"), DA("DA"), DV("DV"), NL("NL"), DZ("DZ"), EN("EN"), EO("EO"), ET("ET"), EE("EE"),
  FO("FO"), FJ("FJ"), FL("FL"), FI("FI"), FR("FR"), FF("FF"), GL("GL"), KA("KA"), DE("DE"), EL("EL"), GN("GN"),
  GU("GU"), HT("HT"), HA("HA"), HE("HE"), HZ("HZ"), HI("HI"), HO("HO"), HU("HU"), IA("IA"), ID("ID"), IE("IE"),
  GA("GA"), IG("IG"), IK("IK"), IO("IO"), IS("IS"), IT("IT"), IU("IU"), JA("JA"), JV("JV"), KL("KL"), KN("KN"),
  KR("KR"), KS("KS"), KK("KK"), KM("KM"), KI("KI"), RW("RW"), KY("KY"), KV("KV"), KG("KG"), KO("KO"), KU("KU"),
  KJ("KJ"), LA("LA"), LB("LB"), LG("LG"), LI("LI"), LN("LN"), LO("LO"), LT("LT"), LU("LU"), LV("LV"), GV("GV"),
  MK("MK"), MG("MG"), MS("MS"), ML("ML"), MT("MT"), MI("MI"), MR("MR"), MH("MH"), MN("MN"), NA("NA"), NV("NV"),
  ND("ND"), NE("NE"), NG("NG"), NB("NB"), NN("NN"), NO("NO"), II("II"), NR("NR"), OC("OC"), OJ("OJ"), CU("CU"),
  OM("OM"), OR("OR"), OS("OS"), PA("PA"), PI("PI"), FA("FA"), PL("PL"), PS("PS"), PT("PT"), QU("QU"), RM("RM"),
  RN("RN"), RO("RO"), RU("RU"), SA("SA"), SC("SC"), SD("SD"), SE("SE"), SM("SM"), SG("SG"), SR("SR"), GD("GD"),
  SN("SN"), SI("SI"), SK("SK"), SL("SL"), SO("SO"), ST("ST"), ES("ES"), SU("SU"), SW("SW"), SS("SS"), SV("SV"),
  TA("TA"), TE("TE"), TG("TG"), TH("TH"), TI("TI"), BO("BO"), TK("TK"), TL("TL"), TN("TN"), TO("TO"), TR("TR"),
  TS("TS"), TT("TT"), TW("TW"), TY("TY"), UG("UG"), UK("UK"), UR("UR"), UZ("UZ"), VE("VE"), VI("VI"), VO("VO"),
  WA("WA"), CY("CY"), WO("WO"), FY("FY"), XH("XH"), YI("YI"), YO("YO"), ZA("ZA"), ZU("ZU");

  private static final Map<String, Language> mapString = new HashMap<>();

  static {
    for (final Language language : EnumSet.allOf(Language.class)) {
      mapString.put(language.getLang(), language);
    }
  }

  private final String lang;

  private Language(final String lang) {
    this.lang = lang;
  }

  @Override
  public String toString() {
    return lang;
  }

  public static Language entryOf(final String lang) {
    return StringUtils.isNoneBlank(lang) ? mapString.get(lang.toUpperCase()) : null;
  }

  public static boolean exists(final String lang) {
    return StringUtils.isNoneBlank(lang) && mapString.containsKey(lang.toUpperCase());
  }

}
