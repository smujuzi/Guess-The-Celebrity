package com.example.stuart.guessthecelebrity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    ImageView downloading;
    int current =0;
    ArrayList<String> links = new ArrayList<>();
    ArrayList<String> celebrities = new ArrayList<>();
    ArrayList<String> answers = new ArrayList<String>();
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    int locationOfCorrectAnswer;


    public class ImageDownloader extends AsyncTask<String, Void, Bitmap>
    {

        @Override
        protected Bitmap doInBackground(String... urls) {


            try
            {
                URL url = new URL(urls[0]);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream inputStream = connection.getInputStream();

                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);

                return myBitmap;

            }

            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }

            catch (IOException e)
            {
                e.printStackTrace();
            }

            return null;


        }
    }

    public void click (View view)
    {
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {

            Toast.makeText(this,"Correct!",Toast.LENGTH_SHORT).show();


        } else {

            Toast.makeText(this,"Wrong!",Toast.LENGTH_SHORT).show();

        }

        if( current == 9)
        {
            current = current - 9;

        }
        else
        {
            current++;
        }

        // https://upload.wikimedia.org/wikipedia/en/a/aa/Bart_Simpson_200px.png

        ImageDownloader task = new ImageDownloader();

        Bitmap myImage;

        try
        {
            //myImage = task.execute("https://upload.wikimedia.org/wikipedia/en/a/aa/Bart_Simpson_200px.png").get();
            myImage = task.execute(links.get(current)).get();
            downloading.setImageBitmap(myImage);
        }

        catch (Exception e)
        {
            e.printStackTrace();

        }


        generateOptions();

        Log.i("Bart", "Image present");


    }

    public void generateOptions()
    {
        Random rand = new Random();

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        int incorrectAnswer;

        for(int i=0 ; i<4; i++)
        {
            if(i == locationOfCorrectAnswer)
            {
                answers.add(celebrities.get(current));
            }

            else
            {
                incorrectAnswer = rand.nextInt(10);

                while ( incorrectAnswer == current)
                {
                    incorrectAnswer = rand.nextInt(10);
                }
                while(answers.contains(celebrities.get(incorrectAnswer)))
                {
                    incorrectAnswer = rand.nextInt(10);
                }

                answers.add(celebrities.get(incorrectAnswer));
            }
        }


        button0.setText(answers.get(0));
        button1.setText(answers.get(1));
        button2.setText(answers.get(2));
        button3.setText(answers.get(3));



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloading = (ImageView) findViewById(R.id.imageView);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);


        //**************************************************************
        //********************************STRING************************

        String list ="  <a class=\"\" href=\"/celebnetworth/politician/michael-bloomberg-net-worth/\">\n" +
                "                                <div class=\"img\">\n" +
                "                                                                                <div style=\"margin:0 auto;max-width:600px;\">\n" +
                "<div class=\"responsive-img img-size-networth-top-list\" >\n" +
                "    <picture>\n" +
                "        <!--[if IE 9]><video style=\"display: none;\"><![endif]-->\n" +
                "                                            <source media=\"(min-width: 0px) and (-webkit-min-device-pixel-ratio: 1.25), (min-width: 0px) and (min-resolution: 120dpi)\" sizes=\"60px\" data-srcset=\"https://static1.therichestimages.com/wordpress/wp-content/uploads/2012/06/Michael-Bloomberg.jpg?q=50&amp;fit=crop&amp;w=60&amp;h=60&dpr=1.5 90w\"/>\n" +
                "                                   <source media=\"(min-width: 0px)\" sizes=\"60px\" data-srcset=\"https://static1.therichestimages.com/wordpress/wp-content/uploads/2012/06/Michael-Bloomberg.jpg?q=50&amp;fit=crop&amp;w=60&amp;h=60 60w\"/>\n" +
                "                <!--[if IE 9]></video><![endif]-->\n" +
                "\n" +
                "                                    <img class=\"lazyload\" alt=\"\" />\n" +
                "                        </picture>\n" +
                "    \n" +
                "</div>\n" +
                "</div>\n" +
                "                                       \n" +
                "                                </div>Michael Bloomberg</a>\n" +
                "                        </td>\n" +
                "                        <td class=\"networth\" width=\"100\">$48.8 Billion</td>\n" +
                "                        <td class=\"age\">\n" +
                "                                                            77\n" +
                "                                                    </td>\n" +
                "                        <td class=\"country\">\n" +
                "                                                            United states\n" +
                "                                                    </td>\n" +
                "                    </tr>\n" +
                "                                    <tr>\n" +
                "                        <td class=\"rank\">\n" +
                "                            2\n" +
                "                        </td>\n" +
                "                        <td class=\"name\">\n" +
                "                            <a class=\"\" href=\"/celebnetworth/celebrity-business/men/rupert-murdoch-net-worth/\">\n" +
                "                                <div class=\"img\">\n" +
                "                                                                                <div style=\"margin:0 auto;max-width:600px;\">\n" +
                "<div class=\"responsive-img img-size-networth-top-list\" >\n" +
                "    <picture>\n" +
                "        <!--[if IE 9]><video style=\"display: none;\"><![endif]-->\n" +
                "                                            <source media=\"(min-width: 0px) and (-webkit-min-device-pixel-ratio: 1.25), (min-width: 0px) and (min-resolution: 120dpi)\" sizes=\"60px\" data-srcset=\"https://static3.therichestimages.com/wordpress/wp-content/uploads/2011/07/Rupert-Murdoch.jpg?q=50&amp;fit=crop&amp;w=60&amp;h=60&dpr=1.5 90w\"/>\n" +
                "                                   <source media=\"(min-width: 0px)\" sizes=\"60px\" data-srcset=\"https://static3.therichestimages.com/wordpress/wp-content/uploads/2011/07/Rupert-Murdoch.jpg?q=50&amp;fit=crop&amp;w=60&amp;h=60 60w\"/>\n" +
                "                <!--[if IE 9]></video><![endif]-->\n" +
                "\n" +
                "                                    <img class=\"lazyload\" alt=\"\" />\n" +
                "                        </picture>\n" +
                "    \n" +
                "</div>\n" +
                "</div>\n" +
                "                                       \n" +
                "                                </div>Rupert Murdoch</a>\n" +
                "                        </td>\n" +
                "                        <td class=\"networth\" width=\"100\">$18.6 Billion</td>\n" +
                "                        <td class=\"age\">\n" +
                "                                                            88\n" +
                "                                                    </td>\n" +
                "                        <td class=\"country\">\n" +
                "                                                            United states\n" +
                "                                                    </td>\n" +
                "                    </tr>\n" +
                "                                    <tr>\n" +
                "                        <td class=\"rank\">\n" +
                "                            3\n" +
                "                        </td>\n" +
                "                        <td class=\"name\">\n" +
                "                            <a class=\"\" href=\"/celebnetworth/celeb/media-mogul/donald-newhouse-net-worth/\">\n" +
                "                                <div class=\"img\">\n" +
                "                                                                                <div style=\"margin:0 auto;max-width:600px;\">\n" +
                "<div class=\"responsive-img img-size-networth-top-list\" >\n" +
                "    <picture>\n" +
                "        <!--[if IE 9]><video style=\"display: none;\"><![endif]-->\n" +
                "                                            <source media=\"(min-width: 0px) and (-webkit-min-device-pixel-ratio: 1.25), (min-width: 0px) and (min-resolution: 120dpi)\" sizes=\"60px\" data-srcset=\"https://static0.therichestimages.com/wordpress/wp-content/uploads/2012/06/donald-newhouse.jpg?q=50&amp;fit=crop&amp;w=60&amp;h=60&dpr=1.5 90w\"/>\n" +
                "                                   <source media=\"(min-width: 0px)\" sizes=\"60px\" data-srcset=\"https://static0.therichestimages.com/wordpress/wp-content/uploads/2012/06/donald-newhouse.jpg?q=50&amp;fit=crop&amp;w=60&amp;h=60 60w\"/>\n" +
                "                <!--[if IE 9]></video><![endif]-->\n" +
                "\n" +
                "                                    <img class=\"lazyload\" alt=\"\" />\n" +
                "                        </picture>\n" +
                "    \n" +
                "</div>\n" +
                "</div>\n" +
                "                                       \n" +
                "                                </div>Donald Newhouse</a>\n" +
                "                        </td>\n" +
                "                        <td class=\"networth\" width=\"100\">$13.3 Billion</td>\n" +
                "                        <td class=\"age\">\n" +
                "                                                            90\n" +
                "                                                    </td>\n" +
                "                        <td class=\"country\">\n" +
                "                                                            United states\n" +
                "                                                    </td>\n" +
                "                    </tr>\n" +
                "                                    <tr>\n" +
                "                        <td class=\"rank\">\n" +
                "                            4\n" +
                "                        </td>\n" +
                "                        <td class=\"name\">\n" +
                "                            <a class=\"\" href=\"/celebnetworth/politician/minister/silvio-berlusconi-net-worth/\">\n" +
                "                                <div class=\"img\">\n" +
                "                                                                                <div style=\"margin:0 auto;max-width:600px;\">\n" +
                "<div class=\"responsive-img img-size-networth-top-list\" >\n" +
                "    <picture>\n" +
                "        <!--[if IE 9]><video style=\"display: none;\"><![endif]-->\n" +
                "                                            <source media=\"(min-width: 0px) and (-webkit-min-device-pixel-ratio: 1.25), (min-width: 0px) and (min-resolution: 120dpi)\" sizes=\"60px\" data-srcset=\"https://static2.therichestimages.com/wordpress/wp-content/uploads/2011/04/Silvio-Berlusconi.jpg?q=50&amp;fit=crop&amp;w=60&amp;h=60&dpr=1.5 90w\"/>\n" +
                "                                   <source media=\"(min-width: 0px)\" sizes=\"60px\" data-srcset=\"https://static2.therichestimages.com/wordpress/wp-content/uploads/2011/04/Silvio-Berlusconi.jpg?q=50&amp;fit=crop&amp;w=60&amp;h=60 60w\"/>\n" +
                "                <!--[if IE 9]></video><![endif]-->\n" +
                "\n" +
                "                                    <img class=\"lazyload\" alt=\"\" />\n" +
                "                        </picture>\n" +
                "    \n" +
                "</div>\n" +
                "</div>\n" +
                "                                       \n" +
                "                                </div>Silvio Berlusconi</a>\n" +
                "                        </td>\n" +
                "                        <td class=\"networth\" width=\"100\">$7.68 Billion</td>\n" +
                "                        <td class=\"age\">\n" +
                "                                                            83\n" +
                "                                                    </td>\n" +
                "                        <td class=\"country\">\n" +
                "                                                            Italy\n" +
                "                                                    </td>\n" +
                "                    </tr>\n" +
                "                                    <tr>\n" +
                "                        <td class=\"rank\">\n" +
                "                            5\n" +
                "                        </td>\n" +
                "                        <td class=\"name\">\n" +
                "                            <a class=\"\" href=\"/celebnetworth/celeb/director/george-lucas-net-worth/\">\n" +
                "                                <div class=\"img\">\n" +
                "                                                                                <div style=\"margin:0 auto;max-width:600px;\">\n" +
                "<div class=\"responsive-img img-size-networth-top-list\" >\n" +
                "    <picture>\n" +
                "        <!--[if IE 9]><video style=\"display: none;\"><![endif]-->\n" +
                "                                            <source media=\"(min-width: 0px) and (-webkit-min-device-pixel-ratio: 1.25), (min-width: 0px) and (min-resolution: 120dpi)\" sizes=\"60px\" data-srcset=\"https://static1.therichestimages.com/wordpress/wp-content/uploads/2012/11/george-lucas.jpg?q=50&amp;fit=crop&amp;w=60&amp;h=60&dpr=1.5 90w\"/>\n" +
                "                                   <source media=\"(min-width: 0px)\" sizes=\"60px\" data-srcset=\"https://static1.therichestimages.com/wordpress/wp-content/uploads/2012/11/george-lucas.jpg?q=50&amp;fit=crop&amp;w=60&amp;h=60 60w\"/>\n" +
                "                <!--[if IE 9]></video><![endif]-->\n" +
                "\n" +
                "                                    <img class=\"lazyload\" alt=\"\" />\n" +
                "                        </picture>\n" +
                "    \n" +
                "</div>\n" +
                "</div>\n" +
                "                                       \n" +
                "                                </div>George Lucas</a>\n" +
                "                        </td>\n" +
                "                        <td class=\"networth\" width=\"100\">$7.62 Billion</td>\n" +
                "                        <td class=\"age\">\n" +
                "                                                            75\n" +
                "                                                    </td>\n" +
                "                        <td class=\"country\">\n" +
                "                                                            United states\n" +
                "                                                    </td>\n" +
                "                    </tr>\n" +
                "                                    <tr>\n" +
                "                        <td class=\"rank\">\n" +
                "                            6\n" +
                "                        </td>\n" +
                "                        <td class=\"name\">\n" +
                "                            <a class=\"\" href=\"/celebnetworth/celeb/ralph-lauren-net-worth/\">\n" +
                "                                <div class=\"img\">\n" +
                "                                                                                <div style=\"margin:0 auto;max-width:600px;\">\n" +
                "<div class=\"responsive-img img-size-networth-top-list\" >\n" +
                "    <picture>\n" +
                "        <!--[if IE 9]><video style=\"display: none;\"><![endif]-->\n" +
                "                                            <source media=\"(min-width: 0px) and (-webkit-min-device-pixel-ratio: 1.25), (min-width: 0px) and (min-resolution: 120dpi)\" sizes=\"60px\" data-srcset=\"https://static2.therichestimages.com/wordpress/wp-content/uploads/2014/05/52f81afc8b39c.jpg?q=50&amp;fit=crop&amp;w=60&amp;h=60&dpr=1.5 90w\"/>\n" +
                "                                   <source media=\"(min-width: 0px)\" sizes=\"60px\" data-srcset=\"https://static2.therichestimages.com/wordpress/wp-content/uploads/2014/05/52f81afc8b39c.jpg?q=50&amp;fit=crop&amp;w=60&amp;h=60 60w\"/>\n" +
                "                <!--[if IE 9]></video><![endif]-->\n" +
                "\n" +
                "                                    <img class=\"lazyload\" alt=\"\" />\n" +
                "                        </picture>\n" +
                "    \n" +
                "</div>\n" +
                "</div>\n" +
                "                                       \n" +
                "                                </div>Ralph Lauren</a>\n" +
                "                        </td>\n" +
                "                        <td class=\"networth\" width=\"100\">$5.41 Billion</td>\n" +
                "                        <td class=\"age\">\n" +
                "                                                            80\n" +
                "                                                    </td>\n" +
                "                        <td class=\"country\">\n" +
                "                                                            United states\n" +
                "                                                    </td>\n" +
                "                    </tr>\n" +
                "                                    <tr>\n" +
                "                        <td class=\"rank\">\n" +
                "                            7\n" +
                "                        </td>\n" +
                "                        <td class=\"name\">\n" +
                "                            <a class=\"\" href=\"/celebnetworth/celeb/director/steven-spielberg-net-worth/\">\n" +
                "                                <div class=\"img\">\n" +
                "                                                                                <div style=\"margin:0 auto;max-width:600px;\">\n" +
                "<div class=\"responsive-img img-size-networth-top-list\" >\n" +
                "    <picture>\n" +
                "        <!--[if IE 9]><video style=\"display: none;\"><![endif]-->\n" +
                "                                            <source media=\"(min-width: 0px) and (-webkit-min-device-pixel-ratio: 1.25), (min-width: 0px) and (min-resolution: 120dpi)\" sizes=\"60px\" data-srcset=\"https://static2.therichestimages.com/wordpress/wp-content/uploads/2011/03/steven-spielberg1.jpg?q=50&amp;fit=crop&amp;w=60&amp;h=60&dpr=1.5 90w\"/>\n" +
                "                                   <source media=\"(min-width: 0px)\" sizes=\"60px\" data-srcset=\"https://static2.therichestimages.com/wordpress/wp-content/uploads/2011/03/steven-spielberg1.jpg?q=50&amp;fit=crop&amp;w=60&amp;h=60 60w\"/>\n" +
                "                <!--[if IE 9]></video><![endif]-->\n" +
                "\n" +
                "                                    <img class=\"lazyload\" alt=\"\" />\n" +
                "                        </picture>\n" +
                "    \n" +
                "</div>\n" +
                "</div>\n" +
                "                                       \n" +
                "                                </div>Steven Spielberg</a>\n" +
                "                        </td>\n" +
                "                        <td class=\"networth\" width=\"100\">$5.41 Billion</td>\n" +
                "                        <td class=\"age\">\n" +
                "                                                            73\n" +
                "                                                    </td>\n" +
                "                        <td class=\"country\">\n" +
                "                                                            United states\n" +
                "                                                    </td>\n" +
                "                    </tr>\n" +
                "                                    <tr>\n" +
                "                        <td class=\"rank\">\n" +
                "                            8\n" +
                "                        </td>\n" +
                "                        <td class=\"name\">\n" +
                "                            <a class=\"\" href=\"/celebnetworth/celeb/director/joao-moreira-salles-net-worth/\">\n" +
                "                                <div class=\"img\">\n" +
                "                                                                                <div style=\"margin:0 auto;max-width:600px;\">\n" +
                "<div class=\"responsive-img img-size-networth-top-list\" >\n" +
                "    <picture>\n" +
                "        <!--[if IE 9]><video style=\"display: none;\"><![endif]-->\n" +
                "                                            <source media=\"(min-width: 0px) and (-webkit-min-device-pixel-ratio: 1.25), (min-width: 0px) and (min-resolution: 120dpi)\" sizes=\"60px\" data-srcset=\"https://static2.therichestimages.com/wordpress/wp-content/uploads/joao-moreira-salles_416x416.jpg?q=50&amp;fit=crop&amp;w=60&amp;h=60&dpr=1.5 90w\"/>\n" +
                "                                   <source media=\"(min-width: 0px)\" sizes=\"60px\" data-srcset=\"https://static2.therichestimages.com/wordpress/wp-content/uploads/joao-moreira-salles_416x416.jpg?q=50&amp;fit=crop&amp;w=60&amp;h=60 60w\"/>\n" +
                "                <!--[if IE 9]></video><![endif]-->\n" +
                "\n" +
                "                                    <img class=\"lazyload\" alt=\"\" />\n" +
                "                        </picture>\n" +
                "    \n" +
                "</div>\n" +
                "</div>\n" +
                "                                       \n" +
                "                                </div>Joao Moreira Salles</a>\n" +
                "                        </td>\n" +
                "                        <td class=\"networth\" width=\"100\">$5.33 Billion</td>\n" +
                "                        <td class=\"age\">\n" +
                "                                                            58\n" +
                "                                                    </td>\n" +
                "                        <td class=\"country\">\n" +
                "                                                            N/A\n" +
                "                                                    </td>\n" +
                "                    </tr>\n" +
                "                                    <tr>\n" +
                "                        <td class=\"rank\">\n" +
                "                            9\n" +
                "                        </td>\n" +
                "                        <td class=\"name\">\n" +
                "                            <a class=\"\" href=\"/celebnetworth/celeb/media-mogul/sumner-redstone-net-worth/\">\n" +
                "                                <div class=\"img\">\n" +
                "                                                                                <div style=\"margin:0 auto;max-width:600px;\">\n" +
                "<div class=\"responsive-img img-size-networth-top-list\" >\n" +
                "    <picture>\n" +
                "        <!--[if IE 9]><video style=\"display: none;\"><![endif]-->\n" +
                "                                            <source media=\"(min-width: 0px) and (-webkit-min-device-pixel-ratio: 1.25), (min-width: 0px) and (min-resolution: 120dpi)\" sizes=\"60px\" data-srcset=\"https://static3.therichestimages.com/wordpress/wp-content/uploads/2012/06/sumner-redstone.jpg?q=50&amp;fit=crop&amp;w=60&amp;h=60&dpr=1.5 90w\"/>\n" +
                "                                   <source media=\"(min-width: 0px)\" sizes=\"60px\" data-srcset=\"https://static3.therichestimages.com/wordpress/wp-content/uploads/2012/06/sumner-redstone.jpg?q=50&amp;fit=crop&amp;w=60&amp;h=60 60w\"/>\n" +
                "                <!--[if IE 9]></video><![endif]-->\n" +
                "\n" +
                "                                    <img class=\"lazyload\" alt=\"\" />\n" +
                "                        </picture>\n" +
                "    \n" +
                "</div>\n" +
                "</div>\n" +
                "                                       \n" +
                "                                </div>Sumner Redstone</a>\n" +
                "                        </td>\n" +
                "                        <td class=\"networth\" width=\"100\">$5.1 Billion</td>\n" +
                "                        <td class=\"age\">\n" +
                "                                                            96\n" +
                "                                                    </td>\n" +
                "                        <td class=\"country\">\n" +
                "                                                            United states\n" +
                "                                                    </td>\n" +
                "                    </tr>\n" +
                "                                    <tr>\n" +
                "                        <td class=\"rank\">\n" +
                "                            10\n" +
                "                        </td>\n" +
                "                        <td class=\"name\">\n" +
                "                            <a class=\"\" href=\"/celebnetworth/celeb/media-mogul/indu-jain-net-worth/\">\n" +
                "                                <div class=\"img\">\n" +
                "                                                                                <div style=\"margin:0 auto;max-width:600px;\">\n" +
                "<div class=\"responsive-img img-size-networth-top-list\" >\n" +
                "    <picture>\n" +
                "        <!--[if IE 9]><video style=\"display: none;\"><![endif]-->\n" +
                "                                            <source media=\"(min-width: 0px) and (-webkit-min-device-pixel-ratio: 1.25), (min-width: 0px) and (min-resolution: 120dpi)\" sizes=\"60px\" data-srcset=\"https://static3.therichestimages.com/wordpress/wp-content/uploads/2011/04/Indu_Jain.jpg?q=50&amp;fit=crop&amp;w=60&amp;h=60&dpr=1.5 90w\"/>\n" +
                "                                   <source media=\"(min-width: 0px)\" sizes=\"60px\" data-srcset=\"https://static3.therichestimages.com/wordpress/wp-content/uploads/2011/04/Indu_Jain.jpg?q=50&amp;fit=crop&amp;w=60&amp;h=60 60w\"/>\n" +
                "                <!--[if IE 9]></video><![endif]-->\n" +
                "\n" +
                "                                    <img class=\"lazyload\" alt=\"\" />\n" +
                "                        </picture>\n" +
                "    \n" +
                "</div>\n" +
                "</div>\n" +
                "                                       \n" +
                "                                </div>Indu Jain</a> " ;





        Pattern p = Pattern.compile("data-srcset=\"(.*?)90w\"");
        Matcher m = p.matcher(list);

        while (m.find())
        {
            System.out.println(m.group(1));
            links.add(m.group(1));
        }


        p = Pattern.compile("</div>(.*?)</a>");
        m = p.matcher(list);

        while (m.find())
        {
            System.out.println(m.group(1));
            celebrities.add(m.group(1));
        }
        //********************************STRING************************
        //**************************************************************

        ImageDownloader task = new ImageDownloader();

        Bitmap myImage;

        try
        {
            //myImage = task.execute("https://upload.wikimedia.org/wikipedia/en/a/aa/Bart_Simpson_200px.png").get();
            myImage = task.execute(links.get(current)).get();
            downloading.setImageBitmap(myImage);
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }


        generateOptions();


    }

}
