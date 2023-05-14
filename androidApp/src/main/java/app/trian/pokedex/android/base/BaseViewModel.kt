/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */



package app.trian.pokedex.android.base

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.trian.pokedex.android.ApplicationState
import app.trian.pokedex.android.base.extensions.backPressedAndClose
import app.trian.pokedex.android.base.extensions.hideBottomSheet
import app.trian.pokedex.android.base.extensions.hideKeyboard
import app.trian.pokedex.android.base.extensions.navigate
import app.trian.pokedex.android.base.extensions.navigateAndReplace
import app.trian.pokedex.android.base.extensions.navigateAndReplaceAll
import app.trian.pokedex.android.base.extensions.navigateSingleTop
import app.trian.pokedex.android.base.extensions.navigateUp
import app.trian.pokedex.android.base.extensions.runSuspend
import app.trian.pokedex.android.base.extensions.showBottomSheet
import app.trian.pokedex.data.common.Response
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel<State : Parcelable, Action>(
    private val initialState: State,
) : ViewModel() {
    companion object {
        val dispatcher: CoroutineDispatcher = Dispatchers.Default
    }

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    /**
     * 5000 ms just tricky way to avoid reset state while screen rotate
     * **/
    val uiState get() = _uiState.asStateFlow()
    //this cause bug
//        .stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.WhileSubscribed(5000L),
//        initialValue = initialData
//    )

    private val action = Channel<Action>(Channel.UNLIMITED)

    private lateinit var _app: ApplicationState

    fun setAppState(appState: ApplicationState) {
        _app = appState
    }

    protected fun onEvent(
        block: suspend (Action) -> Unit
    ) {
        async {
            action
                .consumeAsFlow()
                .collect { block(it) }
        }
    }

    suspend fun <T> Flow<Response<T>>.onEach(
        loading: suspend () -> Unit,
        error: suspend String.() -> Unit,
        success: suspend T.() -> Unit
    ) {
        this.catch {
            error(it.message.orEmpty())
        }.collect {
            when (it) {
                is Response.Error -> error(it.errorMessage())
                Response.Loading -> loading()
                is Response.Result -> success(it.data)
            }
        }
    }

    protected inline fun async(crossinline block: suspend () -> Unit) = with(viewModelScope) {
        launch { block() }
    }

    protected inline fun asyncWithState(crossinline block: suspend State.() -> Unit) =
        with(viewModelScope) {
            launch { block(uiState.value) }
        }

    protected suspend inline fun <T> await(crossinline block: suspend () -> T): T =
        withContext(dispatcher) { block() }

    protected inline fun asyncFlow(crossinline block: suspend () -> Unit) = async {
        withContext(dispatcher) {
            block()
        }
    }

    fun runSuspend(cb: suspend CoroutineScope. () -> Unit) = _app.runSuspend(block = cb)

    protected abstract fun handleActions()
    private fun commit(state: State) {
        _uiState.tryEmit(state)
    }

    fun commit(state: State.() -> State) = this.commit(state(uiState.value))

    fun resetState() = commit(initialState)

    fun dispatch(e: Action) = async { action.send(e) }

    //region response
    fun Response.Error.errorMessage() =
        this.message.ifEmpty { _app.context.getString(this.stringRes) }

    fun Response.Error.errorMessage(vararg params: String) =
        this.message.ifEmpty { _app.context.getString(this.stringRes, *params) }
    //end region

    //region snakcbar
    fun showSnackbar(message: String) = _app.showSnackbar(message)
    fun showSnackbar(message: Int) = _app.showSnackbar(message)
    fun showSnackbar(message: Int, vararg params: String) = _app.showSnackbar(message, *params)

    fun getString(id:Int,vararg params:String) = _app.context.getString(id,*params)
    fun hideKeyboard() = _app.context.hideKeyboard()
    //

    //region navigation
    fun backAndClose() = _app.backPressedAndClose()
    fun navigateUp() = _app.navigateUp()
    fun navigate(routeName: String, vararg args: String) = _app.navigate(routeName, *args)
    fun navigateSingleTop(routeName: String, vararg args: String) =
        _app.navigateSingleTop(routeName, *args)

    fun navigateAndReplace(routeName: String, vararg args: String) =
        _app.navigateAndReplace(routeName, *args)

    fun navigateAndReplaceAll(routeName: String, vararg args: String) =
        _app.navigateAndReplaceAll(routeName, *args)

    //end region

    //region bottom sheet
    fun showBottomSheet() = _app.showBottomSheet()
    fun hideBottomSheet() = _app.hideBottomSheet()
    //end region

    override fun onCleared() {
        super.onCleared()
        action.cancel()
        _uiState.tryEmit(initialState)
        async { currentCoroutineContext().cancel() }
    }
}

abstract class BaseViewModelData<State : Parcelable, DataState : Parcelable, Action>(
    initialState: State,
    private val initialData: DataState
) : BaseViewModel<State, Action>(initialState) {
    private val _uiDataState: MutableStateFlow<DataState> = MutableStateFlow(initialData)
    val uiDataState get() = _uiDataState.asStateFlow()

    //this cause bug
//        .stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.WhileSubscribed(5000L),
//        initialValue = initialData
//    )

    protected inline fun asyncWithData(crossinline block: suspend DataState.() -> Unit) =
        with(viewModelScope) {
            launch { block(uiDataState.value) }
        }

    private fun commitData(dataState: DataState) {
        _uiDataState.tryEmit(dataState)
    }

    fun commitData(dataState: DataState.() -> DataState) {
        this.commitData(dataState(uiDataState.value))
    }

    fun resetDataState() {
        this.commitData(initialData)
    }

    override fun onCleared() {
        super.onCleared()
        _uiDataState.tryEmit(initialData)
    }
}