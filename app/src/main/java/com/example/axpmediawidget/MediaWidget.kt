package com.example.axpmediawidget

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
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
            .background(Color.DarkGray)
            .padding(5.dp),
        verticalAlignment = Alignment.Vertical.CenterVertically,
        horizontalAlignment = Alignment.Horizontal.CenterHorizontally
    ) {
        val modifier = GlanceModifier.defaultWeight()
        Button(
            text = "click",
            onClick = actionRunCallback(ClickActionCallback()::class.java),
            modifier
        )
        Button(
            text = "click",
            onClick = actionRunCallback(ClickActionCallback()::class.java),
            modifier
        )
        Button(
            text = "click",
            onClick = actionRunCallback(ClickActionCallback()::class.java),
            modifier
        )
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