package ro.ase.exam03;

import android.os.Parcel;
import android.os.Parcelable;

public class Rezervare implements Parcelable {

    private int idRezervare;
    private String numeClient;
    private String tipCamera;
    private String dataCazare;


    public Rezervare(int idRezervare, String numeClient, String tipCamera, String dataCazare) {
        this.idRezervare = idRezervare;
        this.numeClient = numeClient;
        this.tipCamera = tipCamera;
        this.dataCazare = dataCazare;
    }

    protected Rezervare(Parcel in) {
        idRezervare = in.readInt();
        numeClient = in.readString();
        tipCamera = in.readString();
        dataCazare = in.readString();
    }

    public static final Creator<Rezervare> CREATOR = new Creator<Rezervare>() {
        @Override
        public Rezervare createFromParcel(Parcel in) {
            return new Rezervare(in);
        }

        @Override
        public Rezervare[] newArray(int size) {
            return new Rezervare[size];
        }
    };

    public int getIdRezervare() {
        return idRezervare;
    }

    public void setIdRezervare(int idRezervare) {
        this.idRezervare = idRezervare;
    }

    public String getNumeClient() {
        return numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    public String getTipCamera() {
        return tipCamera;
    }

    public void setTipCamera(String tipCamera) {
        this.tipCamera = tipCamera;
    }


    public String getDataCazare() {
        return dataCazare;
    }

    public void setDataCazare(String dataCazare) {
        this.dataCazare = dataCazare;
    }


    @Override
    public String toString() {
        return "Rezervare{" +
                "idRezervare=" + idRezervare +
                ", numeClient='" + numeClient + '\'' +
                ", tipCamera='" + tipCamera + '\'' +
                ", dataCazare='" + dataCazare + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idRezervare);
        dest.writeString(numeClient);
        dest.writeString(tipCamera);
        dest.writeString(dataCazare);
    }
}
