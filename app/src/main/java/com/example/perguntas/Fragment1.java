package com.example.perguntas;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import java.util.*;


public class Fragment1 extends Fragment implements ColocarPergunta {
    public Button button1, button2, button3, button4;
    public TextView pergunta, nPergunta, score, timer;
    public String correto;
    public AlertDialog dialog2;
    public Button button;
    public Timer T;
    public ListaPerguntas lista;
    public String title;
    public int timerTime;
    public int index, valorScore;
    public View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            T.cancel();
            int temposobrou = Integer.parseInt(timer.getText().toString());
            //int auxTime2 = Helper.getInstance(getActivity().getApplicationContext()).getTime();

            //auxTime2 = (15 - auxTime2);
            //auxTime = auxTime + auxTime2;
            //Helper.getInstance(getActivity().getApplicationContext()).setTime(auxTime);
            String aux = (String) v.getTag();
            switch (aux) {
                case "button1":
                    button = button1;
                    break;
                case "button2":
                    button = button2;
                    break;
                case "button3":
                    button = button3;
                    break;
                case "button4":
                    button = button4;
                    break;
            }
            if (button.getText() == correto) {
                title = "Correct Awnser";
                //button.setBackgroundColor(Color.GREEN);
                int valorAux = Integer.parseInt(score.getText().toString());
                //valorAux = valorAux + 1;

                // testar

                int pontuacaoPergunta = 1;
                pontuacaoPergunta = temposobrou +5;
                valorAux = valorAux + pontuacaoPergunta;


                Helper.getInstance(getActivity().getApplicationContext()).setScore(valorAux);
                score.setText(valorAux + "");
            } else {
                title = "Wrong Awnser, the correct one would be : " + correto + ".";
                //button.setBackgroundColor(Color.RED);
            }

            //button.setBackgroundColor(getActivity().getResources().getColor(android.R.color.darker_gray));
            Animation anim = new AlphaAnimation(0.0f, 1.0f);
            anim.setDuration(400); //You can manage the blinking time with this parameter
            anim.setStartOffset(20);
            anim.setRepeatMode(Animation.REVERSE);
            anim.setRepeatCount(3);

            anim.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onAnimationEnd(Animation animation) {
                    if (button.getText() == correto) {
                        button.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.button_pergunta_correta));

                        //dialog(true);
                    } else {
                        button.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.button_pergunta_errada));
                        if (button1.getText() == correto) {
                            button1.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.button_pergunta_correta));
                        } else if (button2.getText() == correto) {
                            button2.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.button_pergunta_correta));
                        } else if (button3.getText() == correto) {
                            button3.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.button_pergunta_correta));
                        } else if (button4.getText() == correto) {
                            button4.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.button_pergunta_correta));
                        }
                        //dialog(false);
                    }
                    fazeralgo();
                }
            });
            button.startAnimation(anim);
        }
    };

    public Fragment1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.custom_view_pergunta, container, false);

        timerTime = 15;

        button1 = view.findViewById(R.id.button1);
        button2 = view.findViewById(R.id.button2);
        button3 = view.findViewById(R.id.button3);
        button4 = view.findViewById(R.id.button4);
        pergunta = view.findViewById(R.id.pergunta);
        nPergunta = view.findViewById(R.id.numeropergunta);
        timer = view.findViewById(R.id.timer);
        score = view.findViewById(R.id.score);

        index = Helper.getInstance(getActivity().getApplicationContext()).getIndex();
        lista = Helper.getInstance(getActivity().getApplicationContext()).getLista();
        valorScore = Helper.getInstance(getActivity().getApplicationContext()).getScore();
        Helper.getInstance(getActivity().getApplicationContext()).setTime(0);

        timer.setText(15+"");

        T = new Timer();
        T.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timer.setText(timerTime + "");
                        if (timerTime == 0) {
                            T.cancel();
                            int auxTime = Helper.getInstance(getActivity().getApplicationContext()).getTime();
                            auxTime = (auxTime + 15);
                            Helper.getInstance(getActivity().getApplicationContext()).setTime(auxTime);
                            tempoAcabou();
                        }
                        timerTime--;
                    }
                });
            }
        }, 400, 1000);


        correto = lista.results.get(index).correctAnswer;
        String errado1 = lista.results.get(index).incorrectAnswers.get(0);
        String errado2 = lista.results.get(index).incorrectAnswers.get(1);
        String errado3 = lista.results.get(index).incorrectAnswers.get(2);
        String textopergunta = lista.results.get(index).question;
        int numeroPerguntacurrent = index + 1;
        int totalPerguntas = lista.results.size();

        /*
        button1.setText(correto);
        button2.setText(errado1);
        button3.setText(errado2);
        button4.setText(errado3);
        */
        List<String> erradas = new ArrayList<>();
        erradas.add(errado1);
        erradas.add(errado2);
        erradas.add(errado3);

        Random random = new Random();
        int x = random.nextInt(4);
        ArrayList<Button> butoes = new ArrayList<>();
        butoes.add(button1);
        butoes.add(button2);
        butoes.add(button3);
        butoes.add(button4);
        for (int i = 0; i < butoes.size(); i++) {
            if (i == x) {
                butoes.get(i).setText(correto);
            }
        }

        for (int i = 0; i < butoes.size(); i++) {
            if (i != x) {
                butoes.get(i).setText(erradas.get(0));
                erradas.remove(0);
            }
        }
        pergunta.setText(textopergunta);
        /*
        try{
            byte[] data = Base64.decode(textopergunta, Base64.DEFAULT);
            String text = new String(data, "UTF-8");
            pergunta.setText(text);
        }catch(UnsupportedEncodingException e){}
        */


        nPergunta.setText(numeroPerguntacurrent + "/" + totalPerguntas);
        score.setText(valorScore + "");



        button1.setOnClickListener(click);
        button2.setOnClickListener(click);
        button3.setOnClickListener(click);
        button4.setOnClickListener(click);





        return view;
    }

    public void emptyBackStack() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        int count = fm.getBackStackEntryCount();
        for (int i = 0; i < count; i++) {
            fm.popBackStack();
        }
    }

    public void fazeralgo() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (index + 1 == lista.results.size()) {
                    Intent intent = new Intent(getActivity(), ResultActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    getActivity().startActivity(intent);
                } else {
                    Helper.getInstance(getActivity().getApplicationContext()).setIndex(index + 1);
                    Fragment1 fragmento = new Fragment1();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmento).addToBackStack(null).commit();
                }
            }
        }, 2000);


    }


    public void tempoAcabou() {


        if (button1.getText() == correto) {
            button1.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.button_pergunta_correta));
        } else if (button2.getText() == correto) {
            button2.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.button_pergunta_correta));
        } else if (button3.getText() == correto) {
            button3.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.button_pergunta_correta));
        } else if (button4.getText() == correto) {
            button4.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.button_pergunta_correta));
        }
        //dialog(false);
        fazeralgo();
    }


    public void dialog(Boolean true_false) {
        if (true_false) {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
            View mView = getLayoutInflater().inflate(R.layout.dialog_certo, null);
            Button next = mView.findViewById(R.id.nextQuestion);
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (index + 1 == lista.results.size()) {
                        dialog2.dismiss();
                        Intent intent = new Intent(getActivity(), ResultActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        getActivity().startActivity(intent);
                    } else {
                        dialog2.dismiss();
                        Helper.getInstance(getActivity().getApplicationContext()).setIndex(index + 1);
                        Fragment1 fragmento = new Fragment1();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmento).addToBackStack(null).commit();
                    }
                }
            });
            mBuilder.setView(mView);
            dialog2 = mBuilder.create();
            dialog2.setCancelable(false);
            dialog2.show();

        } else {

            AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
            View mView = getLayoutInflater().inflate(R.layout.dialog_wrong, null);
            Button next = mView.findViewById(R.id.nextQuestion);
            TextView awnser_correta = mView.findViewById(R.id.resposta_correta);
            awnser_correta.setText("The correct awnser was '" + correto + "'");
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (index + 1 == lista.results.size()) {
                        dialog2.dismiss();
                        Intent intent = new Intent(getActivity(), ResultActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        getActivity().startActivity(intent);
                    } else {
                        dialog2.dismiss();
                        Helper.getInstance(getActivity().getApplicationContext()).setIndex(index + 1);
                        Fragment1 fragmento = new Fragment1();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmento).addToBackStack(null).commit();
                    }
                }
            });
            mBuilder.setView(mView);
            dialog2 = mBuilder.create();
            dialog2.setCancelable(false);
            dialog2.show();
        }

    }
    public void doSomething(){
        Helper.getInstance(getContext()).callSomething(this);
    }

    @Override
    public void novaPergunta() {

    }
}
