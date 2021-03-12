package com.meka.findajob.RestApi;

public class BaseManager {

    protected RestApi getRestApi(){
        RestApiClient restApiClient=new RestApiClient(BaseUrl.Url);
        return restApiClient.getRestApi();
    }
}
