package com.kollice.security.test;

import java.io.IOException;
import java.math.BigInteger;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 00259 on 2016/11/29.
 */
public class AlgorithmParametersTest {
    public static void main(String[] args) {
        try {
//            AlgorithmParameters algorithmParameters = AlgorithmParameters.getInstance("des");
//            algorithmParameters.init(new BigInteger("19050619766489163472469").toByteArray());
//            byte[] result = algorithmParameters.getEncoded();
//            System.out.println(new BigInteger(result).toString());

            AlgorithmParameterGenerator algorithmParameterGenerator = AlgorithmParameterGenerator.getInstance("DES");
            algorithmParameterGenerator.init(56);
            AlgorithmParameters algorithmParameters = algorithmParameterGenerator.generateParameters();
            byte[] result = algorithmParameters.getEncoded();
            System.out.println(new BigInteger(result).toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
