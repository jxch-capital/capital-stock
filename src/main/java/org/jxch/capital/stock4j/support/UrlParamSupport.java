package org.jxch.capital.stock4j.support;

import okhttp3.HttpUrl;
import okhttp3.Request;

import java.util.function.Supplier;

public interface UrlParamSupport {

    HttpUrl toUrl(HttpUrl.Builder builder);

    default Request newRequest(Supplier<Request.Builder> request, Supplier<HttpUrl.Builder> url) {
        return request.get().url(toUrl(url.get())).build();
    }

}
