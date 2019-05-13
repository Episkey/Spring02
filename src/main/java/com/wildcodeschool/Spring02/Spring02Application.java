package com.wildcodeschool.Spring02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Controller
@SpringBootApplication
public class Spring02Application {

    public static void main(String[] args) {
        SpringApplication.run(Spring02Application.class, args);
    }


    @RequestMapping("/doctor/{doc}")
    @ResponseBody
    public Doctor getDoctor(@PathVariable int doc, @RequestParam(required = false) boolean det) {
        Doctor doctor1 = new Doctor("9", "Christopher Eccleston");
        Doctor doctor2 = new Doctor("10", "David Tennant");
        Doctor doctor3 = new Doctor("11", "Matt Smith");
        Doctor doctor4 = new Doctor("12", "Peter Capaldi ");
        Doctor doctor5 = new Doctor("13", "Jodie Whittaker ");

        List<Doctor> listDoc = new ArrayList<>();
        listDoc.add(doctor1);
        listDoc.add(doctor2);
        listDoc.add(doctor3);
        listDoc.add(doctor4);
        listDoc.add(doctor5);

        ExtendedDoctor doctorE1 = new ExtendedDoctor("9", "Christopher Eccleston", "13", "41");
        ExtendedDoctor doctorE2 = new ExtendedDoctor("10", "David Tennant", "47", "34");
        ExtendedDoctor doctorE3 = new ExtendedDoctor("11", "Matt Smith", "44", "27");
        ExtendedDoctor doctorE4 = new ExtendedDoctor("12", "Peter Capaldi ", "40", "55");
        ExtendedDoctor doctorE5 = new ExtendedDoctor("13", "Jodie Whittaker ", "11", "35");

        List<Doctor> listDocExtend = new ArrayList<>();
        listDocExtend.add(doctorE1);
        listDocExtend.add(doctorE2);
        listDocExtend.add(doctorE3);
        listDocExtend.add(doctorE4);
        listDocExtend.add(doctorE5);

        if (doc < 9 || doc >= 1) {
            throw new ResponseStatusException(HttpStatus.SEE_OTHER, "See Other");
        }
        if (doc > 13 || doc <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Impossible de récupérer l'incarnation " + doc + " de doctor");
        }

        if (det) {
            return listDocExtend.get(doc - 9);

        } else {
            return listDoc.get(doc - 9);
        }
    }


    class Doctor {

        private String name;
        private String number;

        public Doctor(String name, String number) {
            this.name = name;
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public String getNumber() {
            return number;
        }
    }


    class ExtendedDoctor extends Doctor {

        private String nbEpisode;
        private String ageStart;

        public ExtendedDoctor(String name, String number, String nbEpisode, String ageStart) {
            super(name, number);
            this.nbEpisode = nbEpisode;
            this.ageStart = ageStart;
        }

        public String getNbEpisode() {
            return nbEpisode;
        }

        public void setNbEpisode(String nbEpisode) {
            this.nbEpisode = nbEpisode;
        }

        public String getAgeStart() {
            return ageStart;
        }

        public void setAgeStart(String ageStart) {
            this.ageStart = ageStart;
        }
    }
}
