package com.emv.qrcode.model.mpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.decoder.Decoder;

public class MerchantPresentedModeTest {

  @Test
  public void testSuccessToString() {

    final AdditionalDataFieldTemplate additionalDataField = getAddtionalDataField();
    final MerchantAccountInformationTemplate merchantAccountInformation = getMerchanAccountInformation();
    final MerchantInformationLanguageTemplate merchantInformationLanguage = getMerchantInformationLanguage();
    final UnreservedTemplate unreserved = getUnreserved();
    final TagLengthString rFUforEMVCo = new TagLengthString("65", "00");

    final MerchantPresentedMode merchantPresentMode = new MerchantPresentedMode();
    merchantPresentMode.setAdditionalDataField(additionalDataField);
    merchantPresentMode.setCountryCode("CN");
    merchantPresentMode.setMerchantCategoryCode("4111");
    merchantPresentMode.setMerchantCity("BEIJING");
    merchantPresentMode.setMerchantInformationLanguage(merchantInformationLanguage);
    merchantPresentMode.setMerchantName("BEST TRANSPORT");
    merchantPresentMode.setPayloadFormatIndicator("01");
    merchantPresentMode.setPointOfInitiationMethod("11");
    merchantPresentMode.setPostalCode("1234567");
    merchantPresentMode.setTipOrConvenienceIndicator("01");
    merchantPresentMode.setTransactionAmount("23.72");
    merchantPresentMode.setTransactionCurrency("156");
    merchantPresentMode.setValueOfConvenienceFeeFixed("500");
    merchantPresentMode.setValueOfConvenienceFeePercentage("5");
    merchantPresentMode.addMerchantAccountInformation(merchantAccountInformation);
    merchantPresentMode.addRFUforEMVCo(rFUforEMVCo);
    merchantPresentMode.addUnreserved(unreserved);

    assertThat(merchantPresentMode.toString(), equalTo(
        "00020101021102160004hoge0104abcd520441115303156540523.725502015603500570155802CN5914BEST TRANSPORT6007BEIJING6107123456762970105123450205678900305098760405543210505abcde0605fghij0705klmno0805pqres0905tuvxy1004abcd5004ijkl64280002ZH0102北京0204最佳运输0304abcd65020080320016A0112233449988770708123456786304C395"));
  }

  private MerchantAccountInformationTemplate getMerchanAccountInformation() {

    final TagLengthString paymentNetworkSpecific = new TagLengthString();
    paymentNetworkSpecific.setTag("01");
    paymentNetworkSpecific.setValue("abcd");

    final MerchantAccountInformation merchantAccountInformationValue = new MerchantAccountInformation();
    merchantAccountInformationValue.setGloballyUniqueIdentifier("hoge");
    merchantAccountInformationValue.addPaymentNetworkSpecific(paymentNetworkSpecific);

    final MerchantAccountInformationTemplate merchantAccountInformation = new MerchantAccountInformationTemplate();
    merchantAccountInformation.setValue(merchantAccountInformationValue);
    merchantAccountInformation.setTag("02");
    return merchantAccountInformation;
  }

  private UnreservedTemplate getUnreserved() {

    final TagLengthString contextSpecificData = new TagLengthString();
    contextSpecificData.setTag("07");
    contextSpecificData.setValue("12345678");

    final Unreserved value = new Unreserved();
    value.setGloballyUniqueIdentifier("A011223344998877");
    value.addContextSpecificData(contextSpecificData);

    final UnreservedTemplate unreserved = new UnreservedTemplate();
    unreserved.setValue(value);
    unreserved.setTag("80");
    return unreserved;
  }

  private MerchantInformationLanguageTemplate getMerchantInformationLanguage() {

    final TagLengthString rFUforEMVCo = new TagLengthString();
    rFUforEMVCo.setTag("03");
    rFUforEMVCo.setValue("abcd");

    final MerchantInformationLanguage merchantInformationLanguageValue = new MerchantInformationLanguage();
    merchantInformationLanguageValue.setLanguagePreference("ZH");
    merchantInformationLanguageValue.setMerchantName("北京");
    merchantInformationLanguageValue.setMerchantCity("最佳运输");
    merchantInformationLanguageValue.addRFUforEMVCo(rFUforEMVCo);

    final MerchantInformationLanguageTemplate merchantInformationLanguage = new MerchantInformationLanguageTemplate();
    merchantInformationLanguage.setValue(merchantInformationLanguageValue);
    return merchantInformationLanguage;
  }

  private AdditionalDataFieldTemplate getAddtionalDataField() {

    final TagLengthString purposeTransaction = new TagLengthString();
    purposeTransaction.setTag("08");
    purposeTransaction.setValue("pqres");

    final TagLengthString paymentSystemSpecific = new TagLengthString();
    paymentSystemSpecific.setTag("50");
    paymentSystemSpecific.setValue("ijkl");

    final TagLengthString rFUforEMVCo = new TagLengthString();
    rFUforEMVCo.setTag("10");
    rFUforEMVCo.setValue("abcd");

    final AdditionalDataField additionalDataFieldValue = new AdditionalDataField();
    additionalDataFieldValue.setAdditionalConsumerDataRequest("tuvxy");
    additionalDataFieldValue.setBillNumber("12345");
    additionalDataFieldValue.setCustomerLabel("fghij");
    additionalDataFieldValue.setLoyaltyNumber("54321");
    additionalDataFieldValue.setMobileNumber("67890");
    additionalDataFieldValue.setPurposeTransaction("pqres");
    additionalDataFieldValue.setReferenceLabel("abcde");
    additionalDataFieldValue.setStoreLabel("09876");
    additionalDataFieldValue.setTerminalLabel("klmno");
    additionalDataFieldValue.addPaymentSystemSpecific(paymentSystemSpecific);
    additionalDataFieldValue.addRFUforEMVCo(rFUforEMVCo);

    final AdditionalDataFieldTemplate additionalDataField = new AdditionalDataFieldTemplate();
    additionalDataField.setValue(additionalDataFieldValue);

    return additionalDataField;
  }

  @Test
  public void testSuccessToStringWhenValueIsNull() {

    final MerchantPresentedMode merchantPresentMode = new MerchantPresentedMode();
    merchantPresentMode.setAdditionalDataField(null);
    merchantPresentMode.setCountryCode(null);
    merchantPresentMode.setCRC(null);
    merchantPresentMode.setMerchantCategoryCode(null);
    merchantPresentMode.setMerchantCity(null);
    merchantPresentMode.setMerchantInformationLanguage(null);
    merchantPresentMode.setMerchantName(null);
    merchantPresentMode.setPayloadFormatIndicator(null);
    merchantPresentMode.setPointOfInitiationMethod(null);
    merchantPresentMode.setPostalCode(null);
    merchantPresentMode.setTipOrConvenienceIndicator(null);
    merchantPresentMode.setTransactionAmount(null);
    merchantPresentMode.setTransactionCurrency(null);
    merchantPresentMode.setValueOfConvenienceFeeFixed(null);
    merchantPresentMode.setValueOfConvenienceFeePercentage(null);

    assertThat(merchantPresentMode.toString(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToStringWhenValueIsEmpty() {
    final MerchantPresentedMode merchantPresentMode = new MerchantPresentedMode();

    merchantPresentMode.setAdditionalDataField(new AdditionalDataFieldTemplate());
    merchantPresentMode.setCountryCode(StringUtils.EMPTY);
    merchantPresentMode.setCRC(StringUtils.EMPTY);
    merchantPresentMode.setMerchantCategoryCode(StringUtils.EMPTY);
    merchantPresentMode.setMerchantCity(StringUtils.EMPTY);
    merchantPresentMode.setMerchantInformationLanguage(new MerchantInformationLanguageTemplate());
    merchantPresentMode.setMerchantName(StringUtils.EMPTY);
    merchantPresentMode.setPayloadFormatIndicator(StringUtils.EMPTY);
    merchantPresentMode.setPointOfInitiationMethod(StringUtils.EMPTY);
    merchantPresentMode.setPostalCode(StringUtils.EMPTY);
    merchantPresentMode.setTipOrConvenienceIndicator(StringUtils.EMPTY);
    merchantPresentMode.setTransactionAmount(StringUtils.EMPTY);
    merchantPresentMode.setTransactionCurrency(StringUtils.EMPTY);
    merchantPresentMode.setValueOfConvenienceFeeFixed(StringUtils.EMPTY);
    merchantPresentMode.setValueOfConvenienceFeePercentage(StringUtils.EMPTY);
    merchantPresentMode.addMerchantAccountInformation(new MerchantAccountInformationTemplate());
    merchantPresentMode.addRFUforEMVCo(new TagLengthString());
    merchantPresentMode.addUnreserved(new UnreservedTemplate());

    assertThat(merchantPresentMode.toString(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToBase64() {
    final String encoded = "00020101021102160004hoge0104abcd520441115303156540523.725502015603500570155802CN5914BEST TRANSPORT6007BEIJING6107123456762970105123450205678900305098760405543210505abcde0605fghij0705klmno0805pqres0905tuvxy1004abcd5004ijkl64280002ZH0102北京0204最佳运输0304abcd65020080320016A0112233449988770708123456786304C395";

    final MerchantPresentedMode merchantPresentMode = Decoder.decode(encoded, MerchantPresentedMode.class);

    assertThat(merchantPresentMode.toBase64(), equalTo(
        "MDAwMjAxMDEwMjExMDIxNjAwMDRob2dlMDEwNGFiY2Q1MjA0NDExMTUzMDMxNTY1NDA1MjMuNzI1NTAyMDE1NjAzNTAwNTcwMTU1ODAyQ041OTE0QkVTVCBUUkFOU1BPUlQ2MDA3QkVJSklORzYxMDcxMjM0NTY3NjI5NzAxMDUxMjM0NTAyMDU2Nzg5MDAzMDUwOTg3NjA0MDU1NDMyMTA1MDVhYmNkZTA2MDVmZ2hpajA3MDVrbG1ubzA4MDVwcXJlczA5MDV0dXZ4eTEwMDRhYmNkNTAwNGlqa2w2NDI4MDAwMlpIMDEwMuWMl+S6rDAyMDTmnIDkvbPov5DovpMwMzA0YWJjZDY1MDIwMDgwMzIwMDE2QTAxMTIyMzM0NDk5ODg3NzA3MDgxMjM0NTY3ODYzMDRDMzk1"));
    assertThat(new String(Base64.decodeBase64(merchantPresentMode.toBase64()), StandardCharsets.UTF_8), equalTo(merchantPresentMode.toString()));
  }

  @Test
  public void testSuccessToBase64WhenValueIsNull() {

    final MerchantPresentedMode merchantPresentMode = new MerchantPresentedMode();
    merchantPresentMode.setAdditionalDataField(null);
    merchantPresentMode.setCountryCode(null);
    merchantPresentMode.setCRC(null);
    merchantPresentMode.setMerchantCategoryCode(null);
    merchantPresentMode.setMerchantCity(null);
    merchantPresentMode.setMerchantInformationLanguage(null);
    merchantPresentMode.setMerchantName(null);
    merchantPresentMode.setPayloadFormatIndicator(null);
    merchantPresentMode.setPointOfInitiationMethod(null);
    merchantPresentMode.setPostalCode(null);
    merchantPresentMode.setTipOrConvenienceIndicator(null);
    merchantPresentMode.setTransactionAmount(null);
    merchantPresentMode.setTransactionCurrency(null);
    merchantPresentMode.setValueOfConvenienceFeeFixed(null);
    merchantPresentMode.setValueOfConvenienceFeePercentage(null);

    assertThat(merchantPresentMode.toBase64(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToBase64WhenValueIsEmpty() {
    final MerchantPresentedMode merchantPresentMode = new MerchantPresentedMode();

    merchantPresentMode.setAdditionalDataField(new AdditionalDataFieldTemplate());
    merchantPresentMode.setCountryCode(StringUtils.EMPTY);
    merchantPresentMode.setCRC(StringUtils.EMPTY);
    merchantPresentMode.setMerchantCategoryCode(StringUtils.EMPTY);
    merchantPresentMode.setMerchantCity(StringUtils.EMPTY);
    merchantPresentMode.setMerchantInformationLanguage(new MerchantInformationLanguageTemplate());
    merchantPresentMode.setMerchantName(StringUtils.EMPTY);
    merchantPresentMode.setPayloadFormatIndicator(StringUtils.EMPTY);
    merchantPresentMode.setPointOfInitiationMethod(StringUtils.EMPTY);
    merchantPresentMode.setPostalCode(StringUtils.EMPTY);
    merchantPresentMode.setTipOrConvenienceIndicator(StringUtils.EMPTY);
    merchantPresentMode.setTransactionAmount(StringUtils.EMPTY);
    merchantPresentMode.setTransactionCurrency(StringUtils.EMPTY);
    merchantPresentMode.setValueOfConvenienceFeeFixed(StringUtils.EMPTY);
    merchantPresentMode.setValueOfConvenienceFeePercentage(StringUtils.EMPTY);
    merchantPresentMode.addMerchantAccountInformation(new MerchantAccountInformationTemplate());
    merchantPresentMode.addRFUforEMVCo(new TagLengthString());
    merchantPresentMode.addUnreserved(new UnreservedTemplate());

    assertThat(merchantPresentMode.toBase64(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToHex() throws DecoderException {
    final String encoded = "00020101021102160004hoge0104abcd520441115303156540523.725502015603500570155802CN5914BEST TRANSPORT6007BEIJING6107123456762970105123450205678900305098760405543210505abcde0605fghij0705klmno0805pqres0905tuvxy1004abcd5004ijkl64280002ZH0102北京0204最佳运输0304abcd65020080320016A0112233449988770708123456786304C395";

    final MerchantPresentedMode merchantPresentMode = Decoder.decode(encoded, MerchantPresentedMode.class);

    assertThat(merchantPresentMode.toHex(), equalTo(
        "3030303230313031303231313032313630303034686F676530313034616263643532303434313131353330333135363534303532332E373235353032303135363033353030353730313535383032434E3539313442455354205452414E53504F5254363030374245494A494E4736313037313233343536373632393730313035313233343530323035363738393030333035303938373630343035353433323130353035616263646530363035666768696A303730356B6C6D6E6F303830357071726573303930357475767879313030346162636435303034696A6B6C36343238303030325A4830313032E58C97E4BAAC30323034E69C80E4BDB3E8BF90E8BE9330333034616263643635303230303830333230303136413031313232333334343939383837373037303831323334353637383633303443333935"));
    assertThat(new String(Hex.decodeHex(merchantPresentMode.toHex()), StandardCharsets.UTF_8), equalTo(merchantPresentMode.toString()));
  }

  @Test
  public void testSuccessToHexWhenValueIsNull() {

    final MerchantPresentedMode merchantPresentMode = new MerchantPresentedMode();
    merchantPresentMode.setAdditionalDataField(null);
    merchantPresentMode.setCountryCode(null);
    merchantPresentMode.setCRC(null);
    merchantPresentMode.setMerchantCategoryCode(null);
    merchantPresentMode.setMerchantCity(null);
    merchantPresentMode.setMerchantInformationLanguage(null);
    merchantPresentMode.setMerchantName(null);
    merchantPresentMode.setPayloadFormatIndicator(null);
    merchantPresentMode.setPointOfInitiationMethod(null);
    merchantPresentMode.setPostalCode(null);
    merchantPresentMode.setTipOrConvenienceIndicator(null);
    merchantPresentMode.setTransactionAmount(null);
    merchantPresentMode.setTransactionCurrency(null);
    merchantPresentMode.setValueOfConvenienceFeeFixed(null);
    merchantPresentMode.setValueOfConvenienceFeePercentage(null);

    assertThat(merchantPresentMode.toHex(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToHexWhenValueIsEmpty() {
    final MerchantPresentedMode merchantPresentMode = new MerchantPresentedMode();

    merchantPresentMode.setAdditionalDataField(new AdditionalDataFieldTemplate());
    merchantPresentMode.setCountryCode(StringUtils.EMPTY);
    merchantPresentMode.setCRC(StringUtils.EMPTY);
    merchantPresentMode.setMerchantCategoryCode(StringUtils.EMPTY);
    merchantPresentMode.setMerchantCity(StringUtils.EMPTY);
    merchantPresentMode.setMerchantInformationLanguage(new MerchantInformationLanguageTemplate());
    merchantPresentMode.setMerchantName(StringUtils.EMPTY);
    merchantPresentMode.setPayloadFormatIndicator(StringUtils.EMPTY);
    merchantPresentMode.setPointOfInitiationMethod(StringUtils.EMPTY);
    merchantPresentMode.setPostalCode(StringUtils.EMPTY);
    merchantPresentMode.setTipOrConvenienceIndicator(StringUtils.EMPTY);
    merchantPresentMode.setTransactionAmount(StringUtils.EMPTY);
    merchantPresentMode.setTransactionCurrency(StringUtils.EMPTY);
    merchantPresentMode.setValueOfConvenienceFeeFixed(StringUtils.EMPTY);
    merchantPresentMode.setValueOfConvenienceFeePercentage(StringUtils.EMPTY);
    merchantPresentMode.addMerchantAccountInformation(new MerchantAccountInformationTemplate());
    merchantPresentMode.addRFUforEMVCo(new TagLengthString());
    merchantPresentMode.addUnreserved(new UnreservedTemplate());

    assertThat(merchantPresentMode.toHex(), equalTo(StringUtils.EMPTY));
  }

}
