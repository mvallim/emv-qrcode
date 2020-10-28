package com.emv.qrcode.core;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class CRCTest {

  @Test
  public void testSuccessCrc16Sample1() {
    final String value = "00020101021229300012D156000000000510A93FO3230Q31280012D15600000001030812345678520441115802CN5914BEST TRANSPORT6007BEIJING64200002ZH0104最佳运输0202北京540523.7253031565502016233030412340603***0708A60086670902ME91320016A0112233449988770708123456786304";

    final int crc16 = CRC.crc16(value.getBytes());

    assertThat(String.format("%04X", crc16), equalTo("A13A"));
  }

}
