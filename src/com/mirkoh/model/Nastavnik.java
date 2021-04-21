package com.mirkoh.model;

public class Nastavnik {

    private int id;
    private String ime;
    private String prezime;
    private String zvanje;

    private Nastavnik() {
    }

    public static class Builder {

        private int id;
        private String ime;
        private String prezime;
        private String zvanje;

        public Builder() {}

        public Builder withId(int id) {
            this.id = id;
            return this;
        }
        public Builder withIme(String ime) {
            this.ime = ime;
            return this;
        }

        public Builder withPrezime(String prezime) {
            this.prezime = prezime;
            return this;
        }

        public Builder withZvanje(String zvanje) {
            this.zvanje = zvanje;
            return this;
        }

        public Nastavnik build() {
            Nastavnik nastavnik = new Nastavnik();
            nastavnik.id = this.id;
            nastavnik.ime = this.ime;
            nastavnik.prezime = this.prezime;
            nastavnik.zvanje = this.zvanje;

            return nastavnik;
        }
    }

    @Override
    public String toString() {
        return "Nastavnik{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", zvanje='" + zvanje + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getZvanje() {
        return zvanje;
    }

    public void setZvanje(String zvanje) {
        this.zvanje = zvanje;
    }
}
