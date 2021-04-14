# EMV QRCode

![Java CI with Maven](https://github.com/mvallim/emv-qrcode/workflows/Java%20CI%20with%20Maven/badge.svg)
[![CodeQL](https://github.com/mvallim/emv-qrcode/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/mvallim/emv-qrcode/actions/workflows/codeql-analysis.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=emv-qrcode&metric=alert_status)](https://sonarcloud.io/dashboard?id=emv-qrcode)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=emv-qrcode&metric=coverage)](https://sonarcloud.io/dashboard?id=emv-qrcode)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.mvallim/emv-qrcode/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.mvallim/emv-qrcode)
[![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)](http://www.apache.org/licenses/LICENSE-2.0)

Java Based EMV QR Code Generator and Parser (MPM, CPM)

## Features

* Compatible JDK 8, 11 and 15

* Ready of specification for **Merchant Presented Mode v1.1**
  * Encode MPM
  * Decode MPM
  * Check CRC16
  * Checking duplicate tags
  * Checking invalid tags
  * Validation
  * Supports BRCode aswell
  * Thread safe
  * Production ready (uses in several projects)
  
* Ready of specification for **Consumer Presented Mode v1.1**
  * Encode CPM
  * Decode CPM
  * Validation
  * Checking duplicate tags
  * Thread safe

## Specification

* [EMV QR Code Specification for Payment Systems: Merchant Presented Mode v1.1](docs/EMVCo-Merchant-Presented-QR-Specification-v1-1.pdf)
* [EMV QR Code Specification for Payment Systems: Consumer Presented Mode v1.1](docs/EMVCo-Consumer-Presented-QR-Specification-v1-1.pdf)
* [EMV Book 3 Application Specification](docs/EMV_v4.3_Book_3_Application_Specification_20120607062110791.pdf)
* [EMV Book 4 Other Interfaces](docs/EMV_v4.3_Book_4_Other_Interfaces_20120607062305603.pdf)

## 1. Quick Start

This chapter will show you how to get started with EMV QR Code.

### 1.1 Prerequisite

In order to use EMV QR Code within a Maven project, simply add the following dependency to your pom.xml. There are no other dependencies for EMV QR Code, which means other unwanted libraries will not overwhelm your project.

You can pull it from the central Maven repositories:

#### Maven

```xml
<dependency>
    <groupId>com.github.mvallim</groupId>
    <artifactId>emv-qrcode</artifactId>
    <version>0.1.1</version>
</dependency>
```

If you want to try a snapshot version, add the following repository:

```xml
<repository>
    <id>sonatype-snapshots</id>
    <name>Sonatype Snapshots</name>
    <url>https://oss.sonatype.org/content/repositories/snapshots</url>
    <snapshots>
        <enabled>true</enabled>
    </snapshots>
</repository>
```

#### Gradle

```groovy
implementation 'com.github.mvallim:emv-qrcode:0.1.1'
```

If you want to try a snapshot version, add the following repository:

```groovy
repositories {
    maven {
        url "https://oss.sonatype.org/content/repositories/snapshots"
    }
}
```

### MPM (Merchant Presented Mode) Encode

```java
final AdditionalDataFieldTemplate additionalDataField = getAddtionalDataField();
final MerchantAccountInformationTemplate merchanAccountInformationReservedAdditional = getMerchanAccountInformationReservedAdditional();
final MerchantAccountInformationTemplate merchanAccountInformationReserved = getMerchanAccountInformationReserved();
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
merchantPresentMode.addMerchantAccountInformation(merchanAccountInformationReserved);
merchantPresentMode.addMerchantAccountInformation(merchanAccountInformationReservedAdditional);
merchantPresentMode.addRFUforEMVCo(rFUforEMVCo);
merchantPresentMode.addUnreserved(unreserved);

System.out.println(merchantPresentMode.toString());
  //0002010102110204000426160004hoge0104abcd520441115303156540523.7255020156035005
  //70155802CN5914BEST TRANSPORT6007BEIJING610712345676295010512345020567890030509
  //8760405543210505abcde0605fghij0705klmno0805pqres0905tuvxy5010000110101i6428000
  //2ZH0102北京0204最佳运输0304abcd65020080320016A011223344998877070812345678630432B
  //3

// Primitive Payment System Merchant Account Information (IDs "02" to "25")
private MerchantAccountInformationTemplate getMerchanAccountInformationReserved() {
  final MerchantAccountInformationReserved merchantAccountInformationValue = new MerchantAccountInformationReserved("0004");

  return new MerchantAccountInformationTemplate("02", merchantAccountInformationValue);
}

// Merchant Account Information Template (IDs "26" to "51")
private MerchantAccountInformationTemplate getMerchanAccountInformationReservedAdditional() {
  final TagLengthString paymentNetworkSpecific = new TagLengthString();
  paymentNetworkSpecific.setTag("01");
  paymentNetworkSpecific.setValue("abcd");

  final MerchantAccountInformationReservedAdditional merchantAccountInformationValue = new MerchantAccountInformationReservedAdditional();
  merchantAccountInformationValue.setGloballyUniqueIdentifier("hoge");
  merchantAccountInformationValue.addPaymentNetworkSpecific(paymentNetworkSpecific);

  return new MerchantAccountInformationTemplate("26", merchantAccountInformationValue);
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
  final PaymentSystemSpecific paymentSystemSpecific = new PaymentSystemSpecific();
  paymentSystemSpecific.setGloballyUniqueIdentifier("1");
  paymentSystemSpecific.addPaymentSystemSpecific(new TagLengthString("01", "i"));

  final PaymentSystemSpecificTemplate paymentSystemSpecificTemplate = new PaymentSystemSpecificTemplate();
  paymentSystemSpecificTemplate.setTag("50");
  paymentSystemSpecificTemplate.setValue(paymentSystemSpecific);

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
  additionalDataFieldValue.addPaymentSystemSpecific(paymentSystemSpecificTemplate);

  final AdditionalDataFieldTemplate additionalDataField = new AdditionalDataFieldTemplate();
  additionalDataField.setValue(additionalDataFieldValue);

  return additionalDataField;
}
```

### MPM (Merchant Presented Mode) Decode

```java
@Test
public void testSuccessDecode() throws MerchantPresentedModeException {

  final String encoded = "00020101021102160004hoge0104abcd520441115303156540523"
      + ".7255020256035005802CN5914BEST TRANSPORT6007BEIJING6107123456762800205"
      + "678900305098760505abcde0705klmno0805pqres0903tuv1004abcd50160004123401"
      + "04ijkl64280002ZH0102北京0204最佳运输0304abcd65020080320016A0112233449988"
      + "7707081234567863046325";

  final MerchantPresentedMode merchantPresentedMode = DecoderMpm.decode(encoded, MerchantPresentedMode.class);

  assertThat(merchantPresentedMode.getCountryCode().getValue(), equalTo("CN"));
  assertThat(merchantPresentedMode.getMerchantCategoryCode().getValue(), equalTo("4111"));
  assertThat(merchantPresentedMode.getMerchantCity().getValue(), equalTo("BEIJING"));
  assertThat(merchantPresentedMode.getMerchantName().getValue(), equalTo("BEST TRANSPORT"));
  assertThat(merchantPresentedMode.getPayloadFormatIndicator().getValue(), equalTo("01"));
  assertThat(merchantPresentedMode.getPointOfInitiationMethod().getValue(), equalTo("11"));
  assertThat(merchantPresentedMode.getPostalCode().getValue(), equalTo("1234567"));
  assertThat(merchantPresentedMode.getTipOrConvenienceIndicator().getValue(), equalTo("02"));
  assertThat(merchantPresentedMode.getTransactionAmount().getValue(), equalTo("23.72"));
  assertThat(merchantPresentedMode.getTransactionCurrency().getValue(), equalTo("156"));
  assertThat(merchantPresentedMode.getValueOfConvenienceFeeFixed().getValue(), equalTo("500"));
}

@Test
public void testeFailDuplicateTag() throws MerchantPresentedModeException {

  final String encoded = "00020101021102160004hoge0104abcd5204411153031565303156"
      + "540523.725502015603500570155802CN5914BEST TRANSPORT6007BEIJING610712345"
      + "6762950105123450205678900305098760405543210505abcde0605fghij0705klmno08"
      + "05pqres0905tuvxy5010000110101i64280002ZH0102北京0204最佳运输0304abcd65020"
      + "080320016A011223344998877070812345678";

  final MerchantPresentedModeException merchantPresentedModeException = catchThrowableOfType(() -> 
      DecoderMpm.decode(encoded, MerchantPresentedMode.class), MerchantPresentedModeException.class);

  assertThat(merchantPresentedModeException, instanceOf(DuplicateTagException.class));

  final DuplicateTagException duplicateTagException = DuplicateTagException.class.cast(merchantPresentedModeException);

  assertThat(duplicateTagException.getTag(), equalTo("53"));
  assertThat(duplicateTagException.getValue(), equalTo("5303156"));
}
```

### MPM (Merchant Presented Mode) Validate

```java
@Test
public void testSuccessValidate() {

  final MerchantPresentedMode merchantPresentMode = new MerchantPresentedMode();

  merchantPresentMode.setCountryCode("CN");
  merchantPresentMode.setMerchantCategoryCode("4111");
  merchantPresentMode.setMerchantCity("BEIJING");
  merchantPresentMode.setMerchantName("BEST TRANSPORT");
  merchantPresentMode.setPayloadFormatIndicator("01");
  merchantPresentMode.setPointOfInitiationMethod("11");
  merchantPresentMode.setPostalCode("1234567");
  merchantPresentMode.setTipOrConvenienceIndicator("02");
  merchantPresentMode.setTransactionAmount("23.72");
  merchantPresentMode.setTransactionCurrency("156");
  merchantPresentMode.setValueOfConvenienceFeeFixed("500");

  final ValidationResult validationResult = MerchantPresentedModeValidate.validate(merchantPresentMode);

  assertTrue(validationResult.isValid());
}
```

### MPM (Merchant Presented Mode) Validate CRC16

```java
@Test
public void testSuccessCrc16Sample1() {

  final String encoded = "00020101021229300012D156000000000510A93FO3230Q31280012"
      + "D15600000001030812345678520441115802CN5914BEST TRANSPORT6007BEIJING6420"
      + "0002ZH0104最佳运输0202北京540523.7253031565502016233030412340603***0708A6"
      + "0086670902ME91320016A0112233449988770708123456786304A13A";

  final ValidationResult validationResult = Crc16Validate.validate(encoded);

  assertThat(validationResult.isValid(), equalTo(true));
}

@Test
public void testFailValidateWhenWithoutCRCDecoded() {

  final String encoded = "00020101021102160004hoge0104abcd520441115303156540523"
      + ".7255020256035005802CN5914BEST TRANSPORT6007BEIJING6107123456762800205"
      + "678900305098760505abcde0705klmno0805pqres0903tuv1004abcd50160004123401"
      + "04ijkl64280002ZH0102北京0204最佳运输0304abcd65020080320016A0112233449988"
      + "77070812345678";

  final ValidationResult validationResult = Crc16Validate.validate(encoded);

  assertThat(validationResult.isValid(), equalTo(false));
  assertThat(validationResult.getErrors(), hasSize(1));
  assertThat(validationResult.getErrors(), hasItem(hasProperty("message", equalTo("Invalid CRC16"))));
  assertThat(validationResult.getErrors(), hasItem(hasProperty("attemptedValue", equalTo("5678"))));
}
```

### CPM (Consumer Presented Mode) Encode

```java
final ConsumerPresentedMode consumerPresentedMode = new ConsumerPresentedMode();

final ApplicationTemplate applicationTemplate1 = new ApplicationTemplate();
applicationTemplate1.setApplicationDefinitionFileName("A0000000555555");
applicationTemplate1.setApplicationLabel("Product1");

final ApplicationTemplate applicationTemplate2 = new ApplicationTemplate();
applicationTemplate2.setApplicationDefinitionFileName("A0000000666666");
applicationTemplate2.setApplicationLabel("Product2");

final CommonDataTransparentTemplate commonDataTransparentTemplate = new CommonDataTransparentTemplate();
commonDataTransparentTemplate.setIssuerApplicationData("06010A03000000");
commonDataTransparentTemplate.setApplicationCryptogram("584FD385FA234BCC");
commonDataTransparentTemplate.setApplicationTransactionCounter("0001");
commonDataTransparentTemplate.setUnpredictableNumber("6D58EF13");

final CommonDataTemplate commonDataTemplate = new CommonDataTemplate();
commonDataTemplate.setApplicationPAN("1234567890123458");
commonDataTemplate.setCardholderName("CARDHOLDER/EMV");
commonDataTemplate.setLanguagePreference("ruesdeen");
commonDataTemplate.setCommonDataTransparentTemplate(commonDataTransparentTemplate);

consumerPresentedMode.setPayloadFormatIndicator(new PayloadFormatIndicator());
consumerPresentedMode.addApplicationTemplate(applicationTemplate1);
consumerPresentedMode.addApplicationTemplate(applicationTemplate2);
consumerPresentedMode.setCommonDataTemplate(commonDataTemplate);

assertThat(consumerPresentedMode.toHex(), equalTo("8505435056303161134F07A000000"
  + "0555555500850726F647563743161134F07A0000000666666500850726F647563743262495A"
  + "0812345678901234585F200E43415244484F4C4445522F454D565F2D08727565736465656E6"
  + "4219F100706010A030000009F2608584FD385FA234BCC9F360200019F37046D58EF13"));

assertThat(consumerPresentedMode.toBase64(), equalTo("hQVDUFYwMWETTwegAAAAVVVVUA"
  + "hQcm9kdWN0MWETTwegAAAAZmZmUAhQcm9kdWN0MmJJWggSNFZ4kBI0WF8gDkNBUkRIT0xERVIvR"
  + "U1WXy0IcnVlc2RlZW5kIZ8QBwYBCgMAAACfJghYT9OF+iNLzJ82AgABnzcEbVjvEw=="));
```

### CPM (Consumer Presented Mode) Decode

```java
@Test
public void testSuccessDecode() throws Exception {
  final String encoded = "hQVDUFYwMWETTwegAAAAVVVVUAhQcm9kdWN0MWETTwegAAAAZmZmUA"
    + "hQcm9kdWN0MmJJWggSNFZ4kBI0WF8gDkNBUkRIT0xERVIvRU1WXy0IcnVlc2RlZW5kIZ8QBwY"
    + "BCgMAAACfJghYT9OF+iNLzJ82AgABnzcEbVjvEw==";

  final ConsumerPresentedMode consumerPresentedMode = DecoderCpm.decode(encoded, ConsumerPresentedMode.class);

  final PayloadFormatIndicator payloadFormatIndicator = consumerPresentedMode.getPayloadFormatIndicator();
  assertThat(payloadFormatIndicator.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_PAYLOAD_FORMAT_INDICATOR));
  assertThat(payloadFormatIndicator.getStringValue(), equalTo("CPV01"));

  final ApplicationTemplate applicationTemplate1 = consumerPresentedMode.getApplicationTemplates().get(0);
  assertThat(applicationTemplate1.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_APPLICATION_TEMPLATE));
  assertThat(applicationTemplate1.getApplicationDefinitionFileName().getTag(), equalTo(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME));
  assertThat(applicationTemplate1.getApplicationDefinitionFileName().getStringValue(), equalTo("A0000000555555"));
  assertThat(applicationTemplate1.getApplicationLabel().getTag(), equalTo(TagTransactionProcessingCodes.ID_APPLICATION_LABEL));
  assertThat(applicationTemplate1.getApplicationLabel().getStringValue(), equalTo("Product1"));

  final ApplicationTemplate applicationTemplate2 = consumerPresentedMode.getApplicationTemplates().get(1);
  assertThat(applicationTemplate2.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_APPLICATION_TEMPLATE));
  assertThat(applicationTemplate2.getApplicationDefinitionFileName().getTag(), equalTo(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME));
  assertThat(applicationTemplate2.getApplicationDefinitionFileName().getStringValue(), equalTo("A0000000666666"));
  assertThat(applicationTemplate2.getApplicationLabel().getTag(), equalTo(TagTransactionProcessingCodes.ID_APPLICATION_LABEL));
  assertThat(applicationTemplate2.getApplicationLabel().getStringValue(), equalTo("Product2"));

  final CommonDataTemplate commonDataTemplate = consumerPresentedMode.getCommonDataTemplate();
  assertThat(commonDataTemplate.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TEMPLATE));
  assertThat(commonDataTemplate.getApplicationPAN().getTag(), equalTo(TagTransactionProcessingCodes.ID_APPLICATION_PAN));
  assertThat(commonDataTemplate.getApplicationPAN().getStringValue(), equalTo("1234567890123458"));
  assertThat(commonDataTemplate.getCardholderName().getTag(), equalTo(TagTransactionProcessingCodes.ID_CARDHOLDER_NAME));
  assertThat(commonDataTemplate.getCardholderName().getStringValue(), equalTo("CARDHOLDER/EMV"));
  assertThat(commonDataTemplate.getLanguagePreference().getTag(), equalTo(TagTransactionProcessingCodes.ID_LANGUAGE_PREFERENCE));
  assertThat(commonDataTemplate.getLanguagePreference().getStringValue(), equalTo("ruesdeen"));
  
  final CommonDataTransparentTemplate commonDataTransparentTemplate = commonDataTemplate.getCommonDataTransparentTemplate();
  assertThat(commonDataTransparentTemplate.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TRANSPARENT_TEMPLATE));
  assertThat(commonDataTransparentTemplate.getIssuerApplicationData().getTag(), equalTo(TagTransactionProcessingCodes.ID_ISSUER_APPLICATION_DATA));
  assertThat(commonDataTransparentTemplate.getIssuerApplicationData().getStringValue(), equalTo("06010A03000000"));
  assertThat(commonDataTransparentTemplate.getApplicationCryptogram().getTag(), equalTo(TagTransactionProcessingCodes.ID_APPLICATION_CRYPTOGRAM));
  assertThat(commonDataTransparentTemplate.getApplicationCryptogram().getStringValue(), equalTo("584FD385FA234BCC"));
  assertThat(commonDataTransparentTemplate.getApplicationTransactionCounter().getTag(), equalTo(TagTransactionProcessingCodes.ID_APPLICATION_TRANSACTION_COUNTER));
  assertThat(commonDataTransparentTemplate.getApplicationTransactionCounter().getStringValue(), equalTo("0001"));
  assertThat(commonDataTransparentTemplate.getUnpredictableNumber().getTag(), equalTo(TagTransactionProcessingCodes.ID_UNPREDICTABLE_NUMBER));
  assertThat(commonDataTransparentTemplate.getUnpredictableNumber().getStringValue(), equalTo("6D58EF13"));
}
```

### CPM (Consumer Presented Mode) Validate

```java
@Test
public void testSuccessValidate() {
  final String encoded = "hQVDUFYwMWETTwegAAAAVVVVUAhQcm9kdWN0MWETTwegAAAAZmZmUAhQcm9k"
      + "dWN0MmJJWggSNFZ4kBI0WF8gDkNBUkRIT0xERVIvRU1WXy0IcnVlc2RlZW5kIZ8QBwYBCgMAAACfJ"
      + "ghYT9OF+iNLzJ82AgABnzcEbVjvEw==";

  final ConsumerPresentedMode consumerPresentedMode = DecoderCpm.decode(encoded, ConsumerPresentedMode.class);

  final ValidationResult validationResult = ConsumerPresentedModeValidate.validate(consumerPresentedMode);

  assertTrue(validationResult.isValid());
}

@Test
public void testFailValidate() throws IOException {
  final String encoded = "hQVDUFYwMWEiTwegAAAAVVVVUAhQcm9kdWN0MV8gDE1hc3RlciBDbGFzc2E"
      + "TTwegAAAAZmZmUAhQcm9kdWN0MmJJWggSNFZ4kBI0WF8gDkNBUkRIT0xERVIvRU1WXy0IcnVlc2R"
      + "lZW5kIZ8QBwYBCgMAAACfJghYT9OF+iNLzJ82AgABnzcEbVjvEw==";

  final ConsumerPresentedMode consumerPresentedMode = DecoderCpm.decode(encoded, ConsumerPresentedMode.class);

  final ValidationResult validationResult = ConsumerPresentedModeValidate.validate(consumerPresentedMode);

  assertFalse(validationResult.isValid());
  assertThat(validationResult.getErrors(), hasSize(1));
  assertThat(validationResult.getErrors(), hasItem(hasProperty("code", equalTo("5F20"))));
  assertThat(validationResult.getErrors(), hasItem(hasProperty("message", equalTo("Duplicate definition tag on CommonDataTemplate"))));
  assertThat(validationResult.getErrors(), hasItem(hasProperty("attemptedValue", equalTo("CARDHOLDER/EMV"))));
}
```

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [GitHub](https://github.com/mvallim/emv-qrcode) for versioning. For the versions available, see the [tags on this repository](https://github.com/mvallim/emv-qrcode/tags).

## Authors

* **Marcos Vallim** - *Initial work, Development, Test, Documentation* - [mvallim](https://github.com/mvallim)

See also the list of [contributors](CONTRIBUTORS.txt) who participated in this project.

## License

This project is licensed under the Apache License - see the [LICENSE](LICENSE) file for details
