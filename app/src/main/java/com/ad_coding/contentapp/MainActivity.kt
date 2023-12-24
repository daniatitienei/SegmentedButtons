package com.ad_coding.contentapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ad_coding.contentapp.ui.theme.ContentAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContentAppTheme {
                Scaffold {
                    Box(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize()
                            .padding(horizontal = 20.dp), contentAlignment = Alignment.Center
                    ) {
                        SegmentedButtons(
                            buttons = listOf(
                                SegmentedButton(checked = true, text = "Songs"),
                                SegmentedButton(checked = false, text = "Albums"),
                                SegmentedButton(checked = false, text = "Podcasts")
                            ),
                            onClick = { index, button ->
                                // TODO: ...
                            }
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun SegmentedButtons(
    buttons: List<SegmentedButton>,
    onClick: (Int, SegmentedButton) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onBackground,
                shape = RoundedCornerShape(100)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        buttons.forEachIndexed { index, segmentedButton ->
            val animatedColor by animateColorAsState(
                targetValue = if (segmentedButton.checked) {
                    MaterialTheme.colorScheme.primaryContainer.copy(
                        alpha = 0.4f
                    )
                } else {
                    MaterialTheme.colorScheme.background
                },
                label = ""
            )

            val shape = if (index == 0 && index == buttons.lastIndex) {
                RoundedCornerShape(100)
            } else if (index == 0) {
                RoundedCornerShape(
                    topStart = 100.dp,
                    bottomStart = 100.dp
                )
            } else if (index != buttons.lastIndex) {
                RoundedCornerShape(0)
            } else {
                RoundedCornerShape(
                    topEnd = 100.dp,
                    bottomEnd = 100.dp
                )
            }

            Row(
                modifier = Modifier
                    .weight(1f)
                    .background(
                        color = animatedColor,
                        shape = shape
                    )
                    .clickable {
                        onClick(index, segmentedButton)
                    }
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AnimatedVisibility(visible = segmentedButton.checked) {
                    Row {
                        Icon(
                            imageVector = Icons.Rounded.Check,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
                Text(
                    text = segmentedButton.text,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            if (index < buttons.lastIndex) {
                Divider(
                    modifier = Modifier
                        .height(40.dp)
                        .width(1.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

data class SegmentedButton(
    val checked: Boolean,
    val text: String
)

@Preview
@Composable
fun Preview() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp), contentAlignment = Alignment.Center
        ) {
            SegmentedButtons(
                buttons = listOf(
                    SegmentedButton(checked = true, text = "Songs"),
                    SegmentedButton(checked = false, text = "Albums"),
                    SegmentedButton(checked = false, text = "Podcasts")
                ),
                onClick = { index, button ->
                    // TODO: ...
                }
            )
        }
    }
}