package axp.denie46.mediawidget

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.glance.ColorFilter
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.GlanceTheme.colors
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.ActionParameters
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.state.GlanceStateDefinition
import axp.denie46.mediawidget.R

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
            GlanceTheme {
                Widget()
            }
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
        val padding = 12.dp
        val localIntent = Intent(Intent.ACTION_MAIN)
            .apply{
                component = ComponentName("com.sonyericsson.album", "com.sonyericsson.album.MainActivity")
                flags = Intent.FLAG_ACTIVITY_NEW_TASK //how 335544320 turned to this flag: https://stackoverflow.com/questions/52390129/android-intent-setflags-issue
            }
        val musicIntent = Intent(Intent.ACTION_MAIN)
            .apply{
                component = ComponentName("com.sonyericsson.music", "com.sonyericsson.music.MusicActivity")
            }
        val videoIntent = Intent(Intent.ACTION_VIEW)
            .apply {
                addCategory(Intent.CATEGORY_DEFAULT)
                putExtra("com.sonyericsson.album.intent.extra.SCREEN_NAME", "videos")
                component = ComponentName("com.sonyericsson.album", "com.sonyericsson.album.MainActivity")
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        Box (modifier)
        {
            Image(
                provider = ImageProvider(resId = R.drawable.music_button),
                contentDescription = "",
                modifier = GlanceModifier
                    .clickable(onClick = actionStartActivity(
                        intent = musicIntent
                    )
                    )
                    .fillMaxSize()
                    .padding(padding),
                colorFilter = ColorFilter.tint(colors.primary)

            )
        }
        Box (modifier)
        {
            Image(
                provider = ImageProvider(resId = R.drawable.photo_button),
                contentDescription = "",
                modifier = GlanceModifier
                    .clickable(onClick = actionStartActivity(
                        intent = localIntent
                    )
                    )
                    .fillMaxSize()
                    .padding(padding),
                colorFilter = ColorFilter.tint(colors.primary)
            )
        }
        Box (modifier)
        {
            Image(
                provider = ImageProvider(resId = R.drawable.video_button),
                contentDescription = "",
                modifier = GlanceModifier
                    .clickable(onClick = actionStartActivity(
                        intent = videoIntent
                    )
                    )
                    .fillMaxSize()
                    .padding(padding),
                colorFilter = ColorFilter.tint(colors.primary)
            )
        }

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

//    private fun setMusicIntent(paramContext: Context?, paramRemoteViews: RemoteViews){
//        paramRemoteViews.setOnClickPendingIntent(R.id.music_icon, PendingIntent.getActivity(paramContext, 0, Intent("android.intent.action.MUSIC_PLAYER"), PendingIntent.FLAG_IMMUTABLE))
//    }
}