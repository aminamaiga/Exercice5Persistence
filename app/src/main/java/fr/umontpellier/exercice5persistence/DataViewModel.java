package fr.umontpellier.exercice5persistence;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class DataViewModel extends ViewModel {

    String planning1 = "08h-10h : Rencontre client Dupont";
    String planning2 = "10h-12h : Travailler le dossierr recrutement";
    String planning3 = "14h-16h : Réunion équipe";
    String planning4 = "16h-18h : Préparation dossier vente.";

    private MutableLiveData<List<String>> planings;

   MutableLiveData<List<String>> getPanning(){
        if (planings == null){
            return new MutableLiveData<List<String>>();
        }
        return planings;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
