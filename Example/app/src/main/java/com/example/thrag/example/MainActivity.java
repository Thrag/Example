package com.example.thrag.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/*Les imports des classes button, textview et toast*/


public class MainActivity extends Activity {

    /*Déclaration des éléments de l'activité.*/
    Button add;
    Button next;
    TextView compteurMain;

    int x ;

    /*C'est la méthode qui est lancée lorsque l'activité démarre.
    * On va y déclarer les différents éléments de la vue afin de pouvoir agir deçu.*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*On trouve l'id généré par le fichier R afin d'identifier les boutons.
        * On leur ajoute ensuite un listener*/
        add = (Button) findViewById(R.id.buttonAdd);
        add.setOnClickListener(listener);
        next = (Button) findViewById(R.id.buttonNext);
        next.setOnClickListener(listener);
        compteurMain = (TextView) findViewById(R.id.textViewCompteurMain);

        //On crée un intent pour récupérer l'intent envoyé par l'activité précédente
        Intent intent = getIntent();
        if (intent.hasExtra("compteurSecond"))//On vérifie que l'intent possède une valeur
        {
            int y = intent.getIntExtra("compteurSecond",0);
            x = y;
            compteurMain.setText(""+x);
        }
        else
        {
            x = Integer.parseInt(compteurMain.getText().toString());
        }

    }

    /*La méthode onclickListener qui agit en fonction du bouton cliqué.*/
    View.OnClickListener listener = new View.OnClickListener()
    {
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.buttonAdd:
                    Toast.makeText(getApplicationContext(), "Adding", Toast.LENGTH_SHORT).show();
                    //Les toasts sont des messages qui s'affichent à l'écran en popup. Ils sont très pratiques pour le débug

                    x++;
                    compteurMain.setText(""+x);

                    break;
                case R.id.buttonNext:
                    Toast.makeText(getApplicationContext(), "Next "+x, Toast.LENGTH_SHORT).show();

                    //Les intents sont les moyens de communication entre les activités.
                    Intent i = new Intent(MainActivity.this, SecondActivity.class);
                    int count = x;
                    i.putExtra("compteurMain", count);
                    startActivity(i);

                    break;
            }
        }
    };
}

