package timefeel.com.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import okhttp3.Cache;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


import okio.BufferedSink;
import okio.BufferedSource;
import okio.GzipSink;
import okio.GzipSource;
import okio.Okio;
import okio.Sink;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import timefeel.com.BuildConfig;
import timefeel.com.R;
import timefeel.com.adapter.MoviesAdapter;
import timefeel.com.model.Movie;
import timefeel.com.model.MoviesResponse;
import timefeel.com.rest.ServiceHelper;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public String str;

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String APIKEY = BuildConfig.API_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/


        // TEST OKIO
        /*writeCachFile("testfile");
        try {
            readCachFile();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        // test compression
        //zipWriteReadJakeSample();

        if(APIKEY.isEmpty()){
            Toast.makeText(getApplicationContext(), "APIKEY is not defined", Toast.LENGTH_SHORT).show();
        }

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //recyclerView.setAdapter(null);
        // check online offline
        // and use first level cache with retrofit
        // second level sqlite for persistence
        ServiceHelper.GetInstance().getTopRatedMovies(APIKEY)
        .enqueue(new Callback<MoviesResponse>() {

            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                int StatusCode = response.code();
                List<Movie> movies = response.body().getResults();
                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
                //Log.d(TAG, "Number of movies " + movies.size() + " -- " + "response Code " + StatusCode);
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    ////////////////////////////////////////////////////////////////////////////////////
    /// Okio read write file, don't use InputStream
    ///////////////////////////////////////////////////////////////////////////////
    /** * Write a file */
    private void writeCachFile(String str) {
        //open
        File myFile = new File(getCacheDir(), "myFile");
        try {
            //then write
            if (!myFile.exists()) {
                myFile.createNewFile();
            }
            BufferedSink okioBufferSink = Okio.buffer(Okio.sink(myFile));
            okioBufferSink.writeUtf8(str);
            //don't forget to close, otherwise nothing appears
            okioBufferSink.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Read the file you just created
     */
    private void readCachFile() throws IOException {
        //open
        File myFile = new File(getCacheDir(), "myFile");
        if(myFile.exists()) {

            try {
                BufferedSource okioBufferSrce = Okio.buffer(Okio.source(myFile));
                str = okioBufferSrce.readUtf8();
                Log.e("MainActivity", "readCachFile returns " + str);
                okioBufferSrce.close();
            } catch (IOException e) {
                Log.e("MainActivity", "FileNotFoundException occurs", e);
                str = " occurs, read the logs";
            } finally {
                    /*txvCach.setText(str);*/
            }
        }
    }

    /**
    *  GzipSink, GZipSource
    **/
    private void zipWriteReadJakeSample() {
        // Open
        File myFile = new File(getCacheDir(), "myJakeFile");
        try {
            // Then write
            if (!myFile.exists()) {
                myFile.createNewFile();
            }
            Sink fileSink = Okio.sink(myFile);
            Sink gzipSink = new GzipSink(fileSink);
            BufferedSink okioBufferSink = Okio.buffer(gzipSink);
            /*okioBufferSink.writeUtf8(str);
            Log.e("MainActivity", "buffer " + str);*/
            // Don't forget to close, otherwise nothing appears
            okioBufferSink.close();
            // Then read
            myFile = new File(getCacheDir(), "myJakeFile");
            GzipSource gzipSrc = new GzipSource(Okio.source(myFile));
            BufferedSource okioBufferSrce = Okio.buffer(gzipSrc);
            // if you want to see the zip stream
            // BufferedSource okioBufferSrce=Okio.buffer(Okio.source(myFile));
            /*result = okioBufferSrce.readUtf8();
            Log.e("MainActivity", "result" + result);*/
        } catch (FileNotFoundException e) {
            Log.e("MainActivity", "FileNotFoundException occurs", e);
        } catch (IOException e) {
            Log.e("MainActivity", "IOException occurs", e);
        } finally {
            /*txvJakeWharton.setText(str);*/
        }
    }

    ////////////////////////////////////////////////////////////////////
    /// Okhttp
    /////////////////////////////////////////////////////////////////

  /*  OkHttpClient client = null;
    private OkHttpClient getClient(){
            if(client == null){
                // Asign chache directory
                File myCacheDir = new File(getCacheDir(), "OkHttpCache");
                // create it
                int cachesize  = 1024 * 1024;
                Cache cachedir = new Cache(myCacheDir, cachesize);
                client = new OkHttpClient.Builder()
                        .cache(cachedir)
                        //.addInterceptor(getInterceptor())
                        .build();
            }
        return client;
        }*/

    /**
     * type GET
     *  */
    /*String urlGet = "http://jsonplaceholder.typicode.com/posts/1";
    private String getAStuff() throws IOException {
        Request request = new Request.Builder()
                .url(urlGet)
                .get()
                .build();
        //the synchronous way (Here it's ok we are in an AsyncTask already)
        Response response = getClient().newCall(request).execute();
        int httpStatusCode=response.code();
        String ret = response.body().string();
        //You can also have:
        //Reader reader=response.body().charStream();
        //InputStream stream=response.body().byteStream();
        //byte[] bytes=response.body().bytes();
        //But the best way, now you understand the OkIo
        //because no allocation, no more buffering
        //Source src=response.body().source();
        //you should always close the body to enhance recycling mechanism
        response.body().close();
        return ret;
    }*/

    /**
     * type POST
     *  */
   /* String urlPost="http://jsonplaceholder.typicode.com/posts";
    String json="data: {\n" +"    title: 'foo',\n" +"    body: 'bar',\n" +"    userId: 1\n" +"  }";
    MediaType JSON= MediaType.parse("application/json; charset=utf-8");

    private String postAStuff() throws IOException {
//            RequestBody body = RequestBody.create(JSON, file);
//            RequestBody body = RequestBody.create(JSON, byte[]);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(urlPost)
                .post(body)
                .build();

        Call postCall=getClient().newCall(request);
        Response response = postCall.execute(); //you have your response code
        int httpStatusCode=response.code();
        //your responce body
        String ret=response.body().string();
        //and a lot of others stuff...
        //you should always close the body to enhance recycling mechanism
        response.body().close();
        return ret;
    }*/
    /**
     * type POST
     *  */
   /* String urlPost="http://jsonplaceholder.typicode.com/posts";
    String json="data: {\n" +"    title: 'foo',\n" +"    body: 'bar',\n" +"    userId: 1\n" +"  }";
    MediaType JSON= MediaType.parse("application/json; charset=utf-8");

    private String postAStuff() throws IOException {

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(urlPost)
                .post(body)
                .build();
        Call postCall=getClient().newCall(request);
        postCall.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                // Failed
            }

            @Override
            public void onResponse(Response response) throws IOException {
                //Succeeded
            }
        });
    }*/
    /**
     * type PUT/DELETE
     *  */
    /*String urlPost="http://jsonplaceholder.typicode.com/posts";
    String json="data: {\n" +"    title: 'foo',\n" +"    body: 'bar',\n" +"    userId: 1\n" +"  }";
    MediaType JSON= MediaType.parse("application/json; charset=utf-8");

    private String postAStuff() throws IOException {

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(urlPost)
                .post(body)
                .build();
        Call postCall=getClient().newCall(request);
        postCall.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                // Failed
            }

            @Override
            public void onResponse(Response response) throws IOException {
                //Succeeded
            }
        });
    }*/

}
