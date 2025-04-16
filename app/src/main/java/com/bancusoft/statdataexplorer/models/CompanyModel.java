package com.bancusoft.statdataexplorer.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class CompanyModel implements Serializable {

    @SerializedName("DEN_COM_VW")
    private final String denumire;

    @SerializedName("IDNO_VW")
    private final String idno;

    @SerializedName("ADRESA_VW")
    private final String adresa;

    @SerializedName("FORMA_ORG_VW")
    private final String formaOrganizare;

    @SerializedName("LIST_COND_VW")
    private final String conditii;

    @SerializedName("LISTA_FOND_VW")
    private final String fondatori;

    @SerializedName("GEN_ACT_NE_LIC_VW")
    private final String activitatiFaraLicenta;

    @SerializedName("GEN_ACT_LIC_VW")
    private final String activitatiCuLicenta;

    @SerializedName("STATUTUL_VW")
    private final String statutul;

    @SerializedName("DATA_REG_VW")
    private final String dataInregistrarii;

    @SerializedName("act")
    private final String act;

    public CompanyModel(String denumire, String idno, String adresa, String formaOrganizare,
                        String conditii, String fondatori, String activitatiFaraLicenta,
                        String activitatiCuLicenta, String statutul, String dataInregistrarii, String act) {
        this.denumire = denumire;
        this.idno = idno;
        this.adresa = adresa;
        this.formaOrganizare = formaOrganizare;
        this.conditii = conditii;
        this.fondatori = fondatori;
        this.activitatiFaraLicenta = activitatiFaraLicenta;
        this.activitatiCuLicenta = activitatiCuLicenta;
        this.statutul = statutul;
        this.dataInregistrarii = dataInregistrarii;
        this.act = act;
    }

    public String getDenumire() { return denumire; }
    public String getIdno() { return idno; }
    public String getAdresa() { return adresa; }
    public String getFormaOrganizare() { return formaOrganizare; }
    public String getConditii() { return conditii; }
    public String getFondatori() { return fondatori; }
    public String getActivitatiFaraLicenta() { return activitatiFaraLicenta; }
    public String getActivitatiCuLicenta() { return activitatiCuLicenta; }
    public String getStatutul() { return statutul; }
    public String getDataInregistrarii() { return dataInregistrarii; }
    public String getAct() { return act; }

    @Override
    public String toString() {
        return denumire + " (" + idno + ")";
    }
}
