package id.ac.poliban.mi.ramlan.homework_045;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipeMethodActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_method);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String item_name = null;
        if (extras != null) {
            item_name = extras.getString(RecipeListAdapter.EXTRA_NAME);
        }
        TextView heading = findViewById(R.id.food_name);
        TextView desc = findViewById(R.id.recipe_description);
        ImageView imageView = findViewById(R.id.food_image);
        heading.setText(item_name);
        if (item_name != null) {
            switch (item_name){
                case "Chocolate Mint Bars":
                    imageView.setImageResource(R.drawable.chocolate_mint_bar);
                    desc.setText(R.string.ChocolateMintBars);
                    break;
                case "Blueberry Cupcakes":
                    imageView.setImageResource(R.drawable.blueberry_cupcakes);
                    desc.setText(R.string.BlueberryCupcakes);
                    break;
                case "Fudge Walnut Brownies":
                    imageView.setImageResource(R.drawable.fudge_brownies);
                    desc.setText(R.string.FudgeWalnutBrownies);
                    break;
                case "Lemon Cake":
                    imageView.setImageResource(R.drawable.lemon_cake);
                    desc.setText(R.string.lorem_ipsum);
                    break;
                case "Blueberry Peach Cobbler":
                    imageView.setImageResource(R.drawable.cobbler);
                    desc.setText(R.string.BlueberryPeachCobbler);
                    break;
                case "Texas Sheet Cake":
                    imageView.setImageResource(R.drawable.sheet_cake);
                    desc.setText(R.string.TexasSheetCake);
                    break;
                case "Espresso Crinkles":
                    imageView.setImageResource(R.drawable.espresso_crinkles);
                    desc.setText(R.string.EspressoCrinkles);
                    break;
                case "Chocolate Cherry Cookies":
                    imageView.setImageResource(R.drawable.chocolate_cherry_cookies);
                    desc.setText(R.string.ChocolateCherryCookies);
                    break;
                case "Vanilla Cheesecake":
                    imageView.setImageResource(R.drawable.cheesecake);
                    desc.setText(R.string.VanillaCheesecake);
                    break;
                case "Tiramisu":
                    imageView.setImageResource(R.drawable.tiramisu);
                    desc.setText(R.string.Tiramisu);
                    break;
                case "Carrot Cake":
                    imageView.setImageResource(R.drawable.carrot_cake);
                    desc.setText(R.string.CarrotCake);
                    break;
                case "Blueberry Ice Cream":
                    imageView.setImageResource(R.drawable.blueberry_ice_cream);
                    desc.setText(R.string.BlueberryIceCream);
                    break;
                default: break;
            }
        }
    }
}