package com.micmiu.corba.openorb.hello;

/**
 * Interface definition: HelloService.
 * 
 * @author OpenORB Compiler
 */
public class HelloServicePOATie extends HelloServicePOA
{

    //
    // Private reference to implementation object
    //
    private HelloServiceOperations _tie;

    //
    // Private reference to POA
    //
    private org.omg.PortableServer.POA _poa;

    /**
     * Constructor
     */
    public HelloServicePOATie(HelloServiceOperations tieObject)
    {
        _tie = tieObject;
    }

    /**
     * Constructor
     */
    public HelloServicePOATie(HelloServiceOperations tieObject, org.omg.PortableServer.POA poa)
    {
        _tie = tieObject;
        _poa = poa;
    }

    /**
     * Get the delegate
     */
    public HelloServiceOperations _delegate()
    {
        return _tie;
    }

    /**
     * Set the delegate
     */
    public void _delegate(HelloServiceOperations delegate_)
    {
        _tie = delegate_;
    }

    /**
     * _default_POA method
     */
    public org.omg.PortableServer.POA _default_POA()
    {
        if (_poa != null)
            return _poa;
        else
            return super._default_POA();
    }

    /**
     * Operation sayHello
     */
    public String sayHello(String msg)
    {
        return _tie.sayHello( msg);
    }

}
