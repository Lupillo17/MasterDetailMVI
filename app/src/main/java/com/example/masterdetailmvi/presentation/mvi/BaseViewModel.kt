package com.example.masterdetailmvi.presentation.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<UiState : BaseUiState, UserEvent : BaseUserEvent, SideEffect : BaseSideEffect> :
    ViewModel() {
    private val initialState: UiState by lazy { createInitialState() }
    abstract fun createInitialState(): UiState

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(initialState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _userEvent: MutableSharedFlow<UserEvent> = MutableSharedFlow()
    val userEvent: SharedFlow<UserEvent> = _userEvent.asSharedFlow()

    private val _sideEffect: Channel<SideEffect> = Channel()
    val sideEffect: Flow<SideEffect> = _sideEffect.receiveAsFlow()

    init {
        subscribeUserEvents()
    }

    protected fun setUiState(updateFunction: UiState.() -> UiState) {
        _uiState.update(updateFunction)
    }

    protected fun setUserEvent(userEvent: UserEvent) {
        viewModelScope.launch {
            _userEvent.emit(userEvent)
        }
    }

    protected fun setSideEffect(builder: () -> SideEffect) {
        viewModelScope.launch {
            val sideEffect = builder()
            _sideEffect.send(sideEffect)
        }
    }

    private fun subscribeUserEvents() {
        viewModelScope.launch {
            userEvent.collect {
                handleUserEvent(it)
            }
        }
    }

    abstract fun handleUserEvent(userEvent: UserEvent)
}