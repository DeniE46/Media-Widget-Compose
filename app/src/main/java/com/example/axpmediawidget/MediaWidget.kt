package com.example.axpmediawidget

import android.content.Context
import android.content.res.loader.ResourcesProvider
import android.util.Log
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme.colors
import androidx.glance.ImageProvider
import androidx.glance.action.ActionParameters
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.state.GlanceStateDefinition

object MediaWidget: GlanceAppWidget() {

    override val sizeMode: SizeMode
        get() = super.sizeMode

    override val stateDefinition: GlanceStateDefinition<*>?
        get() = super.stateDefinition

    override suspend fun onDelete(context: Context, glanceId: GlanceId) {
        super.onDelete(context, glanceId)
    }

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            Widget()
        }
    }

}

@Composable
private fun Widget() {
    Row(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(ImageProvider(resId = R.drawable.widget_plate)),
        verticalAlignment = Alignment.Vertical.CenterVertically,
        horizontalAlignment = Alignment.Horizontal.CenterHorizontally
    ) {
        val modifier = GlanceModifier.defaultWeight()
        Box (
            modifier.background(ImageProvider(resId = R.drawable.music_button))
                .clickable(onClick = actionRunCallback(ClickActionCallback()::class.java))
        )
        {}
        Box (
            modifier.background(ImageProvider(resId = R.drawable.photo_button))
                .clickable(onClick = actionRunCallback(ClickActionCallback()::class.java))
        )
        {}
        Box (
            modifier.background(ImageProvider(resId = R.drawable.video_button))
                .clickable(onClick = actionRunCallback(ClickActionCallback()::class.java))
        )
        {}

    }
}

@Preview
@Composable
private fun WidgetPreview() {
    Widget()
}

class MediaWidgetReceiver: GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = MediaWidget
}

class ClickActionCallback: ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        Log.d("TAG", "onAction: clicked")
    }
}