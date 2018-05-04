package com.tzxscu.cdfy.Webservices;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class websrvs {

    private static final String WEB_SERVER_URL="http://192.168.101.4/WebService.asmx";
    private static String NameSpace="http://tempuri.org/";

    public String user_login(String emp_no,String pwd){
        String ret="";
        String methodname="userlogin";
        String soapAction = "http://tempuri.org/userlogin";
        SoapObject request = new SoapObject(NameSpace,methodname);
        request.addProperty("username",emp_no);
        request.addProperty("pwd",pwd);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
        envelope.bodyOut = request;
        envelope.dotNet=true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE transportSE = new HttpTransportSE(WEB_SERVER_URL,30000);
        try {
            transportSE.call(soapAction,envelope);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        SoapObject object = (SoapObject)envelope.bodyIn;
        ret = object.getProperty(0).toString();
        return ret;
    }
}
