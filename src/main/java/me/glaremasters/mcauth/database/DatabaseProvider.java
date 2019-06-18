package me.glaremasters.mcauth.database;

public interface DatabaseProvider {

    void init();

    void insertUser(String uuid, String token);

    void setToken(String token, String uuid);

    boolean hasToken(String uuid);

}
