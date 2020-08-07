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
    final TagLengthString countryCode = new TagLengthString("58", "CN");
    final TagLengthString merchantCategoryCode = new TagLengthString("52", "4111");
    final TagLengthString merchantCity = new TagLengthString("60", "BEIJING");
    final TagLengthString merchantName = new TagLengthString("59", "BEST TRANSPORT");
    final TagLengthString payloadFormatIndicator = new TagLengthString("00", "01");
    final TagLengthString pointOfInitiationMethod = new TagLengthString("01", "11");
    final TagLengthString postalCode = new TagLengthString("61", "1234567");
    final TagLengthString tipOrConvenienceIndicator = new TagLengthString("55", "01");
    final TagLengthString transactionAmount = new TagLengthString("54", "23.72");
    final TagLengthString transactionCurrency = new TagLengthString("53", "156");
    final TagLengthString valueOfConvenienceFeeFixed = new TagLengthString("56", "500");
    final TagLengthString valueOfConvenienceFeePercentage = new TagLengthString("57", "5");
    final TagLengthString rFUforEMVCo = new TagLengthString("65", "00");

    final MerchantPresentedMode merchantPresentMode = new MerchantPresentedMode();
    merchantPresentMode.setAdditionalDataField(additionalDataField);
    merchantPresentMode.setCountryCode(countryCode);
    merchantPresentMode.setMerchantCategoryCode(merchantCategoryCode);
    merchantPresentMode.setMerchantCity(merchantCity);
    merchantPresentMode.setMerchantInformationLanguage(merchantInformationLanguage);
    merchantPresentMode.setMerchantName(merchantName);
    merchantPresentMode.setPayloadFormatIndicator(payloadFormatIndicator);
    merchantPresentMode.setPointOfInitiationMethod(pointOfInitiationMethod);
    merchantPresentMode.setPostalCode(postalCode);
    merchantPresentMode.setTipOrConvenienceIndicator(tipOrConvenienceIndicator);
    merchantPresentMode.setTransactionAmount(transactionAmount);
    merchantPresentMode.setTransactionCurrency(transactionCurrency);
    merchantPresentMode.setValueOfConvenienceFeeFixed(valueOfConvenienceFeeFixed);
    merchantPresentMode.setValueOfConvenienceFeePercentage(valueOfConvenienceFeePercentage);
    merchantPresentMode.addMerchantAccountInformation(merchantAccountInformation);
    merchantPresentMode.addRFUforEMVCo(rFUforEMVCo);
    merchantPresentMode.addUnreserved(unreserved);

    assertThat(merchantPresentMode.toString(), equalTo(
        "00020101021102160004hoge0104abcd520441115303156540523.725502015603500570155802CN5914BEST TRANSPORT6007BEIJING6107123456762970105123450205678900305098760405543210505abcde0605fghij0705klmno0805pqres0905tuvxy1004abcd5004ijkl64280002ZH0102北京0204最佳运输0304abcd65020080320016A0112233449988770708123456786304C395"));
  }

  private MerchantAccountInformationTemplate getMerchanAccountInformation() {
    final TagLengthString globallyUniqueIdentifier = new TagLengthString();
    globallyUniqueIdentifier.setTag("00");
    globallyUniqueIdentifier.setValue("hoge");

    final TagLengthString paymentNetworkSpecific = new TagLengthString();
    paymentNetworkSpecific.setTag("01");
    paymentNetworkSpecific.setValue("abcd");

    final MerchantAccountInformation merchantAccountInformationValue = new MerchantAccountInformation();
    merchantAccountInformationValue.setGloballyUniqueIdentifier(globallyUniqueIdentifier);
    merchantAccountInformationValue.addPaymentNetworkSpecific(paymentNetworkSpecific);

    final MerchantAccountInformationTemplate merchantAccountInformation = new MerchantAccountInformationTemplate();
    merchantAccountInformation.setValue(merchantAccountInformationValue);
    merchantAccountInformation.setTag("02");
    return merchantAccountInformation;
  }

  private UnreservedTemplate getUnreserved() {
    final TagLengthString globallyUniqueIdentifier = new TagLengthString();
    globallyUniqueIdentifier.setTag("00");
    globallyUniqueIdentifier.setValue("A011223344998877");

    final TagLengthString contextSpecificData = new TagLengthString();
    contextSpecificData.setTag("07");
    contextSpecificData.setValue("12345678");

    final Unreserved value = new Unreserved();
    value.setGloballyUniqueIdentifier(globallyUniqueIdentifier);
    value.addContextSpecificData(contextSpecificData);

    final UnreservedTemplate unreserved = new UnreservedTemplate();
    unreserved.setValue(value);
    unreserved.setTag("80");
    return unreserved;
  }

  private MerchantInformationLanguageTemplate getMerchantInformationLanguage() {
    final TagLengthString languagePreference = new TagLengthString();
    languagePreference.setTag("00");
    languagePreference.setValue("ZH");

    final TagLengthString merchantName = new TagLengthString();
    merchantName.setTag("01");
    merchantName.setValue("北京");

    final TagLengthString merchantCity = new TagLengthString();
    merchantCity.setTag("02");
    merchantCity.setValue("最佳运输");

    final TagLengthString rFUforEMVCo = new TagLengthString();
    rFUforEMVCo.setTag("03");
    rFUforEMVCo.setValue("abcd");

    final MerchantInformationLanguage merchantInformationLanguageValue = new MerchantInformationLanguage();
    merchantInformationLanguageValue.setLanguagePreference(languagePreference);
    merchantInformationLanguageValue.setMerchantName(merchantName);
    merchantInformationLanguageValue.setMerchantCity(merchantCity);
    merchantInformationLanguageValue.addRFUforEMVCo(rFUforEMVCo);

    final MerchantInformationLanguageTemplate merchantInformationLanguage = new MerchantInformationLanguageTemplate();
    merchantInformationLanguage.setValue(merchantInformationLanguageValue);
    return merchantInformationLanguage;
  }

  private AdditionalDataFieldTemplate getAddtionalDataField() {
    final TagLengthString additionalConsumerDataRequest = new TagLengthString();
    additionalConsumerDataRequest.setTag("09");
    additionalConsumerDataRequest.setValue("tuvxy");

    final TagLengthString billNumber = new TagLengthString();
    billNumber.setTag("01");
    billNumber.setValue("12345");

    final TagLengthString customerLabel = new TagLengthString();
    customerLabel.setTag("06");
    customerLabel.setValue("fghij");

    final TagLengthString loyaltyNumber = new TagLengthString();
    loyaltyNumber.setTag("04");
    loyaltyNumber.setValue("54321");

    final TagLengthString mobileNumber = new TagLengthString();
    mobileNumber.setTag("02");
    mobileNumber.setValue("67890");

    final TagLengthString purposeTransaction = new TagLengthString();
    purposeTransaction.setTag("08");
    purposeTransaction.setValue("pqres");

    final TagLengthString referenceLabel = new TagLengthString();
    referenceLabel.setTag("05");
    referenceLabel.setValue("abcde");

    final TagLengthString storeLabel = new TagLengthString();
    storeLabel.setTag("03");
    storeLabel.setValue("09876");

    final TagLengthString terminalLabel = new TagLengthString();
    terminalLabel.setTag("07");
    terminalLabel.setValue("klmno");

    final TagLengthString paymentSystemSpecific = new TagLengthString();
    paymentSystemSpecific.setTag("50");
    paymentSystemSpecific.setValue("ijkl");

    final TagLengthString rFUforEMVCo = new TagLengthString();
    rFUforEMVCo.setTag("10");
    rFUforEMVCo.setValue("abcd");

    final AdditionalDataField additionalDataFieldValue = new AdditionalDataField();
    additionalDataFieldValue.setAdditionalConsumerDataRequest(additionalConsumerDataRequest);
    additionalDataFieldValue.setBillNumber(billNumber);
    additionalDataFieldValue.setCustomerLabel(customerLabel);
    additionalDataFieldValue.setLoyaltyNumber(loyaltyNumber);
    additionalDataFieldValue.setMobileNumber(mobileNumber);
    additionalDataFieldValue.setPurposeTransaction(purposeTransaction);
    additionalDataFieldValue.setReferenceLabel(referenceLabel);
    additionalDataFieldValue.setStoreLabel(storeLabel);
    additionalDataFieldValue.setTerminalLabel(terminalLabel);
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
    merchantPresentMode.setCountryCode((TagLengthString) null);
    merchantPresentMode.setCRC((TagLengthString) null);
    merchantPresentMode.setMerchantCategoryCode((TagLengthString) null);
    merchantPresentMode.setMerchantCity((TagLengthString) null);
    merchantPresentMode.setMerchantInformationLanguage(null);
    merchantPresentMode.setMerchantName((TagLengthString) null);
    merchantPresentMode.setPayloadFormatIndicator((TagLengthString) null);
    merchantPresentMode.setPointOfInitiationMethod((TagLengthString) null);
    merchantPresentMode.setPostalCode((TagLengthString) null);
    merchantPresentMode.setTipOrConvenienceIndicator((TagLengthString) null);
    merchantPresentMode.setTransactionAmount((TagLengthString) null);
    merchantPresentMode.setTransactionCurrency((TagLengthString) null);
    merchantPresentMode.setValueOfConvenienceFeeFixed((TagLengthString) null);
    merchantPresentMode.setValueOfConvenienceFeePercentage((TagLengthString) null);

    assertThat(merchantPresentMode.toString(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToStringWhenValueIsEmpty() {
    final MerchantPresentedMode merchantPresentMode = new MerchantPresentedMode();

    merchantPresentMode.setAdditionalDataField(new AdditionalDataFieldTemplate());
    merchantPresentMode.setCountryCode(new TagLengthString());
    merchantPresentMode.setCRC(new TagLengthString());
    merchantPresentMode.setMerchantCategoryCode(new TagLengthString());
    merchantPresentMode.setMerchantCity(new TagLengthString());
    merchantPresentMode.setMerchantInformationLanguage(new MerchantInformationLanguageTemplate());
    merchantPresentMode.setMerchantName(new TagLengthString());
    merchantPresentMode.setPayloadFormatIndicator(new TagLengthString());
    merchantPresentMode.setPointOfInitiationMethod(new TagLengthString());
    merchantPresentMode.setPostalCode(new TagLengthString());
    merchantPresentMode.setTipOrConvenienceIndicator(new TagLengthString());
    merchantPresentMode.setTransactionAmount(new TagLengthString());
    merchantPresentMode.setTransactionCurrency(new TagLengthString());
    merchantPresentMode.setValueOfConvenienceFeeFixed(new TagLengthString());
    merchantPresentMode.setValueOfConvenienceFeePercentage(new TagLengthString());
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
    merchantPresentMode.setCountryCode((TagLengthString) null);
    merchantPresentMode.setCRC((TagLengthString) null);
    merchantPresentMode.setMerchantCategoryCode((TagLengthString) null);
    merchantPresentMode.setMerchantCity((TagLengthString) null);
    merchantPresentMode.setMerchantInformationLanguage(null);
    merchantPresentMode.setMerchantName((TagLengthString) null);
    merchantPresentMode.setPayloadFormatIndicator((TagLengthString) null);
    merchantPresentMode.setPointOfInitiationMethod((TagLengthString) null);
    merchantPresentMode.setPostalCode((TagLengthString) null);
    merchantPresentMode.setTipOrConvenienceIndicator((TagLengthString) null);
    merchantPresentMode.setTransactionAmount((TagLengthString) null);
    merchantPresentMode.setTransactionCurrency((TagLengthString) null);
    merchantPresentMode.setValueOfConvenienceFeeFixed((TagLengthString) null);
    merchantPresentMode.setValueOfConvenienceFeePercentage((TagLengthString) null);

    assertThat(merchantPresentMode.toBase64(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToBase64WhenValueIsEmpty() {
    final MerchantPresentedMode merchantPresentMode = new MerchantPresentedMode();

    merchantPresentMode.setAdditionalDataField(new AdditionalDataFieldTemplate());
    merchantPresentMode.setCountryCode(new TagLengthString());
    merchantPresentMode.setCRC(new TagLengthString());
    merchantPresentMode.setMerchantCategoryCode(new TagLengthString());
    merchantPresentMode.setMerchantCity(new TagLengthString());
    merchantPresentMode.setMerchantInformationLanguage(new MerchantInformationLanguageTemplate());
    merchantPresentMode.setMerchantName(new TagLengthString());
    merchantPresentMode.setPayloadFormatIndicator(new TagLengthString());
    merchantPresentMode.setPointOfInitiationMethod(new TagLengthString());
    merchantPresentMode.setPostalCode(new TagLengthString());
    merchantPresentMode.setTipOrConvenienceIndicator(new TagLengthString());
    merchantPresentMode.setTransactionAmount(new TagLengthString());
    merchantPresentMode.setTransactionCurrency(new TagLengthString());
    merchantPresentMode.setValueOfConvenienceFeeFixed(new TagLengthString());
    merchantPresentMode.setValueOfConvenienceFeePercentage(new TagLengthString());
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
    merchantPresentMode.setCountryCode((TagLengthString) null);
    merchantPresentMode.setCRC((TagLengthString) null);
    merchantPresentMode.setMerchantCategoryCode((TagLengthString) null);
    merchantPresentMode.setMerchantCity((TagLengthString) null);
    merchantPresentMode.setMerchantInformationLanguage(null);
    merchantPresentMode.setMerchantName((TagLengthString) null);
    merchantPresentMode.setPayloadFormatIndicator((TagLengthString) null);
    merchantPresentMode.setPointOfInitiationMethod((TagLengthString) null);
    merchantPresentMode.setPostalCode((TagLengthString) null);
    merchantPresentMode.setTipOrConvenienceIndicator((TagLengthString) null);
    merchantPresentMode.setTransactionAmount((TagLengthString) null);
    merchantPresentMode.setTransactionCurrency((TagLengthString) null);
    merchantPresentMode.setValueOfConvenienceFeeFixed((TagLengthString) null);
    merchantPresentMode.setValueOfConvenienceFeePercentage((TagLengthString) null);

    assertThat(merchantPresentMode.toHex(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToHexWhenValueIsEmpty() {
    final MerchantPresentedMode merchantPresentMode = new MerchantPresentedMode();

    merchantPresentMode.setAdditionalDataField(new AdditionalDataFieldTemplate());
    merchantPresentMode.setCountryCode(new TagLengthString());
    merchantPresentMode.setCRC(new TagLengthString());
    merchantPresentMode.setMerchantCategoryCode(new TagLengthString());
    merchantPresentMode.setMerchantCity(new TagLengthString());
    merchantPresentMode.setMerchantInformationLanguage(new MerchantInformationLanguageTemplate());
    merchantPresentMode.setMerchantName(new TagLengthString());
    merchantPresentMode.setPayloadFormatIndicator(new TagLengthString());
    merchantPresentMode.setPointOfInitiationMethod(new TagLengthString());
    merchantPresentMode.setPostalCode(new TagLengthString());
    merchantPresentMode.setTipOrConvenienceIndicator(new TagLengthString());
    merchantPresentMode.setTransactionAmount(new TagLengthString());
    merchantPresentMode.setTransactionCurrency(new TagLengthString());
    merchantPresentMode.setValueOfConvenienceFeeFixed(new TagLengthString());
    merchantPresentMode.setValueOfConvenienceFeePercentage(new TagLengthString());
    merchantPresentMode.addMerchantAccountInformation(new MerchantAccountInformationTemplate());
    merchantPresentMode.addRFUforEMVCo(new TagLengthString());
    merchantPresentMode.addUnreserved(new UnreservedTemplate());

    assertThat(merchantPresentMode.toHex(), equalTo(StringUtils.EMPTY));
  }

}
