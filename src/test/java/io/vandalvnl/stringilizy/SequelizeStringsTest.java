package io.vandalvnl.stringilizy;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class SequelizeStringsTest {

    @Test
    public void createSql() throws IllegalAccessException, InvocationTargetException {
        User allan = new User("Allan", "29/08/1996", 1.8D);
        String user = SequelizeStrings.createSql("user", allan);
        System.out.println(user);
    }

    public class User {
        private String name;
        private String birthDate;
        private Number height;

        public User(String name, String birthDate, Number height) {
            this.name = name;
            this.birthDate = birthDate;
            this.height = height;
        }

        public String getName() {
            return name;
        }

        public User setName(String name) {
            this.name = name;
            return this;
        }

        public String getBirthDate() {
            return birthDate;
        }

        public User setBirthDate(String birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Number getHeight() {
            return height;
        }

        public User setHeight(Number height) {
            this.height = height;
            return this;
        }
    }
}