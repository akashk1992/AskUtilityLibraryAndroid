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

# Usage 
<b>NetworkUtils</b></br>
boolean isAvailable = NetworkUtils.isNetworkAvailable(this)

<b>AlertDialog</b></br>
AlertDialogUtils.SimpleAlertDialog(this, "Title", "message");</br>

AlertDialogUtils.SimpleAlertDialogFinishActivity(this, "Title", "message");</br>

<b>AlertDialog With Custom Style</b></br>
<i>[Note*: Only changes text and background color]</i></br>
AlertDialogUtils.SimpleAlertDialog(this, "Title", "message", R.style.myStyle);</br>
