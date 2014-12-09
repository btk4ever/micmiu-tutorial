package com.micmiu.corba.openorb.hello;

/**
 * Holder class for : HelloService
 * 
 * @author OpenORB Compiler
 */
final public class HelloServiceHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal HelloService value
     */
    public com.micmiu.corba.openorb.hello.HelloService value;

    /**
     * Default constructor
     */
    public HelloServiceHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public HelloServiceHolder(com.micmiu.corba.openorb.hello.HelloService initial)
    {
        value = initial;
    }

    /**
     * Read HelloService from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = HelloServiceHelper.read(istream);
    }

    /**
     * Write HelloService into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        HelloServiceHelper.write(ostream,value);
    }

    /**
     * Return the HelloService TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return HelloServiceHelper.type();
    }

}
