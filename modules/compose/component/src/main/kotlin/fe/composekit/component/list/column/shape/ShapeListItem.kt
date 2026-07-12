package fe.composekit.component.list.column.shape

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import fe.composekit.component.CommonDefaults
import fe.composekit.component.shape.CustomShapeDefaults
import fe.android.compose.content.OptionalContent
import fe.android.compose.text.OptionalTextContent
import fe.android.compose.text.TextContent
import fe.composekit.component.list.column.*


public object ShapeListItemDefaults {
    @Composable
    public fun colors(
        containerColor: Color = MaterialTheme.colorScheme.surfaceContainerHigh,
        headlineColor: Color = contentColorFor(containerColor),
        supportingColor: Color = contentColorFor(containerColor),
    ): ListItemColors {
        return ListItemDefaults.colors(
            containerColor = containerColor,
            headlineColor = headlineColor,
            supportingColor = supportingColor
        )
    }
}


@Deprecated("Use new overload")
@Composable
public fun ShapeListItem(
    modifier: Modifier = CommonDefaults.BaseModifier,
    shape: Shape = CustomShapeDefaults.SingleShape,
    padding: PaddingValues = CommonDefaults.EmptyPadding,
    colors: ListItemColors = ShapeListItemDefaults.colors(),
    containerHeight: CustomListItemContainerHeight = CustomListItemDefaults.containerHeight(),
    innerPadding: CustomListItemPadding = CustomListItemDefaults.padding(),
    textOptions: CustomListItemTextOptions = CustomListItemDefaults.textOptions(),
    headlineContent: TextContent,
    overlineContent: OptionalTextContent = null,
    supportingContent: OptionalTextContent = null,
    leadingContent: OptionalContent = null,
    trailingContent: OptionalContent = null,
) {
    ShapeListItem(
        baseModifier = modifier,
        shape = shape,
        padding = padding,
        colors = colors,
        containerHeight = containerHeight,
        innerPadding = innerPadding,
        textOptions = textOptions,
        headlineContent = headlineContent,
        overlineContent = overlineContent,
        supportingContent = supportingContent,
        leadingContent = leadingContent,
        trailingContent = trailingContent
    )
}

@Composable
public fun ShapeListItem(
    modifier: Modifier = Modifier,
    baseModifier: Modifier = CommonDefaults.BaseModifier,
    shape: Shape = CustomShapeDefaults.SingleShape,
    padding: PaddingValues = CommonDefaults.EmptyPadding,
    colors: ListItemColors = ShapeListItemDefaults.colors(),
    containerHeight: CustomListItemContainerHeight = CustomListItemDefaults.containerHeight(),
    innerPadding: CustomListItemPadding = CustomListItemDefaults.padding(),
    textOptions: CustomListItemTextOptions = CustomListItemDefaults.textOptions(),
    headlineContent: TextContent,
    overlineContent: OptionalTextContent = null,
    supportingContent: OptionalTextContent = null,
    leadingContent: OptionalContent = null,
    trailingContent: OptionalContent = null,
) {
    CustomListItem(
        modifier = Modifier
            .clip(shape)
            .then(baseModifier)
            .padding(padding)
            .then(modifier),
        colors = colors,
        overlineContent = overlineContent?.content,
        headlineContent = headlineContent.content,
        leadingContent = leadingContent,
        supportingContent = supportingContent?.content,
        trailingContent = trailingContent,
        containerHeight = containerHeight,
        padding = innerPadding,
        textOptions = textOptions
    )
}
