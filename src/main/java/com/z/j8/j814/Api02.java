package com.z.j8.j814;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.concurrent.CompletableFuture;

class Api02 {

    public static void main(String[] args) throws InterruptedException {

        CompletableFuture.supplyAsync(() -> 1)
                .handle((v, t) -> Integer.sum(v, 10))
                .thenApply(i -> Integer.sum(i, 100))
                .thenAccept(System.out::println);

        Thread.sleep(1000L);
    }
}
