package com.arulvakku.lyrics.app.activities.lyrics

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.activities.BaseActivity
import com.arulvakku.lyrics.app.utilities.Constants
import com.arulvakku.lyrics.app.utilities.Prefs
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_lyrics_screen.*
import kotlinx.android.synthetic.main.bottom_sheet_settings.view.*


class LyricsScreenActivity : BaseActivity() {

    private val title by lazy {
        intent.getStringExtra("title")
    }

    private val song by lazy {
        intent.getStringExtra("song")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lyrics_screen)

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        setData()
    }

    /**
     * set title and song data
     */
    private fun setData() {
        supportActionBar?.title = title
        lyricsTExt.text = song
        val font = Prefs.getString(Constants.SP_KEYS.FONT_SIZE, "Medium")
        when {
            font.equals("Small") -> {
                lyricsTExt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
            }
            font.equals("Large") -> {
                lyricsTExt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)
            }
            else -> {
                lyricsTExt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17f)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.lyrics_menu, menu)
        return true
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        return when (id) {
            R.id.share -> {
                shareSong(song);
                true
            }
            R.id.copy -> {
                copyTextToClipboard(song)
                true
            }
            R.id.settings -> {
                openBottomSheet()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun openBottomSheet() {
        val btnsheet = layoutInflater.inflate(R.layout.bottom_sheet_settings, null)
        val dialog = BottomSheetDialog(this)
        val fontSpinner = btnsheet.font_spinner
        val readSpinner = btnsheet.mode_spinner
        val seekBar = btnsheet.seekBar
        val close = btnsheet.close_tv
        dialog.setContentView(btnsheet)

        var size = Prefs.getString(Constants.SP_KEYS.FONT_SIZE, "")
        var theme = Prefs.getString(Constants.SP_KEYS.THEME, "")
        var themePosition = 0
        var sizePosition = 0
        if (size == "Small") {
            sizePosition = 0
        } else if (size == "Medium") {
            sizePosition = 1
        } else if (size == "Large") {
            sizePosition = 2
        }
        if (theme == "Day") {
            themePosition = 0
        } else if (theme == "Night") {
            themePosition = 1
        } else if (theme == "System Default") {
            themePosition = 2
        }

        //Font Spinner Logic
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            listOf("Small", "Medium", "Large")
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        fontSpinner.adapter = adapter
        fontSpinner.setSelection(sizePosition)
        fontSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val item = adapter.getItem(position)
                Prefs.putString(Constants.SP_KEYS.FONT_SIZE, item)
                when {
                    item.equals("Small") -> {
                        lyricsTExt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
                    }
                    item.equals("Large") -> {
                        lyricsTExt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)
                    }
                    else -> {
                        lyricsTExt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17f)
                    }
                }
            }
        }

        //Read Mode Spinner Logic
        val adapter1 = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            listOf("Day", "Night", "System Default")
        )
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        readSpinner.adapter = adapter1
        btnsheet.setOnClickListener {
            dialog.dismiss()
        }
        readSpinner.setSelection(themePosition)
        readSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val item = adapter1.getItem(position)
                Prefs.putString(Constants.SP_KEYS.THEME, item)

            }
        }

        //Brightness Logic

        var brightness = brightness
        seekBar.progress = brightness
        if(!canWrite){
            seekBar.isEnabled = false
            allowWritePermission()
        }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                if (canWrite){
                    setBrightness(progress)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        //Close Button Logic
        close.setOnClickListener {
            val item  = Prefs.getString(Constants.SP_KEYS.THEME)
            try {
                if (item.equals("Day")) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                } else if (item.equals("Night")) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            dialog.dismiss()
        }
        dialog.setCancelable(false)

        dialog.show()
    }

    /**
     * Sharing songs
     */

    private fun shareSong(song: String) {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, song);
        startActivity(Intent.createChooser(shareIntent, getString(R.string.send_to)))
    }

    /**
     * Copy songs to clipboard
     */
    private fun copyTextToClipboard(song: String) {
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("text", song)
        clipboardManager.setPrimaryClip(clipData)
        Toast.makeText(this, "Text copied to clipboard", Toast.LENGTH_LONG).show()
    }

    // Extension property to get write system settings permission status
    val Context.canWrite:Boolean
        get() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return Settings.System.canWrite(this)
            }else{
                return true
            }
        }

    // Extension function to allow write system settings
    fun Context.allowWritePermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val intent = Intent(
                Settings.ACTION_MANAGE_WRITE_SETTINGS,
                Uri.parse("package:$packageName")
            )
            startActivity(intent)
        }
    }

    // Extension property to get screen brightness programmatically
    val Context.brightness:Int
        get() {
            return Settings.System.getInt(
                this.contentResolver,
                Settings.System.SCREEN_BRIGHTNESS,
                0
            )
        }

    // Extension method to set screen brightness programmatically
    fun Context.setBrightness(value:Int):Unit{
        Settings.System.putInt(
            this.contentResolver,
            Settings.System.SCREEN_BRIGHTNESS,
            value
        )
    }


}