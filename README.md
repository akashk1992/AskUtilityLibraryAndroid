# AskUtilityLibraryAndroid
some common functions are written in this library that are used frequently

# Included Libraries in this library</br>
implementation 'com.android.support:appcompat-v7:27.1.1'</br>
compile 'com.google.code.gson:gson:2.8.2'</br>
compile 'com.squareup.retrofit2:retrofit:2.3.0'</br>
compile 'com.squareup.retrofit2:converter-gson:2.3.0'</br>
compile 'com.squareup.okhttp3:logging-interceptor:3.8.1'</br>
compile 'com.github.bumptech.glide:glide:4.6.1'</br>
annotationProcessor 'com.github.bumptech.glide:compiler:4.6.1'</br>
compile 'com.jakewharton:butterknife:8.8.1'</br>
annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.1'</br>

<pre>
<b>To Exclude Library :</b>
Example: 
compile('ask.com.askutilitylibraryandroid:ask-utility-library:1.3'){
exclude group:"com.google.code.gson", module:"gson"
}
</pre>

# Usage 
<b>NetworkUtils</b></br>
boolean isAvailable = NetworkUtils.isNetworkAvailable(this)

<b>AlertDialog</b></br>
AlertDialogUtils.SimpleAlertDialog(this, "Title", "message");</br>

AlertDialogUtils.SimpleAlertDialogFinishActivity(this, "Title", "message");</br>

<b>AlertDialog With Custom Style</b></br>
<i>[Note*: Only changes text and background color]</i></br>
AlertDialogUtils.SimpleAlertDialog(this, "Title", "message", R.style.myStyle);</br>

<pre>
<b>Other Utility Methods:</b>
<a href="https://github.com/akashk1992/AskUtilityLibraryAndroid/blob/master/app/src/main/java/ask/com/askutilitylibraryandroid/AskUtility.java">Check Here</a>
</pre>
