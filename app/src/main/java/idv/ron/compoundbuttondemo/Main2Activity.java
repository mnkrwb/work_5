package idv.ron.compoundbuttondemo;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private TextView tvMessage;
    private TextView tvMessage2;
    private Spinner spFood;
    private Spinner spPlace;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tvMessage = (TextView) findViewById(R.id.tvMessage);
        registerForContextMenu(tvMessage);
        findViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

//        menu.add("Yushan National Park");
//        menu.add("Taroko National Park");
        return true;
    }

    public void two(View v) {
        Intent j = new Intent();
        j.setClass(this,Main3Activity.class);
        startActivity(j);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String message;
        switch (item.getItemId()) {
            case R.id.yangmingshan:
                message = getString(R.string.yangmingshan);
                break;
            case R.id.guide:
                message = getString(R.string.yangmingshan) + " > "
                        + getString(R.string.guide);
                break;
            case R.id.traffic:
                message = getString(R.string.yangmingshan) + " > "
                        + getString(R.string.traffic);
                break;
            case R.id.yushan:
                message = getString(R.string.yushan);
                break;
            case R.id.taroko:
                message = getString(R.string.taroko);
                break;
            case R.id.myloc:
                message = getString(R.string.myloc);
                break;
            case R.id.exit:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
        tvMessage.setText(message);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear:
                tvMessage.setText("");
                break;
            case R.id.yellow:
                tvMessage.setBackgroundColor(Color.YELLOW);
                break;
            case R.id.green:
                tvMessage.setBackgroundColor(Color.GREEN);
                break;
            case R.id.cyan:
                tvMessage.setBackgroundColor(Color.CYAN);
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }

    public void onDeleteClick(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                tvMessage.setText(item.getTitle());
                return true;
            }
        });
        popupMenu.show();
    }

    private void findViews() {
        tvMessage2 = (TextView) findViewById(R.id.tvMessage2);
        spFood = (Spinner) findViewById(R.id.spFood);
        spFood.setSelection(0, true);
        spFood.setOnItemSelectedListener(listener);

        spPlace = (Spinner) findViewById(R.id.spPlace);
        String[] places = {"Australia", "U.K.", "Japan", "Thailand"};
        ArrayAdapter<String> adapterPlace = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, places);
        adapterPlace
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPlace.setAdapter(adapterPlace);
        spPlace.setSelection(0, true);
        spPlace.setOnItemSelectedListener(listener);
    }

    Spinner.OnItemSelectedListener listener = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(
                AdapterView<?> parent, View view, int pos, long id) {
            tvMessage2.setText(parent.getItemAtPosition(pos).toString());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            tvMessage2.setText(R.string.text_NothingSelected);
        }
    };
}
