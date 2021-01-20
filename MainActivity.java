package com.example.foodreview;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    int num_stars = 0;
    int service_stars = 0;
    boolean all_food = false;
    boolean all_fun = false;
    boolean all_life = false;
    String advanced_food = null;
    String advanced_fun = null;
    String advanced_lifestyle = null;
    String mealz = null;
    String prices = null;
    String category = null;
    String lifestyle_price = null;
    String food_type = null;
    String fun_category = null;
    String fun_price = null;
    String age_group = null;
    String food_comment_name = null;
    String food_comment_address = null;
    String food_comment_result_address = null;
    String food_comment_result_name = null;
    String life_comment_result_name = null;
    String life_comment_result_address = null;
    String fun_comment_result_name = null;
    String fun_comment_result_address = null;
    int food_comment_result_rating = 0;
    int food_comment_rating = 0;
    String life_comment_name = null;
    String life_comment_address = null;
    int life_comment_rating = 0;
    String fun_comment_name = null;
    String fun_comment_address = null;
    int fun_comment_rating = 0;
    Bitmap current = null;
    boolean food_pic = false;
    boolean lifestyle_pic = false;
    boolean fun_pic = false;
    SQLiteDatabase database_reviews = null;
    Cursor main_cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database_reviews = openOrCreateDatabase("DB", Context.MODE_PRIVATE, null);
        database_setup();
    }

    public void database_setup() {
        database_reviews.execSQL("drop table if exists Food");
        database_reviews.execSQL("drop table if exists Lifestyle");
        database_reviews.execSQL("drop table if exists Entertainment");
        database_reviews.execSQL("drop table if exists Single_Food");
        database_reviews.execSQL("drop table if exists Single_Lifestyle");
        database_reviews.execSQL("drop table if exists Single_Entertainment");
        database_reviews.execSQL("drop table if exists Food_Comments");
        database_reviews.execSQL("create table Food(Name text, Address text, Rating double, Meal text, Price text, Cuisine text, Photo blob, Reviews int, Average double)");
        database_reviews.execSQL("create table Single_Food(Name text, Address text, Rating int, Meal text, Price text, Cuisine text, Photo blob)");
        database_reviews.execSQL("create table Lifestyle(Name text, Address text, Rating double, Category text, Price text, Service double, Photo blob, Reviews int, Average double)");
        database_reviews.execSQL("create table Single_Lifestyle(Name text, Address text, Rating int, Category text, Price text, Service double, Photo blob)");
        database_reviews.execSQL("create table Entertainment(Name text, Address text, Rating double, Category text, Price text, AG text, Photo blob, Reviews int, Average double)");
        database_reviews.execSQL("create table Single_Entertainment(Name text, Address text, Rating int, Category text, Price text, AG text, Photo blob)");
        database_reviews.execSQL("create table Food_Comments(Name text, Address text, Rating int, Comment text)");
    }

    public void back_to_home(View view) {
        setContentView(R.layout.activity_main);
    }

    public void thrillz(View view) {
        setContentView(R.layout.thrillz);
    }

    public void back(View view) {
        setContentView(R.layout.activity_main);
    }

    public void food(View view) {
        setContentView(R.layout.mealz);
    }

    public void lifestyle(View view) {
        setContentView(R.layout.l_style);
    }

    public void entertainment(View view) {
        setContentView(R.layout.fun);
    }

    public void menu_back(View view) {
        setContentView(R.layout.thrillz);
    }

    public void food_results(View view) {
        setContentView(R.layout.top_food);
    }

    public void breakfast_results(View view) {
        all_food = false;
        Log.v("BREAKFAST", "IT'S BREAKFAST TIME");
        setContentView(R.layout.top_food);
        TextView name = findViewById(R.id.restaurant_one);
        TextView home = findViewById(R.id.address_one);
        TextView rating = findViewById(R.id.rating_one);
        TextView meal = findViewById(R.id.meal_one);
        TextView price = findViewById(R.id.price_one);
        TextView cuisine = findViewById(R.id.cuisine_one);
        TextView reviews = findViewById(R.id.food_reviews_one);
        ImageView foto = findViewById(R.id.r_one_pic);
        ImageView second_foto = findViewById(R.id.r_two_pic);
        ImageView third_foto = findViewById(R.id.r_three_pic);
        String breakfast = "Meal:Breakfast";
        String b_results = "select * from Food F where F.Meal = " + "'" + breakfast + "'" + "order by F.Average desc;";
        main_cursor = database_reviews.rawQuery(b_results, null);
        if (main_cursor.getCount() > 0) {
            main_cursor.moveToFirst();
            name.setText(main_cursor.getString(0));
            home.setText(main_cursor.getString(1));
            rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7));
            meal.setText(main_cursor.getString(3));
            price.setText(main_cursor.getString(4));
            cuisine.setText(main_cursor.getString(5));
            byte[] b_photo = main_cursor.getBlob(6);
            Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
            foto.setBackgroundResource(0);
            foto.setImageBitmap(picture);
            reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
            if (main_cursor.moveToNext() == true) {
                TextView second_name = findViewById(R.id.restaurant_two);
                TextView second_home = findViewById(R.id.address_two);
                TextView second_rating = findViewById(R.id.rating_two);
                TextView second_meal = findViewById(R.id.meal_two);
                TextView second_price = findViewById(R.id.price_two);
                TextView second_cuisine = findViewById(R.id.cuisine_two);
                TextView second_reviews = findViewById(R.id.food_reviews_two);
                second_name.setText(main_cursor.getString(0));
                second_home.setText(main_cursor.getString(1));
                second_rating.setText("Rating:" + main_cursor.getDouble(2)/ (double) main_cursor.getInt(7) + "");
                second_meal.setText(main_cursor.getString(3));
                second_price.setText(main_cursor.getString(4));
                second_cuisine.setText(main_cursor.getString(5));
                byte[] second_b_photo = main_cursor.getBlob(6);
                Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                second_foto.setBackgroundResource(0);
                second_foto.setImageBitmap(second_picture);
                second_reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                if (main_cursor.moveToNext() == true) {
                    TextView third_name = findViewById(R.id.restaurant_three);
                    TextView third_home = findViewById(R.id.address_three);
                    TextView third_rating = findViewById(R.id.rating_three);
                    TextView third_meal = findViewById(R.id.meal_three);
                    TextView third_price = findViewById(R.id.price_three);
                    TextView third_cuisine = findViewById(R.id.cuisine_three);
                    TextView third_reviews = findViewById(R.id.food_reviews_three);
                    third_name.setText(main_cursor.getString(0));
                    third_home.setText(main_cursor.getString(1));
                    third_rating.setText("Rating:" + main_cursor.getDouble(2)/ (double) main_cursor.getInt(7) + "");
                    third_meal.setText(main_cursor.getString(3));
                    third_price.setText(main_cursor.getString(4));
                    third_cuisine.setText(main_cursor.getString(5));
                    byte[] third_b_photo = main_cursor.getBlob(6);
                    Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                    third_foto.setBackgroundResource(0);
                    third_foto.setImageBitmap(third_picture);
                    third_reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                }
            }
        }
    }

    public void lunch_results(View view) {
        all_food = false;
        Log.v("LUNCH", "IT'S LUNCHTIME");
        setContentView(R.layout.top_food);
        TextView name = findViewById(R.id.restaurant_one);
        TextView home = findViewById(R.id.address_one);
        TextView rating = findViewById(R.id.rating_one);
        TextView meal = findViewById(R.id.meal_one);
        TextView price = findViewById(R.id.price_one);
        TextView cuisine = findViewById(R.id.cuisine_one);
        TextView reviews = findViewById(R.id.food_reviews_one);
        ImageView foto = findViewById(R.id.r_one_pic);
        ImageView second_foto = findViewById(R.id.r_two_pic);
        ImageView third_foto = findViewById(R.id.r_three_pic);
        String lunch = "Meal:Lunch";
        String b_results = "select * from Food F where F.Meal = " + "'" + lunch + "'" + "order by F.Average desc;";
        main_cursor = database_reviews.rawQuery(b_results, null);
        if (main_cursor.getCount() > 0) {
            main_cursor.moveToFirst();
            name.setText(main_cursor.getString(0));
            home.setText(main_cursor.getString(1));
            rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7));
            meal.setText(main_cursor.getString(3));
            price.setText(main_cursor.getString(4));
            cuisine.setText(main_cursor.getString(5));
            byte[] b_photo = main_cursor.getBlob(6);
            Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
            foto.setBackgroundResource(0);
            foto.setImageBitmap(picture);
            reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
            if (main_cursor.moveToNext() == true) {
                TextView second_name = findViewById(R.id.restaurant_two);
                TextView second_home = findViewById(R.id.address_two);
                TextView second_rating = findViewById(R.id.rating_two);
                TextView second_meal = findViewById(R.id.meal_two);
                TextView second_price = findViewById(R.id.price_two);
                TextView second_cuisine = findViewById(R.id.cuisine_two);
                TextView second_reviews = findViewById(R.id.food_reviews_two);
                second_name.setText(main_cursor.getString(0));
                second_home.setText(main_cursor.getString(1));
                second_rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7) + "");
                second_meal.setText(main_cursor.getString(3));
                second_price.setText(main_cursor.getString(4));
                second_cuisine.setText(main_cursor.getString(5));
                byte[] second_b_photo = main_cursor.getBlob(6);
                Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                second_foto.setBackgroundResource(0);
                second_foto.setImageBitmap(second_picture);
                second_reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                if (main_cursor.moveToNext() == true) {
                    TextView third_name = findViewById(R.id.restaurant_three);
                    TextView third_home = findViewById(R.id.address_three);
                    TextView third_rating = findViewById(R.id.rating_three);
                    TextView third_meal = findViewById(R.id.meal_three);
                    TextView third_price = findViewById(R.id.price_three);
                    TextView third_cuisine = findViewById(R.id.cuisine_three);
                    TextView third_reviews = findViewById(R.id.food_reviews_three);
                    third_name.setText(main_cursor.getString(0));
                    third_home.setText(main_cursor.getString(1));
                    third_rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7) + "");
                    third_meal.setText(main_cursor.getString(3));
                    third_price.setText(main_cursor.getString(4));
                    third_cuisine.setText(main_cursor.getString(5));
                    byte[] third_b_photo = main_cursor.getBlob(6);
                    Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                    third_foto.setBackgroundResource(0);
                    third_foto.setImageBitmap(third_picture);
                    third_reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                }
            }
        }
    }

    public void dinner_results(View view) {
        all_food = false;
        Log.v("DINNER", "IT'S DINNER TIME");
        setContentView(R.layout.top_food);
        TextView name = findViewById(R.id.restaurant_one);
        TextView home = findViewById(R.id.address_one);
        TextView rating = findViewById(R.id.rating_one);
        TextView meal = findViewById(R.id.meal_one);
        TextView price = findViewById(R.id.price_one);
        TextView cuisine = findViewById(R.id.cuisine_one);
        TextView reviews = findViewById(R.id.food_reviews_one);
        ImageView foto = findViewById(R.id.r_one_pic);
        ImageView second_foto = findViewById(R.id.r_two_pic);
        ImageView third_foto = findViewById(R.id.r_three_pic);
        String dinner = "Meal:Dinner";
        String b_results = "select * from Food F where F.Meal = " + "'" + dinner + "'" + "order by F.Average desc;";
        main_cursor = database_reviews.rawQuery(b_results, null);
        if (main_cursor.getCount() > 0) {
            main_cursor.moveToFirst();
            name.setText(main_cursor.getString(0));
            home.setText(main_cursor.getString(1));
            rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7));
            meal.setText(main_cursor.getString(3));
            price.setText(main_cursor.getString(4));
            cuisine.setText(main_cursor.getString(5));
            byte[] b_photo = main_cursor.getBlob(6);
            Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
            foto.setBackgroundResource(0);
            foto.setImageBitmap(picture);
            reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
            if (main_cursor.moveToNext() == true) {
                TextView second_name = findViewById(R.id.restaurant_two);
                TextView second_home = findViewById(R.id.address_two);
                TextView second_rating = findViewById(R.id.rating_two);
                TextView second_meal = findViewById(R.id.meal_two);
                TextView second_price = findViewById(R.id.price_two);
                TextView second_cuisine = findViewById(R.id.cuisine_two);
                TextView second_reviews = findViewById(R.id.food_reviews_two);
                second_name.setText(main_cursor.getString(0));
                second_home.setText(main_cursor.getString(1));
                second_rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7) + "");
                second_meal.setText(main_cursor.getString(3));
                second_price.setText(main_cursor.getString(4));
                second_cuisine.setText(main_cursor.getString(5));
                byte[] second_b_photo = main_cursor.getBlob(6);
                Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                second_foto.setBackgroundResource(0);
                second_foto.setImageBitmap(second_picture);
                second_reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                if (main_cursor.moveToNext() == true) {
                    TextView third_name = findViewById(R.id.restaurant_three);
                    TextView third_home = findViewById(R.id.address_three);
                    TextView third_rating = findViewById(R.id.rating_three);
                    TextView third_meal = findViewById(R.id.meal_three);
                    TextView third_price = findViewById(R.id.price_three);
                    TextView third_cuisine = findViewById(R.id.cuisine_three);
                    TextView third_reviews = findViewById(R.id.food_reviews_three);
                    third_name.setText(main_cursor.getString(0));
                    third_home.setText(main_cursor.getString(1));
                    third_rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7) + "");
                    third_meal.setText(main_cursor.getString(3));
                    third_price.setText(main_cursor.getString(4));
                    third_cuisine.setText(main_cursor.getString(5));
                    byte[] third_b_photo = main_cursor.getBlob(6);
                    Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                    third_foto.setBackgroundResource(0);
                    third_foto.setImageBitmap(third_picture);
                    third_reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                }
            }
        }
    }

    public void all_food_results(View view) {
        all_food = true;
        Log.v("ALL RESULTS", "ALL RESULTS");
        setContentView(R.layout.top_food);
        TextView name = findViewById(R.id.restaurant_one);
        TextView home = findViewById(R.id.address_one);
        TextView rating = findViewById(R.id.rating_one);
        TextView meal = findViewById(R.id.meal_one);
        TextView price = findViewById(R.id.price_one);
        TextView cuisine = findViewById(R.id.cuisine_one);
        TextView reviews = findViewById(R.id.food_reviews_one);
        ImageView foto = findViewById(R.id.r_one_pic);
        ImageView second_foto = findViewById(R.id.r_two_pic);
        ImageView third_foto = findViewById(R.id.r_three_pic);
        String b_results = "select * from Food F order by F.Average desc;";
        main_cursor = database_reviews.rawQuery(b_results, null);
        Log.v("COUNT", main_cursor.getCount() + "");
        if (main_cursor.getCount() > 0) {
            main_cursor.moveToFirst();
            name.setText(main_cursor.getString(0));
            home.setText(main_cursor.getString(1));
            rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7));
            meal.setText(main_cursor.getString(3));
            price.setText(main_cursor.getString(4));
            cuisine.setText(main_cursor.getString(5));
            byte[] b_photo = main_cursor.getBlob(6);
            Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
            foto.setBackgroundResource(0);
            foto.setImageBitmap(picture);
            reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
            if (main_cursor.moveToNext() == true) {
                TextView second_name = findViewById(R.id.restaurant_two);
                TextView second_home = findViewById(R.id.address_two);
                TextView second_rating = findViewById(R.id.rating_two);
                TextView second_meal = findViewById(R.id.meal_two);
                TextView second_price = findViewById(R.id.price_two);
                TextView second_cuisine = findViewById(R.id.cuisine_two);
                TextView second_reviews = findViewById(R.id.food_reviews_two);
                second_name.setText(main_cursor.getString(0));
                second_home.setText(main_cursor.getString(1));
                second_rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7) + "");
                second_meal.setText(main_cursor.getString(3));
                second_price.setText(main_cursor.getString(4));
                second_cuisine.setText(main_cursor.getString(5));
                byte[] second_b_photo = main_cursor.getBlob(6);
                Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                second_foto.setBackgroundResource(0);
                second_foto.setImageBitmap(second_picture);
                second_reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                if (main_cursor.moveToNext() == true) {
                    TextView third_name = findViewById(R.id.restaurant_three);
                    TextView third_home = findViewById(R.id.address_three);
                    TextView third_rating = findViewById(R.id.rating_three);
                    TextView third_meal = findViewById(R.id.meal_three);
                    TextView third_price = findViewById(R.id.price_three);
                    TextView third_cuisine = findViewById(R.id.cuisine_three);
                    TextView third_reviews = findViewById(R.id.food_reviews_three);
                    third_name.setText(main_cursor.getString(0));
                    third_home.setText(main_cursor.getString(1));
                    third_rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7) + "");
                    third_meal.setText(main_cursor.getString(3));
                    third_price.setText(main_cursor.getString(4));
                    third_cuisine.setText(main_cursor.getString(5));
                    byte[] third_b_photo = main_cursor.getBlob(6);
                    Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                    third_foto.setBackgroundResource(0);
                    third_foto.setImageBitmap(third_picture);
                    third_reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                }
            }
        }
    }

    public void grocery_results(View view) {
        all_life = false;
        Log.v("LIFESTYLE", "GROCERIES");
        setContentView(R.layout.top_life);
        TextView name = findViewById(R.id.life_name_one);
        TextView home = findViewById(R.id.life_address_one);
        TextView rating = findViewById(R.id.life_rating_one);
        TextView category = findViewById(R.id.life_category_one);
        TextView price = findViewById(R.id.life_price_one);
        TextView service = findViewById(R.id.life_service_one);
        TextView review = findViewById(R.id.life_review_one);
        ImageView foto = findViewById(R.id.life_one_pic);
        ImageView second_foto = findViewById(R.id.life_two_pic);
        ImageView third_foto = findViewById(R.id.life_three_pic);
        String grocery = "Category:Groceries";
        String grocery_result = "select * from Lifestyle L where L.Category = " + "'" + grocery + "'" + "order by L.Rating desc;";
        main_cursor = database_reviews.rawQuery(grocery_result, null);
        if (main_cursor.getCount() > 0) {
            main_cursor.moveToFirst();
            name.setText(main_cursor.getString(0));
            home.setText(main_cursor.getString(1));
            rating.setText("Rating:" + main_cursor.getDouble(2)/ (double) main_cursor.getInt(7));
            category.setText(main_cursor.getString(3));
            price.setText(main_cursor.getString(4));
            service.setText("Service:" + main_cursor.getDouble(5)/(double) main_cursor.getInt(7));
            byte[] b_photo = main_cursor.getBlob(6);
            Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
            foto.setBackgroundResource(0);
            foto.setImageBitmap(picture);
            review.setText("Number of Reviews:" + main_cursor.getInt(7));
            if (main_cursor.moveToNext() == true) {
                TextView second_name = findViewById(R.id.life_name_two);
                TextView second_home = findViewById(R.id.life_address_two);
                TextView second_rating = findViewById(R.id.life_rating_two);
                TextView second_category = findViewById(R.id.life_category_two);
                TextView second_price = findViewById(R.id.life_price_two);
                TextView second_service = findViewById(R.id.life_service_two);
                TextView second_review = findViewById(R.id.life_review_two);
                second_name.setText(main_cursor.getString(0));
                second_home.setText(main_cursor.getString(1));
                second_rating.setText("Rating:" + main_cursor.getInt(2)/ (double) main_cursor.getInt(7) + "");
                second_category.setText(main_cursor.getString(3));
                second_price.setText(main_cursor.getString(4));
                second_service.setText("Service:" + main_cursor.getDouble(5)/(double) main_cursor.getInt(7));
                byte[] second_b_photo = main_cursor.getBlob(6);
                Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                second_foto.setBackgroundResource(0);
                second_foto.setImageBitmap(second_picture);
                second_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                if (main_cursor.moveToNext() == true) {
                    TextView third_name = findViewById(R.id.life_name_three);
                    TextView third_home = findViewById(R.id.life_address_three);
                    TextView third_rating = findViewById(R.id.life_rating_three);
                    TextView third_category = findViewById(R.id.life_category_three);
                    TextView third_price = findViewById(R.id.life_price_three);
                    TextView third_service = findViewById(R.id.life_service_three);
                    TextView third_review = findViewById(R.id.life_review_three);
                    third_name.setText(main_cursor.getString(0));
                    third_home.setText(main_cursor.getString(1));
                    third_rating.setText("Rating:" + main_cursor.getInt(2)/ (double) main_cursor.getInt(7) + "");
                    third_category.setText(main_cursor.getString(3));
                    third_price.setText(main_cursor.getString(4));
                    third_service.setText("Service:" + main_cursor.getDouble(5)/(double) main_cursor.getInt(7));
                    byte[] third_b_photo = main_cursor.getBlob(6);
                    Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                    third_foto.setBackgroundResource(0);
                    third_foto.setImageBitmap(third_picture);
                    third_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                }
            }
        }
    }

    public void gas_results(View view) {
        all_life = false;
        Log.v("LIFESTYLE", "GROCERIES");
        setContentView(R.layout.top_life);
        TextView name = findViewById(R.id.life_name_one);
        TextView home = findViewById(R.id.life_address_one);
        TextView rating = findViewById(R.id.life_rating_one);
        TextView category = findViewById(R.id.life_category_one);
        TextView price = findViewById(R.id.life_price_one);
        TextView service = findViewById(R.id.life_service_one);
        TextView review = findViewById(R.id.life_review_one);
        ImageView foto = findViewById(R.id.life_one_pic);
        ImageView second_foto = findViewById(R.id.life_two_pic);
        ImageView third_foto = findViewById(R.id.life_three_pic);
        String gas = "Category:Gas";
        String grocery_result = "select * from Lifestyle L where L.Category = " + "'" + gas + "'" + "order by L.Rating desc;";
        main_cursor = database_reviews.rawQuery(grocery_result, null);
        if (main_cursor.getCount() > 0) {
            main_cursor.moveToFirst();
            name.setText(main_cursor.getString(0));
            home.setText(main_cursor.getString(1));
            rating.setText("Rating:" + main_cursor.getInt(2)/ (double) main_cursor.getInt(7));
            category.setText(main_cursor.getString(3));
            price.setText(main_cursor.getString(4));
            service.setText("Service:" + main_cursor.getDouble(5)/(double) main_cursor.getInt(7));
            byte[] b_photo = main_cursor.getBlob(6);
            Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
            foto.setBackgroundResource(0);
            foto.setImageBitmap(picture);
            review.setText("Number of Reviews:" + main_cursor.getInt(7));
            if (main_cursor.moveToNext() == true) {
                TextView second_name = findViewById(R.id.life_name_two);
                TextView second_home = findViewById(R.id.life_address_two);
                TextView second_rating = findViewById(R.id.life_rating_two);
                TextView second_category = findViewById(R.id.life_category_two);
                TextView second_price = findViewById(R.id.life_price_two);
                TextView second_service = findViewById(R.id.life_service_two);
                TextView second_review = findViewById(R.id.life_review_two);
                second_name.setText(main_cursor.getString(0));
                second_home.setText(main_cursor.getString(1));
                second_rating.setText("Rating:" + main_cursor.getInt(2)/ (double) main_cursor.getInt(7) + "");
                second_category.setText(main_cursor.getString(3));
                second_price.setText(main_cursor.getString(4));
                second_service.setText("Service:" + main_cursor.getDouble(5)/(double) main_cursor.getInt(7));
                byte[] second_b_photo = main_cursor.getBlob(6);
                Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                second_foto.setBackgroundResource(0);
                second_foto.setImageBitmap(second_picture);
                second_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                if (main_cursor.moveToNext() == true) {
                    TextView third_name = findViewById(R.id.life_name_three);
                    TextView third_home = findViewById(R.id.life_address_three);
                    TextView third_rating = findViewById(R.id.life_rating_three);
                    TextView third_category = findViewById(R.id.life_category_three);
                    TextView third_price = findViewById(R.id.life_price_three);
                    TextView third_service = findViewById(R.id.life_service_three);
                    TextView third_review = findViewById(R.id.life_review_three);
                    third_name.setText(main_cursor.getString(0));
                    third_home.setText(main_cursor.getString(1));
                    third_rating.setText("Rating:" + main_cursor.getInt(2)/ (double) main_cursor.getInt(7) + "");
                    third_category.setText(main_cursor.getString(3));
                    third_price.setText(main_cursor.getString(4));
                    third_service.setText("Service:" + main_cursor.getDouble(5)/(double) main_cursor.getInt(7));
                    byte[] third_b_photo = main_cursor.getBlob(6);
                    Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                    third_foto.setBackgroundResource(0);
                    third_foto.setImageBitmap(third_picture);
                    third_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                }
            }
        }
    }

    public void haircut_results(View view) {
        all_life = false;
        Log.v("LIFESTYLE", "GROCERIES");
        setContentView(R.layout.top_life);
        TextView name = findViewById(R.id.life_name_one);
        TextView home = findViewById(R.id.life_address_one);
        TextView rating = findViewById(R.id.life_rating_one);
        TextView category = findViewById(R.id.life_category_one);
        TextView price = findViewById(R.id.life_price_one);
        TextView service = findViewById(R.id.life_service_one);
        TextView review = findViewById(R.id.life_review_one);
        ImageView foto = findViewById(R.id.life_one_pic);
        ImageView second_foto = findViewById(R.id.life_two_pic);
        ImageView third_foto = findViewById(R.id.life_three_pic);
        String haircut = "Category:Haircut";
        String grocery_result = "select * from Lifestyle L where L.Category = " + "'" + haircut + "'" + "order by L.Rating desc;";
        main_cursor = database_reviews.rawQuery(grocery_result, null);
        if (main_cursor.getCount() > 0) {
            main_cursor.moveToFirst();
            name.setText(main_cursor.getString(0));
            home.setText(main_cursor.getString(1));
            rating.setText("Rating:" + main_cursor.getInt(2)/ (double) main_cursor.getInt(7));
            category.setText(main_cursor.getString(3));
            price.setText(main_cursor.getString(4));
            service.setText("Service:" + main_cursor.getDouble(5)/(double) main_cursor.getInt(7));
            byte[] b_photo = main_cursor.getBlob(6);
            Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
            foto.setBackgroundResource(0);
            foto.setImageBitmap(picture);
            review.setText("Number of Reviews:" + main_cursor.getInt(7));
            if (main_cursor.moveToNext() == true) {
                TextView second_name = findViewById(R.id.life_name_two);
                TextView second_home = findViewById(R.id.life_address_two);
                TextView second_rating = findViewById(R.id.life_rating_two);
                TextView second_category = findViewById(R.id.life_category_two);
                TextView second_price = findViewById(R.id.life_price_two);
                TextView second_service = findViewById(R.id.life_service_two);
                TextView second_review = findViewById(R.id.life_review_two);
                second_name.setText(main_cursor.getString(0));
                second_home.setText(main_cursor.getString(1));
                second_rating.setText("Rating:" + main_cursor.getInt(2)/ (double) main_cursor.getInt(7) + "");
                second_category.setText(main_cursor.getString(3));
                second_price.setText(main_cursor.getString(4));
                second_service.setText("Service:" + main_cursor.getDouble(5)/(double) main_cursor.getInt(7));
                byte[] second_b_photo = main_cursor.getBlob(6);
                Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                second_foto.setBackgroundResource(0);
                second_foto.setImageBitmap(second_picture);
                second_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                if (main_cursor.moveToNext() == true) {
                    TextView third_name = findViewById(R.id.life_name_three);
                    TextView third_home = findViewById(R.id.life_address_three);
                    TextView third_rating = findViewById(R.id.life_rating_three);
                    TextView third_category = findViewById(R.id.life_category_three);
                    TextView third_price = findViewById(R.id.life_price_three);
                    TextView third_service = findViewById(R.id.life_service_three);
                    TextView third_review = findViewById(R.id.life_review_three);
                    third_name.setText(main_cursor.getString(0));
                    third_home.setText(main_cursor.getString(1));
                    third_rating.setText("Rating:" + main_cursor.getInt(2)/ (double) main_cursor.getInt(7) + "");
                    third_category.setText(main_cursor.getString(3));
                    third_price.setText(main_cursor.getString(4));
                    third_service.setText("Service:" + main_cursor.getDouble(5)/(double) main_cursor.getInt(7));
                    byte[] third_b_photo = main_cursor.getBlob(6);
                    Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                    third_foto.setBackgroundResource(0);
                    third_foto.setImageBitmap(third_picture);
                    third_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                }
            }
        }
    }

    public void all_lifestyle_results(View view) {
        all_life = true;
        Log.v("LIFESTYLE", "GROCERIES");
        setContentView(R.layout.top_life);
        TextView name = findViewById(R.id.life_name_one);
        TextView home = findViewById(R.id.life_address_one);
        TextView rating = findViewById(R.id.life_rating_one);
        TextView category = findViewById(R.id.life_category_one);
        TextView price = findViewById(R.id.life_price_one);
        TextView service = findViewById(R.id.life_service_one);
        TextView review = findViewById(R.id.life_review_one);
        ImageView foto = findViewById(R.id.life_one_pic);
        ImageView second_foto = findViewById(R.id.life_two_pic);
        ImageView third_foto = findViewById(R.id.life_three_pic);
        String haircut = "Category:Haircut";
        String grocery_result = "select * from Lifestyle L order by L.Rating desc;";
        main_cursor = database_reviews.rawQuery(grocery_result, null);
        if (main_cursor.getCount() > 0) {
            main_cursor.moveToFirst();
            name.setText(main_cursor.getString(0));
            home.setText(main_cursor.getString(1));
            rating.setText("Rating:" + main_cursor.getInt(2)/ (double) main_cursor.getInt(7));
            category.setText(main_cursor.getString(3));
            price.setText(main_cursor.getString(4));
            service.setText("Service:" + main_cursor.getDouble(5)/(double) main_cursor.getInt(7));
            byte[] b_photo = main_cursor.getBlob(6);
            Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
            foto.setBackgroundResource(0);
            foto.setImageBitmap(picture);
            review.setText("Number of Reviews:" + main_cursor.getInt(7));
            if (main_cursor.moveToNext() == true) {
                TextView second_name = findViewById(R.id.life_name_two);
                TextView second_home = findViewById(R.id.life_address_two);
                TextView second_rating = findViewById(R.id.life_rating_two);
                TextView second_category = findViewById(R.id.life_category_two);
                TextView second_price = findViewById(R.id.life_price_two);
                TextView second_service = findViewById(R.id.life_service_two);
                TextView second_review = findViewById(R.id.life_review_two);
                second_name.setText(main_cursor.getString(0));
                second_home.setText(main_cursor.getString(1));
                second_rating.setText("Rating:" + main_cursor.getInt(2)/ (double) main_cursor.getInt(7) + "");
                second_category.setText(main_cursor.getString(3));
                second_price.setText(main_cursor.getString(4));
                second_service.setText("Service:" + main_cursor.getDouble(5)/(double) main_cursor.getInt(7));
                byte[] second_b_photo = main_cursor.getBlob(6);
                Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                second_foto.setBackgroundResource(0);
                second_foto.setImageBitmap(second_picture);
                second_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                if (main_cursor.moveToNext() == true) {
                    TextView third_name = findViewById(R.id.life_name_three);
                    TextView third_home = findViewById(R.id.life_address_three);
                    TextView third_rating = findViewById(R.id.life_rating_three);
                    TextView third_category = findViewById(R.id.life_category_three);
                    TextView third_price = findViewById(R.id.life_price_three);
                    TextView third_service = findViewById(R.id.life_service_three);
                    TextView third_review = findViewById(R.id.life_review_three);
                    third_name.setText(main_cursor.getString(0));
                    third_home.setText(main_cursor.getString(1));
                    third_rating.setText("Rating:" + main_cursor.getInt(2)/ (double) main_cursor.getInt(7) + "");
                    third_category.setText(main_cursor.getString(3));
                    third_price.setText(main_cursor.getString(4));
                    third_service.setText("Service:" + main_cursor.getDouble(5)/(double) main_cursor.getInt(7));
                    byte[] third_b_photo = main_cursor.getBlob(6);
                    Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                    third_foto.setBackgroundResource(0);
                    third_foto.setImageBitmap(third_picture);
                    third_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                }
            }
        }
    }

    public void movies_results(View view) {
        all_fun = false;
        Log.v("ENTERTAINMENT", "MOVIES");
        setContentView(R.layout.top_fun);
        TextView name = findViewById(R.id.fun_name_one);
        TextView home = findViewById(R.id.fun_address_one);
        TextView rating = findViewById(R.id.fun_rating_one);
        TextView category = findViewById(R.id.fun_category_one);
        TextView price = findViewById(R.id.fun_price_one);
        TextView age_group = findViewById(R.id.fun_age_group_one);
        TextView review = findViewById(R.id.fun_review_one);
        ImageView foto = findViewById(R.id.fun_one_pic);
        ImageView second_foto = findViewById(R.id.fun_two_pic);
        ImageView third_foto = findViewById(R.id.fun_three_pic);
        String movies = "Category:Movies";
        String grocery_result = "select * from Entertainment E where E.Category = " + "'" + movies + "'" + " order by E.Rating desc;";
        main_cursor = database_reviews.rawQuery(grocery_result, null);
        if (main_cursor.getCount() > 0) {
            main_cursor.moveToFirst();
            name.setText(main_cursor.getString(0));
            home.setText(main_cursor.getString(1));
            rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7));
            category.setText(main_cursor.getString(3));
            price.setText(main_cursor.getString(4));
            age_group.setText(main_cursor.getString(5));
            byte[] b_photo = main_cursor.getBlob(6);
            Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
            foto.setBackgroundResource(0);
            foto.setImageBitmap(picture);
            review.setText("Number of Reviews:" + main_cursor.getInt(7));
            if (main_cursor.moveToNext() == true) {
                TextView second_name = findViewById(R.id.fun_name_two);
                TextView second_home = findViewById(R.id.fun_address_two);
                TextView second_rating = findViewById(R.id.fun_rating_two);
                TextView second_category = findViewById(R.id.fun_category_two);
                TextView second_price = findViewById(R.id.fun_price_two);
                TextView second_age_group = findViewById(R.id.fun_age_group_two);
                TextView second_review = findViewById(R.id.fun_review_two);
                second_name.setText(main_cursor.getString(0));
                second_home.setText(main_cursor.getString(1));
                second_rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7) + "");
                second_category.setText(main_cursor.getString(3));
                second_price.setText(main_cursor.getString(4));
                second_age_group.setText(main_cursor.getString(5));
                byte[] second_b_photo = main_cursor.getBlob(6);
                Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                second_foto.setBackgroundResource(0);
                second_foto.setImageBitmap(second_picture);
                second_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                if (main_cursor.moveToNext() == true) {
                    TextView third_name = findViewById(R.id.fun_name_three);
                    TextView third_home = findViewById(R.id.fun_address_three);
                    TextView third_rating = findViewById(R.id.fun_rating_three);
                    TextView third_category = findViewById(R.id.fun_category_three);
                    TextView third_price = findViewById(R.id.fun_price_three);
                    TextView third_age_group = findViewById(R.id.fun_age_group_three);
                    TextView third_review = findViewById(R.id.fun_review_three);
                    third_name.setText(main_cursor.getString(0));
                    third_home.setText(main_cursor.getString(1));
                    third_rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7) + "");
                    third_category.setText(main_cursor.getString(3));
                    third_price.setText(main_cursor.getString(4));
                    third_age_group.setText(main_cursor.getString(5));
                    byte[] third_b_photo = main_cursor.getBlob(6);
                    Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                    third_foto.setBackgroundResource(0);
                    third_foto.setImageBitmap(third_picture);
                    third_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                }
            }
        }
    }

    public void shopping_results(View view) {
        all_fun = false;
        Log.v("ENTERTAINMENT", "MOVIES");
        setContentView(R.layout.top_fun);
        TextView name = findViewById(R.id.fun_name_one);
        TextView home = findViewById(R.id.fun_address_one);
        TextView rating = findViewById(R.id.fun_rating_one);
        TextView category = findViewById(R.id.fun_category_one);
        TextView price = findViewById(R.id.fun_price_one);
        TextView age_group = findViewById(R.id.fun_age_group_one);
        TextView review = findViewById(R.id.fun_review_one);
        ImageView foto = findViewById(R.id.fun_one_pic);
        ImageView second_foto = findViewById(R.id.fun_two_pic);
        ImageView third_foto = findViewById(R.id.fun_three_pic);
        String shopping = "Category:Shopping";
        String grocery_result = "select * from Entertainment E where E.Category = " + "'" + shopping + "'" + " order by E.Rating desc;";
        main_cursor = database_reviews.rawQuery(grocery_result, null);
        if (main_cursor.getCount() > 0) {
            main_cursor.moveToFirst();
            name.setText(main_cursor.getString(0));
            home.setText(main_cursor.getString(1));
            rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7));
            category.setText(main_cursor.getString(3));
            price.setText(main_cursor.getString(4));
            age_group.setText(main_cursor.getString(5));
            byte[] b_photo = main_cursor.getBlob(6);
            Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
            foto.setBackgroundResource(0);
            foto.setImageBitmap(picture);
            review.setText("Number of Reviews:" + main_cursor.getInt(7));
            if (main_cursor.moveToNext() == true) {
                TextView second_name = findViewById(R.id.fun_name_two);
                TextView second_home = findViewById(R.id.fun_address_two);
                TextView second_rating = findViewById(R.id.fun_rating_two);
                TextView second_category = findViewById(R.id.fun_category_two);
                TextView second_price = findViewById(R.id.fun_price_two);
                TextView second_age_group = findViewById(R.id.fun_age_group_two);
                TextView second_review = findViewById(R.id.fun_review_two);
                second_name.setText(main_cursor.getString(0));
                second_home.setText(main_cursor.getString(1));
                second_rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7) + "");
                second_category.setText(main_cursor.getString(3));
                second_price.setText(main_cursor.getString(4));
                second_age_group.setText(main_cursor.getString(5));
                byte[] second_b_photo = main_cursor.getBlob(6);
                Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                second_foto.setBackgroundResource(0);
                second_foto.setImageBitmap(second_picture);
                second_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                if (main_cursor.moveToNext() == true) {
                    TextView third_name = findViewById(R.id.fun_name_three);
                    TextView third_home = findViewById(R.id.fun_address_three);
                    TextView third_rating = findViewById(R.id.fun_rating_three);
                    TextView third_category = findViewById(R.id.fun_category_three);
                    TextView third_price = findViewById(R.id.fun_price_three);
                    TextView third_age_group = findViewById(R.id.fun_age_group_three);
                    TextView third_review = findViewById(R.id.fun_review_three);
                    third_name.setText(main_cursor.getString(0));
                    third_home.setText(main_cursor.getString(1));
                    third_rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7) + "");
                    third_category.setText(main_cursor.getString(3));
                    third_price.setText(main_cursor.getString(4));
                    third_age_group.setText(main_cursor.getString(5));
                    byte[] third_b_photo = main_cursor.getBlob(6);
                    Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                    third_foto.setBackgroundResource(0);
                    third_foto.setImageBitmap(third_picture);
                    third_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                }
            }
        }
    }

    public void recreation_results(View view) {
        all_fun = false;
        Log.v("ENTERTAINMENT", "MOVIES");
        setContentView(R.layout.top_fun);
        TextView name = findViewById(R.id.fun_name_one);
        TextView home = findViewById(R.id.fun_address_one);
        TextView rating = findViewById(R.id.fun_rating_one);
        TextView category = findViewById(R.id.fun_category_one);
        TextView price = findViewById(R.id.fun_price_one);
        TextView age_group = findViewById(R.id.fun_age_group_one);
        TextView review = findViewById(R.id.fun_review_one);
        ImageView foto = findViewById(R.id.fun_one_pic);
        ImageView second_foto = findViewById(R.id.fun_two_pic);
        ImageView third_foto = findViewById(R.id.fun_three_pic);
        String recreation = "Category:Recreation";
        String grocery_result = "select * from Entertainment E where E.Category = " + "'" + recreation + "'" + " order by E.Rating desc;";
        main_cursor = database_reviews.rawQuery(grocery_result, null);
        if (main_cursor.getCount() > 0) {
            main_cursor.moveToFirst();
            name.setText(main_cursor.getString(0));
            home.setText(main_cursor.getString(1));
            rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7));
            category.setText(main_cursor.getString(3));
            price.setText(main_cursor.getString(4));
            age_group.setText(main_cursor.getString(5));
            byte[] b_photo = main_cursor.getBlob(6);
            Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
            foto.setBackgroundResource(0);
            foto.setImageBitmap(picture);
            review.setText("Number of Reviews:" + main_cursor.getInt(7));
            if (main_cursor.moveToNext() == true) {
                TextView second_name = findViewById(R.id.fun_name_two);
                TextView second_home = findViewById(R.id.fun_address_two);
                TextView second_rating = findViewById(R.id.fun_rating_two);
                TextView second_category = findViewById(R.id.fun_category_two);
                TextView second_price = findViewById(R.id.fun_price_two);
                TextView second_age_group = findViewById(R.id.fun_age_group_two);
                TextView second_review = findViewById(R.id.fun_review_two);
                second_name.setText(main_cursor.getString(0));
                second_home.setText(main_cursor.getString(1));
                second_rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7) + "");
                second_category.setText(main_cursor.getString(3));
                second_price.setText(main_cursor.getString(4));
                second_age_group.setText(main_cursor.getString(5));
                byte[] second_b_photo = main_cursor.getBlob(6);
                Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                second_foto.setBackgroundResource(0);
                second_foto.setImageBitmap(second_picture);
                second_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                if (main_cursor.moveToNext() == true) {
                    TextView third_name = findViewById(R.id.fun_name_three);
                    TextView third_home = findViewById(R.id.fun_address_three);
                    TextView third_rating = findViewById(R.id.fun_rating_three);
                    TextView third_category = findViewById(R.id.fun_category_three);
                    TextView third_price = findViewById(R.id.fun_price_three);
                    TextView third_age_group = findViewById(R.id.fun_age_group_three);
                    TextView third_review = findViewById(R.id.fun_review_three);
                    third_name.setText(main_cursor.getString(0));
                    third_home.setText(main_cursor.getString(1));
                    third_rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7) + "");
                    third_category.setText(main_cursor.getString(3));
                    third_price.setText(main_cursor.getString(4));
                    third_age_group.setText(main_cursor.getString(5));
                    byte[] third_b_photo = main_cursor.getBlob(6);
                    Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                    third_foto.setBackgroundResource(0);
                    third_foto.setImageBitmap(third_picture);
                    third_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                }
            }
        }
    }

    public void all_entertainment_results(View view) {
        all_fun = true;
        Log.v("ENTERTAINMENT", "MOVIES");
        setContentView(R.layout.top_fun);
        TextView name = findViewById(R.id.fun_name_one);
        TextView home = findViewById(R.id.fun_address_one);
        TextView rating = findViewById(R.id.fun_rating_one);
        TextView category = findViewById(R.id.fun_category_one);
        TextView price = findViewById(R.id.fun_price_one);
        TextView age_group = findViewById(R.id.fun_age_group_one);
        TextView review = findViewById(R.id.fun_review_one);
        ImageView foto = findViewById(R.id.fun_one_pic);
        ImageView second_foto = findViewById(R.id.fun_two_pic);
        ImageView third_foto = findViewById(R.id.fun_three_pic);
        String grocery_result = "select * from Entertainment E order by E.Rating desc;";
        main_cursor = database_reviews.rawQuery(grocery_result, null);
        if (main_cursor.getCount() > 0) {
            main_cursor.moveToFirst();
            name.setText(main_cursor.getString(0));
            home.setText(main_cursor.getString(1));
            rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7));
            category.setText(main_cursor.getString(3));
            price.setText(main_cursor.getString(4));
            age_group.setText(main_cursor.getString(5));
            byte[] b_photo = main_cursor.getBlob(6);
            Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
            foto.setBackgroundResource(0);
            foto.setImageBitmap(picture);
            review.setText("Number of Reviews:" + main_cursor.getInt(7));
            if (main_cursor.moveToNext() == true) {
                TextView second_name = findViewById(R.id.fun_name_two);
                TextView second_home = findViewById(R.id.fun_address_two);
                TextView second_rating = findViewById(R.id.fun_rating_two);
                TextView second_category = findViewById(R.id.fun_category_two);
                TextView second_price = findViewById(R.id.fun_price_two);
                TextView second_age_group = findViewById(R.id.fun_age_group_two);
                TextView second_review = findViewById(R.id.fun_review_two);
                second_name.setText(main_cursor.getString(0));
                second_home.setText(main_cursor.getString(1));
                second_rating.setText("Rating:" + main_cursor.getInt(2) + "");
                second_category.setText(main_cursor.getString(3));
                second_price.setText(main_cursor.getString(4));
                second_age_group.setText(main_cursor.getString(5));
                byte[] second_b_photo = main_cursor.getBlob(6);
                Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                second_foto.setBackgroundResource(0);
                second_foto.setImageBitmap(second_picture);
                second_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                if (main_cursor.moveToNext() == true) {
                    TextView third_name = findViewById(R.id.fun_name_three);
                    TextView third_home = findViewById(R.id.fun_address_three);
                    TextView third_rating = findViewById(R.id.fun_rating_three);
                    TextView third_category = findViewById(R.id.fun_category_three);
                    TextView third_price = findViewById(R.id.fun_price_three);
                    TextView third_age_group = findViewById(R.id.fun_age_group_three);
                    TextView third_review = findViewById(R.id.fun_review_three);
                    third_name.setText(main_cursor.getString(0));
                    third_home.setText(main_cursor.getString(1));
                    third_rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7) + "");
                    third_category.setText(main_cursor.getString(3));
                    third_price.setText(main_cursor.getString(4));
                    third_age_group.setText(main_cursor.getString(5));
                    byte[] third_b_photo = main_cursor.getBlob(6);
                    Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                    third_foto.setBackgroundResource(0);
                    third_foto.setImageBitmap(third_picture);
                    third_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                }
            }
        }
    }

    public void l_back(View view) {
        setContentView(R.layout.thrillz);
    }

    public void life_results(View view) {
        setContentView(R.layout.top_life);
    }

    public void e_back(View view) {
        setContentView(R.layout.thrillz);
    }

    public void fun_results(View view) {
        setContentView(R.layout.top_fun);
    }

    public void food_r_back(View view) {
        setContentView(R.layout.mealz);
    }

    public void adv_food(View view) {
        TextView meal = findViewById(R.id.meal_one);
        advanced_food = meal.getText().toString();
        Log.v("FOOD:", advanced_food);
        setContentView(R.layout.adv_food_search);
    }

    public void advanced_price_search(View view) {
        setContentView(R.layout.food_price_search);
    }

    public void cheap_food(View view) {
        setContentView(R.layout.top_food);
        if (all_food == false) {
            TextView name = findViewById(R.id.restaurant_one);
            TextView home = findViewById(R.id.address_one);
            TextView rating = findViewById(R.id.rating_one);
            TextView meal = findViewById(R.id.meal_one);
            TextView price = findViewById(R.id.price_one);
            TextView cuisine = findViewById(R.id.cuisine_one);
            TextView reviews = findViewById(R.id.food_reviews_one);
            ImageView foto = findViewById(R.id.r_one_pic);
            ImageView second_foto = findViewById(R.id.r_two_pic);
            ImageView third_foto = findViewById(R.id.r_three_pic);
            String cheap = "Price:$0-$10";
            String b_results = "select * from Food F where F.Meal = " + "'" + advanced_food + "'" + "and F.Price = " + "'" + cheap + "'" + "order by F.Average desc;";
            main_cursor = database_reviews.rawQuery(b_results, null);
            if (main_cursor.getCount() > 0) {
                main_cursor.moveToFirst();
                name.setText(main_cursor.getString(0));
                home.setText(main_cursor.getString(1));
                rating.setText("Rating:" + main_cursor.getDouble(2) / (double) main_cursor.getInt(7));
                meal.setText(main_cursor.getString(3));
                price.setText(main_cursor.getString(4));
                cuisine.setText(main_cursor.getString(5));
                byte[] b_photo = main_cursor.getBlob(6);
                Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
                foto.setBackgroundResource(0);
                foto.setImageBitmap(picture);
                reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                if (main_cursor.moveToNext() == true) {
                    TextView second_name = findViewById(R.id.restaurant_two);
                    TextView second_home = findViewById(R.id.address_two);
                    TextView second_rating = findViewById(R.id.rating_two);
                    TextView second_meal = findViewById(R.id.meal_two);
                    TextView second_price = findViewById(R.id.price_two);
                    TextView second_cuisine = findViewById(R.id.cuisine_two);
                    TextView second_reviews = findViewById(R.id.food_reviews_two);
                    second_name.setText(main_cursor.getString(0));
                    second_home.setText(main_cursor.getString(1));
                    second_rating.setText("Rating:" + main_cursor.getDouble(2) / (double) main_cursor.getInt(7) + "");
                    second_meal.setText(main_cursor.getString(3));
                    second_price.setText(main_cursor.getString(4));
                    second_cuisine.setText(main_cursor.getString(5));
                    byte[] second_b_photo = main_cursor.getBlob(6);
                    Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                    second_foto.setBackgroundResource(0);
                    second_foto.setImageBitmap(second_picture);
                    second_reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                    if (main_cursor.moveToNext() == true) {
                        TextView third_name = findViewById(R.id.restaurant_three);
                        TextView third_home = findViewById(R.id.address_three);
                        TextView third_rating = findViewById(R.id.rating_three);
                        TextView third_meal = findViewById(R.id.meal_three);
                        TextView third_price = findViewById(R.id.price_three);
                        TextView third_cuisine = findViewById(R.id.cuisine_three);
                        TextView third_reviews = findViewById(R.id.food_reviews_three);
                        third_name.setText(main_cursor.getString(0));
                        third_home.setText(main_cursor.getString(1));
                        third_rating.setText("Rating:" + main_cursor.getDouble(2) / (double) main_cursor.getInt(7) + "");
                        third_meal.setText(main_cursor.getString(3));
                        third_price.setText(main_cursor.getString(4));
                        third_cuisine.setText(main_cursor.getString(5));
                        byte[] third_b_photo = main_cursor.getBlob(6);
                        Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                        third_foto.setBackgroundResource(0);
                        third_foto.setImageBitmap(third_picture);
                        third_reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                    }
                }
            }

        } else if (all_food == true) {
            TextView name = findViewById(R.id.restaurant_one);
            TextView home = findViewById(R.id.address_one);
            TextView rating = findViewById(R.id.rating_one);
            TextView meal = findViewById(R.id.meal_one);
            TextView price = findViewById(R.id.price_one);
            TextView cuisine = findViewById(R.id.cuisine_one);
            TextView reviews = findViewById(R.id.food_reviews_one);
            ImageView foto = findViewById(R.id.r_one_pic);
            ImageView second_foto = findViewById(R.id.r_two_pic);
            ImageView third_foto = findViewById(R.id.r_three_pic);
            String cheap = "Price:$0-$10";
            String b_results = "select * from Food F where F.Price = " + "'" + cheap + "'" + "order by F.Average desc;";
            main_cursor = database_reviews.rawQuery(b_results, null);
            if (main_cursor.getCount() > 0) {
                main_cursor.moveToFirst();
                name.setText(main_cursor.getString(0));
                home.setText(main_cursor.getString(1));
                rating.setText("Rating:" + main_cursor.getDouble(2) / (double) main_cursor.getInt(7));
                meal.setText(main_cursor.getString(3));
                price.setText(main_cursor.getString(4));
                cuisine.setText(main_cursor.getString(5));
                byte[] b_photo = main_cursor.getBlob(6);
                Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
                foto.setBackgroundResource(0);
                foto.setImageBitmap(picture);
                reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                if (main_cursor.moveToNext() == true) {
                    TextView second_name = findViewById(R.id.restaurant_two);
                    TextView second_home = findViewById(R.id.address_two);
                    TextView second_rating = findViewById(R.id.rating_two);
                    TextView second_meal = findViewById(R.id.meal_two);
                    TextView second_price = findViewById(R.id.price_two);
                    TextView second_cuisine = findViewById(R.id.cuisine_two);
                    TextView second_reviews = findViewById(R.id.food_reviews_two);
                    second_name.setText(main_cursor.getString(0));
                    second_home.setText(main_cursor.getString(1));
                    second_rating.setText("Rating:" + main_cursor.getDouble(2) / (double) main_cursor.getInt(7) + "");
                    second_meal.setText(main_cursor.getString(3));
                    second_price.setText(main_cursor.getString(4));
                    second_cuisine.setText(main_cursor.getString(5));
                    byte[] second_b_photo = main_cursor.getBlob(6);
                    Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                    second_foto.setBackgroundResource(0);
                    second_foto.setImageBitmap(second_picture);
                    second_reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                    if (main_cursor.moveToNext() == true) {
                        TextView third_name = findViewById(R.id.restaurant_three);
                        TextView third_home = findViewById(R.id.address_three);
                        TextView third_rating = findViewById(R.id.rating_three);
                        TextView third_meal = findViewById(R.id.meal_three);
                        TextView third_price = findViewById(R.id.price_three);
                        TextView third_cuisine = findViewById(R.id.cuisine_three);
                        TextView third_reviews = findViewById(R.id.food_reviews_three);
                        third_name.setText(main_cursor.getString(0));
                        third_home.setText(main_cursor.getString(1));
                        third_rating.setText("Rating:" + main_cursor.getDouble(2) / (double) main_cursor.getInt(7) + "");
                        third_meal.setText(main_cursor.getString(3));
                        third_price.setText(main_cursor.getString(4));
                        third_cuisine.setText(main_cursor.getString(5));
                        byte[] third_b_photo = main_cursor.getBlob(6);
                        Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                        third_foto.setBackgroundResource(0);
                        third_foto.setImageBitmap(third_picture);
                        third_reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                    }
                }
            }
        }
    }

    public void average_food(View view) {
        setContentView(R.layout.top_food);
        if (all_food == false) {
            TextView name = findViewById(R.id.restaurant_one);
            TextView home = findViewById(R.id.address_one);
            TextView rating = findViewById(R.id.rating_one);
            TextView meal = findViewById(R.id.meal_one);
            TextView price = findViewById(R.id.price_one);
            TextView cuisine = findViewById(R.id.cuisine_one);
            TextView reviews = findViewById(R.id.food_reviews_one);
            ImageView foto = findViewById(R.id.r_one_pic);
            ImageView second_foto = findViewById(R.id.r_two_pic);
            ImageView third_foto = findViewById(R.id.r_three_pic);
            String average = "Price:$10-$20";
            String b_results = "select * from Food F where F.Meal = " + "'" + advanced_food + "'" + "and F.Price = " + "'" + average + "'" + "order by F.Average desc;";
            main_cursor = database_reviews.rawQuery(b_results, null);
            if (main_cursor.getCount() > 0) {
                main_cursor.moveToFirst();
                name.setText(main_cursor.getString(0));
                home.setText(main_cursor.getString(1));
                rating.setText("Rating:" + main_cursor.getDouble(2) / (double) main_cursor.getInt(7));
                meal.setText(main_cursor.getString(3));
                price.setText(main_cursor.getString(4));
                cuisine.setText(main_cursor.getString(5));
                byte[] b_photo = main_cursor.getBlob(6);
                Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
                foto.setBackgroundResource(0);
                foto.setImageBitmap(picture);
                reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                if (main_cursor.moveToNext() == true) {
                    TextView second_name = findViewById(R.id.restaurant_two);
                    TextView second_home = findViewById(R.id.address_two);
                    TextView second_rating = findViewById(R.id.rating_two);
                    TextView second_meal = findViewById(R.id.meal_two);
                    TextView second_price = findViewById(R.id.price_two);
                    TextView second_cuisine = findViewById(R.id.cuisine_two);
                    TextView second_reviews = findViewById(R.id.food_reviews_two);
                    second_name.setText(main_cursor.getString(0));
                    second_home.setText(main_cursor.getString(1));
                    second_rating.setText("Rating:" + main_cursor.getDouble(2) / (double) main_cursor.getInt(7) + "");
                    second_meal.setText(main_cursor.getString(3));
                    second_price.setText(main_cursor.getString(4));
                    second_cuisine.setText(main_cursor.getString(5));
                    byte[] second_b_photo = main_cursor.getBlob(6);
                    Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                    second_foto.setBackgroundResource(0);
                    second_foto.setImageBitmap(second_picture);
                    second_reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                    if (main_cursor.moveToNext() == true) {
                        TextView third_name = findViewById(R.id.restaurant_three);
                        TextView third_home = findViewById(R.id.address_three);
                        TextView third_rating = findViewById(R.id.rating_three);
                        TextView third_meal = findViewById(R.id.meal_three);
                        TextView third_price = findViewById(R.id.price_three);
                        TextView third_cuisine = findViewById(R.id.cuisine_three);
                        TextView third_reviews = findViewById(R.id.food_reviews_three);
                        third_name.setText(main_cursor.getString(0));
                        third_home.setText(main_cursor.getString(1));
                        third_rating.setText("Rating:" + main_cursor.getDouble(2) / (double) main_cursor.getInt(7) + "");
                        third_meal.setText(main_cursor.getString(3));
                        third_price.setText(main_cursor.getString(4));
                        third_cuisine.setText(main_cursor.getString(5));
                        byte[] third_b_photo = main_cursor.getBlob(6);
                        Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                        third_foto.setBackgroundResource(0);
                        third_foto.setImageBitmap(third_picture);
                        third_reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                    }
                }
            }

        } else if (all_food == true) {
            TextView name = findViewById(R.id.restaurant_one);
            TextView home = findViewById(R.id.address_one);
            TextView rating = findViewById(R.id.rating_one);
            TextView meal = findViewById(R.id.meal_one);
            TextView price = findViewById(R.id.price_one);
            TextView cuisine = findViewById(R.id.cuisine_one);
            TextView reviews = findViewById(R.id.food_reviews_one);
            ImageView foto = findViewById(R.id.r_one_pic);
            ImageView second_foto = findViewById(R.id.r_two_pic);
            ImageView third_foto = findViewById(R.id.r_three_pic);
            String average = "Price:$10-$20";
            String b_results = "select * from Food F where F.Price = " + "'" + average + "'" + "order by F.Average desc;";
            main_cursor = database_reviews.rawQuery(b_results, null);
            if (main_cursor.getCount() > 0) {
                main_cursor.moveToFirst();
                name.setText(main_cursor.getString(0));
                home.setText(main_cursor.getString(1));
                rating.setText("Rating:" + main_cursor.getDouble(2) / (double) main_cursor.getInt(7));
                meal.setText(main_cursor.getString(3));
                price.setText(main_cursor.getString(4));
                cuisine.setText(main_cursor.getString(5));
                byte[] b_photo = main_cursor.getBlob(6);
                Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
                foto.setBackgroundResource(0);
                foto.setImageBitmap(picture);
                reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                if (main_cursor.moveToNext() == true) {
                    TextView second_name = findViewById(R.id.restaurant_two);
                    TextView second_home = findViewById(R.id.address_two);
                    TextView second_rating = findViewById(R.id.rating_two);
                    TextView second_meal = findViewById(R.id.meal_two);
                    TextView second_price = findViewById(R.id.price_two);
                    TextView second_cuisine = findViewById(R.id.cuisine_two);
                    TextView second_reviews = findViewById(R.id.food_reviews_two);
                    second_name.setText(main_cursor.getString(0));
                    second_home.setText(main_cursor.getString(1));
                    second_rating.setText("Rating:" + main_cursor.getDouble(2) / (double) main_cursor.getInt(7) + "");
                    second_meal.setText(main_cursor.getString(3));
                    second_price.setText(main_cursor.getString(4));
                    second_cuisine.setText(main_cursor.getString(5));
                    byte[] second_b_photo = main_cursor.getBlob(6);
                    Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                    second_foto.setBackgroundResource(0);
                    second_foto.setImageBitmap(second_picture);
                    second_reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                    if (main_cursor.moveToNext() == true) {
                        TextView third_name = findViewById(R.id.restaurant_three);
                        TextView third_home = findViewById(R.id.address_three);
                        TextView third_rating = findViewById(R.id.rating_three);
                        TextView third_meal = findViewById(R.id.meal_three);
                        TextView third_price = findViewById(R.id.price_three);
                        TextView third_cuisine = findViewById(R.id.cuisine_three);
                        TextView third_reviews = findViewById(R.id.food_reviews_three);
                        third_name.setText(main_cursor.getString(0));
                        third_home.setText(main_cursor.getString(1));
                        third_rating.setText("Rating:" + main_cursor.getDouble(2) / (double) main_cursor.getInt(7) + "");
                        third_meal.setText(main_cursor.getString(3));
                        third_price.setText(main_cursor.getString(4));
                        third_cuisine.setText(main_cursor.getString(5));
                        byte[] third_b_photo = main_cursor.getBlob(6);
                        Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                        third_foto.setBackgroundResource(0);
                        third_foto.setImageBitmap(third_picture);
                        third_reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                    }
                }
            }
        }
    }

    public void expensive_food(View view) {
        setContentView(R.layout.top_food);
        if (all_food == false) {
            TextView name = findViewById(R.id.restaurant_one);
            TextView home = findViewById(R.id.address_one);
            TextView rating = findViewById(R.id.rating_one);
            TextView meal = findViewById(R.id.meal_one);
            TextView price = findViewById(R.id.price_one);
            TextView cuisine = findViewById(R.id.cuisine_one);
            TextView reviews = findViewById(R.id.food_reviews_one);
            ImageView foto = findViewById(R.id.r_one_pic);
            ImageView second_foto = findViewById(R.id.r_two_pic);
            ImageView third_foto = findViewById(R.id.r_three_pic);
            String expensive = "Price:$20+";
            String b_results = "select * from Food F where F.Meal = " + "'" + advanced_food + "'" + "and F.Price = " + "'" + expensive + "'" + "order by F.Average desc;";
            main_cursor = database_reviews.rawQuery(b_results, null);
            if (main_cursor.getCount() > 0) {
                main_cursor.moveToFirst();
                name.setText(main_cursor.getString(0));
                home.setText(main_cursor.getString(1));
                rating.setText("Rating:" + main_cursor.getDouble(2) / (double) main_cursor.getInt(7));
                meal.setText(main_cursor.getString(3));
                price.setText(main_cursor.getString(4));
                cuisine.setText(main_cursor.getString(5));
                byte[] b_photo = main_cursor.getBlob(6);
                Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
                foto.setBackgroundResource(0);
                foto.setImageBitmap(picture);
                reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                if (main_cursor.moveToNext() == true) {
                    TextView second_name = findViewById(R.id.restaurant_two);
                    TextView second_home = findViewById(R.id.address_two);
                    TextView second_rating = findViewById(R.id.rating_two);
                    TextView second_meal = findViewById(R.id.meal_two);
                    TextView second_price = findViewById(R.id.price_two);
                    TextView second_cuisine = findViewById(R.id.cuisine_two);
                    TextView second_reviews = findViewById(R.id.food_reviews_two);
                    second_name.setText(main_cursor.getString(0));
                    second_home.setText(main_cursor.getString(1));
                    second_rating.setText("Rating:" + main_cursor.getDouble(2) / (double) main_cursor.getInt(7) + "");
                    second_meal.setText(main_cursor.getString(3));
                    second_price.setText(main_cursor.getString(4));
                    second_cuisine.setText(main_cursor.getString(5));
                    byte[] second_b_photo = main_cursor.getBlob(6);
                    Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                    second_foto.setBackgroundResource(0);
                    second_foto.setImageBitmap(second_picture);
                    second_reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                    if (main_cursor.moveToNext() == true) {
                        TextView third_name = findViewById(R.id.restaurant_three);
                        TextView third_home = findViewById(R.id.address_three);
                        TextView third_rating = findViewById(R.id.rating_three);
                        TextView third_meal = findViewById(R.id.meal_three);
                        TextView third_price = findViewById(R.id.price_three);
                        TextView third_cuisine = findViewById(R.id.cuisine_three);
                        TextView third_reviews = findViewById(R.id.food_reviews_three);
                        third_name.setText(main_cursor.getString(0));
                        third_home.setText(main_cursor.getString(1));
                        third_rating.setText("Rating:" + main_cursor.getDouble(2) / (double) main_cursor.getInt(7) + "");
                        third_meal.setText(main_cursor.getString(3));
                        third_price.setText(main_cursor.getString(4));
                        third_cuisine.setText(main_cursor.getString(5));
                        byte[] third_b_photo = main_cursor.getBlob(6);
                        Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                        third_foto.setBackgroundResource(0);
                        third_foto.setImageBitmap(third_picture);
                        third_reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                    }
                }
            }

        } else if (all_food == true) {
            TextView name = findViewById(R.id.restaurant_one);
            TextView home = findViewById(R.id.address_one);
            TextView rating = findViewById(R.id.rating_one);
            TextView meal = findViewById(R.id.meal_one);
            TextView price = findViewById(R.id.price_one);
            TextView cuisine = findViewById(R.id.cuisine_one);
            TextView reviews = findViewById(R.id.food_reviews_one);
            ImageView foto = findViewById(R.id.r_one_pic);
            ImageView second_foto = findViewById(R.id.r_two_pic);
            ImageView third_foto = findViewById(R.id.r_three_pic);
            String expensive = "Price:$20+";
            String b_results = "select * from Food F where F.Price = " + "'" + expensive + "'" + "order by F.Average desc;";
            main_cursor = database_reviews.rawQuery(b_results, null);
            if (main_cursor.getCount() > 0) {
                main_cursor.moveToFirst();
                name.setText(main_cursor.getString(0));
                home.setText(main_cursor.getString(1));
                rating.setText("Rating:" + main_cursor.getDouble(2) / (double) main_cursor.getInt(7));
                meal.setText(main_cursor.getString(3));
                price.setText(main_cursor.getString(4));
                cuisine.setText(main_cursor.getString(5));
                byte[] b_photo = main_cursor.getBlob(6);
                Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
                foto.setBackgroundResource(0);
                foto.setImageBitmap(picture);
                reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                if (main_cursor.moveToNext() == true) {
                    TextView second_name = findViewById(R.id.restaurant_two);
                    TextView second_home = findViewById(R.id.address_two);
                    TextView second_rating = findViewById(R.id.rating_two);
                    TextView second_meal = findViewById(R.id.meal_two);
                    TextView second_price = findViewById(R.id.price_two);
                    TextView second_cuisine = findViewById(R.id.cuisine_two);
                    TextView second_reviews = findViewById(R.id.food_reviews_two);
                    second_name.setText(main_cursor.getString(0));
                    second_home.setText(main_cursor.getString(1));
                    second_rating.setText("Rating:" + main_cursor.getDouble(2) / (double) main_cursor.getInt(7) + "");
                    second_meal.setText(main_cursor.getString(3));
                    second_price.setText(main_cursor.getString(4));
                    second_cuisine.setText(main_cursor.getString(5));
                    byte[] second_b_photo = main_cursor.getBlob(6);
                    Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                    second_foto.setBackgroundResource(0);
                    second_foto.setImageBitmap(second_picture);
                    second_reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                    if (main_cursor.moveToNext() == true) {
                        TextView third_name = findViewById(R.id.restaurant_three);
                        TextView third_home = findViewById(R.id.address_three);
                        TextView third_rating = findViewById(R.id.rating_three);
                        TextView third_meal = findViewById(R.id.meal_three);
                        TextView third_price = findViewById(R.id.price_three);
                        TextView third_cuisine = findViewById(R.id.cuisine_three);
                        TextView third_reviews = findViewById(R.id.food_reviews_three);
                        third_name.setText(main_cursor.getString(0));
                        third_home.setText(main_cursor.getString(1));
                        third_rating.setText("Rating:" + main_cursor.getDouble(2) / (double) main_cursor.getInt(7) + "");
                        third_meal.setText(main_cursor.getString(3));
                        third_price.setText(main_cursor.getString(4));
                        third_cuisine.setText(main_cursor.getString(5));
                        byte[] third_b_photo = main_cursor.getBlob(6);
                        Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                        third_foto.setBackgroundResource(0);
                        third_foto.setImageBitmap(third_picture);
                        third_reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                    }
                }
            }
        }
    }

    public void advanced_price_back(View view) {
        setContentView(R.layout.adv_food_search);
    }

    public void advanced_cuisine_search(View view) {
        setContentView(R.layout.advanced_cuisine_search);
    }

    public void find_cuisine(View view) {
        String selected_cuisine = view.getTag().toString();
        setContentView(R.layout.top_food);
        if (all_food == false) {
            TextView name = findViewById(R.id.restaurant_one);
            TextView home = findViewById(R.id.address_one);
            TextView rating = findViewById(R.id.rating_one);
            TextView meal = findViewById(R.id.meal_one);
            TextView price = findViewById(R.id.price_one);
            TextView cuisine = findViewById(R.id.cuisine_one);
            TextView reviews = findViewById(R.id.food_reviews_one);
            ImageView foto = findViewById(R.id.r_one_pic);
            ImageView second_foto = findViewById(R.id.r_two_pic);
            ImageView third_foto = findViewById(R.id.r_three_pic);
            String expensive = "Price:$20+";
            String b_results = "select * from Food F where F.Meal = " + "'" + advanced_food + "'" + "and F.Cuisine = " + "'" + selected_cuisine + "'" + "order by F.Average desc;";
            main_cursor = database_reviews.rawQuery(b_results, null);
            if (main_cursor.getCount() > 0) {
                main_cursor.moveToFirst();
                name.setText(main_cursor.getString(0));
                home.setText(main_cursor.getString(1));
                rating.setText("Rating:" + main_cursor.getDouble(2) / (double) main_cursor.getInt(7));
                meal.setText(main_cursor.getString(3));
                price.setText(main_cursor.getString(4));
                cuisine.setText(main_cursor.getString(5));
                byte[] b_photo = main_cursor.getBlob(6);
                Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
                foto.setBackgroundResource(0);
                foto.setImageBitmap(picture);
                reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                if (main_cursor.moveToNext() == true) {
                    TextView second_name = findViewById(R.id.restaurant_two);
                    TextView second_home = findViewById(R.id.address_two);
                    TextView second_rating = findViewById(R.id.rating_two);
                    TextView second_meal = findViewById(R.id.meal_two);
                    TextView second_price = findViewById(R.id.price_two);
                    TextView second_cuisine = findViewById(R.id.cuisine_two);
                    TextView second_reviews = findViewById(R.id.food_reviews_two);
                    second_name.setText(main_cursor.getString(0));
                    second_home.setText(main_cursor.getString(1));
                    second_rating.setText("Rating:" + main_cursor.getDouble(2) / (double) main_cursor.getInt(7) + "");
                    second_meal.setText(main_cursor.getString(3));
                    second_price.setText(main_cursor.getString(4));
                    second_cuisine.setText(main_cursor.getString(5));
                    byte[] second_b_photo = main_cursor.getBlob(6);
                    Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                    second_foto.setBackgroundResource(0);
                    second_foto.setImageBitmap(second_picture);
                    second_reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                    if (main_cursor.moveToNext() == true) {
                        TextView third_name = findViewById(R.id.restaurant_three);
                        TextView third_home = findViewById(R.id.address_three);
                        TextView third_rating = findViewById(R.id.rating_three);
                        TextView third_meal = findViewById(R.id.meal_three);
                        TextView third_price = findViewById(R.id.price_three);
                        TextView third_cuisine = findViewById(R.id.cuisine_three);
                        TextView third_reviews = findViewById(R.id.food_reviews_three);
                        third_name.setText(main_cursor.getString(0));
                        third_home.setText(main_cursor.getString(1));
                        third_rating.setText("Rating:" + main_cursor.getDouble(2) / (double) main_cursor.getInt(7) + "");
                        third_meal.setText(main_cursor.getString(3));
                        third_price.setText(main_cursor.getString(4));
                        third_cuisine.setText(main_cursor.getString(5));
                        byte[] third_b_photo = main_cursor.getBlob(6);
                        Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                        third_foto.setBackgroundResource(0);
                        third_foto.setImageBitmap(third_picture);
                        third_reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                    }
                }
            }

        } else if (all_food == true) {
            TextView name = findViewById(R.id.restaurant_one);
            TextView home = findViewById(R.id.address_one);
            TextView rating = findViewById(R.id.rating_one);
            TextView meal = findViewById(R.id.meal_one);
            TextView price = findViewById(R.id.price_one);
            TextView cuisine = findViewById(R.id.cuisine_one);
            TextView reviews = findViewById(R.id.food_reviews_one);
            ImageView foto = findViewById(R.id.r_one_pic);
            ImageView second_foto = findViewById(R.id.r_two_pic);
            ImageView third_foto = findViewById(R.id.r_three_pic);
            String expensive = "Price:$20+";
            String b_results = "select * from Food F where F.Cuisine = " + "'" + selected_cuisine + "'" + "order by F.Average desc;";
            main_cursor = database_reviews.rawQuery(b_results, null);
            if (main_cursor.getCount() > 0) {
                main_cursor.moveToFirst();
                name.setText(main_cursor.getString(0));
                home.setText(main_cursor.getString(1));
                rating.setText("Rating:" + main_cursor.getDouble(2) / (double) main_cursor.getInt(7));
                meal.setText(main_cursor.getString(3));
                price.setText(main_cursor.getString(4));
                cuisine.setText(main_cursor.getString(5));
                byte[] b_photo = main_cursor.getBlob(6);
                Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
                foto.setBackgroundResource(0);
                foto.setImageBitmap(picture);
                reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                if (main_cursor.moveToNext() == true) {
                    TextView second_name = findViewById(R.id.restaurant_two);
                    TextView second_home = findViewById(R.id.address_two);
                    TextView second_rating = findViewById(R.id.rating_two);
                    TextView second_meal = findViewById(R.id.meal_two);
                    TextView second_price = findViewById(R.id.price_two);
                    TextView second_cuisine = findViewById(R.id.cuisine_two);
                    TextView second_reviews = findViewById(R.id.food_reviews_two);
                    second_name.setText(main_cursor.getString(0));
                    second_home.setText(main_cursor.getString(1));
                    second_rating.setText("Rating:" + main_cursor.getDouble(2) / (double) main_cursor.getInt(7) + "");
                    second_meal.setText(main_cursor.getString(3));
                    second_price.setText(main_cursor.getString(4));
                    second_cuisine.setText(main_cursor.getString(5));
                    byte[] second_b_photo = main_cursor.getBlob(6);
                    Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                    second_foto.setBackgroundResource(0);
                    second_foto.setImageBitmap(second_picture);
                    second_reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                    if (main_cursor.moveToNext() == true) {
                        TextView third_name = findViewById(R.id.restaurant_three);
                        TextView third_home = findViewById(R.id.address_three);
                        TextView third_rating = findViewById(R.id.rating_three);
                        TextView third_meal = findViewById(R.id.meal_three);
                        TextView third_price = findViewById(R.id.price_three);
                        TextView third_cuisine = findViewById(R.id.cuisine_three);
                        TextView third_reviews = findViewById(R.id.food_reviews_three);
                        third_name.setText(main_cursor.getString(0));
                        third_home.setText(main_cursor.getString(1));
                        third_rating.setText("Rating:" + main_cursor.getDouble(2) / (double) main_cursor.getInt(7) + "");
                        third_meal.setText(main_cursor.getString(3));
                        third_price.setText(main_cursor.getString(4));
                        third_cuisine.setText(main_cursor.getString(5));
                        byte[] third_b_photo = main_cursor.getBlob(6);
                        Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                        third_foto.setBackgroundResource(0);
                        third_foto.setImageBitmap(third_picture);
                        third_reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                    }
                }
            }
        }
    }

    public void adv_food_back(View view) {
        setContentView(R.layout.top_food);
    }

    public void life_r_back(View view) {
        setContentView(R.layout.l_style);
    }

    public void life_price_search(View view) {
        setContentView(R.layout.life_price_search);
    }

    public void adv_life(View view) {
        TextView life = findViewById(R.id.life_category_one);
        advanced_lifestyle = life.getText().toString();
        setContentView(R.layout.adv_life_search);
    }

    public void advanced_life_price_search(View view) {
        String selected_price = view.getTag().toString();
        setContentView(R.layout.top_life);
        if (all_life == false) {
            TextView name = findViewById(R.id.life_name_one);
            TextView home = findViewById(R.id.life_address_one);
            TextView rating = findViewById(R.id.life_rating_one);
            TextView category = findViewById(R.id.life_category_one);
            TextView price = findViewById(R.id.life_price_one);
            TextView service = findViewById(R.id.life_service_one);
            TextView review = findViewById(R.id.life_review_one);
            ImageView foto = findViewById(R.id.life_one_pic);
            ImageView second_foto = findViewById(R.id.life_two_pic);
            ImageView third_foto = findViewById(R.id.life_three_pic);
            String haircut = "Category:Haircut";
            String grocery_result = "select * from Lifestyle L where L.Category = " + "'" + advanced_lifestyle + "'" + "and L.Price =" + "'" + selected_price + "'" + "order by L.Rating desc;";
            main_cursor = database_reviews.rawQuery(grocery_result, null);
            if (main_cursor.getCount() > 0) {
                main_cursor.moveToFirst();
                name.setText(main_cursor.getString(0));
                home.setText(main_cursor.getString(1));
                rating.setText("Rating:" + main_cursor.getInt(2)/ (double) main_cursor.getInt(7));
                category.setText(main_cursor.getString(3));
                price.setText(main_cursor.getString(4));
                service.setText("Service:" + main_cursor.getDouble(5)/(double) main_cursor.getInt(7));
                byte[] b_photo = main_cursor.getBlob(6);
                Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
                foto.setBackgroundResource(0);
                foto.setImageBitmap(picture);
                review.setText("Number of Reviews:" + main_cursor.getInt(7));
                if (main_cursor.moveToNext() == true) {
                    TextView second_name = findViewById(R.id.life_name_two);
                    TextView second_home = findViewById(R.id.life_address_two);
                    TextView second_rating = findViewById(R.id.life_rating_two);
                    TextView second_category = findViewById(R.id.life_category_two);
                    TextView second_price = findViewById(R.id.life_price_two);
                    TextView second_service = findViewById(R.id.life_service_two);
                    TextView second_review = findViewById(R.id.life_review_two);
                    second_name.setText(main_cursor.getString(0));
                    second_home.setText(main_cursor.getString(1));
                    second_rating.setText("Rating:" + main_cursor.getInt(2)/ (double) main_cursor.getInt(7) + "");
                    second_category.setText(main_cursor.getString(3));
                    second_price.setText(main_cursor.getString(4));
                    second_service.setText("Service:" + main_cursor.getDouble(5)/(double) main_cursor.getInt(7));
                    byte[] second_b_photo = main_cursor.getBlob(6);
                    Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                    second_foto.setBackgroundResource(0);
                    second_foto.setImageBitmap(second_picture);
                    second_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                    if (main_cursor.moveToNext() == true) {
                        TextView third_name = findViewById(R.id.life_name_three);
                        TextView third_home = findViewById(R.id.life_address_three);
                        TextView third_rating = findViewById(R.id.life_rating_three);
                        TextView third_category = findViewById(R.id.life_category_three);
                        TextView third_price = findViewById(R.id.life_price_three);
                        TextView third_service = findViewById(R.id.life_service_three);
                        TextView third_review = findViewById(R.id.life_review_three);
                        third_name.setText(main_cursor.getString(0));
                        third_home.setText(main_cursor.getString(1));
                        third_rating.setText("Rating:" + main_cursor.getInt(2)/ (double) main_cursor.getInt(7) + "");
                        third_category.setText(main_cursor.getString(3));
                        third_price.setText(main_cursor.getString(4));
                        third_service.setText("Service:" + main_cursor.getDouble(5)/(double) main_cursor.getInt(7));
                        byte[] third_b_photo = main_cursor.getBlob(6);
                        Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                        third_foto.setBackgroundResource(0);
                        third_foto.setImageBitmap(third_picture);
                        third_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                    }
                }
            }

        } else if (all_life == true) {
            TextView name = findViewById(R.id.life_name_one);
            TextView home = findViewById(R.id.life_address_one);
            TextView rating = findViewById(R.id.life_rating_one);
            TextView category = findViewById(R.id.life_category_one);
            TextView price = findViewById(R.id.life_price_one);
            TextView service = findViewById(R.id.life_service_one);
            TextView review = findViewById(R.id.life_review_one);
            ImageView foto = findViewById(R.id.life_one_pic);
            ImageView second_foto = findViewById(R.id.life_two_pic);
            ImageView third_foto = findViewById(R.id.life_three_pic);
            String haircut = "Category:Haircut";
            String grocery_result = "select * from Lifestyle L where L.Price =" + "'" + selected_price + "'" + "order by L.Rating desc;";
            main_cursor = database_reviews.rawQuery(grocery_result, null);
            if (main_cursor.getCount() > 0) {
                main_cursor.moveToFirst();
                name.setText(main_cursor.getString(0));
                home.setText(main_cursor.getString(1));
                rating.setText("Rating:" + main_cursor.getInt(2)/ (double) main_cursor.getInt(7));
                category.setText(main_cursor.getString(3));
                price.setText(main_cursor.getString(4));
                service.setText("Service:" + main_cursor.getDouble(5)/(double) main_cursor.getInt(7));
                byte[] b_photo = main_cursor.getBlob(6);
                Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
                foto.setBackgroundResource(0);
                foto.setImageBitmap(picture);
                review.setText("Number of Reviews:" + main_cursor.getInt(7));
                if (main_cursor.moveToNext() == true) {
                    TextView second_name = findViewById(R.id.life_name_two);
                    TextView second_home = findViewById(R.id.life_address_two);
                    TextView second_rating = findViewById(R.id.life_rating_two);
                    TextView second_category = findViewById(R.id.life_category_two);
                    TextView second_price = findViewById(R.id.life_price_two);
                    TextView second_service = findViewById(R.id.life_service_two);
                    TextView second_review = findViewById(R.id.life_review_two);
                    second_name.setText(main_cursor.getString(0));
                    second_home.setText(main_cursor.getString(1));
                    second_rating.setText("Rating:" + main_cursor.getInt(2)/ (double) main_cursor.getInt(7) + "");
                    second_category.setText(main_cursor.getString(3));
                    second_price.setText(main_cursor.getString(4));
                    second_service.setText("Service:" + main_cursor.getDouble(5)/(double) main_cursor.getInt(7));
                    byte[] second_b_photo = main_cursor.getBlob(6);
                    Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                    second_foto.setBackgroundResource(0);
                    second_foto.setImageBitmap(second_picture);
                    second_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                    if (main_cursor.moveToNext() == true) {
                        TextView third_name = findViewById(R.id.life_name_three);
                        TextView third_home = findViewById(R.id.life_address_three);
                        TextView third_rating = findViewById(R.id.life_rating_three);
                        TextView third_category = findViewById(R.id.life_category_three);
                        TextView third_price = findViewById(R.id.life_price_three);
                        TextView third_service = findViewById(R.id.life_service_three);
                        TextView third_review = findViewById(R.id.life_review_three);
                        third_name.setText(main_cursor.getString(0));
                        third_home.setText(main_cursor.getString(1));
                        third_rating.setText("Rating:" + main_cursor.getInt(2)/ (double) main_cursor.getInt(7) + "");
                        third_category.setText(main_cursor.getString(3));
                        third_price.setText(main_cursor.getString(4));
                        third_service.setText("Service:" + main_cursor.getDouble(5)/(double) main_cursor.getInt(7));
                        byte[] third_b_photo = main_cursor.getBlob(6);
                        Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                        third_foto.setBackgroundResource(0);
                        third_foto.setImageBitmap(third_picture);
                        third_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                    }
                }
            }
        }
    }

    public void service_search(View view) {
        setContentView(R.layout.top_life);
        if (all_life == false) {
            TextView name = findViewById(R.id.life_name_one);
            TextView home = findViewById(R.id.life_address_one);
            TextView rating = findViewById(R.id.life_rating_one);
            TextView category = findViewById(R.id.life_category_one);
            TextView price = findViewById(R.id.life_price_one);
            TextView service = findViewById(R.id.life_service_one);
            TextView review = findViewById(R.id.life_review_one);
            ImageView foto = findViewById(R.id.life_one_pic);
            ImageView second_foto = findViewById(R.id.life_two_pic);
            ImageView third_foto = findViewById(R.id.life_three_pic);
            String haircut = "Category:Haircut";
            String grocery_result = "select * from Lifestyle L where L.Category = " + "'" + advanced_lifestyle + "'" + "order by L.Service desc, L.Rating desc;";
            main_cursor = database_reviews.rawQuery(grocery_result, null);
            if (main_cursor.getCount() > 0) {
                main_cursor.moveToFirst();
                name.setText(main_cursor.getString(0));
                home.setText(main_cursor.getString(1));
                rating.setText("Rating:" + main_cursor.getInt(2)/ (double) main_cursor.getInt(7));
                category.setText(main_cursor.getString(3));
                price.setText(main_cursor.getString(4));
                service.setText("Service:" + main_cursor.getDouble(5)/(double) main_cursor.getInt(7));
                byte[] b_photo = main_cursor.getBlob(6);
                Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
                foto.setBackgroundResource(0);
                foto.setImageBitmap(picture);
                review.setText("Number of Reviews:" + main_cursor.getInt(7));
                if (main_cursor.moveToNext() == true) {
                    TextView second_name = findViewById(R.id.life_name_two);
                    TextView second_home = findViewById(R.id.life_address_two);
                    TextView second_rating = findViewById(R.id.life_rating_two);
                    TextView second_category = findViewById(R.id.life_category_two);
                    TextView second_price = findViewById(R.id.life_price_two);
                    TextView second_service = findViewById(R.id.life_service_two);
                    TextView second_review = findViewById(R.id.life_review_two);
                    second_name.setText(main_cursor.getString(0));
                    second_home.setText(main_cursor.getString(1));
                    second_rating.setText("Rating:" + main_cursor.getInt(2)/ (double) main_cursor.getInt(7) + "");
                    second_category.setText(main_cursor.getString(3));
                    second_price.setText(main_cursor.getString(4));
                    second_service.setText("Service:" + main_cursor.getDouble(5)/(double) main_cursor.getInt(7));
                    byte[] second_b_photo = main_cursor.getBlob(6);
                    Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                    second_foto.setBackgroundResource(0);
                    second_foto.setImageBitmap(second_picture);
                    second_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                    if (main_cursor.moveToNext() == true) {
                        TextView third_name = findViewById(R.id.life_name_three);
                        TextView third_home = findViewById(R.id.life_address_three);
                        TextView third_rating = findViewById(R.id.life_rating_three);
                        TextView third_category = findViewById(R.id.life_category_three);
                        TextView third_price = findViewById(R.id.life_price_three);
                        TextView third_service = findViewById(R.id.life_service_three);
                        TextView third_review = findViewById(R.id.life_review_three);
                        third_name.setText(main_cursor.getString(0));
                        third_home.setText(main_cursor.getString(1));
                        third_rating.setText("Rating:" + main_cursor.getInt(2)/ (double) main_cursor.getInt(7) + "");
                        third_category.setText(main_cursor.getString(3));
                        third_price.setText(main_cursor.getString(4));
                        third_service.setText("Service:" + main_cursor.getDouble(5)/(double) main_cursor.getInt(7));
                        byte[] third_b_photo = main_cursor.getBlob(6);
                        Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                        third_foto.setBackgroundResource(0);
                        third_foto.setImageBitmap(third_picture);
                        third_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                    }
                }
            }

        } else if (all_life == true) {
            TextView name = findViewById(R.id.life_name_one);
            TextView home = findViewById(R.id.life_address_one);
            TextView rating = findViewById(R.id.life_rating_one);
            TextView category = findViewById(R.id.life_category_one);
            TextView price = findViewById(R.id.life_price_one);
            TextView service = findViewById(R.id.life_service_one);
            TextView review = findViewById(R.id.life_review_one);
            ImageView foto = findViewById(R.id.life_one_pic);
            ImageView second_foto = findViewById(R.id.life_two_pic);
            ImageView third_foto = findViewById(R.id.life_three_pic);
            String haircut = "Category:Haircut";
            String grocery_result = "select * from Lifestyle L order by L.Service desc, L.Rating desc;";
            main_cursor = database_reviews.rawQuery(grocery_result, null);
            if (main_cursor.getCount() > 0) {
                main_cursor.moveToFirst();
                name.setText(main_cursor.getString(0));
                home.setText(main_cursor.getString(1));
                rating.setText("Rating:" + main_cursor.getInt(2)/ (double) main_cursor.getInt(7));
                category.setText(main_cursor.getString(3));
                price.setText(main_cursor.getString(4));
                service.setText("Service:" + main_cursor.getDouble(5)/(double) main_cursor.getInt(7));
                byte[] b_photo = main_cursor.getBlob(6);
                Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
                foto.setBackgroundResource(0);
                foto.setImageBitmap(picture);
                review.setText("Number of Reviews:" + main_cursor.getInt(7));
                if (main_cursor.moveToNext() == true) {
                    TextView second_name = findViewById(R.id.life_name_two);
                    TextView second_home = findViewById(R.id.life_address_two);
                    TextView second_rating = findViewById(R.id.life_rating_two);
                    TextView second_category = findViewById(R.id.life_category_two);
                    TextView second_price = findViewById(R.id.life_price_two);
                    TextView second_service = findViewById(R.id.life_service_two);
                    TextView second_review = findViewById(R.id.life_review_two);
                    second_name.setText(main_cursor.getString(0));
                    second_home.setText(main_cursor.getString(1));
                    second_rating.setText("Rating:" + main_cursor.getInt(2)/ (double) main_cursor.getInt(7) + "");
                    second_category.setText(main_cursor.getString(3));
                    second_price.setText(main_cursor.getString(4));
                    second_service.setText("Service:" + main_cursor.getDouble(5)/(double) main_cursor.getInt(7));
                    byte[] second_b_photo = main_cursor.getBlob(6);
                    Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                    second_foto.setBackgroundResource(0);
                    second_foto.setImageBitmap(second_picture);
                    second_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                    if (main_cursor.moveToNext() == true) {
                        TextView third_name = findViewById(R.id.life_name_three);
                        TextView third_home = findViewById(R.id.life_address_three);
                        TextView third_rating = findViewById(R.id.life_rating_three);
                        TextView third_category = findViewById(R.id.life_category_three);
                        TextView third_price = findViewById(R.id.life_price_three);
                        TextView third_service = findViewById(R.id.life_service_three);
                        TextView third_review = findViewById(R.id.life_review_three);
                        third_name.setText(main_cursor.getString(0));
                        third_home.setText(main_cursor.getString(1));
                        third_rating.setText("Rating:" + main_cursor.getInt(2)/ (double) main_cursor.getInt(7) + "");
                        third_category.setText(main_cursor.getString(3));
                        third_price.setText(main_cursor.getString(4));
                        third_service.setText("Service:" + main_cursor.getDouble(5)/(double) main_cursor.getInt(7));
                        byte[] third_b_photo = main_cursor.getBlob(6);
                        Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                        third_foto.setBackgroundResource(0);
                        third_foto.setImageBitmap(third_picture);
                        third_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                    }
                }
            }
        }
    }

    public void adv_life_back(View view) {
        setContentView(R.layout.top_life);
    }

    public void fun_r_back(View view) {
        setContentView(R.layout.fun);
    }

    public void adv_fun(View view) {
        TextView fun = findViewById(R.id.fun_category_one);
        advanced_fun = fun.getText().toString();
        setContentView(R.layout.adv_fun_search);
    }

    public void fun_price_select(View view) {
        setContentView(R.layout.fun_price);
    }

    public void fun_age_select(View view) {
        setContentView(R.layout.age_group);
    }

    public void age_search(View view) {
        String age = view.getTag().toString();
        setContentView(R.layout.top_fun);
        if (all_fun == false) {
            TextView name = findViewById(R.id.fun_name_one);
            TextView home = findViewById(R.id.fun_address_one);
            TextView rating = findViewById(R.id.fun_rating_one);
            TextView category = findViewById(R.id.fun_category_one);
            TextView price = findViewById(R.id.fun_price_one);
            TextView age_group = findViewById(R.id.fun_age_group_one);
            TextView review = findViewById(R.id.fun_review_one);
            ImageView foto = findViewById(R.id.fun_one_pic);
            ImageView second_foto = findViewById(R.id.fun_two_pic);
            ImageView third_foto = findViewById(R.id.fun_three_pic);
            String recreation = "Category:Recreation";
            String grocery_result = "select * from Entertainment E where E.Category = " + "'" + advanced_fun + "'" + "and E.AG =" + "'" + age + "'" + " order by E.Rating desc;";
            main_cursor = database_reviews.rawQuery(grocery_result, null);
            if (main_cursor.getCount() > 0) {
                main_cursor.moveToFirst();
                name.setText(main_cursor.getString(0));
                home.setText(main_cursor.getString(1));
                rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7));
                category.setText(main_cursor.getString(3));
                price.setText(main_cursor.getString(4));
                age_group.setText(main_cursor.getString(5));
                byte[] b_photo = main_cursor.getBlob(6);
                Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
                foto.setBackgroundResource(0);
                foto.setImageBitmap(picture);
                review.setText("Number of Reviews:" + main_cursor.getInt(7));
                if (main_cursor.moveToNext() == true) {
                    TextView second_name = findViewById(R.id.fun_name_two);
                    TextView second_home = findViewById(R.id.fun_address_two);
                    TextView second_rating = findViewById(R.id.fun_rating_two);
                    TextView second_category = findViewById(R.id.fun_category_two);
                    TextView second_price = findViewById(R.id.fun_price_two);
                    TextView second_age_group = findViewById(R.id.fun_age_group_two);
                    TextView second_review = findViewById(R.id.fun_review_two);
                    second_name.setText(main_cursor.getString(0));
                    second_home.setText(main_cursor.getString(1));
                    second_rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7) + "");
                    second_category.setText(main_cursor.getString(3));
                    second_price.setText(main_cursor.getString(4));
                    second_age_group.setText(main_cursor.getString(5));
                    byte[] second_b_photo = main_cursor.getBlob(6);
                    Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                    second_foto.setBackgroundResource(0);
                    second_foto.setImageBitmap(second_picture);
                    second_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                    if (main_cursor.moveToNext() == true) {
                        TextView third_name = findViewById(R.id.fun_name_three);
                        TextView third_home = findViewById(R.id.fun_address_three);
                        TextView third_rating = findViewById(R.id.fun_rating_three);
                        TextView third_category = findViewById(R.id.fun_category_three);
                        TextView third_price = findViewById(R.id.fun_price_three);
                        TextView third_age_group = findViewById(R.id.fun_age_group_three);
                        TextView third_review = findViewById(R.id.fun_review_three);
                        third_name.setText(main_cursor.getString(0));
                        third_home.setText(main_cursor.getString(1));
                        third_rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7) + "");
                        third_category.setText(main_cursor.getString(3));
                        third_price.setText(main_cursor.getString(4));
                        third_age_group.setText(main_cursor.getString(5));
                        byte[] third_b_photo = main_cursor.getBlob(6);
                        Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                        third_foto.setBackgroundResource(0);
                        third_foto.setImageBitmap(third_picture);
                        third_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                    }
                }
            }

        } else if (all_fun == true) {
            TextView name = findViewById(R.id.fun_name_one);
            TextView home = findViewById(R.id.fun_address_one);
            TextView rating = findViewById(R.id.fun_rating_one);
            TextView category = findViewById(R.id.fun_category_one);
            TextView price = findViewById(R.id.fun_price_one);
            TextView age_group = findViewById(R.id.fun_age_group_one);
            TextView review = findViewById(R.id.fun_review_one);
            ImageView foto = findViewById(R.id.fun_one_pic);
            ImageView second_foto = findViewById(R.id.fun_two_pic);
            ImageView third_foto = findViewById(R.id.fun_three_pic);
            String recreation = "Category:Recreation";
            String grocery_result = "select * from Entertainment E where E.AG =" + "'" + age + "'" + " order by E.Rating desc;";
            main_cursor = database_reviews.rawQuery(grocery_result, null);
            if (main_cursor.getCount() > 0) {
                main_cursor.moveToFirst();
                name.setText(main_cursor.getString(0));
                home.setText(main_cursor.getString(1));
                rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7));
                category.setText(main_cursor.getString(3));
                price.setText(main_cursor.getString(4));
                age_group.setText(main_cursor.getString(5));
                byte[] b_photo = main_cursor.getBlob(6);
                Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
                foto.setBackgroundResource(0);
                foto.setImageBitmap(picture);
                review.setText("Number of Reviews:" + main_cursor.getInt(7));
                if (main_cursor.moveToNext() == true) {
                    TextView second_name = findViewById(R.id.fun_name_two);
                    TextView second_home = findViewById(R.id.fun_address_two);
                    TextView second_rating = findViewById(R.id.fun_rating_two);
                    TextView second_category = findViewById(R.id.fun_category_two);
                    TextView second_price = findViewById(R.id.fun_price_two);
                    TextView second_age_group = findViewById(R.id.fun_age_group_two);
                    TextView second_review = findViewById(R.id.fun_review_two);
                    second_name.setText(main_cursor.getString(0));
                    second_home.setText(main_cursor.getString(1));
                    second_rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7) + "");
                    second_category.setText(main_cursor.getString(3));
                    second_price.setText(main_cursor.getString(4));
                    second_age_group.setText(main_cursor.getString(5));
                    byte[] second_b_photo = main_cursor.getBlob(6);
                    Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                    second_foto.setBackgroundResource(0);
                    second_foto.setImageBitmap(second_picture);
                    second_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                    if (main_cursor.moveToNext() == true) {
                        TextView third_name = findViewById(R.id.fun_name_three);
                        TextView third_home = findViewById(R.id.fun_address_three);
                        TextView third_rating = findViewById(R.id.fun_rating_three);
                        TextView third_category = findViewById(R.id.fun_category_three);
                        TextView third_price = findViewById(R.id.fun_price_three);
                        TextView third_age_group = findViewById(R.id.fun_age_group_three);
                        TextView third_review = findViewById(R.id.fun_review_three);
                        third_name.setText(main_cursor.getString(0));
                        third_home.setText(main_cursor.getString(1));
                        third_rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7) + "");
                        third_category.setText(main_cursor.getString(3));
                        third_price.setText(main_cursor.getString(4));
                        third_age_group.setText(main_cursor.getString(5));
                        byte[] third_b_photo = main_cursor.getBlob(6);
                        Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                        third_foto.setBackgroundResource(0);
                        third_foto.setImageBitmap(third_picture);
                        third_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                    }
                }
            }
        }
    }

    public void fun_price(View view) {
        String selected_price = view.getTag().toString();
        setContentView(R.layout.top_fun);
        if (all_fun == false) {
            TextView name = findViewById(R.id.fun_name_one);
            TextView home = findViewById(R.id.fun_address_one);
            TextView rating = findViewById(R.id.fun_rating_one);
            TextView category = findViewById(R.id.fun_category_one);
            TextView price = findViewById(R.id.fun_price_one);
            TextView age_group = findViewById(R.id.fun_age_group_one);
            TextView review = findViewById(R.id.fun_review_one);
            ImageView foto = findViewById(R.id.fun_one_pic);
            ImageView second_foto = findViewById(R.id.fun_two_pic);
            ImageView third_foto = findViewById(R.id.fun_three_pic);
            String recreation = "Category:Recreation";
            String grocery_result = "select * from Entertainment E where E.Category = " + "'" + advanced_fun + "'" + "and E.Price =" + "'" + selected_price + "'" + " order by E.Rating desc;";
            main_cursor = database_reviews.rawQuery(grocery_result, null);
            if (main_cursor.getCount() > 0) {
                main_cursor.moveToFirst();
                name.setText(main_cursor.getString(0));
                home.setText(main_cursor.getString(1));
                rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7));
                category.setText(main_cursor.getString(3));
                price.setText(main_cursor.getString(4));
                age_group.setText(main_cursor.getString(5));
                byte[] b_photo = main_cursor.getBlob(6);
                Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
                foto.setBackgroundResource(0);
                foto.setImageBitmap(picture);
                review.setText("Number of Reviews:" + main_cursor.getInt(7));
                if (main_cursor.moveToNext() == true) {
                    TextView second_name = findViewById(R.id.fun_name_two);
                    TextView second_home = findViewById(R.id.fun_address_two);
                    TextView second_rating = findViewById(R.id.fun_rating_two);
                    TextView second_category = findViewById(R.id.fun_category_two);
                    TextView second_price = findViewById(R.id.fun_price_two);
                    TextView second_age_group = findViewById(R.id.fun_age_group_two);
                    TextView second_review = findViewById(R.id.fun_review_two);
                    second_name.setText(main_cursor.getString(0));
                    second_home.setText(main_cursor.getString(1));
                    second_rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7) + "");
                    second_category.setText(main_cursor.getString(3));
                    second_price.setText(main_cursor.getString(4));
                    second_age_group.setText(main_cursor.getString(5));
                    byte[] second_b_photo = main_cursor.getBlob(6);
                    Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                    second_foto.setBackgroundResource(0);
                    second_foto.setImageBitmap(second_picture);
                    second_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                    if (main_cursor.moveToNext() == true) {
                        TextView third_name = findViewById(R.id.fun_name_three);
                        TextView third_home = findViewById(R.id.fun_address_three);
                        TextView third_rating = findViewById(R.id.fun_rating_three);
                        TextView third_category = findViewById(R.id.fun_category_three);
                        TextView third_price = findViewById(R.id.fun_price_three);
                        TextView third_age_group = findViewById(R.id.fun_age_group_three);
                        TextView third_review = findViewById(R.id.fun_review_three);
                        third_name.setText(main_cursor.getString(0));
                        third_home.setText(main_cursor.getString(1));
                        third_rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7) + "");
                        third_category.setText(main_cursor.getString(3));
                        third_price.setText(main_cursor.getString(4));
                        third_age_group.setText(main_cursor.getString(5));
                        byte[] third_b_photo = main_cursor.getBlob(6);
                        Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                        third_foto.setBackgroundResource(0);
                        third_foto.setImageBitmap(third_picture);
                        third_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                    }
                }
            }

        } else if (all_fun == true) {
            TextView name = findViewById(R.id.fun_name_one);
            TextView home = findViewById(R.id.fun_address_one);
            TextView rating = findViewById(R.id.fun_rating_one);
            TextView category = findViewById(R.id.fun_category_one);
            TextView price = findViewById(R.id.fun_price_one);
            TextView age_group = findViewById(R.id.fun_age_group_one);
            TextView review = findViewById(R.id.fun_review_one);
            ImageView foto = findViewById(R.id.fun_one_pic);
            ImageView second_foto = findViewById(R.id.fun_two_pic);
            ImageView third_foto = findViewById(R.id.fun_three_pic);
            String recreation = "Category:Recreation";
            String grocery_result = "select * from Entertainment E where E.Price = " + "'" + selected_price + "'" + " order by E.Rating desc;";
            main_cursor = database_reviews.rawQuery(grocery_result, null);
            if (main_cursor.getCount() > 0) {
                main_cursor.moveToFirst();
                name.setText(main_cursor.getString(0));
                home.setText(main_cursor.getString(1));
                rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7));
                category.setText(main_cursor.getString(3));
                price.setText(main_cursor.getString(4));
                age_group.setText(main_cursor.getString(5));
                byte[] b_photo = main_cursor.getBlob(6);
                Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
                foto.setBackgroundResource(0);
                foto.setImageBitmap(picture);
                review.setText("Number of Reviews:" + main_cursor.getInt(7));
                if (main_cursor.moveToNext() == true) {
                    TextView second_name = findViewById(R.id.fun_name_two);
                    TextView second_home = findViewById(R.id.fun_address_two);
                    TextView second_rating = findViewById(R.id.fun_rating_two);
                    TextView second_category = findViewById(R.id.fun_category_two);
                    TextView second_price = findViewById(R.id.fun_price_two);
                    TextView second_age_group = findViewById(R.id.fun_age_group_two);
                    TextView second_review = findViewById(R.id.fun_review_two);
                    second_name.setText(main_cursor.getString(0));
                    second_home.setText(main_cursor.getString(1));
                    second_rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7) + "");
                    second_category.setText(main_cursor.getString(3));
                    second_price.setText(main_cursor.getString(4));
                    second_age_group.setText(main_cursor.getString(5));
                    byte[] second_b_photo = main_cursor.getBlob(6);
                    Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                    second_foto.setBackgroundResource(0);
                    second_foto.setImageBitmap(second_picture);
                    second_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                    if (main_cursor.moveToNext() == true) {
                        TextView third_name = findViewById(R.id.fun_name_three);
                        TextView third_home = findViewById(R.id.fun_address_three);
                        TextView third_rating = findViewById(R.id.fun_rating_three);
                        TextView third_category = findViewById(R.id.fun_category_three);
                        TextView third_price = findViewById(R.id.fun_price_three);
                        TextView third_age_group = findViewById(R.id.fun_age_group_three);
                        TextView third_review = findViewById(R.id.fun_review_three);
                        third_name.setText(main_cursor.getString(0));
                        third_home.setText(main_cursor.getString(1));
                        third_rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7) + "");
                        third_category.setText(main_cursor.getString(3));
                        third_price.setText(main_cursor.getString(4));
                        third_age_group.setText(main_cursor.getString(5));
                        byte[] third_b_photo = main_cursor.getBlob(6);
                        Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                        third_foto.setBackgroundResource(0);
                        third_foto.setImageBitmap(third_picture);
                        third_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                    }
                }
            }
        }
    }

    public void adv_fun_back(View view) {
        setContentView(R.layout.top_fun);
    }

    public void review(View view) {
        setContentView(R.layout.reviews_homepage);
    }

    public void back_home(View view) {
        setContentView(R.layout.activity_main);
    }

    public void food_review(View view) {
        setContentView(R.layout.food_review);
    }

    public void lifestyle_review(View view) {
        setContentView(R.layout.lifestyle_review);
    }

    public void entertainment_review(View view) {
        setContentView(R.layout.entertainment_review);
    }

    public void food_review_back(View view) {
        num_stars = 0;
        mealz = null;
        prices = null;
        food_type = null;
        setContentView(R.layout.reviews_homepage);
    }

    public void lifestyle_review_back(View view) {
        setContentView(R.layout.reviews_homepage);
    }

    public void entertainment_review_back(View view) {
        setContentView(R.layout.reviews_homepage);
    }

    public void food_submit(View view) {
        Context context = getApplicationContext();
        EditText restaurant = findViewById(R.id.restaurant);
        EditText address = findViewById(R.id.address);
        if (restaurant.length() == 0) {
            Toast no_restaurant = Toast.makeText(context, "No Restaurant Name Specified", Toast.LENGTH_LONG);
            no_restaurant.show();
        } else if (address.length() == 0) {
            Toast no_address = Toast.makeText(context, "No Address Specified", Toast.LENGTH_LONG);
            no_address.show();
        } else if (current == null) {
            Toast no_photo = Toast.makeText(context, "No Photo Taken", Toast.LENGTH_LONG);
            no_photo.show();

        } else {
            String food_name = restaurant.getText().toString();
            String food_address = address.getText().toString();
            String food_query = "select * from Food F where F.Name = " + "'" + food_name + "'" + "" + "and F.Address = " + "'" + food_address + "'" + "";
            String where = "Food.Name ="  + "'" + food_name + "'" + "" +  "and Food.Address ="  + "'" + food_address + "'" + "";
            Cursor check = database_reviews.rawQuery(food_query, null);
            Log.v("COUNT", "COUNT:" + check.getCount());
            if (check.getCount() == 0) {
                ContentValues values = new ContentValues();
                ContentValues single_values = new ContentValues();
                ImageView resto_pic = findViewById(R.id.r_one_pic);
                TextView name = findViewById(R.id.restaurant_one);
                TextView home = findViewById(R.id.address_one);
                TextView rating = findViewById(R.id.rating_one);
                TextView meal = findViewById(R.id.meal_one);
                TextView price = findViewById(R.id.price_one);
                TextView cuisine = findViewById(R.id.cuisine_one);
                values.put("Name", restaurant.getText().toString());
                values.put("Address", address.getText().toString());
                values.put("Rating", (double) num_stars);
                values.put("Meal", mealz);
                values.put("Price", prices);
                values.put("Cuisine", food_type);
                ByteArrayOutputStream compressed_photo = new ByteArrayOutputStream();
                current.compress(Bitmap.CompressFormat.PNG, 0, compressed_photo);
                byte[] photo = compressed_photo.toByteArray();
                values.put("Photo", photo);
                values.put("Reviews", 1);
                values.put("Average", (double) num_stars);
                single_values.put("Name", restaurant.getText().toString());
                single_values.put("Address", address.getText().toString());
                single_values.put("Rating", num_stars);
                single_values.put("Meal", mealz);
                single_values.put("Cuisine", food_type);
                single_values.put("Photo", photo);
                database_reviews.insert("Food", null, values);
                database_reviews.insert("Single_Food", null, single_values);
                mealz = null;
                prices = null;
                food_type = null;
                Toast saved = Toast.makeText(context, "Review Has Been Saved", Toast.LENGTH_LONG);
                saved.show();
                setContentView(R.layout.activity_main);
                food_comment(food_name, food_address, num_stars);
                current = null;
            } else if (check.getCount() > 0) {
                check.moveToFirst();
                String nombre = check.getString(0);
                String lugar = check.getString(1);
                double points = check.getDouble(2);
                String comida = check.getString(3);
                String precio = check.getString(4);
                String foodz = check.getString(5);
                byte [] picz = check.getBlob(6);
                int opinions = check.getInt(7);
                ContentValues s_v = new ContentValues();
                s_v.put("Name", food_name);
                s_v.put("Address", food_address);
                s_v.put("Rating", num_stars);
                s_v.put("Meal", mealz);
                s_v.put("Price", prices);
                s_v.put("Cuisine", food_type);
                ByteArrayOutputStream compressed_photo = new ByteArrayOutputStream();
                current.compress(Bitmap.CompressFormat.PNG, 0, compressed_photo);
                byte[] photo = compressed_photo.toByteArray();
                s_v.put("Photo", photo);
                database_reviews.insert("Single_Food", null, s_v);
                database_reviews.delete("Food", where, null);
                opinions = opinions + 1;
                points = (points + (double) num_stars);
                Log.v("POINTS:", points + "");
                ContentValues modified_values = new ContentValues();
                modified_values.put("Name", nombre);
                modified_values.put("Address", lugar);
                modified_values.put("Rating", points);
                modified_values.put("Meal", comida);
                modified_values.put("Price", precio);
                modified_values.put("Cuisine", foodz);
                modified_values.put("Photo", picz);
                modified_values.put("Reviews", opinions);
                modified_values.put("Average", points/opinions);
                database_reviews.insert("Food", null, modified_values);
                nombre = null;
                lugar = null;
                points = 0;
                comida = null;
                precio = null;
                opinions = 0;
                Toast saved = Toast.makeText(context, "Review Has Been Saved", Toast.LENGTH_LONG);
                saved.show();
                food_comment(food_name, food_address, num_stars);
                current = null;
            }
        }
    }

    public void lifestyle_submit(View view) {
        Context context = getApplicationContext();
        EditText name = findViewById(R.id.lifestyle_name);
        EditText lifestyle_address = findViewById(R.id.lifestyle_address);
        if (name.length() == 0) {
            Toast no_restaurant = Toast.makeText(context, "No Name Specified", Toast.LENGTH_LONG);
            no_restaurant.show();
        } else if (lifestyle_address.length() == 0) {
            Toast no_address = Toast.makeText(context, "No Address Specified", Toast.LENGTH_LONG);
            no_address.show();
        } else if (current == null) {
            Toast no_photo = Toast.makeText(context, "No Photo Taken", Toast.LENGTH_LONG);
            no_photo.show();
        } else {
            String life_name = name.getText().toString();
            String life_address = lifestyle_address.getText().toString();
            String lifestyle_query = "select * from Lifestyle L where L.Name = " + "'" + life_name + "'" + "" + "and L.Address = " + "'" + life_address + "'" + "";
            String l_where = "Lifestyle.Name ="  + "'" + life_name + "'" + "" +  "and Lifestyle.Address ="  + "'" + life_address + "'" + "";
            Cursor check = database_reviews.rawQuery(lifestyle_query, null);
            Log.v("COUNT", "COUNT:" + check.getCount());
            if (check.getCount() == 0) {
                ContentValues values = new ContentValues();
                ContentValues single_values = new ContentValues();            
                values.put("Name", name.getText().toString());
                values.put("Address", lifestyle_address.getText().toString());
                values.put("Rating", num_stars);
                values.put("Category", category);
                values.put("Price", lifestyle_price);
                values.put("Service", (double) service_stars);
                ByteArrayOutputStream compressed_photo = new ByteArrayOutputStream();
                current.compress(Bitmap.CompressFormat.PNG, 0, compressed_photo);
                byte[] photo = compressed_photo.toByteArray();
                values.put("Photo", photo);
                values.put("Reviews", 1);
                values.put("Average", (double) num_stars);
                single_values.put("Name", name.getText().toString());
                single_values.put("Address", lifestyle_address.getText().toString());
                single_values.put("Rating", (double) num_stars);
                single_values.put("Category", category);
                single_values.put("Price", lifestyle_price);
                single_values.put("Service", (double) service_stars);
                single_values.put("Photo", photo);
                database_reviews.insert("Single_Lifestyle", null, single_values);
                database_reviews.insert("Lifestyle", null, values);

                service_stars = 0;
                category = null;
                lifestyle_price = null;
                Toast saved = Toast.makeText(context, "Review Has Been Saved", Toast.LENGTH_LONG);
                saved.show();
                setContentView(R.layout.activity_main);
                life_comment(life_name, life_address, num_stars);
                current = null;


        } else if (check.getCount() > 0) {
                check.moveToFirst();
                String nombre = check.getString(0);
                String lugar = check.getString(1);
                double points = check.getDouble(2);
                String category = check.getString(3);
                String precio = check.getString(4);
                double service_points = check.getDouble(5);
                byte [] picz = check.getBlob(6);
                int opinions = check.getInt(7);
                ContentValues single_lives = new ContentValues();
                single_lives.put("Name", life_name);
                single_lives.put("Address", life_address);
                single_lives.put("Rating", num_stars);
                single_lives.put("Category", category);
                single_lives.put("Price", lifestyle_price);
                single_lives.put("Service", (double) service_stars);
                ByteArrayOutputStream compressed_photo = new ByteArrayOutputStream();
                current.compress(Bitmap.CompressFormat.PNG, 0, compressed_photo);
                byte[] photo = compressed_photo.toByteArray();
                single_lives.put("Photo", photo);
                database_reviews.insert("Single_Lifestyle", null, single_lives);
                database_reviews.delete("Lifestyle", l_where, null);
                opinions = opinions + 1;
                points = (points + (double) num_stars);
                service_points = (service_points + (double) service_stars);
                Log.v("POINTS:", points + "");
                ContentValues modified_values = new ContentValues();
                modified_values.put("Name", nombre);
                modified_values.put("Address", lugar);
                modified_values.put("Rating", points);
                modified_values.put("Category", category);
                modified_values.put("Price", precio);
                modified_values.put("Service", service_points);
                modified_values.put("Photo", picz);
                modified_values.put("Reviews", opinions);
                modified_values.put("Average", points/opinions);
                database_reviews.insert("Lifestyle", null, modified_values);
                service_stars = 0;
                nombre = null;
                lugar = null;
                points = 0;
                category = null;
                precio = null;
                opinions = 0;
                Toast saved = Toast.makeText(context, "Review Has Been Saved", Toast.LENGTH_LONG);
                saved.show();
                setContentView(R.layout.activity_main);
                life_comment(life_name, life_address, num_stars);
                current = null;
            }
        }
    }

    public void entertainment_submit(View view) {
        Context context = getApplicationContext();
        EditText venue = findViewById(R.id.venue);
        EditText venue_address = findViewById(R.id.venue_address);
        if (venue.length() == 0) {
            Toast no_restaurant = Toast.makeText(context, "No Restaurant Name Specified", Toast.LENGTH_LONG);
            no_restaurant.show();
        } else if (venue_address.length() == 0) {
            Toast no_address = Toast.makeText(context, "No Address Specified", Toast.LENGTH_LONG);
            no_address.show();
        } else if (current == null) {
            Toast no_photo = Toast.makeText(context, "No Photo Taken", Toast.LENGTH_LONG);
            no_photo.show();
        } else {
            String place = venue.getText().toString();
            String add = venue_address.getText().toString();
            String fun_query = "select * from Entertainment E where E.Name = " + "'" + place + "'" + "" + "and E.Address = " + "'" + add + "'" + "";
            String e_where = "Entertainment.Name =" + "'" + place + "'" + "" + "and Entertainment.Address =" + "'" + add + "'" + "";
            Cursor check = database_reviews.rawQuery(fun_query, null);
            Log.v("COUNT", "COUNT:" + check.getCount());
            if (check.getCount() == 0) {

                ContentValues values = new ContentValues();
                ContentValues single_values = new ContentValues();
                values.put("Name", venue.getText().toString());
                values.put("Address", venue_address.getText().toString());
                values.put("Rating", (double) num_stars);
                values.put("Category", fun_category);
                values.put("Price", fun_price);
                values.put("AG", age_group);
                ByteArrayOutputStream compressed_photo = new ByteArrayOutputStream();
                current.compress(Bitmap.CompressFormat.PNG, 0, compressed_photo);
                byte[] photo = compressed_photo.toByteArray();
                values.put("Photo", photo);
                values.put("Reviews", 1);
                values.put("Average", (double) num_stars);
                single_values.put("Name", place);
                single_values.put("Address", add);
                single_values.put("Rating", num_stars);
                single_values.put("Category", fun_category);
                single_values.put("Price", fun_price);
                single_values.put("AG", age_group);
                single_values.put("Photo", photo);
                database_reviews.insert("Single_Entertainment", null, single_values);
                database_reviews.insert("Entertainment", null, values);

                fun_category = null;
                fun_price = null;
                age_group = null;
                Toast saved = Toast.makeText(context, "Review Has Been Saved", Toast.LENGTH_LONG);
                saved.show();
                setContentView(R.layout.activity_main);
                life_comment(place, add, num_stars);
                current = null;


            } else if (check.getCount() > 0) {
                check.moveToFirst();
                String nombre = check.getString(0);
                String lugar = check.getString(1);
                double points = check.getDouble(2);
                String category = check.getString(3);
                String precio = check.getString(4);
                String age_group = check.getString(5);
                byte[] picz = check.getBlob(6);
                int opinions = check.getInt(7);
                ContentValues single_values = new ContentValues();
                single_values.put("Name", place);
                single_values.put("Address", add);
                single_values.put("Rating", num_stars);
                single_values.put("Category", fun_category);
                single_values.put("Price", fun_price);
                single_values.put("AG", age_group);
                ByteArrayOutputStream compressed_photo = new ByteArrayOutputStream();
                current.compress(Bitmap.CompressFormat.PNG, 0, compressed_photo);
                byte[] photo = compressed_photo.toByteArray();
                single_values.put("Photo", photo);
                database_reviews.insert("Single_Entertainment", null, single_values);
                database_reviews.delete("Entertainment", e_where, null);
                opinions = opinions + 1;
                points = (points + (double) num_stars);
                Log.v("POINTS:", points + "");
                ContentValues modified_values = new ContentValues();
                modified_values.put("Name", nombre);
                modified_values.put("Address", lugar);
                modified_values.put("Rating", points);
                modified_values.put("Category", category);
                modified_values.put("Price", precio);
                modified_values.put("AG", age_group);
                modified_values.put("Photo", picz);
                modified_values.put("Reviews", opinions);
                modified_values.put("Average", points/opinions);
                database_reviews.insert("Entertainment", null, modified_values);
                nombre = null;
                lugar = null;
                points = 0;
                category = null;
                precio = null;
                age_group = null;
                opinions = 0;
                Toast saved = Toast.makeText(context, "Review Has Been Saved", Toast.LENGTH_LONG);
                saved.show();
                setContentView(R.layout.activity_main);
                life_comment(place, add, num_stars);
                current = null;
            }
        }
    }

    public void next_row(View view) {
        Log.v("Button", "Next");
        TextView name = findViewById(R.id.restaurant_one);
        name.setText("");
        TextView home = findViewById(R.id.address_one);
        home.setText("");
        TextView rating = findViewById(R.id.rating_one);
        rating.setText("");
        TextView meal = findViewById(R.id.meal_one);
        meal.setText("");
        TextView price = findViewById(R.id.price_one);
        price.setText("");
        TextView cuisine = findViewById(R.id.cuisine_one);
        cuisine.setText("");
        TextView reviews = findViewById(R.id.food_reviews_one);
        reviews.setText("");
        TextView number_one = findViewById(R.id.one);
        TextView number_two = findViewById(R.id.two);
        TextView number_three = findViewById(R.id.three);
        TextView second_name = findViewById(R.id.restaurant_two);
        second_name.setText("");
        TextView second_home = findViewById(R.id.address_two);
        second_home.setText("");
        TextView second_rating = findViewById(R.id.rating_two);
        second_rating.setText("");
        TextView second_meal = findViewById(R.id.meal_two);
        second_meal.setText("");
        TextView second_price = findViewById(R.id.price_two);
        second_price.setText("");
        TextView second_cuisine = findViewById(R.id.cuisine_two);
        second_cuisine.setText("");
        TextView second_reviews = findViewById(R.id.food_reviews_two);
        second_reviews.setText("");
        TextView third_name = findViewById(R.id.restaurant_three);
        third_name.setText("");
        TextView third_home = findViewById(R.id.address_three);
        third_home.setText("");
        TextView third_rating = findViewById(R.id.rating_three);
        third_rating.setText("");
        TextView third_meal = findViewById(R.id.meal_three);
        third_meal.setText("");
        TextView third_price = findViewById(R.id.price_three);
        third_price.setText("");
        TextView third_cuisine = findViewById(R.id.cuisine_three);
        third_cuisine.setText("");
        TextView third_reviews = findViewById(R.id.food_reviews_three);
        third_reviews.setText("");
        ImageView foto = findViewById(R.id.r_one_pic);
        foto.setImageBitmap(null);
        Log.v("Button", "Image is Null");
        ImageView second_foto = findViewById(R.id.r_two_pic);
        second_foto.setImageBitmap(null);
        ImageView third_foto = findViewById(R.id.r_three_pic);
        third_foto.setImageBitmap(null);
        int num_one = Integer.parseInt(number_one.getText().toString());
        int num_two = Integer.parseInt(number_two.getText().toString());
        int num_three = Integer.parseInt(number_three.getText().toString());
        num_one = num_one + 3;
        num_two = num_two + 3;
        num_three = num_three + 3;
        number_one.setText(num_one + "");
        number_two.setText(num_two + "");
        number_three.setText(num_three + "");
        Log.v("POSITION:", "Before Next");
        Log.v("POSITION:", main_cursor.getPosition() + "");
        if (main_cursor.moveToPosition(num_one - 1) == true) {
            Log.v("POSITION:", "First Next");
            Log.v("POSITION:", main_cursor.getPosition() + "");
            name.setText(main_cursor.getString(0));
            home.setText(main_cursor.getString(1));
            rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7));
            meal.setText(main_cursor.getString(3));
            price.setText(main_cursor.getString(4));
            cuisine.setText(main_cursor.getString(5));
            byte[] b_photo = main_cursor.getBlob(6);
            Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
            foto.setBackgroundResource(0);
            foto.setImageBitmap(picture);
            reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
            if (main_cursor.moveToPosition(num_two - 1) == true) {
                Log.v("POSITION:", "Second Next");
                Log.v("POSITION:", main_cursor.getPosition() + "");
                second_name.setText(main_cursor.getString(0));
                second_home.setText(main_cursor.getString(1));
                second_rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7) + "");
                second_meal.setText(main_cursor.getString(3));
                second_price.setText(main_cursor.getString(4));
                second_cuisine.setText(main_cursor.getString(5));
                byte[] second_b_photo = main_cursor.getBlob(6);
                Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                second_foto.setBackgroundResource(0);
                second_foto.setImageBitmap(second_picture);
                second_reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                if (main_cursor.moveToPosition(num_three - 1) == true) {
                    Log.v("POSITION:", "Third Next");
                    Log.v("POSITION:", main_cursor.getPosition() + "");
                    third_name.setText(main_cursor.getString(0));
                    third_home.setText(main_cursor.getString(1));
                    third_rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7) + "");
                    third_meal.setText(main_cursor.getString(3));
                    third_price.setText(main_cursor.getString(4));
                    third_cuisine.setText(main_cursor.getString(5));
                    byte[] third_b_photo = main_cursor.getBlob(6);
                    Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                    third_foto.setBackgroundResource(0);
                    third_foto.setImageBitmap(third_picture);
                    third_reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                }
            }
        }
    }

    public void previous_row(View view) {
        Log.v("Button", "Previous");
        TextView name = findViewById(R.id.restaurant_one);
        name.setText("");
        TextView home = findViewById(R.id.address_one);
        home.setText("");
        TextView rating = findViewById(R.id.rating_one);
        rating.setText("");
        TextView meal = findViewById(R.id.meal_one);
        meal.setText("");
        TextView price = findViewById(R.id.price_one);
        price.setText("");
        TextView cuisine = findViewById(R.id.cuisine_one);
        cuisine.setText("");
        TextView reviews = findViewById(R.id.food_reviews_one);
        reviews.setText("");
        TextView number_one = findViewById(R.id.one);
        TextView number_two = findViewById(R.id.two);
        TextView number_three = findViewById(R.id.three);
        TextView second_name = findViewById(R.id.restaurant_two);
        second_name.setText("");
        TextView second_home = findViewById(R.id.address_two);
        second_home.setText("");
        TextView second_rating = findViewById(R.id.rating_two);
        second_rating.setText("");
        TextView second_meal = findViewById(R.id.meal_two);
        second_meal.setText("");
        TextView second_price = findViewById(R.id.price_two);
        second_price.setText("");
        TextView second_cuisine = findViewById(R.id.cuisine_two);
        second_cuisine.setText("");
        TextView second_reviews = findViewById(R.id.food_reviews_two);
        second_reviews.setText("");
        TextView third_name = findViewById(R.id.restaurant_three);
        third_name.setText("");
        TextView third_home = findViewById(R.id.address_three);
        third_home.setText("");
        TextView third_rating = findViewById(R.id.rating_three);
        third_rating.setText("");
        TextView third_meal = findViewById(R.id.meal_three);
        third_meal.setText("");
        TextView third_price = findViewById(R.id.price_three);
        third_price.setText("");
        TextView third_cuisine = findViewById(R.id.cuisine_three);
        third_cuisine.setText("");
        TextView third_reviews = findViewById(R.id.food_reviews_three);
        third_reviews.setText("");
        ImageView foto = findViewById(R.id.r_one_pic);
        foto.setImageBitmap(null);
        ImageView second_foto = findViewById(R.id.r_two_pic);
        second_foto.setImageBitmap(null);
        ImageView third_foto = findViewById(R.id.r_three_pic);
        third_foto.setImageBitmap(null);
        int test = Integer.parseInt(number_one.getText().toString());
        if (test == 1) {
            setContentView(R.layout.mealz);
        } else {
            int num_one = Integer.parseInt(number_one.getText().toString());
            int num_two = Integer.parseInt(number_two.getText().toString());
            int num_three = Integer.parseInt(number_three.getText().toString());
            num_one = num_one - 3;
            num_two = num_two - 3;
            num_three = num_three - 3;
            number_one.setText(num_one + "");
            number_two.setText(num_two + "");
            number_three.setText(num_three + "");
            int reset_position = main_cursor.getPosition();
            Log.v("POSITION:", "Before Previous");
            Log.v("POSITION:", main_cursor.getPosition() + "");
            if (main_cursor.moveToPosition(num_three - 1) == true) {
                Log.v("POSITION:", "First Previous");
                Log.v("POSITION:", main_cursor.getPosition() + "");
                third_name.setText(main_cursor.getString(0));
                third_home.setText(main_cursor.getString(1));
                third_rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7));
                third_meal.setText(main_cursor.getString(3));
                third_price.setText(main_cursor.getString(4));
                third_cuisine.setText(main_cursor.getString(5));
                byte[] b_photo = main_cursor.getBlob(6);
                Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
                third_foto.setBackgroundResource(0);
                third_foto.setImageBitmap(picture);
                third_reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
            }
            if (main_cursor.moveToPosition(num_two - 1) == true) {
                Log.v("POSITION:", "Second Previous");
                Log.v("POSITION:", main_cursor.getPosition() + "");
                second_name.setText(main_cursor.getString(0));
                second_home.setText(main_cursor.getString(1));
                second_rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7) + "");
                second_meal.setText(main_cursor.getString(3));
                second_price.setText(main_cursor.getString(4));
                second_cuisine.setText(main_cursor.getString(5));
                byte[] second_b_photo = main_cursor.getBlob(6);
                Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                second_foto.setBackgroundResource(0);
                second_foto.setImageBitmap(second_picture);
                second_reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
            }
            if (main_cursor.moveToPosition(num_one - 1) == true) {
                        Log.v("POSITION:", "Third Previous");
                        Log.v("POSITION:", main_cursor.getPosition() + "");
                        name.setText(main_cursor.getString(0));
                        home.setText(main_cursor.getString(1));
                        rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7) + "");
                        meal.setText(main_cursor.getString(3));
                        price.setText(main_cursor.getString(4));
                        cuisine.setText(main_cursor.getString(5));
                        byte[] third_b_photo = main_cursor.getBlob(6);
                        Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                        foto.setBackgroundResource(0);
                        foto.setImageBitmap(third_picture);
                        reviews.setText("Number of Reviews:" + main_cursor.getInt(7));
                    }
                }
    }

    public void life_next_row(View view) {
        Log.v("Button", "Next");
        TextView name = findViewById(R.id.life_name_one);
        name.setText("");
        TextView home = findViewById(R.id.life_address_one);
        home.setText("");
        TextView rating = findViewById(R.id.life_rating_one);
        rating.setText("");
        TextView category = findViewById(R.id.life_category_one);
        category.setText("");
        TextView price = findViewById(R.id.life_price_one);
        price.setText("");
        TextView service = findViewById(R.id.life_service_one);
        service.setText("");
        TextView review = findViewById(R.id.life_review_one);
        review.setText("");
        TextView number_one = findViewById(R.id.life_one);
        TextView number_two = findViewById(R.id.life_two);
        TextView number_three = findViewById(R.id.life_three);
        TextView second_name = findViewById(R.id.life_name_two);
        second_name.setText("");
        TextView second_home = findViewById(R.id.life_address_two);
        second_home.setText("");
        TextView second_rating = findViewById(R.id.life_rating_two);
        second_rating.setText("");
        TextView second_category = findViewById(R.id.life_category_two);
        second_category.setText("");
        TextView second_price = findViewById(R.id.life_price_two);
        second_price.setText("");
        TextView second_service = findViewById(R.id.life_service_two);
        second_service.setText("");
        TextView second_review = findViewById(R.id.life_review_two);
        second_review.setText("");
        TextView third_name = findViewById(R.id.life_name_three);
        third_name.setText("");
        TextView third_home = findViewById(R.id.life_address_three);
        third_home.setText("");
        TextView third_rating = findViewById(R.id.life_rating_three);
        third_rating.setText("");
        TextView third_category = findViewById(R.id.life_category_three);
        third_category.setText("");
        TextView third_price = findViewById(R.id.life_price_three);
        third_price.setText("");
        TextView third_service = findViewById(R.id.life_service_three);
        third_service.setText("");
        TextView third_review = findViewById(R.id.life_review_three);
        third_review.setText("");
        ImageView foto = findViewById(R.id.life_one_pic);
        foto.setImageBitmap(null);
        Log.v("Button", "Image is Null");
        ImageView second_foto = findViewById(R.id.life_two_pic);
        second_foto.setImageBitmap(null);
        ImageView third_foto = findViewById(R.id.life_three_pic);
        third_foto.setImageBitmap(null);
        int num_one = Integer.parseInt(number_one.getText().toString());
        int num_two = Integer.parseInt(number_two.getText().toString());
        int num_three = Integer.parseInt(number_three.getText().toString());
        num_one = num_one + 3;
        num_two = num_two + 3;
        num_three = num_three + 3;
        number_one.setText(num_one + "");
        number_two.setText(num_two + "");
        number_three.setText(num_three + "");
        Log.v("POSITION:", "Before Next");
        Log.v("POSITION:", main_cursor.getPosition() + "");
        if (main_cursor.moveToPosition(num_one - 1) == true) {
            Log.v("POSITION:", "First Next");
            Log.v("POSITION:", main_cursor.getPosition() + "");
            name.setText(main_cursor.getString(0));
            home.setText(main_cursor.getString(1));
            rating.setText("Rating:" + main_cursor.getInt(2)/(double) main_cursor.getInt(7));
            category.setText(main_cursor.getString(3));
            price.setText(main_cursor.getString(4));
            service.setText("Service:" + main_cursor.getDouble(5)/(double) main_cursor.getInt(7));
            byte[] b_photo = main_cursor.getBlob(6);
            Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
            foto.setBackgroundResource(0);
            foto.setImageBitmap(picture);
            review.setText("Number of Reviews:" + main_cursor.getInt(7));
            if (main_cursor.moveToPosition(num_two - 1) == true) {
                Log.v("POSITION:", "Second Next");
                Log.v("POSITION:", main_cursor.getPosition() + "");
                second_name.setText(main_cursor.getString(0));
                second_home.setText(main_cursor.getString(1));
                second_rating.setText("Rating:" + main_cursor.getInt(2)/(double) main_cursor.getInt(7) + "");
                second_category.setText(main_cursor.getString(3));
                second_price.setText(main_cursor.getString(4));
                second_service.setText("Service:" + main_cursor.getDouble(5)/(double) main_cursor.getInt(7));
                byte[] second_b_photo = main_cursor.getBlob(6);
                Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                second_foto.setBackgroundResource(0);
                second_foto.setImageBitmap(second_picture);
                second_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                if (main_cursor.moveToPosition(num_three - 1) == true) {
                    Log.v("POSITION:", "Third Next");
                    Log.v("POSITION:", main_cursor.getPosition() + "");
                    third_name.setText(main_cursor.getString(0));
                    third_home.setText(main_cursor.getString(1));
                    third_rating.setText("Rating:" + main_cursor.getInt(2)/(double) main_cursor.getInt(7) + "");
                    third_category.setText(main_cursor.getString(3));
                    third_price.setText(main_cursor.getString(4));
                    third_service.setText("Service:" + main_cursor.getDouble(5)/(double) main_cursor.getInt(7));
                    byte[] third_b_photo = main_cursor.getBlob(6);
                    Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                    third_foto.setBackgroundResource(0);
                    third_foto.setImageBitmap(third_picture);
                    third_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                }
            }
        }
    }

    public void life_previous_row(View view) {
        Log.v("Button", "Previous");
        TextView name = findViewById(R.id.life_name_one);
        name.setText("");
        TextView home = findViewById(R.id.life_address_one);
        home.setText("");
        TextView rating = findViewById(R.id.life_rating_one);
        rating.setText("");
        TextView category = findViewById(R.id.life_category_one);
        category.setText("");
        TextView price = findViewById(R.id.life_price_one);
        price.setText("");
        TextView service = findViewById(R.id.life_service_one);
        service.setText("");
        TextView review = findViewById(R.id.life_review_one);
        review.setText("");
        TextView number_one = findViewById(R.id.life_one);
        TextView number_two = findViewById(R.id.life_two);
        TextView number_three = findViewById(R.id.life_three);
        TextView second_name = findViewById(R.id.life_name_two);
        second_name.setText("");
        TextView second_home = findViewById(R.id.life_address_two);
        second_home.setText("");
        TextView second_rating = findViewById(R.id.life_rating_two);
        second_rating.setText("");
        TextView second_category = findViewById(R.id.life_category_two);
        second_category.setText("");
        TextView second_price = findViewById(R.id.life_price_two);
        second_price.setText("");
        TextView second_service = findViewById(R.id.life_service_two);
        second_service.setText("");
        TextView second_review = findViewById(R.id.life_review_two);
        second_review.setText("");
        TextView third_name = findViewById(R.id.life_name_three);
        third_name.setText("");
        TextView third_home = findViewById(R.id.life_address_three);
        third_home.setText("");
        TextView third_rating = findViewById(R.id.life_rating_three);
        third_rating.setText("");
        TextView third_category = findViewById(R.id.life_category_three);
        third_category.setText("");
        TextView third_price = findViewById(R.id.life_price_three);
        third_price.setText("");
        TextView third_service = findViewById(R.id.life_service_three);
        third_service.setText("");
        TextView third_review = findViewById(R.id.life_review_three);
        third_review.setText("");
        ImageView foto = findViewById(R.id.life_one_pic);
        foto.setImageBitmap(null);
        ImageView second_foto = findViewById(R.id.life_two_pic);
        second_foto.setImageBitmap(null);
        ImageView third_foto = findViewById(R.id.life_three_pic);
        third_foto.setImageBitmap(null);
        int test = Integer.parseInt(number_one.getText().toString());
        if (test == 1) {
            setContentView(R.layout.l_style);
        } else {
            int num_one = Integer.parseInt(number_one.getText().toString());
            int num_two = Integer.parseInt(number_two.getText().toString());
            int num_three = Integer.parseInt(number_three.getText().toString());
            num_one = num_one - 3;
            num_two = num_two - 3;
            num_three = num_three - 3;
            number_one.setText(num_one + "");
            number_two.setText(num_two + "");
            number_three.setText(num_three + "");
            int reset_position = main_cursor.getPosition();
            Log.v("POSITION:", "Before Previous");
            Log.v("POSITION:", main_cursor.getPosition() + "");
            if (main_cursor.moveToPosition(num_three - 1) == true) {
                Log.v("POSITION:", "First Previous");
                Log.v("POSITION:", main_cursor.getPosition() + "");
                third_name.setText(main_cursor.getString(0));
                third_home.setText(main_cursor.getString(1));
                third_rating.setText("Rating:" + main_cursor.getInt(2)/(double) main_cursor.getInt(7));
                third_category.setText(main_cursor.getString(3));
                third_price.setText(main_cursor.getString(4));
                third_service.setText("Service:" + main_cursor.getDouble(5)/(double) main_cursor.getInt(7));
                byte[] b_photo = main_cursor.getBlob(6);
                Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
                third_foto.setBackgroundResource(0);
                third_foto.setImageBitmap(picture);
                third_review.setText("Number of Reviews:" + main_cursor.getInt(7));
            }
            if (main_cursor.moveToPosition(num_two - 1) == true) {
                Log.v("POSITION:", "Second Previous");
                Log.v("POSITION:", main_cursor.getPosition() + "");
                second_name.setText(main_cursor.getString(0));
                second_home.setText(main_cursor.getString(1));
                second_rating.setText("Rating:" + main_cursor.getInt(2)/(double) main_cursor.getInt(7) + "");
                second_category.setText(main_cursor.getString(3));
                second_price.setText(main_cursor.getString(4));
                second_service.setText("Service:" + main_cursor.getDouble(5)/(double) main_cursor.getInt(7));
                byte[] second_b_photo = main_cursor.getBlob(6);
                Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                second_foto.setBackgroundResource(0);
                second_foto.setImageBitmap(second_picture);
                second_review.setText("Number of Reviews:" + main_cursor.getInt(7));
            }
            if (main_cursor.moveToPosition(num_one - 1) == true) {
                Log.v("POSITION:", "Third Previous");
                Log.v("POSITION:", main_cursor.getPosition() + "");
                name.setText(main_cursor.getString(0));
                home.setText(main_cursor.getString(1));
                rating.setText("Rating:" + main_cursor.getInt(2)/(double) main_cursor.getInt(7) + "");
                category.setText(main_cursor.getString(3));
                price.setText(main_cursor.getString(4));
                service.setText("Service:" + main_cursor.getDouble(5)/(double) main_cursor.getInt(7));
                byte[] third_b_photo = main_cursor.getBlob(6);
                Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                foto.setBackgroundResource(0);
                foto.setImageBitmap(third_picture);
                review.setText("Number of Reviews:" + main_cursor.getInt(7));
            }
        }
    }

    public void fun_next_row(View view) {
        Log.v("Button", "Next");
        TextView name = findViewById(R.id.fun_name_one);
        name.setText("");
        TextView home = findViewById(R.id.fun_address_one);
        home.setText("");
        TextView rating = findViewById(R.id.fun_rating_one);
        rating.setText("");
        TextView category = findViewById(R.id.fun_category_one);
        category.setText("");
        TextView price = findViewById(R.id.fun_price_one);
        price.setText("");
        TextView age_group = findViewById(R.id.fun_age_group_one);
        age_group.setText("");
        TextView review = findViewById(R.id.fun_review_one);
        review.setText("");
        TextView number_one = findViewById(R.id.fun_one);
        TextView number_two = findViewById(R.id.fun_two);
        TextView number_three = findViewById(R.id.fun_three);
        TextView second_name = findViewById(R.id.fun_name_two);
        second_name.setText("");
        TextView second_home = findViewById(R.id.fun_address_two);
        second_home.setText("");
        TextView second_rating = findViewById(R.id.fun_rating_two);
        second_rating.setText("");
        TextView second_category = findViewById(R.id.fun_category_two);
        second_category.setText("");
        TextView second_price = findViewById(R.id.fun_price_two);
        second_price.setText("");
        TextView second_age_group = findViewById(R.id.fun_age_group_two);
        second_age_group.setText("");
        TextView second_review = findViewById(R.id.fun_review_two);
        second_review.setText("");
        TextView third_name = findViewById(R.id.fun_name_three);
        third_name.setText("");
        TextView third_home = findViewById(R.id.fun_address_three);
        third_home.setText("");
        TextView third_rating = findViewById(R.id.fun_rating_three);
        third_rating.setText("");
        TextView third_category = findViewById(R.id.fun_category_three);
        third_category.setText("");
        TextView third_price = findViewById(R.id.fun_price_three);
        third_price.setText("");
        TextView third_age_group = findViewById(R.id.fun_age_group_three);
        third_age_group.setText("");
        TextView third_review = findViewById(R.id.fun_review_three);
        third_review.setText("");
        ImageView foto = findViewById(R.id.fun_one_pic);
        foto.setImageBitmap(null);
        Log.v("Button", "Image is Null");
        ImageView second_foto = findViewById(R.id.fun_two_pic);
        second_foto.setImageBitmap(null);
        ImageView third_foto = findViewById(R.id.fun_three_pic);
        third_foto.setImageBitmap(null);
        int num_one = Integer.parseInt(number_one.getText().toString());
        int num_two = Integer.parseInt(number_two.getText().toString());
        int num_three = Integer.parseInt(number_three.getText().toString());
        num_one = num_one + 3;
        num_two = num_two + 3;
        num_three = num_three + 3;
        number_one.setText(num_one + "");
        number_two.setText(num_two + "");
        number_three.setText(num_three + "");
        Log.v("POSITION:", "Before Next");
        Log.v("POSITION:", main_cursor.getPosition() + "");
        if (main_cursor.moveToPosition(num_one - 1) == true) {
            Log.v("POSITION:", "First Next");
            Log.v("POSITION:", main_cursor.getPosition() + "");
            name.setText(main_cursor.getString(0));
            home.setText(main_cursor.getString(1));
            rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7));
            category.setText(main_cursor.getString(3));
            price.setText(main_cursor.getString(4));
            age_group.setText(main_cursor.getString(5));
            byte[] b_photo = main_cursor.getBlob(6);
            Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
            foto.setBackgroundResource(0);
            foto.setImageBitmap(picture);
            review.setText("Number of Reviews:" + main_cursor.getInt(7));
            if (main_cursor.moveToPosition(num_two - 1) == true) {
                Log.v("POSITION:", "Second Next");
                Log.v("POSITION:", main_cursor.getPosition() + "");
                second_name.setText(main_cursor.getString(0));
                second_home.setText(main_cursor.getString(1));
                second_rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7) + "");
                second_category.setText(main_cursor.getString(3));
                second_price.setText(main_cursor.getString(4));
                second_age_group.setText(main_cursor.getString(5));
                byte[] second_b_photo = main_cursor.getBlob(6);
                Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                second_foto.setBackgroundResource(0);
                second_foto.setImageBitmap(second_picture);
                second_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                if (main_cursor.moveToPosition(num_three - 1) == true) {
                    Log.v("POSITION:", "Third Next");
                    Log.v("POSITION:", main_cursor.getPosition() + "");
                    third_name.setText(main_cursor.getString(0));
                    third_home.setText(main_cursor.getString(1));
                    third_rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7) + "");
                    third_category.setText(main_cursor.getString(3));
                    third_price.setText(main_cursor.getString(4));
                    third_age_group.setText(main_cursor.getString(5));
                    byte[] third_b_photo = main_cursor.getBlob(6);
                    Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                    third_foto.setBackgroundResource(0);
                    third_foto.setImageBitmap(third_picture);
                    third_review.setText("Number of Reviews:" + main_cursor.getInt(7));
                }
            }
        }
    }

    public void fun_previous_row(View view) {
        Log.v("Button", "Previous");
        TextView name = findViewById(R.id.fun_name_one);
        name.setText("");
        TextView home = findViewById(R.id.fun_address_one);
        home.setText("");
        TextView rating = findViewById(R.id.fun_rating_one);
        rating.setText("");
        TextView category = findViewById(R.id.fun_category_one);
        category.setText("");
        TextView price = findViewById(R.id.fun_price_one);
        price.setText("");
        TextView age_group = findViewById(R.id.fun_age_group_one);
        age_group.setText("");
        TextView review = findViewById(R.id.fun_review_one);
        review.setText("");
        TextView number_one = findViewById(R.id.fun_one);
        TextView number_two = findViewById(R.id.fun_two);
        TextView number_three = findViewById(R.id.fun_three);
        TextView second_name = findViewById(R.id.fun_name_two);
        second_name.setText("");
        TextView second_home = findViewById(R.id.fun_address_two);
        second_home.setText("");
        TextView second_rating = findViewById(R.id.fun_rating_two);
        second_rating.setText("");
        TextView second_category = findViewById(R.id.fun_category_two);
        second_category.setText("");
        TextView second_price = findViewById(R.id.fun_price_two);
        second_price.setText("");
        TextView second_age_group = findViewById(R.id.fun_age_group_two);
        second_age_group.setText("");
        TextView second_review = findViewById(R.id.fun_review_two);
        second_review.setText("");
        TextView third_name = findViewById(R.id.fun_name_three);
        third_name.setText("");
        TextView third_home = findViewById(R.id.fun_address_three);
        third_home.setText("");
        TextView third_rating = findViewById(R.id.fun_rating_three);
        third_rating.setText("");
        TextView third_category = findViewById(R.id.fun_category_three);
        third_category.setText("");
        TextView third_price = findViewById(R.id.fun_price_three);
        third_price.setText("");
        TextView third_age_group = findViewById(R.id.fun_age_group_three);
        third_age_group.setText("");
        TextView third_review = findViewById(R.id.fun_review_three);
        third_review.setText("");
        ImageView foto = findViewById(R.id.fun_one_pic);
        foto.setImageBitmap(null);
        ImageView second_foto = findViewById(R.id.fun_two_pic);
        second_foto.setImageBitmap(null);
        ImageView third_foto = findViewById(R.id.fun_three_pic);
        third_foto.setImageBitmap(null);
        int test = Integer.parseInt(number_one.getText().toString());
        if (test == 1) {
            setContentView(R.layout.fun);
        } else {
            int num_one = Integer.parseInt(number_one.getText().toString());
            int num_two = Integer.parseInt(number_two.getText().toString());
            int num_three = Integer.parseInt(number_three.getText().toString());
            num_one = num_one - 3;
            num_two = num_two - 3;
            num_three = num_three - 3;
            number_one.setText(num_one + "");
            number_two.setText(num_two + "");
            number_three.setText(num_three + "");
            int reset_position = main_cursor.getPosition();
            Log.v("POSITION:", "Before Previous");
            Log.v("POSITION:", main_cursor.getPosition() + "");
            if (main_cursor.moveToPosition(num_three - 1) == true) {
                Log.v("POSITION:", "First Previous");
                Log.v("POSITION:", main_cursor.getPosition() + "");
                third_name.setText(main_cursor.getString(0));
                third_home.setText(main_cursor.getString(1));
                third_rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7));
                third_category.setText(main_cursor.getString(3));
                third_price.setText(main_cursor.getString(4));
                third_age_group.setText(main_cursor.getString(5));
                byte[] b_photo = main_cursor.getBlob(6);
                Bitmap picture = BitmapFactory.decodeByteArray(b_photo, 0, b_photo.length);
                third_foto.setBackgroundResource(0);
                third_foto.setImageBitmap(picture);
                third_review.setText("Number of Reviews:" + main_cursor.getInt(7));
            }
            if (main_cursor.moveToPosition(num_two - 1) == true) {
                Log.v("POSITION:", "Second Previous");
                Log.v("POSITION:", main_cursor.getPosition() + "");
                second_name.setText(main_cursor.getString(0));
                second_home.setText(main_cursor.getString(1));
                second_rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7) + "");
                second_category.setText(main_cursor.getString(3));
                second_price.setText(main_cursor.getString(4));
                second_age_group.setText(main_cursor.getString(5));
                byte[] second_b_photo = main_cursor.getBlob(6);
                Bitmap second_picture = BitmapFactory.decodeByteArray(second_b_photo, 0, second_b_photo.length);
                second_foto.setBackgroundResource(0);
                second_foto.setImageBitmap(second_picture);
                second_review.setText("Number of Reviews:" + main_cursor.getInt(7));
            }
            if (main_cursor.moveToPosition(num_one - 1) == true) {
                Log.v("POSITION:", "Third Previous");
                Log.v("POSITION:", main_cursor.getPosition() + "");
                name.setText(main_cursor.getString(0));
                home.setText(main_cursor.getString(1));
                rating.setText("Rating:" + main_cursor.getDouble(2)/(double) main_cursor.getInt(7) + "");
                category.setText(main_cursor.getString(3));
                price.setText(main_cursor.getString(4));
                age_group.setText(main_cursor.getString(5));
                byte[] third_b_photo = main_cursor.getBlob(6);
                Bitmap third_picture = BitmapFactory.decodeByteArray(third_b_photo, 0, third_b_photo.length);
                foto.setBackgroundResource(0);
                foto.setImageBitmap(third_picture);
                review.setText("Number of Reviews:" + main_cursor.getInt(7));
            }
        }
    }

    public void food_yellow_star(View view) {
        if (view.getTag().toString() != "yellow") {
            view.setBackgroundResource(R.drawable.yellow_star);
            view.setTag("yellow");
            num_stars = num_stars + 1;
            Log.v("FOCUS", num_stars + "");
            
        } else if (view.getTag().toString() == "yellow") {
            view.setBackgroundResource(R.drawable.star);
            view.setTag("white");
            num_stars = num_stars - 1;
            Log.v("FOCUS", num_stars + "");
        }
    }

    public void lifestyle_yellow_star(View view) {
        if (view.getTag().toString() != "yellow") {
            view.setBackgroundResource(R.drawable.yellow_star);
            view.setTag("yellow");
            num_stars = num_stars + 1;
            Log.v("FOCUS", num_stars + "");
        } else if (view.getTag().toString() == "yellow") {
            view.setBackgroundResource(R.drawable.star);
            view.setTag("white");
            num_stars = num_stars - 1;
            Log.v("FOCUS", num_stars + "");
        }
    }

    public void lifestyle_service_star(View view) {
        if (view.getTag().toString() != "yellow") {
            view.setBackgroundResource(R.drawable.yellow_star);
            view.setTag("yellow");
            service_stars = service_stars + 1;
            Log.v("FOCUS", service_stars + "");
        } else if (view.getTag().toString() == "yellow") {
            view.setBackgroundResource(R.drawable.star);
            view.setTag("white");
            service_stars = service_stars - 1;
            Log.v("FOCUS", service_stars + "");
        }
    }

    public void fun_yellow_star(View view) {
        if (view.getTag().toString() != "yellow") {
            view.setBackgroundResource(R.drawable.yellow_star);
            view.setTag("yellow");
            num_stars = num_stars + 1;
        } else if (view.getTag().toString() == "yellow") {
            view.setBackgroundResource(R.drawable.star);
            view.setTag("white");
            num_stars = num_stars - 1;
        }
    }

    public void food_green_dot(View view) {
        Log.v("FOCUS", view.isFocusable() + "");
        if (view.isFocusable() == true) {
            view.setBackgroundResource(R.drawable.green_dot);
            view.setFocusable(false);
            if (view.getTag().toString().contains("Meal")) {
                mealz = view.getTag().toString();
            } else if (view.getTag().toString().contains("Price")) {
                prices = view.getTag().toString();
            } else if (view.getTag().toString().contains("Cuisine")) {
                food_type = view.getTag().toString();
            }
            Log.v("FOCUS", mealz + "");
        } else if (view.isFocusable() == false) {
            view.setBackgroundResource(R.drawable.circle);
            view.setFocusable(true);
            if (view.getTag().toString().contains("Meal")) {
                mealz = null;
            } else if (view.getTag().toString().contains("Price")) {
                prices = null;
            } else if (view.getTag().toString().contains("Cuisine")) {
                food_type = null;
            }
        }
    }

    public void lifestyle_green_dot(View view) {
        if (view.isFocusable() == true) {
            view.setBackgroundResource(R.drawable.green_dot);
            view.setFocusable(false);
            if (view.getTag().toString().contains("Category")) {
                category = view.getTag().toString();
            } else if (view.getTag().toString().contains("Price")) {
                lifestyle_price = view.getTag().toString();
            }
            Log.v("FOCUS", mealz + "");
        } else if (view.isFocusable() == false) {
            view.setBackgroundResource(R.drawable.circle);
            view.setFocusable(true);
            if (view.getTag().toString().contains("Category")) {
                category = null;
            } else if (view.getTag().toString().contains("Price")) {
                lifestyle_price = null;
            }
        }
    }

    public void fun__green_dot(View view) {
        if (view.isFocusable() == true) {
            view.setBackgroundResource(R.drawable.green_dot);
            view.setFocusable(false);
            if (view.getTag().toString().contains("Category")) {
                fun_category = view.getTag().toString();
            } else if (view.getTag().toString().contains("Price")) {
                fun_price = view.getTag().toString();
            } else if (view.getTag().toString().contains("Age Group")) {
                age_group = view.getTag().toString();
            }
            Log.v("FOCUS", mealz + "");
        } else if (view.isFocusable() == false) {
            view.setBackgroundResource(R.drawable.circle);
            view.setFocusable(true);
            if (view.getTag().toString().contains("Category")) {
                category = null;
            } else if (view.getTag().toString().contains("Price")) {
                lifestyle_price = null;
            } else if (view.getTag().toString().contains("Age Group")) {
                age_group = null;
            }
        }
    }

    public void food_photo(View view) {
        food_pic = true;
        Intent x = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(x,1);
    }

    protected void onActivityResult(int rc, int resc, Intent data) {
        if (food_pic == true) {
            ImageView image = null;
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            image = (ImageView) findViewById(R.id.food_pic);
            image.setBackgroundResource(0);
            image.setImageBitmap(bitmap);
            current = bitmap;
            food_pic = false;
        } else if (lifestyle_pic == true) {
            ImageView image = null;
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            image = (ImageView) findViewById(R.id.lifestyle_pic);
            image.setBackgroundResource(0);
            image.setImageBitmap(bitmap);
            current = bitmap;
            lifestyle_pic = false;

        } else if (fun_pic == true) {
            ImageView image = null;
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            image = (ImageView) findViewById(R.id.venue_pic);
            image.setBackgroundResource(0);
            image.setImageBitmap(bitmap);
            current = bitmap;
            lifestyle_pic = false;
        }
    }

    public void life_photo(View view) {
        lifestyle_pic = true;
        Intent x = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(x,1);
    }

    public void fun_photo(View view) {
        fun_pic = true;
        Intent x = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(x,1);
    }

    public void food_adv_search(View view) {

    }

    public void lifestyle_adv_search(View view) {

    }

    public void entertainment_adv_search(View view) {

    }

    public void food_review_analysis(View view) {
        TextView restaurant = findViewById(R.id.restaurant_one);
        TextView address = findViewById(R.id.address_one);
        String place = restaurant.getText().toString();
        String add = address.getText().toString();
        food_comment_result_name = place;
        food_comment_result_address = add;
        String five_star = "select * from Single_Food S where S.rating = 5 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor five = database_reviews.rawQuery(five_star, null);
        int cinco = five.getCount();
        String four_star = "select * from Single_Food S where S.rating = 4 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor four = database_reviews.rawQuery(four_star, null);
        int cuatro = four.getCount();
        String three_star = "select * from Single_Food S where S.rating = 3 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor three = database_reviews.rawQuery(three_star, null);
        int tres = three.getCount();
        String two_star = "select * from Single_Food S where S.rating = 2 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor two = database_reviews.rawQuery(two_star, null);
        int dos = two.getCount();
        String one_star = "select * from Single_Food S where S.rating = 1 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor one = database_reviews.rawQuery(one_star, null);
        int uno = one.getCount();
        setContentView(R.layout.food_review_breakdown);
        TextView title = findViewById(R.id.f_r_title);
        title.setText(place + " Reviews");
        TextView cinco_estrellas = findViewById(R.id.five_star);
        cinco_estrellas.setText(cinco + "");
        TextView cuatro_estrellas = findViewById(R.id.four_star);
        cuatro_estrellas.setText(cuatro + "");
        TextView tres_estrellas = findViewById(R.id.three_star);
        tres_estrellas.setText(tres + "");
        TextView dos_estrellas = findViewById(R.id.two_star);
        dos_estrellas.setText(dos + "");
        TextView uno_estrellas = findViewById(R.id.one_star);
        uno_estrellas.setText(uno + "");
    }

    public void food_review_two_analysis(View view) {
        TextView restaurant = findViewById(R.id.restaurant_two);
        TextView address = findViewById(R.id.address_two);
        String place = restaurant.getText().toString();
        String add = address.getText().toString();
        food_comment_result_name = place;
        food_comment_result_address = add;
        String five_star = "select * from Single_Food S where S.rating = 5 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor five = database_reviews.rawQuery(five_star, null);
        int cinco = five.getCount();
        String four_star = "select * from Single_Food S where S.rating = 4 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor four = database_reviews.rawQuery(four_star, null);
        int cuatro = four.getCount();
        String three_star = "select * from Single_Food S where S.rating = 3 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor three = database_reviews.rawQuery(three_star, null);
        int tres = three.getCount();
        String two_star = "select * from Single_Food S where S.rating = 2 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor two = database_reviews.rawQuery(two_star, null);
        int dos = two.getCount();
        String one_star = "select * from Single_Food S where S.rating = 1 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor one = database_reviews.rawQuery(one_star, null);
        int uno = one.getCount();
        setContentView(R.layout.food_review_breakdown);
        TextView title = findViewById(R.id.f_r_title);
        title.setText(place + " Reviews");
        TextView cinco_estrellas = findViewById(R.id.five_star);
        cinco_estrellas.setText(cinco + "");
        TextView cuatro_estrellas = findViewById(R.id.four_star);
        cuatro_estrellas.setText(cuatro + "");
        TextView tres_estrellas = findViewById(R.id.three_star);
        tres_estrellas.setText(tres + "");
        TextView dos_estrellas = findViewById(R.id.two_star);
        dos_estrellas.setText(dos + "");
        TextView uno_estrellas = findViewById(R.id.one_star);
        uno_estrellas.setText(uno + "");
    }

    public void food_review_three_analysis(View view) {
        TextView restaurant = findViewById(R.id.restaurant_three);
        TextView address = findViewById(R.id.address_three);
        String place = restaurant.getText().toString();
        String add = address.getText().toString();
        food_comment_result_name = place;
        food_comment_result_address = add;
        String five_star = "select * from Single_Food S where S.rating = 5 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor five = database_reviews.rawQuery(five_star, null);
        int cinco = five.getCount();
        String four_star = "select * from Single_Food S where S.rating = 4 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor four = database_reviews.rawQuery(four_star, null);
        int cuatro = four.getCount();
        String three_star = "select * from Single_Food S where S.rating = 3 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor three = database_reviews.rawQuery(three_star, null);
        int tres = three.getCount();
        String two_star = "select * from Single_Food S where S.rating = 2 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor two = database_reviews.rawQuery(two_star, null);
        int dos = two.getCount();
        String one_star = "select * from Single_Food S where S.rating = 1 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor one = database_reviews.rawQuery(one_star, null);
        int uno = one.getCount();
        setContentView(R.layout.food_review_breakdown);
        TextView title = findViewById(R.id.f_r_title);
        title.setText(place + " Reviews");
        TextView cinco_estrellas = findViewById(R.id.five_star);
        cinco_estrellas.setText(cinco + "");
        TextView cuatro_estrellas = findViewById(R.id.four_star);
        cuatro_estrellas.setText(cuatro + "");
        TextView tres_estrellas = findViewById(R.id.three_star);
        tres_estrellas.setText(tres + "");
        TextView dos_estrellas = findViewById(R.id.two_star);
        dos_estrellas.setText(dos + "");
        TextView uno_estrellas = findViewById(R.id.one_star);
        uno_estrellas.setText(uno + "");
    }

    public void life_review_analysis(View view) {
        TextView restaurant = findViewById(R.id.life_name_one);
        TextView address = findViewById(R.id.life_address_one);
        String place = restaurant.getText().toString();
        String add = address.getText().toString();
        life_comment_result_name = place;
        life_comment_result_address = add;
        String five_star = "select * from Single_Lifestyle S where S.rating = 5 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor five = database_reviews.rawQuery(five_star, null);
        int cinco = five.getCount();
        String four_star = "select * from Single_Lifestyle S where S.rating = 4 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor four = database_reviews.rawQuery(four_star, null);
        int cuatro = four.getCount();
        String three_star = "select * from Single_Lifestyle S where S.rating = 3 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor three = database_reviews.rawQuery(three_star, null);
        int tres = three.getCount();
        String two_star = "select * from Single_Lifestyle S where S.rating = 2 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor two = database_reviews.rawQuery(two_star, null);
        int dos = two.getCount();
        String one_star = "select * from Single_Lifestyle S where S.rating = 1 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor one = database_reviews.rawQuery(one_star, null);
        int uno = one.getCount();
        setContentView(R.layout.life_review_breakdown);
        TextView title = findViewById(R.id.l_r_title);
        title.setText(life_comment_result_name + " Reviews");
        TextView cinco_estrellas = findViewById(R.id.five_star);
        cinco_estrellas.setText(cinco + "");
        TextView cuatro_estrellas = findViewById(R.id.four_star);
        cuatro_estrellas.setText(cuatro + "");
        TextView tres_estrellas = findViewById(R.id.three_star);
        tres_estrellas.setText(tres + "");
        TextView dos_estrellas = findViewById(R.id.two_star);
        dos_estrellas.setText(dos + "");
        TextView uno_estrellas = findViewById(R.id.one_star);
        uno_estrellas.setText(uno + "");
    }

    public void life_review_two_analysis(View view) {
        TextView restaurant = findViewById(R.id.life_name_two);
        TextView address = findViewById(R.id.life_address_two);
        String place = restaurant.getText().toString();
        String add = address.getText().toString();
        life_comment_result_name = place;
        life_comment_result_address = add;
        String five_star = "select * from Single_Lifestyle S where S.rating = 5 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor five = database_reviews.rawQuery(five_star, null);
        int cinco = five.getCount();
        String four_star = "select * from Single_Lifestyle S where S.rating = 4 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor four = database_reviews.rawQuery(four_star, null);
        int cuatro = four.getCount();
        String three_star = "select * from Single_Lifestyle S where S.rating = 3 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor three = database_reviews.rawQuery(three_star, null);
        int tres = three.getCount();
        String two_star = "select * from Single_Lifestyle S where S.rating = 2 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor two = database_reviews.rawQuery(two_star, null);
        int dos = two.getCount();
        String one_star = "select * from Single_Lifestyle S where S.rating = 1 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor one = database_reviews.rawQuery(one_star, null);
        int uno = one.getCount();
        setContentView(R.layout.life_review_breakdown);
        TextView title = findViewById(R.id.l_r_title);
        title.setText(place + " Reviews");
        TextView cinco_estrellas = findViewById(R.id.five_star);
        cinco_estrellas.setText(cinco + "");
        TextView cuatro_estrellas = findViewById(R.id.four_star);
        cuatro_estrellas.setText(cuatro + "");
        TextView tres_estrellas = findViewById(R.id.three_star);
        tres_estrellas.setText(tres + "");
        TextView dos_estrellas = findViewById(R.id.two_star);
        dos_estrellas.setText(dos + "");
        TextView uno_estrellas = findViewById(R.id.one_star);
        uno_estrellas.setText(uno + "");
    }

    public void life_review_three_analysis(View view) {
        TextView restaurant = findViewById(R.id.life_name_three);
        TextView address = findViewById(R.id.life_address_three);
        String place = restaurant.getText().toString();
        String add = address.getText().toString();
        life_comment_result_name = place;
        life_comment_result_address = add;
        String five_star = "select * from Single_Lifestyle S where S.rating = 5 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor five = database_reviews.rawQuery(five_star, null);
        int cinco = five.getCount();
        String four_star = "select * from Single_Lifestyle S where S.rating = 4 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor four = database_reviews.rawQuery(four_star, null);
        int cuatro = four.getCount();
        String three_star = "select * from Single_Lifestyle S where S.rating = 3 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor three = database_reviews.rawQuery(three_star, null);
        int tres = three.getCount();
        String two_star = "select * from Single_Lifestyle S where S.rating = 2 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor two = database_reviews.rawQuery(two_star, null);
        int dos = two.getCount();
        String one_star = "select * from Single_Lifestyle S where S.rating = 1 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor one = database_reviews.rawQuery(one_star, null);
        int uno = one.getCount();
        setContentView(R.layout.life_review_breakdown);
        TextView title = findViewById(R.id.l_r_title);
        title.setText(place + " Reviews");
        TextView cinco_estrellas = findViewById(R.id.five_star);
        cinco_estrellas.setText(cinco + "");
        TextView cuatro_estrellas = findViewById(R.id.four_star);
        cuatro_estrellas.setText(cuatro + "");
        TextView tres_estrellas = findViewById(R.id.three_star);
        tres_estrellas.setText(tres + "");
        TextView dos_estrellas = findViewById(R.id.two_star);
        dos_estrellas.setText(dos + "");
        TextView uno_estrellas = findViewById(R.id.one_star);
        uno_estrellas.setText(uno + "");
    }

    public void fun_review_analysis(View view) {
        TextView restaurant = findViewById(R.id.fun_name_one);
        TextView address = findViewById(R.id.fun_address_one);
        String place = restaurant.getText().toString();
        String add = address.getText().toString();
        fun_comment_result_name = place;
        fun_comment_result_address = add;
        String five_star = "select * from Single_Entertainment S where S.rating = 5 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor five = database_reviews.rawQuery(five_star, null);
        int cinco = five.getCount();
        String four_star = "select * from Single_Entertainment S where S.rating = 4 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor four = database_reviews.rawQuery(four_star, null);
        int cuatro = four.getCount();
        String three_star = "select * from Single_Entertainment S where S.rating = 3 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor three = database_reviews.rawQuery(three_star, null);
        int tres = three.getCount();
        String two_star = "select * from Single_Entertainment S where S.rating = 2 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor two = database_reviews.rawQuery(two_star, null);
        int dos = two.getCount();
        String one_star = "select * from Single_Entertainment S where S.rating = 1 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor one = database_reviews.rawQuery(one_star, null);
        int uno = one.getCount();
        setContentView(R.layout.fun_review_breakdown);
        TextView title = findViewById(R.id.e_r_title);
        title.setText(place + " Reviews");
        TextView cinco_estrellas = findViewById(R.id.five_star);
        cinco_estrellas.setText(cinco + "");
        TextView cuatro_estrellas = findViewById(R.id.four_star);
        cuatro_estrellas.setText(cuatro + "");
        TextView tres_estrellas = findViewById(R.id.three_star);
        tres_estrellas.setText(tres + "");
        TextView dos_estrellas = findViewById(R.id.two_star);
        dos_estrellas.setText(dos + "");
        TextView uno_estrellas = findViewById(R.id.one_star);
        uno_estrellas.setText(uno + "");
    }

    public void fun_review_two_analysis(View view) {
        TextView restaurant = findViewById(R.id.fun_name_two);
        TextView address = findViewById(R.id.fun_address_two);
        String place = restaurant.getText().toString();
        String add = address.getText().toString();
        fun_comment_result_name = place;
        fun_comment_result_address = add;
        String five_star = "select * from Single_Entertainment S where S.rating = 5 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor five = database_reviews.rawQuery(five_star, null);
        int cinco = five.getCount();
        String four_star = "select * from Single_Entertainment S where S.rating = 4 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor four = database_reviews.rawQuery(four_star, null);
        int cuatro = four.getCount();
        String three_star = "select * from Single_Entertainment S where S.rating = 3 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor three = database_reviews.rawQuery(three_star, null);
        int tres = three.getCount();
        String two_star = "select * from Single_Entertainment S where S.rating = 2 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor two = database_reviews.rawQuery(two_star, null);
        int dos = two.getCount();
        String one_star = "select * from Single_Entertainment S where S.rating = 1 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor one = database_reviews.rawQuery(one_star, null);
        int uno = one.getCount();
        setContentView(R.layout.fun_review_breakdown);
        TextView title = findViewById(R.id.e_r_title);
        title.setText(place + " Reviews");
        TextView cinco_estrellas = findViewById(R.id.five_star);
        cinco_estrellas.setText(cinco + "");
        TextView cuatro_estrellas = findViewById(R.id.four_star);
        cuatro_estrellas.setText(cuatro + "");
        TextView tres_estrellas = findViewById(R.id.three_star);
        tres_estrellas.setText(tres + "");
        TextView dos_estrellas = findViewById(R.id.two_star);
        dos_estrellas.setText(dos + "");
        TextView uno_estrellas = findViewById(R.id.one_star);
        uno_estrellas.setText(uno + "");
    }

    public void fun_review_three_analysis(View view) {
        TextView restaurant = findViewById(R.id.fun_name_three);
        TextView address = findViewById(R.id.fun_address_three);
        String place = restaurant.getText().toString();
        String add = address.getText().toString();
        fun_comment_result_name = place;
        fun_comment_result_address = add;
        String five_star = "select * from Single_Entertainment S where S.rating = 5 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor five = database_reviews.rawQuery(five_star, null);
        int cinco = five.getCount();
        String four_star = "select * from Single_Entertainment S where S.rating = 4 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor four = database_reviews.rawQuery(four_star, null);
        int cuatro = four.getCount();
        String three_star = "select * from Single_Entertainment S where S.rating = 3 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor three = database_reviews.rawQuery(three_star, null);
        int tres = three.getCount();
        String two_star = "select * from Single_Entertainment S where S.rating = 2 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor two = database_reviews.rawQuery(two_star, null);
        int dos = two.getCount();
        String one_star = "select * from Single_Entertainment S where S.rating = 1 and S.Name = " + "'" + place + "'" + "" + "and S.Address = " + "'" + add + "'" + "";
        Cursor one = database_reviews.rawQuery(one_star, null);
        int uno = one.getCount();
        setContentView(R.layout.fun_review_breakdown);
        TextView title = findViewById(R.id.e_r_title);
        title.setText(place + " Reviews");
        TextView cinco_estrellas = findViewById(R.id.five_star);
        cinco_estrellas.setText(cinco + "");
        TextView cuatro_estrellas = findViewById(R.id.four_star);
        cuatro_estrellas.setText(cuatro + "");
        TextView tres_estrellas = findViewById(R.id.three_star);
        tres_estrellas.setText(tres + "");
        TextView dos_estrellas = findViewById(R.id.two_star);
        dos_estrellas.setText(dos + "");
        TextView uno_estrellas = findViewById(R.id.one_star);
        uno_estrellas.setText(uno + "");
    }

    public void food_comment(String name, String address, int rating) {
        num_stars = 0;
        food_comment_name = name;
        food_comment_address = address;
        food_comment_rating = rating;
        setContentView(R.layout.food_comment);
        TextView prompt = findViewById(R.id.food_talk);
        EditText comment = findViewById(R.id.food_blurb);
        prompt.setText("Would you like to Comment Further About " + name + "?");
    }

    public void life_comment(String name, String address, int rating) {
        num_stars = 0;
        fun_comment_name = name;
        fun_comment_address = address;
        fun_comment_rating = rating;
        setContentView(R.layout.fun_comment);
        TextView prompt = findViewById(R.id.fun_talk);
        EditText comment = findViewById(R.id.fun_blurb);
        prompt.setText("Would you like to Comment Further About " + name + "?");
    }

    public void fun_comment(String name, String address, int rating) {
        num_stars = 0;
        life_comment_name = name;
        life_comment_address = address;
        life_comment_rating = rating;
        setContentView(R.layout.life_comment);
        TextView prompt = findViewById(R.id.life_talk);
        EditText comment = findViewById(R.id.life_blurb);
        prompt.setText("Would you like to Comment Further About " + name + "?");
    }

    public void food_comment_submit(View view) {
        EditText comment = findViewById(R.id.food_blurb);
        ContentValues comment_vals = new ContentValues();
        if (comment.length() == 0) {
            Context no_comment = getApplicationContext();
            Toast blank = Toast.makeText(no_comment, "You Cannot Submit a Blank Comment", Toast.LENGTH_LONG);
            blank.show();
        } else if (comment.length() > 0) {
            comment_vals.put("Name", food_comment_name);
            comment_vals.put("Address", food_comment_address);
            comment_vals.put("Rating", food_comment_rating);
            comment_vals.put("Comment", comment.getText().toString());
            database_reviews.insert("Food_Comments", null, comment_vals);
            food_comment_name = null;
            food_comment_address = null;
            food_comment_rating = 0;
            Context bread = getApplicationContext();
            Toast toast = Toast.makeText(bread, "Comment Has Been Saved", Toast.LENGTH_LONG);
            toast.show();
            setContentView(R.layout.activity_main);
        }
    }

    public void life_comment_submit(View view){
            EditText comment = findViewById(R.id.life_blurb);
            ContentValues comment_vals = new ContentValues();
            if (comment.length() == 0) {
                Context no_comment = getApplicationContext();
                Toast blank = Toast.makeText(no_comment, "You Cannot Submit a Blank Comment", Toast.LENGTH_LONG);
                blank.show();
            } else if (comment.length() > 0) {
                comment_vals.put("Name", life_comment_name);
                comment_vals.put("Address", life_comment_address);
                comment_vals.put("Rating", life_comment_rating);
                comment_vals.put("Comment", comment.getText().toString());
                database_reviews.insert("Food_Comments", null, comment_vals);
                life_comment_name = null;
                life_comment_address = null;
                life_comment_rating = 0;
                Context bread = getApplicationContext();
                Toast toast = Toast.makeText(bread, "Comment Has Been Saved", Toast.LENGTH_LONG);
                toast.show();
                setContentView(R.layout.activity_main);
            }
        }

    public void fun_comment_submit(View view) {
        EditText comment = findViewById(R.id.fun_blurb);
        ContentValues comment_vals = new ContentValues();
        if (comment.length() == 0) {
            Context no_comment = getApplicationContext();
            Toast blank = Toast.makeText(no_comment, "You Cannot Submit a Blank Comment", Toast.LENGTH_LONG);
            blank.show();
        } else if (comment.length() > 0) {
            comment_vals.put("Name", fun_comment_name);
            comment_vals.put("Address", fun_comment_address);
            comment_vals.put("Rating", fun_comment_rating);
            comment_vals.put("Comment", comment.getText().toString());
            database_reviews.insert("Food_Comments", null, comment_vals);
            fun_comment_name = null;
            fun_comment_address = null;
            fun_comment_rating = 0;
            Context bread = getApplicationContext();
            Toast toast = Toast.makeText(bread, "Comment Has Been Saved", Toast.LENGTH_LONG);
            toast.show();
            setContentView(R.layout.activity_main);
        }
    }

    public void food_comment_results(View view) {
        String comment = "select F.Name, F.Rating, F.Comment from Food_Comments F where F.Name = " + "'" + food_comment_result_name + "'" + "" + "and F.Address = " + "'" + food_comment_result_address + "'" + "order by F.rating desc;";
        main_cursor = database_reviews.rawQuery(comment, null);
        if (main_cursor.getCount() == 0) {
            main_cursor.moveToFirst();
            setContentView(R.layout.f_c_r);
            TextView title = findViewById(R.id.f_c_r_title);
            TextView rating = findViewById(R.id.f_c_r_rating);
            TextView opinion = findViewById(R.id.f_c_r_comment);
            opinion.setText("NO COMMENTS");
        } else if (main_cursor.getCount() > 0) {
            main_cursor.moveToFirst();
            setContentView(R.layout.f_c_r);
            TextView title = findViewById(R.id.f_c_r_title);
            TextView rating = findViewById(R.id.f_c_r_rating);
            TextView opinion = findViewById(R.id.f_c_r_comment);
            title.setText(main_cursor.getString(0) + " Comments");
            rating.setText("Customer Rating:" + main_cursor.getInt(1));
            opinion.setText(main_cursor.getString(2));
        }
    }

    public void life_comment_results(View view) {
        String comment = "select F.Name, F.Rating, F.Comment from Food_Comments F where F.Name = " + "'" + life_comment_result_name + "'" + "" + "and F.Address = " + "'" + life_comment_result_address + "'" + "order by F.rating desc;";
        main_cursor = database_reviews.rawQuery(comment, null);
        if (main_cursor.getCount() == 0) {
            main_cursor.moveToFirst();
            setContentView(R.layout.f_c_r);
            TextView title = findViewById(R.id.f_c_r_title);
            TextView rating = findViewById(R.id.f_c_r_rating);
            TextView opinion = findViewById(R.id.f_c_r_comment);
            opinion.setText("NO COMMENTS");
        } else if (main_cursor.getCount() > 0) {
            main_cursor.moveToFirst();
            setContentView(R.layout.f_c_r);
            TextView title = findViewById(R.id.f_c_r_title);
            TextView rating = findViewById(R.id.f_c_r_rating);
            TextView opinion = findViewById(R.id.f_c_r_comment);
            title.setText(main_cursor.getString(0) + " Comments");
            rating.setText("Customer Rating:" + main_cursor.getInt(1));
            opinion.setText(main_cursor.getString(2));
        }
    }

    public void fun_comment_results(View view) {
        String comment = "select F.Name, F.Rating, F.Comment from Food_Comments F where F.Name = " + "'" + fun_comment_result_name + "'" + "" + "and F.Address = " + "'" + fun_comment_result_address + "'" + "order by F.rating desc;";
        main_cursor = database_reviews.rawQuery(comment, null);
        if (main_cursor.getCount() == 0) {
            main_cursor.moveToFirst();
            setContentView(R.layout.f_c_r);
            TextView title = findViewById(R.id.f_c_r_title);
            TextView rating = findViewById(R.id.f_c_r_rating);
            TextView opinion = findViewById(R.id.f_c_r_comment);
            opinion.setText("NO COMMENTS");
        } else if (main_cursor.getCount() > 0) {
            main_cursor.moveToFirst();
            setContentView(R.layout.f_c_r);
            TextView title = findViewById(R.id.f_c_r_title);
            TextView rating = findViewById(R.id.f_c_r_rating);
            TextView opinion = findViewById(R.id.f_c_r_comment);
            title.setText(main_cursor.getString(0) + " Comments");
            rating.setText("Customer Rating:" + main_cursor.getInt(1));
            opinion.setText(main_cursor.getString(2));
        }
    }

    public void f_c_r_next(View view) {
        Log.v("NEXT", "NEXT:");
        if (main_cursor.moveToNext() == true) {
            TextView title = findViewById(R.id.f_c_r_title);
            TextView rating = findViewById(R.id.f_c_r_rating);
            TextView opinion = findViewById(R.id.f_c_r_comment);
            title.setText(main_cursor.getString(0) + " Comments");
            rating.setText("Customer Rating:" + main_cursor.getInt(1));
            opinion.setText(main_cursor.getString(2));
        }
    }

    public void f_c_r_previous(View view) {
        if (main_cursor.moveToPrevious() == true) {
            TextView title = findViewById(R.id.f_c_r_title);
            TextView rating = findViewById(R.id.f_c_r_rating);
            TextView opinion = findViewById(R.id.f_c_r_comment);
            title.setText(main_cursor.getString(0) + " Comments");
            rating.setText("Customer Rating:" + main_cursor.getInt(1));
            opinion.setText(main_cursor.getString(2));
        }
    }

    public void f_c_r_back(View view) {
        food_comment_result_name = null;
        food_comment_result_address = null;
        life_comment_result_name = null;
        life_comment_result_address = null;
        fun_comment_result_name = null;
        fun_comment_result_address = null;
        setContentView(R.layout.activity_main);
    }
}
