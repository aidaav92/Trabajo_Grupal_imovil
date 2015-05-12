package es.uniovi.imovil.fcrtrainer.digitalsystems;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import java.util.Random;

import es.uniovi.imovil.fcrtrainer.BaseExerciseFragment;
import es.uniovi.imovil.fcrtrainer.R;

/**
 * Created by Aida on 22/04/2015.
 */
public class DigitalCircuitExerciseFragment extends BaseExerciseFragment implements
        View.OnClickListener, AdapterView.OnItemSelectedListener {

    private static final String STATE_CURRENT_QUESTION = "mCurrentQuestion";

    private int mCurrentQuestion;

    private Button mButtoncheck;
    private Button mSolutionButton;
    private String[] mDigitalstring;
    private TypedArray mImageArray;
    private ImageView mImageView;
    private RadioGroup mRGroup;
    private RadioButton mRb0;
    private RadioButton mRb1;
    private String mRbPressed = "";
    private Random mRandom = new Random();
    //Constructor
    public DigitalCircuitExerciseFragment(){}

    //Métodos
    public static DigitalCircuitExerciseFragment newInstance() {
        DigitalCircuitExerciseFragment mfragment = new DigitalCircuitExerciseFragment();
        return mfragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflamos el Layout
        View rootView = inflater.inflate(R.layout.fragment_digital_circuit,
                container, false);

        // Cargamos el array con los circuitos digitales
        mDigitalstring = getResources().getStringArray(R.array.digital_circuits);

        // Inicializamos las vistas de los botones y sus respectivos Listener
        mButtoncheck = (Button) rootView.findViewById(R.id.cButton);
        mSolutionButton = (Button) rootView.findViewById(R.id.sButton);
        mButtoncheck.setOnClickListener(this);
        mSolutionButton.setOnClickListener(this);

        //Inicializamos las vistas del Radiogroup, los radiobuttons y sus repectivos Lsiteners
        mRGroup = (RadioGroup) rootView.findViewById(R.id.dcircuit_group);
        mRb0=(RadioButton) rootView.findViewById(R.id.rb_1);
        mRb1=(RadioButton) rootView.findViewById(R.id.rb_0);

        mRGroup.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup rGroup, int checkedId) {
                switch(checkedId) {
                    case R.id.rb_0:
                        mRbPressed = getResources().getString(R.string.digital_circuit_0);
                        break;
                    case R.id.rb_1:
                        mRbPressed = getResources().getString(R.string.digital_circuit_1);
                        break;
                }
            }
        });

        // Cargamos un array con las imagenes de los circuitos digitales
        mImageArray = getResources().obtainTypedArray(R.array.digital_circuits_images);

        mImageView = (ImageView) rootView.findViewById(R.id.imagedigitalcircuit);

        if (savedInstanceState == null) {
            // Inicializamos la variable contador con el fin de recorrer el array
            // con los diferentes circuitos digitales
            mCurrentQuestion = random();
            mImageView.setImageResource(mImageArray.getResourceId(mCurrentQuestion, 0));
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState == null) {
            return;
        }

        if (mIsPlaying) {
            mSolutionButton.setVisibility(View.GONE);
        }

        mCurrentQuestion = savedInstanceState.getInt(STATE_CURRENT_QUESTION, 0);

        mImageView.setImageResource(mImageArray.getResourceId(
                mCurrentQuestion, 0));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(STATE_CURRENT_QUESTION, mCurrentQuestion);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cButton:
                checkAnswer();
                break;

            case R.id.sButton:
                showSolution();
                break;
        }

    }

    public void checkAnswer() {
        if (mRbPressed.equals(mDigitalstring[mCurrentQuestion])){
            showAnimationAnswer(true);
            if (mIsPlaying){
                computeCorrectQuestion();
            }
            mCurrentQuestion = random();
            mImageView.setImageResource(mImageArray.getResourceId(
                    mCurrentQuestion, 0));
        }
        else {
            showAnimationAnswer(false);
            if (mIsPlaying) {
                computeIncorrectQuestion();
            }
        }
    }

    // Metodo para seleccionar el radiobutton con la respuesta correcta.
    public void showSolution() {
        String answer = mDigitalstring[mCurrentQuestion];

        if (answer.equals("0")){
            mRb1.setChecked(true);
        }else {
            mRb0.setChecked(true);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // Nada que hacer aquí
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Nada que hacer aquí
    }

    // Metodo para generar un número aleatorio
    private int random() {
        return mRandom.nextInt(numberOfCircuits());
    }

    private int numberOfCircuits() {
        return mDigitalstring.length;
    }

    @Override
    protected void startGame() {
        super.startGame();

        // Cambiamos el layout y se adapta al modo juego
        mSolutionButton.setVisibility(View.GONE);
        mCurrentQuestion = random();
        mImageView.setImageResource(mImageArray.getResourceId(mCurrentQuestion,
                0));
    }

    @Override
    protected void cancelGame() {
        super.cancelGame();

        // Cambiamos el layout y lo dejamos otra vez como el modo ejercicio
        mSolutionButton.setVisibility(View.VISIBLE);
    }

    protected void endGame() {
        super.endGame();

        // Cambiamos el layout para dejarlo en modo ejercicio
        mSolutionButton.setVisibility(View.VISIBLE);
    }

    @Override
    protected int obtainExerciseId() {
        return R.string.logic_gate;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mImageArray.recycle();
    }
}
