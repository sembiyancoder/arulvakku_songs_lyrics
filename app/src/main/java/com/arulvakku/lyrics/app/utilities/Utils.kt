package com.arulvakku.lyrics.app.utilities

import android.content.Context
import com.arulvakku.lyrics.app.data.model.Song
import com.arulvakku.lyrics.app.data.model.SongCategory
import com.google.gson.Gson
import java.io.IOException


fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}


fun getSongCategories(): SongCategory {
    val temp: String =
        "{\"Version\": \"1.0\",\"LicensedBy\": \"Arulvakku Licence\",\"StatusCode\": 0,\"RequestUri\": null,\"Method\": null,\"IsTransactionDone\": true,\"Result\": [{\"sCategoryId\": 1,\"sCategory\": \"வருகைப்பாடல்\",\"sColorCode\": \"#ef0078\",\"sCount\": 183},{\"sCategoryId\": 2,\"sCategory\": \"திருப்பாடல்\",\"sColorCode\": \"#ee6002\",\"sCount\": 18},{\"sCategoryId\": 3,\"sCategory\": \"தியானப்பாடல்\",\"sColorCode\": \"#021aee\",\"sCount\": 87},{\"sCategoryId\": 4,\"sCategory\": \"காணிக்கைப்பாடல்\",\"sColorCode\": \"#1B5E20\",\"sCount\": 192},{\"sCategoryId\": 5,\"sCategory\": \"திருவிருந்துப்பாடல்\",\"sColorCode\": \"#0091EA\",\"sCount\": 360},{\"sCategoryId\": 6,\"sCategory\": \"நன்றிப்பாடல்\",\"sColorCode\": \"#F4511E\",\"sCount\": 103},{\"sCategoryId\": 7,\"sCategory\": \"திரு இதயப் பாடல்\",\"sColorCode\": \"#DD2C00\",\"sCount\": 9},{\"sCategoryId\": 8,\"sCategory\": \"தூய ஆவி பாடல்\",\"sColorCode\": \"#78909C\",\"sCount\": 32},{\"sCategoryId\": 10,\"sCategory\": \"மாதா பாடல்\",\"sColorCode\": \"#6200EA\",\"sCount\": 169},{\"sCategoryId\": 11,\"sCategory\": \"புனிதர்கள் பாடல்\",\"sColorCode\": \"#F50057\",\"sCount\": 74},{\"sCategoryId\": 12,\"sCategory\": \"அருங்கொடை பாடல்\",\"sColorCode\": \"#6D4C41\",\"sCount\": 37},{\"sCategoryId\": 13,\"sCategory\": \"ஒப்புரவு பாடல்\",\"sColorCode\": \"#3949AB\",\"sCount\": 8},{\"sCategoryId\": 14,\"sCategory\": \"திருப்பலிப் பாடல்\",\"sColorCode\": \"#1565C0\",\"sCount\": 15},{\"sCategoryId\": 15,\"sCategory\": \"கிறிஸ்து பிறப்பு பாடல்\",\"sColorCode\": \"#0097A7\",\"sCount\": 77},{\"sCategoryId\": 16,\"sCategory\": \"தவக்காலப் பாடல்\",\"sColorCode\": \"#43A047\",\"sCount\": 35},{\"sCategoryId\": 17,\"sCategory\": \"குருத்து ஞாயிறு பாடல்\",\"sColorCode\": \"#558B2F\",\"sCount\": 15},{\"sCategoryId\": 18,\"sCategory\": \"புனித வியாழன் பாடல்\",\"sColorCode\": \"#f47100\",\"sCount\": 17},{\"sCategoryId\": 19,\"sCategory\": \"புனித வெள்ளி பாடல்\",\"sColorCode\": \"#cd00ea\",\"sCount\": 30},{\"sCategoryId\": 20,\"sCategory\": \"உயிர்ப்பு ஞாயிறு பாடல்\",\"sColorCode\": \"#ff8d00\",\"sCount\": 34},{\"sCategoryId\": 21,\"sCategory\": \"பஜனைப் பாடல்\",\"sColorCode\": \"#ee6002\",\"sCount\": 6},{\"sCategoryId\": 22,\"sCategory\": \"திருமணப் பாடல்\",\"sColorCode\": \"#827717\",\"sCount\": 12},{\"sCategoryId\": 23,\"sCategory\": \"இறந்தோர் பாடல்\",\"sColorCode\": \"#ef0078\",\"sCount\": 20}]}";
    val gson = Gson()
    val obj: SongCategory = gson.fromJson(temp, SongCategory::class.java)
    return obj;
}

fun getSongs(): Song {
    val temp: String = "{\"Version\": \"1.0\",\"LicensedBy\": \"Arulvakku Licence\",\"StatusCode\": 0,\"RequestUri\": null,\"Method\": null,\"IsTransactionDone\": true,\"Result\": [{\"sSongId\": 1,\"sTitle\": \"அணி அணியாய் வாருங்கள் அன்பு மாந்தரே\",\"sCategory\": \"வருகைப்பாடல்\",\"sCategoryId\": 1,\"sSong\": \"அணி அணியாய் வாருங்கள் அன்பு மாந்தரே \\nஆண்டவர் இயேசுவின் சாட்சி நீங்களே (2)\\n\\n\\nஅன்புப்பணியாலே உலகை வெல்லுங்கள் \\nஇன்ப துன்பம் எதையும் தாங்கிடுங்கள் (2) \\nஎளியவர் வாழ்வில் துணைநின்று \\nஇயேசுவின் சாட்சியாய் நிலைத்திருங்கள் (2)\\n\\n\\nமண்ணகத்தில் பொருளைச் சேர்க்க வேண்டாம் \\nமறைந்து ஒழிந்து போய்விடுமே (2) \\nவிண்ணில் பொருளை தினம் சேர்த்து \\nஇயேசுவின் சாட்சியாய் நிலைத்திருங்கள் (2)\\n\\n\\n\"},{\"sSongId\": 2,\"sTitle\": \"அமைதி தேடி அலையும் நெஞ்சமே\",\"sCategory\": \"வருகைப்பாடல்\",\"sCategoryId\": 1,\"sSong\": \"அமைதி தேடி அலையும் நெஞ்சமே \\nஅனைத்தும் இங்கு அவரில் தஞ்சமே (2) \\nநிலையான சொந்தம் நீங்காத பந்தம் - 2 \\nஅவரன்றி வேறில்லையே\\n\\n\\nபோற்றுவேன் என் தேவனை பறைசாற்றுவேன் என் நாதனை \\nஎந்நாளுமே என் வாழ்விலே (2) \\nகாடுமேடு பள்ளம் என்று கால்கள் சோர்ந்து அலைந்த ஆடு \\nநாடுதே அது தேடுதே (2)\\n\\n\\nஇறைவனே என் இதயமே இந்த \\nஇயற்கையின் நல் இயக்கமே \\nஎன் தேவனே என் தலைவனே (2) \\nபரந்து விரிந்த உலகம் படைத்து சிறந்த படைப்பாய் \\nஎன்னைக் கண்ட தேவனே என் ஜீவனே (2)\"},{\"sSongId\": 3,\"sTitle\": \"அமைதியின் கருவியாய் ஆண்டவரே வருகின்றோம்\",\"sCategory\": \"வருகைப்பாடல்\",\"sCategoryId\": 1,\"sSong\": \"அமைதியின் கருவியாய் ஆண்டவரே வருகின்றோம் (2)\\nநெஞ்சுக்குள்ளே நீர் அமைத்த அன்பு என்னும்\\nஇல்லம் தன்னில் வளர்கின்றோம் - 2\\n\\n\\nநீயாக தந்த வாழ்க்கை இங்கு \\nநிலைமாறி போவதேனோ \\nமாறாத அருள் நேசம் \\nமன்றாடி கேட்கின்றேன் \\nஊருக்கு ஊரிங்கு போர்க்களங்கள் \\nஉள்ளுக்குள் உள்ளத்தில் போர்க்குணங்கள் \\nமாறும் காலம் காண வேண்டும் \\nமனித நேயம் வாழட்டும்\\n\\n\\nபேதங்கள் ஏதும் இல்லை \\nஎன்னும் வேதங்கள் இன்று வேண்டும் \\nஎல்லோரும் உன் பிள்ளைகள் \\nஇது இல்லையென்றால் நீரும் இல்லை \\nவானுக்கு மேல் உந்தன் வீடு இல்லை \\nபூமிக்கு கீழும் ஏதும் இல்லை \\nமனித இதயம் மாறும் போது \\nபுதிய அரசு பூமியில்\"},{\"sSongId\": 4,\"sTitle\": \"அர்ச்சனை மலராக ஆலயத்தில் வருகின்றோம்\",\"sCategory\": \"வருகைப்பாடல்\",\"sCategoryId\": 1,\"sSong\": \"அர்ச்சனை மலராக ஆலயத்தில் வருகின்றோம்\\nஆனந்தமாய் புகழ் கீதம் என்றும் பாடுவோம் (2)\\nஅர்ப்பணித்து வாழ்ந்திட அன்பர் உம்மில் வாழ்ந்திட\\nஆசையோடு அருள்வேண்டிப் பணிகின்றோம்.\\n\\n\\nதாயின் கருவிலே உருவாகுமுன்னரே \\nஅறிந்து எங்களை தேர்ந்த தெய்வமே \\nபாவியாயினும் பச்சைப் பிள்ளையாயினும் \\nஅர்ச்சித்திருக்கின்றீர், கற்பித்திருக்கின்றீர். \\nபிறரும் வாழ எங்கள் வாழ்வை கொடுக்க அழைக்கின்றீர் \\nஅஞ்சாதீர் என்று எம்மைக் காத்து வருகின்றீர்.\\n\\n\\nஉமது வார்த்தையை எங்கள் வாயில் ஊட்டினிர் \\nஉமது பாதையை எங்கள் பாதையாக்கினீர் \\nஉமது மாட்சியை என்னில் துலங்கச் செய்கின்றீர் \\nஉமது சாட்சியாய் நாங்கள் விளங்கச் சொல்கின்றீர் \\nஅழித்து ஒழிக்க கவிழ்த்து வீழ்த்த திட்டம் தீட்டினீர் \\nகட்டியெழுப்ப நட்டு வைக்க எம்மை அனுப்பினீர் \\nஅஞ்சாதீர் என்று எம்மைக் காத்து வருகின்றீர்\"}]}";
    val gson = Gson()
    val obj: Song = gson.fromJson(temp, Song::class.java)
    return obj;
}










