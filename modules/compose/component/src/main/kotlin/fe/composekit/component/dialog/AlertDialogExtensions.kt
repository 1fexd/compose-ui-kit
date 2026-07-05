package fe.composekit.component.dialog

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp

@Immutable
public data class DialogSizeOptions(
    public val minWidth: Dp,
    public val maxWidth: Dp,
)

@Immutable
public data class ButtonSpacingOptions(
    public val mainAxisSpacing: Dp,
    public val crossAxisSpacing: Dp,
)

@Immutable
public data class DialogPaddingOptions(
    public val box: PaddingValues,
    public val icon: PaddingValues,
    public val title: PaddingValues,
    public val text: PaddingValues,
    public val buttons: PaddingValues,
)
