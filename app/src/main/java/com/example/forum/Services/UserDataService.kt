package com.example.forum.Services

import com.example.forum.Controller.App

object UserDataService {

    var id = ""
    var email = ""
    var name = ""

    fun logout() {
        id = ""
        email = ""
        name = ""
        App.prefs.authToken = ""
        App.prefs.userEmail = ""
        App.prefs.isLoggedIn = false
        MessageService.clearMessages()
        MessageService.clearChannels()
    }
}