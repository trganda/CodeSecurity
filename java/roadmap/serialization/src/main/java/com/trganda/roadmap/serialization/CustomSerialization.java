package com.trganda.roadmap.serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CustomSerialization implements java.io.Serializable {
    public String name;
    public transient int age;

    public CustomSerialization(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeInt(age);
        s.writeObject("This is a object");
    }

    private void readObject(java.io.ObjectInputStream s)
            throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        age = s.readInt();
        String message = (String) s.readObject();
        System.out.println(message);
    }

    @Override
    public String toString() {
        return "CustomSerialization{" + "name='" + name + '\'' + ", age=" + age + '}';
    }

    public static void main(String[] args) {
        try (ObjectOutputStream oos =
                new ObjectOutputStream(Files.newOutputStream(Paths.get("target/person.bin")))) {
            oos.writeObject(new CustomSerialization("trganda", 18));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois =
                new ObjectInputStream(Files.newInputStream(Paths.get("target/person.bin")))) {
            CustomSerialization person = (CustomSerialization) ois.readObject();
            System.out.println(person);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
