package com.claude.lesson_6_config.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "library")
public class LibraryProperties {
    private String name;
    private int maxBooks;
    private String address;
    private OpeningHours openingHours = new OpeningHours();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxBooks() {
        return maxBooks;
    }

    public void setMaxBooks(int maxBooks) {
        this.maxBooks = maxBooks;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    public class OpeningHours{
        @Value("${library.opening-hours.weekdays}")
        private String weekdays;
        @Value("${library.opening-hours.weekends")
        private String weekends;

        public String getWeekdays() {
            return weekdays;
        }

        public void setWeekdays(String weekdays) {
            this.weekdays = weekdays;
        }

        public String getWeekends() {
            return weekends;
        }

        public void setWeekends(String weekends) {
            this.weekends = weekends;
        }
    }
}
