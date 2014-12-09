package com.micmiu.corba.openorb.hello;

/**
 * Interface definition: HelloService.
 * 
 * @author OpenORB Compiler
 */
public class _HelloServiceStub extends org.omg.CORBA.portable.ObjectImpl
        implements HelloService
{
    static final String[] _ids_list =
    {
        "IDL:com/micmiu/corba/openorb/hello/HelloService:1.0"
    };

    public String[] _ids()
    {
     return _ids_list;
    }

    private final static Class _opsClass = com.micmiu.corba.openorb.hello.HelloServiceOperations.class;

    /**
     * Operation sayHello
     */
    public String sayHello(String msg)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("sayHello",true);
                    _output.write_string(msg);
                    _input = this._invoke(_output);
                    String _arg_ret = _input.read_string();
                    return _arg_ret;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("sayHello",_opsClass);
                if (_so == null)
                   continue;
                com.micmiu.corba.openorb.hello.HelloServiceOperations _self = (com.micmiu.corba.openorb.hello.HelloServiceOperations) _so.servant;
                try
                {
                    return _self.sayHello( msg);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

}
