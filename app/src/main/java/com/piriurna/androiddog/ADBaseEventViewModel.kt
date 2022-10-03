package com.piriurna.androiddog

import androidx.lifecycle.ViewModel

abstract class ADBaseEventViewModel<E: ADBaseEvent> : ViewModel() {
    open fun onTriggerEvent(event: E) {}
}
