package com.example.perguntas;

import android.content.Context;
import android.provider.ContactsContract;
import com.google.firebase.database.DatabaseReference;

public class FirebaseHelper {

        private static FirebaseHelper mInstance;
        private static Context context;
        public String idJogo;
        public Boolean criouJogo;
        public DatabaseReference mDatabase;
        public DatabaseReference mDataJogos;

        private FirebaseHelper(Context context) {
            this.context = context;
            mDatabase = App.getInstance().getFirebaseDatabase().getReference();
            mDataJogos = App.getInstance().getFirebaseDatabase().getReference("jogos");
        }

        public static synchronized FirebaseHelper getInstance(Context context) {
            if (mInstance == null) {
                mInstance = new FirebaseHelper(context);
            }
            return mInstance;
        }

        public void insertInto(DatabaseReference ref , Object a){
            ref.setValue(a);
        }

        public DatabaseReference getDatabaseReference(){return this.mDatabase;}

        public DatabaseReference getDatabaseJogos(){return this.mDataJogos;}

        public void setIdJogo(String id){this.idJogo=id;}

        public String getIdJogo(){return idJogo;}

        public void setCriouJogo(boolean jogo){this.criouJogo=jogo;}

        public Boolean getCriouJogo(){return criouJogo;}
}
