package com.example.JAQpApi.Exceptions;

public class NotFoundException extends Exception
{
    public NotFoundException(String message)
    {
        super(message);
    }

    public NotFoundException(String _type, String _resource)
    {
        super(_type + " with " + _resource + " not found");
    }
}
