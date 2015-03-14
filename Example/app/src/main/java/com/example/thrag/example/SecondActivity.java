package com.example.thrag.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/*Les imports des classes button, textview et toast*/
/**
 * Created by Thrag on 14/03/15.
 */
public class SecondActivity extends Activity
{
    /*Déclaration des éléments de l'activité.*/
    Button sub;
    Button previous;
    TextView compteurSecond;

    int x;

    /*C'est la méthode qui est lancée lorsque l'activité démarre.
    * On va y déclarer les différents éléments de la vue afin de pouvoir agir deçu.*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        /*On trouve l'id généré par le fichier R afin d'identifier les boutons.
        * On leur ajoute ensuite un listener*/
        sub = (Button) findViewById(R.id.buttonSub);
        sub.setOnClickListener(listener);
        previous = (Button) findViewById(R.id.buttonPrevious);
        previous.setOnClickListener(listener);
        compteurSecond = (TextView) findViewById(R.id.textViewCompteurSecond);

        //On crée un intent pour récupérer l'intent envoyé par l'activité précédente
        Intent intent = getIntent();
        if (intent.hasExtra("compteurMain"))//On vérifie que l'intent possède une valeur
        {
            int y = intent.getIntExtra("compteurMain",0);
            x = y;

            compteurSecond.setText(""+x);
        }
        else
        {
            x = Integer.parseInt(compteurSecond.getText().toString());
        }
    }

    /*La méthode onclickListener qui agit en fonction du bouton cliqué.*/
    View.OnClickListener listener = new View.OnClickListener()
    {
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.buttonSub:
                    Toast.makeText(getApplicationContext(), "Substract", Toast.LENGTH_SHORT).show();
                    //Les toasts sont des messages qui s'affichent à l'écran en popup. Ils sont très pratiques pour le débug

                    x--;
                    compteurSecond.setText(""+x);

                    break;
                case R.id.buttonPrevious:
                    Toast.makeText(getApplicationContext(), "Previous "+x, Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(SecondActivity.this, MainActivity.class);
                    int count = x;
                    i.putExtra("compteurSecond", count);
                    startActivity(i);
                    break;
            }
        }
    };
}
