package Android.Kotlin.appnote.data.model

import android.os.Parcelable
import java.util.*
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Note(
        val id: String,
        val title: String,
        val bodyNote: String,
        val color: Color = Color.WHITE,
        val lastChange: Date = Date(),
): Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Note

        if (id != other.id) return false

        return true
    }
    override fun hashCode(): Int {
        return id.hashCode()
    }
}
enum class Color {
    WHITE,
    YELLOW,
    GREEN,
    BLUE,
    RED,
    VIOLET,
    PINK
}

