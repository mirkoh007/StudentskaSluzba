package com.mirkoh.model;

public class Student extends Osoba {

    private String grad;
    private String indeks;

    private Student() {
        super();
    }

    public static class Builder {
        private int id;
        private String ime;
        private String prezime;
        private String grad;
        private String indeks;

        public Builder() {
        }

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

        public Builder withGrad(String grad) {
            this.grad = grad;
            return this;
        }

        public Builder withIndeks(String indeks) {
            this.indeks = indeks;
            return this;
        }

        public Student build() {
            Student student = new Student();
            student.id = this.id;
            student.ime = this.ime;
            student.prezime = this.prezime;
            student.grad = this.grad;
            student.indeks = this.indeks;

            return student;
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", indeks='" + indeks + '\'' +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", grad='" + grad + '\'' +
                '}';
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getIndeks() {
        return indeks;
    }

    public void setIndeks(String indeks) {
        this.indeks = indeks;
    }
}
