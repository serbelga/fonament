# Fonament

[![Maven Central](https://img.shields.io/maven-central/v/dev.sergiobelda.fonament/fonament)](https://search.maven.org/search?q=g:dev.sergiobelda.fonament)

Fonament is a base library that offers a collection of elements to build an application.

## Fonament UI

The fonament UI contains base blocks to build a UI Node (Screen, Dialog, ...).

![FonamentUI](https://github.com/user-attachments/assets/6e0a0077-5967-4065-9084-f3609587835e)

- `FonamentUI<FonamentUIState>`: Represents the base class for the UI Node. It contains a `FonamentContent` instance that is rendered on the `invoke` and that receives the `FonamentUIState` instance from the `FonamentViewModel`.
- `FonamentViewModel`: handles the events triggered by the `content` and updates the state of `uiState` instance. These events are received on the `handleEvent(event: FonamentEvent)` function.
- `FonamentContent`: contains the content of our UI Node. It receives a `uiState`, `contentState`, `modifier` and `onEvent` parameters on `invoke` function. The UI state responsibility is shared between the `uiState` and `contentState` variables, following the Android Developers definition for the [types of UI state](https://developer.android.com/develop/ui/compose/state-hoisting#types-ui-state). The `contentState: FonamentContentState` is the state holder for the UI Elements on this content. It is based on the State hoisting practice of having a [plain state holder class](https://developer.android.com/develop/ui/compose/state-hoisting#classes-as-state-owner).

### Defining the `FonamentUI` class

```kotlin
data object EditTaskListScreen : FonamentUI<EditTaskListUIState>() {
   override val content: FonamentContent<EditTaskListUIState, *> = EditTaskListContent
}

@Immutable
data class EditTaskListUIState(
   val isLoading: Boolean = false,
) : FonamentUIState
```

### Defining the `FonamentViewModel` class

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

   ...

}
```

### `FonamentEvent`s

```kotlin
sealed interface EditTaskListEvent : FonamentEvent {
   data class UpdateTaskList(val name: String) : EditTaskListEvent
}
```

### Defining the `FonamentContent` class

<details>

<summary><code>FonamentContent</code> example</summary>

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

> [!IMPORTANT]
> The events can be triggered on `FonamentContent` using the `onEvent` function.

<details>

<summary><code>FonamentContentState</code> example</summary>



</details>

### Display a Fonament UI Node



### Preview/Test UI content

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

## Usage examples

- [Todometer KMP](https://github.com/serbelga/Todometer-KMP/tree/main/app-feature)

## License

```
   Copyright 2025 Sergio Belda

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```

