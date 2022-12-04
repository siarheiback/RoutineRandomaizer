package com.finmanager.routinerandomaizer

sealed class VisibilityController{
    class Inactive(var msg: String) : VisibilityController()
    class Active(var msg: String) : VisibilityController()
    class NoTasks(var msg: String) : VisibilityController()
}

