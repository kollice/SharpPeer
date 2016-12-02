package com.kollice.security.test;

import java.security.Security;
import java.util.Arrays;

/**
 * Created by 00259 on 2016/11/29.
 */
public class SecurityTest {
    public static void main(String args[]) {
        Arrays.asList(Security.getProviders()).stream().forEach((p) -> {
            System.out.println("----------------------->" + p);
            p.entrySet().stream().forEach(System.out::println);
        });
    }
}
