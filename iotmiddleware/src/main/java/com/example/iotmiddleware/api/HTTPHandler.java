package com.example.iotmiddleware.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class HTTPHandler {
    private String HTTPServer;
    private final CloseableHttpClient httpClient;

    public HTTPHandler(String HTTPServerURL){
        this.HTTPServer=HTTPServerURL;
        this.httpClient = HttpClients.createDefault();

    }

    public String httpGet(HashMap<String,String> urlParameterList,HashMap<String,String> headers){
        String encodedUrlParameterList="";
        for(String parameter: urlParameterList.keySet()){
            encodedUrlParameterList=encodedUrlParameterList+parameter+"="+urlParameterList.get(parameter)+"&";
        }

        HttpGet request = new HttpGet(this.HTTPServer+"?"+encodedUrlParameterList);
        String result ="NULL";
        for(String parameter: headers.keySet()){
            request.addHeader(parameter,headers.get(parameter));
        }
        try  {
            CloseableHttpResponse response = httpClient.execute(request);
            result=response.getStatusLine().toString();
        }
        catch(Exception e){
            // TODO log errors
        }
        return result;

    }

    public String httpPost(HashMap<String,String> urlParameterList,HashMap<String,String> headers){
        List<NameValuePair> urlParameters = new ArrayList<>();
        HttpPost post = new HttpPost(this.HTTPServer);
        String result ="NULL";
        
        for(String parameter: headers.keySet()){
            post.addHeader(parameter,headers.get(parameter));
        }
        for(String parameter: urlParameterList.keySet()){
            urlParameters.add(new BasicNameValuePair(parameter, urlParameterList.get(parameter)));
        }
        try {
            post.setEntity(new UrlEncodedFormEntity(urlParameters));
            CloseableHttpResponse response = httpClient.execute(post);
            result= response.getStatusLine().toString();

        } catch (Exception e) {
            //TODO: handle exception
        }
        return result;
    }

}