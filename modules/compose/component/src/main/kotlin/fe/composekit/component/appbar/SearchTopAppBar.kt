package fe.composekit.component.appbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fe.android.compose.text.DefaultContent.Companion.text
import fe.android.compose.text.TextContent
import fe.composekit.component.BackIconButton
import fe.composekit.component.list.column.SaneLazyColumnDefaults
import kotlinx.coroutines.flow.collectLatest


@OptIn(ExperimentalMaterial3Api::class)
@Deprecated("Use overload with navigationIcon parameter instead")
@Composable
public fun SearchTopAppBar(
    titleContent: TextContent,
    placeholderContent: TextContent,
    query: String,
    onQueryChange: (String?) -> Unit,
    onBackPressed: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {},
) {
    val state = rememberTextFieldState(initialText = query)
    LaunchedEffect(state) {
        snapshotFlow { state.text.toString() }.collectLatest {
            onQueryChange(it)
        }
    }
    SearchTopAppBar(
        titleContent = titleContent,
        placeholderContent = placeholderContent,
        state = state,
        onSearch = onQueryChange,
        navigationIcon = {
            BackIconButton(onBackPressed = onBackPressed)
        },
        actions = actions
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun SearchTopAppBar(
    titleContent: TextContent,
    placeholderContent: TextContent,
    state: TextFieldState,
    onSearch: (String) -> Unit,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Column(
        modifier = Modifier.padding(bottom = SaneLazyColumnDefaults.BottomSpacing),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy((-1).dp)
    ) {
        TopAppBar(
            titleContent = titleContent,
            navigationIcon = navigationIcon,
            actions = actions,
            scrollBehavior = scrollBehavior,
        )

        DockedSearchBar(
            modifier = Modifier.padding(horizontal = SaneLazyColumnDefaults.HorizontalSpacing),
            inputField = {
                SearchBarDefaults.InputField(
                    state = state,
                    onSearch = onSearch,
                    expanded = false,
                    onExpandedChange = {},
                    placeholder = placeholderContent.content,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        if (state.text.isNotEmpty()) {
                            IconButton(onClick = state::clearText) {
                                Icon(
                                    imageVector = Icons.Rounded.Clear,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                )
            },
            expanded = false,
            onExpandedChange = {},
            content = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun TopAppBar(
    titleContent: TextContent,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        title = titleContent.content,
        navigationIcon = navigationIcon,
        actions = actions,
        scrollBehavior = scrollBehavior
    )
}

@Composable
public fun SearchNavigationIcon(onBackPressed: () -> Unit) {
    IconButton(onClick = onBackPressed) {
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
            contentDescription = null
        )
    }
}

//actions: @Composable RowScope.() -> Unit = {},

@Preview(showBackground = true)
@Composable
private fun SearchTopAppBarPreview() {
    SearchTopAppBar(
        titleContent = text("Hello"),
        placeholderContent = text("World"),
        query = "Test",
        onQueryChange = {

        },
        onBackPressed = {},
    )
}
