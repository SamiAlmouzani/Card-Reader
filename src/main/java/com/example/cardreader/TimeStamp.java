package com.example.cardreader;

import javafx.beans.property.SimpleStringProperty;

public class TimeStamp {
        SimpleStringProperty userId, time_in, time_out, role, status;

        public TimeStamp(String userId, String time_in, String time_out, String role, String status) {
            this.userId = new SimpleStringProperty(userId);
            this.time_in = new SimpleStringProperty(time_in);

            this.time_out = new SimpleStringProperty(time_out);
            this.role = new SimpleStringProperty(role);
            this.status = new SimpleStringProperty(status);

        }


        public String getUserId() {
            return userId.get();
        }
        public String getTimeIn() {
            return time_in.get();
        }
        public String getTimeOut() { return time_out.get(); }
        public String getRole() {
            return role.get();
        }
        public String getStatus() {
            return status.get();
        }
}
