package com.micmiu.corba.openorb.hello;

/**
 * Interface definition: HelloService.
 * 
 * @author OpenORB Compiler
 */
public abstract class HelloServicePOA extends org.omg.PortableServer.Servant
        implements HelloServiceOperations, org.omg.CORBA.portable.InvokeHandler
{
    public HelloService _this()
    {
        return HelloServiceHelper.narrow(_this_object());
    }

    public HelloService _this(org.omg.CORBA.ORB orb)
    {
        return HelloServiceHelper.narrow(_this_object(orb));
    }

    private static String [] _ids_list =
    {
        "IDL:com/micmiu/corba/openorb/hello/HelloService:1.0"
    };

    public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte [] objectId)
    {
        return _ids_list;
    }

    public final org.omg.CORBA.portable.OutputStream _invoke(final String opName,
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler)
    {

        if (opName.equals("sayHello")) {
                return _invoke_sayHello(_is, handler);
        } else {
            throw new org.omg.CORBA.BAD_OPERATION(opName);
        }
    }

    // helper methods
    private org.omg.CORBA.portable.OutputStream _invoke_sayHello(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();

        String _arg_result = sayHello(arg0_in);

        _output = handler.createReply();
        _output.write_string(_arg_result);

        return _output;
    }

}
