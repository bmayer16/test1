package com.example.rechner_bmayer;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.view.menu.MenuBuilder;

/**
 * Dies ist die Klasse um den Code der Rechner App zu schreiben.
 * Da ich dieses Jahr nur SEW und Mathe machen muss, werde ich
 * jedoch nicht wie in der Aufgabenstellung verlangt, nur meine
 * zwei Aufgaben machen (X und B) sondern alle. Die die ich aber nicht
 * machen muss, werde ich als Kommentar lassen, denn zumindest
 * bei der Zwischenaufgabe 2 würden sie sich gegenseitig behindern.
 * Autor:       Birgit Mayer
 * erstellt:    07.10.2020 7:40
 * beendet:     07.10.2020 15:16
 */
public class MainActivity extends AppCompatActivity {
    //einige Variablen die in allen oder zumindest mehreren Methoden benötigt werden
    private String wert1;
    private String wert2;
    private String ergebnis;
    private int rechenart;
    public static final String w_1 = "w_1";
    public static final String w_2 = "w_2";
    public static final String e_1 = "e_1";
    public static final String rechenart_1 = "rechenart";
    TextView textView;

    /**
     * Hier wird alles erstellt, was von Anfang an da sein muss.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        deleteText();

        //B
        /**
        RadioButton radioButtona = findViewById(R.id.radioButtona);
        radioButtona.setActivated(false);
        RadioButton radioButtons = findViewById(R.id.radioButtons);
        radioButtons.setActivated(false);
        RadioButton radioButtonm = findViewById(R.id.radioButtonm);
        radioButtonm.setActivated(false);
        RadioButton radioButtond = findViewById(R.id.radioButtond);
        radioButtond.setActivated(false);
        //C
        Button berechnen = findViewById(R.id.button);
        berechnen.setActivated(false);
        */
    }

    /**
     * In der berechnen Methode wird das Ergebnis der zwei Werte berechnet,
     * je nachdem, welcher RadioButton ausgewählt wurde.
     * @param view
     */
    public void berechnen(View view){
        Double w1 = getWert1();
        Double w2 = getWert2();
        Double ergebnis = 0.0;
        TextView tv = (TextView) findViewById(R.id.textView);
        //holt sich die ID spinner
        Spinner zeicheng = findViewById(R.id.spinner);
        //checkt ob und welcher Button ausgewählt ist
        String btn = (String) zeicheng.getSelectedItem();
        switch (btn) {
            case "+":
                ergebnis = w1 + w2;
                break;
            case "-":
                ergebnis = w1 - w2;
                break;
            case "*":
                ergebnis = w1 * w2;
                break;
            case "/":
                ergebnis = w1 / w2;
                break;
            default:
                Toast.makeText(getApplicationContext(),"Waehlen Sie eine Rechenmethode aus!", Toast.LENGTH_LONG).show();
                break;
        }
        tv.setText(ergebnis.toString());
    }

    /**
     * Die Update Methode setzt die gespeicherten Werte wieder ein.
     * Sie wird bei Klick auf den MR Button aufgerufen.
     * @param view
     */
    public void update(View view){
        SharedPreferences sp = getSharedPreferences("werte", MODE_PRIVATE);
        //X
        wert1 = sp.getString(w_1,"");
        wert2 = sp.getString(w_2,"");
        if(wert1 != null && wert2 != null) {
            setWert1(wert1);
            setWert2(wert2);
        }

        //Y
        /**
        ergebnis = sp.getString(e_1,"");
        if(ergebnis != null) {
            setErgebnis(ergebnis);
        }
        */

        //Z
        /**
        rechenart = sp.getInt(rechenart_1, 0);
        setRechenart(rechenart);
        */
    }

    /**
     * Hier wird ein OnTouchListener verwendet, da es bei einer Berührung und
     * nicht bei einem Klick ausgeführt werden soll. Das Ergebnis wird gelöscht.
     */
    public void deleteText(){
        textView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent event){
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    textView.setText("");
                }
                return true;
            }
        });

    }

    //Zwischenaufgabe 1: A
    @Override
    protected void onResume() {
        super.onResume();
        Button button = findViewById(R.id.button);
        button.setBackgroundColor(getResources().getColor(R.color.green));
    }

    //Zwischenaufgabe 1: B und C
    /**
    @Override
    protected void onStart(){
        super.onStart();
        //B
        RadioButton radioButtona = findViewById(R.id.radioButtona);
        radioButtona.setActivated(true);
        RadioButton radioButtons = findViewById(R.id.radioButtons);
        radioButtons.setActivated(true);
        RadioButton radioButtonm = findViewById(R.id.radioButtonm);
        radioButtonm.setActivated(true);
        RadioButton radioButtond = findViewById(R.id.radioButtond);
        radioButtond.setActivated(true);
        //C
        Button berechnen = findViewById(R.id.button);
        berechnen.setActivated(true); j
    }
    */


    //Zwischenaufgabe 2:
    public void speichern(View view){
        SharedPreferences sp = getSharedPreferences("werte", MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = sp.edit();
        //X
        Double w1 = getWert1();
        String w11 = w1.toString();
        Double w2 = getWert2();
        String w22 = w2.toString();
        editor.putString(w_1,w11);
        editor.putString(w_2,w22);

        //Y
        /**
        Double e = getErgebnis();
        String e1 = e.toString();
        editor.putString(e_1,e1);
        */

        //Z
        /**
        RadioGroup zeicheng = findViewById(R.id.radioGroup);
        //checkt ob und welcher Button ausgewählt ist
        int btn = zeicheng.getCheckedRadioButtonId();
        editor.putInt(rechenart_1,btn);
        */

        editor.apply();
        Toast.makeText(getApplicationContext(),"Werte gespeichert!", Toast.LENGTH_SHORT).show();
    }



    //Getter und Setter Methoden, welche ich in den anderen Methoden benötige.

    /**Gibt das aktuelle Ergebnis zurück
     * @return wert1
     */
    public double getErgebnis(){
        //holt die IDs
        TextView ergebnis = findViewById(R.id.textView);
        //wandelt sie in Strings um
        String ergebnis_1 = ergebnis.getText().toString();
        //holt sich die Werte
        Double e1 = Double.valueOf(ergebnis_1);
        return e1;
    }

    /**Gibt den Wert1 zurück
     * @return wert1
     */
    public double getWert1(){
        //holt die IDs
        EditText wert1 = findViewById(R.id.editText);
        //wandelt sie in Strings um
        String wert_1 = wert1.getText().toString();
        //holt sich die Werte
        Double w1 = Double.valueOf(wert_1);
        return w1;
    }

    /**
     * Gibt den zweiten Wert zurück
     * @return Wert2
     */
    public double getWert2(){
        EditText wert2 = findViewById(R.id.editText1);
        String wert_2 = wert2.getText().toString();
        Double w2 = Double.valueOf(wert_2);
        return w2;
    }

    /**
     * Hier wird die derzeitige Rechenart auf den gewünschten Wert gesetzt
     * @param wert
     */
    public void setRechenart(String wert) {
        //holt sich die ID spinner
        Spinner zeicheng = findViewById(R.id.spinner);

        switch (wert) {
            case "+":
                zeicheng.setSelection(1);
                break;
            case "-":
                zeicheng.setSelection(2);
                break;
            case "*":
                zeicheng.setSelection(3);
                break;
            case "/":
                zeicheng.setSelection(4);
                break;
            default:
                Toast.makeText(getApplicationContext(), "Keine Rechenart gewählt", Toast.LENGTH_LONG).show();
                zeicheng.setSelection(0);
                break;
        }
    }

    /**
     * Hier wird das derzeitige Ergebnis auf den gewünschten Wert gesetzt
     * @param wert
     */
    public void setErgebnis(String wert){
        TextView ergebnis = findViewById(R.id.textView);
        ergebnis.setText(wert);
    }

    /**
     * Hier wird der erste Wert auf den gewünschten Wert gesetzt
     * @param wert
     */
    public void setWert1(String wert){
        EditText wert1 = findViewById(R.id.editText);
        wert1.setText(wert);
    }

    /**
     * Hier wird der zweite Wert auf den gewünschten Wert gesetzt
     * @param wert
     */
    public void setWert2(String wert){
        EditText wert2 = findViewById(R.id.editText1);
        wert2.setText(wert);
    }



    //EI
    //Menü
    //Quelle: https://o7planning.org/de/12605/anleitung-android-optionmenu

    /**
     * Das Menü wird aufgerufen und eingebunden
     * @param menu
     * @return
     */
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_main_menu, menu);

        if(menu instanceof MenuBuilder){
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }

        return true;
    }

    /**
     * Hier wird entschieden was passiert, wenn eines der MenüItems ausgewählt wurde.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItem_reset:
                reset();
                return true;
            case R.id.menuItem_info:
                Toast.makeText(this, "Autor: Birgit Mayer", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Die Methode Reset setzt alle Komponenten auf ihre Ausgangswerte zurück.
     */
    public void reset(){
        EditText wert1 = findViewById(R.id.editText);
        wert1.setText(R.string.wert_1);
        EditText wert2 = findViewById(R.id.editText1);
        wert2.setText(R.string.wert_2);
        TextView ergebnis = findViewById(R.id.textView);
        ergebnis.setText(R.string.anfangswert);
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setSelected(false);

    }
}