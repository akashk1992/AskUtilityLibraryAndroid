# Install
<b>gradle dependency</b></br>
<pre>
dependencies {
...
compile('ask.com.askutilitylibraryandroid:ask-utility-library:1.3')
...
}

allprojects {
    repositories {
        jcenter()
        ...
    }
}
</pre>
</br>

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

# Usage Network Check and Alert Dialogs 
<b>NetworkUtils</b></br>
boolean isAvailable = NetworkUtils.isNetworkAvailable(this)

<b>AlertDialog</b></br>
AlertDialogUtils.SimpleAlertDialog(this, "Title", "message");</br>

AlertDialogUtils.SimpleAlertDialogFinishActivity(this, "Title", "message");</br>

<b>AlertDialog With Custom Style</b></br>
<i>[Note*: Only changes text and background color]</i></br>
AlertDialogUtils.SimpleAlertDialog(this, "Title", "message", R.style.myStyle);</br>

# Usage Document Picker(*only files)
<pre>
documentPicker = new DocumentPicker(this);
documentPicker.startPicker(DOCUMENT_PICK_REQUEST);
</br>
@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == DOCUMENT_PICK_REQUEST) {
            try {
                String filePath = documentPicker.filePath(data);
                //FileUtils.openDoc(ExampleActivity.this, filePath); //To Open File
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
</pre>

# Usage Runtime Permission check
<pre>
 PermissionCheck permissionCheck = new PermissionCheck(this);
 permissionCheck.askPermissionIfMissing(STORAGE_PERMISSIONS_REQUEST,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_CONTACTS, Manifest.permission.ACCESS_FINE_LOCATION);
                
**Handle Permissions**
 switch (requestCode) {
            case STORAGE_PERMISSIONS_REQUEST:
                if ((grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            break;
 }
</pre>

<pre>
<b>Other Utility Methods:</b>
<a href="https://github.com/akashk1992/AskUtilityLibraryAndroid/blob/master/asklibrary/src/main/java/ask/com/asklibrary/AskUtility.java">Check Here</a>
</pre>

# Endless RecyclerScrollview
<pre>
scrollListener = new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (hasMoreData) {
                    if (!isLoadingMoreData) {
                        showProgress = false;
                        isLoadingMoreData = true;
                        Tasks.call(new Callable<Void>() {
                            @Override
                            public Void call() throws Exception {
                                recyclerAdapter.addLastItem();
                                return null;
                            }
                        });
                        pageNumber++;
                        getClients();
                    }
                }
            }
        };
mRecyclerView.addOnScrollListener(scrollListener);

public void addLastItem() {
//When load more data in progress
    clientsList.add(null);
    notifyItemInserted(getItemCount() - 1);
 }

public void removeLastItem() {
//When load more data complete
    clientsList.remove(getItemCount() - 1);
    notifyItemRemoved(getItemCount() - 1);
 }
 
 //When you want to reset pagination like on refresh page
scrollListener.resetState();


<b>In RecyclerAdapter</b>
 @Override
 public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     if (viewType == VIEW_TYPE_ITEM) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.clients_list_item, parent, false);
        return new MyViewHolder(itemView);
      } else {
      View loadingView = LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_item, parent, false);
      return new LoadingViewHolder(loadingView);
    }
 }
    
 @Override
 public int getItemViewType(int position) {
     return (clientsList.get(position) == null) ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
 }
 
[Note:* loading_item.xml file is included in app module]
</pre>
