# Fonament Presentation

The fonament presentation contains base blocks to build a UI Node (Screen, Dialog, ...).

![FonamentUI](assets/fonament-ui.svg)

- `FonamentUI<FonamentUIState>`: Represents the base class for the UI Node. It contains a `FonamentContent` instance that is rendered on the `invoke` and that receives the `FonamentUIState` instance from the `FonamentViewModel`.
- `FonamentViewModel`: handles the events triggered by the `content` and updates the state of `uiState` instance. These events are received on the `handleEvent(event: FonamentEvent)` function.
- `FonamentContent`: contains the content of our UI Node. It receives a `uiState`, `contentState`, `modifier` and `onEvent` parameters on `invoke` function. The UI state responsibility is shared between the `uiState` and `contentState` variables, following the Android Developers definition for the [types of UI state](https://developer.android.com/develop/ui/compose/state-hoisting#types-ui-state). The `contentState: FonamentContentState` is the state holder for the UI Elements on this content. It is based on the State hoisting practice of having a [plain state holder class](https://developer.android.com/develop/ui/compose/state-hoisting#classes-as-state-owner).

## Defining the `FonamentUI` class

```kotlin
data object EditTaskListScreen : FonamentUI<EditTaskListUIState>() {
   override val content: FonamentContent<EditTaskListUIState, *> = EditTaskListContent
}

@Immutable
data class EditTaskListUIState(
   val isLoading: Boolean = false,
) : FonamentUIState
```

## Defining the `FonamentViewModel` class

```kotlin
class EditTaskListViewModel(
   private val updateTaskListUseCase: UpdateTaskListUseCase,
) : FonamentViewModel<EditTaskListUIState>(
   initialUIState = EditTaskListUIState(isLoading = true),
) {
   override fun handleEvent(event: FonamentEvent) {
      when (event) {
         is EditTaskListEvent.UpdateTaskList -> updateTaskList(event)
      }
   }

   // ...
}
```

## `FonamentEvent`s

```kotlin
sealed interface EditTaskListEvent : FonamentEvent {
   data class UpdateTaskList(val name: String) : EditTaskListEvent
}
```

## Defining the `FonamentContent` class

![FonamentContent](assets/fonament-ui-content.svg)

<details open>

<summary><code>FonamentContent</code> Code</summary>

```kotlin
data object EditTaskListContent : FonamentContent<EditTaskListUIState, EditTaskListContentState>() {

   @Composable
   override fun createContentState(
      uiState: EditTaskListUIState,
   ): EditTaskListContentState = rememberEditTaskListContentState(
      taskListName = uiState.taskList?.name ?: "",
   )

   @Composable
   override fun Content(
      uiState: EditTaskListUIState,
      contentState: EditTaskListContentState,
      modifier: Modifier,
   ) {
      when {
         !uiState.isLoading -> {
            Scaffold(
               topBar = {
                  EditTaskListTopBar(
                     onSaveButtonClick = {
                        onEvent(
                           EditTaskListEvent.UpdateTaskList(
                              contentState.nameTextFieldValue,
                           ),
                        )
                     },
                  )
               },
               content = { paddingValues ->
                  EditTaskListContent(
                     nameTextFieldValue = contentState.nameTextFieldValue,
                     modifier = Modifier.padding(paddingValues),
                  )
               },
            )
         }
      }
   }
```

</details>

!!! info
    The events can be triggered on `FonamentContent` using the `onEvent` function.

<details>

The `FonamentContentState` on this example is the responsible of holding the `TextField` value and receives the current `TaskList` name as an initial value.

<summary><code>FonamentContentState</code> Code</summary>

```kotlin
data class EditTaskListContentState internal constructor(
   private val taskListName: String,
) : FonamentContentState {

   var nameTextFieldValue: String by mutableStateOf(taskListName)
      private set

   override fun handleEvent(event: FonamentEvent) {
      when (event) {
         is EditTaskListEvent.NameTextFieldValueChange -> {
            nameTextFieldValue = event.value
         }
      }
   }

   companion object {
      internal fun saver(): Saver<EditTaskListContentState, String> = Saver(
         save = {
            it.nameTextFieldValue
         },
         restore = {
            EditTaskListContentState(
               taskListName = it,
            )
         },
      )
   }
}
```

```kotlin
@Composable
internal fun rememberEditTaskListContentState(
    taskListName: String,
): EditTaskListContentState = rememberSaveable(
    inputs = arrayOf(taskListName),
    saver = EditTaskListContentState.saver(),
) {
    EditTaskListContentState(
        taskListName = taskListName,
    )
}
```

</details>

!!! abstract
    For this implementation we are implementing a custom `Saver` to avoid losing `nameTextFieldValue` between configuration changes. Also, it is **important** to remember the `FonamentContentState` instantiation to be reused across compositions.

## 📲 Display a Fonament UI Node

```kotlin
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun App() {
   EditTaskListScreen.invoke(
      viewModel = viewModel<EditTaskListViewModel>(),
      onEvent = {},
   )
}
```

!!! info
    We are using the `androidx.lifecycle.viewmodel.compose.viewModel` function to create the ViewModel on this base example, but we can also `Koin` and `Hilt`. Check the examples on `demos` [directory](https://github.com/serbelga/fonament/tree/main/demos).

## 🧭 Navigation

Navigation is handled with a specific type of `FonamentEvent`, the `FonamentNavigationEvent`. To make a `FonamentUI` node navigable we should use the `FonamentUI<U>.NavigationNode` extension function. This function receives a `FonamentNavigationEventHandler` and the `viewModel`. The `FonamentNavigationEventHandler` is an interface that handles `FonamentNavigationEvent`.

```kotlin
sealed interface TaskDetailsNavigationEvent : FonamentNavigationEvent {
    data object NavigateBack : TaskDetailsNavigationEvent

    data object NavigateToEditTask : TaskDetailsNavigationEvent
}

fun taskDetailsNavigationEventHandler(
    navigateBack: () -> Unit,
    navigateToEditTask: () -> Unit,
): FonamentNavigationEventHandler<TaskDetailsNavigationEvent> = FonamentNavigationEventHandler {
    when (it) {
        TaskDetailsNavigationEvent.NavigateBack -> navigateBack()
        TaskDetailsNavigationEvent.NavigateToEditTask -> navigateToEditTask()
    }
}
```

Then, in our Navigation graph (in this example, `NavGraph` from `NavigationCompose`):

```kotlin
private fun NavGraphBuilder.taskDetailsNode(
    navigateBack: () -> Unit,
    navigateToEditTask: (String) -> Unit,
) {
    composable(...) { navBackStackEntry ->
        val taskId = ...
        val taskDetailsNavigationEventHandler = taskDetailsNavigationEventHandler(
            navigateBack = navigateBack,
            navigateToEditTask = {
                navigateToEditTask.invoke(taskId)
            },
        )
        TaskDetailsScreen.NavigationNode(
            navigationEventHandler = taskDetailsNavigationEventHandler,
            viewModel = ... // DI viewModel with taskId parameter.
        )
    }
}
```

Launch the event on content

```kotlin
   @Composable
   private fun TaskDetailsTopBar() {
      TopAppBar(
         navigationIcon = {
            IconButton(
               onClick = {
                  onEvent(TaskDetailsNavigationEvent.NavigateBack)
               },
      ) {
```

## 📸 Preview/Test UI content

This separation of concerns between `FonamentContent` and `FonamentViewModel` makes it easier to test and preview because we don't need a `FonamentViewModel` instance to test/preview our content.

```kotlin
@PreviewLocales
@PreviewLightDark
@PreviewLandscape
@Composable
fun EditTaskListContentPreview() {
   EditTaskListContent(
      uiState = EditTaskListUIState(
         taskList = taskListSample,
      ),
      contentState = rememberEditTaskListContentState(
         taskListName = taskListSample.name,
      ),
   )
}
```

## Reference links

- [Classes as state owner](https://developer.android.com/develop/ui/compose/state-hoisting#classes-as-state-owner)
- [Types of UI state](https://developer.android.com/develop/ui/compose/state-hoisting#types-ui-state)
- [UI layer architecture](https://developer.android.com/topic/architecture/ui-layer#architecture)
