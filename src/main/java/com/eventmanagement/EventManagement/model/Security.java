package com.eventmanagement.EventManagement.model;

import com.eventmanagement.EventManagement.model.interfaces.ISecurity;
import java.security.MessageDigest;

public class Security implements ISecurity
{
    @Override
    public String getHashedPassword(String originalPassword)
    {
        // Refered https://www.javatpoint.com/how-to-encrypt-password-in-java
        // Looked good way to create a hash of the password and store it.
        // took the algoithm from the url
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(originalPassword.getBytes());
            byte[] bytes = md.digest();
            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return s.toString();
        }
        catch (Exception ex)
        {
            System.out.println("Exception found during hashing password.");
            return null;
        }
    }

}
