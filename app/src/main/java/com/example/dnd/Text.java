package com.example.dnd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;



public class Text extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        TextView desc = findViewById(R.id.textdnd);
        ConstraintLayout back = findViewById(R.id.back);
        ImageView btnback = findViewById(R.id.imageView);

        Bundle arguments = getIntent().getExtras();
        String name = arguments.get("name").toString();
        String obj = arguments.getString("obj");
        String loc = arguments.getString("loc");
        String set = arguments.getString("set");

        String[] lavsq = {"Раннее утро осеннего дня. По высокому небу то и дело пробегают облака. Дует небольшой ветерок." + name + "чувствует как ветер доносит запах моря до него. Прогуливаясь по площади" + name + "замечает старьевщика который толкает перед собой тележку. Вокруг него раскинулся блошиный рынок, и площадь полнится счастливыми голосами и яркими товарами. Вдруг " + name + " видит картину, на которой изображен" + obj + "." + name + " не может оторвать взгляд от него. В один миг" + name + "наблюдает что внезапно небо покрылось черными облаками, пошел проливной дождь. Прохожий случайно задевает плечо и " + name + " отводит взгляд от картины и возвращаетесь к реальности.","Идя по Даунтауну " + name + " начинает слышать музыку и счастливый детский смех. Свернув на площадь независимости " + name + " старается найти источник музыки. " + name + " оказываетесь на сцене на которой находится " + obj + ".",name + " в группе туристов осматриваете достопримечательности Даунтауна. Гид зазывает всех и просит обратить внимание на мемориал Основателям, заложившим город. " + name + " замечает ряд странных символов на бронзовой плите возле него. Внимательно осмотрев их " + name + " находит в них упоминая о " + obj };
        String[] lavok = {"Безмолвное утреннее небо окрашивается в цвет чеканной стали, а прохладный воздух гуляет по изломанным крышам Аркхема. " + name + " стоит перед киоском с плакатами местных постановок: 'Певец джаза', 'Лазарь смеялся', 'Антигона', 'Бархатные коготки'. Мимо идут прохожие горячо обсуждающие последний нашумевший мюзикл под названием  '" + obj + "'.","Душная тишина пропитывает ночной воздух. Обычно " + name + " расслабляется в такую погоду, но на улице очень сыро - что не редкость для этого времени года. Находясь на платформе вокзала " + name + " замечает что платформа скользкая от воды и водорослей. Проходя мимо, незнакомец вкладывает в руки " + obj + ". Он объясняет, что голоса, шепчущие из за порога, хотят чтобы этот предмет был у него.", "Вечернее небо усыпано звездами, на платформу на которой находится " + name + " падает тусклый свет луны.  Прохладный воздух гуляет по вокзалу Аркхема. " + name + " замечает поезд, прибывший на станцию с зеленой вспышкой, явно не из этого мира. В поезде никого нет, и " + obj + " собирается силами, чтобы исследовать его. В тамбуре " + name + " замечает " + obj,name + " прибыл утренним поездом на вокзал Аркхема. Он замечаете что поезда со временем прибытия 09:30, 10:00 и 10:30 прибывают на вокзал одновременно. " + name + " расспрашиваете пассажиров об их поездах и они подтверждают странность события. В этот момент " + name + " видит пассажира без багажа у которого " + obj + " в руках","Приближаясь к вокзалу в районе Нортсайда " + name + " замечает что прохожие горбятся и втягивают головы в плечи. С каждой минутой становится все труднее делать вид что все в порядке. " + name + " бежит на вокзал и наблюдает как незнакомцы в мантиях несут в руках " + obj};
        String[] lavblackhole = {"Мрачное осеннее небо района Ривертаун, пронизывает тусклый свет луны. " + name + " решил срезать путь до магазина и оказавшись около входа в черную пещеру " + name + " замечает фигур в мантии спрятавшись в тени " + name + " наблюдает за ритуалом. " + name + " ощущает ужас от магии ритуала. Собрав волю в кулак " + name + " мчится прочь от места риутала, случайно спотыкается о " + obj, "Бескрайняя чернота окутала район Ривертауна лишь тусклый свет от Магазина освещает пустую улицу. Идя по следам банды Шелдона " + name + " оказываетесь в черной пещере. " + name + " находит промокший дневник главаря банды. Исследуя его " + name  + " замечает что все записи зашифрованы, единственное что " + name + " смогл разобрать среди записей это слово " + obj, "Наступает ночь, голубая дымка дня поднимается, чтобы показать звезды. В спешке идя по Ривертауну в темноте черной пещеры " + name + " слышит страдальческий голос, шепчущий на иностранном языке. Прислушавшись " + name + "понимает что речь на немецком." + name + " понимает что разговор идет между упырями. Один из упырей говорит что не может найти свой " + obj,  "Душная тишина пропитывает ночной воздух. Обычно " + name + " расслабляется в такую погоду, но на улице очень сыро - что не редкость для этого времени года. Бродя по темным коридорам темной пещеры " + name + " замечает чернильные щупальца свидетельствующие о чужеродном присутствии. " + name + " замечает что вокруг сгущается тьма. Не теря времени " + name + " торопится покинуть это место пока не замечает " + name};

        String[] postzem = {"Отбившись от дикой собаки " + name + " пытается отдохнуть в развалинах дома. Осматривая территорию " + name + " находит " + obj + "." + name + " понимает что люди давно покинули эти края, всё что осталось - обугленные части домов."};
        String[] postobl = {"Раньше это было плавучей крепостью, но сейчас собирает в себе немногих выживших, у которых не осталось дома. " + name + " счастлившик которому повезло оказатся здесь. Сегодня " + name + " нашел в трюме " + obj};
        String[] postbun = { "Дом для всех, кто смог пережить ужасы прошлого. Тут могут жить те немногие, кто остался в живых после катастрофы. " + name + " один из счастливчиков который попал сюда. Единственное его богатство это " + obj + " подаренный ему его отцом"};
        String[] postrun = {"Заброшенные, частично разрушенные города, предприятия, военные объекты. Наследие былой цивилизации. " + name + " сталкер в этом мире разрухи. " + name + " уже неделю рышет в руинах старого научного города в поисках " + obj};
        String[] postpust = {"Обширная пустыня на месте ранее заселённых территорий. Многокилометровый пустыня, незаходящая за горизонт. " + name + " сталкер в этом мире разрухи. " + name + " уже неделю рышет в руинах старого научного города в посиках " + obj};

        String[] fanles = {"Луна уже давно закрепилась на небесном полотне, сияя во все стороны. Лес тихо шептал свои секреты, ветер осторожно обвивал стволы деревьев и уносил их зеленые листья. В чаще настолько прохладно и спокойно, что хочется остаться там навечно.", "Темное звездное небо завораживало. Миллиарды звезд медленно плыли, тихо наблюдая за всеми. Шелест листьев и совсем бесшумное шуршание лесных обитателей. Где-то упала падающая звезда. Наверно свидетели этого события уже успели загадать желание. Вроде бы такая тишь..", "В этот магический лес есть дверь, которая появляется в разных местах, но от неё нужен ключ. Ключ спрятан где-то в лесу.Но никто его не нашёл, может быть этой части леса и не существует. О ней ходят только легенды. И там обитают волшебные существа.", "В лесу находится речка, которая переходит в пруд. А чтобы перейти через реку есть красивый мост."};
        String[] fanzam = {"Старинный замок, стоящий на возвышенности, серые каменные стены, массивные кованые решетки на узких окнах пугают и манят. Замок тайн, хранящихся в его подвалах. Ночью в них зажигается яркий, манящий свет. Днем его строения утопают в яркой зелени. С фасада простирается прекрасный розарий. В нем представлены розы различных цветов и сортов, есть даже редкие черные. Мало кто знает, что эти благородные цветы, служат не только украшением, но и для благоухания.", "Замок кажется загадочным и опасным в своем архитектурном величии, но, если пройти по извилистой дороге, приблизимся к двери, ощущение опасности меняется благоговением к мощи каменных стен. От огромных каменных серых стен веет холодом. Замок кажется опасным и величественным."};
        String[] fantav = {"Само четырехэтажное здание было выложено из огромных камней и рассчитано даже на дракона. Последний этаж венчал бревенчатый сруб с покатой крышей. На флигеле крутился привязанный за хвост бес, оглашающий округу руладами своего богатырского храпа", "Таверна была выстроена из необтесанных валунов, скрепленных большими слоями известковой смеси. Чем-то это здание напоминало крепость, столь же большой и несокрушимой казалась таверна. Окна были лишь на втором этаже. Большие и застланные крепкими решетками они не позволяли ворам проникнуть внутрь. Между толстыми металлическими ставнями были стенка совсем темные снаружи, так что не увидишь, происходящего внутри. Среди больших окон можно было найти несколько обычных, и сразу бросалось в глаза совсем свежая работа по камню. Эту часть таверны построили недавно. У входа в таверну стоял привратник и приветливо встречал всех вошедших.", "Кабачок «У моря» вовсе не располагался у побережья, нет. Даже от портового района его отделяли многие-многие кварталы. Это была почти что заурядная таверна, потерянная среди узких улочек Великолепного Феникса. Просто покойный ее хозяин, бывший корабельный повар, то бишь кок, как он себя называл, получив таверну в приданое за женой, осел, но тоска по качке и соленому ветру взяла свое, и в итоге внешне кабак превратился в нечто столь же уютное, сколь и странное."};
        String[] fanpod = {"Сырое подземелье, еще не стерлись шаги прошлых участников экспедиции. Паутина располагается по всему потолку, она сдерживают сталактиты от падения."};
        String[] fanrat = {"В ратуше обычно у нас находилась резиденция мэра и тюрьма, рядом стояли войска, поскольку в ней находилась городская казна. На башне ратуши стояли часы. " + name + " стоял перед воротами держа в руках " + obj};

        String randomStr1 = lavsq[new Random().nextInt(lavsq.length)];
        String randomStr2 = lavok[new Random().nextInt(lavok.length)];
        String randomStr3 = lavblackhole[new Random().nextInt(lavblackhole.length)];

        String randomStr4 = postzem[new Random().nextInt(postzem.length)];
        String randomStr5 = postobl[new Random().nextInt(postobl.length)];
        String randomStr6 = postbun[new Random().nextInt(postbun.length)];
        String randomStr7 = postrun[new Random().nextInt(postrun.length)];
        String randomStr8 = postpust[new Random().nextInt(postpust.length)];

        String randomStr9 = fanles[new Random().nextInt(fanles.length)];
        String randomStr10 = fanzam[new Random().nextInt(fanzam.length)];
        String randomStr11 = fantav[new Random().nextInt(fantav.length)];
        String randomStr12 = fanpod[new Random().nextInt(fanpod.length)];
        String randomStr13 = fanrat[new Random().nextInt(fanrat.length)];

        String ifset = "";
        if((loc.equals("Вокзал"))){
            back.setBackgroundResource(R.drawable.vok);
            back.getBackground().setAlpha(150);
            ifset = randomStr2; }
        if((loc.equals("Площадь независимости"))){
            back.setBackgroundResource(R.drawable.sq);
            back.getBackground().setAlpha(150);
            ifset = randomStr1; }
        if((loc.equals("Черная пещера"))){
            back.setBackgroundResource(R.drawable.blackhole);
            back.getBackground().setAlpha(150);
            ifset = randomStr3; }
        if((loc.equals("Выжженная земля"))){
            back.setBackgroundResource(R.drawable.zem);
            back.getBackground().setAlpha(150);
            ifset = randomStr4; }
        if((loc.equals("Обломки корабля"))){
            back.setBackgroundResource(R.drawable.oblkor);
            back.getBackground().setAlpha(150);
            ifset = randomStr5; }
        if((loc.equals("Бункер"))){
            back.setBackgroundResource(R.drawable.bun);
            back.getBackground().setAlpha(150);
            ifset = randomStr6; }
        if((loc.equals("Руины"))){
            back.setBackgroundResource(R.drawable.run);
            back.getBackground().setAlpha(150);
            ifset = randomStr7; }
        if((loc.equals("Пустошь"))){
            back.setBackgroundResource(R.drawable.pust);
            back.getBackground().setAlpha(150);
            ifset = randomStr8; }
        if((loc.equals("Лес"))){
            back.setBackgroundResource(R.drawable.les);
            back.getBackground().setAlpha(150);
            ifset = randomStr9; }
        if((loc.equals("Замок"))){
            back.setBackgroundResource(R.drawable.castle);
            back.getBackground().setAlpha(150);
            ifset = randomStr10; }
        if((loc.equals("Таверна"))){
            back.setBackgroundResource(R.drawable.tav);
            back.getBackground().setAlpha(150);
            ifset = randomStr11; }
        if((loc.equals("Подземелье"))){
            back.setBackgroundResource(R.drawable.podz);
            back.getBackground().setAlpha(150);
            ifset = randomStr12; }
        if((loc.equals("Ратуша"))){
            back.setBackgroundResource(R.drawable.rar);
            back.getBackground().setAlpha(150);
            ifset = randomStr13; }
        String setset = ifset;
        desc.setText(setset);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Text.this, MainActivity.class);
                startActivity(intent); finish();
            }
        });


        class http extends AsyncTask<String, Void, Boolean> {
                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                    }

                    @Override
                    protected Boolean doInBackground(String... urls) {

                        String urladr = "http://45.129.200.47:3040/";
                        URL url;
                        HttpURLConnection httpURLConnection = null;
                        OutputStream os = null;
                        InputStreamReader isR = null;
                        BufferedReader bfR = null;
                        StringBuilder stringBuilder = new StringBuilder();

                        try {
                            //отправка сообщения
                            Map<String, String> postargs = new HashMap<>();
                            postargs.put("name", "bob");
                            postargs.put("pass", "123");

                            byte[] out = postargs.toString().getBytes();

                            url = new URL(urladr);
                            httpURLConnection = (HttpURLConnection) url.openConnection();
                            httpURLConnection.setRequestMethod("GET");
                            httpURLConnection.setDoOutput(true);
                            httpURLConnection.setDoInput(true);

                            httpURLConnection.addRequestProperty("User-Agent", "Mozilla/5.0");
                            httpURLConnection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                            httpURLConnection.setConnectTimeout(500);
                            httpURLConnection.setReadTimeout(500);
                            httpURLConnection.connect();

                            os = httpURLConnection.getOutputStream();
                            os.write(out);

                            if (HttpURLConnection.HTTP_OK == httpURLConnection.getResponseCode()) {
                                isR = new InputStreamReader(httpURLConnection.getInputStream());
                                bfR = new BufferedReader(isR);
                                String line;
                                while ((line = bfR.readLine()) != null) {
                                    stringBuilder.append(line);
                                }
                            }

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Boolean result) {
                    }
                }

                desc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        http httptask = new http();
                        httptask.execute();
                    }
                });

        }}
