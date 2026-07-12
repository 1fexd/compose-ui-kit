package fe.android.compose.content

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

public typealias Content = @Composable () -> Unit
public typealias OptionalContent = (@Composable () -> Unit)?

@Composable
public inline fun rememberOptionalContent(
    condition: Boolean,
    crossinline content: @Composable () -> Unit
): OptionalContent {
    return remember(key1 = condition) {
        if (condition) ({ content() }) else null
    }
}

@Composable
public inline fun <K1 : Any?, K2 : Any?> rememberOptionalContent(
    condition: Boolean,
    key1: K1,
    key2: K2,
    crossinline content: @Composable () -> Unit
): OptionalContent {
    return remember(key1 = key1, key2 = key2) {
        if (condition) ({ content() }) else null
    }
}

@Composable
public inline fun <K1 : Any?, K2 : Any?, K3 : Any?> rememberOptionalContent(
    condition: Boolean,
    key1: K1,
    key2: K2,
    key3: K3,
    crossinline content: @Composable () -> Unit
): OptionalContent {
    return remember(key1 = key1, key2 = key2, key3 = key3) {
        if (condition) ({ content() }) else null
    }
}

@Composable
public inline fun <T : Any?> rememberOptionalContent(
    key1: T?,
    crossinline content: @Composable (T) -> Unit
): OptionalContent {
    return remember(key1 = key1) {
        key1?.let {
            { content(it) }
        }
    }
}

@Composable
public inline fun rememberContent(crossinline content: @Composable () -> Unit): Content {
    return remember {
        { content() }
    }
}
