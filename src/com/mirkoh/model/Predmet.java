package com.mirkoh.model;

import java.util.List;

public class Predmet {

    private int id;
    private String nazivPredmeta;
    private List<Student> studenti;

    private Predmet() {
    }

    public static class Builder {
        private int id;
        private String nazivPredmeta;
        private List<Student> studenti;

        public Builder() {}

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withNazivPredmeta(String nazivPredmeta) {
            this.nazivPredmeta = nazivPredmeta;
            return this;
        }

        public Builder withStudenti(List<Student> studenti) {
            this.studenti = studenti;
            return this;
        }

        public Predmet build() {
            Predmet predmet = new Predmet();
            predmet.id = this.id;
            predmet.nazivPredmeta = this.nazivPredmeta;
            predmet.studenti = this.studenti;

            return predmet;
        }
    }

    @Override
    public String toString() {
        return "Predmet{" +
                "id=" + id +
                ", nazivPredmeta='" + nazivPredmeta + '\'' +
                ", studenti=" + studenti +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazivPredmeta() {
        return nazivPredmeta;
    }

    public void setNazivPredmeta(String nazivPredmeta) {
        this.nazivPredmeta = nazivPredmeta;
    }

    public List<Student> getStudenti() {
        return studenti;
    }

    public void setStudenti(List<Student> studenti) {
        this.studenti = studenti;
    }
}
