package br.unicamp.ft.ulisses.aula11_drawer2019;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

class MyImageLoaderAsyncTask extends AsyncTask<String, Bitmap, Void> {
private ImageView imageView;
    public MyImageLoaderAsyncTask(ImageView imageView) {
        this.imageView = imageView;
    }



    public static Bitmap baixarImagem(String url) throws IOException {
        URL endereco;
        InputStream inputStream;
        Bitmap imagem;

        endereco = new URL(url);
        inputStream = endereco.openStream();
        imagem = BitmapFactory.decodeStream(inputStream);

        inputStream.close();

        return imagem;


    }


    @Override
    protected Void doInBackground(String... strings) {
        return null;
    }
}
