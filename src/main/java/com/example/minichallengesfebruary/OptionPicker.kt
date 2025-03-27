import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.minichallengesfebruary.ui.theme.PickerBackground
import com.example.minichallengesfebruary.ui.theme.PickerInactiveText

@Composable
fun OptionPicker(
    modifier: Modifier = Modifier,
) {
    var selectedOption by remember { mutableStateOf("1,000") }
    val options = listOf("1,000", "1.000", "1 000")

    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
    ) {
        Text(text = "Thousands separator")
        OptionPicker(options = options, selectedOption = selectedOption) {
            selectedOption = it
        }
    }
}

@Composable
private fun OptionPicker(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    val selectedIndex = options.indexOf(selectedOption)
    val density = LocalDensity.current
    var rowWidth by remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(PickerBackground)
            .padding(4.dp)
            .onSizeChanged { rowWidth = it.width }
    ) {
        if (rowWidth > 0) {
            val optionWidthPx = rowWidth / options.size
            val optionWidthDp = with(density) { optionWidthPx.toDp() }
            val transition = updateTransition(targetState = selectedIndex, label = "optionTransition")
            val offsetX by transition.animateDp(
                transitionSpec = { tween(durationMillis = 300, easing = FastOutSlowInEasing) },
                label = "offsetX"
            ) { index -> with(density) { (index * optionWidthPx).toDp() } }

            Box(
                modifier = Modifier
                    .offset(x = offsetX)
                    .width(optionWidthDp)
                    .height(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            options.forEach { option ->
                val isSelected = option == selectedOption

                Text(
                    text = option,
                    color = if (isSelected) Color.Black else PickerInactiveText,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .clickable(
                            interactionSource = null,
                            indication = null
                        ) { onOptionSelected(option) }
                        .wrapContentHeight(Alignment.CenterVertically),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OptionPickerPreview() {
    var selectedOption by remember { mutableStateOf("Opcja 1") }
    val options = listOf("Opcja 1", "Opcja 2", "Opcja 3")

    OptionPicker(options = options, selectedOption = selectedOption) {
        selectedOption = it
    }
}
