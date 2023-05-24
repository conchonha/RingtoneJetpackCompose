package com.example.myapplication.common

import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier

/**
 * Theo dõi trạng thái của :
 * + nhấp vào nút: PressInteraction.Press
 * + Nếu người dùng nhấc ngón tay ra khỏi nút, nút này sẽ tạo ra PressInteraction.Release
 * + nếu người dùng kéo ngón tay ra khỏi nút rồi nhấc ngón tay lên, nút đó sẽ tạo ra PressInteraction.Cancel
 * +  nếu muốn xem liệu một nút cụ thể có được nhấn hay không, bạn có thể gọi phương thức InteractionSource.collectIsPressedAsState():
 * + Start
 * + Stop
 * + Cancel
 * +
 */
@Composable
fun ButtonContainer(
    eventList: (SnapshotStateList<Interaction>) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val interactions = remember { mutableStateListOf<Interaction>() }
    eventList.invoke(interactions)

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> {
                    interactions.add(interaction)
                }

                is PressInteraction.Release -> {
                    interactions.remove(interaction.press)
                }

                is PressInteraction.Cancel -> {
                    interactions.remove(interaction.press)
                }

                is DragInteraction.Start -> {
                    interactions.add(interaction)
                }

                is DragInteraction.Stop -> {
                    interactions.remove(interaction.start)
                }

                is DragInteraction.Cancel -> {
                    interactions.remove(interaction.start)
                }
            }
        }
        //Bây giờ, nếu muốn biết thành phần hiện đang được nhấn hay kéo, bạn chỉ cần kiểm tra xem interactions có trống hay không
        val isPressedOrDragged = interactions.isNotEmpty()
    }

    //Nếu muốn biết lượt tương tác gần đây nhất, bạn chỉ cần xem mục sau cuối trong list
    val lastInteraction = when (interactions.lastOrNull()) {
        is DragInteraction.Start -> "Dragged"
        is PressInteraction.Press -> "Pressed"
        else -> "No state"
    }

    Button(modifier = modifier, onClick = { /*TODO*/ }, interactionSource = interactionSource) {
        content()
    }
}