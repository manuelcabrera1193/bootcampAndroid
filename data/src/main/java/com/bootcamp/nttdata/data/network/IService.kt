package com.bootcamp.nttdata.data.network

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface IService {

    /**
     *  Template get request
     *
     *  @param @Url concatenated endpoint + get params
     *  @return any object
     */
    @GET
    fun get(
        @Url url: String
    ): Deferred<Response<Any>>

    /**
     *  Template post request
     *
     *  @param @Url: url base + endpoint
     *  @param @Body payload
     *  @return any object
     */
    @POST
    fun post(
        @Url url: String,
        @Body body: Any?
    ): Deferred<Response<Any>>

    /**
     *  Template post request
     *
     *  @param @Url: url base + endpoint
     *  @param @FieldMap payload
     *  @return any object
     */
    @FormUrlEncoded
    @POST
    fun postEncode(
        @Url url: String,
        @FieldMap body: HashMap<String, String>
    ): Deferred<Response<Any>>

    /**
     *  Template put request
     *
     *  @param @Url: url base + endpoint
     *  @param @Body payload
     *  @return any object
     */
    @PUT
    fun put(
        @Url url: String,
        @Body body: Any?
    ): Deferred<Response<Any>>

    /**
     *  Template put request
     *
     *  @param @Url: url base + endpoint
     *  @param @FieldMap payload
     *  @return any object
     */
    @FormUrlEncoded
    @PUT
    fun putEncode(
        @Url url: String,
        @FieldMap body: HashMap<String, String>
    ): Deferred<Response<Any>>

    /**
     *  Template patch request
     *
     *  @param @Url: url base + endpoint
     *  @param @Body payload
     *  @return any object
     */
    @PATCH
    fun patch(
        @Url url: String,
        @Body body: Any?
    ): Deferred<Response<Any>>

    /**
     *  Template patch request
     *
     *  @param @Url: url base + endpoint
     *  @param @FieldMap payload
     *  @return any object
     */
    @FormUrlEncoded
    @PATCH
    fun patchEncode(
        @Url url: String,
        @FieldMap body: HashMap<String, String>
    ): Deferred<Response<Any>>

    /**
     *  Template delete request
     *
     *  @param @Url: url base + endpoint
     *  @return any object
     */
    @DELETE
    fun delete(
        @Url url: String
    ): Deferred<Response<Any>>
}