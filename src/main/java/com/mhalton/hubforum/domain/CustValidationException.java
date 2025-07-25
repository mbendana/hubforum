package com.mhalton.hubforum.domain;

public class CustValidationException extends RuntimeException
{
    public CustValidationException(String message)
    {
        super(message);
    }
}
