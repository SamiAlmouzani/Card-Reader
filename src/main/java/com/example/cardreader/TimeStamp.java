package com.example.cardreader;

import javafx.beans.property.SimpleStringProperty;

public class TimeStamp {
        public SimpleStringProperty idUser, name, time_in, time_out, role, status;

        public TimeStamp(String idUser,String name, String time_in, String time_out, String role, String status) {
            this.idUser = new SimpleStringProperty(idUser);
            this.name = new SimpleStringProperty(name);
            this.time_in = new SimpleStringProperty(time_in);
            this.time_out = new SimpleStringProperty(time_out);
            this.role = new SimpleStringProperty(role);
            this.status = new SimpleStringProperty(status);

        }

        public String getIdUser() {
            return idUser.get();
        }
        public String getTime_in() {
            return time_in.get();
        }
        public String getTime_out() { return time_out.get(); }
        public String getRole() {
            return role.get();
        }
        public String getStatus() {
            return status.get();
        }
        public String getName() {
            return name.get();
        }



}
