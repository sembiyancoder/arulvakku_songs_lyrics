package com.arulvakku.lyrics.app.activities

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.utilities.Constants
import com.arulvakku.lyrics.app.utilities.Prefs
import kotlinx.android.synthetic.main.activity_settings.*
import java.lang.Exception

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;

        Font_Size_Tv.setOnClickListener {
            showFontDialog()
        }

        Theme_Tv.setOnClickListener {
            showThemeDialog()
        }

        changeFontSizes()

    }


    private fun changeFontSizes(){
        val font = Prefs.getString(Constants.SP_KEYS.FONT_SIZE,"Medium")
        when {
            font.equals("Small") -> {
                Font_Size_Tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,14f)
                Theme_Tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,14f)
            }
            font.equals("Large") -> {
                Font_Size_Tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,24f)
                Theme_Tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,24f)
            }
            else -> {
                Font_Size_Tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,17f)
                Theme_Tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,17f)
            }
        }
    }

    private fun showFontDialog() {

        lateinit var dialog: AlertDialog
        var size = Prefs.getString(Constants.SP_KEYS.FONT_SIZE, "")
        var checkedItem = -1

        // Show Selected position enable
        if (size == "Small") { //14sp
            checkedItem = 0
        } else if (size == "Medium") { //17sp
            checkedItem = 1
        } else if (size == "Large") { // 24sp
            checkedItem = 2
        } else {
            checkedItem = -1
        }

        val array = arrayOf("Small", "Medium", "Large")

        val builder = AlertDialog.Builder(this)

        builder.setTitle("Font Size")

        // Set the single choice items for alert dialog with initial selection
        builder.setSingleChoiceItems(array, checkedItem) { _, which ->
            size = array[which]
            try {
                Prefs.putString(Constants.SP_KEYS.FONT_SIZE, size)
                changeFontSizes()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        // Set the positive/yes button click listener
        builder.setPositiveButton("OK") { _, _ ->
            dialog.dismiss()

        }
        dialog = builder.create()
        dialog.show()
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(resources.getColor(R.color.colorAccent));
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(resources.getColor(R.color.colorAccent));

    }

    private fun showThemeDialog() {

        lateinit var dialog: AlertDialog
        var theme = Prefs.getString(Constants.SP_KEYS.THEME, "")
        var checkedItem = -1

        // Show Selected position enable
        if (theme == "System Default") {
            checkedItem = 0
        } else if (theme == "Light") {
            checkedItem = 1
        } else if (theme == "Dark") {
            checkedItem = 2
        } else {
            checkedItem = -1
        }

        val array = arrayOf("System Default", "Light", "Dark")

        val builder = AlertDialog.Builder(this)

        builder.setTitle("Choose Theme")

        // Set the single choice items for alert dialog with initial selection
        builder.setSingleChoiceItems(array, checkedItem) { _, which ->
            theme = array[which]
            Prefs.putString(Constants.SP_KEYS.THEME, theme)
        }

        // Set the positive/yes button click listener
        builder.setPositiveButton("OK") { _, _ ->
            try {
                if (theme == "Light") {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                } else if (theme == "Dark") {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        dialog = builder.create()
        dialog.show()
        dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            .setTextColor(resources.getColor(R.color.colorAccent));

    }
}
