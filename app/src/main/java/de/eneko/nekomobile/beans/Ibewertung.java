package de.eneko.nekomobile.beans;

import de.eneko.nekomobile.activities.models.MessgeraetModel;

public interface Ibewertung {
    String getGrundparameter();
    String getFormparameter();
    Integer getBewertungsfaktor_01();
    Integer getBewertungsfaktor_02();
    Integer getBewertungsfaktor_03();
    Integer getBewertungsfaktor_04();
    Integer getBewertungsfaktor_05();;
    Integer getBewertungsfaktor_06();
    Integer getBewertungsfaktor_07();
    Integer getBewertungsfaktor_08();
    Integer getBewertungsfaktor_09();
    Integer getBewertungsfaktor_10();
    Integer getBewertungsfaktor_11();
    String getReihenanordnung();
    MessgeraetModel getBaseModel();
    Boolean isBewertungEquals(Ibewertung value);
}
