package com.example.JAQpApi.Exceptions;

public class AccessDeniedException extends Exception
{
    public AccessDeniedException(String message)
    {
        super(message);
    }

    public AccessDeniedException(String _resource, String _reason)
    {
        super("Access to " + _resource + ", with reason " + _reason);
    }
}
