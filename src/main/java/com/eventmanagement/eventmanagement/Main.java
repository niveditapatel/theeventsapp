package com.eventmanagement.eventmanagement;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {



     //  System.out.println(passwordEncoder().encode("niv"));


        String groupusers = "abc@gmail.com,nivedita@icloud,abc@abd.com";
        ArrayList<String> list = new ArrayList<String>();
            String CSV = groupusers;
                StringTokenizer tokenizer = new StringTokenizer(CSV, ",");
                while (tokenizer.hasMoreTokens()) {
                    list.add(tokenizer.nextToken());
                }
        list.forEach(System.out::println);




    }

}

/*

Users

mufaddal.naya@gmail.com  -> hello    -> Admin
mmm@gmail.com            -> mmn      -> Creator
mmn_new@gmail.com        -> mmn_new  ->User

 */