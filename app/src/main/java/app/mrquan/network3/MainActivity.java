package app.mrquan.network3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import app.mrquan.network3.pojo.Book;
import app.mrquan.network3.pojo.Name;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .get()
//                .url("http://47.94.13.255:8080/quan")
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d("错误", "onFailure: "+e.toString());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
////                Log.d("消息回调", "onResponse: "+response.body().string());
//                List<Book> books = new Gson().fromJson(response.body().string(),new TypeToken<List<Book>>(){}.getType());
//                for (Book book:books){
//                    Log.d("返回书籍", "onResponse: "+book);
//                }
//            }
//        });

        Name name = new Name();
        name.setName("liu");
//        String json = "{\"name\":\"mmmmm\"}";
        String json = new Gson().toJson(name);
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),json);
        Request request = new Request.Builder()
                .url("http://47.94.13.255:8080/quan")
                .method("POST",requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("错误", "onFailure: "+e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                Log.d("消息回调", "onResponse: "+response.body().string());
                Book book = new Gson().fromJson(response.body().string(),Book.class);
                Log.d("返回数据", "onResponse: "+book);
            }
        });


    }
}
