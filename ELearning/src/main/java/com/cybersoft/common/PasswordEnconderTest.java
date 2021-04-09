package com.cybersoft.common;

import org.springframework.security.crypto.password.PasswordEncoder;


//Class kế thừa passwordEncoder , tạo ra một phương thức không mã hóa password 
public class PasswordEnconderTest implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return charSequence.toString().equals(s);
    }
}