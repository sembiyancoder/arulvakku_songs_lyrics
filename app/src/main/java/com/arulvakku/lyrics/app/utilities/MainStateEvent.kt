package com.bosco.mrroom.utils


/**
 * @author bsoft-61 on 18/2/21.
 * */
sealed class MainStateEvent {
    object GetBlogsEvent: MainStateEvent()

    object None: MainStateEvent()
}