package com.micmiu.corba.openorb.hello;

/** 
 * Helper class for : HelloService
 *  
 * @author OpenORB Compiler
 */ 
public class HelloServiceHelper
{
    /**
     * Insert HelloService into an any
     * @param a an any
     * @param t HelloService value
     */
    public static void insert(org.omg.CORBA.Any a, com.micmiu.corba.openorb.hello.HelloService t)
    {
        a.insert_Object(t , type());
    }

    /**
     * Extract HelloService from an any
     *
     * @param a an any
     * @return the extracted HelloService value
     */
    public static com.micmiu.corba.openorb.hello.HelloService extract( org.omg.CORBA.Any a )
    {
        if ( !a.type().equivalent( type() ) )
        {
            throw new org.omg.CORBA.MARSHAL();
        }
        try
        {
            return com.micmiu.corba.openorb.hello.HelloServiceHelper.narrow( a.extract_Object() );
        }
        catch ( final org.omg.CORBA.BAD_PARAM e )
        {
            throw new org.omg.CORBA.MARSHAL(e.getMessage());
        }
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;

    /**
     * Return the HelloService TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
            _tc = orb.create_interface_tc( id(), "HelloService" );
        }
        return _tc;
    }

    /**
     * Return the HelloService IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:com/micmiu/corba/openorb/hello/HelloService:1.0";

    /**
     * Read HelloService from a marshalled stream
     * @param istream the input stream
     * @return the readed HelloService value
     */
    public static com.micmiu.corba.openorb.hello.HelloService read(org.omg.CORBA.portable.InputStream istream)
    {
        return(com.micmiu.corba.openorb.hello.HelloService)istream.read_Object(com.micmiu.corba.openorb.hello._HelloServiceStub.class);
    }

    /**
     * Write HelloService into a marshalled stream
     * @param ostream the output stream
     * @param value HelloService value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, com.micmiu.corba.openorb.hello.HelloService value)
    {
        ostream.write_Object((org.omg.CORBA.portable.ObjectImpl)value);
    }

    /**
     * Narrow CORBA::Object to HelloService
     * @param obj the CORBA Object
     * @return HelloService Object
     */
    public static HelloService narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof HelloService)
            return (HelloService)obj;

        if (obj._is_a(id()))
        {
            _HelloServiceStub stub = new _HelloServiceStub();
            stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
            return stub;
        }

        throw new org.omg.CORBA.BAD_PARAM();
    }

    /**
     * Unchecked Narrow CORBA::Object to HelloService
     * @param obj the CORBA Object
     * @return HelloService Object
     */
    public static HelloService unchecked_narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof HelloService)
            return (HelloService)obj;

        _HelloServiceStub stub = new _HelloServiceStub();
        stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
        return stub;

    }

}
