package com.eventmanagement.EventManagement.model.interfaces;

public interface ISecurity
{
    public String getHashedPassword (String originalPassword);
}
