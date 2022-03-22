package com.tung.musicapplication.base

open class BaseNetworkTest {

    protected lateinit var networkTest: NetworkTest

    open fun setUp() {
        networkTest = NetworkTest()
        networkTest.startMockServer(true)
    }

    open fun tearDown() {
        //Stop Mock server
        networkTest.stopMockServer()
    }
}